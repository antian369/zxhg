/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
if(!outJson['result']){
    $("#sx_table").hide();
}else{
    $("#sx_table").dataTable(tableTheme);
}

$(".info").click(function(){
    var ind = $(this).attr("ind");
    var data = outJson['result'][ind];
    padBackData(data, "#sx_dialog");
    $("#sx_dialog").dialog("open");
});

$("#sx_dialog").dialog({
    autoOpen: false,
    height: 500,
    width: 600,
    modal: true,
    buttons: {
        "回复": function() {
            $("#hf_dialog #hfbt").html("回复：" + $("#sx_dialog #sxbt").html());
            $("#hf_dialog").dialog("open");
        },
        "关闭": function() {
            $( this ).dialog( "close" );
        }
    }
});

$("#hf_dialog").dialog({
    autoOpen: false,
    height: 300,
    width: 600,
    modal: true,
    buttons: {
        "发送": function() {
            alertMsg("该功能暂不可用");
        },
        "关闭": function() {
            $( this ).dialog( "close" );
        }
    }
});