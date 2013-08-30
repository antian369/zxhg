
<%@page import="com.soa.util.SystemUtil"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html><!-- InstanceBegin template="/Templates/work.dwt.jsp" codeOutsideHTMLIsLocked="false" -->
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="<c:url value="/css/frame_pc.css" />" type="text/css" rel="stylesheet"/>
        <link href="<c:url value="/css/redmond/jquery-ui-1.8.21.css" />" type="text/css" rel="stylesheet"/>
        <link href="<c:url value="/css/demo_table_jui.css" />" type="text/css" rel="stylesheet"/>
        <script type="text/javascript" src="<c:url value='/script/jquery-1.7.1.js' />"></script>
        <script type="text/javascript" src="<c:url value='/script/frame-pc.js' />"></script>
        <script type="text/javascript" src="<c:url value='/script/jquery-ui-1.8.21.min.js' />"></script>
        <script type="text/javascript" src="<c:url value='/script/jquery.dataTables.js' />"></script>
        <!-- InstanceBeginEditable name="doctitle" -->
        <title><%=SystemUtil.serverDesc%> -- 在线用户</title>
        <!-- InstanceEndEditable -->
        <!-- InstanceBeginEditable name="head" -->
        <!-- InstanceEndEditable -->
    </head>
    <body>
        <!-- InstanceBeginEditable name="content" -->
        <div style="margin: 10px auto 10px auto; text-align: center"><h2>在线用户</h2></div>
        <hr />
        <p style="text-align: center">共 <span id="all">0</span> 人</p>
        <div id="container" style="width: 95%;margin: 10px auto 10px auto;" align="center">
            <table class="tablelist" id="users">
                <thead>
                    <tr>
                        <th width="25%" data-key="username">用户名</th>
                        <th width="25%" data-key="name">姓名</th>
                        <th width="25%" data-key="login_ip">登录IP</th>
                        <th width="25%" data-key="login_time">登录时间</th>
                    </tr>
                </thead>
                <tbody>
                </tbody>
            </table>
        </div>
        <!-- InstanceEndEditable -->
    </body>
    <!-- InstanceEnd --></html>
