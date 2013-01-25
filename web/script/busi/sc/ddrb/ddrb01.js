/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
$("#scrq").val(getNowDate());
var tableJson = "";
var scrq = "";
$("#create").click(function(){
    if(checkForm("#search_form")){
        tableJson = "";
        scrq = "";
        $("td[id]").html("");
        var o = new AjaxOptions("#search_form");
        o.put("service_code", "P13011");
        o.isPadBack = false;
        o.sus = function(data){
            padBackData(data.table);
            tableJson = encodeURI(data.table_json);
            scrq = this.data.getParam("scrq");
            $("#scrq_span").html(scrq);
        };
        $.ajax(o);
    }
});

$("#auto_create").click(function(){
    tableJson = "";
    scrq = "";
    $("td[id]").html("");
    var o = new AjaxOptions();
    o.put("service_code", "P13010");
    o.isPadBack = false;
    o.sus = function(data){
        padBackData(data.table);
        tableJson = encodeURI(data.table_json);
        scrq = data.scrq;
        $("#scrq_span").html(data.scrq);
    };
    $.ajax(o);
});

$("#save").click(function(){
    if(!(tableJson && scrq)){
        alertMsg("请先生成某一天的调度日报！");
        return;
    }
    if(confirm("是否要保存 " + scrq + " 的调度日报？")){
        var o = new AjaxOptions();
        o.put("service_code", "P13012");
        o.put("scrq", scrq);
        o.put("rbnr", tableJson);
        $.ajax(o);
    }
});
