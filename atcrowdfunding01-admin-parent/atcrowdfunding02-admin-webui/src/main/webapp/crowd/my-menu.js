// 在鼠标离开节点范围时删除按钮组
function myRemoveHoverDom(treeId, treeNode) {
    // 获取要删除的按钮组对象
    var btnGroup = treeNode.tId + "group";

    // 删除按钮组
    $("#" + btnGroup).remove();
}

// 在鼠标进入节点范围内添加按钮组
function myAddHoverDom(treeId, treeNode) {

    // 按钮组的标签结构：<span><a><i></i></a><a><i></i></a></span>
    // 按钮组出现的位置：节点中 treeDemo_n_a 超链接的后面

    // 准备各个按钮的 HTML 标签
    var addBtn = "<a id='"+treeNode.id+"' class='btn btn-info dropdown-toggle btn-xs addBtn'style='margin-left:10px;padding-top:0px;' href='#' title='添加子节点'>&nbsp;&nbsp;<i class='fa fa-fw fa-plus rbg '></i></a>";
    var removeBtn = "<a id='"+treeNode.id+"' class='btn btn-info dropdown-toggle btn-xs removeBtn'style='margin-left:10px;padding-top:0px;' href='#' title='删除节点'>&nbsp;&nbsp;<i class='fa fa-fw fa-times rbg '></i></a>";
    var editBtn = "<a id='"+treeNode.id+"' class='btn btn-info dropdown-toggle btn-xs editBtn'style='margin-left:10px;padding-top:0px;' href='#' title='修改节点'>&nbsp;&nbsp;<i class='fa fa-fw fa-edit rbg '></i></a>";

    // 用来保存要添加的按钮组
    var btnHtml = "";

    // 用来确保每个节点只显示一组按钮组
    var btnGroup = treeNode.tId + "group";
    if ($("#" + btnGroup).length > 0) {
        return;
    }

    // 获取当前节点的级别
    var level = treeNode.level;

    // level: 0     级别为根节点，只能 添加 节点
    if (level == 0) {
        btnHtml += addBtn;
    }

    // level: 1     级别为根节点的子节点，能 添加、修改、删除（有要求，没有子节点时可删除，反之） 节点
    if (level == 1) {
        btnHtml += editBtn;
        btnHtml += " ";

        // 获取当前节点的子节点的数目
        var childrenLength = treeNode.children.length;

        if (childrenLength == 0) {
            btnHtml += removeBtn;
            btnHtml += " ";
        }
        btnHtml += addBtn;
    }

    // level: 2     级别为根节点，能 修改、删除 节点
    if (level == 2) {
        btnHtml += editBtn;
        btnHtml += " ";
        btnHtml += removeBtn;
    }

    //alert(btnHtml);
    // 找到要添加按钮组的位置的前一个超链接
    var anchorId = treeNode.tId + "_a";

    // 在超链接后面追加span便签
    $("#"+anchorId).after("<span id='"+btnGroup+"'>"+btnHtml+"</span>");
}

// 发送ajax请求，刷新菜单页面
function generateTree() {

    // 发送ajax请求，获取生成树形结构的JSON数据
    $.ajax({
        "url": "menu/get/tree.json",
        "type": "post",
        "dataType": "json",
        "success": function (response) {
            var result = response.result;
            if ("SUCCESS" == result) {

                // 创建JSON对象用于存储对zTree所做的设置
                var setting = {

                    // 修改默认图标
                    "view": {
                        "addDiyDom": myAddDiyDom,
                        "addHoverDom": myAddHoverDom,
                        "removeHoverDom": myRemoveHoverDom
                    },
                    "data": {
                        "key": {
                            "url": "xiaobaobei"
                        }
                    }
                };

                // 从响应体中获取生成树形结构的JSON数据
                var zNodes = response.data;

                // 初始化树形结构
                $.fn.zTree.init($("#treeDemo"), setting, zNodes);
            }
            if ("FAILED" == result) {
                layer.msg(response.message);
            }
        }
    });
}

// 修改默认图标
function myAddDiyDom(treeId, treeNode) { // treeId 是整个树形结构附着的 ul 标签的 id  // treeNode 当前树形节点的全部的数据，包括从后端查询得到的 Menu 对象的全部属性

    // zTree 生成 id 的规则
    // 例子：treeDemo_7_ico
    // 解析：ul 标签的 id_当前节点的序号_功能
    // 提示：“ul 标签的 id_当前节点的序号”部分可以通过访问 treeNode 的 tId 属性得到
    // 根据 id 的生成规则拼接出来 span 标签的 id
    var spanId = treeNode.tId + "_ico";
    // 根据控制图标的 span 标签的 id 找到这个 span 标签
    // 删除旧的 class
    // 添加新的 class
    $("#" + spanId)
        .removeClass()
        .addClass(treeNode.icon);
}

