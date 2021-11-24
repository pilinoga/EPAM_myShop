package com.epam.shop.controller.command.transition;

import com.epam.shop.controller.command.Command;
import com.epam.shop.controller.context.RequestContext;
import com.epam.shop.controller.context.ResponseContext;

public class GoToAddProductCommand implements Command {
    private static final Command INSTANCE = new GoToAddProductCommand();
    private static final String ADD_PRODUCT_JSP = "WEB-INF/jsp/add_product.jsp";
    private static final ResponseContext CONTEXT = new ResponseContext() {
        @Override
        public String getPage() {
            return ADD_PRODUCT_JSP;
        }

        @Override
        public boolean isRedirect() {
            return false;
        }
    };

    private GoToAddProductCommand(){}

    public static Command getInstance() {
        return INSTANCE;
    }

    @Override
    public ResponseContext execute(RequestContext context) {
        return CONTEXT;
    }
}
