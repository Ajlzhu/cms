/*
 * @Author: https://github.com/WangEn
 * @Author: https://gitee.com/lovetime/
 * @Date:   2018-03-27
 * @lastModify 2018-3-28
 * +----------------------------------------------------------------------
 * | WeAdmin 表格table中多个删除等操作公用js
 * | 有改用时直接复制到对应页面也不影响使用
 * +----------------------------------------------------------------------
 */
 layui.extend({
     admin: '{/}./static/js/admin'
 });
layui.use(['laydate', 'jquery', 'admin','table'], function() {
	var laydate = layui.laydate;
	var	$ = layui.jquery;
	var	admin = layui.admin;
	var table = layui.table;
	//执行一个laydate实例
	laydate.render({
		elem: '#start' //指定元素
	});
	//执行一个laydate实例
	laydate.render({
		elem: '#end' //指定元素
	});
	/*用户-停用*/
	window.member_stop = function (obj, id) {
		layer.confirm('确认要停用吗？', function(index) {
			if($(obj).attr('title') == '启用') {

				//发异步把用户状态进行更改
				$(obj).attr('title', '停用')
				$(obj).find('i').html('&#xe62f;');

				$(obj).parents("tr").find(".td-status").find('span').addClass('layui-btn-disabled').html('已停用');
				layer.msg('已停用!', {
					icon: 5,
					time: 1000
				});

			} else {
				$(obj).attr('title', '启用')
				$(obj).find('i').html('&#xe601;');

				$(obj).parents("tr").find(".td-status").find('span').removeClass('layui-btn-disabled').html('已启用');
				layer.msg('已启用!', {
					icon: 5,
					time: 1000
				});
			}
		});
	}

    /*用户-删除*/
    window.delUser = function (id) {
        $.ajax({
            type: "post",
            url: "./user/delete/id",
            data: {id:id},
            dataType: "json",
            success: function (data) {
                if(data.success){
                    layer.msg(data.message,{
                        icon: 1,
                        time: 1000
                    });
                }else{
                    layer.msg('data.message',{
                        icon: 2,
                        time: 1000
                    });
                }
            }
        });
    }
    /*用户-批量删除*/
	window.delSelected = function () {
	    //获取选中行数据
        var data = table.checkStatus('userTable').data;
        var ids = '';
        if (data.length<1) {
            layer.msg('请选中要删除的数据',{
                time: 1000
            });
            return
        }else{
            $.each(data,function(index,user){
                ids += ','+user.userId;
            });
            //截取拼接的字符串
            ids = ids.substr(1);
            layer.confirm('确认要删除吗？', function() {
                $.ajax({
                    type: "post",
                    url: "./user/delete/id",
                    data: {id:ids},
                    dataType: "json",
                    success: function (data) {
                        if(data.success){
                            layer.msg(data.message,{
                                icon: 1,
                                time: 1000
                            });
                        }else{
                            layer.msg('data.message',{
                                icon: 2,
                                time: 1000
                            });
                        }
                    }
                });
            });
        }
	}
	window.editUser = function(title, url, data, w, h){
        if(title == null || title == '') {
            title = false;
        };
        if(url == null || url == '') {
            url = "404.html";
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
                //设置用户状态 为1默认启用
                if (data.status == 1 ) {
                    body.contents().find("#status").prop("checked",true);
                    body.contents().find("#status").val(1);
                }
                body.contents().find("#userId").val(data.userId);
                body.contents().find("#username").val(data.username);
                body.contents().find("#nickname").val(data.nickname);
                body.contents().find("#phone").val(data.phone);
                body.contents().find("#L_email").val(data.email);

                body.contents().find("#dept").val(data.dept.deptId);
            },
            error: function(layero, index) {
                layer.msg("编辑页面打开失败");
            }
        });
    }
});