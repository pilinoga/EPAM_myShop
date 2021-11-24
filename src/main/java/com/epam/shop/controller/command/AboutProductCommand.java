package com.epam.shop.controller.command;

import com.epam.shop.controller.context.RequestContext;
import com.epam.shop.controller.context.ResponseContext;
import com.epam.shop.service.api.ProductService;
import com.epam.shop.service.dto.ProductDto;
import com.epam.shop.service.impl.ProductServiceImpl;
import java.util.List;

public class AboutProductCommand implements Command{
    private static final Command INSTANCE = new AboutProductCommand();
    private static final String JSP = "WEB-INF/jsp/products.jsp";
    private static final String ID_PRODUCT = "id_product";
    private static final String SPECIFICATION_ATTRIBUTE = "specification";
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

    private AboutProductCommand(){}

    public static Command getInstance() {
        return INSTANCE;
    }

    @Override
    public ResponseContext execute(RequestContext context) {
        long productId = Long.parseLong(context.getParameter(ID_PRODUCT));
        int page = Integer.parseInt((context.getParameter(PAGE_PARAMETER)));
        String specification = service.getById(productId).getSpecification();
        List<ProductDto> list = service.getRowsForPage(page);
        context.addAttribute(SPECIFICATION_ATTRIBUTE,specification);
        context.addAttribute(PRODUCTS_ATTRIBUTE,list);
        context.addAttribute(ID_PRODUCT,productId);
        context.addAttribute(PAGE_PARAMETER,page);
        return CONTEXT;

    }
}
