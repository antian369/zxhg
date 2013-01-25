
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
        <title><%=SystemUtil.serverDesc%> -- 设备备品管理</title>
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
        <div style="margin: 10px auto 10px auto; text-align: center"><h2>设备备品管理</h2></div>
        <form action="zlgl02.do" method="post" id="search_form" name="search_form" class="sub_form" style="width: 80%;">
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
                    <td width="20%">
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
                        <th>修改</th>
                        <th>删除</th>
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
        <div id="sbbp_dialog" title="设备备品新增">
            <form id="sbbp_data">
                <table width="100%" class="table_input" border="0">
                <tr>
                    <td width="40%" align="right">工艺流程：</td>
                    <td width="60%">
                        <select id="gybh" name="gybh">
                            <option>#001:煤化工工艺</option>
                            <option>#002:甲醇生产工艺</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td width="40%" align="right">设备位号：</td>
                    <td width="60%">
                            <input type="text" id="sbwh" name="sbwh" maxlength="3" fn="isNum('设备位号','#sbbp_data #sbwh')"/>
                            <span style="color: red">*</span>
                    </td>
                </tr>
                <tr>
                    <td width="40%" align="right">设备名称：</td>
                    <td width="60%">
                            <input type="text" id="sbmc" name="sbmc" maxlength="50" fn="notNull('设备名称','#sbbp_data #sbmc')"/>
                            <span style="color: red">*</span>
                    </td>
                </tr>
                <tr>
                    <td width="40%" align="right">备品名称：</td>
                    <td width="60%">
                            <input type="text" id="bpmc" name="bpmc" maxlength="50" fn="notNull('备品名称','#sbbp_data #bpmc')"/>
                            <span style="color: red">*</span>
                    </td>
                </tr>
                <tr>
                    <td width="40%" align="right">规格型号：</td>
                    <td width="60%">
                            <input type="text" id="bpggxh" name="bpggxh" maxlength="50" fn="notNull('规格型号','#sbbp_data #bpggxh')"/>
                            <span style="color: red">*</span>
                    </td>
                </tr>
                <tr>
                    <td width="40%" align="right">设计寿命：</td>
                    <td width="60%">
                            <input type="text" id="bpsjsm" name="bpsjsm" maxlength="50" fn="notNull('设计寿命','#sbbp_data #bpsjsm')"/>
                            <span style="color: red">*</span>
                    </td>
                </tr>
                <tr>
                    <td width="40%" align="right">已使用寿命：</td>
                    <td width="60%">
                            <input type="text" id="bpyysm" name="bpyysm" maxlength="50" fn="notNull('已使用寿命','#sbbp_data #bpyysm')"/>
                            <span style="color: red">*</span>
                    </td>
                </tr>
                <tr>
                    <td width="40%" align="right">数量：</td>
                    <td width="60%">
                            <input type="text" id="bpsl" name="bpsl" maxlength="50" fn="isNum('数量','#sbbp_data #bpsl')"/>
                            <span style="color: red">*</span>
                    </td>
                </tr>
                <tr>
                    <td width="40%" align="right">材质：</td>
                    <td width="60%">
                            <input type="text" id="bpcz" name="bpcz" maxlength="50" fn="notNull('材质','#sbbp_data #bpcz')"/>
                            <span style="color: red">*</span>
                    </td>
                </tr>
                <tr>
                    <td width="40%" align="right">图号：</td>
                    <td width="60%">
                            <input type="text" id="bpth" name="bpth" fn="notNull('图号','#sbbp_data #bpth')" />
                            <span style="color: red">*</span>
                    </td>
                </tr>
                <tr>
                    <td width="40%" align="right">原生产厂家：</td>
                    <td width="60%">
                            <input type="text" id="bpycj" name="bpycj" fn="notNull('原生产厂家','#sbbp_data #bpycj')"/>
                            <span style="color: red">*</span>
                    </td>
                </tr>
                <tr>
                    <td width="40%" align="right">现使用生产厂家：</td>
                    <td width="60%">
                            <input type="text" id="bpxcj" name="bpxcj" maxlength="50" fn="notNull('现使用生产厂家','#sbbp_data #bpxcj')"/>
                            <span style="color: red">*</span>
                    </td>
                </tr>
                <tr>
                    <td width="40%" align="right">最近一次更换时间：</td>
                    <td width="60%">
                            <input type="text" id="bpzjsj" name="bpzjsj" data-role="date" fn="notNull('最近一次更换时间','#sbbp_data #bpzjsj')"/>
                            <span style="color: red">*</span>
                    </td>
                </tr>
                <tr>
                    <td width="40%" align="right">更换原因分析：</td>
                    <td width="60%">
                            <input type="text" id="bpghyy" name="bpghyy" maxlength="50" fn="notNull('更换原因分析','#sbbp_data #bpghyy')"/>
                            <span style="color: red">*</span>
                    </td>
                </tr>
                <tr>
                    <td width="40%" align="right">费用：</td>
                    <td width="60%">
                            <input type="text" id="bpfy" name="bpfy" maxlength="50" fn="isNum('费用','#sbbp_data #bpfy')"/>
                            <span style="color: red">*</span>
                    </td>
                </tr>
                </table>
            </form>
        </div>
        <!-- InstanceEndEditable -->
    </body>
    <!-- InstanceEnd --></html>
