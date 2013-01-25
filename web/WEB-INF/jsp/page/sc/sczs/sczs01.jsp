
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
        <title><%=SystemUtil.serverDesc%> -- 生产情况综述录入</title>
        <!-- InstanceEndEditable -->
        <!-- InstanceBeginEditable name="head" -->
        <script type="text/javascript">
        </script>
        <!-- InstanceEndEditable -->
    </head>
    <body>
        <!-- InstanceBeginEditable name="content" -->
        <div style="margin: 10px auto 10px auto; text-align: center"><h2>生产情况综述录入</h2></div>
        <hr />
        <form id="data_form" name="data_form" class="sub_form" style="width: 80%;">
            <table width="100%" class="table_input" border="0">
                <tr>
                    <td align="center" colspan="2">
                        日期：
                        <input type="text" id="scrq" name="scrq" data-role="date" size="12" maxlength="10" fn="notNull('日期', '#data_form #scrq')" />
                        <input type="button" id="save" value=" 保存 " />
                    </td>
                </tr>
                <tr>
                    <td colspan="2" height="30"><hr /></td>
                </tr>
                <tr>
                    <td width="20%" align="right">热电：</td>
                    <td>
                        <textarea id="rdzs" name="rdzs" rows="7" cols="80" maxlength="500" fn="notNull('热电','#data_form #rdzs')" ></textarea>
                        <span style="color: red;">*</span>
                    </td>
                </tr>
                <tr>
                    <td width="20%" align="right">气化：</td>
                    <td>
                        <textarea id="qhzs" name="qhzs" rows="7" cols="80" maxlength="500" fn="notNull('气化','#data_form #qhzs')" ></textarea>
                        <span style="color: red;">*</span>
                    </td>
                </tr>
                <tr>
                    <td width="20%" align="right">甲醇：</td>
                    <td>
                        <textarea id="jczs" name="jczs" rows="7" cols="80" maxlength="500" fn="notNull('甲醇','#data_form #jczs')" ></textarea>
                        <span style="color: red;">*</span>
                    </td>
                </tr>
                <tr>
                    <td width="20%" align="right">其它：</td>
                    <td>
                        <textarea id="bz" name="bz" rows="7" cols="80" maxlength="500"></textarea>
                    </td>
                </tr>
            </table>
        </form>
        <!-- InstanceEndEditable -->
    </body>
    <!-- InstanceEnd --></html>
