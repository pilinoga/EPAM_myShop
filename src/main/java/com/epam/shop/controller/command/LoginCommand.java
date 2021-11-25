package com.epam.shop.controller.command;

import com.epam.shop.controller.context.RequestContext;
import com.epam.shop.controller.context.ResponseContext;
import com.epam.shop.dao.Role;
import com.epam.shop.service.api.CustomerService;
import com.epam.shop.service.api.UserService;
import com.epam.shop.service.dto.CustomerDto;
import com.epam.shop.service.dto.UserDto;
import com.epam.shop.service.impl.CustomerServiceImpl;
import com.epam.shop.service.impl.UserServiceImpl;
import javax.servlet.http.HttpSession;
import static java.util.Objects.nonNull;

public class LoginCommand implements Command {
    private static final Command INSTANCE = new LoginCommand();
    private static final String PROFILE_JSP = "WEB-INF/jsp/profile.jsp";
    private static final String LOGIN_JSP = "WEB-INF/jsp/login.jsp";
    private static final String LOGIN_PARAMETER = "login";
    private static final String PASSWORD_PARAMETER = "password";
    private static final String ID_ATTRIBUTE = "id";
    private static final String CUSTOMER_ATTRIBUTE = "customer";
    private static final String ROLE_PARAMETER = "role";
    private static final String ERROR = "error_login";
    private final UserService userService = new UserServiceImpl();
    private final CustomerService customerService = new CustomerServiceImpl();
    private static final ResponseContext LOGIN_CONTEXT = new ResponseContext() {
        @Override
        public String getPage() {
            return LOGIN_JSP;
        }

        @Override
        public boolean isRedirect() {
            return false;
        }
    };
    private static final ResponseContext PROFILE_CONTEXT = new ResponseContext() {
        @Override
        public String getPage() {
            return PROFILE_JSP;
        }

        @Override
        public boolean isRedirect() {
            return false;
        }
    };

    private LoginCommand() {}

    public static Command getInstance() {
        return INSTANCE;
    }

    @Override
    public ResponseContext execute(RequestContext context) {
        final String login = context.getParameter(LOGIN_PARAMETER);
        final String password = context.getParameter(PASSWORD_PARAMETER);
        UserDto userFromForm = new UserDto(login,password);
        HttpSession session = context.getSession();
        if (nonNull(session.getAttribute(ID_ATTRIBUTE))) {
            return moveToPage(context);
        }
        if (userService.isValid(userFromForm)&&
                userService.isExist(userFromForm)) {
            UserDto userFromDB = userService.getByLogin(login);
            final Role role = userFromDB.getRole();
            Long id = userFromDB.getId();
            context.getSession().setAttribute(ROLE_PARAMETER, role);
            context.getSession().setAttribute(ID_ATTRIBUTE, id);
        }
        return moveToPage(context);
    }

    /**
     * Method to return home page for user
     *
     * @param context contains in session user role
     * @return response page for user
     */
    private ResponseContext moveToPage(RequestContext context) {
        HttpSession session = context.getSession();
        final Role role = (Role) session.getAttribute(ROLE_PARAMETER);
        if (role.equals(Role.ADMIN)) {
            return PROFILE_CONTEXT;
        }
        if (role.equals(Role.CUSTOMER)) {
            Long id = (Long) session.getAttribute(ID_ATTRIBUTE);
            if(customerService.isInfoExist(id)){
                CustomerDto customer = customerService.getById(id);
                context.getSession().setAttribute(CUSTOMER_ATTRIBUTE, customer);
            }
            return PROFILE_CONTEXT;
        } else {
            context.addAttribute(ERROR, Message.ERROR_LOGIN);
            return LOGIN_CONTEXT;
        }
    }
}
