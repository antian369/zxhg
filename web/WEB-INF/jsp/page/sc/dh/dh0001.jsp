
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
        <title><%=SystemUtil.serverDesc%> -- 单耗录入</title>
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
        <div style="margin: 10px auto 10px auto; text-align: center"><h2>单耗录入</h2></div>
        <hr />
        <form id="data_form" name="data_form" class="sub_form" style="width: 80%;">
            <table width="100%" border="0">
                <tr class="cl_tr">
                    <td width="50%">
                        日期：
                        <input type="text" id="scrq" name="scrq" data-role="date" size="12" maxlength="10" fn="notNull('日期', '#data_form #scrq')" />
                    </td>
                    <td>
                        <input type="button" id="save" value="保存" />
                    </td>
                </tr>
                <tr>
                    <td colspan="2" height="30"><hr /></td>
                </tr>
                <tr class="cl_tr">
                    <td width="50%">
                        精甲醇(t)耗原煤：
                        <input type="text" id="hym" name="hym" size="12" fn="isNum('精甲醇(t)耗原煤', '#data_form #hym')" value="0" />
                    </td>
                    <td>
                        精甲醇(t)耗燃煤：
                        <input type="text" id="hrm" name="hrm" size="12" fn="isNum('精甲醇(t)耗燃煤', '#data_form #hrm')" value="0" />
                    </td>
                </tr>
                <tr class="cl_tr">
                    <td width="50%">
                        精甲醇(t)耗水：&nbsp;&nbsp;&nbsp;
                        <input type="text" id="hs" name="hs" size="12" fn="isNum('精甲醇(t)耗水', '#data_form #hs')" value="0" />
                    </td>
                    <td>
                        精甲醇(t)耗外电：
                        <input type="text" id="hwd" name="hwd" size="12" fn="isNum('精甲醇(t)耗外电', '#data_form #hwd')" value="0" />
                    </td>
                </tr>
                <tr class="cl_tr">
                    <td width="50%">
                        吨甲醇耗气：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        <input type="text" id="hq" name="hq" size="12" fn="isNum('吨甲醇耗气', '#data_form #hq')" value="0" />
                    </td>
                    <td>
                        &nbsp;
                    </td>
                </tr>
                <tr class="cl_tr">
                    <td colspan="2">
                        备注：
                        <input type="text" id="bz" name="bz" size="50" />
                    </td>
                </tr>
            </table>
        </form>
        <!-- InstanceEndEditable -->
    </body>
    <!-- InstanceEnd --></html>
