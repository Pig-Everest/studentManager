<%--
  Created by IntelliJ IDEA.
  User: z6774
  Date: 2019/8/12
  Time: 20:24
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
        <table class="table table-condensed table-hover table-bordered table-striped">
            <tr>
                <td style="font-size: 30px" colspan="5"><b>添加课程</b></td>
            </tr>
            <tr>
                <td><input id="chooseAll" type="checkbox"/></td>
                <td>课程</td>
                <td id="test">教师</td>
                <td>学期</td>
                <td>学分</td>
            </tr>

            <c:forEach items="${coursesList}" var="courses" varStatus="u">
                <tr>
                    <td><input type="checkbox" value="${courses.coursesid}"/></td>
                    <td>${courses.coursesname}</td>
                    <td>${teacherList[u.index].teachername}</td>
                    <td>${courses.term}</td>
                    <td>${courses.cost}</td>
                </tr>
            </c:forEach>
            <tr>
                <td colspan="3">
                    <button type="button" id="sub" class="btn btn-default">确认</button>
                </td>
                <td colspan="2">
                    <button type="button" class="btn btn-default" onclick="history.back()">返回</button>
                </td>
            </tr>
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
                </ul>
            </nav>
        </div>
    </div>
</div>
</body>
<script type="text/javascript">
    $(function () {
        var id = $("#id").val();
        $("#goToPage1").bind("click", function () {
            window.location.href = "http://localhost:8888/studentInfoManager_war_exploded/goToTimeTableAdd/1?classesId=" + id;
        });
        $("#goToPageLast").bind("click", function () {
            window.location.href = "http://localhost:8888/studentInfoManager_war_exploded/goToTimeTableAdd/" +
                $("#totalPage").val() + "?classesId=" + id;
        });
        $(".goToPage").bind("click", function () {
            window.location.href = "http://localhost:8888/studentInfoManager_war_exploded/goToTimeTableAdd/" +
                $(this).html() + "?classesId=" + id;
        });

        $("#sub").bind("click", function () {
            var length = $("input[type='checkbox']:checked").length;
            if (length == 0) {
                alert("请选中要添加的课程");
            } else {
                $("input[type='checkbox']:checked").each(function () {
                    var a = $(this).val();
                    var id = $("#id").val();
                    $.ajax({
                        url: "insertTimeTable?classesId=" + id + "&coursesId=" + a,
                        type: "post",
                        success: function () {
                        }
                    });
                });
                window.location.href = "http://localhost:8888/studentInfoManager_war_exploded/selectTimeTable/1/" + id;
            }
        });

    });
</script>
</html>
