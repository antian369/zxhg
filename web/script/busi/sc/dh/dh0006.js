/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

$("#container").hide();

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
        $("#container").fadeIn(300);
    };
    o.fal = function() {
        $("#container").hide();
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

    var categories = [];
    var series = [];
    series[0] = {"name": "粗醇折精醇", "data": []};
    series[1] = {"name": "送乙二醇气折醇", "data": []};
    var result = data.JSChart.result;
    for (var i = 0; i < result.length; i++) {
        categories[i] = result[i][0];
        series[0].data[i] = result[i][1];
        series[1].data[i] = result[i][2];
    }
    $('#container').highcharts({
        chart: {
            type: 'column'
        },
        title: {
            text: year + "年" + month + '月甲醇产量图'
        },
        xAxis: {
            title: {
                text: '日期'
            },
            categories: categories
        },
        yAxis: {
            min: 0,
            title: {
                text: '单位：吨'
            }
        },
        legend: {
            align: 'right',
            x: -100,
            verticalAlign: 'top',
            y: 20,
            floating: true,
            backgroundColor: (Highcharts.theme && Highcharts.theme.legendBackgroundColorSolid) || 'white',
            borderColor: '#CCC',
            borderWidth: 1,
            shadow: false
        },
        tooltip: {
            formatter: function() {
                return '<b>' + year + '-' + (this.x > 25 ? month - 1 : month - 0) + '-' + this.x + '</b><br/>' +
                        this.series.name + ': ' + this.y + ' 吨<br/>' +
                        '总计: ' + this.point.stackTotal + ' 吨';
            }
        },
        plotOptions: {
            column: {
                stacking: 'normal'
            }
        },
        series: series
    });
}