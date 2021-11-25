package com.epam.shop.controller.command;

import com.epam.shop.controller.context.RequestContext;
import com.epam.shop.controller.context.ResponseContext;
import com.epam.shop.service.api.ProductService;
import com.epam.shop.service.dto.ProductDto;
import com.epam.shop.service.impl.ProductServiceImpl;

public class EditProductCommand implements Command{
    private static final Command INSTANCE = new EditProductCommand();
    private static final String JSP = "WEB-INF/jsp/edit_product.jsp";
    private static final String ID_PARAMETER = "id";
    private static final String NAME_PARAMETER = "name";
    private static final String DESCRIPTION_PARAMETER = "description";
    private static final String PRICE_PARAMETER = "price";
    private static final String SPECIFICATION_PARAMETER = "specification";
    private static final String ERROR_ATTRIBUTE = "error_edit";
    private static final String EDIT_ATTRIBUTE = "edit_product";
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

    private EditProductCommand(){}

    public static Command getInstance() {
        return INSTANCE;
    }

    @Override
    public ResponseContext execute(RequestContext context) {
        if(service.isValid(context)){
            long id = Long.parseLong(context.getParameter(ID_PARAMETER));
            ProductDto product = parseContext(context);
            product.setId(id);
            service.update(product);
            context.addAttribute(EDIT_ATTRIBUTE, Message.UPDATED_PRODUCT);
        }else{
            context.addAttribute(ERROR_ATTRIBUTE, Message.INVALID_DATA);
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
        String price = context.getParameter(PRICE_PARAMETER);
        String specification = context.getParameter(SPECIFICATION_PARAMETER);
        return new ProductDto(name,description, Double.parseDouble(price),specification);
    }
}
