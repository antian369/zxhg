
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
        <title><%=SystemUtil.serverDesc%> -- 角色管理</title>
        <!-- InstanceEndEditable -->
        <!-- InstanceBeginEditable name="head" -->
        <!-- InstanceEndEditable -->
    </head>
    <body>
        <!-- InstanceBeginEditable name="content" -->
        <div style="margin: 10px auto 10px auto; text-align: center"><h2>角色管理</h2></div>
        <hr />
        <div style="width: 90%;margin: 10px auto 10px auto;">
            <table cellpadding="0" cellspacing="0" border="0" class="display" id="role_list">
                <thead>
                    <tr>
                        <th>角色名称</th>
                        <th>所在部门</th>
                        <th>创建人</th>
                        <th>创建时间</th>
                        <th>备注</th>
                        <th>修改</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${roles.value}" var="role">
                        <tr>
                            <td align="center"><a href="pl0002_update.do?role_id=${role.role_id.value}">${role.role_name.value}</a></td>
                            <td align="center">${role.dep_name.value}</td>
                            <td align="center">${role.create_user.value}</td>
                            <td align="center">${role.create_time.value}</td>
                            <td align="center">${role.bz.value}</td>
                            <td align="center"><a href="pl0002_update.do?role_id=${role.role_id.value}">修改</a></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
        <hr />
        <br />
        <p align="center">
            <input type="button" id="add" value="创建角色" onclick="location.href='pl0002_add.do'" />
        </p>
        <!-- InstanceEndEditable -->
    </body>
<!-- InstanceEnd --></html>
