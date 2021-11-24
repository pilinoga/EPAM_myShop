package com.epam.shop.controller.command.transition;

import com.epam.shop.controller.command.Command;
import com.epam.shop.controller.context.RequestContext;
import com.epam.shop.controller.context.ResponseContext;

public class AboutCommand implements Command {
    private static final Command INSTANCE =  new AboutCommand();
    private static final String ABOUT_JSP = "WEB-INF/jsp/about.jsp";
    private static final ResponseContext CONTEXT = new ResponseContext() {
        @Override
        public String getPage() {
            return ABOUT_JSP;
        }

        @Override
        public boolean isRedirect() {
            return false;
        }
    };

    private AboutCommand(){}

    public static Command getInstance() {
        return INSTANCE;
    }

    @Override
    public ResponseContext execute(RequestContext context) {
        return CONTEXT;
    }
}
