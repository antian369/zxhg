<%--
    Document   : index
    Created on : 2012-7-17, 21:59:09
    Author     : Asus
--%>

<%@page import="com.soa.util.SystemUtil"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="<c:url value="/css/redmond/jquery-ui-1.8.21.css" />" type="text/css" rel="stylesheet"/>
        <link href="<c:url value="/css/frame_pc.css" />" type="text/css" rel="stylesheet"/>
        <title><%=SystemUtil.serverDesc%> -- 用户登录</title>
        <script type="text/javascript" src="<c:url value='/script/jquery-1.7.1.js' />"></script>
        <script type="text/javascript" src="<c:url value='/script/frame-pc.js' />"></script>
        <script type="text/javascript" src="<c:url value='/script/jquery-ui-1.8.21.min.js' />"></script>
        <link href="<c:url value="/css/alogin.css" />" rel="stylesheet" type="text/css" />
        <script type="text/javascript">
            $(function(){
                var height = $(window).height() - 121;
                $("#page_body").css("height", height);

                $("#check_img,#change_img").click(function(){
                    $("#check_img").attr("src", BaseUrl + "checknum.do?" + Date());
                    $("#check_num").val("");
                });
                $("#sub").click(function(){
                    if(!checkForm('#data_form')){
                        return false;
                    }
                    var options = new AjaxOptions("#data_form");
                    options.put("service_code", "P11000");
                    options.sus = function(){
                        showDialog("开始加载页面，请稍候...", 10000);
                        getTopWindows().location.href = BaseUrl + "_page/frame/welcome.do";
                    };
                    options.fal = function(){
                        $("#check_img").click();
                    };
                    $.ajax(options);
                    return false;
                });
                $("#check_img").click();        //显示一张验证码
                if($.browser.webkit){
                    $("#supper").hide();
                }
                $(":input").keydown(function(e){
                    if(e.keyCode==13){
                        $("#sub").click();
                    }
                });
            });
        </script>
        <style type="text/css">
            #index-logo{
                color:#EEE;
                text-align: center;
                font-size: 32px;
                margin-top: 50px;
                font-family:Georgia,"Times New Roman",Times,serif;
            }
            #index-footer {
                margin: 10px auto 0;
            }
            #index-footer-inside {
                position: relative;
                width: 640px;
                margin: 0 auto;
                padding: 5px 320px 5px 0;
                color: #AAA;
            }
            #index-footer-inside a {
                border-bottom: 1px dashed #AAA;
                color: #AAA;
            }
        </style>
    </head>
    <body>
    <body>
        <div id="index-logo">
            <strong>欢迎使用<%=SystemUtil.serverDesc%></strong>
        </div>
        <form id="data_form" runat="server">
            <div class="Main">
                <ul>
                    <li class="top"></li>
                    <li class="top2"></li>
                    <li class="topA"></li>
                    <li class="topB"><span>
                            <img src="images/login/logo.gif" alt="" style="" />
                        </span></li>
                    <li class="topC"></li>
                    <li class="topD">
                        <p id="supper" style="color: red">提示：系统检查到您使用的浏览器不是chrome内核，推荐使用最新版本chrome或IE9以上版本的浏览器！<a href="chrome.exe" target="_black">下载chrome</a></p>
                        <ul class="login">
                            <li><span class="left">用户名：</span>
                                <span style="text-align: left">
                                    <input type="text" id="username" name="username" fn="username('用户名','#username')" class="txt"/>
                                </span></li>
                            <li><span class="left">密 码：</span> <span style="text-align: left">
                                    <input type="password" id="password" name="password" fn="password('密码','#password')" class="txt"/>
                                </span></li>
                            <li><span class="left">验证码：</span> <span style="text-align: left">
                                    <input type="text" id="check_num" name="check_num" size="8" fn="isNum('验证码','#check_num')|isLength('验证码',4,'#check_num')" class="txtCode" />
                                    <img id="check_img" />
                                    <a href="#this" id="change_img">换一张</a>
                                </span>
                            </li>
                            <li>
                            </li>
                        </ul>
                    </li>
                    <li class="topE"></li>
                    <li class="middle_A"></li>
                    <li class="middle_B"></li>
                    <li class="middle_C">
                        <span class="btn">
                            <a id="sub" href="#this"><img alt="" src="images/login/btnlogin.gif" /></a>
                        </span>
                    </li>
                    <li class="middle_D"></li>
                    <li class="bottom_A"></li>
                    <li class="bottom_B"></li>
                </ul>
            </div>
        </form>
        <div id="index-footer">
            <div id="index-footer-inside">
                <p>
		    Copyright&nbsp;©&nbsp;2012&nbsp; <%=SystemUtil.serverDesc%> &nbsp;|&nbsp;
                    &nbsp;|&nbsp;新乡中新化工有限责任公司
                </p>
            </div>
        </div>
    </body>
</html>
