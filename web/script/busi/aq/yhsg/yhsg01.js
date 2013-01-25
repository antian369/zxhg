/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
$("#fxdw").val(dep);
$("#yhdw").val(dep);
console.log(dep);
$("#fxrxm").change(function(){
    $("#fxr").val("");
});
$("#sub").click(function(){
    if(checkForm("#data_form")){
        var options = new AjaxOptions("#data_form");
        options.put("service_code", "P12014");
        options.sus = function(){
            reloadPage();
        };
        $.ajax(options);
    }
});
