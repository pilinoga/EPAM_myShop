package com.epam.shop.controller.command.transition;

import com.epam.shop.controller.command.Command;
import com.epam.shop.controller.context.RequestContext;
import com.epam.shop.controller.context.ResponseContext;
import com.epam.shop.dao.Role;
import com.epam.shop.service.api.OrderService;
import com.epam.shop.service.dto.OrderDto;
import com.epam.shop.service.impl.OrderServiceImpl;
import javax.servlet.http.HttpSession;
import java.util.List;

public class GoToOrderHistoryCommand implements Command {
    private static final Command INSTANCE = new GoToOrderHistoryCommand();
    private static final String JSP = "WEB-INF/jsp/order_history.jsp";
    private static final String ID_ATTRIBUTE = "id";
    private static final String ORDERS_ATTRIBUTE = "orders";
    private static final String ROLE_ATTRIBUTE = "role";
    private static final String PAGE_PARAMETER= "page";
    private final OrderService orderService = new OrderServiceImpl();
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

    private GoToOrderHistoryCommand (){}

    public static Command getInstance() {
        return INSTANCE;
    }

    @Override
    public ResponseContext execute(RequestContext context) {
        HttpSession session = context.getSession();
        Role role = (Role) session.getAttribute(ROLE_ATTRIBUTE);
        int page = Integer.parseInt(context.getParameter(PAGE_PARAMETER));
        List<OrderDto> list;
        if(role.equals(Role.ADMIN)){
            list = orderService.getRowsForPage(page);
        }else{
            long idCustomer = (long) session.getAttribute(ID_ATTRIBUTE);
            list = orderService.getForCustomer(idCustomer,page);
        }
        context.addAttribute(ORDERS_ATTRIBUTE,list);
        context.addAttribute(PAGE_PARAMETER,page);
        return CONTEXT;
    }
}

