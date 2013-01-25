
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
        <title><%=SystemUtil.serverDesc%> -- 隐患核实</title>
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
        <div style="margin: 10px auto 10px auto; text-align: center"><h2>隐患核实</h2></div>
        <form action="yhsg04.do" method="post" id="search_form" name="search_form" class="sub_form" style="width: 80%;">
            <input type="hidden" id="do" name="do" value="1" />
            <table width="100%" border="0">
                <tr>
                    <td width="10%"></td>
                    <td width="30%">
                        发现人姓名：<input type="text" id="fxrxm" name="fxrxm" size="15"/>
                    </td>
                    <td width="25%">
                        隐患单位：
                        <select id="yhdw" name="yhdw">
                            <option value="">全部</option>
                            <c:forEach items="${deps.value}" var="dep">
                                <option value="${dep.dep_id.value}">${dep.dep_name.value}</option>
                            </c:forEach>
                        </select>
                    </td>
                    <td width="15%">
                        状态：
                        <select id="sgzt" name="sgzt">
                            <option value="">全部</option>
                            <c:forEach items="${requestScope['aq_yh_yhsg.sgzt']}" var="par">
                                <option value="${par.colValue}">${par.valueDesc}</option>
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
            <table cellpadding="0" cellspacing="0" border="0" class="display" id="yhsg_table">
                <thead>
                    <tr>
                        <th>发现人</th>
                        <th>隐患单位</th>
                        <th>隐患描述</th>
                        <th>登记时间</th>
                        <th>核实人</th>
                        <th>奖金</th>
                        <th>收购状态</th>
                        <th>详细</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${result.value}" var="yhsg" varStatus="xh">
                        <tr>
                            <td align="center">${yhsg.fxrxm.value}</td>
                            <td align="center" select="${yhsg.yhdw.value}" class="table_view_select" target_select="#yhdw"></td>
                            <td align="center">${yhsg.yhms.value}</td>
                            <td align="center">${yhsg.djsj.value}</td>
                            <td align="center">${yhsg.hzrxm.value}</td>
                            <td align="center">${yhsg.jiangjin.value}</td>
                            <td align="center">${yhsg.sgzt_desc.value}</td>
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
            <form action="yhsg04.do" method="post" id="page_form" name="page_form">
                <%-- 翻页参数，还需要使用js对id=page的项进行赋值 --%>
                <input type="hidden" id="page" name="page" value="${param.page}"/>
                <input type="hidden" name="do" value="1" />
                <input type="hidden" name="fxrxm" value="${param.fxrxm}" />
                <input type="hidden" id="yhdw" name="yhdw" value="${param.yhdw}" />
                <input type="hidden" id="sgzt" name="sgzt" value="${param.sgzt}" />
            </form>
            <%-- 页码生成 end --%>
        </div>

        <%-- 收购单细信息 --%>
        <div id="yhsg_dialog" title="隐患收购单细信息">
            <input type="hidden" id="yh_id" name="yh_id" />
            <table class="table_input" border="0" width="100%">
                <tr>
                    <td width="40%" align="right">发现人：</td>
                    <td width="60%" id="fxrxm"></td>
                </tr>
                <tr>
                    <td width="40%" align="right">发现单位：</td>
                    <td width="60%">
                        <select id="fxdw" name="fxdw" disabled="disabled">
                            <c:forEach items="${deps.value}" var="dep">
                                <option value="${dep.dep_id.value}">${dep.dep_name.value}</option>
                            </c:forEach>
                        </select></td>
                </tr>
                <tr>
                    <td width="40%" align="right">隐患描述：</td>
                    <td width="60%" id="yhms"></td>
                </tr>
                <tr>
                    <td width="40%" align="right">隐患单位：</td>
                    <td width="60%">
                        <select id="yhdw" name="yhdw" disabled="disabled">
                            <c:forEach items="${deps.value}" var="dep">
                                <option value="${dep.dep_id.value}">${dep.dep_name.value}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td width="40%" align="right">隐患地点：</td>
                    <td width="60%" id="yhdd"></td>
                </tr>
                <tr>
                    <td width="40%" align="right">登记人：</td>
                    <td width="60%" id="djr"></td>
                </tr>
                <tr>
                    <td width="40%" align="right">登记时间：</td>
                    <td width="60%" id="djsj"></td>
                </tr>
                <tr>
                    <td width="40%" align="right">核准人：</td>
                    <td width="60%" id="hzrxm"></td>
                </tr>
                <tr>
                    <td width="40%" align="right">核准时间：</td>
                    <td width="60%" id="hzsj"></td>
                </tr>
                <tr>
                    <td width="40%" align="right">奖金：</td>
                    <td width="60%" id="jiangjin"></td>
                </tr>
                <tr>
                    <td width="40%" align="right">收购状态：</td>
                    <td width="60%" id="sgzt_desc"></td>
                </tr>
            </table>
        </div>

        <!-- InstanceEndEditable -->
    </body>
    <!-- InstanceEnd --></html>
