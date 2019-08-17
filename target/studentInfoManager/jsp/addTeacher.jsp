<%--
  Created by IntelliJ IDEA.
  User: z6774
  Date: 2019/8/11
  Time: 20:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<% String path = request.getContextPath();%>
<% String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";%>
<base href="<%=basePath%>">
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" type="text/css"
          href="<%=path%>/css/bootstrap.min.css">
    <script type="text/javascript" src="<%=path%>/js/jquery-3.1.0.min.js"></script>
    <style type="text/css">
        * {
            text-align: center;
        }
    </style>
</head>
<body>
<form action="<%=path%>/insertTeacher">
    <div class="row">
        <div class="col-lg-3"></div>
        <div class="col-lg-6" style="text-align: center;margin: auto">
            <table class="table table-striped">
                <tr>
                    <td style="font-size: 30px" colspan="2"><b>添加班级</b></td>
                </tr>
                <tr>
                    <td style="float: left">教师名：</td>
                    <td><input type="text" name="teachername" placeholder="输入名字"/></td>
                </tr>
                <tr>
                    <td>
                        <button type="submit" class="btn btn-default">确认</button>
                    </td>
                    <td>
                        <button type="button" class="btn btn-default" onclick="history.back()">返回</button>
                    </td>
                </tr>
            </table>
        </div>
    </div>
</form>
</body>
</html>
