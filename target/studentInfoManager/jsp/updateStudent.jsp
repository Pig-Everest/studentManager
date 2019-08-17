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
<form action="<%=path%>/updateStudent">
    <div class="row">
        <div class="col-lg-3"></div>
        <div class="col-lg-6" style="text-align: center;margin: auto">
            <table class="table table-striped">
                <tr>
                    <td style="font-size: 30px" colspan="2"><b>修改学生</b></td>
                </tr>
                <tr>
                    <td style="float: left">学生名：</td>
                    <td>
                        <input type="hidden" name="studentid" value="${student.studentid}"/>
                        <input style="width: 100%" type="text" name="studentname" placeholder="输入名字" value="${student.studentname}"/>
                    </td>
                </tr>
                <tr>
                    <td style="float: left">班级名：</td>
                    <td>
                        <select name="classesid" id="classesid" class="form-control">
                            <option value="${classes.classesid}">${classes.classesname}</option>
                            <c:forEach items="${classesList}" var="classes" varStatus="v">
                                <option value="${classes.classesid}">${classes.classesname}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td style="float: left">入学时间：</td>
                    <td><input style="width: 100%" type="text" name="intime" placeholder="输入入学时间,格式为XXXX-XX" value="${student.intime}"/></td>
                </tr>
                <tr>
                    <td style="float: left">出生年月：</td>
                    <td><input style="width: 100%" type="text" name="birthday" placeholder="输入出生年月日，格式为XXXX-XX-XX" value="${student.birthday}"/></td>
                </tr>
                <tr>
                    <td style="float: left">性别：</td>
                    <td>
                        <select name="sex" id="sex" class="form-control">
                            <option value="${student.sex}">${student.sex}</option>
                            <option value="男">男</option>
                            <option value="女">女</option>
                            <option value="嬲">嬲</option>
                        </select>
                    </td>
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
