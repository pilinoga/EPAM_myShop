package com.epam.shop.controller.filter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

public class LanguageFilter implements Filter {
    private static final String LOCALE_PARAMETER = "locale";
    private static final String LANG_PARAMETER = "language";
    private static final String DEFAULT_LANG = "en";

    public void init(FilterConfig filterConfig) {}

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;

        if (isNull(req.getSession().getAttribute(LANG_PARAMETER))) {
            req.getSession().setAttribute(LANG_PARAMETER, DEFAULT_LANG);
        }
        if (nonNull(req.getSession().getAttribute(LANG_PARAMETER)) &&
                nonNull(req.getParameter(LOCALE_PARAMETER))){
            String langParam = req.getParameter(LOCALE_PARAMETER);
            req.getSession().setAttribute(LANG_PARAMETER, langParam);
        }
        chain.doFilter(request, response);
    }

    public void destroy() {}

}
