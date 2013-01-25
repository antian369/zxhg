
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
        <title><%=SystemUtil.serverDesc%> -- 主要装置运行时间录入</title>
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
        <div style="margin: 10px auto 10px auto; text-align: center"><h2>主要装置运行时间录入</h2></div>
        <hr />
        <form id="data_form" name="data_form" class="sub_form" style="width: 80%;">
            <table width="100%" class="table_input" border="0">
                <tr>
                    <td width="40%" align="right">分厂：</td>
                    <td>
                        <select id="sbfc" name="sbfc">
                            <option value="0">热电厂</option>
                            <option value="1">气化厂</option>
                            <option value="2">甲醇厂</option>
                            <option value="3">乙二醇外围</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td width="40%" align="right">设备：</td>
                    <td>
                        <select class="sb" style="display: none">
                            <option>1#锅炉</option>
                            <option>2#锅炉</option>
                            <option>3#锅炉</option>
                            <option>发电机组</option>
                            <option>脱盐水</option>
                            <option>循环水</option>
                            <option>污水</option>
                        </select>
                        <select class="sb" style="display: none">
                            <option>空分</option>
                            <option>HT-LA炉</option>
                            <option>HT-LB炉</option>
                        </select>
                        <select class="sb" style="display: none">
                            <option>甲醇合成</option>
                            <option>硫回收</option>
                        </select>
                        <select class="sb" style="display: none">
                            <option>变压吸附</option>
                            <option>循环水</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td width="40%" align="right">日期：</td>
                    <td>
                        <input type="text" id="rq" name="rq" data-role="date" autovalue size="12" fn="notNull('日期', '#data_form #rq')" />
                        <span style="color:red">*</span>
                    </td>
                </tr>
                <tr>
                    <td width="40%" align="right">运行时间：</td>
                    <td>
                        <input type="text" id="yxsj" name="yxsj" size="12" fn="notNull('运行时间', '#data_form #yxsj')" />
                        <span style="color:red">*</span>
                    </td>
                </tr>
            </table>
            <br />
            <div align="center">
                <input type="button" id="sub" value=" 提交 " />
                <input type="reset" value=" 重置 " />
            </div>
        </form>
        <!-- InstanceEndEditable -->
    </body>
    <!-- InstanceEnd --></html>
