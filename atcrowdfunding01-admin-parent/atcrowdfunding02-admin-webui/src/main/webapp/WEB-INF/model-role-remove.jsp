<%--
  Created by IntelliJ IDEA.
  User: 小许智能
  Date: 2021/9/16
  Time: 17:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="showRemoveRoleModal" class="modal fade" tabindex="-1" role="dialog">
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
                <table class="table">
                    <caption>你要删除下列角色？</caption>
                    <thead id="removeRoleTHead">

                    </thead>
                </table>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button id="ConfirmRemoveBtn" type="button" class="btn btn-primary"> 确定删除</button>
            </div>
        </div>
    </div>
</div>