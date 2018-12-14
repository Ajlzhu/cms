<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>权限管理</title>
    <%@page language="java" contentType="text/html; UTF-8" pageEncoding="utf-8" %>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <link rel="stylesheet" href="./static/css/font.css">
    <link rel="stylesheet" href="./static/css/weadmin.css">
    <!-- 让IE8/9支持媒体查询，从而兼容栅格 -->
    <!--[if lt IE 9]>
    <script src="https://cdn.staticfile.org/html5shiv/r29/html5.min.js"></script>
    <script src="https://cdn.staticfile.org/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>

<body>
<div class="weadmin-nav">
                <span class="layui-breadcrumb">
        <a href="">系统设置</a>
        <a href="">权限管理</a>
      </span>
    <a class="layui-btn layui-btn-sm" style="line-height:1.6em;margin-top:3px;float:right" href="javascript:location.replace(location.href);" title="刷新">
        <i class="layui-icon" style="line-height:30px">ဂ</i></a>
</div>
<div class="weadmin-body">
    <div class="layui-row">
        <form class="layui-form layui-col-md12 we-search">
            <div class="layui-inline">
                <input class="layui-input" placeholder="开始时间" name="start" id="start">
            </div>
            <div class="layui-inline">
                <input class="layui-input" placeholder="结束时间" name="end" id="end">
            </div>
            <div class="layui-inline">
                <input id="username" type="text" name="username" placeholder="请输入用户名" autocomplete="off" class="layui-input">
            </div>
            <button class="layui-btn" lay-submit="" lay-filter="sreach"><i class="layui-icon">&#xe615;</i></button>
        </form>
    </div>

    <div class="weadmin-block">
        <shiro:hasPermission name="user:delete">
            <button class="layui-btn layui-btn-danger" onclick="delSelected()"><i class="layui-icon"></i>批量删除</button>
        </shiro:hasPermission>
        <shiro:hasPermission name="user:save">
            <button class="layui-btn" onclick="WeAdminShow('添加用户','./user/add')"><i class="layui-icon"></i>添加</button>
        </shiro:hasPermission>
    </div>
    <%--用户列表--%>
    <table class="layui-hide" id="user-table" lay-filter="user-table"></table>
</div>

<%--操作bar--%>
<script type="text/html" id="operatebar">
    <a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="detail">查看</a>
    <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
</script>
<script src="./lib/layui/layui.js" charset="utf-8"></script>
<script src="./static/js/eleDel.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript">
    layui.use(['jquery','form', 'layedit', 'laydate','laypage','table','layer'], function() {
        var $ = layui.jquery;
        var form = layui.form;
        var table = layui.table;

        //搜索按钮监听提交
        form.on('submit(sreach)', function(data){
            table.render({
                id:'userTable'
                ,elem: '#user-table'
                ,url: './user/list'
                ,method: 'post'
                ,where: {username: data.field.username}
                ,request: {
                    pageName: 'pageNum' //页码的参数名称，默认：page
                    ,limitName: 'pageSize' //每页数据量的参数名，默认：limit
                }
                ,response: {
                    statusName: 'code' //规定数据状态的字段名称，默认：code
                    ,statusCode: 200 //规定成功的状态码，默认：0
                    ,msgName: 'msg' //规定状态信息的字段名称，默认：msg
                    ,countName: 'count' //规定数据总数的字段名称，默认：count
                    ,dataName: 'data' //规定数据列表的字段名称，默认：data
                }
                ,cols: [[
                    {type:'checkbox'},
                    {field:'userId', width:100, align:'center',title: 'ID', sort: true},
                    {field:'username', width:150, align:'center',title: '用户名'},
                    {field:'createBy', width:150, align:'center',title: '创建人'},
                    {field:'createTime', width:150, align:'center',title: '创建时间', sort: true},
                    {field:'updateBy', title: '修改人', align:'center',width: 150},
                    {field:'updateTime', width:150, align:'center',title: '修改时间'},
                    {field:'status', width:100, align:'center',title: '状态'},
                    {fixed: 'right', width:178, align:'center',title: '操作' ,toolbar: '#operatebar'}
                ]]
                ,page:true
            });
            return false
        });
        //监听工具条
        table.on('tool(user-table)', function(obj){
            var data = obj.data;
            if(obj.event === 'detail'){
                layer.msg('ID：'+ data.userId + ' 的查看操作');
            } else if(obj.event === 'del'){
                layer.confirm('确认删除', function(index){
                    delUser(data.userId);
                    obj.del();
                    layer.close(index);
                });
            } else if(obj.event === 'edit'){
                //layer.alert('编辑行：<br>'+ JSON.stringify(data))
                WeAdminEdit('编辑','./user/edit',5);
            }
        });
    });
</script>
</body>
</html>