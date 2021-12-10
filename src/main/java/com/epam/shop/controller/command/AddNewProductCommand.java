package com.epam.shop.controller.command;

import com.epam.shop.controller.context.RequestContext;
import com.epam.shop.controller.context.ResponseContext;
import com.epam.shop.service.api.ProductService;
import com.epam.shop.service.dto.ProductDto;
import com.epam.shop.service.impl.ProductServiceImpl;

public class AddNewProductCommand implements Command{
    private static final Command INSTANCE = new AddNewProductCommand();
    private static final String JSP = "WEB-INF/jsp/add_product.jsp";
    private static final String NAME_PARAMETER = "name";
    private static final String DESCRIPTION_PARAMETER = "description";
    private static final String SPECIFICATION_PARAMETER = "specification";
    private static final String PRICE_PARAMETER = "price";
    private static final String ADD_PRODUCT = "add_product";
    private static final String PRODUCT_ERROR = "error_product";
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

    private AddNewProductCommand(){}

    public static Command getInstance() {
        return INSTANCE;
    }

    @Override
    public ResponseContext execute(RequestContext context) {
        if(service.isValid(context)){
            ProductDto product = parseContext(context);
            service.create(product);
              context.addAttribute(ADD_PRODUCT, true);
        }else{
            context.addAttribute(PRODUCT_ERROR, true);
        }
        return CONTEXT;
    }

    /**
     * Method to get parameters from request
     *
     * @param context contains request parameters
     * @return ProductDto with filled fields from request
     */
    private ProductDto parseContext(RequestContext context){
        String name = context.getParameter(NAME_PARAMETER);
        String description = context.getParameter(DESCRIPTION_PARAMETER);
        double price = Double.parseDouble(context.getParameter(PRICE_PARAMETER));
        String specification = context.getParameter(SPECIFICATION_PARAMETER);
        return new ProductDto(name,description,price,specification);
    }
}
