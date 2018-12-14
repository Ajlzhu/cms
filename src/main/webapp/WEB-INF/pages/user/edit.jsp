<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>CMS后台管理系统</title>
    <%@page language="java" contentType="text/html; UTF-8" pageEncoding="utf-8" %>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <link rel="stylesheet" href="../static/css/font.css">
    <link rel="stylesheet" href="../static/css/weadmin.css">
    <!-- 让IE8/9支持媒体查询，从而兼容栅格 -->
    <!--[if lt IE 9]>
    <script src="https://cdn.staticfile.org/html5shiv/r29/html5.min.js"></script>
    <script src="https://cdn.staticfile.org/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>
<body>
<div class="weadmin-body">
    <form class="layui-form">
        <div class="layui-form-item">
            <label for="username" class="layui-form-label">
                <span class="we-red">*</span>ID
            </label>
            <div class="layui-input-inline">
                <input disabled="disabled" type="text" id="userId" name="userId" required="" lay-verify="required"
                       autocomplete="off" value="9527" class="layui-input">
            </div>
            <div class="layui-form-mid layui-word-aux">
                <span class="we-red">*</span>
            </div>
        </div>
        <div class="layui-form-item">
            <label for="username" class="layui-form-label">
                <span class="we-red">*</span>账号
            </label>
            <div class="layui-input-inline layui-disabled">
                <input disabled="disabled" type="text" id="username" name="username" required="" lay-verify="required"
                       autocomplete="off" value="admin" class="layui-input">
            </div>
            <div class="layui-form-mid layui-word-aux">
                <span class="we-red">*</span>唯一登入名
            </div>
        </div>
        <div class="layui-form-item">
            <label for="nickname" class="layui-form-label">
                <span class="we-red">*</span>姓名
            </label>
            <div class="layui-input-inline">
                <input type="text" id="nickname" name="nickname" required="" lay-verify="required"
                       autocomplete="off" value="admin" class="layui-input">
            </div>
            <div class="layui-form-mid layui-word-aux">
                <span class="we-red">*</span>昵称
            </div>
        </div>
        <div class="layui-form-item">
            <label for="phone" class="layui-form-label">
                <span class="we-red">*</span>手机
            </label>
            <div class="layui-input-inline">
                <input type="text" value="18925139194" id="phone" name="phone" required="" lay-verify="phone"
                       autocomplete="off" class="layui-input">
            </div>
            <div class="layui-form-mid layui-word-aux">
                <span class="we-red">*</span>
            </div>
        </div>
        <div class="layui-form-item">
            <label for="L_email" class="layui-form-label">
                <span class="we-red">*</span>邮箱
            </label>
            <div class="layui-input-inline">
                <input type="text" value="113664000@qq.com" id="L_email" name="email" required="" lay-verify="email"
                       autocomplete="off" class="layui-input">
            </div>
            <div class="layui-form-mid layui-word-aux">
                <span class="we-red">*</span>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label"><span class="we-red">*</span>部门</label>
            <div class="layui-input-inline">
                <input type="hidden" id="dept"/>
                <select id="deptSelect" name="deptId" lay-verify="" lay-search>
                </select>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">
                <span class="we-red">*</span>状态
            </label>
            <div class="layui-input-inline">
                <input type="checkbox" id="status" name="status" lay-skin="switch" value="0" lay-text="启用|禁用"
                       lay-filter="statusChange">
            </div>
            <div class="layui-form-mid layui-word-aux">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">
            </label>
            <button class="layui-btn" lay-filter="updateUser" lay-submit="">
                保存
            </button>
        </div>
    </form>
</div>
<script src="../lib/layui/layui.js" charset="utf-8"></script>
<script type="text/javascript">
    layui.extend({
        admin: '{/}../static/js/admin'
    });
    layui.use(['form', 'layer', 'admin', 'jquery'], function () {
        var form = layui.form,
            admin = layui.admin,
            $ = layui.jquery,
            layer = layui.layer;
        //监听用户状态设置
        form.on('switch(statusChange)', function (obj) {
            $("#status").val(obj.elem.checked ? 1 : 0);
        });
        //加载所有角色信息
        $(function () {
            /*$.ajax({
                type: "post",
                url: "../role/list/all",
                dataType: "json",
                success: function (data) {
                    if (data.success) {
                        var roles = data.data;
                        var chechboxRole = "";
                        var roleName = $("#userRoleName").val();
                        $.each(roles, function (index, role) {
                            if(roleName == role.roleName){
                                chechboxRole += "<input type=\"checkbox\" name=\"role\" lay-skin=\"primary\" title="+ role.roleName +" checked>";
                            }else{
                                chechboxRole += "<input type=\"checkbox\" name=\"role\" lay-skin=\"primary\" title=" + role.roleName + ">";
                            }
                        });
                        $("#roleList").append(chechboxRole);
                        form.render('checkbox');
                    }
                }
            });*/
            //加载部门下拉菜单
            $.ajax({
                type: "post",
                url: "../dept/list",
                dataType: "json",
                success: function (data) {
                    var depts = data.data;
                    var deptSelect = "";
                    var dept_id = $("#dept").val();
                    if (data.success) {
                        $.each(depts, function (index, dept) {
                            if (dept_id == dept.deptId) {
                                deptSelect += "<option value=\"" + dept.deptId + "\" selected>" + dept.deptName + "</option>";
                            } else {
                                deptSelect += "<option value=\"" + dept.deptId + "\">" + dept.deptName + "</option>";
                            }
                        });
                        $("#deptSelect").append(deptSelect);
                        //刷新select选择框渲染
                        form.render('select');
                    }
                }
            });
        });
        //自定义验证规则
        form.verify({
            nikename: function (value) {
                if (value.length < 5) {
                    return '昵称至少得5个字符啊';
                }
            }
            , pass: [/(.+){6,12}$/, '密码必须6到12位']
            , repass: function (value) {
                if ($('#L_pass').val() != $('#L_repass').val()) {
                    return '两次密码不一致';
                }
            }
        });

        //监听提交
        form.on('submit(updateUser)', function (data) {
            var user = data.field;
            var status = $("#status").val();
            $.ajax({
                type: "post",
                url: "../user/update/id",
                data: {
                    userId: user.userId, nickname: user.nickname, phone: user.phone,
                    email: user.email, status: status, deptId: user.deptId
                },
                dataType: "json",
                success: function (result) {
                    if (result.success) {
                        layer.alert(result.message, {icon: 1}, function () {
                            // 获得frame索引
                            var index = parent.layer.getFrameIndex(window.name);
                            //刷新父窗口table数据(模拟点击父页面的查询按钮)
                            $("#searchUser", parent.document).click();
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