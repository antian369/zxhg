
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
     '关闭':->
       $(this).dialog 'close'
