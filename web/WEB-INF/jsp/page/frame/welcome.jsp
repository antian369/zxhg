<%--
    Document   : welcome
    Created on : 2012-7-17, 23:46:31
    Author     : Asus
--%>

<%@page import="com.soa.util.SystemUtil"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="<c:url value="/css/frame_pc.css" />" type="text/css" rel="stylesheet"/>
        <link href="<c:url value="/css/redmond/jquery-ui-1.8.21.css" />" type="text/css" rel="stylesheet"/>
        <script type="text/javascript" src="<c:url value='/script/jquery-1.7.1.js' />"></script>
        <script type="text/javascript" src="<c:url value='/script/frame-pc.js' />"></script>
        <script type="text/javascript" src="<c:url value='/script/jquery-ui-1.8.21.min.js' />"></script>
        <script type="text/javascript" src="<c:url value='/script/jstree/jquery.jstree.js' />"></script>
        <title>欢迎使用<%=SystemUtil.serverDesc%></title>
        <script type="text/javascript">
            var menus = ${menus_json.value};
        </script>
        <style type="text/css">
            #menu_search{
                height: 15px;
                background-color: #0B84C9;
                color: #FFF;
                font-weight: bold;
                padding: 4px 12px 4px 12px;
            }
        </style>
    </head>
    <body>
        <div id="logo">
            <strong><%=SystemUtil.serverDesc%></strong>
        </div>
        <div id="page_body" style="height: 700px;">
            <div id="left_work">
                <p id="menu_search">系统权限</p>
                <div id="left_menu">
                    <ul id="menu">
                        <li><a href="<c:url value="/_page/frame/update_psw.do" />" title="修改密码" target="work">修改密码</a></li>
                        <li><a href="<c:url value="/_page/frame/index.do" />" title="返回首页" target="work">返回首页</a></li>
                        <li><a href="<c:url value="/_page/frame/logout.do" />" title="退出登录" target="work">退出</a></li>
                    </ul>
                </div>
            </div>
            <div id="right_work">
                <!-- InstanceBeginEditable name="content" -->
                <iframe id="work" name="work" src="<c:url value="/_page/frame/index.do" />"></iframe>
                <!-- InstanceEndEditable -->
            </div>
        </div>
        <div id="footer">
            <div id="footer-inside">
                <p>
                    Copyright&nbsp;©&nbsp;2012&nbsp; <%=SystemUtil.serverDesc%> &nbsp;|&nbsp;
                    &nbsp;|&nbsp;新乡中新化工有限责任公司
                </p>
            </div>
        </div>
    </body>
</html>
