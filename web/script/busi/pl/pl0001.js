/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

var oTable = $('#dep_list').dataTable(tableTheme);

$("#sub").click(function(){
    if(checkForm("#data_form")){
        var options = new AjaxOptions("#data_form");
        options.put("service_code", "P11002");
        options.sus = function(){
            setTimeout(function(){
                location.reload();
            }, 800);
        };
        $.ajax(options);
    }
});

$(".update_dep").click(function(){
    var options = new AjaxOptions();
    options.put("service_code", "S11003");
    options.put("dep_id", $(this).attr("dep_id"));
    options.padBackElement = "#update_dep_form";
    options.isAlert = false;
    options.sus = function(){
        $("#update_dep_form #dep_id").val(this.get("dep_id"));
        $("#update_dep_dialog").dialog("open");
    };
    $.ajax(options);
});

//创建用户弹出框
$("#update_dep_dialog").dialog({
    autoOpen: false,
    height: 300,
    width: 600,
    modal: true,
    buttons: {
        "保存": function() {
            if(checkForm("#update_dep_form")){
                var options = new AjaxOptions("#update_dep_form");
                options.put("service_code", "P11003");
                options.sus = function(){
                    setTimeout(function(){
                        location.reload();
                    }, 800);
                };
                $.ajax(options);
            }
        },
        "取消": function() {
            $( this ).dialog( "close" );
        }
    }
});