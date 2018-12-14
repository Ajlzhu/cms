<!doctype html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>修改密码</title>
    <%@page language="java" contentType="text/html; UTF-8" pageEncoding="utf-8" %>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
    <meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <meta http-equiv="Cache-Control" content="no-siteapp"/>
    <link rel="shortcut icon" href="./favicon.ico" type="image/x-icon"/>
    <link rel="stylesheet" href="./static/css/font.css">
    <link rel="stylesheet" href="./static/css/weadmin.css">
    <script type="text/javascript" src="./lib/layui/layui.js" charset="utf-8"></script>
    <head>
        <title>修改密码</title>
    </head>
<body>
<div style="margin-top: 30px;">
</div>
<form class="layui-form">
    <div class="layui-form-item">
        <label class="layui-form-label">当前密码</label>
        <div class="layui-input-inline">
            <input type="password" name="oldPassword" lay-verify="title" autocomplete="off" placeholder="请输入当前密码" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">新密码</label>
        <div class="layui-input-inline">
            <input type="password" id="L_pass" name="password" lay-verify="pass" autocomplete="off" placeholder="请输入新密码" class="layui-input">
        </div>
        <div class="layui-form-mid layui-word-aux">
            6到16个字符
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">确认新密码</label>
        <div class="layui-input-inline">
            <input type="password" id="L_repass" name="repassword" lay-verify="repass" placeholder="再次确认新密码"autocomplete="off" class="layui-input">
        </div>
    </div>

    <div class="layui-form-item" style="margin-top: 30px">
        <div class="layui-input-block">
            <button class="layui-btn" lay-submit="" lay-filter="changePwd">确认修改</button>
        </div>
    </div>
</form>
<script type="text/javascript">
    layui.extend({
        admin: '{/}./static/js/admin'
    });
    layui.use(['form','layer','admin','jquery'],function () {
        var form = layui.form,
            admin = layui.admin,
            layer = layui.layer,
            $  = layui.jquery;
        form.render();
        //自定义验证规则
        form.verify({
            pass: [/(.+){6,12}$/, '密码必须6到12位']
            ,repass: function(value){
                if($('#L_pass').val()!=$('#L_repass').val()){
                    return '两次密码不一致';
                }
            }
        });
        //监听提交
        form.on('submit(changePwd)', function (data) {
            var pwd = data.field;
            $.ajax({
                type: "post",
                url: "./user/update/password",
                data: {oldPassword: pwd.oldPassword, password: pwd.password},
                dataType: "json",
                success: function (result) {
                    if (result.success) {
                        layer.alert(result.message, {icon: 1}, function () {
                            // 获得frame索引
                            var index = parent.layer.getFrameIndex(window.name);
                            //关闭当前frame
                            parent.layer.close(index);
                        });
                    } else {
                        layer.msg('result.message', {
                            icon: 2,
                            time: 1000
                        });
                    }
                }
            });
            return false;
        });
    });
</script>
</body>
</html>
