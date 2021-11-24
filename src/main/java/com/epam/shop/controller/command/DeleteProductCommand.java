package com.epam.shop.controller.command;

import com.epam.shop.controller.context.RequestContext;
import com.epam.shop.controller.context.ResponseContext;
import com.epam.shop.service.api.ProductService;
import com.epam.shop.service.dto.ProductDto;
import com.epam.shop.service.impl.ProductServiceImpl;

public class DeleteProductCommand implements Command{
    private static final Command INSTANCE = new DeleteProductCommand();
    private static final String JSP = "WEB-INF/jsp/edit_product.jsp";
    private static final String ID_PARAMETER = "id";
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

    private DeleteProductCommand(){}

    public static Command getInstance() {
        return INSTANCE;
    }

    @Override
    public ResponseContext execute(RequestContext context) {
       long id = Long.parseLong(context.getParameter(ID_PARAMETER));
        ProductDto product = service.getById(id);
        service.delete(product);
        context.addAttribute("action","продукт удален из БД!"); //todo
        return CONTEXT;
    }
}
