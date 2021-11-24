package com.epam.shop.controller.command;

import com.epam.shop.controller.context.RequestContext;
import com.epam.shop.controller.context.ResponseContext;
import com.epam.shop.service.api.CustomerService;
import com.epam.shop.service.dto.CustomerDto;
import com.epam.shop.service.impl.CustomerServiceImpl;
import javax.servlet.http.HttpSession;

public class EditProfileCommand implements Command{
    private static final Command INSTANCE = new EditProfileCommand();
    private static final String JSP = "WEB-INF/jsp/edit_profile.jsp";
    private static final String FIRST_NAME_PARAMETER = "first_name";
    private static final String LAST_NAME_PARAMETER = "last_name";
    private static final String EMAIL_PARAMETER = "email";
    private static final String PHONE_NUMBER_PARAMETER = "phone_number";
    private static final String CARD_BALANCE_PARAMETER = "card_balance";
    private static final String CUSTOMER_ATTRIBUTE = "customer";
    private static final String ID_ATTRIBUTE = "id";
    private final CustomerService service = new CustomerServiceImpl();
    private static final ResponseContext CONTEXT = new ResponseContext() {
        @Override
        public String getPage() {
            return JSP;
        }

        @Override
        public boolean isRedirect() {
            return false;
        }
    };

    private EditProfileCommand(){}

    public static Command getInstance() {
        return INSTANCE;
    }

    @Override
    public ResponseContext execute(RequestContext context) {
        if(service.isValid(context)){
            HttpSession session = context.getSession();
            Long id = (Long) context.getSession().getAttribute(ID_ATTRIBUTE);
            CustomerDto customerNew = parseContext(context);
            customerNew.setId(id);
            if(service.isInfoExist(id)){
                service.update(customerNew);
             }else{
                service.create(customerNew);
            }
            session.setAttribute(CUSTOMER_ATTRIBUTE,customerNew);
            context.addAttribute("edit_profile", "профиль обновлен"); //todo error mapper
        }else{
            context.addAttribute("error_edit", "невалидные данные");
        }
        return CONTEXT;
    }

    /**
     * Method to get parameters from request
     *
     * @param context contains request parameters
     * @return CustomerDto with filled fields from request
     */
    private CustomerDto parseContext(RequestContext context){
        String firstName = context.getParameter(FIRST_NAME_PARAMETER);
        String lastName = context.getParameter(LAST_NAME_PARAMETER);
        String email = context.getParameter(EMAIL_PARAMETER);
        long phoneNumber = Long.parseLong(context.getParameter(PHONE_NUMBER_PARAMETER));
        double cardBalance = Double.parseDouble(context.getParameter(CARD_BALANCE_PARAMETER));
        return new CustomerDto(firstName,lastName,email,phoneNumber,cardBalance,false);
    }
}
