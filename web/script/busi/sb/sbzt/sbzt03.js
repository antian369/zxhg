/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
if(!outJson['result']){
    $("#gysb_table").hide();
}else{
    $("#gysb_table").dataTable(tableTheme);
}

$(".info").click(function(){
    var ind = $(this).attr("ind");
    var data = outJson['result'][ind];
    padBackData(data, "#gysb_dialog");
    $("#gysb_dialog").dialog("open");
});

$("#gysb_dialog").dialog({
    autoOpen: false,
    height: 500,
    width: 600,
    modal: true,
    buttons: {
        "提交" : function(){
            alertMsg("该功能暂不可用！");
        },
        "关闭": function() {
            $( this ).dialog( "close" );
        }
    }
});