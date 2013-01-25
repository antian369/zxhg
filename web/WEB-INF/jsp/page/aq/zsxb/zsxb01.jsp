
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
        <title><%=SystemUtil.serverDesc%> -- 自述旬报登记</title>
        <!-- InstanceEndEditable -->
        <!-- InstanceBeginEditable name="head" -->
        <script type="text/javascript">
            var zw = "${sessionScope.ses.zw1.value}";
        </script>
        <!-- InstanceEndEditable -->
    </head>
    <body>
        <!-- InstanceBeginEditable name="content" -->

        <div style="margin: 10px auto 10px auto; text-align: center"><h2>自述旬报登记</h2></div>
        <hr />
        <form id="data_form" name="data_form" class="sub_form" style="width: 80%;">
            <table width="100%" class="table_input" border="0">
                <tr>
                    <td width="40%" align="right">填报人姓名：</td>
                    <td width="60%">
                        <input type="hidden" id="tbr" name="tbr" value="${sessionScope.ses.username.value}" />
                        <input type="text" id="tbrxm" name="tbrxm" fn="isChinese('填报人姓名',2,'#data_form #tbrxm')" value="${sessionScope.ses.name.value}"/>
                        <span style="color: red;">*</span>
                    </td>
                </tr>
                <tr>
                    <td width="40%" align="right">填报单位：</td>
                    <td width="60%">${sessionScope.ses.dep_name.value}</td>
                </tr>
                <tr>
                    <td width="40%" align="right">填报人职务：</td>
                    <td width="60%">
                        <select id="zw" name="zw">
                            <c:forEach items="${requestScope['pl_user.zw']}" var="par">
                                <option value="${par.colValue}">${par.valueDesc}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td width="40%" align="right">分管主要工作：</td>
                    <td width="60%">
                        <input type="text" id="fggz" name="fggz" fn="notNull('分管主要工作','#data_form #fggz')" />
                        <span style="color: red;">*</span>
                    </td>
                </tr>
                <tr>
                    <td width="40%" align="right">隐患名称：</td>
                    <td width="60%">
                        <input type="text" id="yhmc" name="yhmc" fn="notNull('隐患名称','#data_form #yhmc')" />
                        <span style="color: red;">*</span>
                    </td>
                </tr>
                <tr>
                    <td width="40%" align="right">隐患类别：</td>
                    <td width="60%">
                        <select id="yhlb" name="yhlb">
                            <c:forEach items="${requestScope['aq_yh_info.yhlb']}" var="par">
                                <option value="${par.colValue}">${par.valueDesc}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td width="40%" align="right">隐患描述：</td>
                    <td width="60%">
                        <textarea id="yhms" name="yhms" rows="5" cols="40" fn="notNull('隐患描述','#data_form #yhms')" ></textarea>
                        <span style="color: red;">*</span>
                    </td>
                </tr>
                <tr>
                    <td width="40%" align="right">隐患级别：</td>
                    <td width="60%">
                        <select id="yhjb" name="yhjb">
                            <c:forEach items="${requestScope['aq_yh_info.yhjb']}" var="par">
                                <option value="${par.colValue}">${par.valueDesc}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td width="40%" align="right">隐患单位：</td>
                    <td width="60%">${sessionScope.ses.dep_name.value}</td>
                </tr>
                <tr>
                    <td width="40%" align="right">隐患地点：</td>
                    <td width="60%">
                        <input type="text" id="yhdd" name="yhdd" fn="notNull('隐患地点','#data_form #yhdd')" size="60" />
                        <span style="color: red;">*</span>
                    </td>
                </tr>
                <tr>
                    <td width="40%" align="right">整改人姓名：</td>
                    <td width="60%">
                        <input type="hidden" id="zgr" name="zgr" value="${sessionScope.ses.username.value}" />
                        <input type="text" id="zgrxm" name="zgrxm" fn="isChinese('整改人姓名',2,'#data_form #zgrxm')" value="${sessionScope.ses.name.value}"/>
                        <span style="color: red;">*</span>
                    </td>
                </tr>
                <tr>
                    <td width="40%" align="right">整改措施：</td>
                    <td width="60%">
                        <textarea id="zgcs" name="zgcs" rows="5" cols="40" fn="notNull('整改措施','#data_form #zgcs')" ></textarea>
                        <span style="color: red;">*</span>
                    </td>
                </tr>
                <tr>
                    <td width="40%" align="right">预计整改完成时间：</td>
                    <td width="60%">
                        <input type="text" id="sqsx" name="sqsx" fn="notNull('预计整改完成时间','#data_form #sqsx')" data-role="date" size="12"/>
                        <input type="text" id="sqsx_h" name="sqsx_h" value="0" fn="isNum('预计整改完成时间(时)','#data_form #sqsx_h')" size="3"/>时
                        <span style="color: red;">*</span>
                    </td>
                </tr>
                <tr>
                    <td width="40%" align="right">整改备注：</td>
                    <td width="60%">
                        <input type="text" id="zgbz" name="zgbz" size="60" />
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
