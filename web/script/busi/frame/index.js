/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
//var options = new AjaxOptions();
//options.put("service_code", "S13005");
//options.isAlert = false;
//options.isPadBack = false;
//options.sus = function(data){
//    $("#report").attr("src", data["url"]);
//};
//$.ajax(options);

var myChart = new JSChart('graph', 'bar');
myChart.setTitle('当月甲醇月产量');
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

myChart.setDataJSON(BaseUrl + "chart/data/S13016.do");      //获取甲醇月产量

myChart.draw();

//var i = 0;
//var clearJsChart = setInterval(function() {
//    if (i++ > 2000) {
//        clearInterval(clearJsChart);
//    }
//    $("#graph div:has(img):last").remove();
//}, 10);

//$("#show_bar_value").click(function() {
//    if ($(this).attr("checked")) {
//        myChart.setBarValues(true);
//    } else {
//        myChart.setBarValues(false);
//    }
//    myChart.draw();
//})