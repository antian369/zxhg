
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
        <title><%=SystemUtil.serverDesc%> -- 工艺检查查询</title>
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
        <div style="margin: 10px auto 10px auto; text-align: center"><h2>工艺检查查询</h2></div>
        <form action="gyjc02.do" method="post" id="search_form" name="search_form" class="sub_form" style="width: 80%;">
            <input type="hidden" id="do" name="do" value="1" />
            <table width="100%" border="0">
                <tr>
                    <td width="40%">
                        类别：
                        <select id="lb" name="lb">
                            <option value="1">工艺指标</option>
                            <option value="2">巡检点检</option>
                            <option value="3">班前班后会</option>
                            <option value="4">双述法</option>
                            <option value="5">其它专项检查</option>
                        </select>
                    </td>
                    <td width="40%">
                        部门：
                        <select id="bm" name="bm">
                            <c:forEach items="${deps.value}" var="dep">
                                <option value="${dep.dep_id.value}">${dep.dep_name.value}</option>
                            </c:forEach>
                        </select>
                    </td>
                    <td>
                        <input type="submit" id="sub" value="查询" nouse />
                    </td>
                </tr>
            </table>
        </form>
        <hr />
        <div id="container" style="width: 95%;margin: 10px auto 10px auto;">
            <table cellpadding="0" cellspacing="0" border="0" class="display" id="gyjc_table">
                <thead>
                    <tr>
                        <th>主题</th>
                        <th>部门</th>
                        <th>级别</th>
                        <th>类别</th>
                        <th>录入人</th>
                        <th>录入时间</th>
                        <th>查看</th>
                        <th>操作</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${result.value}" var="gyjc" varStatus="xh">
                        <tr>
                            <td align="center">${gyjc.jczt.value}</td>
                            <td align="center">${gyjc.lrdw.value}</td>
                            <td align="center">${gyjc.jb_desc.value}</td>
                            <td align="center">${gyjc.lb_desc.value}</td>
                            <td align="center">${gyjc.lrrxm.value}</td>
                            <td align="center">${gyjc.lrsj.value}</td>
                            <td align="center">
                                <a href="#this" class="info" ind="${xh.index}">查看</a>
                            </td>
                            <td align="center">
                                <a href="#this" class="update" ind="${xh.index}">修改</a>
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
            <form action="gyjc02.do" method="post" id="page_form" name="page_form">
                <%-- 翻页参数，还需要使用js对id=page的项进行赋值 --%>
                <input type="hidden" id="page" name="page" value="${param.page}"/>
                <input type="hidden" name="do" value="1" />
            </form>
            <%-- 页码生成 end --%>
        </div>
        <div id="gyjc_dialog" title="工艺检查信息">

            <table width="100%" class="table_input" border="0">
                <tr>
                    <td width="40%" align="right">部门：</td>
                    <td width="60%" id="lrdw"></td>
                </tr>
                <tr>
                    <td width="40%" align="right">级别：</td>
                    <td width="60%" id="jb_desc"></td>
                </tr>
                <tr>
                    <td width="40%" align="right">类别：</td>
                    <td width="60%" id="lb_desc"></td>
                </tr>
                <tr>
                    <td width="40%" align="right">主题：</td>
                    <td width="60%" id="jczt"></td>
                </tr>
                <tr>
                    <td width="40%" align="right">内容：</td>
                    <td width="60%" id="jcnr"></td>
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
