// Generated by CoffeeScript 1.6.3
(function() {
  var o;

  o = new AjaxOptions();

  o.put('service_code', 'S11011');

  o.isPadBack = false;

  o.sus = function(data) {
    data.all = data.users.length;
    padBackData(data);
    return padBackTable(data.users, '#users', false);
  };

  $.ajax(o);

}).call(this);
