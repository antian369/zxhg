
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
        <title><%=SystemUtil.serverDesc%> -- 三违修改</title>
        <!-- InstanceEndEditable -->
        <!-- InstanceBeginEditable name="head" -->
        <script type="text/javascript">
            var outJson = ${out_json};
        </script>
        <!-- InstanceEndEditable -->
    </head>
    <body>
        <!-- InstanceBeginEditable name="content" -->
        <div style="margin: 10px auto 10px auto; text-align: center"><h2>三违修改</h2></div>
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
                        <th>详细</th>
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
            </form>
            <%-- 页码生成 end --%>
        </div>

        <%-- 查看三违 --%>
        <div id="cksw_dialog" title="查看三违">
            <br />
            <table class="table_input" width="90%" class="table_input">
                <tr>
                    <td width="30%" align="right" style="background-color: #319ACF; color: white;">登记：</td>
                    <td></td>
                </tr>
                <tr>
                    <td width="30%" align="right">三违编号：</td>
                    <td id="sw_id"></td>
                </tr>
                <tr>
                    <td width="30%" align="right">三违时间：</td>
                    <td id="swsj"></td>
                </tr>
                <tr>
                    <td width="30%" align="right">三违地点：</td>
                    <td id="swdd"></td>
                </tr>
                <tr>
                    <td width="30%" align="right">三违人员：</td>
                    <td id="swry"></td>
                </tr>
                <tr>
                    <td width="30%" align="right">三违现象：</td>
                    <td><textarea id="swxx" disabled="disabled" cols="30" rows="5"></textarea></td>
                </tr>
                <tr>
                    <td width="30%" align="right">三违分类：</td>
                    <td id="swfl_desc"></td>
                </tr>
                <tr>
                    <td width="30%" align="right">三违单位：</td>
                    <td>
                        <select id="ssdw" disabled="disable">
                            <c:forEach items="${deps.value}" var="dep">
                                <option value="${dep.dep_id.value}">${dep.dep_name.value}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td width="30%" align="right">三违备注：</td>
                    <td id="swbz"></td>
                </tr>
                <tr>
                    <td width="30%" align="right">发现人：</td>
                    <td id="fxr"></td>
                </tr>
                <tr>
                    <td width="30%" align="right">发现人姓名：</td>
                    <td id="fxrxm"></td>
                </tr>
                <tr>
                    <td width="30%" align="right">发现人所在部门：</td>
                    <td>
                        <select id="fxrbm" disabled="disable">
                            <c:forEach items="${deps.value}" var="dep">
                                <option value="${dep.dep_id.value}">${dep.dep_name.value}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td width="30%" align="right">核实：</td>
                    <td id="hslx_desc"></td>
                </tr>
                <tr>
                    <td width="30%" align="right">录入人：</td>
                    <td id="lrr"></td>
                </tr>
                <tr>
                    <td width="30%" align="right">录入人部门：</td>
                    <td>
                        <select id="lrrbm" disabled="disable">
                            <c:forEach items="${deps.value}" var="dep">
                                <option value="${dep.dep_id.value}">${dep.dep_name.value}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td width="30%" align="right">录入时间：</td>
                    <td id="lrsj"></td>
                <tr>
                    <td width="30%" align="right" style="background-color: #319ACF; color: white;">核实：</td>
                    <td></td>
                </tr>
                <tr>
                    <td width="30%" align="right">核实人：</td>
                    <td id="hsr"></td>
                </tr>
                <tr>
                    <td width="30%" align="right">核实人姓名：</td>
                    <td id="hsrxm"></td>
                </tr>
                <tr>
                    <td width="30%" align="right">核实人所在部门：</td>
                    <td>
                        <select id="hsrbm" disabled="disable">
                            <c:forEach items="${deps.value}" var="dep">
                                <option value="${dep.dep_id.value}">${dep.dep_name.value}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td width="30%" align="right">核实时间：</td>
                    <td id="hssj"></td>
                </tr>
                <tr>
                    <td width="30%" align="right">处罚依据：</td>
                    <td id="cfyj"></td>
                </tr>
                <tr>
                    <td width="30%" align="right">处罚措施：</td>
                    <td id="cfcs"></td>
                </tr>
                <tr>
                    <td width="30%" align="right">处罚金额：</td>
                    <td id="cfje"></td>
                </tr>
                <tr>
                    <td width="30%" align="right">核实备注：</td>
                    <td id="hsbz"></td>
                </tr>
                <tr>
                    <td width="30%" align="right" style="background-color: #319ACF; color: white;">确认：</td>
                    <td></td>
                </tr>
                <tr>
                    <td width="30%" align="right">确认人：</td>
                    <td id="qrr"></td>
                </tr>
                <tr>
                    <td width="30%" align="right">确认人姓名：</td>
                    <td id="qrrxm"></td>
                </tr>
                <tr>
                    <td width="30%" align="right">确实人所在部门：</td>
                    <td>
                        <select id="qrrbm" disabled="disable">
                            <c:forEach items="${deps.value}" var="dep">
                                <option value="${dep.dep_id.value}">${dep.dep_name.value}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td width="30%" align="right">确认时间：</td>
                    <td id="qrsj"></td>
                </tr>
                <tr>
                    <td width="30%" align="right">确认备注：</td>
                    <td id="qrbz"></td>
                </tr>
                <tr>
                    <td width="30%" align="right">状态：</td>
                    <td id="zt_desc"></td>
                </tr>
            </table>
        </div>
        <%-- 修改三违 --%>
        <div id="xgsw_dialog" title="修改三违">
            <p style="color: red">注：只有录入人才能修改三违</p>
            <form id="xgsw_form">
                <input type="hidden" id="sw_id" name="sw_id" />
                <table class="table_input" width="100%">
                    <tr>
                        <td width="40%" align="right">三违发现时间：</td>
                        <td>
                            <input type="text" id="swsj" name="swsj" fn="notNull('三违时间','#xgsw_dialog #swsj')" style="width: 100px" data-role="date"/>
                            <input type="text" id='swsj_h' name="swsj_h" fn="isNum('三违时间(小时)', '#xgsw_dialog #swsj_h')" maxlength="2" style="width: 20px" />时
                            <input type="text" id='swsj_m' name="swsj_m" fn="isNum('三违时间(分钟)', '#xgsw_dialog #swsj_m')" maxlength="2" style="width: 20px" />分
                            <span style="color: red">*</span>
                        </td>
                    </tr>
                    <tr>
                        <td width="40%" align="right">三违地点：</td>
                        <td>
                            <input type="text" id="swdd" name="swdd" fn="notNull('三违地点','#xgsw_form #swdd')" size="40" />
                            <span style="color: red">*</span>
                        </td>
                    </tr>
                    <tr>
                        <td width="40%" align="right">三违人员：</td>
                        <td>
                            <input type="text" id="swry" name="swry" fn="notNull('三违人员','#xgsw_form #swry')" />
                            <span style="color: red">*</span>
                        </td>
                    </tr>
                    <tr>
                        <td width="40%" align="right">三违现象：</td>
                        <td>
                            <textarea id="swxx" name="swxx" fn="notNull('三违现象','#xgsw_form #swxx')" cols="30" rows="5"></textarea>
                            <span style="color: red">*</span>
                        </td>
                    </tr>
                    <tr>
                        <td width="40%" align="right">三违分类：</td>
                        <td>
                            <select id="swfl" name="swfl">
                                <c:forEach items="${requestScope['aq_sw_info.swfl']}" var="par">
                                    <option value="${par.colValue}">${par.valueDesc}</option>
                                </c:forEach>
                            </select>
                            <span style="color: red">*</span>
                        </td>
                    </tr>
                    <tr>
                        <td width="40%" align="right">三违单位：</td>
                        <td>
                            <select id="ssdw" name="ssdw">
                                <c:forEach items="${deps.value}" var="dep">
                                    <option value="${dep.dep_id.value}">${dep.dep_name.value}</option>
                                </c:forEach>
                            </select>
                            <span style="color: red">*</span>
                        </td>
                    </tr>
                    <tr>
                        <td width="40%" align="right">三违核实：</td>
                        <td>
                            <select id="hslx" name="hslx">
                                <c:forEach items="${requestScope['aq_sw_info.hslx']}" var="par">
                                    <option value="${par.colValue}">${par.valueDesc}</option>
                                </c:forEach>
                            </select>
                            <span style="color: red">*</span>
                        </td>
                    </tr>
                    <tr>
                        <td width="40%" align="right">三违备注：</td>
                        <td><input type="text" id="swbz" name="swbz" size="40" /></td>
                    </tr>
                    <tr>
                        <td width="40%" align="right">发现人姓名：</td>
                        <td>
                            <input type="text" id="fxrxm" name="fxrxm" fn="notNull('发现人姓名', '#xgsw_form #fxrxm')" />
                            <span style="color: red">*</span>
                        </td>
                    </tr>
                    <tr>
                        <td width="40%" align="right">发现人所在部门：</td>
                        <td>
                            <select id="fxrbm" name="fxrbm">
                                <option value="">其它</option>
                                <c:forEach items="${deps.value}" var="dep">
                                    <option value="${dep.dep_id.value}">${dep.dep_name.value}</option>
                                </c:forEach>
                            </select>
                            <span style="color: red">*</span>
                        </td>
                    </tr>
                </table>
                <input type="hidden" id="fxr" name="fxr" />
            </form>
        </div>
        <!-- InstanceEndEditable -->
    </body>
    <!-- InstanceEnd --></html>
