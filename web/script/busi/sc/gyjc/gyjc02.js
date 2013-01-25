/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
if(!outJson['result']){
    $("#gyjc_table").hide();
}else{
    $("#gyjc_table").dataTable(tableTheme);
}

$(".info").click(function(){
    var ind = $(this).attr("ind");
    var data = outJson['result'][ind];
    padBackData(data, "#gyjc_dialog");
    $("#gyjc_dialog").dialog("open");
});

$("#gyjc_dialog").dialog({
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

$(".update").click(function(){
    alertMsg("只有录入人才可以修改！");
});