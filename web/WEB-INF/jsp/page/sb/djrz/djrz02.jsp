
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
        <title><%=SystemUtil.serverDesc%> -- 设备点检日志上报</title>
        <!-- InstanceEndEditable -->
        <!-- InstanceBeginEditable name="head" -->
        <script type="text/javascript">
            var dep = '${sessionScope.ses.dep_id.value}';
        </script>
        <!-- InstanceEndEditable -->
    </head>
    <body>
        <!-- InstanceBeginEditable name="content" -->
        <div style="margin: 10px auto 10px auto; text-align: center"><h2>设备点检日志上报</h2></div>
        <hr />
        <form id="data_form" name="data_form" class="sub_form" style="width: 80%;">
            <table width="100%" class="table_input" border="0">
                <tr>
                    <td width="40%" align="right">点检班级：</td>
                    <td>
                        <select id="bjmc" name="bjmc">
                            <option>001:班级1</option>
                            <option>002:班级2</option>
                            <option>003:班级3</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td width="40%" align="right">点检日期：</td>
                    <td>
                        <input type="text" id="djrq" name="djrq" data-role="date" fn="notNull('点检日期', '#data_form #djrq')" />
                        <span style="color:red">*</span>
                    </td>
                </tr>
                <tr>
                    <td width="40%" align="right">有无异常：</td>
                    <td>
                        <select id="djyc" name="djyc">
                            <option>无异常</option>
                            <option>有异常</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td width="40%" align="right">点检情况：</td>
                    <td>
                        <textarea id="djqk" name="djqk" rows="5" cols="40" fn="notNull('点检情况','#data_form #djqk')" ></textarea>
                        <span style="color:red">*</span>
                    </td>
                </tr>
                <tr>
                    <td width="40%" align="right">检修情况：</td>
                    <td>
                        <textarea id="jxqk" name="jxqk" rows="5" cols="40" fn="notNull('检修情况','#data_form #jxqk')" ></textarea>
                        <span style="color:red">*</span>
                    </td>
                </tr>
                <tr>
                    <td width="40%" align="right">设备启停情况：</td>
                    <td>
                        <textarea id="jxqk" name="jxqk" rows="5" cols="40" fn="notNull('设备启停情况','#data_form #jxqk')" ></textarea>
                        <span style="color:red">*</span>
                    </td>
                </tr>
                <tr>
                    <td width="40%" align="right">备品消耗：</td>
                    <td>
                        <textarea id="bpxh" name="bpxh" rows="5" cols="40" fn="notNull('备品消耗','#data_form #bpxh')" ></textarea>
                        <span style="color:red">*</span>
                    </td>
                </tr>
                <tr>
                    <td width="40%" align="right">遗留问题：</td>
                    <td>
                        <textarea id="ylwt" name="ylwt" rows="5" cols="40" fn="notNull('遗留问题','#data_form #ylwt')" ></textarea>
                        <span style="color:red">*</span>
                    </td>
                </tr>
                <tr>
                    <td width="40%" align="right">分析与防范：</td>
                    <td>
                        <textarea id="fxff" name="fxff" rows="5" cols="40" fn="notNull('分析与防范','#data_form #fxff')" ></textarea>
                        <span style="color:red">*</span>
                    </td>
                </tr>
                <tr>
                    <td width="40%" align="right">点检员：</td>
                    <td>
                        <input type="text" id="djy" name="djy" fn="notNull('点检员', '#data_form #djy')" value="${sessionScope.ses.name.value}"/>
                        <span style="color:red">*</span>
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
