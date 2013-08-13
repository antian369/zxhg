
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
        <title><%=SystemUtil.serverDesc%> -- 隐患收购删除</title>
        <!-- InstanceEndEditable -->
        <!-- InstanceBeginEditable name="head" -->
        <script type="text/javascript">
            var dep = '${sessionScope.ses.dep_id.value}';
        </script>
        <!-- InstanceEndEditable -->
    </head>
    <body>
        <!-- InstanceBeginEditable name="content" -->
        <div style="margin: 10px auto 10px auto; text-align: center"><h2>隐患收购删除</h2></div>
        <hr />
        <div id="container" style="width: 95%;margin: 10px auto 10px auto;" align="center">
            <table class="tablelist" id="yhsgs">
                <thead>
                    <tr>
                        <th width="10%" data-key="fxr">发现人</th>
                        <th width="30%" data-key="yhms">隐患描述</th>
                        <th width="14%" data-key="fxsj">发现时间</th>
                        <th width="14%" data-key="djsj">登记时间</th>
                        <th width="14%" data-key="zgsj">整改时间</th>
                        <th width="10%" data-key="zgr">整改人</th>
                        <th data-key="opt">操作</th>
                    </tr>
                </thead>
                <tbody>
                </tbody>
            </table>
        </div>

        <%-- 收购单细信息 --%>
        <div id="yhsg_dialog" title="隐患收购单细信息">
            <table class="tablelist" width="100%">
                <tr>
                    <td width="20%" style="background-color: #319ACF; color: white;">隐患收购信息：</td>
                    <td colspan="3"></td>
                </tr>
                <tr>
                    <td width="20%">发现人：</td>
                    <td width="30%" id="fxr"></td>
                    <td width="20%">发现单位：</td>
                    <td>
                        <select id="fxdw" name="fxdw" disabled="disabled">
                            <c:forEach items="${deps.value}" var="dep">
                                <option value="${dep.dep_id.value}">${dep.dep_name.value}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td width="20%">发现时间：</td>
                    <td width="30%" id="fxsj"></td>
                    <td width="20%">隐患单位：</td>
                    <td>
                        <select id="yhdw" name="yhdw" disabled="disabled">
                            <c:forEach items="${deps.value}" var="dep">
                                <option value="${dep.dep_id.value}">${dep.dep_name.value}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td width="20%">隐患地点：</td>
                    <td id="yhdd" colspan="3"></td>
                </tr>
                <tr>
                    <td width="20%">隐患描述：</td>
                    <td colspan="3"><textarea id="yhms" name="yhms" rows="5" cols="40" disabled="disabled"></textarea></td>
                </tr>
                <tr>
                    <td width="20%">登记人：</td>
                    <td width="30%" id="djrxm"></td>
                    <td width="20%">登记时间：</td>
                    <td id="djsj"></td>
                </tr>
                <tr>
                    <td width="20%" style="background-color: #319ACF; color: white;">隐患核实信息：</td>
                    <td colspan="3"></td>
                </tr>
                <tr>
                    <td width="20%">隐患分类：</td>
                    <td width="30%" id="yhfl_desc"></td>
                    <td width="20%">确认人：</td>
                    <td width="30%" id="qrrxm"></td>
                </tr>
                <tr>
                    <td width="20%">整改人：</td>
                    <td id="zgr"></td>
                    <td width="20%">整改时间：</td>
                    <td id="zgsj"></td>
                </tr>
                <tr>
                    <td width="20%">整改情况：</td>
                    <td colspan="3"><textarea id="zgqk" name="zgqk" rows="5" cols="40" disabled="disabled"></textarea></td>
                </tr>
                <tr>
                    <td width="20%" style="background-color: #319ACF; color: white;">隐患认定信息：</td>
                    <td colspan="3"></td>
                </tr>
                <tr>
                    <td width="20%">隐患级别：</td>
                    <td width="30%" id="yhjb_desc"></td>
                    <td width="20%">认定人：</td>
                    <td id="rdrxm"></td>
                </tr>
                <tr>
                    <td width="20%">认定备注：</td>
                    <td colspan="3"><textarea id="rdbz" name="rdbz" rows="5" cols="40" disabled="disabled"></textarea></td>
                </tr>
                <tr>
                    <td width="20%">隐患收购状态：</td>
                    <td id="zt_desc" colspan="3"></td>
                </tr>
            </table>
        </div>

        <%-- 认定 --%>
        <div id="yhrd_dialog" title="隐患收购认定">
            <form id="yhrd_form">
                <input type="hidden" id="yh_id" name="yh_id" />
                <table class="table_input" border="0" width="100%">
                    <tr>
                        <td width="30%" align="right">隐患级别：</td>
                        <td>
                            <select id="yhjb" name="yhjb">
                                <c:forEach items="${requestScope['aq_yh_yhsg.yhjb']}" var="par">
                                    <option value="${par.colValue}">${par.valueDesc}</option>
                                </c:forEach>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td width="30%" align="right">认定备注：</td>
                        <td>
                            <textarea id="rdbz" name="rdbz" rows="5" cols="40" fn="notNull('认定备注','#yhrd_form #rdbz')" ></textarea>
                            <span style="color: red;">*</span>
                        </td>
                    </tr>
                </table>
            </form>
        </div>
        <!-- InstanceEndEditable -->
    </body>
    <!-- InstanceEnd --></html>
