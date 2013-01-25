/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
if(!outJson['result']){
    $("#zgjl_table").hide();
}else{
    $("#zgjl_table").dataTable(tableTheme);
}

$(".info").click(function(){
    if(depName != "安环部"){
        alertMsg("必须由安环部验收！");
        return;
    }
    var ind = $(this).attr("ind");
    var data = outJson['result'][ind];
    padBackData(data, "#zgjl_dialog");
    var oldData = {
        gsfcr : username,
        gsfcrxm : xm
    };
    padBackData(data, "#gsys_dialog");
    padBackData(oldData, "#gsys_dialog");
    $("#zgjl_dialog").dialog("open");
});

$("#zgjl_dialog").dialog({
    autoOpen: false,
    height: 500,
    width: 600,
    modal: true,
    buttons: {
        "验收" : function(){
            $("#gsys_dialog").dialog("open");
            $( this ).dialog( "close" );
        },
        "关闭": function() {
            $( this ).dialog( "close" );
        }
    }
});

$("#gsys_dialog").dialog({
    autoOpen: false,
    height: 300,
    width: 600,
    modal: true,
    buttons: {
        "提交" : function(){
            var options = new AjaxOptions("#gsys_form");
            options.put("service_code", "P12011");
            options.sus = function(){
                setTimeout(function(){
                    location.reload();
                }, 800);
            };
            $.ajax(options);
        },
        "关闭": function() {
            $( this ).dialog( "close" );
        }
    }
});
$("#gsys_dialog #gsfcrxm").change(function(){
    $("#gsys_dialog #gsfcr").val("");
});