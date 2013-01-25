
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
        <title><%=SystemUtil.serverDesc%> -- 事故登记</title>
        <!-- InstanceEndEditable -->
        <!-- InstanceBeginEditable name="head" -->
        <!-- InstanceEndEditable -->
    </head>
    <body>
        <!-- InstanceBeginEditable name="content" -->
        <div style="margin: 10px auto 10px auto; text-align: center"><h2>事故登记</h2></div>
        <form id="data_form" name="data_form" class="sub_form" style="width: 60%;">
            <table width="100%" border="0" class="table_input">
                <tr>
                    <td align="right" width="40%">事故单位：</td>
                    <td>
                        <select id="sgdw" name="sgdw">
                            <c:forEach items="${deps.value}" var="dep">
                                <option value="${dep.dep_id.value}">${dep.dep_name.value}</option>
                            </c:forEach>
                        </select>
                        <span style="color: red">*</span>
                    </td>
                </tr>
                <tr>
                    <td align="right">事故发生地点：</td>
                    <td>
                        <input type="text" id="sgdd" name="sgdd" fn="notNull('事故发生地点', '#data_form #sgdd')"/>
                        <span style="color: red">*</span>
                    </td>
                </tr>
                <tr>
                    <td align="right">事故时间：</td>
                    <td>
                        <input type="text" id="sgsj" name="sgsj" fn="notNull('事故时间', '#data_form #sgsj')" data-role="date" />
                        <span style="color: red">*</span>
                    </td>
                </tr>
                <tr>
                    <td align="right">事故类别：</td>
                    <td>
                        <select id="sglb" name="sglb">
                            <c:forEach items="${requestScope['aq_sg_info.sglb']}" var="lb">
                                <option value="${lb.colValue}">${lb.valueDesc}</option>
                            </c:forEach>
                        </select>
                        <span style="color: red">*</span>
                    </td>
                </tr>
                <tr>
                    <td align="right">事故级别：</td>
                    <td>
                        <select id="sgjb" name="sgjb">
                            <c:forEach items="${requestScope['aq_sg_info.sgjb']}" var="jb">
                                <option value="${jb.colValue}">${jb.valueDesc}</option>
                            </c:forEach>
                        </select>
                        <span style="color: red">*</span>
                    </td>
                </tr>
                <tr>
                    <td align="right">事故性质：</td>
                    <td>
                        <select id="sgxz" name="sgxz">
                            <c:forEach items="${requestScope['aq_sg_info.sgxz']}" var="jb">
                                <option value="${jb.colValue}">${jb.valueDesc}</option>
                            </c:forEach>
                        </select>
                        <span style="color: red">*</span>
                    </td>
                </tr>
                <tr>
                    <td align="right">直接经济损失（万元）：</td>
                    <td>
                        <input type="text" id="zjss" name="zjss" fn="isNum('直接经济损失', '#data_form #zjss')" />
                        <span style="color: red">*</span>
                    </td>
                </tr>
                <tr>
                    <td align="right">间接经济损失（万元）：</td>
                    <td>
                        <input type="text" id="jjss" name="jjss" fn="isNum('间接经济损失', '#data_form #jjss')" />
                        <span style="color: red">*</span>
                    </td>
                </tr>
                <tr>
                    <td align="right">主要危化品：</td>
                    <td>
                        <textarea id="zywhp" name="zywhp" fn="notNull('主要危化品','#data_form #zywhp')" cols="30" rows="5"></textarea>
                        <span style="color: red">*</span>
                    </td>
                </tr>
                <tr>
                    <td align="right">死亡人数：</td>
                    <td>
                        <input type="text" id="swrs" name="swrs" fn="isNum('死亡人数', '#data_form #swrs')" value="0" />
                        <span style="color: red">*</span>
                    </td>
                </tr>
                <tr>
                    <td align="right">重伤人数：</td>
                    <td>
                        <input type="text" id="zsrs" name="zsrs" fn="isNum('重伤人数', '#data_form #zsrs')" value="0" />
                        <span style="color: red">*</span>
                    </td>
                </tr>
                <tr>
                    <td align="right">疏散人数：</td>
                    <td>
                        <input type="text" id="ssrs" name="ssrs" fn="isNum('疏散人数', '#data_form #ssrs')" value="0" />
                        <span style="color: red">*</span>
                    </td>
                </tr>
                <tr>
                    <td align="right">中毒人数：</td>
                    <td>
                        <input type="text" id="zdrs" name="zdrs" fn="isNum('中毒人数', '#data_form #zdrs')" value="0" />
                        <span style="color: red">*</span>
                    </td>
                </tr>
                <tr>
                    <td align="right">入院人数：</td>
                    <td>
                        <input type="text" id="ryrs" name="ryrs" fn="isNum('入院人数', '#data_form #ryrs')" value="0" />
                        <span style="color: red">*</span>
                    </td>
                </tr>
                <tr>
                    <td align="right">失踪人数：</td>
                    <td>
                        <input type="text" id="szrs" name="szrs" fn="isNum('失踪人数', '#data_form #ryrs')" value="0" />
                        <span style="color: red">*</span>
                    </td>
                </tr>
                <tr>
                    <td align="right">备注：</td>
                    <td><input type="text" id="sgbz" name="sgbz" /></td>
                </tr>
                <tr><td colspan="2"><hr /></td></tr>
                <tr>
                    <td align="center" colspan="2">
                        <input type="button" id="sub" value="保存" />
                        <input type="reset" id="reset_form" value="重置" />
                    </td>
                </tr>
            </table>
        </form>
        <!-- InstanceEndEditable -->
    </body>
    <!-- InstanceEnd --></html>
