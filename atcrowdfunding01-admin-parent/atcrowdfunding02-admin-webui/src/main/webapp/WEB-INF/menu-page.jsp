<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <%@ include file="/WEB-INF/include-header.jsp" %>
    <link rel="stylesheet" href="ztree/zTreeStyle.css"/>
    <script type="text/javascript" src="ztree/jquery.ztree.all-3.5.min.js"></script>
    <script type="text/javascript" src="crowd/my-menu.js"></script>
    <script type="text/javascript">
        $(function() {

            // 刷新菜单页面
            generateTree();

            // 给删除模态窗口中的ok按钮绑定鼠标单击事件
            $("#confirmBtn").click(function () {

                // 获取用户要修改的数据，注意：pid不改变
                var id = window.id;

                // 发送ajax请求，修改menu信息
                $.ajax({
                    "url": "menu/remove.json",
                    "data": {
                        "id": id
                    },
                    "type": "post",
                    "dataType": "json",
                    "success": function(response) {
                        var result = response.result;
                        if ("SUCCESS" == result) {

                            layer.msg("操作成功！");

                            // 刷新菜单页面
                            generateTree();
                        }
                        if ("FAILED" == result) {
                            layer.msg(response.message);
                        }
                    },
                    "error": function(response) {
                        layer.msg(response.status + response.statusText);
                    }
                });
                // 关闭模态窗口
                $("#menuConfirmModal").modal("hide");
            });

            // 给删除图案绑定on函数
            $("#treeDemo").on("click", ".removeBtn", function() {

                // 获取要删除节点的id,并存放在全局变量中，方便删除操作时使用
                window.id = this.id;

                // 获取zTreeObj对象
                var zTreeObj = $.fn.zTree.getZTreeObj("treeDemo");

                // 根据id查找节点对象
                var removeNode = zTreeObj.getNodeByParam("id", this.id)

                // 将要删除的menu名称回显到span标签中
                $("#removeNodeSpan").text(" "+removeNode.name+" ");

                // 打开删除模态窗口
                $("#menuConfirmModal").modal("show");

                // 取消超链接默认行为
                return false;
            });

            // 给修改模态窗口的更新按钮绑定鼠标单击事件
            $("#menuEditBtn").click(function () {

                // 获取用户要修改的数据，注意：pid不改变
                var id = window.id;
                var name = $("#menuEditModal [name = name]").val();
                var url = $("#menuEditModal [name = url]").val();
                var icon = $("#menuEditModal [name = icon]:checked").val();

                // 发送ajax请求，修改menu信息
                $.ajax({
                    "url": "menu/edit.json",
                    "data": {
                        "id": id,
                        "name": name,
                        "url": url,
                        "icon": icon
                    },
                    "type": "post",
                    "dataType": "json",
                    "success": function(response) {
                        var result = response.result;
                        if ("SUCCESS" == result) {

                            layer.msg("操作成功！");

                            // 刷新菜单页面
                            generateTree();
                        }
                        if ("FAILED" == result) {
                            layer.msg(response.message);
                        }
                    },
                    "error": function(response) {
                        layer.msg(response.status + response.statusText);
                    }
                });
                // 关闭模态窗口
                $("#menuEditModal").modal("hide");
            });

            // 给修改符号绑定鼠标单击事件
            $("#treeDemo").on("click", ".editBtn", function() {

                // 获取要修改节点id,并保存到全局变量中，方便修改操作时使用
                window.id = this.id;

                // 获取zTreeObj对象
                var zTreeObj = $.fn.zTree.getZTreeObj("treeDemo");

                // 根据id查找节点对象
                var editNode = zTreeObj.getNodeByParam("id", this.id)

                // 回显表单
                $("#menuEditModal [name = name]").val(editNode.name);
                $("#menuEditModal [name = url]").val(editNode.url);
                $("#menuEditModal [name = icon]").val([editNode.icon]);

                //打开修改模态窗口
                $("#menuEditModal").modal("show");

                // 取消超链接默认行为
                return false;
            });

            // 给添加符号（+）绑定鼠标单击事件
           $("#treeDemo").on("click", ".addBtn", function() {

               // 新添加的节点的pId即为当前添加子节点的节点id
               // 将当前节点的id放入到全局变量中，方便保存节点操作时使用
               window.id = this.id;

               // 打开添加模块窗口
               $("#menuAddModal").modal("show");

               // 取消超链接默认行为
               return false;
           });
           // 给添加模态框的保存按钮绑定鼠标单击事件
            $("#menuSaveBtn").click(function() {

                //获取用户输入的数据
                var name = $("#menuAddModal [name = name]").val();
                var url = $("#menuAddModal [name = url]").val();
                var icon = $("#menuAddModal [name = icon]:checked").val();

                // 发送ajax请求，保存menu对象
                $.ajax({
                    "url": "menu/save.json",
                    "data": {
                        "pid": window.id,
                        "name": name,
                        "url": url,
                        "icon": icon
                    },
                    "type": "post",
                    "dataType": "json",
                    "success": function(response) {
                        var result = response.result;
                        if ("SUCCESS" == result) {
                            layer.msg("操作成功！ ");

                            //刷新菜单页面
                            generateTree();
                        }
                    },
                    "error": function(response) {
                        layer.msg(response.status + response.statusText);
                    }
                });

                // 关闭模态窗口
                $("#menuAddModal").modal("hide");

                // 重置模态窗口里面的内容
                $("#menuResetBtn").click();
            });

        });
    </script>
</head>

<body>
<%@ include file="/WEB-INF/include-nav.jsp" %>

<div class="container-fluid">
    <div class="row">
        <%@ include file="/WEB-INF/include-sidebar.jsp" %>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">

            <div class="panel panel-default">
                <div class="panel-heading"><i class="glyphicon glyphicon-th-list"></i> 权限菜单列表
                    <div style="float:right;cursor:pointer;" data-toggle="modal" data-target="#myModal"><i
                            class="glyphicon glyphicon-question-sign"></i></div>
                </div>
                <div class="panel-body">
                    <ul id="treeDemo" class="ztree">

                    </ul>
                </div>
            </div>
        </div>
    </div>
</div>
<%@ include file="/WEB-INF/modal-menu-add.jsp" %>
<%@ include file="/WEB-INF/modal-menu-confirm.jsp" %>
<%@ include file="/WEB-INF/modal-menu-edit.jsp" %>
</body>
</html>