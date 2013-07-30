/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

var tableOrder = $.extend(true, {}, tableTheme, {aaSorting: [[0, 'desc']]});
$("#sw_table").dataTable(tableOrder);
var ind;        //查看的第几行

/**
 * 详细信息按键及弹窗
 */
$(".info").click(function(){
    ind = $(this).attr("ind");
    var json = outJson["result"][$(this).attr("ind")];
    padBackData(json, "#cksw_dialog");
    padBackData(json, "#rqsw_form");
    $("#cksw_dialog").dialog("open");
});
$("#cksw_dialog").dialog({
    autoOpen: false,
    height: 500,
    width: 600,
    modal: true,
    buttons: {
        "确认": function() {
            var json = outJson["result"][ind];
            if(json.zt != '02'){
                alertMsg("只能对 '未确认' 的三违进行确认！");
                return;
            }
            $( this ).dialog( "close" );
            $("#rqsw_dialog").dialog("open");
        },
        "取消": function() {
            $( this ).dialog( "close" );
        }
    }
});

$("#rqsw_dialog").dialog({
    autoOpen: false,
    height: 150,
    width: 600,
    modal: true,
    buttons: {
        "提交": function() {
            var options = new AjaxOptions("#rqsw_form");
            options.put("service_code", "P12004");
            options.sus = function(){
                setTimeout(function(){
                    $("#page_form").submit();
                }, 800);
            };
            $.ajax(options);
        },
        "取消": function() {
            $( this ).dialog( "close" );
        }
    }
});