#dep 在jsp 文件中定义，为当前登录人的单位
$('#fxdw').val dep
$('#yhdw').val dep

$('#sub').click ->
  if checkForm '#data_form'
    fh = $('#fxsj_h').val()
    if fh > 23 or fh < 0
      alertMsg '发现时间（时）必须为0～23'
      return
    fh = '0' + fh if fh.length is 1
    fh = " #{fh}:00:00"
    o = new AjaxOptions '#data_form'
    o.put 'service_code','P12014'
    o.put 'fxsj',($('#fxsj').val() + fh)
    o.sus = ->
      reloadPage()
    $.ajax o
