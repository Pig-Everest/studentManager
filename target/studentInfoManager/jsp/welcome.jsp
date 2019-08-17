<%--
  Created by IntelliJ IDEA.
  User: z6774
  Date: 2019/8/8
  Time: 10:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<% String path = request.getContextPath();%>
<% String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";%>
<base href="<%=basePath%>">
<html>
<head>
    <base href="<%=basePath%>">

    <title>main</title>
    <link rel="stylesheet" type="text/css"
          href="<%=path%>/css/bootstrap.min.css">
    <script type="text/javascript" src="<%=path%>/js/jquery-3.1.0.min.js"></script>

    <style type="text/css">
        *{
            padding: 0px;
            margin: 0px;
        }
    </style>

</head>
<body>
<div style="text-align: center;font-size: 60px">
    <b>欢迎管理员登录！！</b>
</div>
</body>
</html>
