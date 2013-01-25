
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
        <title><%=SystemUtil.serverDesc%> -- 点检班级设置</title>
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
        <div style="margin: 10px auto 10px auto; text-align: center"><h2>点检班级设置</h2></div>
        <form action="zlgl02.do" method="post" id="search_form" name="search_form" class="sub_form" style="width: 80%;">
            <input type="hidden" id="do" name="do" value="1" />
            <table width="100%" border="0">
                <tr>
                    <td width="30%">
                        班级编号：
                        <input type="text" id="bjbh" name="bjbh" />
                    </td>
                    <td width="30%">
                        班级名称：
                        <input type="text" id="bjmc" name="bjmc" />
                    </td>
                    <td width="25%">
                        工艺分厂：
                        <select id="bm" name="bm">
                            <c:forEach items="${deps.value}" var="dep">
                                <option value="${dep.dep_id.value}">${dep.dep_name.value}</option>
                            </c:forEach>
                        </select>
                    </td>
                    <td>
                        <input type="button" id="sub" value="查询" nouse />
                        <input type="button" id="add" value="新增" />
                    </td>
                </tr>
            </table>
        </form>
        <hr />
        <div id="container" style="width: 95%;margin: 10px auto 10px auto;">
            <table cellpadding="0" cellspacing="0" border="0" class="display" id="djbj_table">
                <thead>
                    <tr>
                        <th>班级编号</th>
                        <th>班级名称</th>
                        <th>工艺分厂</th>
                        <th>班级描述</th>
                        <th>录入人</th>
                        <th>录入时间</th>
                        <th>修改</th>
                        <th>删除</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${result.value}" var="djbj" varStatus="xh">
                        <tr>
                            <td align="center">${djbj.bjbh.value}</td>
                            <td align="center">${djbj.bjmc.value}</td>
                            <td align="center">${djbj.gyfc.value}</td>
                            <td align="center">${djbj.bjms.value}</td>
                            <td align="center">${djbj.lrrxm.value}</td>
                            <td align="center">${djbj.lrsj.value}</td>
                            <td align="center">
                                <a href="#this" class="update" ind="${xh.index}">修改</a>
                            </td>
                            <td align="center">
                                <a href="#this" class="del" ind="${xh.index}">删除</a>
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
            <form action="zlgl02.do" method="post" id="page_form" name="page_form">
                <%-- 翻页参数，还需要使用js对id=page的项进行赋值 --%>
                <input type="hidden" id="page" name="page" value="${param.page}"/>
                <input type="hidden" name="do" value="1" />
            </form>
            <%-- 页码生成 end --%>
        </div>
        <div id="djbj_dialog" title="工艺流程新增">
            <form id="djbj_data">
                <table width="100%" class="table_input" border="0">
                    <tr>
                        <td width="40%" align="right">班级编号：</td>
                        <td width="60%">
                            <input type="text" id="bjbh" name="bjbh" maxlength="20" fn="isNum('班级编号','#djbj_data #bjbh')"/>
                        </td>
                    </tr>
                    <tr>
                        <td width="40%" align="right">班级名称：</td>
                        <td width="60%">
                            <input type="text" id="bjmc" name="bjmc" maxlength="80" fn="notNull('班级名称','#djbj_data #bjmc')"/>
                        </td>
                    </tr>
                    <tr>
                        <td width="40%" align="right">工艺流程分厂：</td>
                        <td width="60%" id="gyfc">${sessionScope.ses.dep_name.value}</td>
                    </tr>
                    <tr>
                        <td width="40%" align="right">班级描述：</td>
                        <td width="60%">
                            <textarea id="bjms" name="bjms" rows="5" cols="40" maxlength="500" fn="notNull('班级描述','#djbj_data #bjms')"></textarea>
                        </td>
                    </tr>
                </table>
            </form>
        </div>
        <!-- InstanceEndEditable -->
    </body>
    <!-- InstanceEnd --></html>
