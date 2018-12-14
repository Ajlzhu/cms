<!doctype html>
<html lang="en">

<head>
	<meta charset="UTF-8">
	<title>CMS后台管理系统</title>
	<%@page language="java" contentType="text/html; UTF-8" pageEncoding="utf-8" %>
	<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
	<meta name="renderer" content="webkit|ie-comp|ie-stand">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
	<meta http-equiv="Cache-Control" content="no-siteapp" />
	<link rel="shortcut icon" href="./favicon.ico" type="image/x-icon" />
	<link rel="stylesheet" href="./static/css/font.css">
	<link rel="stylesheet" href="./static/css/weadmin.css">
    <script type="text/javascript" src="./lib/layui/layui.js" charset="utf-8"></script>

</head>

<body>
<!-- 顶部开始 -->
<div class="container">
	<div class="logo">
		<a href="./home">CMS v1.0</a>
	</div>
	<div class="left_open">
		<i title="展开左侧栏" class="iconfont">&#xe699;</i>
	</div>
	<ul class="layui-nav left fast-add" lay-filter="">
		<li class="layui-nav-item">
			<a href="javascript:;">+新增</a>
			<dl class="layui-nav-child">
				<!-- 二级菜单 -->
				<dd>
					<a onclick="WeAdminShow('资讯','https://www.youfa365.com/')"><i class="iconfont">&#xe6a2;</i>资讯</a>
				</dd>
				<dd>
					<a onclick="WeAdminShow('图片','http://www.baidu.com')"><i class="iconfont">&#xe6a8;</i>图片</a>
				</dd>
				<dd>
					<a onclick="WeAdminShow('用户','https://www.youfa365.com/')"><i class="iconfont">&#xe6b8;</i>用户</a>
				</dd>
			</dl>
		</li>
	</ul>
	<ul class="layui-nav right" lay-filter="">
		<li class="layui-nav-item">
			<a href="javascript:;">${user.nickname}</a>
			<dl class="layui-nav-child">
				<!-- 二级菜单 -->
				<dd>
				<a onclick="WeAdminShow('个人信息','http://www.baidu.com')">个人信息</a>
			</dd>
				<dd>
					<a onclick="WeAdminShow('修改密码','./changepwd',600,350)">修改密码</a>
				</dd>
				<dd>
					<a class="loginout" href="./logout">退出</a>
				</dd>
			</dl>
		</li>
		<li class="layui-nav-item to-index">
			<a href="./home">首页</a>
		</li>
	</ul>
</div>
<!-- 顶部结束 -->
<!-- 中部开始 -->
<!-- 左侧菜单开始 -->
<div class="left-nav">
	<div id="side-nav">
		<ul id="nav">
			<li>
				<a href="javascript:;">
					<i class="iconfont">&#xe6b8;</i>
					<cite>会员管理</cite>
					<i class="iconfont nav_right">&#xe697;</i>
				</a>
				<ul class="sub-menu">
					<li>
						<a _href="./pages/member/list.html">
							<i class="iconfont">&#xe6a7;</i>
							<cite>会员列表</cite>

						</a>
					</li>
					<li>
						<a _href="./pages/member/del.html">
							<i class="iconfont">&#xe6a7;</i>
							<cite>会员删除</cite>

						</a>
					</li>
					<li>
						<a href="javascript:;">
							<i class="iconfont">&#xe70b;</i>
							<cite>会员管理</cite>
							<i class="iconfont nav_right">&#xe697;</i>
						</a>
						<ul class="sub-menu">
							<li>
								<a _href="./pages/member/addInput.html">
									<i class="iconfont">&#xe6a7;</i>
									<cite>输入框操作</cite>
								</a>
							</li>
							<li>
								<a _href="./pages/404.html">
									<i class="iconfont">&#xe6a7;</i>
									<cite>三级菜单演示</cite>
								</a>
							</li>
							<li><script type="text/javascript" src="./lib/layui/layui.js" charset="utf-8"></script>
                                <script type="text/javascript" src="./lib/layui/lay/modules/element.js" charset="utf-8"></script>
								<a _href="./pages/404.html">
									<i class="iconfont">&#xe6a7;</i>
									<cite>导航菜单演示</cite>
								</a>
							</li>
						</ul>
					</li>
				</ul>
			</li>
			<li>
				<a href="javascript:;">
					<i class="iconfont">&#xe705;</i>
					<cite>文章管理</cite>
					<i class="iconfont nav_right">&#xe697;</i>
				</a>
				<ul class="sub-menu">
					<li>
						<a _href="./pages/article/list.html">
							<i class="iconfont">&#xe6a7;</i>
							<cite>文章列表</cite>
						</a>
					</li>
					<li>
						<a _href="./pages/article/category.html">
							<i class="iconfont">&#xe6a7;</i>
							<cite>分类管理</cite>
						</a>
					</li>
				</ul>
			</li>
		</ul>
	</div>
</div>
<!-- <div class="x-slide_left"></div> -->
<!-- 左侧菜单结束 -->
<!-- 右侧主体开始 -->
<div class="page-content">
	<div class="layui-tab tab" lay-filter="wenav_tab" id="WeTabTip" lay-allowclose="true">
		<ul class="layui-tab-title" id="tabName">
			<li>控制台</li>
		</ul>
		<div class="layui-tab-content">
			<div class="layui-tab-item layui-show">
				<iframe src='./workbench' frameborder="0" scrolling="yes" class="weIframe"></iframe>
			</div>
		</div>
	</div>
</div>
<div class="page-content-bg"></div>
<!-- 右侧主体结束 -->
<!-- 中部结束 -->
<!-- 底部开始 -->
<div class="footer">
	<div class="copyright">Copyright ©2018 WeAdmin v1.0 All Rights Reserved</div>
</div>
<!-- 底部结束 -->
<script type="text/javascript">
    //			layui扩展模块的两种加载方式-示例
    //		    layui.extend({
    //			  admin: '{/}../../static/js/admin' // {/}的意思即代表采用自有路径，即不跟随 base 路径
    //			});
    //			//使用拓展模块
    //			layui.use('admin', function(){
    //			  var admin = layui.admin;
    //			});
	//layui配置全局三方js
    layui.config({//配置自定义js为layui模块
        base: './static/js/',
        version: '1001100'
    });
    layui.use(['jquery'],function(){
        var $ = layui.jquery;
        var form = layui.form;
        $(function(){
            //动态添加菜单
            $.ajax({
                type: "post",
                url: "./permission/menu",
                data: {userId:${user.userId}},
                dataType: "json",
                success: function (data) {
                    var menu;
                    var menus = data.data;
					$.each(menus,function(index,menu){
					    var main_menu = "<li><a href=\"javascript:;\"><cite>"+menu.pname+"</cite><i class=\"iconfont nav_right\">&#xe697;</i></a><ul class=\"sub-menu\">";
					    var sub_meun="";
                        $.each(menu.childPermission,function(index,child){
                            sub_meun = sub_meun+"<li><a _href=\"."+child.purl+"\"><i class=\"iconfont\">&#xe6a7;</i> <cite>"+child.pname+"</cite></a></li>";
						});
						$("#nav").append(main_menu+sub_meun+"</ul></li>");
					});
                }
            });
        });
    });
    layui.use('admin');

</script>
</body>
<!--Tab菜单右键弹出菜单-->
<ul class="rightMenu" id="rightMenu">
	<li data-type="fresh">刷新</li>
	<li data-type="current">关闭当前</li>
	<li data-type="other">关闭其它</li>
	<li data-type="all">关闭所有</li>
</ul>
</html>