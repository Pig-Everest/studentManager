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
    <div style="font-size: 20px;text-align: left;height: 50px;border-bottom: 1px gray solid"><b>班级管理</b></div>
    <br> <br>
    <div class="table-responsive">
        <form class="form-horizontal" action="<%=path%>/selectClasses/1">
            <div class="form-group">
                <label for="inputEmail3" class="col-sm-2 control-label">班级名：</label>
                <div class="col-sm-5">

                    <input id="inputName" type="text" name="name" class="form-control" id="inputEmail3"
                           value="${name}" placeholder="输入名字">
                </div>
                <div class="col-sm-5">
                    <button type="submit" class="btn btn-default">搜索</button>
                    <button type="button" class="btn btn-default"
                            onclick="window.location.href='<%=path%>/jsp/addClasses.jsp'">增加
                    </button>
                </div>
            </div>
        </form>
        <table
                class="table table-condensed table-hover table-bordered table-striped">
            <tr>
                <td><input id="chooseAll" type="checkbox"/></td>
                <td id="test">班级名</td>
                <td>操作</td>
            </tr>

            <c:forEach items="${list}" var="classes" varStatus="u">
                <tr>
                    <td><input type="checkbox" value="${classes.classesid}"/></td>
                    <td>${classes.classesname }</td>
                    <td class="selectTimeTable">查看班级课程信息</td>
                    <input type="hidden" value="${classes.classesid}"/>
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
                    </a></li>&nbsp;&nbsp;&nbsp;&nbsp;<button type="button" class="btn btn-info"
                                                             id="update">
                    修改
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
        $("#goToPage1").bind("click", function () {
            window.location.href = "http://localhost:8888/studentInfoManager_war_exploded/selectClasses/1?name=" + name;
        });
        $("#goToPageLast").bind("click", function () {
            window.location.href = "http://localhost:8888/studentInfoManager_war_exploded/selectClasses/" +
                $("#totalPage").val() + "?name=" + name;
        });
        $(".goToPage").bind("click", function () {
            window.location.href = "http://localhost:8888/studentInfoManager_war_exploded/selectClasses/" +
                $(this).html() + "?name=" + name;
        });

        $("#test").bind("click", function () {
            window.location.href = "timeTable.jsp";
        });
        $(".selectTimeTable").bind("click", function () {
            window.location.href = "http://localhost:8888/studentInfoManager_war_exploded/selectTimeTable/1/" + $(this).next().val()
        });
        $("#delete").bind("click", function () {
            var length = $("input[type='checkbox']:checked").length;
            if (length == 0) {
                alert("请选中要删除的班级");
            } else {
                $("input[type='checkbox']:checked").each(function () {
                    var a = $(this).val();
                    $.ajax({
                        url: "deleteClasses?classesId=" + a,
                        type: "post",
                        success: function () {
                        }
                    });
                });
                window.location.href = "http://localhost:8888/studentInfoManager_war_exploded/selectClasses/1";
            }
        });
        $("#update").bind("click", function () {
            var length = $("input[type='checkbox']:checked").length;
            if (length == 0) {
                alert("请选中要修改的班级");
            } else if (length > 1) {
                alert("只能修改单个班级，请重新选择");
            } else {
                $("input[type='checkbox']:checked").each(function () {
                    var a = $(this).val();
                    window.location.href = "http://localhost:8888/studentInfoManager_war_exploded/goToUpdateClasses?id=" + a;
                });
            }
        });
        $("#chooseAll").bind("click", function () {
            $("input[type='checkbox']").prop("checked", this.checked);
        });
    });
</script>
</html>
