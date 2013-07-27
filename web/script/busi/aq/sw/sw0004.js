/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

$("#sw_table").dataTable(tableTheme);

/**
 * 详细信息按键及弹窗
 */
$(".info").click(function() {
    var json = outJson["result"][$(this).attr("ind")];
    padBackData(json, "#cksw_dialog");
    var swsj = json.swsj;
    json.swsj = swsj.substr(0, 10);
    json.swsj_h = swsj.substr(11, 2);
    json.swsj_m = swsj.substr(14, 2);
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
            $(this).dialog("close");
            $("#xgsw_dialog").dialog("open");
        },
        "取消": function() {
            $(this).dialog("close");
        }
    }
});

/**
 * 当发现人改变时，重置相关项
 */
$("#xgsw_form #fxrxm").change(function() {
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
            if (!checkForm("#xgsw_form")) {
                return;
            }
            var swsj = $("#xgsw_form #swsj").val();
            var swshH = $("#xgsw_form #swsj_h").val();
            if (Number(swshH) > 23 || Number(swshH) < 0) {
                alertMsg('三违时间（小时）必须为0~23');
                return;
            }
            var swshM = $('#xgsw_form #swsj_m').val();
            if (Number(swshM) > 59 || Number(swshM) < 0) {
                alertMsg('三违时间（分钟）必须为0~59');
                return;
            }
            swshH = swshH.length == 1 ? '0' + swshH : swshH;
            swshM = swshM.length == 1 ? '0' + swshM : swshM;
            swsj = swsj + ' ' + swshH + ':' + swshM + ':00';
            var options = new AjaxOptions("#xgsw_form");
            options.put("service_code", "P12002");
            options.put('swsj', swsj);
            options.sus = function() {
                setTimeout(function() {
                    $("#page_form").submit();
                }, 800);
            };
            $.ajax(options);
        },
        "取消": function() {
            $(this).dialog("close");
        }
    }
});