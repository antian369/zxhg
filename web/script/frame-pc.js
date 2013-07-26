var _error_info = new Array();
_error_info["0"] = "\u627e\u4e0d\u5230\u670d\u52a1\u5668\uff01";
_error_info["400"] = "\u8bf7\u6c42\u65e0\u6548";
_error_info["401.1"] = "\u672a\u6388\u6743\uff1a\u767b\u5f55\u5931\u8d25";
_error_info["401.2"] = "\u672a\u6388\u6743\uff1a\u670d\u52a1\u5668\u914d\u7f6e\u95ee\u9898\u5bfc\u81f4\u767b\u5f55\u5931\u8d25";
_error_info["401.3"] = "ACL \u7981\u6b62\u8bbf\u95ee\u8d44\u6e90";
_error_info["401.4"] = "\u672a\u6388\u6743\uff1a\u6388\u6743\u88ab\u7b5b\u9009\u5668\u62d2\u7edd";
_error_info["401.5"] = "\u672a\u6388\u6743\uff1aISAPI \u6216 CGI \u6388\u6743\u5931\u8d25";
_error_info["403"] = "\u7981\u6b62\u8bbf\u95ee";
_error_info["403.1"] = "\u7981\u6b62\u8bbf\u95ee\uff1a\u7981\u6b62\u53ef\u6267\u884c\u8bbf\u95ee";
_error_info["403.2"] = "\u7981\u6b62\u8bbf\u95ee\uff1a\u7981\u6b62\u8bfb\u8bbf\u95ee";
_error_info["403.3"] = "\u7981\u6b62\u8bbf\u95ee\uff1a\u7981\u6b62\u5199\u8bbf\u95ee";
_error_info["403.4"] = "\u7981\u6b62\u8bbf\u95ee\uff1a\u8981\u6c42 SSL";
_error_info["403.5"] = "\u7981\u6b62\u8bbf\u95ee\uff1a\u8981\u6c42 SSL 128";
_error_info["403.6"] = "\u7981\u6b62\u8bbf\u95ee\uff1aIP \u5730\u5740\u88ab\u62d2\u7edd";
_error_info["403.7"] = "\u7981\u6b62\u8bbf\u95ee\uff1a\u8981\u6c42\u5ba2\u6237\u8bc1\u4e66";
_error_info["403.8"] = "\u7981\u6b62\u8bbf\u95ee\uff1a\u7981\u6b62\u7ad9\u70b9\u8bbf\u95ee";
_error_info["403.9"] = "\u7981\u6b62\u8bbf\u95ee\uff1a\u8fde\u63a5\u7684\u7528\u6237\u8fc7\u591a";
_error_info["403.11"] = "\u7981\u6b62\u8bbf\u95ee\uff1a\u5bc6\u7801\u66f4\u6539";
_error_info["403.12"] = "\u7981\u6b62\u8bbf\u95ee\uff1a\u6620\u5c04\u5668\u62d2\u7edd\u8bbf\u95ee";
_error_info["403.13"] = "\u7981\u6b62\u8bbf\u95ee\uff1a\u5ba2\u6237\u8bc1\u4e66\u5df2\u88ab\u540a\u9500";
_error_info["403.15"] = "\u7981\u6b62\u8bbf\u95ee\uff1a\u5ba2\u6237\u8bbf\u95ee\u8bb8\u53ef\u8fc7\u591a";
_error_info["403.16"] = "\u7981\u6b62\u8bbf\u95ee\uff1a\u5ba2\u6237\u8bc1\u4e66\u4e0d\u53ef\u4fe1\u6216\u8005\u65e0\u6548";
_error_info["403.17"] = "\u7981\u6b62\u8bbf\u95ee\uff1a\u5ba2\u6237\u8bc1\u4e66\u5df2\u7ecf\u5230\u671f\u6216\u8005\u5c1a\u672a\u751f\u6548";
_error_info["404.1"] = "\u65e0\u6cd5\u627e\u5230 Web \u7ad9\u70b9";
_error_info["404"] = "\u65e0\u6cd5\u627e\u5230\u6587\u4ef6";
_error_info["405"] = "\u8d44\u6e90\u88ab\u7981\u6b62";
_error_info["406"] = "\u65e0\u6cd5\u63a5\u53d7";
_error_info["407"] = "\u8981\u6c42\u4ee3\u7406\u8eab\u4efd\u9a8c\u8bc1";
_error_info["410"] = "\u6c38\u8fdc\u4e0d\u53ef\u7528";
_error_info["412"] = "\u5148\u51b3\u6761\u4ef6\u5931\u8d25";
_error_info["414"] = "\u8bf7\u6c42 ,URI \u592a\u957f";
_error_info["500"] = "\u5185\u90e8\u670d\u52a1\u5668\u9519\u8bef";
_error_info["500.1"] = "\u5185\u90e8\u670d\u52a1\u5668\u9519\u8bef ,ASP \u9519\u8bef";
_error_info["500.11"] = "\u670d\u52a1\u5668\u5173\u95ed";
_error_info["500.12"] = " \u5e94\u7528\u7a0b\u5e8f\u91cd\u65b0\u542f\u52a8";
_error_info["500.13"] = "\u670d\u52a1\u5668\u592a\u5fd9";
_error_info["500.14"] = "\u5e94\u7528\u7a0b\u5e8f\u65e0\u6548";
_error_info["500.15"] = "\u4e0d\u5141\u8bb8\u8bf7\u6c42 global.asa";
_error_info["501"] = "\u672a\u5b9e\u73b0";
_error_info["502"] = "\u7f51\u5173\u9519\u8bef";
function getErrorMsg(code) {
    if (_error_info) {
        if (_error_info[code]) {
            return _error_info[code];
        } else {
            debugLog("\u672a\u5b9a\u4e49\u7684\u5f02\u5e38\uff01");
            return "\u672a\u5b9a\u4e49\u7684\u5f02\u5e38\uff01";
        }
    } else {
        debugLog("\u5f02\u5e38\u4fe1\u606f\u672a\u521d\u59cb\u5316\uff01");
        return "\u5f02\u5e38\u4fe1\u606f\u672a\u521d\u59cb\u5316\uff01";
    }
}
var BaseUrl = "/zxhg/";
var pageUrl = BaseUrl + "_page/";
var ajaxUrl = "/zxhg/ajax.do";
/**
 * 调试
 */
var AjaxDebug = true;
/**
 * 是否弹出ajax结果提示
 */
var isAjaxAlert = false;
/**
 * 保存ajax前的:input状态
 */
var _input_status = new Array();
/**
 * 保存最后一次ajax后的返回结果，类型为一个map  returnData['xx']
 */
var returnData = null;
/**
 * 上传状态
 */
var uploadStatus = false;
/**
 * 页面加载的等待界面
 */
var changeLoading = null;
/**
 * 页面加载等待的定时器
 */
var changeLoadingTime = null;
/**
 * 阻止用户多次点击，阻止时间
 */
var balkTime = 1000;
/**
 * 页面中的可操作元素，文本框和按钮等
 */
var pageElement;
/**
 * ajax的进度条定时器
 */
var ajaxLoading = null;
/**
 * 页面的菜单名
 */
var mid = null;
/**
 * 页面的路径
 */
var pagePath = null;
/**
 * data table jquery 插件的默认汉化皮肤，在$(document.ready())中赋值
 */
var tableTheme = null;
/**
 * 初始化一些必须在窗口加载完成之后才能获取的参数
 */
function getTopHight() {
    try {
        return $(getTopWindows()).scrollTop();
    } catch (e) {
        return 0;
    }
}

/**
 * 获取最顶层的窗口
 */
function getTopWindows() {
    var win = window;
    while (win != parent) {
        win = win.parent;
    }
    return win;
}
/**
 * 一个简单的弹出示提示框
 * @param msg 要提示的信息
 * @param time 消息框的持续时间
 * @param top 距顶部的距离,默认150px
 */
function showDialog(msg, time, top) {
    if (!time)
        time = 400;
    showErrorDialog(msg, time, top);
}


/**
 * 一个简单的弹出示错误提示框
 * @param msg 要提示的信息
 * @param top 距顶部的距离,默认150px
 * @param time 持续时间
 */
function showErrorDialog(msg, time, top) {
    if (!top) {
        if ($.browser.msie) {
            top = getTopHight() + 60;
        } else {
            top = getTopHight() + 220;
        }
    }
    if (!time) {
        time = 5000;
    }
    var doc;
    if ($.browser.msie) {
        //如果是IE浏览器，提示框只在本页面中显示
        doc = document;
    } else {
        doc = getTopWindows().document;
    }
    $("<div class='ui-loader ui-overlay-shadow ui-body-e ui-corner-all'><h1>" + msg + "</h1><hr class='dot' /><p align='center'><a href='#this' class='ui-close-dialog'>关闭</a></p></div>")
            .css({
        "display": "block",
        "opacity": 0.96,
        "top": top
    })
            .appendTo($(doc).find("body"))
            .delay(800)
            .fadeOut(time, function() {
        $(this).remove();
    });
}

/**
 * 关闭所有提示框
 */
function closeAllDialog() {
    $(getTopWindows().document).find(".ui-loader").remove();
}

/**
 * 使用快捷键  F9  开关debug
 * 使用快捷键  F8  开关ajax提示
 */
$(document).keydown(function() {
    switch (event.keyCode) {
        case 120:       //F9
            if (AjaxDebug) {
                alert("alert debug 已关闭！");
                $("#_debug_button").attr("value", "debug 未开启");
                AjaxDebug = false;
            } else {
                alert("alert debug 已开启！");
                $("#_debug_button").attr("value", "debug 已开启");
                AjaxDebug = true;
            }
            break;
        case 119:       //F8
            if (isAjaxAlert) {
                alert("ajax提示已关闭！");
                isAjaxAlert = false;
            } else {
                alert("ajax提示已开启！");
                isAjaxAlert = true;
            }
            break;
        default:
            break;
    }
});
/**
 * 提示框
 * @param content 提示的内容，不能为空
 * @param title 提示框的标题，可为空
 * @param dialog 深度定制提示时使用，是一个json对象，参考jquery ui 的dialog参数。
 * @param html 深度定制提示时使用，内容的html代码。
 */
function alertMsg(content, title, dialog, html) {
    if (!content) {
        content = "";
    }
    if (!title) {
        title = "提示：";
    }
    if (!dialog) {
        dialog = {
            modal: true,
            buttons: {
                '确定': function() {
                    $(this).dialog("close");
                }
            }
        };
    }
    if (html) {
        $("#dialog-message").html(html);
    }
    $("#dialog-message").attr("title", title);
    $("#dialog-message #dialog-message-content").html(content);
    $("#dialog-message").dialog(dialog);
}
/**
 * 删除所有表单的默认提交事件，
 * 在form中点击回车键是默认的提交事件
 */
$(document).ready(function() {
    closeAllDialog();
    $(".ui-close-dialog").live("click", function() {
        $(this).closest("div").remove();
    });

    $(":button,:submit,:reset").button();
    $(":text[data-role='date']").datepicker({
        showOn: "both",
        buttonImage: BaseUrl + "images/calendar.gif",
        buttonImageOnly: true,
        dateFormat: 'yy-mm-dd',
        regional: "zh-CN"
    })
            .attr("readonly", "readonly")
            .keydown(function(e) {
        if (e.keyCode == '8') {
            e.preventDefault();
        }
    });
    $(":text[data-role='date'][autovalue]").val(getNowDate());      //有默认值的
    //获取当前页面的菜单名
    var urlArr = window.location.toString().split("/");     //当前页面的URL
    mid = urlArr[urlArr.length - 1];
    mid = mid.substring(0, mid.indexOf("."));
    urlArr = window.location.toString().split(pageUrl);     //_page目录之后的内容
    if (urlArr.length > 1) {
        pagePath = urlArr[urlArr.length - 1];
        pagePath = pagePath.substring(0, pagePath.indexOf("."));
    }
    $("body").append('<div id="dialog-message" style="padding-top: 10px;"><p id="dialog-message-content"></p></div>')
            .append('<div id="_loading"><h1>正在加载，请稍候...</h1><div id="_progressbar"></div></div><div id="_overlay" class="ui-widget-overlay"></div>');

    //动态加载js文件
    //页面加载之前执行。。可进行AOP处理
    $.getScript(BaseUrl + "script/busi/" + pagePath + ".js", function() {
        //页面加载之后执行。。可进行AOP处理
    });
    tableTheme = {
        "bJQueryUI": true,
        "sPaginationType": "full_numbers",
        "bPaginate": false,
        "oLanguage": {
            "sZeroRecords": "没有查询到数据...",
            "sInfo": $("#_cut_page").length ? $("#_cut_page").html() : "&nbsp;",
            "sInfoEmpty": "没有数据",
            "sInfoFiltered": "",
            "sSearch": "过滤",
            "sLoadingRecords": "正在加载，请稍候..."
        }
    };

    //当页面为分页查询时，把查询参数回填到查询表单
    if ($("#_cut_page").length) {
        var param = {};     //暂存查询查询参数
        $("#_cut_page :input").each(function() {
            param[$(this).attr("name")] = $(this).val();        //获取查询参数
        });
        var searchForm = $("#search_form");     //设置查询表单
        if (searchForm.length) {
            searchForm = $("form:eq(0)");       //如果没有找到，设置第一个表单为查询表单
        }
        if (searchForm.length) {
            padBackData(param, searchForm.context);     //回填数据
        }
        $("#_cut_page").remove();       //分页控制已被移动到表格内
    }

    $(".page_num").live("click", function() {
        //翻页事件
        $("#page").val($(this).attr("page"));
        $("#page_form").submit();
    });
    //表格中做为显示使用的select
    $(".table_view_select").each(function() {
        if ($(this).attr("target_select")) {
            //该类的，select属性表示值，target_select表示值参考的下拉列表
            //target_select的值为选择器，下面的表达示会把该选择器指向的select中值到select属性相符的option填入到html中
            $(this).html($($(this).attr("target_select")).find("option[value='" + $(this).attr("select") + "']").html());
        } else {
            //target_select属性为空时，
            $(this).val($(this).attr("select")).css("display", "none");     //把下拉列表隐藏
            $(this).after($(this).find("option:selected").html());      //在其后显示所选的值
        }
    });

    $(":input[nouse]").click(function(e) {
        alertMsg("该功能暂不可用！");
    });

    $("td[col]").live("click", tableTdClick);       //为使用padBackTable函数填充的表格委派事件
});
/**
 * 显示加载进度
 */
function showLoading() {
    $("#_progressbar").progressbar({
        value: 100
    });
    $("#_loading").show();
    $("#_overlay").show();
}
/**
 * 隐藏加载进度
 */
function hideLoading() {
    $("#_progressbar").progressbar('destroy');
    $("#_loading").hide();
    $("#_overlay").hide();
}
/**
 * 阻止用户多次点击，该方法必须放在页面初始化函数之后执行。
 * 用户点击一次按钮后会暂时接触该按钮上的事件，使按钮失效一段时间。
 */
function balkMoreClick() {
    $(":input").click(function() {
        var events = $(this).data("events");        //获取控件上绑定的事件
        var tempEvent = {};                         //用于暂存事件
        $.each(events, function(k, v) {
            tempEvent[k] = {};
            $.each(v, function(i, e) {
                tempEvent[k][i] = e.handler;        //获取所有绑定的事件
            });
        });
        $(this).unbind();                           //接触事件绑定
        setTimeout(function(event, obj) {             //定时，一段时间后恢复之前绑定的事件
            $.each(event, function(k, v) {
                $.each(v, function(i, e) {
                    obj.bind(k, e);                 //重新绑定事件
                });
            });
        }, balkTime, tempEvent, $(this));               //定时器的参数
    });
}
/**
 * 页面的默认加载事件
 */
function defualeInit() {
    balkMoreClick();        //阻止用户多次点击
    $("form").bind("submit", function(event) {
        event.preventDefault();
    }).submit(function() {
        return false;
    });
    //为页面中的元素增加tab次序
    pageElement = $(":input:enabled");
    pageElement.each(function(i) {
        $(this).attr("tabindex", i + 1);
    });
}

/**
 * 调试信息对话框
 * @param obj 对话框信息
 */
function debugLog(obj) {
    if (AjaxDebug) {
        if (!$.browser.msie) {
            console.log(obj);
        }
    }
}

/**
 * 在一个标签中增加一个debug开关，该开关可以开启或关闭debug状态
 * @param id 标签id
 */
function debugLogButton(id) {
    var _value;
    if (AjaxDebug) {
        _value = "debug 已开启";
    } else {
        _value = "debug 未开启";
    }
    $(id).append("<input id = '_debug_button' type='button' class='debug_button' value='" + _value + "'/>");
    $("#_debug_button").click(function() {
        if (AjaxDebug) {
            AjaxDebug = false;
            $(this).attr("value", "debug 未开启");
        } else {
            AjaxDebug = true;
            $(this).attr("value", "debug 已开启");
        }
    });
}

/**
 * 执行ajax时把所有input的attribute属性置为value
 * 适用 disabled:disabled readOnly:readOnly display:none 等
 * @param attribute input属性名
 * @param value 属性值
 */
function _inputStatus(attribute, value, page) {
    _input_status = new Array();
    if (!value) {
        value = attribute;
        debugLog("value值为空，使用attribute名做为value值：" + value);
    }
    $(":input:disabled").each(function() {
        _input_status[_input_status.length] = $(this);
    });
    $(":input").attr(attribute, value);
    debugLog("有 " + _input_status.length + " 个input的" + attribute + "属性为不为空");
}

/**
 * 把input的attribute恢复到ajax前
 * 适用 disabled:disabled readOnly:readOnly display:none 等
 * attribute, value的值必须与运行_inputStatus()时的相同
 * @param attribute input属性名
 * @param value 属性值
 */
function _rdInputStatus(attribute, value, page) {
    if (!value) {
        value = attribute;
        debugLog("value值为空，使用attribute名做为value值：" + value);
    }
    $(":input").removeAttr(attribute);
    for (var index in _input_status) {
        _input_status[index].attr(attribute, value);
    }
    debugLog("有 " + _input_status.length + " 个input的" + attribute + "属性为" + value);
    _input_status = null;
}

/**
 * 当前 时间
 */
function getNowTime() {
    return new Date().format("HH:mm:ss");
}

/**
 * 当前 日期 + 时间
 */
function getNowDateTime() {
    return new Date().format("yyyy-MM-dd HH:mm:ss");
}

/**
 * 当前 日期
 */
function getNowDate() {
    return new Date().format("yyyy-MM-dd");
}

/**
 * Ajax的Options对象，可对Ajax进行详细设置
 * 该类仅为方便代码开发
 * @param formId 表单的提交与回填范围，可以是page也可以具体的form，一般使用page。
 */
AjaxOptions = function(formId) {
    /**
     * 在执行ajax请求过程中是否出现提示<br />类型：boolean
     */
    this.isAlert = true;
    /**
     * 所有请求均为异步请求。如果需要发送同步请求，请将此项设置为false
     * 类型： Boolean
     */
    this.async = true;

    /**
     * 与服务器进行ajax交互时，用户是否可以进行输入。
     * 设置为true时，ajax期间所有表单项全为disabled状态。
     */
    this.noInput = true;
    /**
     * 要提交的表单
     */
    this.form = $(formId);

    /**
     * (默认：true， dataType为script和jsonp时默认为false) 设置为false将不缓存此页面
     * 类型 Boolean
     */
    this.cache = false;

    /**
     * 类型： String
     */
    this.url = ajaxUrl;

    /**
     * 类型：boolean
     * 为true时表示请求的是一个页面，为false时表示请求的是一个服务
     */
    this.isPage = false;

    /**
     * 返回信息回填到指定容器
     */
    this.padBackElement = undefined;
    /**
     * 是否回填
     */
    this.isPadBack = true;

    /**
     * 类型： Object
     */
    this.data = "";
    if (this.form.length) {//判断表单是否为空
        this.data = this.form.serialize();
        if (!$.trim(this.data)) {
            this.data = this.form.find("form").serialize();
        }
    }


    /**
     * 默认的成功回调函数
     * @param data 服务器返回的数据
     */
    this.success = function(data) {
        if (this.isPage) {
            if ($.isFunction(this.sus)) {
                debugLog("开始执行自定义的成功回调函数！");
                showDialog("执行成功");
                this.sus(data);
            }
            if ($.isFunction(this.after)) {
                debugLog("开始执行自定义的after函数！");
                this.after(200, "成功");
            }
            return;
        }
        try {
            returnData = eval('(' + data + ')');       //把字符串转为json
        } catch (e) {
            returnData = {
                'head': {}
            };
        }
        if (returnData.head.response_code != "000000") {
            //异常处理
            debugLog("异常代码=" + returnData.head.response_code + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 描述=" + returnData.head.response_desc);
            alertMsg(returnData.head.response_desc);
            if ($.isFunction(this.fal)) {
                debugLog("执行异常，开始运行！");
                this.fal(returnData.head.response_code, returnData.head.response_desc);
                return;
            }
        }
        if (this.isPadBack) {
            padBackData(returnData, this.padBackElement);        //把返回数据回填到页面
        }
        this.responseData = returnData;     //需要把返回数据的处理 和页面的控制分离。
    };

    /**
     * 请求完成后回调函数 (请求成功或失败之后均调用)。
     * 参数： XMLHttpRequest 对象和一个描述成功请求类型的字符串。
     * 类型： function(XMLHttpRequest, textStatus)
     */
    this.complete = function(request, status) {
        clearTimeout(ajaxLoading);      //清楚定时器
        hideLoading();      //隐藏
        if (this.noInput) {
            _rdInputStatus("disabled", "disabled", this.form);
        }
        if (status == "success") {
            if (this.isPage)
                return;     //如果请求的是页面，直接退出
            if (returnData.head.response_code == "000000") {
                if (this.isAlert) {
                    showDialog("操作成功！");
                }
                if ($.isFunction(this.sus)) {
                    debugLog("开始执行自定义的成功回调函数！");
                    this.sus(returnData);
                }
            }
            if ($.isFunction(this.after)) {
                debugLog("开始执行finally函数");
                this.after(returnData.head.response_code, returnData.head.response_desc);
            }
        }
        //        getReturnDateObj().append(" &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;结束： " + getNowTime());
    };

    /**
     * 发送请求前可修改 XMLHttpRequest 对象的函数，如添加自定义 HTTP 头。XMLHttpRequest 对象是唯一的参数。
     * 这是一个 Ajax 事件。如果返回false可以取消本次ajax请求。
     * 类型： function(XMLHttpRequest)
     */
    this.beforeSend = function(XMLHttpRequest) {
        this.data = this.data + "&_type=ajax&m_id=" + mid;
        var nowTime = getNowTime();
        if (this.noInput) {
            _inputStatus("disabled", "disabled", this.form);
        }
        debugLog("url:\t" + this.url + "\ndata:\t" + this.data.replace(/&/g, "\n\t") + "\ntype:\t" + this.type + "\ntime:\t" + nowTime);
        if (this.isAlert) {
            ajaxLoading = setTimeout(function() {
                showLoading();
            }, 1000);
        }
        return true;
    };

    /**
     * (默认: "application/x-www-form-urlencoded") 发送信息至服务器时内容编码类型。默认值适合大多数情况。
     * 如果你明确地传递了一个content-type给 $.ajax() 那么他必定会发送给服务器（即使没有数据要发送）
     * 类型： String
     */
    this.contentType = "application/x-www-form-urlencoded; charset=utf-8";

    /**
     * 这个对象用于设置Ajax相关回调函数的上下文。
     * 也就是说，让回调函数内this指向这个对象（如果不设定这个参数，那么this就指向调用本次AJAX请求时传递的options参数）。
     * 比如指定一个DOM元素作为context参数，这样就设置了success回调函数的上下文为这个DOM元素。
     * 类型：Object； (document.body)
     */
    this.context = undefined;

    /**
     * 给Ajax返回的原始数据的进行预处理的函数。
     * 提供data和type两个参数：data是Ajax返回的原始数据，type是调用jQuery.ajax时提供的dataType参数。
     * 函数返回的值将由jQuery进一步处理。
     * 类型： function(data, type)
     */
    this.dataFilter = function(data, type) {
        if (type) {
            debugLog("dataFilter-->data type:" + type);
        }
        return data;
    };

    /**
     * 预期服务器返回的数据类型。
     * 如果不指定，jQuery 将自动根据 HTTP 包 MIME 信息来智能判断，比如XML MIME类型就被识别为XML。
     * 在1.4中，JSON就会生成一个JavaScript对象，而script则会执行这个脚本。随后服务器端返回的数据会根据这个值解析后，传递给回调函数。可用值:
     *      "xml": 返回 XML 文档，可用 jQuery 处理。
     *      "html": 返回纯文本 HTML 信息；包含的script标签会在插入dom时执行。
     *      "script": 返回纯文本 JavaScript 代码。不会自动缓存结果。除非设置了"cache"参数。'''注意：'''在远程请求时(不在同一个域下)，所有POST请求都将转为GET请求。(因为将使用DOM的script标签来加载)
     *      "json": 返回 JSON 数据 。
     *      "jsonp": JSONP 格式。使用 JSONP 形式调用函数时，如 "myurl?callback=?" jQuery 将自动替换 ? 为正确的函数名，以执行回调函数。
     *      "text": 返回纯文本字符串
     * 类型：String
     */
    this.dataType = undefined;

    /**
     * (默认: 自动判断 (xml 或 html)) 请求失败时调用此函数。
     * 有以下三个参数：XMLHttpRequest 对象、错误信息、（可选）捕获的异常对象。
     * 如果发生了错误，错误信息（第二个参数）除了得到null之外，还可能是"timeout", "error", "notmodified" 和 "parsererror"。
     * 类型： function(XMLHttpRequest, textStatus, errorThrow)
     */
    this.error = function(request, status, error) {
        switch (status) {
            case "timeout":
                debugLog("请求超时!");
                error_msg = "请求超时!";
                if (this.isAlert) {
                    alertMsg("请求超时！");
                }
                break;
            case "error":
            case "notmodified":
            case "parsererror":
                var error_msg = null;
                try {
                    if (request.status == 0 || request.status == 1 || request.status == 2 || request.status == 3) {
                        //0 (未初始化), 1 (正在装载), 2 (装载完毕), 3 (交互中), 4 (完成)
                        error_msg = "系统正在等待响应，请稍后。";
                    } else {
                        error_msg = _error_info[request.status];
                        if (!error_msg) {
                            error_msg = "错误代码未定义！";
                            if (this.isAlert) {
                                debugLog("错误代码未定义！");
                            }
                        }
                    }
                } catch (e) {
                    debugLog("function : AjaxOptions.error()\nname : " + e.name + "\nmessage : " + e.message + "\n 错误信息未引入！");
                    error_msg = "错误代码未定义！";
                }
                debugLog("(" + request.status + ")" + error_msg);
                if (this.isAlert) {
                    alertMsg(error_msg);
                }
                break;
            default:
                debugLog("服务器未响应！");
                error_msg = "服务器未响应！";
                if (this.isAlert) {
                    alertMsg("服务器未响应！");
                }
                break;
        }
        if ($.isFunction(this.fal)) {
            debugLog("开始执行自定义的异常回调函数！");
            this.fal(request.status, error_msg);
        }
        if ($.isFunction(this.after)) {
            debugLog("开始执行finally函数");
            this.after(request.status, error_msg);
        }
    };

    /**
     * (默认: true) 是否触发全局 AJAX 事件。设置为 false 将不会触发全局 AJAX 事件.
     */
    this.global = true;

    /**
     * (默认: false) 仅在服务器数据改变时获取新数据。使用 HTTP 包 Last-Modified 头信息判断。
     */
    this.ifModified = false;

    /**
     * 在一个jsonp请求中重写回调函数的名字。
     * 这个值用来替代在"callback=?"这种GET或POST请求中URL参数里的"callback"部分，
     * 比如{jsonp:'onJsonPLoad'}会导致将"onJsonPLoad=?"传给服务器。
     * 类型： String
     */
    this.jsonp = undefined;

    /**
     * 为jsonp请求指定一个回调函数名。
     * 这个值将用来取代jQuery自动生成的随机函数名。
     * 这主要用来让jQuery生成度独特的函数名，这样管理请求更容易，也能方便地提供回调函数和错误处理。
     * 你也可以在想让浏览器缓存GET请求的时候，指定这个回调函数名。
     * 类型： String
     */
    this.jsonpCallback = undefined;

    /**
     * 用于响应HTTP访问认证请求的密码
     * 类型： String
     */
    this.password = undefined;

    /**
     * (默认: true) 默认情况下，通过data选项传递进来的数据，
     * 如果是一个对象(技术上讲只要不是字符串)，都会处理转化成一个查询字符串，以配合默认内容类型 "application/x-www-form-urlencoded"。
     * 如果要发送 DOM 树信息或其它不希望转换的信息，请设置为 false。
     */
    this.processData = true;

    /**
     * 只有当请求时dataType为"jsonp"或"script"，并且type是"GET"才会用于强制修改charset。通常只在本地和远程的内容编码不同时使用。
     * 类型： String
     */
    this.scriptCharset = undefined;

    /**
     * 用于响应HTTP访问认证请求的用户名
     * 类型： String
     */
    this.username = undefined;

    /**
     * 注：当请求为异步时有效
     * 类型： Number
     */
    this.timeout = 10000;

    /**
     * 类型： String
     */
    this.type = "post";

    /**
     * 自定义的成功回调函数
     * @param data ajax返回的数据
     */
    this.sus = undefined;

    /**
     * 自定义的异常回调函数
     */
    this.fal = function(code, desc) {
        //alert("系统错误: " + code + " -- " + desc);
    };

    /**
     * 自定义的finally函数
     */
    this.after = function(code, desc) {

    };

    /**
     * 服务器返回的数据 map类型
     */
    this.responseData = undefined;
    /**
     * 向data中增加参数
     */
    this.put = function(key, value) {
        this.data = this.data.putParam(key, value);
    };
    /**
     * 获取data中的值
     */
    this.get = function(key) {
        return this.data.getParam(key);
    };
    /**
     * 删除data中的参数
     */
    this.remove = function(key) {
        this.data = this.data.remove(key);
    };
};

/**
 * 提供一个表格的数据对象
 */
TableData = function(data) {
    /**
     * 表格ID
     */
    var tableId = undefined;
    /**
     * 主键名
     */
    var primary = undefined;
    /**
     * 主键名 数组
     */
    var primaryArr = undefined;
    /**
     * 表格数据 二维数组
     */
    var datagrid = undefined;

    return {
        tableId: tableId,
        primary: primary,
        primaryArr: primaryArr,
        datagrid: datagrid
    };
};

/**
 * 把一个简单的数据对象回填到clone页面
 * @param data： ajax返回数据
 * @param form： form名 可选
 * @return Boolean
 */
function padBackData(data, form) {
    //表格数据回填，目前服务器端只能处理一个表格，但客户端的JS(下面一段代码)可处理多个
    //表格数据数组, 表格名, 表格属性, 属性名, 表格对象, 表格长度, 行数据, 行HTML代码
    var tempType, tempSet, tempArr, tempI;
    //处理form名为一个jQuery选择器
    if (form && typeof(form) == "string") {
        form = $(form);
        if (!form.length) {
            debugLog("回填时没有找到目标控件，直接回填到document");
            form = $(document);
        }
    } else {
        debugLog("回填时没有找到目标控件，直接回填到document");
        form = $(document);
    }
    try {
        for (var key in data) {
            if (typeof(data[key]) == "object" && key != "head") {
                //把数据填充到表格
                padBackTable(data[key], key);
            } else {
                tempType = form.find("#" + key).attr("type");        //回填的表单项类型
                if (!tempType) {
                    //使用ID获取不到时使用NAME属性
                    tempSet = form.find("[name='" + key + "']");
                    tempType = tempSet.attr("type");
                } else {
                    //可使用ID获取
                    tempSet = form.find("#" + key);
                }
                if (tempType) {
                    switch (tempType) {
                        case "text":
                        case "password":
                        case "hidden":
                        case "textarea":
                            tempSet.attr("value", data[key]);     //直接赋值
                            break;
                        case "radio":
                            tempSet.each(function() {
                                if ($.trim($(this).attr("value")) == $.trim(data[key])) {
                                    $(this).attr("checked", "checked");
                                }
                            });
                            break;
                        case "checkbox":
                            tempSet.each(function() {
                                tempArr = data[key];//.split(ResCutArr);         //分割结果集中的数组元素
                                //使用循环判断，避免一个元素为另一个元素的子集的情况
                                for (tempI = 0; tempI < tempArr.length; tempI++) {
                                    if (tempArr[tempI] == $.trim($(this).attr("value"))) {
                                        break;
                                    }
                                }
                                if (tempI != tempArr.length) {
                                    $(this).attr("checked", "checked");
                                }
                            });
                            break;
                        default:
                            if (tempType.indexOf("select") != -1) {
                                //对单项select赋值
                                tempSet.val(data[key]);
                            }
                            break;
                    }
                } else {
                    //非表单元素  使用ID
                    tempSet = form.find("#" + key);
                    if (tempSet.length > 0) {
                        tempType = form.find("#" + key).get(0).tagName.toLocaleLowerCase();        //回填的表单项类型
                        switch (tempType) {
                            case "select" :
                            case "textarea" :
                                tempSet.val(data[key]);
                                break;
                            default :
                                var str = data[key];
                                if (typeof(str) == "string") {
                                    str = str.replace(/\r\n/g, "<br />");
                                    str = str.replace(/\n/g, "<br />");
                                    str = str.replace(/\r/g, "<br />");
                                    str = str.replace(/\"/g, "&quot;");
                                    str = str.replace(/\'/g, "&acute;");
                                }
                                tempSet.html(str);
                                break;
                        }
                    }
                }
            }
        }
        return true;
    } catch (e) {
        debugLog("function : padBackData()\nname : " + e.name + "\nmessage : " + e.message);
        return false;
    }
}

/**
 * 把一个json数据填充到表格中<br />表格为table标签，并且第一个tr标签的内容是th标签（表头）<br />
 * th标签中包含data-key属性，该属性中的值为json中的key，如果没有该属性或data中没有该值，该列不会填充数值
 * @param tableData 要填充的数据，必须是一个数组，数组中的元素为json
 * @param tableId 表格的id
 * @param isFormat 是否格式化 默认true
 */
function padBackTable(tableData, tableId, isFormat) {
    var tableObj, tableLength, keyList, thead, tbody;
    try {
        tableObj = $(tableId);
        if (tableObj.attr("id")) {
            if (tableObj.find("thead").length == 0) {     //如果没有thead标签，创建一个
                debugLog("表格 " + tableId + " 没有 thead 标签");
                return;
            }
            thead = tableObj.find("thead");
            if (tableObj.find("tbody").length == 0) {     //如果没有tbody标签，创建一个
                debugLog("表格 " + tableId + " 没有 thead 标签");
                tableObj.append($("<tbody/>"));
            }
            tbody = tableObj.find("tbody");
            tableLength = 0;
            keyList = new Array();
            thead.find("tr th").each(function(i) {
                tableLength++;
                keyList[i] = $(this).attr("data-key");
                if (!keyList[i]) {
                    keyList[i] = "";
                }
            });

            tbody.empty();
            tableObj.data("_content", tableData);
            var tr, td, rowData;
            for (var index in tableData) {
                tr = $("<tr/>").attr("row", index);
                rowData = tableData[index];
                if (typeof(rowData) == "object") {
                    for (var k = 0; k < keyList.length; k++) {
                        td = $("<td/>").attr("col", k);
                        if (keyList[k]) {
                            td.html(rowData[keyList[k]]);
                        } else {
                            td.html("&nbsp;");
                        }
                        tr.append(td);
                    }
                }
                tbody.append(tr);
            }
            if (!tableObj.attr("is_format")) {
                if (isFormat == undefined || isFormat) {
                    tableObj.removeClass("display").addClass("display");
                    if (tableObj.attr("class").indexOf("dataTable") == -1) {
                        tableObj.dataTable(tableTheme);
                    }
                } else {
                    tableObj.removeClass("tablelist").addClass("tablelist").removeClass("display").addClass("display");
                }
                tableObj.attr("is_format", "true");
            }
        } else {
            debugLog("表格不存在！");
        }
    } catch (e) {
        debugLog("function : padBackTable()\nname : " + e.name + "\nmessage : " + e.message);
    }
}
/**
 * 使用padBackTable函数回填表格的点击事件
 * 会触发使用$.click(funtion(e, r, c, rd, td){})表格上的点击函数
 */
function tableTdClick(e) {
    var row, col, rowData, tableData, tr, table;
    col = $(this).attr("col");
    tr = $(this).parent();
    row = tr.attr("row");
    table = tr.parent().parent();
    tableData = table.data("_content");
    rowData = tableData[row];
    table.trigger("clicktd", [row, col, rowData, tableData]);//触发表格上的点击函数
}
/**
 * 返回表格上绑定的数据
 */
function getTableData(selector) {
    return $(selector).data("_content");
}

/**
 * 把表单项组装成可提交的数据包，与jquery的serialize相比，包括disabled状态的属性标签
 */
function structFormData(form) {
    form = $(form);
    if (form.length == 0) {
        return "";
    }
    var data = "";
    form.find(":text,:password,:radio:checked,:checkbox:checked,:hidden,textarea").each(function() {
        if ($(this).attr("name")) {
            data = data + $(this).attr("name") + "=" + encodeURI($(this).val()) + "&";
        }
    });
    form.find("select").each(function(i, s) {
        s = $(s);
        if (s.attr("name")) {
            s.find("option:selected").each(function(j, option) {
                option = $(option);
                data = data + s.attr("name") + "=" + encodeURI(option.attr("value")) + "&";
            });
        }
    });
    return data;
}

/**
 * 把表单项组装成json
 */
function jsonFormData(form) {
    form = $(form);
    if (form.length == 0) {
        return "";
    }
    var data = {};
    form.find(":text,:password,:radio:checked,:checkbox:checked,:hidden,textarea").each(function() {
        if ($(this).attr("name")) {
            data[$(this).attr("name")] = $(this).val();
        }
    });
    form.find("select").each(function(i, s) {
        s = $(s);
        if (s.attr("name")) {
            s.find("option:selected").each(function(j, option) {
                option = $(option);
                data[s.attr("name")] = option.attr("value");
            });
        }
    });
    return data;
}

/**
 * 针对ajax操作时的data参数<br />
 * 获取一个参数的值
 */
String.prototype.getParam = function(key) {
    return dataToMap(this)[key];
};
/**
 * 针对ajax操作时的data参数<br />
 * 删除一个参数<br />
 * 函数会返回一个新的字符串，需要对原字符串重新赋值
 */
String.prototype.removeParam = function(key) {
    var map = dataToMap(this);
    map[key] = null;
    return structData(map);
};

/**
 * 针对ajax操作时的data参数<br />
 * 增加一个参数，如果该参数已经存在则替换<br />
 * 函数会返回一个新的字符串，需要对原字符串重新赋值
 */
String.prototype.putParam = function(key, value) {
    var map = dataToMap(this);
    map[key] = value;
    return structData(map);
};

/**
 * 把jquery serialize()转的表单数据转为map
 * @param data 表单数据
 */
function dataToMap(data) {
    var arr = data.split("&");
    var map = new Object();
    var kv;
    for (var i in arr) {
        kv = arr[i].split("=");
        if (kv.length == 2) {
            if (map[kv[0]]) {
                if (typeof(map[kv[0]]) == "string") {
                    var vr = new Array();
                    vr[0] = map[kv[0]];
                    vr[1] = kv[1];
                    map[kv[0]] = vr;
                } else {
                    map[kv[0]][map[kv[0]].length] = kv[1];
                }
            } else {
                map[kv[0]] = kv[1];
            }
        }
    }
    return map;
}

/**
 * 把map组装成表单数据包
 */
function structData(data) {
    if (!data) {
        return "";
    }
    if (typeof(data) != "object") {
        return data;
    }
    var str = "";
    for (var i in data) {
        if (!data[i]) {
            continue;
        }
        if (str) {
            str = str + "&";
        }
        if (typeof(data[i]) == "string") {
            str = str + i + "=" + data[i];
        } else {
            for (var j in data[i]) {
                str = str + i + "=" + data[i][j] + "&";
            }
        }
    }
    return str;
}

/**
 * 把array、json组装成表单数据包，如果包含数组，数组中的元素可以是简单的json对象，非json对象不处理
 */
function structArrayData(data) {
    if (!data) {
        return "";
    }
    if (typeof(data) != "object") {
        return data;
    }
    var str = "";
    var arr = {};
    for (var i in data) {
        if (!data[i]) {
            continue;
        }
        if (typeof(data[i]) == "object") {
            for (var j in data[i]) {
                if (!arr[j]) {
                    arr[j] = (data[i][j] ? data[i][j] : "null");
                } else {
                    arr[j] = arr[j] + "#@#" + (data[i][j] ? data[i][j] : "null");
                }
            }
        }
    }
    for (var i in arr) {
        if (str) {
            str = str + "&";
        }
        str = str + i + "=" + arr[i];
    }
    return str;
}

var errorColor = "#FFD7D8";
var okColor = "#ccffcc";

/**
 * 利用Javascript中每个对象(Object)的prototype属性 为Javascript中的内置对象添加方法和属性。
 */
String.prototype._length = function()
{
    var len = 0;
    for (var i = 0; i < this.length; i++)
        if (this.charCodeAt(i) >= 10000) {
            len += 2;
        } else {
            len++;
        }
    return len;
};
/**
 * 验证，不能为空
 */
function notNull(i_field, obj)
{
    var i_value = $.trim($(obj).attr("value"));
    $(obj).attr("value", i_value);
    if (i_value == "" || i_value == null)
    {
        return "'" + i_field + "' 不可为空！";
    }
    return 1;
}
/**
 * 检验登陆名
 */
function username(i_field, obj) {
    var value = $.trim($(obj).attr("value"));
    $(obj).attr("value", value);
    var patrn = /^[a-zA-Z0-9]{1}([a-zA-Z0-9]|[._-]){4,19}$/;
    if (!patrn.exec(value)) {
        return i_field + "必须为5到19位的英文和数字";
    } else {
        return 1;
    }
}
/**
 * 检验密码
 */
function password(i_field, obj) {
    var value = $.trim($(obj).attr("value"));
    $(obj).attr("value", value);
    var patrn = /^[a-zA-Z0-9]{1}([a-zA-Z0-9]|[._-]){4,19}$/;
    if (!patrn.exec(value)) {
        return i_field + "必须为5到19位的英文和数字";
    } else {
        return 1;
    }
}
/**
 * 检验汉字
 */
function isChinese(i_field, i_length, obj)
{
    var i_value = $.trim($(obj).attr("value"));
    i_value = delspace(i_value);
    $(obj).attr("value", i_value);
    var hzyz = notNull(i_field, obj);
    if (hzyz != 1)
        return hzyz;
    var hzcd = dyLength(i_field, i_length, obj);
    if (hzcd != 1) {
        return hzcd;
    }
    for (i = 0; i < i_value.length; i++) {
        if (i_value.charCodeAt(i) < 128)
            return "'" + i_field + "'只能为汉字";
    }
    return 1;
}
/**
 * 检验包含汉字
 */
function hasChinese(i_field, obj)
{
    var i_value = $.trim($(obj).attr("value"));
    i_value = delspace(i_value);
    var hzyz = notNull(i_field, obj);
    if (hzyz != 1)
        return hzyz;
    for (i = 0; i < i_value.length; i++) {
        if (i_value.charCodeAt(i) > 128)
            return "'" + i_field + "'不能包含汉字";
    }
    return 1;
}

/**
 *检验证件号码 zjmc：A 18位身份证， H 15位身份证
 *正确返回1
 */
function check_zjhm(zjmc, zjhm) {
    var birthday = "";
    var zjhm1 = "";
    var zjhm2 = "";
    var s = "";
    //    var zjhmObj = document.getElementById(zjhm);
    var zjhmObj = $(zjhm);
    zjhmObj.attr("value", $.trim(zjhmObj.attr("value")));
    zjhmObj.attr("value", zjhmObj.attr("value").toUpperCase());
    zjhmObj.attr("value", delspace(zjhmObj.attr("value")));
    //    $( zjhm).attr("value", zjhmObj.value);
    zjhm = zjhmObj.attr("value");
    if (!zjhm) {
        return "证件号码不能为空";
    }
    if (zjmc == "A" || zjmc == "H") { //身份证号码
        if (zjmc == 'H' && zjhm.length != 15) {
            return "身份证明号码长度不正确,请检查！";
        }
        if (zjmc == 'A' && zjhm.length != 18) {
            return "身份证明号码长度不正确,请检查！";
        }
        zjhm1 = zjhm;
        if (zjhm.length == 18) {
            zjhm1 = zjhm.substr(0, 17);
            zjhm2 = zjhm.substr(17, 1);
        }

        re = new RegExp("[^0-9]");
        s = zjhm1.match(re);
        if (s != null && s != "") {
            return "输入的值中含有非法字符'" + s + "'请检查！";
        }
        //取出生日期
        if (zjhm.length == 15) {
            birthday = "19" + zjhm.substr(6, 6);
        } else {
            re = new RegExp("[^0-9A-Z]");
            s = zjhm2.match(re);
            if (s != null && s != "") { //18位身份证对末位要求数字或字符
                return "输入的值中含有非法字符'" + s + "'请检查！";
            }
            birthday = zjhm.substr(6, 8);
        }
        birthday = birthday.substr(0, 4) + "-" + birthday.substr(4, 2) + "-" + birthday.substr(6, 2);
        var birthyz = isDateBirthday("身份证号码出生日期", birthday, zjhmObj);
        if (birthyz != 1) { //检查日期的合法性
            return birthyz;
        }
        if (zjhm.length == 18) {
            return (sfzCheck(zjhm, zjhmObj)); //对18位长的身份证进行末位校验
        } else {
            return sfzCheck(id15to18(zjhm));
        }
    } else if (zjmc == "B") {
        if (!(zjhm.length == 9 || zjhm.length == 10)) {
            return "组织机构代码长度应为9位或10位！";
        }

    }
    return 1;
}
/**
 * 日期校验
 * @param i_field 日期lable
 * @param thedate 日期值(字符串)
 */
function isDateBirthday(i_field, thedate) {
    var reg = /^(\d{1,4})(-|\/)(\d{1,2})\2(\d{1,2})$/;
    var r = thedate.match(reg);
    if (r == null)
    {
        return "'" + i_field + "' 含非法字符！";

    }
    var d = new Date(r[1], r[3] - 1, r[4]);
    var newStr = d.getFullYear() + r[2] + (d.getMonth() + 1) + r[2] + d.getDate();
    var newDate = r[1] + r[2] + (r[3] - 0) + r[2] + (r[4] - 0);
    if (newStr == newDate)
    {
        return 1;
    }
    return "'" + i_field + "'日期格式不对,应为YYYY-MM-DD！";
}
function sfzCheck(hm, obj)
{
    var w = new Array();
    var ll_sum;
    var ll_i;
    var ls_check;
    w[0] = 7;
    w[1] = 9;
    w[2] = 10;
    w[3] = 5;
    w[4] = 8;
    w[5] = 4;
    w[6] = 2;
    w[7] = 1;
    w[8] = 6;
    w[9] = 3;
    w[10] = 7;
    w[11] = 9;
    w[12] = 10;
    w[13] = 5;
    w[14] = 8;
    w[15] = 4;
    w[16] = 2;
    ll_sum = 0;

    for (ll_i = 0; ll_i <= 16; ll_i++) {
        ll_sum = ll_sum + (hm.substr(ll_i, 1) - 0) * w[ll_i];

    }
    ll_sum = ll_sum % 11;

    switch (ll_sum) {
        case 0 :
            ls_check = "1";
            break;
        case 1 :
            ls_check = "0";
            break;
        case 2 :
            ls_check = "X";
            break;
        case 3 :
            ls_check = "9";
            break;
        case 4 :
            ls_check = "8";
            break;
        case 5 :
            ls_check = "7";
            break;
        case 6 :
            ls_check = "6";
            break;
        case 7 :
            ls_check = "5";
            break;
        case 8 :
            ls_check = "4";
            break;
        case 9 :
            ls_check = "3";
            break;
        case 10 :
            ls_check = "2";
            break;
    }
    if (hm.substr(17, 1) != ls_check) {
        return "身份证号码错误";
    }
    return 1
}
/**
 * 身份证号15位转18位
 */
function id15to18(zjhm) {
    var strJiaoYan = new Array("1", "0", "X", "9", "8", "7", "6", "5", "4", "3", "2");
    var intQuan = new Array(7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2, 1);
    var ll_sum = 0;
    var i;
    zjhm = zjhm.substring(0, 6) + "19" + zjhm.substring(6);
    for (i = 0; i <= 16; i++) {
        ll_sum = ll_sum + (parseFloat(zjhm.substr(i, 1))) * intQuan[i];
    }
    ll_sum = ll_sum % 11;
    zjhm = zjhm + strJiaoYan[ll_sum];
    return zjhm;
}

/**
 * 自动清除输入框中的空格
 */
function delspace(string) {
    var temp = "";
    string = '' + string;
    splitstring = string.split(" "); //双引号之间是个空格；
    for (i = 0; i < splitstring.length; i++)
        temp += splitstring[i];
    return temp;
}
/**
 * 检查手机号
 */
function check_sjhm(obj)
{
    var obj_value = $.trim($(obj).attr("value"));
    $(obj).attr("value", obj_value);
    var sjyz = isLength("手机号码", "11", obj);
    if (sjyz != 1)
    {
        return sjyz;
    }
    sjyz = isNum("手机号码", obj);
    if (sjyz != 1)
    {
        return sjyz;
    }
    return 1;
}
/**
 * 检查长度
 */
function isLength(i_field, i_length, obj)
{//  alert("---长度要求:"+i_length+" "+i_value.length);
    var obj_value = $.trim($(obj).attr("value"));
    var i_value = delspace(obj_value);
    $(obj).attr("value", i_value);
    if (!(i_value.length == i_length))
    {
        return "'" + i_field + "' 的长度要求为' " + i_length + " '！";
    }
    return 1;
}
/**
 *检查是否为字母，请配合位数验证函数isLength()使用
 */
function isChar(i_field, obj) {
    var value = $.trim($(obj).attr("value"));
    $(obj).attr("value", value);
    var pattern = /^[a-zA-Z]+$/;
    if (!pattern.exec(value)) {
        return i_field + "必须为字母";
    } else {
        return 1;
    }
    ;
}
/**
 *检查是否为字母或数字，请配合位数验证函数isLength()使用
 */
function isCharOrNum(i_field, obj) {
    var value = $.trim($(obj).attr("value"));
    $(obj).attr("value", value);
    var pattern = /^[a-zA-Z0-9]+$/;
    if (!pattern.exec(value)) {
        return i_field + "必须为字母或数字";
    } else {
        return 1;
    }
    ;
}
;
/***
 * 检查是否为数字
 */
function isNum(i_field, obj)
{
    var obj_value = $.trim($(obj).attr("value"));
    var i_value = delspace(obj_value);
    $(obj).attr("value", i_value);
    var lengthyz = notNull(i_field, obj);
    if (lengthyz != 1)
    {
        return lengthyz;
    }

    re = new RegExp("[^0-9.-]");
    var s = i_value.match(re);
    if (s != null && s != "") {
        return "'" + i_field + "' 中含有非法字符 '" + s + "' ！";
    }
    return 1;
}
/**
 * 检查邮政编码
 */
function check_yzbm(obj)
{
    var obj_value = $.trim($(obj).attr("value"));
    $(obj).attr("value", obj_value);
    var yzbmyz = isLength("邮政编码", "6", obj);
    if (yzbmyz != 1)
    {
        return yzbmyz;
    }
    yzbmyz = isNum("邮政编码", obj);
    if (yzbmyz != 1)
    {
        return yzbmyz;
    }
    return 1;
}
/**
 * 检验长度最大为i_length位
 */
function xyLength(i_field, i_length, obj)
{
    var obj_value = $.trim($(obj).attr("value"));
    var i_value = delspace(obj_value);
    $(obj).attr("value", i_value);
    if (i_value._length() > i_length)
    {
        return "'" + i_field + "' 的长度最长为 '" + i_length + "' ！";
    }
    return 1;
}
//长度至少为i_length位
function dyLength(i_field, i_length, obj)
{
    var obj_value = $.trim($(obj).attr("value"));
    var i_value = delspace(obj_value);
    $(obj).attr("value", i_value);
    if (i_value.length < i_length)
    {
        return "'" + i_field + "' 的长度至少为 '" + i_length + "'！";
    }
    return 1;
}
/**
 * 检验vin
 */
function vinCheck(obj)
{
    var i_value = $.trim(delspace($(obj).attr("value")));
    $(obj).attr("value", i_value);
    var hm = i_value;
    if (i_value.length != 17)
    {
        return notNull("车辆识别代号", obj);
    }

    var q = new Array();
    var z = new Array();
    var sz = new Array();
    var vin_sum;
    var vin_sumover;
    var ll_i;
    var single_c;
    var vinall = "ABCDEFGHJKLMNPRSTUVWXYZ0123456789";
    var wz;
    q[0] = 8;
    q[1] = 7;
    q[2] = 6;
    q[3] = 5;
    q[4] = 4;
    q[5] = 3;
    q[6] = 2;
    q[7] = 10;
    q[8] = 0;
    q[9] = 9;
    q[10] = 8;
    q[11] = 7;
    q[12] = 6;
    q[13] = 5;
    q[14] = 4;
    q[15] = 3;
    q[16] = 2;

    z[0] = 1;
    z[1] = 2;
    z[2] = 3;
    z[3] = 4;
    z[4] = 5;
    z[5] = 6;
    z[6] = 7;
    z[7] = 8;
    z[8] = 1;
    z[9] = 2;
    z[10] = 3;
    z[11] = 4;
    z[12] = 5;
    z[13] = 7;
    z[14] = 9;
    z[15] = 2;
    z[16] = 3;
    z[17] = 4;
    z[18] = 5;
    z[19] = 6;
    z[20] = 7;
    z[21] = 8;
    z[22] = 9;

    ll_i = 0;
    vin_sum = 0;
    for (ll_i = 0; ll_i <= 16; ll_i++)
    {
        single_c = hm.substr(ll_i, 1);
        wz = vinall.indexOf(single_c);
        if (wz != -1) //vialid chars
        {

            if (wz < 23) //is char
            {
                sz[ll_i] = z[wz];
            }
            else  // is num
            {
                sz[ll_i] = wz - 23;
            }
            vin_sum = vin_sum + q[ll_i] * sz[ll_i];

        }
        else  // in vialid chars
        {
            vin_sum = -1;
            break;
        }
    }   //end for
    vin_sumover = vin_sum % 11;
    if (((hm.substr(8, 1) == "X" && vin_sumover == 10) || (hm.substr(8, 1) == vin_sumover)) && (vin_sum != -1))
    {
        return 1;
    }
    else
    {
        return "车辆识别代号不符合要求!";
    }
} //end function

/**
 * 表单校验函数，使用text password textarea 标签的fn属性
 */
function checkForm(form) {
    if (typeof(form) != "string") {
        alertMsg("参数类型错误");
        return false;
    }
    form = $(form);
    if (form.length > 0) {
        var msg = "";
        var temp = "";
        form.find(":text, :password, textarea").each(function() {
            if ($(this).attr("fn")) {
                temp = $(this).attr("fn").split("|");
                for (var ind in temp) {
                    debugLog("运行检验函数：" + temp[ind]);
                    temp[ind] = eval(temp[ind]);
                    if (temp[ind] != 1) {
                        msg = msg + "<br />" + temp[ind];
                    }
                }
            }
        });
        if (msg) {
            alertMsg(msg);
            return false;
        } else {
            return true;
        }
    } else {
        alertMsg("表单不存在");
        return false;
    }
}
/**
 * 创建菜单
 */
function createMenu(menus, menuTag) {
    if (!menus)
        return;     //为空时，退出
    var m, a, li, ul;
    for (var ind in menus) {
        m = menus[ind];
        if (m.m_type == '1') {      //如果是目录
            a = $("<a/>").attr("href", "#").html(m.m_name);
            ul = $("<ul/>").attr({
                id: m.m_id,
                m_super: $.trim(m.m_super) ? menuTag.attr("m_super") + "," + m.m_super : ""
            });
            li = $("<li/>").attr({
                type: "1",
                m_id: m.m_id,
                id: m.m_id
            }).append(a).append(ul);
            createMenu(m.chidren, ul);
            menuTag.prepend(li);
            menuTag.attr("id", ul.attr("id") + "," + m.m_id);
        } else if (m.m_type == '0') {       //如果是菜单
            a = $("<a/>").html(m.m_name).attr({
                href: BaseUrl + "_page/" + m.m_url + ".do",
                title: m.m_title,
                target: "work",
                m_super: menuTag.attr("m_super") + "," + m.m_super
            });
            li = $("<li/>").attr({
                id: m.m_id,
                "type": "0"
            }).append(a);
            menuTag.prepend(li);
            menuTag.attr("id", menuTag.attr("id") + "," + m.m_id);
        }
    }
}
/**
 * 创建部门树
 */
function createUserTree(tree, tag) {
    if (!tree)
        return;     //为空时，退出
    var dep, user, a, li, ul;
    for (var ind in tree) {
        dep = tree[ind];
        a = $("<a/>").attr("href", "#").html(dep.dep_name + " ( " + dep.users.length + "人 )");
        ul = $("<ul/>").attr({
            id: dep.dep_id + "ul"
        });
        li = $("<li/>").attr({
            type: "dep",
            id: dep.dep_id
        }).append(a).append(ul);
        tag.prepend(li);
        for (var i in dep.users) {
            user = dep.users[i];
            a = $("<a/>").html(user.name).attr({
                href: "#this",
                title: user.username
            });
            li = $("<li/>").attr({
                id: user.username,
                "type": "user"
            }).append(a);
            ul.prepend(li);
        }
    }
}
/**
 * jquery-ui 时间选择器汉化
 */
jQuery(function($) {
    $.datepicker.regional['zh-CN'] = {
        closeText: '关闭',
        prevText: '&#x3c;上月',
        nextText: '下月&#x3e;',
        currentText: '今天',
        monthNames: ['一月', '二月', '三月', '四月', '五月', '六月',
            '七月', '八月', '九月', '十月', '十一月', '十二月'],
        monthNamesShort: ['一', '二', '三', '四', '五', '六',
            '七', '八', '九', '十', '十一', '十二'],
        dayNames: ['星期日', '星期一', '星期二', '星期三', '星期四', '星期五', '星期六'],
        dayNamesShort: ['周日', '周一', '周二', '周三', '周四', '周五', '周六'],
        dayNamesMin: ['日', '一', '二', '三', '四', '五', '六'],
        weekHeader: '周',
        dateFormat: 'yy-mm-dd',
        firstDay: 1,
        isRTL: false,
        showMonthAfterYear: true,
        yearSuffix: '年'
    };
    $.datepicker.setDefaults($.datepicker.regional['zh-CN']);
});

/**
 * 可以是数字，也可以是空
 */
function mbIsNum(i_field, obj) {
    if ($(obj).val()) {
        isNum(i_field, obj);
    }
}
/**
 * 刷新页面
 */
function reloadPage() {
    if ($("#page_form").length) {
        $("#page_form").submit();
    } else {
        location.reload();
    }
}

/**
 * 为日期对象增加函数
 * 日期格式化
 * 格式 YYYY/yyyy/YY/yy 表示年份
 * MM/M 月份
 * W/w 星期
 * dd/DD/d/D 日期
 * hh/HH/h/H 时间
 * mm/m 分钟
 * ss/SS/s/S 秒
 */
Date.prototype.format = function(formatStr)
{
    var str = formatStr;
    var Week = ['日', '一', '二', '三', '四', '五', '六'];

    str = str.replace(/yyyy|YYYY/, this.getFullYear());
    str = str.replace(/yy|YY/, (this.getYear() % 100) > 9 ? (this.getYear() % 100).toString() : '0' + (this.getYear() % 100));

    var month = this.getMonth() + 1;
    str = str.replace(/MM/, month > 9 ? month.toString() : '0' + month);
    str = str.replace(/M/g, month);

    str = str.replace(/w|W/g, Week[this.getDay()]);

    str = str.replace(/dd|DD/, this.getDate() > 9 ? this.getDate().toString() : '0' + this.getDate());
    str = str.replace(/d|D/g, this.getDate());

    str = str.replace(/hh|HH/, this.getHours() > 9 ? this.getHours().toString() : '0' + this.getHours());
    str = str.replace(/h|H/g, this.getHours());
    str = str.replace(/mm/, this.getMinutes() > 9 ? this.getMinutes().toString() : '0' + this.getMinutes());
    str = str.replace(/m/g, this.getMinutes());

    str = str.replace(/ss|SS/, this.getSeconds() > 9 ? this.getSeconds().toString() : '0' + this.getSeconds());
    str = str.replace(/s|S/g, this.getSeconds());

    return str;
}