/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
if(!outJson['result']){
    $("#gyrz_table").hide();
}else{
    $("#gyrz_table").dataTable(tableTheme);
}

$(".info").click(function(){
    var ind = $(this).attr("ind");
    var data = outJson['result'][ind];
    padBackData(data, "#gyrz_dialog");
    $("#gyrz_dialog").dialog("open");
});

$("#gyrz_dialog").dialog({
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