<%--
  Created by IntelliJ IDEA.
  User: 小许智能
  Date: 2021/9/15
  Time: 22:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="editModal" class="modal fade" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"
                        aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title"> 尚筹网系统弹窗</h4>
            </div>
            <div class="modal-body">
                <div class="form-group has-success has-feedback">
                    <input type="text" name="editRoleName" class="form-control" id="inputSuccess4"  autofocus>
                    <span class="glyphicon glyphicon-user form-control-feedback"></span>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button id="editRoleBtn" type="button" class="btn btn-success"> 更 新</button>
            </div>
        </div>
    </div>
</div>
