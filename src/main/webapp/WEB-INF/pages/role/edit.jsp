<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>CMS后台管理系统</title>
    <%@page language="java" contentType="text/html; UTF-8" pageEncoding="utf-8" %>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
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
            <label class="layui-form-label">角色名称</label>
            <div class="layui-input-inline">
                <input class="layui-input" type="hidden" name="roleId" id="roleId"/>
                <input class="layui-input" type="text" name="roleName" id="roleName"/>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">选择权限</label>
            <div class="layui-input-block">
                <div id="LAY-auth-tree-index"></div>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-input-block">
                <button class="layui-btn" type="submit" lay-submit lay-filter="LAY-auth-tree-submit">提交</button>
                <button class="layui-btn layui-btn-primary" type="reset">重置</button>
            </div>
        </div>
    </form>
</div>
<script src="../lib/layui/layui.js" charset="utf-8"></script>
<script type="text/javascript">
    layui.extend({
        authtree: '{/}../static/js/authtree',
    });
    layui.use(['jquery','form','authtree','layer'], function () {
        var $ = layui.jquery;
        var authtree = layui.authtree;
        var form = layui.form;
        var layer = layui.layer;
        // 一般来说，权限数据是异步传递过来的
        setTimeout(function (){
            var roleId = $("#roleId").val();
            $.ajax({
                url: '../permission/tree',
                type: "post",
                data:{roleId:roleId},
                dataType: "json",
                success: function (data) {
                    var trees = authtree.listConvert(data.data, {
                        primaryKey: 'pid'
                        ,startPid: 0
                        ,parentKey: 'parentId'
                        ,nameKey: 'pname'
                        ,valueKey: 'pid'
                        ,checkedKey: 'checked'
                    });
                    // 如果后台返回的不是树结构，请使用 authtree.listConvert 转换
                    authtree.render('#LAY-auth-tree-index', trees, {
                        inputname: 'authids[]',
                        layfilter: 'lay-check-auth',
                        autowidth: true
                    });
                }
            });
        },0);

        form.on('submit(LAY-auth-tree-submit)', function(obj){
            // 获取所有已选中节点
            var checked = authtree.getChecked('#LAY-auth-tree-index');
            var permissions = "";
            $.each(checked, function (index, permission) {
                permissions += ",".concat(permission);
            });
            permissions = permissions.substr(1);
            var roleId = obj.field.roleId;
            var roleName = obj.field.roleName;
            $.ajax({
                type: "post",
                url: './update/id',
                dataType: 'json',
                data: {roleId:roleId,roleName:roleName,pIds:permissions},
                success: function(result){
                    if (result.success) {
                        layer.alert(result.message, {icon: 1}, function () {
                            // 获得frame索引
                            var index = parent.layer.getFrameIndex(window.name);
                            //刷新父窗口table数据(模拟点击父页面的查询按钮)
                            $("#sreachRole", parent.document).click();
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