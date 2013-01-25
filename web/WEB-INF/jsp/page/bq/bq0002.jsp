
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
        <title><%=SystemUtil.serverDesc%> -- 发布私信</title>
        <!-- InstanceEndEditable -->
        <!-- InstanceBeginEditable name="head" -->
        <script type="text/javascript" src="<c:url value='/script/kindeditor/kindeditor-min.js' />"></script>
        <script type="text/javascript" src="<c:url value='/script/jstree/jquery.jstree.js' />"></script>
        <script type="text/javascript">
            var dep = '${sessionScope.ses.dep_id.value}';
            var outJson = ${out_json};
            var editor;
            $(function(){
                KindEditor.ready(function(K) {
                    editor = K.create('#sxnr', {
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
        <style type="text/css">
            #right_title{
                height: 15px;
                background-color: #0B84C9;
                color: #FFF;
                font-weight: bold;
                padding: 4px 12px 4px 12px;
            }
        </style>
        <!-- InstanceEndEditable -->
    </head>
    <body>
        <!-- InstanceBeginEditable name="content" -->
        <div style="margin: 10px auto 10px auto; text-align: center"><h2>发布私信</h2></div>
        <hr />
        <table width="100%" align="center">
            <tr>                
                <td width="80%" style="vertical-align:top; border-right: solid 2px black" align="center">
                    <div id="left_msg">
                        <form id="data_form" name="data_form" class="sub_form" style="width: 90%;">
                            <table width="100%" class="table_input" border="0">
                                <tr>
                                    <td width="30%" align="right">私信标题：</td>
                                    <td>
                                        <input type="text" id="sxbt" name="sxbt" maxlength="200" size="40" fn="notNull('私信标题','#data_form #sxbt')"/>
                                        <span style="color: red">*</span>
                                    </td>
                                </tr>
                                <tr>
                                    <td width="30%" align="right">附件：</td>
                                    <td>
                                        <input type="file" id="fj" name="fj"/>
                                    </td>
                                </tr>
                                <tr>
                                    <td width="30%" align="right">内容：</td>
                                    <td></td>
                                </tr>
                                <tr>
                                    <td colspan="2" align="center">
                                        <textarea id="sxnr" name="sxnr" style="width:90%;height:400px;visibility:hidden;"></textarea>
                                        <span style="color: red">*</span>
                                    </td>
                                </tr>
                            </table>
                            <br />
                            <div align="center">
                                <input type="button" id="sub" value=" 提交 " />
                                <input type="reset" value=" 重置 " />
                            </div>
                    </div>
                </td>
                <td style="vertical-align:top;">
                    <p id="right_title">请选择收信人：</p>
                    <div id="right_msg">
                        <div id="dep_tree_div" style="margin:10px auto 10px auto;">
                            <ul id="dep_tree"></ul>
                        </div>
                    </div>
                </td>
            </tr>
        </table>
    </form>
    <!-- InstanceEndEditable -->
</body>
<!-- InstanceEnd --></html>
