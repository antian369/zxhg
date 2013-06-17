/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
$("#tblb").buttonset();

$("#graph,#tblb").hide();

var charts = {};

var myChart = new JSChart('graph', 'line');
myChart.setErrors(false);
myChart.setTitleFontSize(16);
myChart.setTitleColor('#555');
myChart.setAxisNameX('');
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
    o.put("service_code", "S13005");
    o.put("search_month", m);
    o.sus = function(data) {
        charts = data.JSChart;
        var labelX = data.LabelX;
        for (var i = 0; i < labelX.length; i++) {
            myChart.setLabelX([i + 1, labelX[i]]);
        }
        myChart.setShowXValues(false);
        drawTable("hym");       //默认显示耗原煤
        $(":radio").remove("checked");
        $("#hym").attr("checked", "checked");
        $("#tblb").buttonset();
        $("#graph, #tblb").fadeIn(300);
    };
    o.fal = function() {
        $("#graph, #tblb").hide();
    };
    $.ajax(o);
}).trigger("click");

$(":radio").click(function() {
    var tblb = $(":radio:checked").attr("id");
    drawTable(tblb);
});

/**
 * 生成图表
 * @param {type} tblb
 * @returns {undefined}
 */
function drawTable(tblb) {
    switch (tblb) {
        case "hym" :
            myChart.setAxisNameY('单位：吨');
            myChart.setTitle(m + ' 精甲醇(t)耗原煤');
            break;
        case "hrm":
            myChart.setAxisNameY('单位：吨');
            myChart.setTitle(m + ' 精甲醇(t)耗燃煤');
            break;
        case "hs":
            myChart.setAxisNameY('单位：吨');
            myChart.setTitle(m + ' 精甲醇(t)耗水');
            break;
        case "hwd":
            myChart.setAxisNameY('单位：度');
            myChart.setTitle(m + ' 精甲醇(t)耗外电');
            break;
        case "hq":
            myChart.setAxisNameY('单位：kNm³');
            myChart.setTitle(m + ' 吨甲醇耗有效气');
            break;
        case "qhym":
            myChart.setAxisNameY('单位：吨');
            myChart.setTitle(m + ' 千方有效气耗原煤');
            break;
        case "zqhm":
            myChart.setAxisNameY('单位：吨');
            myChart.setTitle(m + ' 吨蒸汽耗燃料煤');
            break;
    }
    myChart.setDataArray(charts[tblb], 'line');
    myChart.draw();
}
