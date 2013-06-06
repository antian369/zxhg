//构建部门树
createUserTree(outJson["dep_tree"], $("#dep_tree"));

$("#dep_tree_div").jstree({
    "themes" : {
        "theme" : "apple"
    },
    "plugins" : [ "themes", "html_data", "checkbox", "sort", "ui" ]
})
.bind("select_node.jstree", function (event, data) {
    if(data.rslt.obj.attr("type") == "dep"){
        $("#dep_tree_div").jstree("toggle_node",data.rslt.obj);        //打开或关闭自身
    }
});

$("#sub").click(function(){
    if(!getCheck()){
        alertMsg("请从右边侧栏中至少选择一名收信人！");
        return;
    }
    if(checkForm("#data_form")){
        console.log(editor.html());
//        alertMsg("该功能暂不可用！");
    //提交前设置内容
    //                if(editor.isEmpty()){
    //                    showErrorDialog("内容不能为空！");
    //                    return;
    //                }
    //                $("#news_content").val(editor.html());

    //清除内容
    //                editor.html('');
    }
});

/**
 * 获取已选择的项
 */
function getCheck(){
    var check = "";
    $("#dep_tree_div").find(".jstree-checked[type='user']").each(function(i, v){
        if(i > 0 ){
            check = check + ",";
        }
        check = check + v.id;
    });
    return check;
}