<%--
  Created by IntelliJ IDEA.
  User: z6774
  Date: 2019/8/8
  Time: 10:10
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
<div class="container-fluid">
    <div class="row" style="height: 20%">
        <div class="col-lg-12" style="border-bottom: 1px gray solid">
            <div style="color: black;font-size: 40px;padding-top: 5%"><b id="top">学生信息管理系统</b></div>
        </div>
    </div>
    <div class="row" style="height: 80%">
        <div class="col-lg-3" style="border-right: 1px gray solid">
            <div style="font-size: 20px;text-align: center;padding: 5%"><button class="btn btn-info" id="userManager" >用户管理</button></div>
            <div style="font-size: 20px;text-align: center;padding: 5%"><button class="btn btn-info" id="classManager" >班级管理</button></div>
            <div style="font-size: 20px;text-align: center;padding: 5%"><button class="btn btn-info" id="coursesManager" >课程管理</button></div>
            <div style="font-size: 20px;text-align: center;padding: 5%"><button class="btn btn-info" id="teacherManager" >教师管理</button></div>
            <div style="font-size: 20px;text-align: center;padding: 5%"><button class="btn btn-info" id="studentManager" >学生管理</button></div>
            <div style="font-size: 20px;text-align: center;padding: 5%"><button class="btn btn-info" id="examManager" >成绩管理</button></div>
            <div style="font-size: 20px;text-align: center;padding: 5%"><button class="btn btn-info" id="out" >登录/退出系统</button></div>
        </div>
        <div class="col-lg-9" style="">
            <iframe id="iframe" src="jsp/welcome.jsp" width="100%" height="80%">
            </iframe>
        </div>
    </div>
</div>
</body>
<script type="text/javascript">
    $(function () {
        $("#top").bind("click",function () {
            $("#iframe").attr("src","jsp/welcome.jsp");
        });
        $("#classManager").bind("click",function () {
            $("#iframe").attr("src","<%=path%>/selectClasses/1");
        });
        $("#coursesManager").bind("click",function () {
            $("#iframe").attr("src","<%=path%>/selectAllCourses/1");
        });
        $("#teacherManager").bind("click",function () {
            $("#iframe").attr("src","<%=path%>/selectAllTeacher/1");
        });
        $("#studentManager").bind("click",function () {
            $("#iframe").attr("src","<%=path%>/selectAllStudent/1");
        });
    });
</script>
</html>
