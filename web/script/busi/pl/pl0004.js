/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

$("#auth_div").hide();

$("#search").click(function(){
    var options = new AjaxOptions("#search_form");
    options.put("service_code", "S11008");
    options.isAlert = false;
    options.sus = function(data){
        var isCheck = "";
        $.each(data["role_limit"], function(i, v){
            if(v.m_id.length == 6){
                isCheck += ",#" + v.m_id;
            }
        });
        createMenu(data["all_menu"], $("#menu_checkbox"));
        $("#menu_div").jstree({
            "themes" : {
                "theme" : "apple"
            },
            "plugins" : [ "themes", "html_data", "checkbox", "sort", "ui" ]
        })
        .bind("select_node.jstree", function (event, data) {
            if(data.rslt.obj.attr("type") == "1"){
                $("#menu_div").jstree("toggle_node",data.rslt.obj);        //打开或关闭自身
            }
        });
        $("#save").attr("role_id", this.get("role_id"));
        $("#auth_div").fadeIn(800, function(){
            $("#menu_div").jstree("check_node", $(isCheck.substr(1)));
        });
    };
    $("#auth_div").fadeOut(100);
    $.ajax(options);
});

$("#all_check").click(function(){
    $("#menu_div").jstree("check_all");
});

$("#all_uncheck").click(function(){
    $("#menu_div").jstree("uncheck_all");
});

$("#save").click(function(){
    var options = new AjaxOptions();
    options.put("service_code", "P11009");
    options.put("role_id", $(this).attr("role_id"));
    options.put("menus", getCheck());
    options.sus = function(){
        $("#auth_div").fadeOut(800);
    };
    $.ajax(options);
});

//以传参方式进入页面时，直接支持查询
if(roleId){
    $("#role_id").val(roleId);
    $("#search").click();
}

/**
 * 获取已选择的项
 */
function getCheck(){
    var check = "";
    $("#menu_div").find(".jstree-undetermined, .jstree-checked").each(function(i, v){
        if(i > 0 ){
            check = check + ",";
        }
        check = check + v.id;
    });
    return check;
}