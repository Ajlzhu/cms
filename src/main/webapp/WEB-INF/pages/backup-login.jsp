<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <%@page language="java" contentType="text/html; UTF-8" pageEncoding="utf-8" %>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <title>login</title>
    <link rel="stylesheet" href='./lib/layui/css/layui.css'>
    <style>
        .formdiv{
            width: 380px;
            height: 200px;
            position: absolute;
            left: 40%;
            top: 35%;
            padding-top: 35px;
            z-index: 99999;
        }
        .bgdiv{
            width: 100%;
            height: 100%;
            background: url(./static/images/bg.png)no-repeat;
            background-size: 100% 100%;
            position: absolute;
            overflow: hidden;
        }
    </style>
</head>
<body>
    <div class="bgdiv">
       <div class="layui-bg-gray formdiv">
            <form class="layui-form" action="./login" method="post">
                <div class="layui-form-item">
                    <label class="layui-form-label">账 号:</label>
                    <div class="layui-input-inline">
                        <input type="text" name="username" required  lay-verify="required" placeholder="请输入用户名" autocomplete="off" class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">密 码:</label>
                    <div class="layui-input-inline">
                        <input type="password" name="password" required lay-verify="required" placeholder="请输入密码" autocomplete="off" class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item">
                    <div class="layui-input-block">
                        <button class="layui-btn" lay-submit lay-filter="formDemo">登录</button>
                        <a href="./register" class="layui-btn">注册</a>
                    </div>
                </div>
            </form>
        </div>
    </div>
</body>
<script src="./static/js/layui.js"></script>
</html>