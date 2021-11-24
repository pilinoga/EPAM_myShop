package com.epam.shop.controller.context;

import javax.servlet.http.HttpSession;

public interface RequestContext {
    void addAttribute(String name, Object attribute);
    String getParameter(String name);
    Object getAttribute(String key);
    HttpSession getSession();
    String getContextPath();
    void removeAttribute(String name);

}
