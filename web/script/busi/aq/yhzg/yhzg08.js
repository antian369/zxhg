// Generated by CoffeeScript 1.6.3
(function() {
  var tableOrder;

  tableOrder = $.extend(true, {}, tableTheme, {
    aaSorting: [[5, 'desc']]
  });

  $("#yh_table").dataTable(tableOrder);

  $('#reset').click(function() {
    $('#search_form :text').val('');
    return $('#search_form select').val('');
  });

  $('.info').click(function() {
    var ind, o, rd;
    ind = $(this).attr('ind');
    rd = outJson.result[ind];
    o = new AjaxOptions();
    o.put('service_code', 'S12022');
    o.put('yh_id', rd.yh_id);
    o.isAlert = false;
    o.isPadBack = false;
    o.sus = function(data) {
      var row, _i, _len, _ref;
      padBackData(rd, '#yh_dialog');
      _ref = data.zgs;
      for (_i = 0, _len = _ref.length; _i < _len; _i++) {
        row = _ref[_i];
        row.opt = '<a href="#this">查看</a>';
      }
      $('#yh_dialog').dialog('open');
      $('#yh_dialog').attr('yh_id', rd.yh_id);
      padBackTable(data.zgs, '#yh_dialog #zgs', false);
      return $('#yh_dialog').attr('table_row', ind);
    };
    return $.ajax(o);
  });

  $('#zgs').bind('clicktd', function(e, r, c, rd, td) {
    if (c === '5') {
      padBackData(rd, '#zg_dialog');
      return $('#zg_dialog').dialog('open');
    }
  });

  $('#yh_dialog').dialog({
    autoOpen: false,
    height: 550,
    width: 800,
    modal: true,
    buttons: {
      '删除': function() {
        var o;
        if (!confirm('确定删除？')) {
          return;
        }
        o = new AjaxOptions();
        o.put('service_code', 'P12024');
        o.put('yh_id', $('#yh_dialog').attr('yh_id'));
        o.sus = function() {
          var row;
          $('#yh_dialog').dialog('close');
          row = $('#yh_dialog').attr('table_row');
          return $("#yh_table tbody tr:eq(" + row + ")").fadeOut(800);
        };
        return $.ajax(o);
      },
      '关闭': function() {
        return $(this).dialog('close');
      }
    }
  });

  $('#zg_dialog').dialog({
    autoOpen: false,
    height: 400,
    width: 600,
    modal: true,
    buttons: {
      '关闭': function() {
        return $(this).dialog('close');
      }
    }
  });

}).call(this);
