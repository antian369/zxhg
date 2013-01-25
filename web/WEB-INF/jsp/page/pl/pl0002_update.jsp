
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
        <title><%=SystemUtil.serverDesc%> -- 修改角色信息</title>
        <!-- InstanceEndEditable -->
        <!-- InstanceBeginEditable name="head" -->
        <script type="text/javascript">
            var depId = "${dep_id.value}";
        </script>
        <!-- InstanceEndEditable -->
    </head>
    <body>
        <!-- InstanceBeginEditable name="content" -->
        <div style="margin: 10px auto 10px auto; text-align: center"><h2>修改角色信息</h2></div>
        <form id="data_form" name="data_form" class="sub_form" style="width: 60%;">
            <table width="100%" border="0" class="table_input">
                <input type="hidden" id="role_id" name="role_id" value="${role_id.value}" />
                <tr>
                    <td align="right" width="40%">角色名称：</td>
                    <td>
                        <input type="text" id="role_name" name="role_name" fn="notNull('角色名称','#role_name')" value="${role_name.value}" />
                        <span style="color: red">*</span>
                    </td>
                </tr>
                <tr>
                    <td align="right">所在部门：</td>
                    <td>
                        <select id="dep_id" name="dep_id">
                            <c:forEach items="${deps.value}" var="dep">
                                <option value="${dep.dep_id.value}">${dep.dep_name.value}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td align="right">备注：</td>
                    <td><input type="text" id="bz" name="bz" value="${bz.value}" /></td>
                </tr>
                <tr>
                    <td align="center" colspan="2">
                        <input type="button" id="sub" value="保存" />
                        <input type="reset" value="重置" />
                        <input type="button" id="back" value="返回上一页" onclick="location.href='pl0002.do'" />
                        <input type="button" id="auth" value="授权" onclick="location.href='pl0004.do?role_id=${role_id.value}'" />
                    </td>
                </tr>
            </table>
        </form>
        <!-- InstanceEndEditable -->
    </body>
<!-- InstanceEnd --></html>
