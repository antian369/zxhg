
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
        <title><%=SystemUtil.serverDesc%> -- 隐患收购登记单</title>
        <!-- InstanceEndEditable -->
        <!-- InstanceBeginEditable name="head" -->
        <script type="text/javascript">
            var dep = "${sessionScope.ses.dep_id.value}";
        </script>
        <!-- InstanceEndEditable -->
    </head>
    <body>
        <!-- InstanceBeginEditable name="content" -->
        <div style="margin: 10px auto 10px auto; text-align: center"><h2>隐患收购登记单</h2></div>
        <hr />
        <form id="data_form" name="data_form" class="sub_form" style="width: 80%;">
            <table width="100%" class="table_input" border="0">
                <tr>
                    <td width="40%" align="right">发现人：</td>
                    <td width="60%">
                        <input type="hidden" id="fxr" name="fxr" value="${sessionScope.ses.username.value}" />
                        <input type="text" id="fxrxm" name="fxrxm" fn="isChinese('发现人姓名',2,'#data_form #fxrxm')" value="${sessionScope.ses.name.value}"/>
                        <span style="color: red;">*</span>
                    </td>
                </tr>
                <tr>
                    <td width="40%" align="right">发现单位：</td>
                    <td width="60%">
                        <select id="fxdw" name="fxdw">
                            <c:forEach items="${deps.value}" var="dep">
                                <option value="${dep.dep_id.value}">${dep.dep_name.value}</option>
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
