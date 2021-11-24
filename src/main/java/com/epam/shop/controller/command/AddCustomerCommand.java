package com.epam.shop.controller.command;

import com.epam.shop.controller.context.RequestContext;
import com.epam.shop.controller.context.ResponseContext;
import com.epam.shop.service.api.CustomerService;
import com.epam.shop.service.dto.CustomerDto;
import com.epam.shop.service.impl.CustomerServiceImpl;
import javax.servlet.http.HttpSession;

public class AddCustomerCommand implements Command{
    private static final Command INSTANCE = new AddCustomerCommand();
    private static final String CUSTOMER_ADD_JSP = "WEB-INF/jsp/add_customer.jsp";
    private static final String PROFILE_JSP = "WEB-INF/jsp/profile.jsp";
    private static final String CUSTOMER_ATTRIBUTE = "customer";
    private static final String FIRST_NAME_PARAMETER = "first_name";
    private static final String LAST_NAME_PARAMETER = "last_name";
    private static final String EMAIL_PARAMETER = "email";
    private static final String PHONE_NUMBER_PARAMETER = "phone_number";
    private static final String CARD_BALANCE_PARAMETER = "card_balance";
    private static final String ID_ATTRIBUTE = "id";
    private final CustomerService service = new CustomerServiceImpl();
    private static final ResponseContext CONTEXT = new ResponseContext() {
        @Override
        public String getPage() {
            return PROFILE_JSP;
        }

        @Override
        public boolean isRedirect() {
            return false;
        }
    };

    private static final ResponseContext ERROR_CONTEXT = new ResponseContext() {
        @Override
        public String getPage() {
            return CUSTOMER_ADD_JSP;
        }

        @Override
        public boolean isRedirect() {
            return false;
        }
    };

    private AddCustomerCommand(){}

    public static Command getInstance() {
        return INSTANCE;
    }

    @Override
    public ResponseContext execute(RequestContext context) {
        if(service.isValid(context)) {
            HttpSession session = context.getSession();
            CustomerDto customer = parseContext(context);
            Long id = (Long) context.getSession().getAttribute(ID_ATTRIBUTE);
            customer.setId(id);
            service.create(customer);
            session.setAttribute(CUSTOMER_ATTRIBUTE,customer);
            return CONTEXT;
        }else{
            context.addAttribute("error", "невалидные данные");//todo
            return ERROR_CONTEXT;
        }
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
