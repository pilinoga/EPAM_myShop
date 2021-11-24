package com.epam.shop.controller.command;

import com.epam.shop.controller.context.RequestContext;
import com.epam.shop.controller.context.ResponseContext;
import com.epam.shop.dao.Role;
import com.epam.shop.service.api.UserService;
import com.epam.shop.service.dto.UserDto;
import com.epam.shop.service.impl.UserServiceImpl;

public class RegistrationCommand implements Command{
    private static final Command INSTANCE = new RegistrationCommand();
    private static final String ADD_CUSTOMER_JSP = "WEB-INF/jsp/add_customer.jsp";
    private static final String REGISTR_JSP = "WEB-INF/jsp/registration.jsp";
    private static final String LOGIN_PARAMETER = "login";
    private static final String PASSWORD_PARAMETER = "password";
    private static final String ID_ATTRIBUTE = "id";
    private static final String ROLE_ATTRIBUTE = "role";
    private final UserService service = new UserServiceImpl();
    private static final ResponseContext CONTEXT = new ResponseContext() {
        @Override
        public String getPage() {
            return ADD_CUSTOMER_JSP;
        }

        @Override
        public boolean isRedirect() {
            return false;
        }
    };
    private static final ResponseContext ERROR_CONTEXT = new ResponseContext() {
        @Override
        public String getPage() {
            return REGISTR_JSP;
        }

        @Override
        public boolean isRedirect() {
            return false;
        }
    };

    private RegistrationCommand(){}

    public static Command getInstance() {
        return INSTANCE;
    }

    @Override
    public ResponseContext execute(RequestContext context) {
        final String login = context.getParameter(LOGIN_PARAMETER);
        final String password = context.getParameter(PASSWORD_PARAMETER);
        UserDto user = new UserDto(login,password);
        boolean validation = service.isValid(user);
        if(validation && service.isUniqueLogin(user)){
            context.getSession().setAttribute(ROLE_ATTRIBUTE, Role.CUSTOMER);
            Long id = service.create(user).getId();
            context.getSession().setAttribute(ID_ATTRIBUTE,id);
            context.addAttribute("login", "аккаунт успешно создан");
            return CONTEXT;
        }else{
            if(!validation) {
                context.addAttribute("error_login","невалидные данные");// todo
                   }else{
                context.addAttribute("error_login_unique", "логин занят, придумайте другой");
            }
            return ERROR_CONTEXT;
        }
    }
}
