#进入页面时调用查询服务
d = new Date()
$('#year').val d.getFullYear()
m = d.getMonth() + 1
m = if m > 9 then m else "0#{m}"
$('#month').val m

$('#search').click ->
  y = $('#year').val()
  m = $('#month').val()
  o = new AjaxOptions()
  o.put 'service_code','S11012'
  o.put 'begin',"#{y}-#{m}-01"
  o.sus = (data) ->
    row.opt = '<a href="#this">查看详细</a>' for row in data.users
    padBackTable data.users,'#users',false
  $.ajax o
.trigger 'click'

$('#users').bind 'clicktd', (e,r,c,rd,td) ->
  if c is '5'
    o = new AjaxOptions()
    o.put 'service_code','S11013'
    o.put 'username',rd.username
    o.isAlert = false
    o.sus = (data) ->
      padBackTable data.logins,'#login_dialog #logins',false
      $('#login_dialog').dialog 'open'
    $.ajax o


$("#login_dialog").dialog
  autoOpen: false
  height: 550
  width: 850
  modal: true
  buttons:
     '关闭':->
       $(this).dialog 'close'
