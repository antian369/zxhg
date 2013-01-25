/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
var options = new AjaxOptions();
options.put("service_code", "S13005");
options.isAlert = false;
options.isPadBack = false;
options.sus = function(data){
    $("#report").attr("src", data["url"]);
};
//$.ajax(options);