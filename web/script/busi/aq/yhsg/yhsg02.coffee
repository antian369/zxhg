#进入页面时调用查询服务
o = new AjaxOptions()
o.put 'service_code','S12027'
o.put 'yhdw',dep    #在jsp中定义，为当前用户的部门
o.put 'zt','1'      #状态为未核实的收购
o.isPadBack = false
o.sus = (data) ->
  if data.yhsgs? and data.yhsgs.length > 0
    row.opt = '<a href="#this">核实</a>' for row in data.yhsgs
    padBackTable data.yhsgs,'#yhsgs',false
  else
    alertMsg '没有需要处理的隐患收购'
$.ajax o

$('#yhsgs').bind 'clicktd', (e,r,c,rd,td) ->
  if c is '4'
    padBackData rd,'#yhsg_dialog'
    $('#yhhs_form #yh_id').val rd.yh_id
    $('#yhsg_dialog').attr({yh_id:rd.yh_id,r:r}).dialog('open')


$("#yhsg_dialog").dialog
  autoOpen: false
  height: 550
  width: 800
  modal: true
  buttons:
    '核实': ->
      $("#yhhs_dialog").dialog "open"
    '不是隐患': ->
      if confirm '是否确认?'
        o = new AjaxOptions()
        o.put 'service_code','P12025'
        o.put 'yh_id',($("#yhsg_dialog").attr 'yh_id')
        o.put 'zgqk','不是隐患'
        o.sus = ->
          $('#yhsg_dialog').dialog 'close'
          row = $('#yhsg_dialog').attr('r')
          $("#yhsgs tbody tr:eq(#{row})").fadeOut 800
        $.ajax o
     '关闭':->
       $(this).dialog 'close'

$('#yhhs_dialog').dialog
  autoOpen: false
  height: 320
  width: 650
  modal: true
  buttons:
    '提交': ->
      if checkForm '#yhhs_form'
        zh = $('#yhhs_form #zgsj_h').val()
        if zh > 23 or zh < 0
          alertMsg '整改时间（时）必须为0～23'
          return
        zh = '0' + zh if zh.length is 1
        zh = " #{zh}:00:00"
        o = new AjaxOptions '#yhhs_form'
        o.put 'service_code','P12015'
        o.put 'zgsj',($('#yhhs_dialog #zgsj_d').val() + zh)
        o.sus = ->
          $('#yhhs_dialog').dialog 'close'
          $('#yhsg_dialog').dialog 'close'
          row = $('#yhsg_dialog').attr('r')
          $("#yhsgs tbody tr:eq(#{row})").fadeOut 800
        $.ajax o
    '关闭': ->
      $(this).dialog 'close'
