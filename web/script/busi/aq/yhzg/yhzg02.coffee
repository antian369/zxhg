#进入页面时调用查询服务
o = new AjaxOptions()
o.put 'service_code','S12021'
o.put 'yhdw',dep    #在jsp中定义，为当前用户的部门
o.put 'zt','1'      #状态为正在整改的隐患
o.isPadBack = false
o.sus = (data) ->
  if data.yhs? and data.yhs.length > 0
    row.opt = '<a href="#this">申请验收</a>' for row in data.yhs
    padBackTable data.yhs,'#yhs',false
  else
    alertMsg '没有需要处理的隐患'
$.ajax o

$('#yhs').bind 'clicktd', (e,r,c,rd,td) ->
  if c is '6'
    o = new AjaxOptions()
    o.put 'service_code','S12022'
    o.put 'yh_id',rd.yh_id
    o.isAlert = false
    o.isPadBack = false
    o.sus = (data) ->
      padBackData rd,'#yh_dialog'
      row.opt = '<a href="#this">查看</a>' for row in data.zgs
      padBackTable data.zgs,'#yh_dialog #zgs',false
      $('#yh_dialog').dialog 'open'
      $('#zgbz_dialog').attr 'table_row',r
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
    '申请验收': ->
      $('#zgbz_dialog').dialog 'open'
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

$('#zgbz_dialog').dialog
  autoOpen: false
  height: 240
  width: 500
  modal: true
  buttons:
    '提交': ->
      a = notNull '整改备注','#zgbz_dialog #zgbz'
      if a isnt 1
        alertMsg a
      else
        o = new AjaxOptions()
        o.put 'service_code','P12020'
        o.put 'yh_id',$('#yh_dialog #yh_id').val()
        o.put 'zgbz',$('#zgbz_dialog #zgbz').val()
        o.sus = ->
          row = $('#zgbz_dialog').attr 'table_row'
          $('#zgbz_dialog #zgbz').val ''
          $('#zgbz_dialog').dialog 'close'
          $('#yh_dialog').dialog 'close'
          $("#yhs tbody tr:eq(#{row})").fadeOut 800
        $.ajax o
    '关闭': ->
      $(this).dialog 'close'

