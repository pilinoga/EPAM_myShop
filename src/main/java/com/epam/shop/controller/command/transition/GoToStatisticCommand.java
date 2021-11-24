package com.epam.shop.controller.command.transition;

import com.epam.shop.controller.command.Command;
import com.epam.shop.controller.context.RequestContext;
import com.epam.shop.controller.context.ResponseContext;
import com.epam.shop.service.api.CustomerService;
import com.epam.shop.service.api.OrderItemService;
import com.epam.shop.service.api.ProductService;
import com.epam.shop.service.dto.CustomerDto;
import com.epam.shop.service.dto.OrderItemDto;
import com.epam.shop.service.dto.ProductDto;
import com.epam.shop.service.impl.CustomerServiceImpl;
import com.epam.shop.service.impl.OrderItemServiceImpl;
import com.epam.shop.service.impl.ProductServiceImpl;
import java.util.List;

public class GoToStatisticCommand implements Command {
    private static final Command INSTANCE =  new GoToStatisticCommand();
    private static final String JSP = "WEB-INF/jsp/statistic.jsp";
    private static final String CUSTOMERS_ATTRIBUTE = "customers";
    private static final String ITEMS_ATTRIBUTE = "items";
    private static final String PRODUCTS_ATTRIBUTE = "products";
    private static final String PRODUCTS_ALL_ATTRIBUTE = "productsAll";
    private final CustomerService customerService = new CustomerServiceImpl();
    private final ProductService productService = new ProductServiceImpl();
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

    private GoToStatisticCommand(){}

    public static Command getInstance() {
        return INSTANCE;
    }

    @Override
    public ResponseContext execute(RequestContext context) {
        List<OrderItemDto> topItems = itemService.getQuantity();
        List<ProductDto> products = productService.getTopSales();
        List<ProductDto> productsAll = productService.getAll();
        List<CustomerDto> list = customerService.getAll();
        context.addAttribute(ITEMS_ATTRIBUTE,topItems);
        context.addAttribute(PRODUCTS_ATTRIBUTE,products);
        context.addAttribute(PRODUCTS_ALL_ATTRIBUTE ,productsAll);
        context.addAttribute(CUSTOMERS_ATTRIBUTE, list);
        return CONTEXT;
    }

}
