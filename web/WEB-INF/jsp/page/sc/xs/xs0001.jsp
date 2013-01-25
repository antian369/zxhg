
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
        <title><%=SystemUtil.serverDesc%> -- 销售信息录入</title>
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
        <div style="margin: 10px auto 10px auto; text-align: center"><h2>销售信息录入</h2></div>
        <hr />
        <form id="data_form" name="data_form" class="sub_form" style="width: 80%;">
            <table width="100%" border="0">
                <tr class="cl_tr">
                    <td width="50%" height="35">
                        日期：
                        <input type="text" id="scrq" name="scrq" data-role="date" size="12" maxlength="10" fn="notNull('日期', '#data_form #scrq')" />
                    </td>
                    <td>
                        <input type="button" id="save" value="保存" />
                    </td>
                </tr>
                <tr>
                    <td colspan="2" height="35"><hr /></td>
                </tr>
                <tr class="cl_tr">
                    <td width="50%" height="35">
                        粗甲醇：
                        <input type="text" id="cjc" name="cjc" size="12" fn="isNum('粗甲醇', '#data_form #cjc')" value="0" />
                    </td>
                    <td>
                        精甲醇：
                        <input type="text" id="jjc" name="jjc" size="12" fn="isNum('精甲醇', '#data_form #jjc')" value="0" />
                    </td>
                </tr>
                <tr class="cl_tr">
                    <td width="50%" height="35">
                        液氧：&nbsp;&nbsp;&nbsp;
                        <input type="text" id="yy" name="yy" size="12" fn="isNum('液氧', '#data_form #yy')" value="0" />
                    </td>
                    <td>
                        液氮：&nbsp;&nbsp;&nbsp;
                        <input type="text" id="yd" name="yd" size="12" fn="isNum('液氮', '#data_form #yd')" value="0" />
                    </td>
                </tr>
                <tr class="cl_tr">
                    <td width="50%" height="35">
                        液氩：&nbsp;&nbsp;&nbsp;
                        <input type="text" id="ya" name="ya" size="12" fn="isNum('液氩', '#data_form #ya')" value="0" />
                    </td>
                    <td>
                        硫磺：&nbsp;&nbsp;&nbsp;
                        <input type="text" id="lh" name="lh" size="12" fn="isNum('硫磺', '#data_form #lh')" value="0" />
                    </td>
                </tr>
                <tr class="cl_tr">
                    <td colspan="2" height="35">
                        硫酸铵：
                        <input type="text" id="lsa" name="lsa" size="12" fn="isNum('硫酸铵', '#data_form #lsa')" value="0" />
                    </td>
                </tr>
                <tr class="cl_tr">
                    <td colspan="2" height="35">
                        备注：&nbsp;&nbsp;&nbsp;
                        <input type="text" id="bz" name="bz" size="50" />
                    </td>
                </tr>
            </table>
        </form>
        <!-- InstanceEndEditable -->
    </body>
    <!-- InstanceEnd --></html>
