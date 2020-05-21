<%--
 COPYRIGHT (C) 2014-2015 BY MULDINI. ALL RIGHTS RESERVED.
--%>
<%@page contentType="text/html;charset=utf-8"%>
<html lang="zh-cn">
<head>
<title>Error</title>
</head>
<body>
  <p>
   对不起<br>SORRY<br>出错了<br>
  </p>
  <%
      // 当前页面被设为错误页面时（在web.xml中配置）
      // 记错误日志。先记录HTTP错误状态码。
      Integer errorStatusCode = (Integer) request
              .getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
      System.err.println("HTTP Error Status Code=" + errorStatusCode);

      // 若对应的错误信息不为空，则将其记入日志。
      String errorMessage = (String) request
              .getAttribute(RequestDispatcher.ERROR_MESSAGE);
      if (errorMessage != null && !errorMessage.isEmpty()) {
          System.err.println("HTTP Error Message=" + errorMessage);
      }

      // 当错误发生时，返回的状态代码通常是4**或5**。
      // 为了在ie11中显示该错误页面，需将返回的状态代码改为200，即正常返回。
      // firefox55和chrome56中无需如此修改
      response.setStatus(HttpServletResponse.SC_OK);
  %>
</body>
</html>