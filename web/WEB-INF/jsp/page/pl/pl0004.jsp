
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
        <title><%=SystemUtil.serverDesc%> -- 角色权限管理</title>
        <!-- InstanceEndEditable -->
        <!-- InstanceBeginEditable name="head" -->
        <script type="text/javascript" src="<c:url value='/script/jstree/jquery.jstree.js' />"></script>
        <script type="text/javascript">var roleId = "${param.role_id}"</script>
        <!-- InstanceEndEditable -->
    </head>
    <body>
        <!-- InstanceBeginEditable name="content" -->
        <div style="margin: 10px auto 10px auto; text-align: center"><h2>角色权限管理</h2></div>
        <hr />
        <form id="search_form" name="search_form" class="sub_form" style="width: 80%;">
            <p align="center">请选择角色：
                <select id="role_id" name="role_id">
                    <c:forEach items="${roles.value}" var="role">
                        <option value="${role.role_id.value}">${role.role_name.value}</option>
                    </c:forEach>
                </select>
                <input type="button" id="search" value="查询" />
            </p>
        </form>
        <br />
        <div id="auth_div">
            <div id="menu_div" style="margin:10px 35% 10px auto; width: 30%;">
                <ul id="menu_checkbox"></ul>
            </div>
            <br />
            <hr />
            <p align="center">
                <input type="button" id="save" value="保存" />
                <input type="button" id="all_check" value="全选" />
                <input type="button" id="all_uncheck" value="取消全选" />
            </p>
        </div>
        <!-- InstanceEndEditable -->
    </body>
<!-- InstanceEnd --></html>
