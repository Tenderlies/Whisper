package com.tosh.whisper;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CustomFilter implements Filter {

    @Override
    public void destroy() {
        // ...
        return;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        if (request instanceof HttpServletRequest && response instanceof HttpServletResponse) {
            HttpServletRequest httpRequest = (HttpServletRequest) request;
            HttpServletResponse httpResponse = (HttpServletResponse) response;

            httpRequest.setCharacterEncoding("utf-8");
            httpResponse.setCharacterEncoding("utf-8");

            System.out.println(
                    httpRequest.getMethod() + " " + httpRequest.getServletPath() + " " + httpRequest.getRemoteAddr());

            chain.doFilter(httpRequest, httpResponse);

            System.out.println(httpResponse.getStatus() + " " + httpResponse.getContentType() + "\n");
        }

    }

    @Override
    public void init(FilterConfig config) throws ServletException {
        // ...
        return;
    }

}
