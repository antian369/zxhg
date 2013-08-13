#进入页面时调用查询服务
o = new AjaxOptions()
o.put 'service_code','S12028'
o.put 'yhlb','3'      #隐患类别为 综合安全类
o.isPadBack = false
o.sus = (data) ->
  if data.yhsgs? and data.yhsgs.length > 0
    row.opt = '<a href="#this">认定</a>' for row in data.yhsgs
    padBackTable data.yhsgs,'#yhsgs',false
  else
    alertMsg '没有需要处理的隐患收购'
$.ajax o

$('#yhsgs').bind 'clicktd', (e,r,c,rd,td) ->
  if c is '6'
    padBackData rd,'#yhsg_dialog'
    $('#yhrd_form #yh_id').val rd.yh_id
    $('#yhsg_dialog').attr({yh_id:rd.yh_id,r:r}).dialog('open')


$("#yhsg_dialog").dialog
  autoOpen: false
  height: 550
  width: 800
  modal: true
  buttons:
    '认定': ->
      $("#yhrd_dialog").dialog "open"
     '关闭':->
       $(this).dialog 'close'

$('#yhrd_dialog').dialog
  autoOpen: false
  height: 320
  width: 650
  modal: true
  buttons:
    '提交': ->
      if checkForm '#yhrd_form'
        o = new AjaxOptions '#yhrd_form'
        o.put 'service_code','P12026'
        o.sus = ->
          $('#yhrd_dialog').dialog 'close'
          $('#yhsg_dialog').dialog 'close'
          row = $('#yhsg_dialog').attr('r')
          $("#yhsgs tbody tr:eq(#{row})").fadeOut 800
        $.ajax o
    '关闭': ->
      $(this).dialog 'close'
