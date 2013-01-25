/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
$("#auth").hide();

$("#sub").click(function(){
    if(checkForm("#data_form")){
        var options = new AjaxOptions("#data_form");
        options.put("service_code", "P11004");
        options.sus = function(data){
            $(":text,#sub,:reset").attr("disabled", "disabled");
            $("#auth").attr("role_id", data["role_id"]).fadeIn(800);
        };
        $.ajax(options);
    }
});

$("#back").click(function(){
    location.href="pl0002.do";
});

$("#auth").click(function(){
    location.href="pl0004.do?role_id=" + $(this).attr("role_id");
});
