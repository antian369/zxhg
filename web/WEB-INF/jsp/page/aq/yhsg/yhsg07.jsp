
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
        <title><%=SystemUtil.serverDesc%> -- 隐患收购删除</title>
        <!-- InstanceEndEditable -->
        <!-- InstanceBeginEditable name="head" -->
        <script type="text/javascript">
            var outJson = ${out_json};
        </script>
        <!-- InstanceEndEditable -->
    </head>
    <body>
        <!-- InstanceBeginEditable name="content" -->
        <div style="margin: 10px auto 10px auto; text-align: center"><h2>隐患收购删除</h2></div>
        <hr />
        <form method="post" id="search_form" name="search_form" class="sub_form" style="width: 80%;">
            <table width="100%" border="0">
                <tr>
                    <td width="25%">
                        隐患单位：
                        <select id="yhdw" name="yhdw">
                            <option value="" checked>全部</option>
                            <c:forEach items="${deps.value}" var="dep">
                                <option value="${dep.dep_id.value}">${dep.dep_name.value}</option>
                            </c:forEach>
                        </select>
                    </td>
                    <td>
                        隐患级别：
                        <select id="yhjb" name="yhjb">
                            <option value="" checked>全部</option>
                            <c:forEach items="${requestScope['aq_yh_yhsg.yhjb']}" var="par">
                                <option value="${par.colValue}">${par.valueDesc}</option>
                            </c:forEach>
                        </select>
                    </td>
                    <td>
                        检查时间：
                        <input type="text" data-role="date" id="begin" name="begin" size="8" />至
                        <input type="text" data-role="date" id="end" name="end" size="8" />
                    </td>
                </tr>
                <tr>
                    <td>
                        隐患分类：
                        <select id="yhlb" name="yhlb">
                            <option value="" checked>全部</option>
                            <c:forEach items="${requestScope['aq_yh_yhsg.yhlb']}" var="par">
                                <option value="${par.colValue}">${par.valueDesc}</option>
                            </c:forEach>
                        </select>
                    </td>
                    <td>
                        收购状态：
                        <select id="zt" name="zt">
                            <option value="" checked>全部</option>
                            <c:forEach items="${requestScope['aq_yh_yhsg.zt']}" var="par">
                                <option value="${par.colValue}">${par.valueDesc}</option>
                            </c:forEach>
                        </select>
                    </td>
                    <td align="center">
                        <input type="submit" id="sub" value="查询" />
                        <input type="button" id="reset" value="重置" />
                    </td>
                </tr>
            </table>
        </form>
        <hr />
        <div id="container" style="width: 95%;margin: 10px auto 10px auto;">
            <table cellpadding="0" cellspacing="0" border="0" class="display" id="yhsgs">
                <thead>
                    <tr>
                    <tr>
                        <th>发现人</th>
                        <th>隐患描述</th>
                        <th>发现时间</th>
                        <th>登记时间</th>
                        <th>整改时间</th>
                        <th>整改人</th>
                        <th>详细</th>
                    </tr>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${result.value}" var="yh" varStatus="xh">
                        <tr ind="${xh.index}">
                            <td align="center">${yh.fxr.value}</td>
                            <td align="center">${yh.yhms.value}</td>
                            <td align="center">${yh.fxsj.value}</td>
                            <td align="center">${yh.djsj.value}</td>
                            <td align="center">${yh.zgsj.value}</td>
                            <td align="center">${yh.zgr.value}</td>
                            <td align="center">
                                <a href="#this" class="info" ind="${xh.index}">删除</a>
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
                <input type="hidden" name="yhdw" value="${param.yhdw}" />
                <input type="hidden" name="yhmc" value="${param.yhjb}" />
                <input type="hidden" name="begin" value="${param.begin}" />
                <input type="hidden" name="end" value="${param.end}" />
                <input type="hidden" name="zt" value="${param.zt}" />
                <input type="hidden" name="lazy" value="${param.yhlb}" />
            </form>
            <%-- 页码生成 end --%>
        </div>

        <%-- 收购单细信息 --%>
        <div id="yhsg_dialog" title="隐患收购单细信息">
            <table class="tablelist" width="100%">
                <tr>
                    <td width="20%" style="background-color: #319ACF; color: white;">隐患收购信息：</td>
                    <td colspan="3"></td>
                </tr>
                <tr>
                    <td width="20%">发现人：</td>
                    <td width="30%" id="fxr"></td>
                    <td width="20%">发现单位：</td>
                    <td>
                        <select id="fxdw" name="fxdw" disabled="disabled">
                            <c:forEach items="${deps.value}" var="dep">
                                <option value="${dep.dep_id.value}">${dep.dep_name.value}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td width="20%">发现时间：</td>
                    <td width="30%" id="fxsj"></td>
                    <td width="20%">隐患单位：</td>
                    <td>
                        <select id="yhdw" name="yhdw" disabled="disabled">
                            <c:forEach items="${deps.value}" var="dep">
                                <option value="${dep.dep_id.value}">${dep.dep_name.value}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td width="20%">隐患地点：</td>
                    <td id="yhdd" colspan="3"></td>
                </tr>
                <tr>
                    <td width="20%">隐患描述：</td>
                    <td colspan="3"><textarea id="yhms" name="yhms" rows="5" cols="40" disabled="disabled"></textarea></td>
                </tr>
                <tr>
                    <td width="20%">登记人：</td>
                    <td width="30%" id="djrxm"></td>
                    <td width="20%">登记时间：</td>
                    <td id="djsj"></td>
                </tr>
                <tr>
                    <td width="20%" style="background-color: #319ACF; color: white;">隐患核实信息：</td>
                    <td colspan="3"></td>
                </tr>
                <tr>
                    <td width="20%">隐患分类：</td>
                    <td width="30%" id="yhfl_desc"></td>
                    <td width="20%">确认人：</td>
                    <td width="30%" id="qrrxm"></td>
                </tr>
                <tr>
                    <td width="20%">整改人：</td>
                    <td id="zgr"></td>
                    <td width="20%">整改时间：</td>
                    <td id="zgsj"></td>
                </tr>
                <tr>
                    <td width="20%">整改情况：</td>
                    <td colspan="3"><textarea id="zgqk" name="zgqk" rows="5" cols="40" disabled="disabled"></textarea></td>
                </tr>
                <tr>
                    <td width="20%" style="background-color: #319ACF; color: white;">隐患认定信息：</td>
                    <td colspan="3"></td>
                </tr>
                <tr>
                    <td width="20%">隐患级别：</td>
                    <td width="30%" id="yhjb_desc"></td>
                    <td width="20%">认定人：</td>
                    <td id="rdrxm"></td>
                </tr>
                <tr>
                    <td width="20%">认定备注：</td>
                    <td colspan="3"><textarea id="rdbz" name="rdbz" rows="5" cols="40" disabled="disabled"></textarea></td>
                </tr>
                <tr>
                    <td width="20%">隐患收购状态：</td>
                    <td id="zt_desc" colspan="3"></td>
                </tr>
            </table>
        </div>
        <!-- InstanceEndEditable -->
    </body>
    <!-- InstanceEnd --></html>
