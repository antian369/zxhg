/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

var now = new Date();

$("#nf").val(now.getFullYear());   //年
$("#yf").val(now.getMonth() + 1);   //月

$("#nf,#yf").change(function(){
    $("#jhyf").val($("#nf").val() + "-" + $("#yf").val());
    $("#bz,#yzb").val("");
    $("#rzb").html("");
    var o = new AjaxOptions("#data_form");
    o.isAlert = false;
    o.put("service_code", "S13009");
    o.sus = function(){
        if($("#yzb").val() && $("#yzb").val() != "0"){
            $("#yzb").attr("disabled", "disabled");
        }else{
            $("#yzb").removeAttr("disabled");
        }
    }
    $.ajax(o);
});
$("#nf").trigger("change");

$("#save").click(function(){
    if(checkForm("#data_form")){
        $("#jhyf").val($("#nf").val() + "-" + $("#yf").val());
        var o = new AjaxOptions();
        o.data = structFormData("#data_form");
        o.put("service_code", "P13002");
        $.ajax(o);
    }
});
$("#data_form").submit(function(){
    return false;
});

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

var now = new Date();

$("#nf").val(now.getFullYear());   //年
$("#yf").val(now.getMonth() + 1);   //月

$("#nf,#yf").change(function(){
    $("#jhyf").val($("#nf").val() + "-" + $("#yf").val());
    $("#bz,#yzb").val("");
    $("#rzb").html("");
    var o = new AjaxOptions("#data_form");
    o.isAlert = false;
    o.put("service_code", "S13009");
    $.ajax(o);
});
$("#nf").trigger("change");

$("#save").click(function(){
    if(checkForm("#data_form")){
        $("#jhyf").val($("#nf").val() + "-" + $("#yf").val());
        var o = new AjaxOptions("#data_form");
        o.put("service_code", "P13002");
        $.ajax(o);
    }
});
$("#data_form").submit(function(){
    return false;
});

