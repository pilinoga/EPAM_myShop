package com.epam.shop.controller.command;

import com.epam.shop.controller.context.RequestContext;
import com.epam.shop.controller.context.ResponseContext;
import com.epam.shop.service.api.OrderService;
import com.epam.shop.service.dto.OrderDto;
import com.epam.shop.service.impl.OrderServiceImpl;
import javax.servlet.http.HttpSession;

public class RefuseOrderCommand implements Command {
    private static final Command INSTANCE = new RefuseOrderCommand();
    private static final String JSP = "WEB-INF/jsp/basket.jsp";
    private static final String ID_ORDER_ATTRIBUTE = "id_order";
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

    private RefuseOrderCommand() {
    }

    public static Command getInstance() {
        return INSTANCE;
    }

    @Override
    public ResponseContext execute(RequestContext context) {
        HttpSession session = context.getSession();
        long orderId = (long) session.getAttribute(ID_ORDER_ATTRIBUTE);
        OrderDto order = orderService.getById(orderId);
        orderService.delete(order);
        session.removeAttribute(ID_ORDER_ATTRIBUTE);
        context.addAttribute("message_refuse","заказ отменен!"); //todo
        return CONTEXT;
    }
}
