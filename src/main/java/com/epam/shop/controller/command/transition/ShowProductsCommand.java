package com.epam.shop.controller.command.transition;

import com.epam.shop.controller.command.Command;
import com.epam.shop.controller.context.RequestContext;
import com.epam.shop.controller.context.ResponseContext;
import com.epam.shop.service.api.ProductService;
import com.epam.shop.service.dto.ProductDto;
import com.epam.shop.service.impl.ProductServiceImpl;
import java.util.List;

public class ShowProductsCommand implements Command {
    private static final Command INSTANCE = new ShowProductsCommand();
    private static final String JSP = "WEB-INF/jsp/products.jsp";
    private static final String PRODUCTS_ATTRIBUTE = "products";
    private static final String PAGE_PARAMETER= "page";
    private final ProductService service = new ProductServiceImpl();
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

    private ShowProductsCommand(){}

    public static Command getInstance() {
        return INSTANCE;
    }

    @Override
    public ResponseContext execute(RequestContext context) {
        int page = Integer.parseInt(context.getParameter(PAGE_PARAMETER));
        List<ProductDto> list = service.getRowsForPage(page);
        context.addAttribute(PRODUCTS_ATTRIBUTE,list);
        context.addAttribute(PAGE_PARAMETER,page);
        return CONTEXT;
    }
}
