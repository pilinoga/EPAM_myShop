package com.epam.shop.controller.context;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class RequestContextImpl implements RequestContext{
    private final HttpServletRequest request;

    public RequestContextImpl(HttpServletRequest request) {
        this.request = request;
            }

    @Override
    public void addAttribute(String name, Object attribute) {
        request.setAttribute(name,attribute);
    }

    @Override
    public Object getAttribute(String key) {
        return request.getAttribute(key);
            }

    @Override
    public String getParameter(String name) {
        return request.getParameter(name);
    }

    @Override
    public HttpSession getSession() {
        return request.getSession();
    }

    @Override
    public String getContextPath(){
        return request.getContextPath();
    }

    @Override
    public void removeAttribute(String name) {
        request.removeAttribute(name);
    }
}
