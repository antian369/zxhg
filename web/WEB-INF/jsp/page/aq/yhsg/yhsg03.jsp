
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
        <title><%=SystemUtil.serverDesc%> -- 整改进度汇报</title>
        <!-- InstanceEndEditable -->
        <!-- InstanceBeginEditable name="head" -->
        <script type="text/javascript">
            var outJson = ${out_json};
            var dep = '${sessionScope.ses.dep_id.value}';
            var username = '${sessionScope.ses.username.value}';
            var xm = '${sessionScope.ses.name.value}';
        </script>
        <!-- InstanceEndEditable -->
    </head>
    <body>
        <!-- InstanceBeginEditable name="content" -->
        <div style="margin: 10px auto 10px auto; text-align: center"><h2>整改进度汇报</h2></div>
        <form action="yhsg03.do" method="post" id="search_form" name="search_form" class="sub_form" style="width: 80%;">
            <input type="hidden" id="do" name="do" value="1" />
            <input type="hidden" id="yhdw" name="yhdw" value="${sessionScope.ses.dep_id.value}" />
            <%-- 来源：隐患收购 --%>
            <input type="hidden" id="ly" name="ly" value="1" />
            <%-- 整改结果：正在整改 --%>
            <input type="hidden" id="zgjg" name="zgjg" value="0" />
            <%-- 隐患状态：正在整改 --%>
            <input type="hidden" id="zt" name="zt" value="1" />
            <table width="100%" border="0">
                <tr>
                    <td width="25%">
                        隐患类别：
                        <select id="yhlb" name="yhlb">
                            <option value="" checked>全部</option>
                            <c:forEach items="${requestScope['aq_yh_info.yhlb']}" var="par">
                                <option value="${par.colValue}">${par.valueDesc}</option>
                            </c:forEach>
                        </select>
                    </td>
                    <td width="20%">
                        隐患级别：
                        <select id="yhjb" name="yhjb">
                            <option value="" checked>全部</option>
                            <c:forEach items="${requestScope['aq_yh_info.yhjb']}" var="par">
                                <option value="${par.colValue}">${par.valueDesc}</option>
                            </c:forEach>
                        </select>
                    </td>
                    <td width="25%">
                        隐患名称：<input type="text" id="yhmc" name="yhmc" size="15"/>
                    </td>
                    <td>
                        <input type="submit" id="sub" value="查询" />
                        <span style="color: red">提示：只能查询本单位收购的隐患</span>
                    </td>
                </tr>
            </table>
        </form>
        <hr />
        <div id="container" style="width: 95%;margin: 10px auto 10px auto;">
            <table cellpadding="0" cellspacing="0" border="0" class="display" id="zgjl_table">
                <thead>
                    <tr>
                        <th>隐患名称</th>
                        <th>隐患级别</th>
                        <th>隐患类别</th>
                        <th>整改人</th>
                        <th>整改时间</th>
                        <th>预计完成时间</th>
                        <th>批准时限</th>
                        <th>操作</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${result.value}" var="zgjl" varStatus="xh">
                        <tr>
                            <td align="center">${zgjl.yhmc.value}</td>
                            <td align="center">${zgjl.yhjb_desc.value}</td>
                            <td align="center">${zgjl.yhlb_desc.value}</td>
                            <td align="center">${zgjl.zgrxm.value}</td>
                            <td align="center">${zgjl.kssj.value.detaled}</td>
                            <td align="center">${zgjl.sqsx.value.detaled}</td>
                            <td align="center">${zgjl.pzsx.value.detaled}</td>
                            <td align="center">
                                <a href="#this" class="info" ind="${xh.index}">验收/延时</a>
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
            <form action="yhsg03.do" method="post" id="page_form" name="page_form">
                <%-- 翻页参数，还需要使用js对id=page的项进行赋值 --%>
                <input type="hidden" id="page" name="page" value="${param.page}"/>
                <input type="hidden" name="do" value="1" />
                <input type="hidden" name="yhlb" value="${param.yhlb}" />
                <input type="hidden" name="yhjb" value="${param.yhjb}" />
                <input type="hidden" name="yhmc" value="${param.yhmc}" />
                <input type="hidden" id="yhdw" name="yhdw" value="${sessionScope.ses.dep_id.value}" />
                <input type="hidden" id="ly" name="ly" value="1" /><%-- 来源：隐患收购 --%>
                <input type="hidden" id="zgjg" name="zgjg" value="0" /><%-- 整改结果：正在整改 --%>
                <input type="hidden" id="zt" name="zt" value="1" />
            </form>
            <%-- 页码生成 end --%>
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
                    <td width="40%" align="right">整改备注：</td>
                    <td width="60%" id="zgbz"></td>
                </tr>
            </table>
        </div>
        <%-- 分厂验收 --%>
        <div id="fcys_dialog">
            <form id="fcys_form">
                <input type="hidden" id="fcfcr" name="fcfcr" value="${sessionScope.ses.username.value}" />
                <input type="hidden" id="yh_id" name="yh_id" />
                <input type="hidden" id="zg_id" name="zg_id" />
                <table class="table_input" border="0" width="100%">
                    <tr>
                        <td width="40%" align="right">验收人：</td>
                        <td width="60%">
                            <input type="text" id="fcfcrxm" name="fcfcrxm" fn="nowNull('验收人','#fcys_form #fcfcrxm')" value="${sessionScope.ses.name.value}"/>
                            <span style="color: red">*</span>
                        </td>
                    </tr>
                    <tr>
                        <td width="40%" align="right">验收结果：</td>
                        <td width="60%">
                            <select id="fcysjg" name="fcysjg">
                                <option value="4" selected="selected">合格</option>
                                <option value="1">不合格</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td width="40%" align="right">备注：</td>
                        <td width="60%">
                            <input type="text" id="fcfcbz" name="fcfcbz" />
                        </td>
                    </tr>
                </table>
            </form>
        </div>
        <%-- 延时申请 --%>
        <div id="yssq_dialog">
            <form id="yssq_form">
                <input type="hidden" id="yh_id" name="yh_id" />
                <input type="hidden" id="zg_id" name="zg_id" />
                <table class="table_input" border="0" width="100%">
                    <tr>
                        <td width="40%" align="right">整改人姓名：</td>
                        <td width="60%">
                            <input type="hidden" id="zgr" name="zgr" />
                            <input type="text" id="zgrxm" name="zgrxm" fn="isChinese('整改人姓名',2,'#yssq_form #zgrxm')" value="${sessionScope.ses.name.value}"/>
                            <span style="color: red;">*</span>
                        </td>
                    </tr>
                    <tr>
                        <td width="40%" align="right">整改措施：</td>
                        <td width="60%">
                            <textarea id="zgcs" name="zgcs" rows="5" cols="40" fn="notNull('整改措施','#yssq_form #zgcs')" ></textarea>
                            <span style="color: red;">*</span>
                        </td>
                    </tr>
                    <tr>
                        <td width="40%" align="right">预计整改完成时间：</td>
                        <td width="60%">
                            <input type="text" id="sqsx_1" name="sqsx" fn="notNull('预计整改完成时间','#yssq_form #sqsx_1')" data-role="date" size="12"/>
                            <input type="text" id="sqsx_h" name="sqsx_h" value="0" fn="isNum('预计整改完成时间(时)','#yssq_form #sqsx_h')" size="3"/>时
                            <span style="color: red;">*</span>
                        </td>
                    </tr>
                    <tr>
                        <td width="40%" align="right">整改备注：</td>
                        <td width="60%">
                            <input type="text" id="zgbz" name="zgbz" size="60" />
                        </td>
                    </tr>
                </table>
            </form>
        </div>
        <!-- InstanceEndEditable -->
    </body>
<!-- InstanceEnd --></html>
