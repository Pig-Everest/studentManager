<%--
  Created by IntelliJ IDEA.
  User: z6774
  Date: 2019/8/7
  Time: 8:50
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
<div class="container">
    <div style="font-size: 20px;text-align: left;height: 50px;border-bottom: 1px gray solid"><b>课程${name}->详细信息</b></div>
    <br> <br>
    <div class="table-responsive">
        <div class="form-group">
            <label class="col-sm-12 control-label" style="text-align: center">开设该课程的班级信息</label>
        </div>
        <table
                class="table table-condensed table-hover table-bordered table-striped">
            <tr>
                <td>课程</td>
                <td id="test">班级</td>
                <td>学期</td>
                <td>学分</td>
            </tr>

            <c:forEach items="${coursesList}" var="courses" varStatus="u">
                <tr>
                    <td>${courses.coursesname}</td>
                    <td>${classesList[u.index].classesname}</td>
                    <td>${courses.term}</td>
                    <td>${courses.cost}</td>
                </tr>
            </c:forEach>
        </table>
        <div style="text-align: center;">
            <input id="back" class="btn btn-default" type="button" value="返回"/>
        </div>
    </div>
</div>
</body>
<script type="text/javascript">
    $(function () {
        $("#back").bind("click", function () {
            window.history.back();
        });
    });
</script>
</html>
