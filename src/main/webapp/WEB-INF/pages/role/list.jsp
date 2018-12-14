<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>CMS后台管理系统</title>
    <%@page language="java" contentType="text/html; UTF-8" pageEncoding="utf-8" %>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        <a href="">首页</a>
        <a href="">管理员管理</a>
        <a>
          <cite>角色管理</cite></a>
      </span>
    <a class="layui-btn layui-btn-sm" style="line-height:1.6em;margin-top:3px;float:right" href="javascript:location.replace(location.href);" title="刷新">
        <i class="layui-icon" style="line-height:30px">ဂ</i></a>
</div>
<div class="weadmin-body">
    <div class="layui-row">
        <form class="layui-form layui-col-md12 we-search">
            <div class="layui-inline">
                <input class="layui-input" placeholder="开始日" name="start" id="start">
            </div>
            <div class="layui-inline">
                <input class="layui-input" placeholder="截止日" name="end" id="end">
            </div>
            <div class="layui-inline">
                <input type="text" name="roleName" placeholder="请输入角色名称" autocomplete="off" class="layui-input">
            </div>
            <button id="sreachRole" class="layui-btn" lay-submit="" lay-filter="sreachRole"><i class="layui-icon">&#xe615;</i></button>
        </form>
    </div>
    <div class="weadmin-block">
        <button class="layui-btn layui-btn-danger" onclick="delAll()"><i class="layui-icon"></i>批量删除</button>
        <button class="layui-btn" onclick="WeAdminShow('添加角色','./add.html')"><i class="layui-icon"></i>添加</button>
        <span class="fr" style="line-height:40px">共有数据：88 条</span>
    </div>
    <table class="layui-hide" id="role-table" lay-filter="role-table"></table>
</div>
<script src="./lib/layui/layui.js" charset="utf-8"></script>
<script src="./static/js/eleDel.js" type="text/javascript" charset="utf-8"></script>
<%--操作bar--%>
<script type="text/html" id="operatebar">
    <a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="detail">查看</a>
    <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
</script>
<script type="text/javascript">
    layui.use(['jquery', 'form', 'layedit', 'laydate', 'laypage', 'table', 'layer'], function () {
        var $ = layui.jquery;
        var form = layui.form;
        var table = layui.table;
        form.on('submit(sreachRole)', function (data) {
            var condition = data.field;
            table.render({
                id: 'userTable'
                , elem: '#role-table'
                , url: './role/list'
                , method: 'post'
                , where: {roleName: condition.roleName}
                , request: {
                    pageName: 'pageNum' //页码的参数名称，默认：page
                    , limitName: 'pageSize' //每页数据量的参数名，默认：limit
                }
                , response: {
                    statusName: 'code' //规定数据状态的字段名称，默认：code
                    , statusCode: 200 //规定成功的状态码，默认：0
                    , msgName: 'msg' //规定状态信息的字段名称，默认：msg
                    , countName: 'count' //规定数据总数的字段名称，默认：count
                    , dataName: 'data' //规定数据列表的字段名称，默认：data
                }
                , cols: [[
                    {type: 'checkbox'},
                    {field: 'roleName', width: 100, align: 'center', title: '角色名称'},
                    {width: 100, align: 'center', title: '创建人',templet:'<div>{{d.creator.nickname == null ? \'\' : d.creator.nickname}}</div>'},
                    {field: 'createTime', width: 160, align: 'center', title: '创建时间', sort: true},
                    {width: 100, align: 'center', title: '修改人',templet:'<div>{{d.modifier.nickname == null ? \'\' : d.creator.nickname}}</div>'},
                    {field: 'updateTime', width: 160, align: 'center', title: '修改时间',sort: true},
                    {fixed: 'right', width: 178, align: 'center', title: '操作', toolbar: '#operatebar'}
                ]]
                , page: true
            });
            return false
        });
        //监听工具条
        table.on('tool(role-table)', function (obj) {
            var data = obj.data;
            if (obj.event === 'detail') {
                layer.msg('ID：' + data.roleId + ' 的查看操作');
            } else if (obj.event === 'del') {
                layer.confirm('确认删除', function (index) {
                    delUser(data.userId);
                    obj.del();
                    layer.close(index);
                });
            } else if (obj.event === 'edit') {
                //layer.alert('编辑行：<br>'+ JSON.stringify(data))
                editRole('编辑', './role/edit', data,500,700);
            }
        });
        //打角色编辑页面
       function editRole(title, url, data, w, h){
            if(title == null || title == '') {
                title = false;
            };
            if(url == null || url == '') {
                url = "../404.html";
            };
            if(w == null || w == '') {
                w = ($(window).width() * 0.9);
            };
            if(h == null || h == '') {
                h = ($(window).height() - 50);
            };
            layer.open({
                type: 2,
                area: [w + 'px', h + 'px'],
                fix: false, //不固定
                maxmin: true,
                shadeClose: true,
                shade: 0.4,
                title: title,
                content: url,
                success: function(layero, index) {
                    // 参考 https://yq.aliyun.com/ziliao/133150
                    var body = layer.getChildFrame('body', index);
                    //初始化编辑页面的用户信息
                    body.contents().find("#roleName").val(data.roleName);
                    body.contents().find("#roleId").val(data.roleId);
                },
                error: function(layero, index) {
                    layer.msg("编辑页面打开失败");
                }
            });
        }
    });

</script>
</body>

</html>