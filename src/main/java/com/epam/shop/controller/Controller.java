package com.epam.shop.controller;

import com.epam.shop.controller.command.Command;
import com.epam.shop.controller.context.RequestContextImpl;
import com.epam.shop.controller.context.ResponseContext;
import com.epam.shop.dao.Role;
import com.epam.shop.dao.connection.ConnectionPool;
import com.epam.shop.dao.exception.ConnectionException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import static java.util.Objects.isNull;

@WebServlet("/shop")
public class Controller extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(Controller.class);
    private static final String COMMAND_PARAM = "command";
    private static final String ROLE = "role";
    private static final String ACCESS_DENIED_PAGE = "WEB-INF/jsp/error.jsp";

    @Override
    public void init() throws ServletException {
        try {
            ConnectionPool.getInstance().initialize();
        } catch (ConnectionException e) {
            logger.error("Connection pool was not initialized");
        }
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        execute(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        execute(req, resp);
    }

    @Override
    public void destroy() {
        try {
            ConnectionPool.getInstance().shutDown();
        } catch (ConnectionException e) {
            logger.error("Connection pool was not shut down");
        }
        super.destroy();

    }

    private void process(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        final String name = req.getParameter(COMMAND_PARAM);
        Command command = Command.of(name);
        ResponseContext commandResult = command.execute(new RequestContextImpl(req));
        if(commandResult.isRedirect()){
            resp.sendRedirect(commandResult.getPage());
        }else{
            RequestDispatcher dispatcher = req.getRequestDispatcher(commandResult.getPage());
            dispatcher.forward(req,resp);
        }
    }

    private void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
         HttpSession session = req.getSession(true);
         if(isNull(session.getAttribute(ROLE))){
            session.setAttribute(ROLE, Role.UNKNOWN);
        }
        String commandName = req.getParameter(COMMAND_PARAM);
        Role userAccess = (Role) session.getAttribute(ROLE);
        if(Command.isEqualAccess(userAccess, commandName)){
            process(req,resp);
        }else{
            RequestDispatcher dispatcher = req.getRequestDispatcher(ACCESS_DENIED_PAGE);
            dispatcher.forward(req,resp);
        }
    }
}

