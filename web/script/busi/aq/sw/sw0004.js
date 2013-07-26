/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

$("#sw_table").dataTable(tableTheme);

/**
 * 当发现人改变时，重置相关项
 */
$("#swdj_form #fxrxm").change(function(){
    $("#swdj_form #fxrbm").val("");         //发现人部门
    $("#swdj_form #fxr").val("");       //发现人用户名
});
/**
 * 三违登记按钮及弹窗
 */
$("#swdj").click(function(){
    $("#swdj_dialog").dialog("open");
});
$("#swdj_dialog").dialog({
    autoOpen: false,
    height: 500,
    width: 600,
    modal: true,
    buttons: {
        "提交": function() {
            if(!checkForm("#swdj_form")){
                return;
            }
            var options = new AjaxOptions("#swdj_form");
            options.put("service_code", "P12001");
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
/**
 * 详细信息按键及弹窗
 */
$(".info").click(function(){
    var json = outJson["result"][$(this).attr("ind")];
    padBackData(json, "#cksw_dialog");
    padBackData(json, "#xgsw_form");
    $("#cksw_dialog").dialog("open");
});
$("#cksw_dialog").dialog({
    autoOpen: false,
    height: 500,
    width: 600,
    modal: true,
    buttons: {
        "修改": function() {
            $( this ).dialog( "close" );
            $("#xgsw_dialog").dialog("open");
        },
        "取消": function() {
            $( this ).dialog( "close" );
        }
    }
});

/**
 * 当发现人改变时，重置相关项
 */
$("#xgsw_form #fxrxm").change(function(){
    $("#xgsw_form #fxrbm").val("");         //发现人部门
    $("#xgsw_form #fxr").val("");       //发现人用户名
});
$("#xgsw_dialog").dialog({
    autoOpen: false,
    height: 500,
    width: 600,
    modal: true,
    buttons: {
        "保存": function() {
            if(!checkForm("#xgsw_form")){
                return;
            }
            var options = new AjaxOptions("#xgsw_form");
            options.put("service_code", "P12002");
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