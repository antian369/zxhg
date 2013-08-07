
$('#sub').click ->
  if checkForm "#data_form"
    ph = $("#pzsx_h").val()
    if ph < 0 or ph >= 24
      alertMsg "批准整改完成时间(时)，必须为0~23"
      return
    ph = '0' + ph if ph.length is 1
    ph = " #{ph}:00:00"

    jh = $("#jcsj_h").val()
    if jh < 0 or jh >= 24
      alertMsg "检查时间(时)，必须为0~23"
      return
    jh = '0' + jh if jh.length is 1
    jh = " #{jh}:00:00"

    options = new AjaxOptions "#data_form"
    options.put 'service_code','P12016'
    options.put 'jcsj',($('#jcsj').val() + jh)
    options.put 'pzsx',($('#pzsx').val() + ph)
    options.sus = ->
      reloadPage()
    $.ajax options