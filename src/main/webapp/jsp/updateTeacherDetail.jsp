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
<div class="row">
    <div class="col-lg-3"></div>
    <div class="col-lg-6" style="text-align: center;margin: auto">
        <table class="table table-striped">
            <tr>
                <td style="font-size: 30px" colspan="2"><b>修改课程</b></td>
            </tr>
            <tr>
                <td style="float: left">课程名：</td>
                <td>
                    <c:forEach items="${coursesList}" var="courses" varStatus="v">
                        <input class="id" type="hidden" value="${courses.coursesid}"/>
                    </c:forEach>
                    <input type="text" name="coursesName" id="coursesName" placeholder="输入名字" value="${name}"/>
                </td>
            </tr>
            <tr>
                <td>
                    <button type="button" id="sub" class="btn btn-default">确认</button>
                </td>
                <td>
                    <button type="button" class="btn btn-default" onclick="history.back()">返回</button>
                </td>
            </tr>
        </table>
    </div>
</div>
</body>
<script type="text/javascript">
    $(function () {
        var ids = new Array();
        var i=0;
        $(".id").each(function () {
            ids[i] = parseInt($(this).val());
            i++;
        });
        $("#sub").bind("click",function () {
            var name = $("#coursesName").val();
            window.location.href="http://localhost:8888/studentInfoManager_war_exploded/updateTeacherDetail?" +
                "coursesName="+name+"&coursesIds="+ids;
        });
    });
</script>
</html>
