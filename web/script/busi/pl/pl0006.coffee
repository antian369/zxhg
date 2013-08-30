#进入页面时调用查询服务
o = new AjaxOptions()
o.put 'service_code','S11011'
o.isPadBack = false
o.sus = (data) ->
  data.all = data.users.length
  padBackData data
  padBackTable data.users,'#users',false
$.ajax o
