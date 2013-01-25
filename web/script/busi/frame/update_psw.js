/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
$("#sub").click(function(){
    if(checkForm("#data_form")){
        if($("#new_password").val() != $("#again_password").val()){
            alertMsg("两次输入的密码不一致");
            return;
        }
        var options = new AjaxOptions("#data_form");
        options.put("service_code", "P11001");
        options.sus = function(){
            $(":password").val("");
        };
        $.ajax(options);
    }
});
