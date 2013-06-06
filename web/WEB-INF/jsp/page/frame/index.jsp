
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
        <title><%=SystemUtil.serverDesc%> -- 欢迎使用</title>
        <!-- InstanceEndEditable -->
        <!-- InstanceBeginEditable name="head" -->
        <script type="text/javascript" src="<c:url value='/script/jscharts.js' />"></script>
        <!-- InstanceEndEditable -->
        <style type="text/css">
            table{
                margin-left: auto;
                margin-right: auto;
            }
            strong{
                margin-left: 5%;
            }
            td{
                border-bottom: dashed 1px;
            }
        </style>
    </head>
    <body>
        <!-- InstanceBeginEditable name="content" -->
        <div style="margin: 10px auto 10px auto; width: 90%;"><h3>欢迎使用，${sessionScope.ses.name.value} </h3></div>
        <hr />
        <div align="center" style="width: 750px;height: 450px;margin: 20px auto;background-color: #F7F7F7">
            <div id="graph">正在加载甲醇日产量图表...</div>
        </div>
<!--        <div align="center">
            <input type="checkbox" id="show_bar_value" checked /> <label for="show_bar_value">显示数值(精确到个位)</label>
        </div>-->
        <hr />
        <%--
        <div style="width: 90%;margin: 10px auto 10px auto">
            <h3><a href="<c:url value="/_page/bq/bq0003.do" />">查看公告</a></h3>
            <table align="center" width="90%">
                <c:forEach items="${result.value}" var="gg" varStatus="xh">
                    <tr>
                        <td align="left" width="70%">${gg.ggbt.value}</td>
                        <td align="right">${gg.fssj.value}</td>
                    </tr>
                </c:forEach>
            </table>
        </div>
        --%>
        <!-- InstanceEndEditable -->
    </body>
    <!-- InstanceEnd --></html>
