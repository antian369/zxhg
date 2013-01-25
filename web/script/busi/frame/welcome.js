/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
window.onresize = function() {
    var height = $(window).height() - 121;
    var width = $(window).width();
    $("#page_body").css("height", height);
    $("#right_work").css("height", height);
    $("#left_work").css("height", height);
    $("#work").css("width", "100%");
    $("#work").height(height -10);
};
window.onresize();
createMenu(menus["menus"], $("#menu"));     //菜单数组
//左边菜单
$("#left_menu").jstree({
    "themes" : {
        "theme" : "apple"
    },
    "plugins" : [ "themes", "html_data","ui" ]
}).bind("select_node.jstree", function (event, data) {
    if(data.rslt.obj.attr("type") == "1"){
        $("#left_menu").jstree("toggle_node",data.rslt.obj);        //打开或关闭自身
    }
})
.delegate("a", "click", function (event, data) {
    event.preventDefault();
    if(event.target.target){
        work.location.href=event.target.href;       //如果是超链接，进行页面跳转
    }
});
setTimeout(function () {
    $.jstree._reference("#left_menu").open_node("#sc,#sb,#pl,#bq,#aq,#jy,#wd");
}, 800);
