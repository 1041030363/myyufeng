<%--
 COPYRIGHT (C) 2017 BY MULDINI. ALL RIGHTS RESERVED.
--%>
<%@page contentType="text/html;charset=utf-8"%>
<%@page import="java.util.Random"%>
<%@page import="com.muldini.mymall.common.StringConstant"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<title>注册</title>
<link rel="stylesheet" type="text/css" href="css/login.css" />
<link rel="icon" href="img/icon.jpg">
<script>
	window.onload = init;
	function init() {
		var registerBtn = document.getElementById("register-btn");
		if (registerBtn != null) {
			registerBtn.onclick = register;
		}
	}
	function register() {
		var userForm = document.getElementById("user-form");
		userForm.setAttribute("action", "register");
		userForm.submit();
	}
</script>
</head>
<body>
	<div class="header">
		<div class="header_n">
			<a href="index.html"> <img src="img/17play_v1.0.png">
			</a>
			<ul>
				<li><a href="index.html" >首页</a></li>
				<li><a href="" >全网搜索</a></li>

				<li><a href="" >个人中心</a></li>
				<li><a href="login" id="long" >登录</a></li>
				<li><a href="register" id="long" >注册</a></li>
			</ul>
		</div>
	</div>
	<div class="top">
		<h1>注册</h1>
		<hr />

	</div>
	<div class="login">
		<form action="login" method="post" id="user-form">
			<div>
				账号：<input type="text" name="user" required>
			</div>
			<div>
				密码：<input type="password" name="pwd" required>
			</div>
			<%
				// 用户输入的验证码
				String inputCode = request.getParameter("code");
				if (inputCode == null) {
					inputCode = "";
				}

				// 获取正确的验证码
				String validCode = (String) session.getAttribute("SESSION_AUTH_CODE");
				if (validCode == null) {
					validCode = "";
				}
			%>
			<div>
				验证码：<input type="text" name="code" value="<%=inputCode%>">
				<%=validCode%></div>
			<div>
				</button>
				
				<div onclick="javascript:location.href='register.jsp'">
					<button id="register-btn">注册</button>
				</div>
			</div>
		</form>
		<p>
			<%
				String msg = (String) request.getAttribute(StringConstant.REQ_MSG);
				if (msg != null) {
			%>
			<%=msg%>
			<%
      }
  %>
		</p>
	</div>
</body>
</html>