
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
        <title><%=SystemUtil.serverDesc%> -- 通报事故</title>
        <!-- InstanceEndEditable -->
        <!-- InstanceBeginEditable name="head" -->
        <script type="text/javascript">
            var outJson = ${out_json};
        </script>
        <!-- InstanceEndEditable -->
    </head>
    <body>
        <!-- InstanceBeginEditable name="content" -->
        <div style="margin: 10px auto 10px auto; text-align: center"><h2>通报事故</h2></div>
        <hr />
        <div id="container" style="width: 95%;margin: 10px auto 10px auto;">
            <table cellpadding="0" cellspacing="0" border="0" class="display" id="sg_table">
                <thead>
                    <tr>
                        <th>事故单位</th>
                        <th>事故地点</th>
                        <th>事故时间</th>
                        <th>事故级别</th>
                        <th>事故类别</th>
                        <th>直接损失(万元)</th>
                        <th>间接损失(万元)</th>
                        <th>操作</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${result.value}" var="sg" varStatus="xh">
                        <tr>
                            <!--<td align="center" select="${sg.sgdw.value}" class="table_view_select" target_select="#sgdw"></td>-->
                            <td align="center">${sg.sgdw.value}</td>
                            <td align="center">${sg.sgdd.value}</td>
                            <td align="center">${sg.sgsj.value}</td>
                            <td align="center">${sg.sgjb_desc.value}</td>
                            <td align="center">${sg.sglb_desc.value}</td>
                            <td align="center">${sg.zjss.value}</td>
                            <td align="center">${sg.jjss.value}</td>
                            <td align="center">
                                <a href="#this" class="info" ind="${xh.index}">通报</a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
        <div align="center" id="_cut_page">
            <%-- 页码生成 begin --%>
            <c:forEach begin="${begin_page.value}" end="${end_page.value}" var="p">
                &nbsp;&nbsp;&nbsp;
                <c:choose>
                    <c:when  test="${page.value != p}">
                        <a href="#this" page="${p}" class="page_num"> ${p} </a>
                    </c:when>
                    <c:otherwise>
                        <span style="color: red">${p}</span>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
            &nbsp;&nbsp;&nbsp;
            <a href="#this" page="1" class="page_num">首页</a>/
            <a href="#this" page="${page_count.value}" class="page_num">末页</a>
            &nbsp;&nbsp;&nbsp;
            共 <span style="color: red">${page_count.value}</span> 页， <span style="color: red">${count.value}</span> 条
            <form method="post" id="page_form" name="page_form">
                <%-- 翻页参数，还需要使用js对id=page的项进行赋值 --%>
                <input type="hidden" id="page" name="page" value="${param.page}"/>
                <input type="hidden" name="sgdw" value="${param.sgdw}" />
                <input type="hidden" name="sgjb" value="${param.sgjb}" />
                <input type="hidden" name="sgxz" value="${param.sgxz}" />
                <input type="hidden" name="sglb" value="${param.sglb}" />
            </form>
            <%-- 页码生成 end --%>
        </div>

        <%-- 弹出页 事故详细 begin --%>
        <div id="cksg_dialog" title="事故详细信息">
            <br />
            <table class="table_input" width="90%">
                <tr>
                    <td align="right" width="40%">事故单位：</td>
                    <td id="sgdw"></td>
                </tr>
                <tr>
                    <td align="right">事故发生地点：</td>
                    <td id="sgdd"></td>
                </tr>
                <tr>
                    <td align="right">事故时间：</td>
                    <td id="sgsj"></td>
                </tr>
                <tr>
                    <td align="right">事故类别：</td>
                    <td id="sglb_desc"></td>
                </tr>
                <tr>
                    <td align="right">事故级别：</td>
                    <td id="sgjb_desc"></td>
                </tr>
                <tr>
                    <td align="right">事故性质：</td>
                    <td id="sgxz_desc"></td>
                </tr>
                <tr>
                    <td align="right">直接经济损失（万元）：</td>
                    <td id="zjss"></td>
                </tr>
                <tr>
                    <td align="right">间接经济损失（万元）：</td>
                    <td id="jjss"></td>
                </tr>
                <tr>
                    <td align="right">事故概要：</td>
                    <td id="zywhp"></td>
                </tr>
                <tr>
                    <td align="right">死亡人数：</td>
                    <td id="swrs"></td>
                </tr>
                <tr>
                    <td align="right">重伤人数：</td>
                    <td id="zsrs"></td>
                </tr>
                <tr>
                    <td align="right">疏散人数：</td>
                    <td id="ssrs"></td>
                </tr>
                <tr>
                    <td align="right">中毒人数：</td>
                    <td id="zdrs"></td>
                </tr>
                <tr>
                    <td align="right">入院人数：</td>
                    <td id="ryrs"></td>
                </tr>
                <tr>
                    <td align="right">失踪人数：</td>
                    <td id="szrs"></td>
                </tr>
                <tr>
                    <td align="right">事故备注：</td>
                    <td id="sgbz"></td>
                </tr>
            </table>
        </div>
        <%-- 弹出页 事故详细 end --%>

        <%-- 弹出页 通报事故 begin --%>
        <div id="tbsg_dialog" title="通报事故">
            <br />
            <form id="tbsg_form">
                <input type="hidden" id="sg_id" name="sg_id" />
                <table class="table_input" width="90%">
                    <tr>
                        <td align="right">事故处理：</td>
                        <td>
                            <textarea id="sgcl" name="sgcl" fn="notNull('事故处理', '#tbsg_form #sgcl')" rows="5" cols="40"></textarea>
                            <span style="color: red">*</span>
                        </td>
                    </tr>
                    <tr>
                        <td align="right">整改措施：</td>
                        <td>
                            <textarea id="zgcs" name="zgcs" fn="notNull('整改措施', '#tbsg_form #zgcs')" rows="5" cols="40"></textarea>
                            <span style="color: red">*</span>
                        </td>
                    </tr>
                    <tr>
                        <td align="right">调查组成员：</td>
                        <td>
                            <textarea id="dczcy" name="dczcy" fn="notNull('调查组成员', '#tbsg_form #dczcy')" rows="5" cols="40"></textarea>
                            <span style="color: red">*</span>
                        </td>
                    </tr>
                    <tr>
                        <td align="right">填表单位负责人：</td>
                        <td>
                            <input type="text" id="tbfzr" name="tbfzr" fn="notNull('填表单位负责人', '#tbsg_form #tbfzr')" />
                            <span style="color: red">*</span>
                        </td>
                    </tr>
                    <tr>
                        <td align="right">备注：</td>
                        <td>
                            <input type="text" id="tbbz" name="tbbz" />
                        </td>
                    </tr>
                </table>
            </form>
        </div>
        <%-- 弹出页 通报事故 end --%>
        <!-- InstanceEndEditable -->
    </body>
    <!-- InstanceEnd --></html>
