<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <%@ include file="/WEB-INF/include-header.jsp" %>
    <script type="text/javascript">
        $(function() {

            // 给提交超链接绑定鼠标单击事件
            $("#submitBtn").click(function() {

                // 在表单提交前把用户要提交的角色全部选中
                $("select:eq(1)>option").prop("selected", "selected");


            });

            // 给右移（>）标签帮点鼠标单击事件
            $("#toRight").click(function() {

                // 将左边选中的角色名移动到右边
                $("select:eq(0)>option:selected").appendTo($("select:eq(1)"));

            });

            // 给左移（<）标签帮点鼠标单击事件
            $("#toLeft").click(function() {

                // 将左边选中的角色名移动到右边
                $("select:eq(1)>option:selected").appendTo($("select:eq(0)"));

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
            <ol class="breadcrumb">
                <li><a href="#">首页</a></li>
                <li><a href="#">数据列表</a></li>
                <li class="active">分配角色</li>
            </ol>
            <div class="panel panel-default">
                <div class="panel-body">
                    <form action="assign/do/admin/assign/role/page.html" method="post" role="form" class="form-inline">
                        <input type="hidden" name="adminId" value="${param.adminId}" />
                        <input type="hidden" name="pageNum" value="${param.pageNum}" />
                        <input type="hidden" name="keyWord" value="${param.keyWord}" />
                        <div class="form-group">
                            <label for="exampleInputPassword1">未分配角色列表</label><br>
                            <select class="form-control" multiple="multiple" size="10" style="width:100px;overflow-y:auto;">
                                <c:forEach items="${requestScope.unAssignedRoleList}" var="unAssignedRole">
                                    <option value="${unAssignedRole.id}">${unAssignedRole.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="form-group">
                            <ul>
                                <li id="toRight" class="btn btn-default glyphicon glyphicon-chevron-right"></li>
                                <br>
                                <li id="toLeft" class="btn btn-default glyphicon glyphicon-chevron-left"
                                    style="margin-top:20px;"></li>
                            </ul>
                        </div>
                        <div class="form-group" style="margin-left:40px;">
                            <label for="exampleInputPassword2">已分配角色列表</label><br>
                            <select name="roleIdList" class="form-control" multiple="multiple" size="10" style="width:100px;overflow-y:auto;">
                                <c:forEach items="${requestScope.assignedRoleList}" var="assignedRole">
                                    <option value="${assignedRole.id}">${assignedRole.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <button id="submitBtn" type="submit" style="width: 150px;margin: 50px auto 0px auto" class="btn btn-lg btn-success btn-block">提交</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>