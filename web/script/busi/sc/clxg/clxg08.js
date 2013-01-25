/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
var today = new Date();
today.setDate(today.getDate() - 1);
$("#scrq").val(today.format("yyyy-MM-dd"));       //今天日期

$("#scrq").change(function(){
    var o = new AjaxOptions("#data_form");
    $(":text:not(#scrq)").val(0);
    $("#bz").val("");
    o.put("service_code", "S13008");
    o.isAlert = false;
    $.ajax(o);
}).trigger("change");

$("#save").click(function(){
    if(checkForm("#data_form")){
        var o = new AjaxOptions("#data_form");
        o.put("service_code", "P13001");
        $.ajax(o);
    }
});

