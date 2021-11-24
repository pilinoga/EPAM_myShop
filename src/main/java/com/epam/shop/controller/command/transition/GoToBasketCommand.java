package com.epam.shop.controller.command.transition;

import com.epam.shop.controller.command.Command;
import com.epam.shop.controller.context.RequestContext;
import com.epam.shop.controller.context.ResponseContext;
import com.epam.shop.service.api.OrderService;
import com.epam.shop.service.dto.ProductDto;
import com.epam.shop.service.impl.OrderServiceImpl;
import javax.servlet.http.HttpSession;
import java.util.List;

public class GoToBasketCommand implements Command {
    private static final Command INSTANCE =  new GoToBasketCommand();
    private static final String JSP = "WEB-INF/jsp/basket.jsp";
    private static final String ID_ORDER = "id_order";
    private static final String PRODUCTS_ATTRIBUTE = "products";
    private static final String ORDER_PRICE_ATTRIBUTE = "order_price";
    private final OrderService service = new OrderServiceImpl();
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

    private GoToBasketCommand(){}

    public static Command getInstance() {
        return INSTANCE;
    }

    @Override
    public ResponseContext execute(RequestContext context){
        HttpSession session = context.getSession();
        long orderId = (long) session.getAttribute(ID_ORDER);
        List<ProductDto> products = service.getProducts(orderId);
        double orderPrice = service.getOrderPrice(products);
        context.addAttribute(ORDER_PRICE_ATTRIBUTE,orderPrice);
        context.addAttribute(PRODUCTS_ATTRIBUTE,products);
        return CONTEXT;
    }
}
