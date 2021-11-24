package com.epam.shop.controller.command.transition;

import com.epam.shop.controller.command.Command;
import com.epam.shop.controller.context.RequestContext;
import com.epam.shop.controller.context.ResponseContext;

public class GoToEditProfileCommand implements Command {
    private static final Command INSTANCE = new GoToEditProfileCommand();
    private static final String JSP = "WEB-INF/jsp/edit_profile.jsp";
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

    private GoToEditProfileCommand(){}

    public static Command getInstance() {
        return INSTANCE;
    }

    @Override
    public ResponseContext execute(RequestContext context) {
        return CONTEXT;
    }
}

