/*
COPYRIGHT (C) 2017 BY MULDINI. ALL RIGHTS RESERVED.
*/

package com.muldini.mymall.ui.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

/**
 * 设置编码或解码字符集
 */
@WebFilter(urlPatterns = {"/login", "/register"})
public final class CharsetFilter implements Filter {

    /**
     * @see Filter#destroy()
     */
    public void destroy() {
    }

    /**
     * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
     */
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException {
        
        // 解决POST请求中的中文乱码，设定解码字符集
        request.setCharacterEncoding("utf-8");

        // pass the request along the filter chain
        chain.doFilter(request, response);
    }

    /**
     * @see Filter#init(FilterConfig)
     */
    public void init(FilterConfig fConfig) throws ServletException {
    }

}
