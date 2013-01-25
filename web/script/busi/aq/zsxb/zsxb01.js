/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
$("#zw").val(zw);
$("#tbrxm").change(function(){
    $("#tbr").val("");
});
$("#zgrxm").change(function(){
    $("#zgr").val("");
});
$("#sub").click(function(){
    if(checkForm("#data_form")){
        var h = $("#sqsx_h").val();
        if(h < 0 || h >= 24){
            alertMsg("整改时限(小时)，必须为0~23");
            return;
        }
        var options = new AjaxOptions("#data_form");
        options.put("service_code", "P12008");
        options.sus = function(){
            location.reload();
        };
        $.ajax(options);
    }
});
