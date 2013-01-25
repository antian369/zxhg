
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
        <title><%=SystemUtil.serverDesc%> -- 设备事故记录录入</title>
        <!-- InstanceEndEditable -->
        <!-- InstanceBeginEditable name="head" -->
        <script type="text/javascript" src="<c:url value='/script/kindeditor/kindeditor-min.js' />"></script>
        <script type="text/javascript">
            var dep = '${sessionScope.ses.dep_id.value}';
            var editor;
            $(function(){
                KindEditor.ready(function(K) {
                    editor = K.create('#sgnr', {
                        resizeType : 1,
                        cssPath : BaseUrl + 'script/kindeditor/plugins/code/prettify.css',
                        uploadJson : BaseUrl + '_page/frame/upload_pic.do',
                        allowPreviewEmoticons : false,
                        items : [
                            'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold', 'italic', 'underline',
                            'removeformat', '|', 'justifyleft', 'justifycenter', 'justifyright', 'insertorderedlist',
                            'insertunorderedlist', '|', 'image', 'link']
                    });
                });
            });
        </script>
        <!-- InstanceEndEditable -->
    </head>
    <body>
        <!-- InstanceBeginEditable name="content" -->
        <div style="margin: 10px auto 10px auto; text-align: center"><h2>设备事故记录录入</h2></div>
        <hr />
        <form id="data_form" name="data_form" class="sub_form" style="width: 80%;">
            <table width="100%" class="table_input" border="0">
                <tr>
                    <td width="20%" align="right">工艺流程：</td>
                    <td>
                        <select id="gybh" name="gybh">
                            <option>请选择...</option>
                            <option>#001:煤化工工艺</option>
                            <option>#002:甲醇生产工艺</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td width="20%" align="right">工艺设备位号：</td>
                    <td>
                        <select id="sbwh" name="sbwh">
                            <option>请选择...</option>
                            <option>1:洗煤</option>
                            <option>2:粉碎</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td width="20%" align="right">设备名称：</td>
                    <td>
                        <select id="sbmc" name="sbmc">
                            <option>请选择...</option>
                            <option>压滤机</option>
                            <option>破碎机</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td width="20%" align="right">事故时间：</td>
                    <td>
                        <input type="text" id="sgsj" name="sgsj" data-role="date" fn="notNull('事故时间','#data_form #sgsj')"/>
                        <span style="color:red">*</span>
                    </td>
                </tr>
                <tr>
                    <td width="20%" align="right">标题：</td>
                    <td>
                        <input type="text" id="sgbt" name="sgbt" maxlength="200" size="60" fn="notNull('标题','#data_form #sgbt')"/>
                        <span style="color:red">*</span>
                    </td>
                </tr>
                <tr>
                    <td width="20%" align="right">内容：</td>
                    <td></td>
                </tr> 
                <tr>
                    <td colspan="2" align="center">
                        <textarea id="sgnr" name="sgnr" style="width:700px;height:400px;visibility:hidden;"></textarea>
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
