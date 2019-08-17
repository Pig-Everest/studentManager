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
    <div style="font-size: 20px;text-align: left;height: 50px;border-bottom: 1px gray solid"><b>${classes.classesname}班->课表信息</b></div>
    <br> <br>
    <div class="table-responsive">
        <form id="form" class="form-horizontal" action="" method="post">
            <div class="form-group">
                <label for="teacherId" class="col-sm-2 control-label">班级名：</label>
                <div class="col-sm-5">
                    <select name="name" id="teacherId" class="form-control">
                        <c:choose>
                            <c:when test="${empty teacher.teacherid}">
                                <option value="">--请选择--</option>
                            </c:when>
                            <c:otherwise>
                                <option value="${teacher.teacherid}">${teacher.teachername}</option>
                                <option value="">--请选择--</option>
                            </c:otherwise>
                        </c:choose>
                        <c:forEach items="${teacherNames}" var="teacher" varStatus="v">
                            <option value="${teacher.teacherid}">${teacher.teachername}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="col-sm-5">
                    <button type="submit" class="btn btn-default">搜索</button>
                    <button type="button" class="btn btn-default" id="sub">增加
                    </button>
                </div>
            </div>
        </form>
        <table
                class="table table-condensed table-hover table-bordered table-striped">
            <tr>
                <td><input type="checkbox" id="chooseAll"/></td>
                <td>开设课程</td>
                <td>任课教师</td>
                <td>学期</td>
                <td>学分</td>
            </tr>

            <c:forEach items="${coursesList}" var="courses" varStatus="u">
                <tr>
                    <td><input type="checkbox" value="${courses.coursesid}"/></td>
                    <td>${courses.coursesname }</td>
                    <td>${teachers[u.index].teachername}</td>
                    <td>${courses.term}</td>
                    <td>${courses.cost}</td>
                </tr>
            </c:forEach>
        </table>
        <input type="hidden" id="id" value="${id}"/>
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
        var teacherId = $("#teacherId").val();
        $("#form").attr("action", "http://localhost:8888/studentInfoManager_war_exploded/selectTimeTable/1/" +
            $("#id").val() + "?teacherid=" + teacherId);
        $("#goToPage1").bind("click", function () {
            window.location.href = "http://localhost:8888/studentInfoManager_war_exploded/selectTimeTable/1/" +
                $("#id").val() + "?teacherid=" + teacherId;
        });
        $("#goToPageLast").bind("click", function () {
            window.location.href = "http://localhost:8888/studentInfoManager_war_exploded/selectTimeTable/" + $("#totalPage").val() + "/" +
                $("#id").val() + "?teacherid=" + teacherId;
        });
        $(".goToPage").bind("click", function () {
            window.location.href = "http://localhost:8888/studentInfoManager_war_exploded/selectTimeTable/" + $(this).html() + "/" +
                $("#id").val() + "?teacherid=" + teacherId;
        });
        $("#teacherId").on("change", function () {
            teacherId = $("#teacherId").val();
            $("#form").attr("action", "http://localhost:8888/studentInfoManager_war_exploded/selectTimeTable/1/" +
                $("#id").val() + "?teacherid=" + teacherId);
        });
        $("#chooseAll").bind("click", function () {
            $("input[type='checkbox']").prop("checked", this.checked);
        });
        $("#sub").bind("click", function () {
            var id = $("#id").val();
            window.location.href = "http://localhost:8888/studentInfoManager_war_exploded/goToTimeTableAdd/1?classesId=" + id;
        });
        $("#delete").bind("click", function () {
            var id = $("#id").val();
            var length = $("input[type='checkbox']:checked").length;
            if (length == 0) {
                alert("请选中要删除的课程");
            } else {
                $("input[type='checkbox']:checked").each(function () {
                    var coursesId = $(this).val();
                    $.ajax({
                        url: "deleteTimeTable?classesId=" + id + "&coursesId=" + coursesId,
                        type: "post",
                        success: function () {
                        }
                    });
                });
                window.location.href = "http://localhost:8888/studentInfoManager_war_exploded/selectTimeTable/1/" +
                    $("#id").val()
            }
        });
    });
</script>
</html>
