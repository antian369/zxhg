
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
        <title><%=SystemUtil.serverDesc%> -- 三违登记</title>
        <!-- InstanceEndEditable -->
        <!-- InstanceBeginEditable name="head" -->
        <script type="text/javascript">
            var outJson = ${out_json};
        </script>
        <!-- InstanceEndEditable -->
    </head>
    <body>
        <!-- InstanceBeginEditable name="content" -->
        <div style="margin: 10px auto 10px auto; text-align: center"><h2>三违登记</h2></div>
        <form id="data_form" name="data_form" class="sub_form" style="width: 70%;">
            <table class="tablelist" width="100%">
                <tr>
                    <td width="40%" align="right">三违发现时间：</td>
                    <td>
                        <input type="text" id="swsj" name="swsj" fn="notNull('三违时间','#data_form #swsj')" style="width: 100px" data-role="date"/>
                        <input type="text" id='swsj_h' name="swsj_h" fn="isNum('三违时间(小时)', '#data_form #swsj_h')" maxlength="2" style="width: 20px" />时
                        <input type="text" id='swsj_m' name="swsj_m" fn="isNum('三违时间(分钟)', '#data_form #swsj_m')" maxlength="2" style="width: 20px" />分
                        <span style="color: red">*</span>
                    </td>
                </tr>
                <tr>
                    <td width="40%" align="right">三违地点：</td>
                    <td>
                        <input type="text" id="swdd" name="swdd" fn="notNull('三违地点','#data_form #swdd')" size="40" />
                        <span style="color: red">*</span>
                    </td>
                </tr>
                <tr>
                    <td width="40%" align="right">三违单位：</td>
                    <td>
                        <select id="ssdw" name="ssdw">
                            <c:forEach items="${deps.value}" var="dep">
                                <option value="${dep.dep_id.value}">${dep.dep_name.value}</option>
                            </c:forEach>
                        </select>
                        <span style="color: red">*</span>
                    </td>
                </tr>
                <tr>
                    <td width="40%" align="right">三违人员：</td>
                    <td>
                        <input type="text" id="swry" name="swry" fn="notNull('三违人员','#data_form #swry')" />
                        <span style="color: red">*</span>
                    </td>
                </tr>
                <tr>
                    <td width="40%" align="right">三违现象：</td>
                    <td>
                        <textarea id="swxx" name="swxx" fn="notNull('三违现象','#data_form #swxx')" cols="30" rows="5"></textarea>
                        <span style="color: red">*</span>
                    </td>
                </tr>
                <tr>
                    <td width="40%" align="right">三违分类：</td>
                    <td>
                        <select id="swfl" name="swfl">
                            <c:forEach items="${requestScope['aq_sw_info.swfl']}" var="par">
                                <option value="${par.colValue}">${par.valueDesc}</option>
                            </c:forEach>
                        </select>
                        <span style="color: red">*</span>
                    </td>
                </tr>
                <tr>
                    <td width="40%" align="right">三违备注：</td>
                    <td><input type="text" id="swbz" name="swbz" size="40" /></td>
                </tr>
                <tr>
                    <td width="40%" align="right">三违核实：</td>
                    <td>
                        <select id="hslx" name="hslx">
                            <c:forEach items="${requestScope['aq_sw_info.hslx']}" var="par">
                                <option value="${par.colValue}">${par.valueDesc}</option>
                            </c:forEach>
                        </select>
                        <span style="color: red">*</span>
                    </td>
                </tr>
                <tr>
                    <td width="40%" align="right">发现人姓名：</td>
                    <td>
                        <input type="text" id="fxrxm" name="fxrxm" value="${sessionScope.ses.name.value}" fn="notNull('发现人姓名', '#data_form #fxrxm')" />
                        <span style="color: red">*</span>
                    </td>
                </tr>
                <tr>
                    <td width="40%" align="right">发现人所在部门：</td>
                    <td>
                        <select id="fxrbm" name="fxrbm">
                            <option value="${sessionScope.ses.dep_id.value}" selected="selected">${sessionScope.ses.dep_name.value}</option>
                            <option value="">其它</option>
                            <c:forEach items="${deps.value}" var="dep">
                                <option value="${dep.dep_id.value}">${dep.dep_name.value}</option>
                            </c:forEach>
                        </select>
                        <span style="color: red">*</span>
                    </td>
                </tr>
                <tr>
                    <td colspan="2" align="center">&nbsp;</td>
                </tr>
                <tr>
                    <td colspan="2" align="center">
                        <input type="button" id='sub' value="提交" />
                        <input type="reset" value="重置" />
                    </td>
                </tr>
            </table>
            <input type="hidden" id="fxr" name="fxr" value="${sessionScope.ses.username.value}" />
        </form>
        <!-- InstanceEndEditable -->
    </body>
    <!-- InstanceEnd --></html>
