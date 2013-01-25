
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
        <title><%=SystemUtil.serverDesc%> -- 工艺备品查询</title>
        <!-- InstanceEndEditable -->
        <!-- InstanceBeginEditable name="head" -->
        <script type="text/javascript">
            var outJson = ${out_json};
            var dep = '${sessionScope.ses.dep_id.value}';
        </script>
        <!-- InstanceEndEditable -->
    </head>
    <body>
        <!-- InstanceBeginEditable name="content" -->
        <div style="margin: 10px auto 10px auto; text-align: center"><h2>工艺备品查询</h2></div>
        <form action="zlgl03.do" method="post" id="search_form" name="search_form" class="sub_form" style="width: 80%;">
            <input type="hidden" id="do" name="do" value="1" />
            <table width="100%" border="0">
                <tr>
                    <td width="30%">
                        设备名称：
                        <input type="text" id="sbmc" name="sbmc" size="15"/>
                    </td>
                    <td width="30%">
                        备品名称：
                        <input type="text" id="bpmc" name="bpmc" size="15"/>
                    </td>
                    <td width="30%">
                        工艺分厂：
                        <select id="bm" name="bm">
                            <c:forEach items="${deps.value}" var="dep">
                                <option value="${dep.dep_id.value}">${dep.dep_name.value}</option>
                            </c:forEach>
                        </select>
                    </td>
                    <td>
                        <input type="button" id="sub" value="查询" nouse />
                    </td>
                </tr>
            </table>
        </form>
        <hr />
        <div id="container" style="width: 95%;margin: 10px auto 10px auto;">
            <table cellpadding="0" cellspacing="0" border="0" class="display" id="sbbp_table">
                <thead>
                    <tr>
                        <th>工艺流程</th>
                        <th>工艺分厂</th>
                        <th>设备位号</th>
                        <th>设备名称</th>
                        <th>备品名称</th>
                        <th>设计寿命</th>
                        <th>已使用寿命</th>
                        <th>查看</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${result.value}" var="sbbp" varStatus="xh">
                        <tr>
                            <td align="center">${sbbp.gymc.value}</td>
                            <td align="center">${sbbp.gyfc.value}</td>
                            <td align="center">${sbbp.sbwh.value}</td>
                            <td align="center">${sbbp.sbmc.value}</td>
                            <td align="center">${sbbp.bpmc.value}</td>
                            <td align="center">${sbbp.bpsjsm.value}</td>
                            <td align="center">${sbbp.bpyysm.value}</td>
                            <td align="center">
                                <a href="#this" class="info" ind="${xh.index}">查看</a>
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
            <form action="zlgl03.do" method="post" id="page_form" name="page_form">
                <%-- 翻页参数，还需要使用js对id=page的项进行赋值 --%>
                <input type="hidden" id="page" name="page" value="${param.page}"/>
                <input type="hidden" name="do" value="1" />
            </form>
            <%-- 页码生成 end --%>
        </div>
        <div id="sbbp_dialog" title="工艺设备详细信息">
            <table width="100%" class="table_input" border="0">
                <tr>
                    <td width="40%" align="right">工艺流程：</td>
                    <td width="60%" id="gymc"></td>
                </tr>
                <tr>
                    <td width="40%" align="right">设备位号：</td>
                    <td width="60%" id="sbwh"></td>
                </tr>
                <tr>
                    <td width="40%" align="right">设备名称：</td>
                    <td width="60%" id="sbmc"></td>
                </tr>
                <tr>
                    <td width="40%" align="right">备品名称：</td>
                    <td width="60%" id="bpmc"></td>
                </tr>
                <tr>
                    <td width="40%" align="right">规格型号：</td>
                    <td width="60%" id="bpggxh"></td>
                </tr>
                <tr>
                    <td width="40%" align="right">设计寿命：</td>
                    <td width="60%" id="bpsjsm"></td>
                </tr>
                <tr>
                    <td width="40%" align="right">已使用寿命：</td>
                    <td width="60%" id="bpyysm"></td>
                </tr>
                <tr>
                    <td width="40%" align="right">数量：</td>
                    <td width="60%" id="bpsl"></td>
                </tr>
                <tr>
                    <td width="40%" align="right">材质：</td>
                    <td width="60%" id="bpcz"></td>
                </tr>
                <tr>
                    <td width="40%" align="right">图号：</td>
                    <td width="60%" id="bpth"></td>
                </tr>
                <tr>
                    <td width="40%" align="right">原生产厂家：</td>
                    <td width="60%" id="bpysj"></td>
                </tr>
                <tr>
                    <td width="40%" align="right">现使用生产厂家：</td>
                    <td width="60%" id="bpxsj"></td>
                </tr>
                <tr>
                    <td width="40%" align="right">最近一次更换时间：</td>
                    <td width="60%" id="bpzjsj"></td>
                </tr>
                <tr>
                    <td width="40%" align="right">更换原因分析：</td>
                    <td width="60%" id="bpghyy"></td>
                </tr>
                <tr>
                    <td width="40%" align="right">费用：</td>
                    <td width="60%" id="bpfy"></td>
                </tr>
                <tr>
                    <td width="40%" align="right">录入人：</td>
                    <td width="60%" id="lrrxm"></td>
                </tr>
                <tr>
                    <td width="40%" align="right">录入时间：</td>
                    <td width="60%" id="lrsj"></td>
                </tr>
            </table>
        </div>
        <!-- InstanceEndEditable -->
    </body>
    <!-- InstanceEnd --></html>
