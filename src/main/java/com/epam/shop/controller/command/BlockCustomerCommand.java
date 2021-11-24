package com.epam.shop.controller.command;

import com.epam.shop.controller.context.RequestContext;
import com.epam.shop.controller.context.ResponseContext;
import com.epam.shop.service.api.CustomerService;
import com.epam.shop.service.dto.CustomerDto;
import com.epam.shop.service.impl.CustomerServiceImpl;
import java.util.List;

public class BlockCustomerCommand implements Command {
    private static final Command INSTANCE = new BlockCustomerCommand();
    private static final String JSP = "WEB-INF/jsp/customers.jsp";
    private final CustomerService service = new CustomerServiceImpl();
    private static final String ID_PARAMETER = "id";
    private static final String CUSTOMERS_ATTRIBUTE = "customers";
    private static final String PAGE_PARAMETER= "page";
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

    private BlockCustomerCommand(){}

    public static Command getInstance() {
        return INSTANCE;
    }

    @Override
    public ResponseContext execute(RequestContext context) {
        Long id = Long.valueOf(context.getParameter(ID_PARAMETER));
        int page = Integer.parseInt(context.getParameter(PAGE_PARAMETER));
        CustomerDto customer  = service.getById(id);
        customer.setBlock(!customer.getBlock());
        service.update(customer);
        List<CustomerDto> list = service.getRowsForPage(page);
        context.addAttribute(CUSTOMERS_ATTRIBUTE, list);
        context.addAttribute(PAGE_PARAMETER,page);
        return CONTEXT;
    }
}
