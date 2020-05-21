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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.muldini.mymall.common.StringConstant;

/**
 * 登录管理
 */
@WebFilter(urlPatterns = { "/shop", "/shop.jsp" })
public final class LoginFilter implements Filter {

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

        // 登录管理，即若会话过期或没有登录则去登录页

        HttpServletRequest httpReq = (HttpServletRequest) request; // 为用getSession方法
        HttpServletResponse httpResp = (HttpServletResponse) response; // 为用sendRedirect方法

        HttpSession session = httpReq.getSession(false);        
        if (session == null) {
            httpResp.sendRedirect("login"); // 若会话过期则去登录
            return;
        }

        String userId = (String) session
                .getAttribute(StringConstant.SESSION_USER_ID);
        if (userId == null) {
            httpResp.sendRedirect("login"); // 若会话中没有userId，表示没有登录，则去登录
            return;
        }

        // pass the request along the filter chain
        chain.doFilter(request, response);
    }

    /**
     * @see Filter#init(FilterConfig)
     */
    public void init(FilterConfig fConfig) throws ServletException {
    }

}
