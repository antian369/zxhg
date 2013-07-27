
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
        <title><%=SystemUtil.serverDesc%> -- 三违删除</title>
        <!-- InstanceEndEditable -->
        <!-- InstanceBeginEditable name="head" -->
        <script type="text/javascript">
            var outJson = ${out_json};
        </script>
        <!-- InstanceEndEditable -->
    </head>
    <body>
        <!-- InstanceBeginEditable name="content" -->
        <div style="margin: 10px auto 10px auto; text-align: center"><h2>三违删除</h2></div>
        <form method="post" id="search_form" name="search_form" class="sub_form" style="width: 80%;">
            <table width="100%" border="0">
                <tr>
                    <td width="25%">
                        状态：
                        <select id="zt" name="zt">
                            <option value="" checked>全部</option>
                            <c:forEach items="${requestScope['aq_sw_info.zt']}" var="par">
                                <option value="${par.colValue}">${par.valueDesc}</option>
                            </c:forEach>
                        </select>
                    </td>
                    <td width="25%">
                        分类：
                        <select id="swfl" name="swfl">
                            <option value="" checked>全部</option>
                            <c:forEach items="${requestScope['aq_sw_info.swfl']}" var="par">
                                <option value="${par.colValue}">${par.valueDesc}</option>
                            </c:forEach>
                        </select>
                    </td>
                    <td width="25%">
                        三违单位：
                        <select id="ssdw" name="ssdw">
                            <option value="" checked>全部</option>
                            <c:forEach items="${deps.value}" var="dep">
                                <option value="${dep.dep_id.value}">${dep.dep_name.value}</option>
                            </c:forEach>
                        </select>
                    </td>
                    <td>
                        <input type="submit" id="sub" value="查询" />
                    </td>
                </tr>
            </table>
        </form>
        <hr />
        <div id="container" style="width: 95%;margin: 10px auto 10px auto;">
            <table cellpadding="0" cellspacing="0" border="0" class="display" id="sw_table">
                <thead>
                    <tr>
                        <th>三违发现时间</th>
                        <th>三违单位</th>
                        <th>三违人员</th>
                        <th>三违分类</th>
                        <th>状态</th>
                        <th>发现人</th>
                        <th>操作</th>
                    </tr>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${result.value}" var="sw" varStatus="xh">
                        <tr>
                            <td align="center">${sw.swsj.value.detaled}</td>
                            <td align="center" select="${sw.ssdw.value}" class="table_view_select" target_select="#ssdw"></td>
                            <td align="center">${sw.swry.value}</td>
                            <td align="center">${sw.swfl_desc.value}</td>
                            <td align="center">${sw.zt_desc.value}</td>
                            <td align="center">${sw.fxrxm.value}</td>
                            <td align="center">
                                <a href="#this" class="del" ind="${xh.index}" id="${sw.sw_id.value}">删除</a>
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
                <input type="hidden" id="zt" name="zt" value="${param.zt}"/>
                <input type="hidden" id="swfl" name="swfl" value="${param.swfl}"/>
                <input type="hidden" id="ssdw" name="ssdw" value="${param.ssdw}"/>
            </form>
            <%-- 页码生成 end --%>
        </div>

        <!-- InstanceEndEditable -->
    </body>
    <!-- InstanceEnd --></html>
