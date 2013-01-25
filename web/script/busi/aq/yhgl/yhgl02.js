/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
if(!outJson['result']){
    $("#zsxb_table").hide();
}else{
    $("#zsxb_table").dataTable(tableTheme);
}

$(".info").click(function(){
    var data = outJson['result'][$(this).attr("ind")];
    padBackData(data, "#zsxb_dialog");
    $("#zsxb_dialog").dialog("open");
    var options = new AjaxOptions();
    options.put("service_code", "S12005");
    options.put("yh_id", data.yh_id);
    options.isAlert = false;
    options.isPadBack = false;
    options.sus = function(d){
        $("#zgxx").empty();
        for(var ind in d["zgjl"]){
            $("<a/>").attr({
                "href" : "#this",
                "class" : "zgjl_info"
            })
            .css("color", "blue")
            .data("json", d["zgjl"][ind]).html("第 " + (Number(ind) + 1) + " 次整改&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;")
            .appendTo("#zgxx");
        }
    };
    $.ajax(options);
});

$("#zsxb_dialog").dialog({
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

$(".zgjl_info").live("click", function(){
    var data = $(this).data("json");
    padBackData(data, "#zgjl_dialog");
    $("#zgjl_dialog").dialog("open");
});
$("#zgjl_dialog").dialog({
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