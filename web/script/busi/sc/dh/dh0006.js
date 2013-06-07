/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

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
myChart.setBarValues(false);
myChart.setBackgroundImage(BaseUrl + 'images/chart_bg.jpg');

$("#graph").hide();

var nowDate = getNowDate();
nowDate = nowDate.split("-");
$("#year").val(nowDate[0]);
$("#month").val(nowDate[1]);

$("#search").click(function() {
    var year = $("#year").val();
    var month = $("#month").val();
    var now = getNowDate();
    if (now < (year + "-" + month + "-01")) {
        alertMsg("不能大于当前时间！");
        return;
    }
    $("#graph").show();
    myChart.setTitle(year + "-" + month + ' 甲醇月产量');
    myChart.setDataJSON(BaseUrl + "chart/data/S13016.do?search_month=" + year + "-" + month);      //获取甲醇月产量
    myChart.draw();
}).trigger("click");
