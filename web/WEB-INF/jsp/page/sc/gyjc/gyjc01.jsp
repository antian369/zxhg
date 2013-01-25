
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
        <title><%=SystemUtil.serverDesc%> -- 工艺检查登记</title>
        <!-- InstanceEndEditable -->
        <!-- InstanceBeginEditable name="head" -->
        <script type="text/javascript">
        </script>
        <!-- InstanceEndEditable -->
    </head>
    <body>
        <!-- InstanceBeginEditable name="content" -->
        <div style="margin: 10px auto 10px auto; text-align: center"><h2>工艺检查登记</h2></div>
        <hr />
        <form id="data_form" name="data_form" class="sub_form" style="width: 80%;">
            <table width="100%" class="table_input" border="0">
                <tr>
                    <td width="40%" align="right">部门：</td>
                    <td width="60%">${sessionScope.ses.dep_name.value}</td>
                </tr>
                <tr>
                    <td width="40%" align="right">级别：</td>
                    <td width="60%">
                        <select id="jb" name="jb">
                            <option value="1">面向公司全体人员</option>
                            <option value="2">面向部门内部</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td width="40%" align="right">类别：</td>
                    <td width="60%">
                        <select id="lb" name="lb">
                            <option value="1">工艺指标</option>
                            <option value="2">巡检点检</option>
                            <option value="3">班前班后会</option>
                            <option value="4">双述法</option>
                            <option value="5">其它专项检查</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td width="40%" align="right">主题：</td>
                    <td width="60%">
                        <input type="text" id="jczt" name="jczt" fn="notNull('主题','#data_form #jczt')" size="60" maxlength="100"/>
                        <span style="color: red;">*</span>
                    </td>
                </tr>
                <tr>
                    <td width="40%" align="right">内容：</td>
                    <td width="60%">
                        <textarea id="jcnr" name="jcnr" rows="5" cols="40" maxlength="500" fn="notNull('内容','#data_form #jcnr')" ></textarea>
                        <span style="color: red;">*</span>
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
