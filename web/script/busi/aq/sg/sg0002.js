/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

$("#sg_table").dataTable(tableTheme);
var ind;        //查看的第几行
/**
 * 详细信息按键及弹窗
 */
$(".info").click(function(){
    ind = $(this).attr("ind");
    var json = outJson["result"][$(this).attr("ind")];
    padBackData(json, "#cksg_dialog");
    padBackData(json, "#tbsg_dialog");
    $("#cksg_dialog").dialog("open");
});
$("#cksg_dialog").dialog({
    autoOpen: false,
    height: 500,
    width: 600,
    modal: true,
    buttons: {
        "通报": function() {
            var json = outJson["result"][ind];
            $( this ).dialog( "close" );
            $("#tbsg_dialog").dialog("open");
        },
        "取消": function() {
            $( this ).dialog( "close" );
        }
    }
});

$("#tbsg_dialog").dialog({
    autoOpen: false,
    height: 500,
    width: 600,
    modal: true,
    buttons: {
        "提交": function() {
            if(checkForm("#tbsg_form")){
                var options = new AjaxOptions("#tbsg_form");
                options.put("service_code", "P12006");
                options.sus = function(){
                    setTimeout(function(){
                        $("#page_form").submit();
                    }, 800);
                };
                $.ajax(options);
            }
        },
        "取消": function() {
            $( this ).dialog( "close" );
        }
    }
});