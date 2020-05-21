/*
COPYRIGHT (C) 2017 BY MULDINI. ALL RIGHTS RESERVED.
*/

package com.muldini.mymall.ui.controller;

import java.io.IOException;
import java.util.Map;
import java.util.Random;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.muldini.mymall.biz.service.UserService;
import com.muldini.mymall.common.StringConstant;
import com.muldini.mymall.common.User;

/**
 * 登录
 */
@WebServlet(name = "loginServlet", urlPatterns = { "/login" })
public final class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action");

        // 若是退出登录，则删除会话中的用户id后，去到登录页
        if ("off".equals(action)) {
            HttpSession session = request.getSession(false);
            if (session != null) {
			    session.removeAttribute(StringConstant.SESSION_USER_ID); // 删除用户id
			}
            response.sendRedirect("login"); // 跳转到登录页
            return;
        }

        // 生成随机验证码，并存入session
        Random random = new Random();
        int code = random.nextInt(900) + 100; // 返回100到999之间的随机整数

        String codeStr = String.valueOf(code); // int变String

        HttpSession session = request.getSession();

        session.setAttribute(StringConstant.SESSION_AUTH_CODE, codeStr); // 存入session

        // 跳转到登录页
        RequestDispatcher dispatcher = request
                .getRequestDispatcher("/login.jsp");
        dispatcher.forward(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {

        // 保存用户登录信息
        String userId = request.getParameter("user");
        HttpSession session = request.getSession(false);

        // 若Session未创建或已过期，则去登录
        if (session == null) {
            doGet(request, response);
            return;
        }

        if (userId != null && !userId.isEmpty()) {
            session.setAttribute(StringConstant.SESSION_USER_ID, userId);
        }

        // 获取用户输入的验证码
        String authCode = request.getParameter("code");

        // 获取正确的验证码
        String validCode = (String) session
                .getAttribute(StringConstant.SESSION_AUTH_CODE);

        // 验证码校验
        // 若拿不到验证码，则抛出异常
        if (validCode == null) {
            throw new RuntimeException("验证码获取失败");
        }

        // 若验证码错误，则到登录页
        if (!validCode.equals(authCode)) {
            request.setAttribute(StringConstant.REQ_MSG, "验证码错误");
            doGet(request, response);
            return;
        }

        UserService userService = UserService.INSTANCE;
        User user = new User();
        user.setName(userId);
        user.setPassword(request.getParameter("pwd"));
        
        Map result = userService.login(user); // 登录
        
        // 处理结果
        String statusCode = (String) result.get("STATUS_CODE");

        // 若为10，表示登录成功，则到购物页
        if ("10".equals(statusCode)) {
            response.sendRedirect("index.html");
            return;
        }

        String msg = (String) result.get("MSG");
        request.setAttribute(StringConstant.REQ_MSG, msg);
        doGet(request, response);
    }
}
