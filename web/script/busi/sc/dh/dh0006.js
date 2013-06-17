/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

$("#graph").hide();

var nowDate = getNowDate();
nowDate = nowDate.split("-");
$("#year").val(nowDate[0]);
$("#month").val(nowDate[1]);

var m;      //月
$("#search").click(function() {
    var year = $("#year").val();
    var month = $("#month").val();
    var now = getNowDate();
    m = year + "-" + month;
    if (now < (m + "-01")) {
        alertMsg("不能大于当前时间！");
        return;
    }
    var o = new AjaxOptions();
    o.put("service_code", "S13016");
    o.put("search_month", m);
    o.sus = function(data) {
        draw(data);
        $("#graph").fadeIn(300);
    };
    o.fal = function() {
        $("#graph").hide();
    };
    $.ajax(o);
}).trigger("click");

/**
 * 生成图表
 * @param {type} data
 * @returns {undefined}
 */
function draw(data) {
    var year = $("#year").val();
    var month = $("#month").val();
    var myChart = new JSChart('graph', 'bar');
    myChart.setErrors(false);
    myChart.setTitleFontSize(16);
    myChart.setTitleColor('#555');
    myChart.setAxisNameX('');
    myChart.setAxisNameY('单位：吨');
    myChart.setAxisColor('#C4C4C4');
    myChart.setAxisNameFontSize(16);
    myChart.setAxisNameColor('#555');
    myChart.setAxisValuesColor('#777');
    myChart.setAxisColor('#B5B5B5');
    myChart.setAxisWidth(1);
    myChart.setTextPaddingLeft(0);
    myChart.setBarValuesColor('#2F6D99');
    myChart.setBarOpacity(0.5);
    myChart.setAxisPaddingTop(60);
    myChart.setAxisPaddingBottom(40);
    myChart.setAxisPaddingLeft(60);
    myChart.setBarBorderWidth(0);
    myChart.setBarSpacingRatio(50);
    myChart.setBarOpacity(0.9);
    myChart.setFlagRadius(6);
    myChart.setSize(750, 450);
    myChart.setLegendShow(true);
    myChart.setBarValues(false);
    myChart.setBackgroundImage(BaseUrl + 'images/chart_bg.jpg');
    myChart.setTitle(year + "-" + month + ' 甲醇月产量');
    myChart.setDataArray(data.JSChart.result, 'bar');
    myChart.setBarColor('#2D6B96', 1);
    myChart.setBarColor('#9CCEF0', 2);
    myChart.setLegendForBar(1, '粗醇折精醇');
    myChart.setLegendForBar(2, '乙二醇折精醇');
    myChart.draw();
}