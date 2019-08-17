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
    <div style="font-size: 20px;text-align: left;height: 50px;border-bottom: 1px gray solid"><b>学生管理</b></div>
    <br> <br>
    <div class="table-responsive">
        <form class="form-horizontal" id="form" action="">
            <div class="form-group">
                <label for="inputEmail3" class="col-sm-2 control-label">学生姓名：</label>
                <div class="col-sm-4">

                    <input style="width: 100%" id="inputName" type="text" name="name" class="form-control" id=""
                           value="${name}" placeholder="输入名字">
                </div>
                <label for="inputEmail3" class="col-sm-2 control-label">学生学号：</label>
                <div class="col-sm-4">
                    <c:choose>
                        <c:when test="${num==0}">
                            <input style="width: 100%" id="inputNum" type="text" name="num" class="form-control"
                                   id="inputEmail3"
                                   value="" placeholder="输入学号">
                        </c:when>
                        <c:otherwise>
                            <input style="width: 100%" id="inputNum" type="text" name="num" class="form-control"
                                   id="inputEmail3"
                                   value="${num}" placeholder="输入学号">
                        </c:otherwise>
                    </c:choose>
                </div>
                <div class="col-sm-12" style="text-align: center;margin: auto">
                    <button type="submit" class="btn btn-default">搜索</button>
                    <button type="button" class="btn btn-default"
                            onclick="location.href='<%=path%>/goToAddStudent'">增加
                    </button>
                </div>
            </div>
        </form>
        <table
                class="table table-condensed table-hover table-bordered table-striped">
            <tr>
                <td><input type="checkbox"/></td>
                <td>学号</td>
                <td>姓名</td>
                <td>入学时间</td>
                <td>出生年月</td>
                <td>性别</td>
                <td>班级</td>
            </tr>

            <c:forEach items="${studentList}" var="student" varStatus="u">
                <tr>
                    <td><input type="checkbox" value="${student.studentid}"/></td>
                    <td>${student.studentnum }</td>
                    <td>${student.studentname}</td>
                    <td>${student.intime}</td>
                    <td>${student.birthday}</td>
                    <td>${student.sex}</td>
                    <td>${classesList[u.index].classesname}</td>
                </tr>
            </c:forEach>
        </table>
        <div style="text-align: center;">
            <nav aria-label="Page navigation">
                <ul class="pagination">
                    <li><a aria-label="Previous"> <span
                            aria-hidden="true" id="goToPage1">首页</span>
                    </a></li>
                    <c:forEach items="${pageInfo.getNavigatepageNums()}" var="v">
                        <c:choose>
                            <c:when test="${v==pageInfo.pageNum}">
                                <li><a class="goToPage"
                                       style="background-color: #5bc0de;">${v }</a></li>
                            </c:when>
                            <c:otherwise>
                                <li><a class="goToPage">${v }</a></li>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                    <li><a id="goToPageLast" aria-label="Next"> <span
                            aria-hidden="true">末页</span>
                        <input id="totalPage" type="hidden" value="${pageInfo.pages}"/>
                    </a></li>&nbsp;&nbsp;&nbsp;&nbsp;
                    <button type="button" class="btn btn-info" id="update">修改
                    </button>
                    <button type="button" class="btn btn-danger" id="delete">删除
                    </button>
                </ul>
            </nav>
        </div>
    </div>
</div>
</body>
<script type="text/javascript">
    $(function () {
        var name = $("#inputName").val();
        var num = $("#inputNum").val();
        $("#goToPage1").bind("click", function () {
            window.location.href = "http://localhost:8888/studentInfoManager_war_exploded/selectAllStudent/1?name=" +
                name + "&num=" + num;
        });
        $("#goToPageLast").bind("click", function () {
            window.location.href = "http://localhost:8888/studentInfoManager_war_exploded/selectAllStudent/" +
                $("#totalPage").val() + "?name=" + name + "&num=" + num;
        });
        $(".goToPage").bind("click", function () {
            window.location.href = "http://localhost:8888/studentInfoManager_war_exploded/selectAllStudent/" +
                $(this).html() + "?name=" + name + "&num=" + num;
        });

        $("#inputName").on("change", function () {
            name = $("#inputName").val();
            num = $("#inputNum").val();
            $("#form").attr("action", "http://localhost:8888/studentInfoManager_war_exploded/selectAllStudent/1?name=" +
                name + "&num=" + num);
        });
        $("#inputNum").on("change", function () {
            name = $("#inputName").val();
            num = $("#inputNum").val();
            $("#form").attr("action", "http://localhost:8888/studentInfoManager_war_exploded/selectAllStudent/1?name=" +
                name + "&num=" + num);
        });
        $("#delete").bind("click", function () {
            var length = $("input[type='checkbox']:checked").length;
            if (length == 0) {
                alert("请选中要删除的学生信息");
            } else {
                $("input[type='checkbox']:checked").each(function () {
                    var a = $(this).val();
                    $.ajax({
                        url: "deleteStudent?studentId=" + a,
                        type: "post",
                        success: function () {
                        }
                    });
                });
                window.location.href = "http://localhost:8888/studentInfoManager_war_exploded/selectAllStudent/1";
            }
        });
        $("#update").bind("click", function () {
            var length = $("input[type='checkbox']:checked").length;
            if (length == 0) {
                alert("请选中要修改的学生信息");
            } else if (length > 1) {
                alert("只能修改单个学生信息，请重新选择");
            } else {
                $("input[type='checkbox']:checked").each(function () {
                    var a = $(this).val();
                    window.location.href = "http://localhost:8888/studentInfoManager_war_exploded/goToUpdateStudent?studentId=" + a;
                });
            }
        });
    });
</script>
</html>
