
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
        <title><%=SystemUtil.serverDesc%> -- 修改密码</title>
        <!-- InstanceEndEditable -->
        <!-- InstanceBeginEditable name="head" -->
        <!-- InstanceEndEditable -->
    </head>
    <body>
        <!-- InstanceBeginEditable name="content" -->
        <div style="margin: 10px auto 10px auto; text-align: center"><h2>修改密码</h2></div>
        <hr />
        <div style="margin: 15px auto; width: 600px;">
            <form id="data_form" class="sub_form">
                <table width="600" border="0" align="center" class="table_input">
                    <tr>
                        <td width="226" align="right">原密码：</td>
                        <td width="364"><input type="password" id="old_password" name="old_password" fn="password('原密码','#old_password')" /></td>
                    </tr>
                    <tr>
                        <td width="226" align="right">请输入新密码：</td>
                        <td width="364"><input type="password" id="new_password" name="new_password" fn="password('新密码','#new_password')"/></td>
                    </tr>
                    <tr>
                        <td width="226" align="right">请再次输入：</td>
                        <td width="364"><input type="password" id="again_password" name="again_password" /></td>
                    </tr>
                    <tr>
                        <td colspan="2" align="center">
                            <input type="button" id="sub" value=" 提交 " class="button" />
                            <input type="reset" id="reset" value=" 重置 " class="button" />
                        </td>
                    </tr>
                </table>
            </form>
        </div>
        <!-- InstanceEndEditable -->
    </body>
<!-- InstanceEnd --></html>
