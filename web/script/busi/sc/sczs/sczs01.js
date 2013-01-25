/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

$("#scrq").val(getNowDate());       //今天日期

$("#scrq").change(function(){
    var o = new AjaxOptions("#data_form");
    $("textarea").val("");
    o.put("service_code", "S13012");
    o.isAlert = false;
    $.ajax(o);
}).trigger("change");

$("#save").click(function(){
    if(checkForm("#data_form")){
        var o = new AjaxOptions("#data_form");
        o.put("service_code", "P13007");
        $.ajax(o);
    }
});