/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

var o = new AjaxOptions();
o.put("service_code", "S13016");
o.isAlert = false;
o.sus = function(data) {
    draw(data);
    $("#graph").fadeIn(300);
    getRwwc();
};
o.fal = function() {
    $("#graph").hide();
};
$.ajax(o);

/**
 * 生成图表
 * @param {type} data
 * @returns {undefined}
 */
function draw(data) {
    var nowDate = getNowDate();
    nowDate = nowDate.split("-");
    var year = nowDate[0];
    var month = nowDate[1];
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

/**
 * 获取任务完成度
 * @returns {undefined}
 */
function getRwwc(){
    var o = new AjaxOptions();
    o.isAlert = false;
    o.isPadBack = false;
    o.put("service_code", "S13017");
    o.sus = function(data){
        $("#rwwc").html(data.result);
    };
    $.ajax(o);
}