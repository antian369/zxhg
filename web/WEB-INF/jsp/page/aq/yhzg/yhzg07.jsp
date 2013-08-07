
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
        <title><%=SystemUtil.serverDesc%> -- 隐患整改查询</title>
        <!-- InstanceEndEditable -->
        <!-- InstanceBeginEditable name="head" -->
        <script type="text/javascript">
            var outJson = ${out_json};
        </script>
        <!-- InstanceEndEditable -->
    </head>
    <body>
        <!-- InstanceBeginEditable name="content" -->
        <div style="margin: 10px auto 10px auto; text-align: center"><h2>隐患整改查询</h2></div>
        <form method="post" id="search_form" name="search_form" class="sub_form" style="width: 80%;">
            <input type="hidden" id="do" name="do" value="1" />
            <table width="100%" border="0">
                <tr>
                    <td width="25%">
                        隐患名称：
                        <input type="text" id="yhmc" name="yhmc" size="15" />
                    </td>
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
                        检查时间：
                        <input type="text" data-role="date" id="begin" name="begin" size="8" />至
                        <input type="text" data-role="date" id="end" name="end" size="8" />
                    </td>
                </tr>
                <tr>
                    <td>
                        隐患状态：
                        <select id="zt" name="zt">
                            <option value="" checked>全部</option>
                            <c:forEach items="${requestScope['aq_yh_info.zt']}" var="par">
                                <option value="${par.colValue}">${par.valueDesc}</option>
                            </c:forEach>
                        </select>
                    </td>
                    <td>
                        整改被通报：
                        <select id="lazy" name="lazy">
                            <option value="" checked>全部</option>
                            <option value="0">否</option>
                            <option value="1">是</option>
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
            <table cellpadding="0" cellspacing="0" border="0" class="display" id="yh_table">
                <thead>
                    <tr>
                        <th>隐患名称</th>
                        <th>隐患单位</th>
                        <th>隐患类别</th>
                        <th>检查类型</th>
                        <th>隐患级别</th>
                        <th>检查时间</th>
                        <th>整改通报</th>
                        <th>详细</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${result.value}" var="yh" varStatus="xh">
                        <tr>
                            <td align="center">${yh.yhmc.value}</td>
                            <td align="center" select="${yh.yhdw.value}" class="table_view_select" target_select="#yhdw"></td>
                            <td align="center">${yh.yhlb_desc.value}</td>
                            <td align="center">${yh.ly_desc.value}</td>
                            <td align="center">${yh.yhjb_desc.value}</td>
                            <td align="center">${yh.jcsj.value}</td>
                            <td align="center">${yh.lazy_desc.value}</td>
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
            <form method="post" id="page_form" name="page_form">
                <%-- 翻页参数，还需要使用js对id=page的项进行赋值 --%>
                <input type="hidden" id="page" name="page" value="${param.page}"/>
                <input type="hidden" name="yhdw" value="${param.yhdw}" />
                <input type="hidden" name="yhmc" value="${param.yhmc}" />
                <input type="hidden" name="begin" value="${param.begin}" />
                <input type="hidden" name="end" value="${param.end}" />
                <input type="hidden" name="zt" value="${param.zt}" />
                <input type="hidden" name="lazy" value="${param.lazy}" />
            </form>
            <%-- 页码生成 end --%>
        </div>

        <%-- 隐患详细信息 --%>
        <div id="yh_dialog" title="隐患详细信息">
            <input type="hidden" id="yh_id" name="yh_id" />
            <table class="tablelist" width="100%">
                <tr>
                    <td width="20%" style="background-color: #319ACF; color: white;">隐患信息：</td>
                    <td colspan="3"></td>
                </tr>
                <tr>
                    <td width="20%">隐患名称：</td>
                    <td id="yhmc" colspan="3"></td>
                </tr>
                <tr>
                    <td width="20%">隐患描述：</td>
                    <td colspan="3"><textarea id="yhms" name="yhms" rows="5" cols="40" disabled="disabled"></textarea></td>
                </tr>
                <tr>
                    <td width="20%">隐患地点：</td>
                    <td id="yhdd" colspan="3"></td>
                </tr>
                <tr>
                    <td width="20%">隐患类别：</td>
                    <td width="30%" id="yhlb_desc"></td>
                    <td width="20%">隐患级别：</td>
                    <td id="yhjb_desc"></td>
                </tr>
                <tr>
                    <td width="20%">隐患单位：</td>
                    <td width="30%">
                        <select id="yhdw" name="yhdw" disabled="disabled">
                            <c:forEach items="${deps.value}" var="dep">
                                <option value="${dep.dep_id.value}">${dep.dep_name.value}</option>
                            </c:forEach>
                        </select>
                    </td>
                    <td width="20%">检查类型：</td>
                    <td id="ly_desc"></td>
                </tr>
                <tr>
                    <td width="20%">隐患状态：</td>
                    <td width="30%" id="zt_desc"></td>
                    <td width="20%">检查时间：</td>
                    <td width="30%" id="jcsj"></td>
                </tr>
                <tr>
                    <td width="20%" style="background-color: #319ACF; color: white;">整改记录：</td>
                    <td colspan="3"></td>
                </tr>
                <tr>
                    <td colspan="4" align="center">
                        <table id="zgs" class="tablelist" width="95%">
                            <thead>
                                <tr>
                                    <th width="25%" data-key="kssj">开始时间</th>
                                    <th width="25%" data-key="pzsx">批准时限</th>
                                    <th width="10%" data-key="lazy_zt_desc">延时</th>
                                    <th width="15%" data-key="yszt_desc">验收</th>
                                    <th width="15%" data-key="ysrxm">验收人</th>
                                    <th data-key="opt">详细</th>
                                </tr>
                            </thead>
                            <tbody></tbody>
                        </table>
                    </td>
                </tr>
            </table>
        </div>
        <div id="zg_dialog" title="整改详细信息">
            <table class="tablelist" width="100%">
                <tr>
                    <td width="20%" style="background-color: #319ACF; color: white;">整改信息：</td>
                    <td colspan="3"></td>
                </tr>
                <tr>
                    <td width="20%">开始时间：</td>
                    <td width="30%" id="kssj"></td>
                    <td width="20%">批准时限：</td>
                    <td id="pzsx"></td>
                </tr>
                <tr>
                    <td width="20%">延时状态：</td>
                    <td width="30%" id="lazy_zt_desc"></td>
                    <td width="20%">验收状态：</td>
                    <td id="yszt_desc"></td>
                </tr>
                <tr>
                    <td width="20%">整改备注：</td>
                    <td colspan="3"><textarea id="zgbz" name="zgbz" rows="5" cols="40" disabled="disabled"></textarea></td>
                </tr>
                <tr>
                    <td width="20%">验收人：</td>
                    <td width="30%" id="ysrxm"></td>
                    <td width="20%">验收时间：</td>
                    <td id="yssj"></td>
                </tr>
                <tr>
                    <td width="20%">验收备注：</td>
                    <td colspan="3"><textarea id="ysbz" name="zgbz" rows="5" cols="40" disabled="disabled"></textarea></td>
                </tr>
            </table>
        </div>
        <!-- InstanceEndEditable -->
    </body>
    <!-- InstanceEnd --></html>
