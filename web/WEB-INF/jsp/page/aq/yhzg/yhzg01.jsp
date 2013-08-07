
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
        <title><%=SystemUtil.serverDesc%> -- 隐患登记</title>
        <!-- InstanceEndEditable -->
        <!-- InstanceBeginEditable name="head" -->
        <script type="text/javascript">
            var zw = "${sessionScope.ses.zw1.value}";
        </script>
        <!-- InstanceEndEditable -->
    </head>
    <body>
        <!-- InstanceBeginEditable name="content" -->

        <div style="margin: 10px auto 10px auto; text-align: center"><h2>隐患登记</h2></div>
        <hr />
        <form id="data_form" name="data_form" class="sub_form" style="width: 80%;">
            <table width="100%" class="tablelist">
                <tr>
                    <td width="40%" align="right">隐患名称：</td>
                    <td width="60%">
                        <input type="text" id="yhmc" name="yhmc" size="40" fn="notNull('隐患名称','#data_form #yhmc')" />
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
                    <td width="60%">
                        <select id="yhdw" name="yhdw">
                            <c:forEach items="${deps.value}" var="dep">
                                <option value="${dep.dep_id.value}">${dep.dep_name.value}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td width="40%" align="right">隐患地点：</td>
                    <td width="60%">
                        <input type="text" id="yhdd" name="yhdd" fn="notNull('隐患地点','#data_form #yhdd')" size="60" />
                        <span style="color: red;">*</span>
                    </td>
                </tr>
                <tr>
                    <td width="40%" align="right">检查类型：</td>
                    <td width="60%">
                        <select id="ly" name="ly">
                            <c:forEach items="${requestScope['aq_yh_info.ly']}" var="par">
                                <option value="${par.colValue}">${par.valueDesc}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td width="40%" align="right">检查时间：</td>
                    <td width="60%">
                        <input type="text" id="jcsj" name="jcsj" data-role="date" fn="notNull('检查时间','#data_form #jcsj')" size="12"/>
                        <input type="text" id="jcsj_h" name="jcsj_h" value="0" fn="isNum('检查时间(时)','#data_form #jcsj_h')" size="1"/>时
                        <span style="color: red;">*</span>
                    </td>
                </tr>
                <tr>
                    <td width="40%" align="right">批准整改完成时间：</td>
                    <td width="60%">
                        <input type="text" id="pzsx" name="pzsx" fn="notNull('批准整改完成时间','#data_form #pzsx')" data-role="date" size="12"/>
                        <input type="text" id="pzsx_h" name="pzsx_h" value="0" fn="isNum('批准整改完成时间(时)','#data_form #pzsx_h')" size="1"/>时
                        <span style="color: red;">*</span>
                    </td>
                </tr>
                <tr>
                    <td colspan="2">
                        <div align="center">
                            <input type="button" id="sub" value=" 提交 " />
                            <input type="reset" value=" 重置 " />
                        </div>
                    </td>
                </tr>
            </table>
        </form>
        <!-- InstanceEndEditable -->
    </body>
    <!-- InstanceEnd --></html>
