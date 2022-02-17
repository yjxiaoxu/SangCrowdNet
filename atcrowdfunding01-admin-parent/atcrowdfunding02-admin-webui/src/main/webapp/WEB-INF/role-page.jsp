<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CN">
<%@ include file="/WEB-INF/include-header.jsp" %>
<head>
    <link rel="stylesheet" href="css/pagination.css">
    <link rel="stylesheet" href="ztree/zTreeStyle.css"/>
    <script type="text/javascript" src="jquery/jquery.pagination.js"></script>
    <script type="text/javascript" src="ztree/jquery.ztree.all-3.5.min.js"></script>
    <script type="text/javascript" src="crowd/myrole.js"></script>
    <script type="text/javascript">
        $(function() {

            // 为分页操作初始化数据
            window.pageNum = 1;
            window.pageSize = 5;
            window.roleName = "";

            // 执行分页操作,生成页面
            generatePage();

            // 给符号 √ 绑定鼠标单击事件
           $("#roleBody").on("click", ".checkBtn", function() {

               // 获取当前要操作的角色id并放入到全局变量中，方便执行权限分配操作时使用
               window.id = this.id;

               // 获取并填充权限树形菜单
                fillAuthTree();

               // 打开模态窗口
               $("#assignAuthModal").modal("show");

           });

            // 给模态窗口中的分配按钮绑定鼠标单击事件
            $("#assignBtn").click(function() {

                // 获取用户选中的要分配权限的id集合
                var authIdArray = [];

                // 获取 zTreeObj 对象
                var zTreeObj = $.fn.zTree.getZTreeObj("authTreeDemo");

                // 获取全部被勾选的节点
                var checkedNodes = zTreeObj.getCheckedNodes();

                // 遍历 checkedNodes
                for(var i = 0; i < checkedNodes.length; i++) {
                    var checkedNode = checkedNodes[i];
                    var authId = checkedNode.id;

                    // 因为auth表中id为 1 和 4 的代表的只是某一个权限在哪个模块下，可以不需要保存到inner_role_auth表中
                    if (authId != 1 && authId != 4) {
                        authIdArray.push(authId);
                    }
                }

                var responseBody = {

                    // 为了服务器端 handler 方法能够统一使用 List<Integer>方式接收数据，roleId 也存入数组
                    "roleIdArray": [window.id],
                    "authIdArray": authIdArray
                };

                // 将值转换为Json字符串
                responseBody = JSON.stringify(responseBody);

                // 发送ajax请求，给当前角色分配权限
                $.ajax({
                    "url": "auth/assign/auth/to/role.json",
                    "type": "post",
                    "data": responseBody,
                    "contentType":"application/json;charset=UTF-8",
                    "dataType": "json",
                    "success": function(response) {
                        if ("SUCCESS" == response.result) {

                            layer.msg("操作成功");

                            // 关闭模态窗口
                            $("#assignAuthModal").modal("hide");
                        }
                        if ("FAILED" == response.result) {
                            layer.msg("操作失败" + response.status +response.statusText);
                        }
                    },
                    "error": function(response) {
                        layer.msg("操作失败" + response.status +response.statusText);
                    }
                });

            });

            // 给查询按钮绑定鼠标单击事件
            $("#roleSearchBtn").click(function() {

                // 将用户输入的关键字赋值给roleName
                window.roleName = $.trim($("#roleName").val());

                // 执行分页操作,生成页面
                generatePage();
            });

            // 给新增按钮绑定鼠标单击事件
            $("#showAddRoleBtn").click(function() {
                $("#addModal").modal("show");
            });

            // 给模态窗口的保存按钮绑定鼠标单击事件
            $("#saveRoleBtn").click(function() {

                // 获取用户要保存的角色名称
                var name = $.trim($("input[name = 'saveRoleName']").val());

                // 发送ajax请求，保存角色名称
                $.ajax({
                    "url" : "role/save.json",
                    "data": {
                        "name": name
                    },
                    "type": "post",
                    "dataType": "json",
                    success: function(response) {
                        var result = response.result;
                        if (result == "SUCCESS") {

                            // 弹出提示框
                            layer.msg("操作成功");

                            // 跳转到新添加角色的那一页
                            window.pageNum = 999999;

                            // 执行分页操作,生成页面
                            generatePage();
                        } else {
                            layer.msg(response.message);
                        }
                    }
                });

                // 清空文本框里面的记录
                $("input[name = 'saveRoleName']").val("");

                // 关闭添加角色的模态窗口
                $("#addModal").modal("hide");
            });

            // 给角色修改图标绑定on事件
            $("#roleBody").on("click", ".pencilBtn", function() {

                // 获取要修改的角色名称
                var roleName = $(this).parent().prev().text();

                // 将要修改的角色的id保存到全局变量中，方便在修改操作时获取
                window.id = this.name;

                // 将要修改的角色名称赋给修改文本框里
                $("input[name = 'editRoleName']").val(roleName);

                // 打开修改模态窗口
                $("#editModal").modal("show");
            });

            // 给模态窗口的更新按钮绑定鼠标单击事件
            $("#editRoleBtn").click(function() {

                // 获取用户更改后的角色名称
                var name = $("input[name = 'editRoleName']").val();

                // 发送ajax请求更新角色名称
                $.ajax({
                    "url": "role/edit.json",
                    "data": {
                        "id": window.id,
                        "name": name
                    },
                    "type": "post",
                    "dataType": "json",
                    success: function(response) {
                        var result = response.result;
                        if (result == "SUCCESS") {

                            // 弹出提示框
                            layer.msg("操作成功");

                            // 执行分页操作,生成页面
                            generatePage();
                        } else {
                            layer.msg(response.message);
                        }
                    }
                });

                // 关闭模态窗口
                $("#editModal").modal("hide");
            });

            // 给全选框绑定鼠标单击事件
            $("#selectAll").click(function() {

                // 给单选框赋予选中或不选中
                $("input[class = online]").prop("checked", this.checked);
            });

            // 给单选框绑定on事件，通过单选框选中的个数操作全选框
            $("#roleBody").on("click", ".online", function() {

                // 给全选框赋予选中或不选中
                $("#selectAll").prop("checked", $("input[class = online]").length == $("input[class = online]:checked").length);
            });

            // 给删除图形（×）绑定on事件
            $("#roleBody").on("click", ".removeBtn", function() {

                // 创建数组用来存放要删除的角色信息的集合
                var roleArray = new Array();

                // 获取要删除的角色名称
                var roleName = $(this).parent().prev().text();

                // 将要修改的角色的id保存到全局变量中，方便在修改操作时获取
                var roleId = this.name;
                roleArray.push({
                    "roleId": roleId,
                    "roleName": roleName
                });

                // 弹出确认删除模态窗口
                showRemoveBatchRole(roleArray);
            });

            // 给删除按钮添加鼠标单击事件
            $("#removeRoleBtn").click(function() {

                // 创建数组用来存放要删除的角色信息的集合
                var roleArray = new Array();

                // 获取要删除的角色单选框集合
                var $onlines = $("input[class = online]:checked");
                if ($onlines.length == 0) {
                    layer.msg("请至少选中一项进行操作");
                } else {
                    for (var i = 0; i < $onlines.length; i++) {
                        var roleId = $onlines[i].id;
                        var roleName = $($onlines[i]).parent().next().text();
                        roleArray.push({
                            "roleId" : roleId,
                            "roleName": roleName
                        });
                    }

                    // 弹出确认删除模态框
                    showRemoveBatchRole(roleArray);
                }
            });

            // 给模态窗口的确认删除按钮添加鼠标单击事件
            $("#ConfirmRemoveBtn").click(function() {

                // 获取要删除的role的id集合，并转换为json格式
               var requestBody = JSON.stringify(window.roleIdArray);

               // 发送ajax请求，删除角色名称
                $.ajax({
                    "url": "role/remove/byId/list.json",
                    "type": "post",
                    "data": requestBody,
                    "dataType": "json",
                    "contentType": "application/json;charset=UTF-8",
                    "async": false,
                    "success": function(response) {
                        if ("SUCCESS" == response.result) {
                            layer.msg("删除成功！");
                        } else {
                            layer.msg("操作失败！");
                        }
                    },
                    "error": function(response) {
                        layer.msg(response.message);
                    }
                });

                // 执行分页操作,生成页面
                generatePage();

                // 取消全选框的选中
                $("#selectAll").prop("checked", false);

                // 关闭模态窗口
                $("#showRemoveRoleModal").modal("hide");
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
                <div class="panel-heading">
                    <h3 class="panel-title"><i class="glyphicon glyphicon-th"></i> 数据列表</h3>
                </div>
                <div class="panel-body">
                    <form class="form-inline" role="form" style="float:left;">
                        <div class="form-group has-feedback">
                            <div class="input-group">
                                <div class="input-group-addon">查询条件</div>
                                <input id="roleName" class="form-control has-success" type="text" placeholder="请输入查询条件">
                            </div>
                        </div>
                        <button type="button" id="roleSearchBtn" class="btn btn-warning"><i class="glyphicon glyphicon-search"></i> 查询</button>
                    </form>
                    <button type="button" id="removeRoleBtn" class="btn btn-danger" style="float:right;margin-left:10px;"><i class=" glyphicon glyphicon-remove"></i> 删除</button>
                    <button type="button" class="btn btn-primary" style="float:right;" id="showAddRoleBtn"><i class="glyphicon glyphicon-plus"></i> 新增</button>
                    <br>
                    <hr style="clear:both;">
                    <div class="table-responsive">
                        <table class="table  table-bordered">
                            <thead>
                            <tr>
                                <th width="30">#</th>
                                <th width="30"><input type="checkbox" id="selectAll"></th>
                                <th>名称</th>
                                <th width="100">操作</th>
                            </tr>
                            </thead>
                            <tbody id="roleBody">
<%--                            <tr>--%>
<%--                                <td>1</td>--%>
<%--                                <td><input type="checkbox"></td>--%>
<%--                                <td>PM - 项目经理</td>--%>
<%--                                <td>--%>
<%--                                    <button type="button" class="btn btn-success btn-xs"><i class=" glyphicon glyphicon-check"></i></button>--%>
<%--                                    <button type="button" class="btn btn-primary btn-xs"><i class=" glyphicon glyphicon-pencil"></i></button>--%>
<%--                                    <button type="button" class="btn btn-danger btn-xs"><i class=" glyphicon glyphicon-remove"></i></button>--%>
<%--                                </td>--%>
<%--                            </tr>--%>
                            </tbody>
                            <tfoot>
                            <tr>
                                <td colspan="6" align="center">
                                    <div id="Pagination" class="pagination"><!-- 这里显示分页 --></div>
                                </td>
                            </tr>

                            </tfoot>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<%@ include file="/WEB-INF/model-role-add.jsp" %>
<%@ include file="/WEB-INF/model-role-edit.jsp" %>
<%@ include file="/WEB-INF/model-role-remove.jsp" %>
<%@ include file="/WEB-INF/modal-role-assign-auth.jsp" %>
</body>
</html>