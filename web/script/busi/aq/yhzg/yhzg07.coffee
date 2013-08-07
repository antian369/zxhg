
tableOrder = $.extend(true, {}, tableTheme, {aaSorting: [[5, 'desc']]})
$("#yh_table").dataTable tableOrder

$('#reset').click ->
  $('#search_form :text').val ''
  $('#search_form select').val ''

$('.info').click ->
  ind = $(this).attr 'ind'
  rd = outJson.result[ind]
  o = new AjaxOptions()
  o.put 'service_code','S12022'
  o.put 'yh_id',rd.yh_id
  o.isAlert = false
  o.isPadBack = false
  o.sus = (data) ->
    padBackData rd,'#yh_dialog'
    row.opt = '<a href="#this">查看</a>' for row in data.zgs
    $('#yh_dialog').dialog 'open'
    padBackTable data.zgs,'#yh_dialog #zgs',false
  $.ajax o

$('#zgs').bind 'clicktd', (e,r,c,rd,td) ->
  if c is '5'
    padBackData rd, '#zg_dialog'
    $('#zg_dialog').dialog 'open'

$('#yh_dialog').dialog
  autoOpen: false
  height: 550
  width: 800
  modal: true
  buttons:
    '关闭': ->
      $(this).dialog 'close'

$('#zg_dialog').dialog
  autoOpen: false
  height: 400
  width: 600
  modal: true
  buttons:
    '关闭': ->
      $(this).dialog 'close'