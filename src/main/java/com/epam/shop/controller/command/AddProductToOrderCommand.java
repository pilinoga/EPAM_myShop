package com.epam.shop.controller.command;

import com.epam.shop.controller.context.RequestContext;
import com.epam.shop.controller.context.ResponseContext;
import com.epam.shop.service.api.OrderItemService;
import com.epam.shop.service.api.OrderService;
import com.epam.shop.service.api.ProductService;
import com.epam.shop.service.dto.OrderDto;
import com.epam.shop.service.dto.OrderItemDto;
import com.epam.shop.service.dto.ProductDto;
import com.epam.shop.service.impl.OrderItemServiceImpl;
import com.epam.shop.service.impl.OrderServiceImpl;
import com.epam.shop.service.impl.ProductServiceImpl;
import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import static java.util.Objects.isNull;

public class AddProductToOrderCommand implements Command{
    private static final Command INSTANCE = new AddProductToOrderCommand();
    private static final String JSP = "WEB-INF/jsp/products.jsp";
    private static final String ID_CUSTOMER = "id";
    private static final String ID_PRODUCT = "id";
    private static final String ID_ORDER = "id_order";
    private static final String PRODUCTS_ATTRIBUTE = "products";
    private static final String ADD_ATTRIBUTE = "action";
    private static final String PAGE_PARAMETER= "page";
    private final OrderService serviceOrder = new OrderServiceImpl();
    private final OrderItemService serviceItem = new OrderItemServiceImpl();
    private final ProductService serviceProduct = new ProductServiceImpl();
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

    private AddProductToOrderCommand(){}

    public static Command getInstance() {
        return INSTANCE;
    }

    @Override
    public ResponseContext execute(RequestContext context) {
        HttpSession session = context.getSession();
        long customerId = (long) session.getAttribute(ID_CUSTOMER);
        long productId = Long.parseLong(context.getParameter(ID_PRODUCT));
        int page = Integer.parseInt((context.getParameter(PAGE_PARAMETER)));
        if (isNull(session.getAttribute(ID_ORDER))) {
            OrderDto order = new OrderDto();
            order.setCustomerId(customerId);
            order.setOrderDate(Date.valueOf(LocalDate.now()));
            long orderId = serviceOrder.create(order).getId();
            session.setAttribute(ID_ORDER, orderId);
            OrderItemDto orderItemDto = new OrderItemDto();
            orderItemDto.setOrderId(orderId);
            orderItemDto.setProductId(productId);
            serviceItem.create(orderItemDto);
        }else{
            OrderItemDto orderItemDto = new OrderItemDto();
            long orderId = (long) session.getAttribute(ID_ORDER);
            orderItemDto.setOrderId(orderId);
            orderItemDto.setProductId(productId);
            serviceItem.create(orderItemDto);
        }
        List<ProductDto> products = serviceProduct.getRowsForPage(page);
        context.addAttribute(PRODUCTS_ATTRIBUTE, products);
        context.addAttribute(PAGE_PARAMETER,page);
        context.addAttribute(ADD_ATTRIBUTE,true);
        return CONTEXT;
    }
}
