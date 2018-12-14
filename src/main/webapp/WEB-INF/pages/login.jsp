<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>管理员登录-WeAdmin Frame型后台管理系统-WeAdmin 1.0</title>
    <%@page language="java" contentType="text/html; UTF-8" pageEncoding="utf-8" %>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <meta http-equiv="Cache-Control" content="no-siteapp"/>
    <link rel="shortcut icon" href="./favicon.ico" type="image/x-icon"/>
    <link rel="stylesheet" href="./static/css/font.css">
    <link rel="stylesheet" href="./static/css/weadmin.css">

    <script type="text/javascript" src="./lib/layui/layui.js" charset="utf-8"></script>
</head>
<body class="login-bg">
    <div class="login">
        <div class="message">WeAdmin 1.0-管理登录</div>
        <div id="darkbannerwrap"></div>

        <form class="layui-form" action="./login" method="post">
            <input name="username" placeholder="用户名"  type="text" lay-verify="required" class="layui-input" >
            <hr class="hr15">
            <input name="password" lay-verify="required" placeholder="密码"  type="password" class="layui-input">
            <hr class="hr15">
            <input  class="loginin" lay-submit="" lay-filter="login" value="登录" style="width:100%;"type="submit">
            <hr class="hr20" >
            <span style="text-align:center; display:block; color:red; font-size:18px">${message}</span>
        </form>
    </div>
</body>

<script type="text/javascript">
    /*layui.use(['form'],function(){
        var form  = layui.form;
        var $ = layui.jquery;
        form.on('submit(login)', function(data){
            $.ajax({
                type: "post",
                url: "./login",
                data: {username:data.field.username,password:data.field.password},
                dataType: "json",
                success: function (data) {
                    if (!data.success){
                        $("#login-error").html(data.message);
                    }
                }
            });
        });
    });*/
</script>
</html>