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
    <div style="font-size: 20px;text-align: left;height: 50px;border-bottom: 1px gray solid"><b>教师${teacher.teachername}->详细信息</b></div>
    <br> <br>
    <div class="table-responsive">
        <form id="form" class="form-horizontal" action="">
            <div class="form-group">
                <label class="col-sm-2 control-label">班级</label>
                <div class="col-sm-5">
                    <select name="classesId" id="classesId" class="form-control">
                        <c:choose>
                            <c:when test="${empty classes.classesid}">
                                <option value="0">--请选择--</option>
                            </c:when>
                            <c:otherwise>
                                <option value="${classes.classesid}">${classes.classesname}</option>
                                <option value="0">--请选择--</option>
                            </c:otherwise>
                        </c:choose>
                        <c:forEach items="${classesList}" var="classes" varStatus="v">
                            <option value="${classes.classesid}">${classes.classesname}</option>
                        </c:forEach>
                    </select><input id="id" type="hidden" value="${id}"/>
                </div>
                <div class="col-sm-5">
                    <button type="submit" class="btn btn-default">搜索</button>
                </div>
                <label class="col-sm-12 control-label" style="text-align: center">目前已安排的课程</label>
            </div>
        </form>
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
                    <td>${courses.coursesname}<input type="hidden" class="id" value="${courses.coursesid}"/></td>
                    <td>${classesList[u.index].classesname}</td>
                    <td>${courses.term}</td>
                    <td>${courses.cost}</td>
                </tr>
            </c:forEach>
        </table>
        <div style="text-align: center;">
        </div>
    </div>
    <div class="table-responsive">
        <table
                class="table table-condensed table-hover table-bordered table-striped">
            <tr>
                <td><input type="checkbox"/></td>
                <td>课程名</td>
            </tr>

            <c:forEach items="${courseNames}" var="name" varStatus="u">
                <tr>
                    <td><input type="checkbox" value="${name}"/></td>
                    <td>${name}</td>
                </tr>
            </c:forEach>
        </table>
    </div>
    <button type="button" class="btn btn-info" id="update">修改
    </button>
    <button type="button" class="btn btn-danger" id="delete">删除
    </button>
</div>
</body>
<script type="text/javascript">
    $(function () {
        var ids = new Array();
        var i = 0;
        $(".id").each(function () {
            ids[i] = parseInt($(this).val());
            i++;
        });
        $("#back").bind("click", function () {
            window.history.back();
        });
        var id = $("#id").val();
        $("#form").attr("action", "http://localhost:8888/studentInfoManager_war_exploded/selectTeacherDetailByName/" +
            id + "?classesId=" + 0);
        $("#classesId").on("change", function () {
            var classesId = $("#classesId").val();
            $("#form").attr("action", "http://localhost:8888/studentInfoManager_war_exploded/selectTeacherDetailByName/" +
                id + "?classesId=" + classesId);
        });
        $("#update").bind("click", function () {
            var length = $("input[type='checkbox']:checked").length;
            if (length == 0) {
                alert("请选中要修改的课程名");
            } else if (length > 1) {
                alert("只能修改单个课程名，请重新选择");
            } else {
                $("input[type='checkbox']:checked").each(function () {
                    var a = $(this).val();
                    window.location.href = "http://localhost:8888/studentInfoManager_war_exploded/goToUpdateTeacherDetail?" +
                        "coursesName=" + a + "&coursesIds=" + ids;
                });
            }
        });
        $("#delete").bind("click", function () {
            var length = $("input[type='checkbox']:checked").length;
            if (length == 0) {
                alert("请选中要删除的课程");
            } else {
                $("input[type='checkbox']:checked").each(function () {
                    var a = $(this).val();
                    $.ajax({
                        url: "deleteTeacherDetail?coursesName=" + a + "&coursesIds=" + ids,
                        type: "post",
                        success: function () {
                        }
                    });
                });
                window.location.href = "http://localhost:8888/studentInfoManager_war_exploded/selectAllTeacher/1";
            }
        });
    });
</script>
</html>
