/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
if(!outJson['result']){
    $("#yhxx_table").hide();
}else{
    $("#yhxx_table").dataTable(tableTheme);
}

$(".info").click(function(){
    var data = outJson['result'][$(this).attr("ind")];
    padBackData(data, "#yhxx_dialog");
    var options = new AjaxOptions();
    switch (data.ly) {
        case "1":
            options.put("service_code", "S12011");
            break;
        case "2":
            options.put("service_code", "S12005");
            break;
        case "3":
            options.put("service_code", "S12013");
            break;
    }
    options.put("yh_id", data.yh_id);
    options.isAlert = false;
    options.padBackElement = "#yhxx_dialog";
    options.sus = function(d){
        $("#zgxx").empty();
        $(".yh").hide();
        switch (data.ly) {
            case "1":
                $("#yhsg_table").show();
                break;
            case "2":
                $("#zsxb_table").show();
                break;
            case "3":
                $("#yhjc_table").show();
                break;
        }
        for(var ind in d["zgjl"]){
            $("<a/>").attr({
                "href" : "#this",
                "class" : "zgjl_info"
            })
            .css("color", "blue")
            .data("json", d["zgjl"][ind]).html("第 " + (Number(ind) + 1) + " 次整改&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;")
            .appendTo("#zgxx");
        }
        $("#yhxx_dialog").dialog("open");
    };
    $.ajax(options);
});

$("#yhxx_dialog").dialog({
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