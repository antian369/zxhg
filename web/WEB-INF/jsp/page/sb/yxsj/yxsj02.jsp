
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
        <title><%=SystemUtil.serverDesc%> -- 主要装置运行时间统计</title>
        <!-- InstanceEndEditable -->
        <!-- InstanceBeginEditable name="head" -->
        <script type="text/javascript">
            var outJson = ${out_json};
            var dep = '${sessionScope.ses.dep_id.value}';
        </script>
        <style type="text/css">            
            .tablelist th{
                padding: 4px 8px 4px 8px;
            }
        </style>
        <!-- InstanceEndEditable -->
    </head>
    <body>
        <!-- InstanceBeginEditable name="content" -->

        <div style="margin: 10px auto 10px auto; text-align: center">
            <h2>${yf.value}主要装置运行时间统计</h2></div>
        <hr />
        <table cellpadding="0" cellspacing="0" border="0" class="tablelist" id="ddrb_table" width="99%" style="margin: 10px auto 5px auto;">
            <tr>
                <th width="5%" rowspan="2">日期</th> 
                <td colspan="7" align="center"><strong>热电厂</strong></td>
                <td colspan="3" align="center"><strong>气化厂</strong></td>
                <td colspan="2" align="center"><strong>甲醇厂</strong></td>
                <td colspan="2" align="center"><strong>乙二醇外围</strong></td>
            </tr>
            <tr>
                <th width="7%">1#锅炉</th>
                <th width="7%">2#锅炉</th>
                <th width="7%">3#锅炉</th>
                <th width="9%">发电机组</th>
                <th width="6%">脱盐水</th>
                <th width="6%">循环水</th>
                <th width="5%">污水</th>
                <th width="5%">空分</th>
                <th width="8%">HT-LA炉</th>
                <th width="8%">HT-LB炉</th>
                <th width="8%">甲醇合成</th>
                <th width="6%">硫回收</th>
                <th width="8%">变压吸附</th>
                <th>循环水</th>
            </tr>
            <c:forEach items="${list.value}" var="row" varStatus="xh">
                <tr>
                    <td  align="right">${xh.index + 1}</td>
                    <td  align="right">${row.sb1.value}</td>
                    <td  align="right">${row.sb2.value}</td>
                    <td  align="right">${row.sb3.value}</td>
                    <td  align="right">${row.sb4.value}</td>
                    <td  align="right">${row.sb5.value}</td>
                    <td  align="right">${row.sb6.value}</td>
                    <td  align="right">${row.sb7.value}</td>
                    <td  align="right">${row.sb8.value}</td>
                    <td  align="right">${row.sb9.value}</td>
                    <td  align="right">${row.sb10.value}</td>
                    <td  align="right">${row.sb11.value}</td>
                    <td  align="right">${row.sb12.value}</td>
                    <td  align="right">${row.sb13.value}</td>
                    <td  align="right">${row.sb14.value}</td>
                </tr>
            </c:forEach>
            <tr>
                <td  align="right">合计</td>
                <td  align="right">${sum.value.sb1.value}</td>
                <td  align="right">${sum.value.sb2.value}</td>
                <td  align="right">${sum.value.sb3.value}</td>
                <td  align="right">${sum.value.sb4.value}</td>
                <td  align="right">${sum.value.sb5.value}</td>
                <td  align="right">${sum.value.sb6.value}</td>
                <td  align="right">${sum.value.sb7.value}</td>
                <td  align="right">${sum.value.sb8.value}</td>
                <td  align="right">${sum.value.sb9.value}</td>
                <td  align="right">${sum.value.sb10.value}</td>
                <td  align="right">${sum.value.sb11.value}</td>
                <td  align="right">${sum.value.sb12.value}</td>
                <td  align="right">${sum.value.sb13.value}</td>
                <td  align="right">${sum.value.sb14.value}</td>
            </tr>
        </table>
        <!-- InstanceEndEditable -->
    </body>
    <!-- InstanceEnd --></html>
