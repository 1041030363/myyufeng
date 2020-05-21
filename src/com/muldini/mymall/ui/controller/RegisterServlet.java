/*
COPYRIGHT (C) 2017 BY MULDINI. ALL RIGHTS RESERVED.
*/

package com.muldini.mymall.ui.controller;

import java.io.IOException;
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
 * 注册
 */
@WebServlet(name = "registerServlet",urlPatterns = { "/register" })
public final class RegisterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {

        // 生成随机验证码，并存入session
    	 Random random = new Random();
         int code = random.nextInt(900) + 100; // 返回100到999之间的随机整数

         String codeStr = String.valueOf(code); // int变String

         HttpSession session = request.getSession();

         session.setAttribute(StringConstant.SESSION_AUTH_CODE, codeStr); // 存入session

        
        RequestDispatcher dispatcher = request
                .getRequestDispatcher("/register.jsp");
        dispatcher.forward(request, response);
    }
    
    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {

        // 获取用户输入的验证码
        String authCode = request.getParameter("code");

        // 获取正确的验证码
        HttpSession session = request.getSession(false);
        if (session == null) {
            doGet(request, response);
            return;
        }
        
        String validCode = (String) session
                .getAttribute(StringConstant.SESSION_AUTH_CODE);

        // 若拿不到验证码，则抛出异常
        if (validCode == null) {
            throw new RuntimeException("验证码获取失败");
        }

        // 若验证码错误，则直接重新回到首页，否则保存注册信息
        if (!validCode.equals(authCode)) {
            request.setAttribute(StringConstant.REQ_MSG, "验证码错误");
            doGet(request, response);
            return;
        }

        // 获取用户信息，并放入User对象内
        String id = request.getParameter("user");
        String pwd = request.getParameter("pwd");
        User user = new User();
        user.setName(id);;
        user.setPassword(pwd);;

        // 调用后台模块保存注册信息
        UserService userService = UserService.INSTANCE;
        boolean result = userService.createUser(user);
        if (result) {
            request.setAttribute(StringConstant.REQ_MSG, "注册成功"); 
            RequestDispatcher dispatcher = request
                    .getRequestDispatcher("/registerSuccess.html");
            dispatcher.forward(request, response);
            return;
        } else {
            request.setAttribute(StringConstant.REQ_MSG, "用户id已存在"); 
        }
        doGet(request, response);
    }
}
