/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
$("#scrq_input").change(function(){
    var o = new AjaxOptions();
    o.put("service_code", "S13014");
    o.put("scrq", $(this).val());
    o.sus = function(data){
        $("td[id]").html("");
        var table = data.rbnr;
        table = eval("(" + table + ")");
        padBackData(table);
    };
    $.ajax(o);
}).trigger("change");

