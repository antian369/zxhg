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
    var options = new AjaxOptions();
    options.put("service_code", "S12013");
    options.put("yh_id", data["yh_id"]);
    options.isAlert = false;
    options.isPadBack = false;
    options.sus = function(d){
        var lastZgjl;
        for(var index in d["zgjl"]){
            if(d["zgjl"][index].zgjg == "0"){
                lastZgjl = d["zgjl"][index];        //找到正在整改的那一条记录
                var oldData = {
                    zgr : $("#fcys_form #zgr").val(),
                    zgrxm : $("#fcys_form #zgrxm").val(),
                    fcfcr : $("#fcys_form #fcfcr").val(),
                    fcfcrxm : $("#fcys_form #fcfcrxm").val()

                };
                padBackData(d, "#fcys_dialog");
                padBackData(lastZgjl, "#fcys_dialog")
                padBackData(oldData, "#fcys_dialog");
                $("#fcys_dialog").dialog("open");
            }
        }
        if(!lastZgjl){
            alertMsg("查询不到数据，请重新查询！");
        }
    };
    $.ajax(options);
});

$("#fcys_dialog").dialog({
    autoOpen: false,
    height: 500,
    width: 600,
    modal: true,
    buttons: {
        "提交" : function(){
            if(checkForm("#fcys_form")){
                var options = new AjaxOptions("#fcys_form");
                options.put("service_code", "P12017");
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
$("#fcys_dialog #fcfcrxm").change(function(){
    $("#fcys_dialog #fcfcr").val("");
});