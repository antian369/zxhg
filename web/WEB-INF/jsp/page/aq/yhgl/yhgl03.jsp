
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
        <title><%=SystemUtil.serverDesc%> -- 隐患收购查询</title>
        <!-- InstanceEndEditable -->
        <!-- InstanceBeginEditable name="head" -->
        <script type="text/javascript">
            var outJson = ${out_json};
        </script>
        <!-- InstanceEndEditable -->
    </head>
    <body>
        <!-- InstanceBeginEditable name="content" -->
        <div style="margin: 10px auto 10px auto; text-align: center"><h2>隐患收购查询</h2></div>
        <form action="yhgl03.do" method="post" id="search_form" name="search_form" class="sub_form" style="width: 80%;">
            <input type="hidden" id="do" name="do" value="1" />
            <table width="100%" border="0">
                <tr>
                    <td width="25%">
                        状态：
                        <select id="zt" name="zt">
                            <option value="" checked>全部</option>
                            <c:forEach items="${requestScope['aq_yh_info.zt']}" var="par">
                                <option value="${par.colValue}">${par.valueDesc}</option>
                            </c:forEach>
                        </select>
                    </td>
                    <td width="25%">
                        隐患类别：
                        <select id="yhlb" name="yhlb">
                            <option value="" checked>全部</option>
                            <c:forEach items="${requestScope['aq_yh_info.yhlb']}" var="par">
                                <option value="${par.colValue}">${par.valueDesc}</option>
                            </c:forEach>
                        </select>
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
                        <input type="submit" id="sub" value="查询" />
                    </td>
                </tr>
                <tr>
                    <td width="25%">
                        隐患级别：
                        <select id="yhjb" name="yhjb">
                            <option value="" checked>全部</option>
                            <c:forEach items="${requestScope['aq_yh_info.yhjb']}" var="par">
                                <option value="${par.colValue}">${par.valueDesc}</option>
                            </c:forEach>
                        </select>
                    </td>
                    <td width="25%">
                        隐患名称：<input type="text" id="yhmc" name="yhmc" size="10"/>
                    </td>
                    <td width="25%">
                        发现人：<input type="text" id="fxrxm" name="fxrxm" size="8"/>
                    </td>
                    <td>&nbsp;</td>
                </tr>
            </table>
        </form>
        <hr />
        <div id="container" style="width: 95%;margin: 10px auto 10px auto;">
            <table cellpadding="0" cellspacing="0" border="0" class="display" id="yhsg_table">
                <thead>
                    <tr>
                        <th>隐患名称</th>
                        <th>隐患级别</th>
                        <th>隐患类别</th>
                        <th>发现人</th>
                        <th>隐患单位</th>
                        <th>状态</th>
                        <th>登记时间</th>
                        <th>详细</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${result.value}" var="yhsg" varStatus="xh">
                        <tr>
                            <td align="center">${yhsg.yhmc.value}</td>
                            <td align="center">${yhsg.yhjb_desc.value}</td>
                            <td align="center">${yhsg.yhlb_desc.value}</td>
                            <td align="center">${yhsg.fxrxm.value}</td>
                            <td align="center" select="${yhsg.yhdw.value}" class="table_view_select" target_select="#yhdw"></td>
                            <td align="center">${yhsg.zt_desc.value}</td>
                            <td align="center">${yhsg.djsj.value.detaled}</td>
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
            <form action="yhgl03.do" method="post" id="page_form" name="page_form">
                <%-- 翻页参数，还需要使用js对id=page的项进行赋值 --%>
                <input type="hidden" id="page" name="page" value="${param.page}"/>
                <input type="hidden" name="do" value="1" />
                <input type="hidden" name="zt" value="${param.zt}" />
                <input type="hidden" name="yhdw" value="${param.yhdw}" />
                <input type="hidden" name="yhlb" value="${param.yhlb}" />
                <input type="hidden" name="yhjb" value="${param.yhjb}" />
                <input type="hidden" name="yhmc" value="${param.yhmc}" />
                <input type="hidden" name="fxrxm" value="${param.fxrxm}" />
            </form>
            <%-- 页码生成 end --%>
        </div>
        <%-- 详细信息 --%>
        <div id="yhsg_dialog" title="自述旬报详细信息">
            <table width="100%" class="table_input" border="0">
                <tr>
                    <td width="30%" align="right" style="background-color: #319ACF; color: white;">隐患收购信息：</td>
                    <td></td>
                </tr>
                <tr>
                    <td width="40%" align="right">发现人姓名：</td>
                    <td width="60%" id="fxrxm"></td>
                </tr>
                <tr>
                    <td width="40%" align="right">登记时间：</td>
                    <td width="60%" id="djsj"></td>
                </tr>
                <tr>
                    <td width="40%" align="right">核准人姓名：</td>
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
                    <td width="60%" id="sgzt"></td>
                </tr>
                <tr>
                    <td width="30%" align="right" style="background-color: #319ACF; color: white;">隐患信息：</td>
                    <td></td>
                </tr>
                <tr>
                    <td width="40%" align="right">隐患名称：</td>
                    <td width="60%" id="yhmc"></td>
                </tr>
                <tr>
                    <td width="40%" align="right">隐患类别：</td>
                    <td width="60%" id="yhlb_desc"></td>
                </tr>
                <tr>
                    <td width="40%" align="right">隐患描述：</td>
                    <td width="60%" id="yhms"></td>
                </tr>
                <tr>
                    <td width="40%" align="right">隐患级别：</td>
                    <td width="60%" id="yhjb_desc"></td>
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
                    <td width="40%" align="right">隐患状态：</td>
                    <td width="60%" id="zt_desc"></td>
                </tr>
                <tr>
                    <td width="40%" align="right">整改详细：</td>
                    <td width="60%" id="zgxx"></td>
                </tr>
            </table>
        </div>

        <%-- 整改详细信息 --%>
        <div id="zgjl_dialog" title="整改详细信息">
            <table class="table_input" border="0" width="100%">
                <tr>
                    <td width="30%" align="right" style="background-color: #319ACF; color: white;">整改信息：</td>
                    <td></td>
                </tr>
                <tr>
                    <td width="40%" align="right">整改人姓名：</td>
                    <td width="60%" id="zgrxm"></td>
                </tr>
                <tr>
                    <td width="40%" align="right">整改措施：</td>
                    <td width="60%" id="zgcs"></td>
                </tr>
                <tr>
                    <td width="40%" align="right">开始时间：</td>
                    <td width="60%" id="kssj"></td>
                </tr>
                <tr>
                    <td width="40%" align="right">预计整改完成时间：</td>
                    <td width="60%" id="sqsx"></td>
                </tr>
                <tr>
                    <td width="40%" align="right">批准时限：</td>
                    <td width="60%" id="pzsx"></td>
                </tr>
                <tr>
                    <td width="40%" align="right">整改结果：</td>
                    <td width="60%" id="zgjg_desc"></td>
                </tr>
                <tr>
                    <td width="40%" align="right">整改备注：</td>
                    <td width="60%" id="zgbz"></td>
                </tr>
                <tr>
                    <td width="30%" align="right" style="background-color: #319ACF; color: white;">分厂验收：</td>
                    <td></td>
                </tr>
                <tr>
                    <td width="40%" align="right">验收人：</td>
                    <td width="60%" id="fcfcrxm"></td>
                </tr>
                <tr>
                    <td width="40%" align="right">验收时间：</td>
                    <td width="60%" id="fcsj"></td>
                </tr>
                <tr>
                    <td width="40%" align="right">备注：</td>
                    <td width="60%" id="fcfcbz"></td>
                </tr>
                <tr>
                    <td width="30%" align="right" style="background-color: #319ACF; color: white;">公司验收：</td>
                    <td></td>
                </tr>
                <tr>
                    <td width="40%" align="right">验收人：</td>
                    <td width="60%" id="gsfcrxm"></td>
                </tr>
                <tr>
                    <td width="40%" align="right">验收时间：</td>
                    <td width="60%" id="gsfcsj"></td>
                </tr>
                <tr>
                    <td width="40%" align="right">备注：</td>
                    <td width="60%" id="gsfcbz"></td>
                </tr>
            </table>
        </div>
        <!-- InstanceEndEditable -->
    </body>
<!-- InstanceEnd --></html>
