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
    var ind = $(this).attr("ind");
    var data = outJson['result'][ind];
    if(data.yhdw != dep){
        alertMsg("只能验收本单位的隐患");
        return;
    }
    padBackData(data, "#zgjl_dialog");
    var oldData = {
        zgr : username,
        zgrxm : xm,
        sqsx : data.sqsx.substr(0, 10),
        sqsx_h : data.sqsx.substr(11, 2)
    };
    padBackData(data, "#yssq_dialog");
    padBackData(oldData, "#yssq_dialog");
    $("#zgjl_dialog").dialog("open");
});

$("#zgjl_dialog").dialog({
    autoOpen: false,
    height: 400,
    width: 600,
    modal: true,
    buttons: {
        "延时申请" : function(){
            $("#yssq_dialog").dialog("open");
            $( this ).dialog( "close" );
        },
        "关闭": function() {
            $( this ).dialog( "close" );
        }
    }
});

$("#yssq_dialog").dialog({
    autoOpen: false,
    height: 300,
    width: 600,
    modal: true,
    buttons: {
        "提交" : function(){
            if(checkForm("#yssq_form")){
                var h = $("#yssq_form #sqsx_h").val();
                if(h < 0 || h >= 24){
                    alertMsg("整改时限(小时)，必须为0~23");
                    return;
                }
                var options = new AjaxOptions("#yssq_form");
                options.put("service_code", "P12009");
                options.sus = function(){
                    setTimeout(function(){
                        reloadPage();
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
$("#yssq_dialog #fcfcrxm").change(function(){
    $("#yssq_dialog #fcfcr").val("");
});