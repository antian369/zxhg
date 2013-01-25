
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
        <title><%=SystemUtil.serverDesc%> -- 单耗月报表</title>
        <!-- InstanceEndEditable -->
        <!-- InstanceBeginEditable name="head" -->
        <script type="text/javascript">
            var outJson = ${out_json};
            var dep = '${sessionScope.ses.dep_id.value}';
        </script>
        <!-- InstanceEndEditable -->
    </head>
    <body>
        <!-- InstanceBeginEditable name="content" -->
        <div style="margin: 10px auto 10px auto; text-align: center"><h2>单耗月报表</h2></div>
        <hr />
        <form action="dh0005.do" method="post" id="search_form" name="search_form" class="sub_form" style="width: 80%;">
            <input type="hidden" id="do" name="do" value="1" />
            <table width="100%" border="0">
                <tr>
                    <td width="55%" align="right">
                        日期：
                        <select id="year" name="year">
                            <option value="2012">2012</option>
                        </select>年
                        <select id="month" name="month">
                            <option value="1">1</option>
                            <option value="2">2</option>
                            <option value="3">3</option>
                            <option value="4">4</option>
                            <option value="5">5</option>
                            <option value="6">6</option>
                            <option value="7">7</option>
                            <option value="8">8</option>
                            <option value="9">9</option>
                            <option value="10">10</option>
                            <option value="11">11</option>
                            <option value="12">12</option>
                        </select>月
                    <td>
                        <input type="button" id="sub" value="查询" nouse />
                    </td>
                </tr>
            </table>
        </form>
        <hr />
        <div id="container" style="width: 95%;margin: 10px auto 10px auto;">
            <table cellpadding="0" cellspacing="0" border="0" class="display" id="dhbb_table">
                <thead>
                    <tr>
                        <th>日期</th>
                        <th>吨甲醇耗原煤(t/t)</th>
                        <th>吨甲醇耗燃煤(t/t)</th>
                        <th>吨甲醇耗原水(t/t)</th>
                        <th>吨甲醇耗外电(kwh/t)</th>
                        <th>吨甲醇耗耗气(千方/t)</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${dhbb.value}" var="dhbb" varStatus="xh">
                        <tr>
                            <td align="center">${dhbb.dhrq.value}</td>
                            <td align="center">${dhbb.ym.value}</td>
                            <td align="center">${dhbb.rm.value}</td>
                            <td align="center">${dhbb.ys.value}</td>
                            <td align="center">${dhbb.wd.value}</td>
                            <td align="center">${dhbb.qh.value}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
        <!-- InstanceEndEditable -->
    </body>
    <!-- InstanceEnd --></html>
