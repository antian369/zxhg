
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
        <title><%=SystemUtil.serverDesc%> -- 金山化工用蒸汽产量修改</title>
        <!-- InstanceEndEditable -->
        <!-- InstanceBeginEditable name="head" -->
        <script type="text/javascript">
            var dep = '${sessionScope.ses.dep_id.value}';
        </script>
        <!-- InstanceEndEditable -->
    </head>
    <body>
        <!-- InstanceBeginEditable name="content" -->
        <div style="margin: 10px auto 10px auto; text-align: center"><h2>金山化工用蒸汽产量修改</h2></div>
        <hr />
        <form id="data_form" name="data_form" class="sub_form" style="width: 80%;">
            <input type="hidden" id="cpbh" name="cpbh" value="006" />
            <table width="100%" border="0">
                <tr>
                    <td width="33%" align="center">
                        日期：
                        <input type="text" id="scrq" name="scrq" data-role="date" size="12" maxlength="10" fn="notNull('日期', '#data_form #scrq')" />
                    </td>
                    <td width="33%" align="center">
                        单位： t
                    </td>
                    <td align="center">
                        <input type="button" id="save" value="保存" />
                    </td>
                </tr>
                <tr>
                    <td colspan="3" height="30"><hr /></td>
                </tr>
                <tr class="cl_tr">
                    <td width="33%">
                        中班：
                        <input type="text" id="zb" name="zb" size="12" fn="isNum('中班产量', '#data_form #zb')" value="0" />
                    </td>
                    <td width="33%">
                        夜班：
                        <input type="text" id="yb" name="yb" size="12" fn="isNum('夜班产量', '#data_form #yb')" value="0" />
                    </td>
                    <td>
                        白班：
                        <input type="text" id="bb" name="bb" size="12" fn="isNum('白班产量', '#data_form #bb')" value="0" />
                    </td>
                </tr>
            </table>
        </form>
        <!-- InstanceEndEditable -->
    </body>
    <!-- InstanceEnd --></html>
