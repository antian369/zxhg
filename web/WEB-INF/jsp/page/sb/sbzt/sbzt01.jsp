
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
        <title><%=SystemUtil.serverDesc%> -- 设备总目录</title>
        <!-- InstanceEndEditable -->
        <!-- InstanceBeginEditable name="head" -->
        <script type="text/javascript">
        </script>
        <!-- InstanceEndEditable -->
    </head>
    <body>
        <!-- InstanceBeginEditable name="content" -->
        <div style="margin: 10px auto 10px auto; text-align: center"><h2>设备总目录</h2></div>
        <hr />
        <form action="sbzt01.do" style="width: 90%;" class="sub_form">
            <div id="sbfc" align="center">
                <input type="radio" id="sbfc1" name="sbfc" /><label for="sbfc1">备煤</label>
                <input type="radio" id="sbfc2" name="sbfc" /><label for="sbfc2">热电</label>
                <input type="radio" id="sbfc3" name="sbfc" /><label for="sbfc3">气化</label>
                <input type="radio" id="sbfc4" name="sbfc" /><label for="sbfc4">甲醇</label>
                <input type="radio" id="sbfc5" name="sbfc" /><label for="sbfc5">仪表</label>
                <input type="radio" id="sbfc6" name="sbfc" /><label for="sbfc6">质检</label>
            </div>
            <br />
            <hr />
            <br />
            <table width="90%" align="center" id="content">
                <tr>
                    <td width="25%" style="vertical-align:top" align="center">
                        <div id="content_left">
                        </div>
                    </td>
                    <td width="73%">
                        <div id="content_right" style="border-left: solid 2px black">
                            <table width="100%" class="table_input" border="0">
                                <tr>
                                    <td width="30%" align="right">工艺流程：</td>
                                    <td id="gymc">1</td>
                                </tr>
                                <tr>
                                    <td width="30%" align="right">设备位号：</td>
                                    <td id="sbwh"></td>
                                </tr>
                                <tr>
                                    <td width="30%" align="right">位号描述：</td>
                                    <td id="whms"></td>
                                </tr>
                                <tr>
                                    <td width="30%" align="right">设备名称：</td>
                                    <td id="sbmc"></td>
                                </tr>
                                <tr>
                                    <td width="30%" align="right">出厂编号：</td>
                                    <td id="scbh"></td>
                                </tr>
                                <tr>
                                    <td width="30%" align="right">型号规格：</td>
                                    <td id="xhgg"></td>
                                </tr>
                                <tr>
                                    <td width="30%" align="right">结构类型：</td>
                                    <td id="jglx"></td>
                                </tr>
                                <tr>
                                    <td width="30%" align="right">设备重量：</td>
                                    <td id="sbzl"></td>
                                </tr>
                                <tr>
                                    <td width="30%" align="right">介质成份：</td>
                                    <td id="jzcf"></td>
                                </tr>
                                <tr>
                                    <td width="30%" align="right">生产厂家：</td>
                                    <td id="sccj"></td>
                                </tr>
                                <tr>
                                    <td width="30%" align="right">生产日期：</td>
                                    <td id="scrq"></td>
                                </tr>
                                <tr>
                                    <td width="30%" align="right">安装日期：</td>
                                    <td id="azrq"></td>
                                </tr>
                                <tr>
                                    <td width="30%" align="right">投用日期：</td>
                                    <td id="tyrq"></td>
                                </tr>
                                <tr>
                                    <td width="30%" align="right">设备分类：</td>
                                    <td id="sbfl"></td>
                                </tr>
                                <tr>
                                    <td width="30%" align="right">技术参数：</td>
                                    <td id="jscs"></td>
                                </tr>
                                <tr>
                                    <td width="30%" align="right">录入人：</td>
                                    <td id="lrrxm"></td>
                                </tr>
                                <tr>
                                    <td width="30%" align="right">录入时间：</td>
                                    <td id="lrsj"></td>
                                </tr>
                            </table>
                        </div>
                    </td>
                </tr>
            </table>
        </form>
        <!-- InstanceEndEditable -->
    </body>
    <!-- InstanceEnd --></html>
