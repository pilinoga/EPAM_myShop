package com.epam.shop.controller.command;

import com.epam.shop.controller.context.RequestContext;
import com.epam.shop.controller.context.ResponseContext;
import com.epam.shop.service.api.OrderItemService;
import com.epam.shop.service.api.OrderService;
import com.epam.shop.service.dto.OrderDto;
import com.epam.shop.service.dto.OrderItemDto;
import com.epam.shop.service.dto.ProductDto;
import com.epam.shop.service.impl.OrderItemServiceImpl;
import com.epam.shop.service.impl.OrderServiceImpl;
import javax.servlet.http.HttpSession;
import java.util.List;

public class RemoveProductFromOrderCommand implements Command{
    private static final Command INSTANCE = new RemoveProductFromOrderCommand();
    private static final String JSP = "WEB-INF/jsp/basket.jsp";
    private static final String ID_PRODUCT_PARAMETER = "product_id";
    private static final String ID_ORDER_ATTRIBUTE = "id_order";
    private static final String PRODUCTS_ATTRIBUTE = "products";
    private static final String ORDER_PRICE_ATTRIBUTE = "order_price";
    private final OrderService orderService = new OrderServiceImpl();
    private final OrderItemService itemService = new OrderItemServiceImpl();
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

    private RemoveProductFromOrderCommand(){}

    public static Command getInstance() {
        return INSTANCE;
    }

    @Override
    public ResponseContext execute(RequestContext context) {
        HttpSession session = context.getSession();
        long orderId = (long) session.getAttribute(ID_ORDER_ATTRIBUTE);
        long removeProductId = Long.parseLong(context.getParameter(ID_PRODUCT_PARAMETER));
        itemService.delete(new OrderItemDto(orderId,removeProductId));
        List<ProductDto> products = orderService.getProducts(orderId);
        double orderPrice = orderService.getOrderPrice(products);
        context.addAttribute(ORDER_PRICE_ATTRIBUTE,orderPrice);
        context.addAttribute(PRODUCTS_ATTRIBUTE,products);
        if(products.size() == 0){
            OrderDto order = orderService.getById(orderId);
            orderService.delete(order);
            session.removeAttribute(ID_ORDER_ATTRIBUTE);
        }
        return CONTEXT;
    }
}
