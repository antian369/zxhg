
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
        <title><%=SystemUtil.serverDesc%> -- 工艺设备管理</title>
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
        <div style="margin: 10px auto 10px auto; text-align: center"><h2>工艺设备管理</h2></div>
        <form action="zlgl02.do" method="post" id="search_form" name="search_form" class="sub_form" style="width: 80%;">
            <input type="hidden" id="do" name="do" value="1" />
            <table width="100%" border="0">
                <tr>
                    <td width="30%">
                        设备名称：
                        <input type="text" id="sbmc" name="sbmc" size="15"/>
                    </td>
                    <td width="30%">
                        工艺流程名称：
                        <input type="text" id="gymc" name="gymc" size="15"/>
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
            <table cellpadding="0" cellspacing="0" border="0" class="display" id="gysb_table">
                <thead>
                    <tr>
                        <th>工艺流程</th>
                        <th>工艺分厂</th>
                        <th>设备位号</th>
                        <th>设备名称</th>
                        <th>型号规格</th>
                        <th>结构类型</th>
                        <th>设备分类</th>
                        <th>修改</th>
                        <th>删除</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${result.value}" var="gysb" varStatus="xh">
                        <tr>
                            <td align="center">${gysb.gymc.value}</td>
                            <td align="center">${gysb.gyfc.value}</td>
                            <td align="center">${gysb.sbwh.value}</td>
                            <td align="center">${gysb.sbmc.value}</td>
                            <td align="center">${gysb.xhgg.value}</td>
                            <td align="center">${gysb.jglx.value}</td>
                            <td align="center">${gysb.sbfl.value}</td>
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
        <div id="gysb_dialog" title="工艺设备新增">
            <form id="gysb_data">
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
                            <input type="text" id="sbwh" name="sbwh" maxlength="3" fn="isNum('设备位号','#gysb_data #sbwh')"/>
                            <span style="color: red">*</span>
                        </td>
                    </tr>
                    <tr>
                        <td width="40%" align="right">设备名称：</td>
                        <td width="60%">
                            <input type="text" id="sbmc" name="sbmc" maxlength="50" fn="notNull('设备名称','#gysb_data #sbmc')"/>
                            <span style="color: red">*</span>
                        </td>
                    </tr>
                    <tr>
                        <td width="40%" align="right">出厂编号：</td>
                        <td width="60%">
                            <input type="text" id="scbh" name="scbh" maxlength="50" fn="notNull('出厂编号','#gysb_data #scbh')"/>
                            <span style="color: red">*</span>
                        </td>
                    </tr>
                    <tr>
                        <td width="40%" align="right">型号规格：</td>
                        <td width="60%">
                            <input type="text" id="xhgg" name="xhgg" maxlength="50" fn="notNull('型号规格','#gysb_data #xhgg')"/>
                            <span style="color: red">*</span>
                        </td>
                    </tr>
                    <tr>
                        <td width="40%" align="right">结构类型：</td>
                        <td width="60%">
                            <input type="text" id="jglx" name="jglx" maxlength="50" fn="notNull('结构类型','#gysb_data #jglx')"/>
                            <span style="color: red">*</span>
                        </td>
                    </tr>
                    <tr>
                        <td width="40%" align="right">设备重量：</td>
                        <td width="60%">
                            <input type="text" id="sbzl" name="sbzl" maxlength="50" fn="notNull('设备重量','#gysb_data #sbzl')"/>
                            <span style="color: red">*</span>
                        </td>
                    </tr>
                    <tr>
                        <td width="40%" align="right">介质成份：</td>
                        <td width="60%">
                            <input type="text" id="jzcf" name="jzcf" maxlength="50" fn="notNull('介质成份','#gysb_data #jzcf')"/>
                            <span style="color: red">*</span>
                        </td>
                    </tr>
                    <tr>
                        <td width="40%" align="right">生产厂家：</td>
                        <td width="60%">
                            <input type="text" id="sccj" name="sccj" maxlength="50" fn="notNull('生产厂家','#gysb_data #sccj')"/>
                            <span style="color: red">*</span>
                        </td>
                    </tr>
                    <tr>
                        <td width="40%" align="right">生产日期：</td>
                        <td width="60%">
                            <input type="text" id="scrq" name="scrq" fn="notNull('生产日期','#gysb_data #scrq')" data-role="date"/>
                            <span style="color: red">*</span>
                        </td>
                    </tr>
                    <tr>
                        <td width="40%" align="right">安装日期：</td>
                        <td width="60%">
                            <input type="text" id="azrq" name="azrq" data-role="date" fn="notNull('安装日期','#gysb_data #azrq')"/>
                            <span style="color: red">*</span>
                        </td>
                    </tr>
                    <tr>
                        <td width="40%" align="right">投用日期：</td>
                        <td width="60%">
                            <input type="text" id="tyrq" name="tyrq" data-role="date" fn="notNull('投用日期','#gysb_data #tyrq')"/>
                            <span style="color: red">*</span>
                        </td>
                    </tr>
                    <tr>
                        <td width="40%" align="right">设备分类：</td>
                        <td width="60%">
                            <input type="text" id="sbfl" name="sbfl" maxlength="50" fn="notNull('设备分类','#gysb_data #sbfl')"/>
                            <span style="color: red">*</span>
                        </td>
                    </tr>
                    <tr>
                        <td width="40%" align="right">技术参数：</td>
                        <td width="60%">
                            <input type="text" id="jscs" name="jscs" maxlength="50" fn="notNull('技术参数','#gysb_data #jscs')"/>
                            <span style="color: red">*</span>
                        </td>
                    </tr>
                    <tr>
                        <td width="40%" align="right">检修周期：</td>
                        <td width="60%">
                            <input type="text" id="jxzq" name="jxzq" maxlength="3" size="4" fn="notNull('技术参数','#gysb_data #jxzq')"/>
                            天
                            <span style="color: red">*</span>
                        </td>
                    </tr>
                </table>
            </form>
        </div>
        <!-- InstanceEndEditable -->
    </body>
    <!-- InstanceEnd --></html>
