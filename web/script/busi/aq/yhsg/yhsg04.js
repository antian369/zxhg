/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
if(!outJson['result']){
    $("#yhsg_table").hide();
}else{
    $("#yhsg_table").dataTable(tableTheme);
}

$(".info").click(function(){
    var ind = $(this).attr("ind");
    var data = outJson['result'][ind];
    padBackData(data, "#yhsg_dialog");
    $("#yhsg_dialog").dialog("open");
});

$("#yhsg_dialog").dialog({
    autoOpen: false,
    height: 510,
    width: 600,
    modal: true,
    buttons: {
        "关闭": function() {
            $( this ).dialog( "close" );
        }
    }
});
