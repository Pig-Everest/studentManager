<%--
  Created by IntelliJ IDEA.
  User: z6774
  Date: 2019/8/8
  Time: 11:01
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
        * {
            padding: 0px;
            margin: 0px;
        }
    </style>

</head>
<body>
<div class="container-fluid">
    <div class="row" style="height: 20%;text-align: center;font-size: 30px">
        <b>用户登录</b>
    </div>
    <div class="row" style="height: 80%;width: 43%;text-align: center;margin: auto">
        <table class="table table-bordered" style="">
            <tr>
                <td width="12%">登录名：</td>
                <td width="30%">
                    <input id="account" class="input-sm" style="width: 80%" type="text"/>
                </td>
            </tr>
            <tr>
                <td width="12%">密码：</td>
                <td width="30%">
                    <input id="pwd" class="input-sm" style="width: 80%" type="text"/>
                </td>
            </tr>
            <tr>
                <td width="12%">验证码</td>
                <td width="30%">
                    <input id="imgY" class="input-sm" style="width: 57.5%" type="text"/>
                    <img id="yanzhengma" src=""/><span style="color: red;font-size: 15px;margin-left: 20%"
                                                       id="errorInfo"></span>
                    <input id="img1" value="" hidden/>
                </td>
            </tr>
            <tr>
                <td colspan="2" width="42%">
                    <div style="background: gray;text-align: center">
                        <button id="btn" class="btn btn-primary" value="登录">登录</button>
                    </div>
                </td>
            </tr>
        </table>
    </div>
</div>
</body>
<script type="text/javascript">
    $(function () {
        var timestamp1 = new Date().getTime();
        var time1 = parseInt(timestamp1) % 10000;
        $("#yanzhengma").attr("src", "getImg?r1=" + time1);
        $("#img1").attr("value", time1);

        $("#btn").bind("click", function () {
            var userAccount = $("#account").val();
            var userPwd = $("#pwd").val();
            var imgY = $("#imgY").val();
            var img1 = $("#img1").val();
            if (imgY == img1) {
                $.ajax({
                    url: "checkUserLogin",
                    type: "post",
                    data: {"useraccount": userAccount, "userpwd": userPwd},
                    dataType: "json",
                    success: function (data) {
                        if (data == "true") {
                            window.location.href = "jsp/main.jsp?";
                        } else if (data == "false") {
                            $("#errorInfo").html("账号或者密码输入错误。");
                            var timestamp1 = new Date().getTime();
                            var time1 = parseInt(timestamp1) % 10000;
                            $("#yanzhengma").attr("src", "getImg?r1=" + time1);
                            $("#img1").attr("value", time1);
                        }
                    }
                });
            } else {
                $("#errorInfo").html("验证码输入错误。");
                var timestamp = new Date().getTime();
                var time = parseInt(timestamp) % 10000;
                $("#yanzhengma").attr("src", "getImg?r1=" + time);
                $("#img1").attr("value", time);
            }
        });

        $("#yanzhengma").bind("click", function () {
            var timestamp = new Date().getTime();
            var time = parseInt(timestamp) % 10000;
            $("#yanzhengma").attr("src", "getImg?r1=" + time);
            $("#img1").attr("value", time);
        });
    });
</script>
</html>
