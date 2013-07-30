/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

var tableOrder = $.extend(true, {}, tableTheme, {aaSorting: [[2, 'desc']]});
$("#sg_table").dataTable(tableOrder);
var ind;        //查看的第几行
/**
 * 详细信息按键及弹窗
 */
$(".info").click(function() {
    ind = $(this).attr("ind");
    var json = outJson["result"][$(this).attr("ind")];
    padBackData(json, "#cksg_dialog");
    $("#cksg_dialog").dialog("open");
    $('#cksg_dialog #sg_id').val($(this).attr('id'));
});
$("#cksg_dialog").dialog({
    autoOpen: false,
    height: 500,
    width: 600,
    modal: true,
    buttons: {
        "删除": function() {
            if (confirm('确定要删除吗?')) {
                var options = new AjaxOptions();
                options.put("sg_id", $('#cksg_dialog #sg_id').val());
                options.put('service_code', 'P12019');
                options.sus = function() {
                    $('#sg_table tbody tr').has('a#' + this.get('sg_id')).fadeOut(800);
                    $("#cksg_dialog").dialog("close");
                };
                $.ajax(options);
            }
        },
        "关闭": function() {
            $(this).dialog("close");
        }
    }
});
