/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

$("#sg_table").dataTable(tableTheme);
var ind;        //查看的第几行
/**
 * 详细信息按键及弹窗
 */
$(".info").click(function(){
    ind = $(this).attr("ind");
    var json = outJson["result"][$(this).attr("ind")];
    padBackData(json, "#cksg_dialog");
    $("#cksg_dialog").dialog("open");
});
$("#cksg_dialog").dialog({
    autoOpen: false,
    height: 500,
    width: 600,
    modal: true,
    buttons: {
        "关闭": function() {
            $( this ).dialog( "close" );
        }
    }
});
