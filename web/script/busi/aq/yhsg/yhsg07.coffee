
tableOrder = $.extend(true, {}, tableTheme, {aaSorting: [[5, 'desc']]})
$("#yhsgs").dataTable tableOrder

$('.info').click ->
  ind = $(this).attr 'ind'
  rd = outJson.result[ind]
  padBackData rd,'#yhsg_dialog'
  $('#yhsg_dialog').attr({yh_id:rd.yh_id,r:ind}).dialog('open')


$("#yhsg_dialog").dialog
  autoOpen: false
  height: 550
  width: 800
  modal: true
  buttons:
    '删除':->
      if confirm '确定要删除吗？'
        o = new AjaxOptions()
        o.put 'service_code','P12027'
        o.put 'yh_id',$('#yhsg_dialog').attr('yh_id')
        o.isAlert = false
        o.sus = ->
          row = $('#yhsg_dialog').attr 'r'
          $('#yhsg_dialog').dialog 'close'
          $("tr[ind=#{row}]").fadeOut 800
        $.ajax o
    '关闭':->
      $(this).dialog 'close'
