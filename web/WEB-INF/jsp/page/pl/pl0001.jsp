
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
        <title><%=SystemUtil.serverDesc%> -- 部门管理</title>
        <!-- InstanceEndEditable -->
        <!-- InstanceBeginEditable name="head" -->
        <!-- InstanceEndEditable -->
    </head>
    <body>
        <!-- InstanceBeginEditable name="content" -->
        <div style="margin: 10px auto 10px auto; text-align: center"><h2>部门管理</h2></div>
        <form id="data_form" name="data_form" class="sub_form" style="width: 80%;">
            <table width="100%" border="0">
                <tr>
                    <td width="35%">
                        部门名称：<input type="text" id="dep_name" name="dep_name" fn="notNull('部门名称', '#dep_name')" size="16"/>
                        <span style="color: red">*</span>
                    </td>
                    <td width="25%">
                        负责人：<input type="text" id="dep_fzr" name="dep_fzr" size="8" />
                    </td>
                    <td width="25%">
                        部门类型：
                        <select id="dep_type" name="dep_type">
                            <c:forEach items="${requestScope['pl_dep_info.dep_type']}" var="type">
                                <option value="${type.colValue}">${type.valueDesc}</option>
                            </c:forEach>
                        </select>
                    </td>
                    <td>
                        <input type="button" id="sub" value="新增" />
                    </td>
                </tr>
            </table>
        </form>
        <hr />
        <div style="width: 90%;margin: 10px auto 10px auto;">
            <table id="dep_list" cellpadding="0" cellspacing="0" border="0" class="display">
                <thead>
                    <tr>
                        <th>部门</th>
                        <th>负责人</th>
                        <th>部门类型</th>
                        <th>修改</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${deps.value}" var="dep">
                        <tr>
                            <td align="center"><a href="#this" dep_id="${dep.dep_id.value}" class="update_dep">${dep.dep_name.value}</a></td>
                            <td align="center">${dep.dep_fzr.value}</td>
                            <td align="center">${dep.dep_type_desc.value}</td>
                            <td align="center"><a href="#this" dep_id="${dep.dep_id.value}" class="update_dep">修改</a></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
        <%-- 弹出窗口 --%>
        <div id="update_dep_dialog" title="修改部门信息">
            <form id="update_dep_form">
                <input type="hidden" id="dep_id" name="dep_id" />
                <table class="table_input" width="100%">
                    <tr>
                        <td width="40%" align="right">部门名称：</td>
                        <td>
                            <input type="text" id="dep_name" name="dep_name" fn="notNull('部门名称','#update_dep_form #dep_name')" />
                            <span style="color: red">*</span>
                        </td>
                    </tr>
                    <tr>
                        <td width="40%" align="right">负责人：</td>
                        <td>
                            <input type="text" id="dep_fzr" name="dep_fzr" />
                        </td>
                    </tr>
                    <tr>
                        <td width="40%" align="right">部门类型：</td>
                        <td>
                            <select id="dep_type" name="dep_type">
                                <c:forEach items="${requestScope['pl_dep_info.dep_type']}" var="type">
                                    <option value="${type.colValue}">${type.valueDesc}</option>
                                </c:forEach>
                            </select>
                        </td>
                    </tr>
                </table>
            </form>
        </div>
        <!-- InstanceEndEditable -->
    </body>
<!-- InstanceEnd --></html>
