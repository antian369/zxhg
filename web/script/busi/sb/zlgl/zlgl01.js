/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
if(!outJson['result']){
    $("#gylc_table").hide();
}else{
    $("#gylc_table").dataTable(tableTheme);
}

$(".info").click(function(){
    var ind = $(this).attr("ind");
    var data = outJson['result'][ind];
    padBackData(data, "#gylc_dialog");
    $("#gylc_dialog").dialog("open");
});

$("#gylc_dialog").dialog({
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