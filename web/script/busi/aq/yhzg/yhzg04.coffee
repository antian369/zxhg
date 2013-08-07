#进入页面时调用查询服务
o = new AjaxOptions()
o.put 'service_code','S12023'
o.put 'zt','2'      #状态为 申请验收 的隐患
o.isPadBack = false
o.sus = (data) ->
  if data.yhs? and data.yhs.length > 0
    row.opt = '<a href="#this">验收</a>' for row in data.yhs
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
      for row in data.zgs
        row.opt = '<a href="#this">查看</a>'
        pzsx = row.pzsx if row.yszt is '2'
      padBackTable data.zgs,'#yh_dialog #zgs',false
      $('#yh_dialog').dialog 'open'
      $('#ysbz_dialog').attr 'table_row',r
      $('#ysbz_dialog #pzsx_d').val(pzsx.substr 0,10)
      $('#ysbz_dialog #pzsx_h').val(pzsx.substr 11,2)
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
    '验收': ->
      $('#ysbz_dialog').dialog 'open'
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

$('#ysbz_dialog').dialog
  autoOpen: false
  height: 290
  width: 500
  modal: true
  buttons:
    '提交': ->
      a = notNull '验收备注','#ysbz_form #ysbz'
      if a isnt 1
        alertMsg a
      else
        o = new AjaxOptions '#ysbz_form'
        if $('#ystg_n').attr('checked')?
        #如果验收不通过，对批准时间做检验
          if checkForm "#ysbz_form"
            ph = $("#pzsx_h").val()
            if ph < 0 or ph >= 24
              alertMsg "批准整改完成时间(时)，必须为0~23"
              return
            ph = '0' + ph if ph.length is 1
            ph = " #{ph}:00:00"
            o.put 'pzsx',($('#ysbz_form #pzsx_d').val() + ph)
          else
            return
        #如果验收通过，不再对批准时限做校验
        o.put 'service_code','P12022'
        o.put 'yh_id',$('#yh_dialog #yh_id').val()
        o.sus = ->
          row = $('#ysbz_dialog').attr 'table_row'
          $('#ysbz_dialog #ysbz').val ''
          $('#ysbz_dialog').dialog 'close'
          $('#yh_dialog').dialog 'close'
          $("#yhs tbody tr:eq(#{row})").fadeOut 800
        $.ajax o
    '关闭': ->
      $(this).dialog 'close'

$(':radio[name=ystg]').click ->
  if $(this).val() is '1'
    $('#pzsx_tr').fadeIn 300
  else
    $('#pzsx_tr').fadeOut 300

$('#pzsx_tr').hide()