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
    padBackData(data, "#yhhs_dialog");
    $("#yhsg_dialog").dialog("open");
});

$("#yhsg_dialog").dialog({
    autoOpen: false,
    height: 400,
    width: 600,
    modal: true,
    buttons: {
        "核实" : function(){
            $("#yhhs_dialog").dialog("open");
            $( this ).dialog( "close" );
        },
        "不是隐患" : function(){
            if(confirm("是否确认？")){
                var options = new AjaxOptions();
                options.put("service_code", "P12015");
                options.put("yh_id", $("#yhsg_dialog #yh_id").val());
                options.put("sgzt", "0");
                options.sus = function(){
                    reloadPage();
                };
                $.ajax(options);
            }
        },
        "关闭": function() {
            $( this ).dialog( "close" );
        }
    }
});

$("#yhhs_dialog").dialog({
    autoOpen: false,
    height: 500,
    width: 650,
    modal: true,
    buttons: {
        "提交" : function(){
            if(checkForm("#yhhs_form")){
                var h = $("#sqsx_h").val();
                if(h < 0 || h >= 24){
                    alertMsg("整改时限(小时)，必须为0~23");
                    return;
                }
                var options = new AjaxOptions("#yhhs_form");
                options.put("service_code", "P12015");
                options.put("sgzt", "2");
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

$("#yhhs_dialog #zgrxm").change(function(){
    $("#yhhs_dialog #zgr").val("");
});