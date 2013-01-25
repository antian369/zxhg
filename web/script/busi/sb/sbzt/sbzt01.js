/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

$("#sbfc").buttonset();
$("#content").hide();
var lastJson;
$(":input[name='sbfc']").click(function(){
    var sbfc = $(this).attr("sbfc");        //设备分厂
    $("#content_right").hide();
    var options = new AjaxOptions();
    options.put("service_code", "S14002");
    options.isPadBack = false;
    options.sus = function(data){
        lastJson = data["result"];
        $("#content").fadeOut();
        $("#content_left").empty();
        for(var i=0;i<lastJson.length;i++){
            var p = $("<p/>");
            var input = $("<input/>").attr({
                'class':"sb",
                "type":"radio",
                "name":"sb",
                "id":"sb" + i,
                "xh":i
            });
            var label = $("<label/>").attr("for", "sb" + i).html(lastJson[i]['sbmc']);
            p.append(input).append(label);
            $("#content_left").append(p);
        }
        $("#content_left").buttonset();
        $("#content").fadeIn(800);
    };
    $.ajax(options);
});
$(".sb").live("click", function(){
    var data = lastJson[$(this).attr("xh")];
    $("#content_right").fadeOut();
    padBackData(data, "#content_right");
    $("#content_right").fadeIn(600);
});