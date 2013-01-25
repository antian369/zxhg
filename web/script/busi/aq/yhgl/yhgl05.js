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
        pzsx : data.sqsx.substr(0, 10),
        pzsx_h : data.sqsx.substr(11, 2)
    };
    padBackData(data, "#pzsx_dialog");
    padBackData(oldData, "#pzsx_dialog");
    $("#zgjl_dialog").dialog("open");
});

$("#zgjl_dialog").dialog({
    autoOpen: false,
    height: 500,
    width: 600,
    modal: true,
    buttons: {
        "审批" : function(){
            $("#pzsx_dialog").dialog("open");
            $( this ).dialog( "close" );
        },
        "关闭": function() {
            $( this ).dialog( "close" );
        }
    }
});

$("#pzsx_dialog").dialog({
    autoOpen: false,
    height: 300,
    width: 600,
    modal: true,
    buttons: {
        "提交" : function(){
            if(checkForm("#pzsx_form")){
                var h = $("#pzsx_form #pzsx_h").val();
                if(h < 0 || h >= 24){
                    alertMsg("批准时限(小时)，必须为0~23");
                    return;
                }
                var options = new AjaxOptions("#pzsx_form");
                options.put("service_code", "P12012");
                options.sus = function(){
                    setTimeout(function(){
                        location.reload();
                    }, 800);
                };
                $.ajax(options);
            }
        },
        "关闭": function() {
            $( this ).dialog( "close" );
        }
    }
});
$("#pzsx_dialog #gsfcrxm").change(function(){
    $("#pzsx_dialog #gsfcr").val("");
});