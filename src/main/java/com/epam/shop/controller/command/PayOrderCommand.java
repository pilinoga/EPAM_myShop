package com.epam.shop.controller.command;

import com.epam.shop.controller.context.RequestContext;
import com.epam.shop.controller.context.ResponseContext;
import com.epam.shop.service.api.CustomerService;
import com.epam.shop.service.api.OrderService;
import com.epam.shop.service.dto.CustomerDto;
import com.epam.shop.service.dto.OrderDto;
import com.epam.shop.service.dto.ProductDto;
import com.epam.shop.service.impl.CustomerServiceImpl;
import com.epam.shop.service.impl.OrderServiceImpl;
import javax.servlet.http.HttpSession;
import java.util.List;

public class PayOrderCommand implements Command{
    private static final Command INSTANCE = new PayOrderCommand();
    private static final String JSP = "WEB-INF/jsp/basket.jsp";
    private static final String ID_ORDER_ATTRIBUTE = "id_order";
    private static final String ORDER_PRICE_ATTRIBUTE = "order_price";
    private static final String PRODUCTS_ATTRIBUTE = "products";
    private static final String ID_CUSTOMER = "id";
    private static final String ERROR_ATTRIBUTE = "error";
    private static final String BLOCK_ATTRIBUTE = "block";
    private static final String PAY_ATTRIBUTE = "pay";
    private final OrderService orderService = new OrderServiceImpl();
    private final CustomerService customerService = new CustomerServiceImpl();
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

    private PayOrderCommand(){}

    public static Command getInstance() {
        return INSTANCE;
    }

    @Override
    public ResponseContext execute(RequestContext context) {
        HttpSession session = context.getSession();
        long customerId = (long) session.getAttribute(ID_CUSTOMER);
        long orderId = (long) session.getAttribute(ID_ORDER_ATTRIBUTE);
        List<ProductDto> products = orderService.getProducts(orderId);
        double orderPrice = orderService.getOrderPrice(products);
        CustomerDto customer = customerService.getById(customerId);
        OrderDto order = orderService.getById(orderId);
        if (!customer.getBlock()) {
            if (customer.getCardBalance() < orderPrice){
                orderService.delete(order);
                context.addAttribute(ERROR_ATTRIBUTE,true);
            }else {
                double newBalance = customer.getCardBalance() - orderPrice;
                customer.setCardBalance(newBalance);
                order.setPrice(orderPrice);
                order.setStatus(true);
                customerService.update(customer);
                orderService.update(order);
                context.addAttribute(PAY_ATTRIBUTE,true);
            }
        }else{
            orderService.delete(order);
            context.addAttribute(BLOCK_ATTRIBUTE,true);
        }
        session.removeAttribute(ID_ORDER_ATTRIBUTE);
        context.addAttribute(PRODUCTS_ATTRIBUTE,products);
        context.addAttribute(ORDER_PRICE_ATTRIBUTE, orderPrice);
        context.addAttribute(ID_ORDER_ATTRIBUTE, orderId);
        return CONTEXT;
    }
}

