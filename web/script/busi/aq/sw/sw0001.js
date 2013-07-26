/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * 当发现人改变时，重置相关项
 */
$("#data_form #fxrxm").change(function() {
    $("#data_form #fxrbm").val("");         //发现人部门
    $("#data_form #fxr").val("");       //发现人用户名
});
/**
 * 提交
 */
$("#sub").click(function() {
    if (!checkForm("#data_form")) {
        return;
    }
    var swsj = $("#swsj").val();
    var swshH = $("#swsj_h").val();
    if (Number(swshH) > 23 || Number(swshH) < 0) {
        alertMsg('三违时间（小时）必须为0~23');
        return;
    }
    var swshM = $('#swsj_m').val();
    if (Number(swshM) > 59 || Number(swshM) < 0) {
        alertMsg('三违时间（分钟）必须为0~59');
        return;
    }
    swshH = swshH.length == 1 ? '0' + swshH : swshH;
    swshM = swshM.length == 1 ? '0' + swshM : swshM;
    swsj = swsj + ' ' + swshH + ':' + swshM + ':00';
    var options = new AjaxOptions("#data_form");
    options.put("service_code", "P12001");
    options.put('swsj', swsj);
    options.sus = function() {
        setTimeout(function() {
            reloadPage();
        }, 800);
    };
    $.ajax(options);
});