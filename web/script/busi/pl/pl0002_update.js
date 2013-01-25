/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

$("#sub").click(function(){
    if(checkForm("#data_form")){
        var options = new AjaxOptions("#data_form");
        options.put("service_code", "P11005");
        options.sus = function(){
            $(":text,#sub,:reset").attr("disabled", "disabled");
        };
        $.ajax(options);
    }
});

