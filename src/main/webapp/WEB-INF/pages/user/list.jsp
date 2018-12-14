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
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
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
        <a href="">用户管理</a>
      </span>
    <a class="layui-btn layui-btn-sm" style="line-height:1.6em;margin-top:3px;float:right"
       href="javascript:location.replace(location.href);" title="刷新">
        <i class="layui-icon" style="line-height:30px">ဂ</i></a>
</div>
<div class="weadmin-body">
    <div class="layui-row">
        <form class="layui-form layui-col-md12 we-search">
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label">部门</label>
                    <div class="layui-input-inline">
                        <select id="deptSelect" name="deptId" lay-verify="" lay-search>

                        </select>
                    </div>
                </div>
                <div class="layui-inline" style="margin-left: -10px">
                    <label class="layui-form-label">账号</label>
                    <input id="username" type="text" name="username" placeholder="账号" autocomplete="off" class="layui-input">
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">姓名</label>
                    <input id="nickname" type="text" name="nickname" placeholder="姓名" autocomplete="off" class="layui-input">
                </div>
                <div class="layui-inline">
                    <button class="layui-btn " lay-submit="" lay-filter="sreach" id="searchUser" style="margin-left: 10px">查询</button>
                </div>
            </div>
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label">邮箱</label>
                    <input class="layui-input" name="email" id="email" placeholder="电子邮箱">
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">手机</label>
                    <input class="layui-input" name="phone" id="phone" placeholder="手机号码">
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">状态</label>
                    <div class="layui-input-inline">
                        <select name="status" lay-verify="">
                            <option value="">请选择</option>
                            <option value="0">禁用</option>
                            <option value="1">启用</option>
                        </select>
                    </div>
                </div>
                <div class="layui-inline">
                    <button type="reset" class="layui-btn">重置</button>
                </div>
            </div>
        </form>
    </div>

    <div class="weadmin-block">
        <shiro:hasPermission name="user:delete">
            <button class="layui-btn layui-btn-danger" onclick="delSelected()"><i class="layui-icon"></i>批量删除</button>
        </shiro:hasPermission>
        <shiro:hasPermission name="user:save">
            <button class="layui-btn" onclick="WeAdminShow('添加用户','./user/add',700,500)"><i class="layui-icon"></i>添加</button>
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
<script type="text/html" id="status">
    <input disabled="disabled" type="checkbox" name="status" value="{{d.status}}" lay-skin="switch" lay-text="启用|禁用"
           lay-filter="statusChange" {{ d.status== 1 ? 'checked' : '' }}>
</script>
<script src="./lib/layui/layui.js" charset="utf-8"></script>
<script src="./static/js/eleDel.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript">
    layui.use(['jquery', 'form', 'layedit', 'laydate', 'laypage', 'table', 'layer'], function () {
        var $ = layui.jquery;
        var form = layui.form;
        var table = layui.table;
        $(function(){
            //加载部门下拉菜单
            $.ajax({
                type: "post",
                url: "./dept/list",
                dataType: "json",
                success: function (data) {
                    var depts = data.data;
                    var deptSelect = "<option value=\"\">请选择</option>";
                    if (data.success) {
                        $.each(depts, function (index, dept) {
                            deptSelect += "<option value=\"" + dept.deptId + "\">" + dept.deptName + "</option>";
                        });
                        $("#deptSelect").append(deptSelect);
                        //刷新select选择框渲染
                        form.render('select');
                    }
                }
            });
        });
        //搜索按钮监听提交
        form.on('submit(sreach)', function (data) {
            var condition = data.field;
            table.render({
                id: 'userTable'
                , elem: '#user-table'
                , url: './user/list'
                , method: 'post'
                , where: {username: condition.username,nickname:condition.nickname,phone:condition.phone,
                    email:condition.email,"dept.deptId":condition.deptId,status:condition.status}

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
                    {field: 'userId', width: 100, align: 'center', title: 'ID', sort: true},
                    {field: 'username', width: 100, align: 'center', title: '账号'},
                    {field: 'nickname', width: 120, align: 'center', title: '姓名'},
                    {width: 120, align: 'center', title: '所属部门',templet:'<div>{{d.dept.deptName}}</div>'},
                    {field: 'phone', width: 120, align: 'center', title: '手机号码'},
                    {field: 'email', width: 120, align: 'center', title: '邮箱'},
                    {width: 100, align: 'center', title: '创建人',templet:'<div>{{d.creator == null ? \'\' : d.creator.nickname == null ? \'\' : d.creator.nickname}}</div>'},
                    {field: 'createTime', width: 150, align: 'center', title: '创建时间', sort: true},
                    {width: 100, align: 'center', title: '修改人',templet:'<div>{{d.modifier == null ? \'\' : d.modifier.nickname == null ? \'\' : d.modifier.nickname}}</div>'},
                    {field: 'updateTime', width: 150, align: 'center', title: '修改时间',sort: true},
                    {field: 'status', width: 100, align: 'center', templet: '#status', title: '状态', unresize: true},
                    {fixed: 'right', width: 178, align: 'center', title: '操作', toolbar: '#operatebar'}
                ]]
                , page: true
            });
            return false
        });
        //监听工具条
        table.on('tool(user-table)', function (obj) {
            var data = obj.data;
            if (obj.event === 'detail') {
                layer.msg('ID：' + data.userId + ' 的查看操作');
            } else if (obj.event === 'del') {
                layer.confirm('确认删除', function (index) {
                    delUser(data.userId);
                    obj.del();
                    layer.close(index);
                });
            } else if (obj.event === 'edit') {
                //layer.alert('编辑行：<br>'+ JSON.stringify(data))
                editUser('编辑', './user/edit', data,700,500);
            }
        });
    });
</script>
</body>
</html>