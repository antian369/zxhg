/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

var tableOrder = $.extend(true, {}, tableTheme, {aaSorting: [[0, 'desc']]});
$("#sw_table").dataTable(tableOrder);

/**
 * 详细信息按键及弹窗
 */
$(".del").click(function() {
    if (confirm('确定要删除吗?')) {
        var ind = $(this).attr("ind");
        var options = new AjaxOptions();
        options.put("sw_id", $(this).attr('id'));
        options.put('service_code', 'P12018');
        options.sus = function() {
            $('#sw_table tbody tr').has('a#' + this.get('sw_id')).fadeOut(800);
        };
        $.ajax(options);
    }
});
$("#cksw_dialog").dialog({
    autoOpen: false,
    height: 500,
    width: 600,
    modal: true,
    buttons: {
        "关闭": function() {
            $(this).dialog("close");
        }
    }
});
