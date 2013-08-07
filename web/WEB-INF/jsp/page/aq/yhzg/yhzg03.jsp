
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
        <title><%=SystemUtil.serverDesc%> -- 整改延时申请</title>
        <!-- InstanceEndEditable -->
        <!-- InstanceBeginEditable name="head" -->
        <script type="text/javascript">
            var dep = '${sessionScope.ses.dep_id.value}';
        </script>
        <!-- InstanceEndEditable -->
    </head>
    <body>
        <!-- InstanceBeginEditable name="content" -->
        <div style="margin: 10px auto 10px auto; text-align: center"><h2>整改延时申请</h2></div>
        <hr />
        <div id="container" style="width: 95%;margin: 10px auto 10px auto;" align="center">
            <table id="yhs" class="tablelist">
                <thead>
                    <tr>
                        <th width="35%" data-key="yhmc">隐患名称</th>
                        <th width="10%" data-key="yhlb_desc">隐患类别</th>
                        <th width="10%" data-key="ly_desc">检查类型</th>
                        <th width="10%" data-key="yhjb_desc">隐患级别</th>
                        <th width="15%" data-key="jcsj">检查时间</th>
                        <th width="10%" data-key="zt_desc">状态</th>
                        <th data-key="opt">详细信息</th>
                    </tr>
                </thead>
                <tbody>
                </tbody>
            </table>
        </div>

        <%-- 隐患详细信息 --%>
        <div id="yh_dialog" title="隐患详细信息">
            <input type="hidden" id="yh_id" name="yh_id" />
            <table class="tablelist" width="100%">
                <tr>
                    <td width="20%" style="background-color: #319ACF; color: white;">隐患信息：</td>
                    <td colspan="3"></td>
                </tr>
                <tr>
                    <td width="20%">隐患名称：</td>
                    <td id="yhmc" colspan="3"></td>
                </tr>
                <tr>
                    <td width="20%">隐患描述：</td>
                    <td colspan="3"><textarea id="yhms" name="yhms" rows="5" cols="40" disabled="disabled"></textarea></td>
                </tr>
                <tr>
                    <td width="20%">隐患地点：</td>
                    <td id="yhdd" colspan="3"></td>
                </tr>
                <tr>
                    <td width="20%">隐患类别：</td>
                    <td width="30%" id="yhlb_desc"></td>
                    <td width="20%">隐患级别：</td>
                    <td id="yhjb_desc"></td>
                </tr>
                <tr>
                    <td width="20%">隐患单位：</td>
                    <td width="30%">
                        <select id="yhdw" name="yhdw" disabled="disabled">
                            <c:forEach items="${deps.value}" var="dep">
                                <option value="${dep.dep_id.value}">${dep.dep_name.value}</option>
                            </c:forEach>
                        </select>
                    </td>
                    <td width="20%">检查类型：</td>
                    <td id="ly_desc"></td>
                </tr>
                <tr>
                    <td width="20%">隐患状态：</td>
                    <td width="30%" id="zt_desc"></td>
                    <td width="20%">检查时间：</td>
                    <td width="30%" id="jcsj"></td>
                </tr>
                <tr>
                    <td width="20%" style="background-color: #319ACF; color: white;">整改记录：</td>
                    <td colspan="3"></td>
                </tr>
                <tr>
                    <td colspan="4" align="center">
                        <table id="zgs" class="tablelist" width="95%">
                            <thead>
                                <tr>
                                    <th width="25%" data-key="kssj">开始时间</th>
                                    <th width="25%" data-key="pzsx">批准时限</th>
                                    <th width="10%" data-key="lazy_zt_desc">延时</th>
                                    <th width="15%" data-key="yszt_desc">验收</th>
                                    <th width="15%" data-key="ysrxm">验收人</th>
                                    <th data-key="opt">详细</th>
                                </tr>
                            </thead>
                            <tbody></tbody>
                        </table>
                    </td>
                </tr>
            </table>
        </div>
        <div id="zg_dialog" title="整改详细信息">
            <table class="tablelist" width="100%">
                <tr>
                    <td width="20%" style="background-color: #319ACF; color: white;">整改信息：</td>
                    <td colspan="3"></td>
                </tr>
                <tr>
                    <td width="20%">开始时间：</td>
                    <td width="30%" id="kssj"></td>
                    <td width="20%">批准时限：</td>
                    <td id="pzsx"></td>
                </tr>
                <tr>
                    <td width="20%">延时状态：</td>
                    <td width="30%" id="lazy_zt_desc"></td>
                    <td width="20%">验收状态：</td>
                    <td id="yszt_desc"></td>
                </tr>
                <tr>
                    <td width="20%">整改备注：</td>
                    <td colspan="3"><textarea id="zgbz" name="zgbz" rows="5" cols="40" disabled="disabled"></textarea></td>
                </tr>
                <tr>
                    <td width="20%">验收人：</td>
                    <td width="30%" id="ysrxm"></td>
                    <td width="20%">验收时间：</td>
                    <td id="yssj"></td>
                </tr>
                <tr>
                    <td width="20%">验收备注：</td>
                    <td colspan="3"><textarea id="ysbz" name="zgbz" rows="5" cols="40" disabled="disabled"></textarea></td>
                </tr>
            </table>
        </div>

        <%-- 隐患验收申请 --%>
        <div id="zgbz_dialog" title="隐患延时申请">
            <table width="100%" class="tablelist" style="margin: 10px auto">
                <tr>
                    <td width="40%" align="right">申请备注：</td>
                    <td><textarea id="zgbz" name="zgbz" rows="5" cols="40"></textarea></td>
                </tr>
            </table>
        </div>
        <!-- InstanceEndEditable -->
    </body>
    <!-- InstanceEnd --></html>
