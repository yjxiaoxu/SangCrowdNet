// 获取并填充权限树形菜单
function fillAuthTree() {

    // 发送ajax请求，获取所有的权限信息
    var ajaxReturn = $.ajax({
        "url" : "assign/get/all/auth.json",
        "type": "post",
        "dataType": "json",
        "async": false
    });

    // 判断请求是否成功
    var status = ajaxReturn.status;
    if (status != 200) {
        layer.msg("请求处理失败:"+ ajaxReturn.status + "," + ajaxReturn.statusText);
        return;
    }

    // 执行到此处表示请求处理成功，从返回的值中获取Auth的Json数据
    var authList = ajaxReturn.responseJSON.data;

    // 准备对 zTree 进行设置的 JSON 对象
    var setting = {
        "data": {
            "simpleData": {
                // 开启简单 JSON 功能
                "enable": true,
                // 使用 categoryId 属性关联父节点，不用默认的 pId 了
                "pIdKey": "categoryId"
            },
            "key": {
                // 使用 title 属性显示节点名称，不用默认的 name 作为属性名了
                "name": "title"
            }
        },
        "check": {
            "enable": true
        }
    };

    // 生成树形结构
    $.fn.zTree.init($("#authTreeDemo"), setting, authList);

    // 获取 zTreeObj 对象
    var zTreeObj = $.fn.zTree.getZTreeObj("authTreeDemo");

    // 调用 zTreeObj 对象的方法，把节点展开
    zTreeObj.expandAll(true);

    // 发送ajax请求，根据角色id获取当前角色已分配的权限，并勾选对应的单选框
    ajaxReturn = $.ajax({
        "url": "auth/get/auth/by/id.json",
        "type": "post",
        "data": {
            "id": window.id
        },
        "dataType": "json",
        "async": false
    });

    if (ajaxReturn.status != 200) {
        layer.msg("请求处理失败:"+ ajaxReturn.status + "," + ajaxReturn.statusText);
        return;
    }

    var authIdArray = ajaxReturn.responseJSON.data;

    // 遍历数组，将用户已有的权限对应的单选框选中
    for (var i = 0; i < authIdArray.length; i++) {

        var authId = authIdArray[i];

        // 根据 id 查询树形结构中对应的节点
        var treeNode = zTreeObj.getNodeByParam("id", authId);

        // 将 treeNode 设置为被勾选
        // checked 设置为 true 表示节点勾选
        var checked = true;

        // checkTypeFlag 设置为 false，表示不“联动”，不联动是为了避免把不该勾选的勾选上
        var checkTypeFlag = false;

        // 执行
        zTreeObj.checkNode(treeNode, checked, checkTypeFlag);
    }
}
// 执行分页，生成分页效果
function generatePage() {
    getPageInfo();
}

// 获取分页数据
function getPageInfo() {

    // 发送ajax请求，获取pageInfo数据
    $.ajax({
        "url": "role/get/page/info.json",
        "data": {
            "pageNum": window.pageNum,
            "pageSize": window.pageSize,
            "roleName": window.roleName
        },
        "type": "post",
        "dataType": "json",
        success: function(response) {

            // 填充表格
            fillTable(response.data);
        },
        error: function(response) {
            layer.msg(response.message);
        }
    });
}

// 填充表格
function fillTable(pageInfo) {
    var html = '';
    $.each(pageInfo.list, function(i, n) {
        html += '<tr>';
        html += '<td>'+(i+1)+'</td>';
        html += '<td><input type="checkbox" id="'+n.id+'" class="online"></td>';
        html += '<td>'+n.name+'</td>';
        html += '<td>';
        html += '<button type="button" id="'+n.id+'" class="btn btn-success btn-xs checkBtn"><i class=" glyphicon glyphicon-check"></i></button>';
        html += '<button type="button" name="'+n.id+'" class="btn btn-primary btn-xs pencilBtn"><i class=" glyphicon glyphicon-pencil"></i></button>';
        html += '<button type="button" name="'+n.id+'" class="btn btn-danger btn-xs removeBtn"><i class=" glyphicon glyphicon-remove"></i></button>';
        html += '</td>';
        html += '</tr>';
    });
    $("#roleBody").html(html);
    //
    generateNavigator(pageInfo);
}

// 生成分页导航条
function generateNavigator(pageInfo) {
    var total = pageInfo.total;

    // 声明相关属性
    var properties = {
        "num_edge_entries": 3,
        "num_display_entries": 5,
        "callback": paginationCallBack,
        "items_per_page": pageInfo.pageSize,
        "current_page": pageInfo.pageNum - 1,
        "prev_text": "上一页",
        "next_text": "下一页"
    }

    // 调用 pagination()函数
    $("#Pagination").pagination(total, properties);
}

// 翻页时执行的回调函数
function paginationCallBack(pageIndex, jQuery) {

    // 修改 window 对象的 pageNum 属性
    window.pageNum = pageIndex + 1;

    // 调用分页函数
    generatePage();

    // 取消页码超链接的默认行为
    return false;
}

// 展示要删除的角色名称的模态窗口
function showRemoveBatchRole(roleArray) {

    // 定义一个全局变量的数组，用来存放要删除的role的id集合
    window.roleIdArray = [];

    // 获取要删除的角色名称，并展示出来
    var html = '';
    for (var i = 0; i < roleArray.length; i++) {

        // 获取要删除的role名称集合
        html += '<tr>';
        html += '<td>'+roleArray[i].roleName+'</td>';
        html += '</tr>';

        // 获取要删除的每一个role的id
        var roleId = roleArray[i].roleId;

        // 将id放入全局变量数组中，方便批量删除时操作
        window.roleIdArray.push(roleId);
    }
    $("#removeRoleTHead").html(html);

    // 打开确认删除模态窗口
    $("#showRemoveRoleModal").modal("show");
}
