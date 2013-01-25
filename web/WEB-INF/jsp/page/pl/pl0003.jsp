
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
        <title><%=SystemUtil.serverDesc%> -- 用户管理</title>
        <!-- InstanceEndEditable -->
        <!-- InstanceBeginEditable name="head" -->
        <!-- InstanceEndEditable -->
    </head>
    <body>
        <!-- InstanceBeginEditable name="content" -->
        <div style="margin: 10px auto 10px auto; text-align: center"><h2>用户管理</h2></div>
        <form action="pl0003.do" method="post" id="search_form" name="search_form" class="sub_form" style="width: 80%;">
            <table width="100%" border="0">
                <tr>
                    <td width="25%">
                        部门：
                        <select id="dep_id" name="dep_id">
                            <option value="" checked>全部</option>
                            <c:forEach items="${deps.value}" var="dep">
                                <option value="${dep.dep_id.value}">${dep.dep_name.value}</option>
                            </c:forEach>
                        </select>
                    </td>
                    <td width="25%">
                        用户名：<input type="text" id="username" name="username" size="15" />
                    </td>
                    <td width="25%">
                        姓名：<input type="text" id="name" name="name" size="8" />
                    </td>
                    <td>
                        <input type="submit" id="sub" value="查询" />
                        <input type="button" id="create_user" value="创建用户" />
                    </td>
                </tr>
            </table>
        </form>
        <hr />
        <div id="container" style="width: 95%;margin: 10px auto 10px auto;">
            <table cellpadding="0" cellspacing="0" border="0" class="display" id="user_table">
                <thead>
                    <tr>
                        <th>用户名</th>
                        <th>姓名</th>
                        <th>角色</th>
                        <th>部门</th>
                        <th>状态</th>
                        <th>操作</th>
                        <th>重置密码</th>
                        <th>特有权限</th>
                        <th>详细</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${result.value}" var="user">
                        <tr>
                            <td align="center">${user.username.value}</td>
                            <td align="center">${user.name.value}</td>
                            <td align="center">${user.role_name.value}</td>
                            <td align="center">${user.dep_name.value}</td>
                            <td align="center" id="${user.username.value}_zt">${user.zt_desc.value}</td>
                            <td align="center">
                                <a href="#this" class="operate" id="${user.username.value}" zt="${user.zt.value}"></a>
                            </td>
                            <td align="center">
                                <a href="#this" class="reset_psw" username="${user.username.value}" >重置</a>
                            </td>
                            <td align="center">
                                <a href="pl0005.do?username=${user.username.value}">授权</a>
                            </td>
                            <td align="center">
                                <a href="#this" class="info" username="${user.username.value}">查看</a>
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
            <form action="pl0003.do" method="post" id="page_form" name="page_form">
                <%-- 翻页参数，还需要使用js对id=page的项进行赋值 --%>
                <input type="hidden" id="page" name="page" value="${param.page}"/>
                <input type="hidden" name="username" value="${param.username}" />
                <input type="hidden" name="name" value="${param.name}" />
                <input type="hidden" name="dep_id" value="${param.dep_id}" />
            </form>
            <%-- 页码生成 end --%>
        </div>
        <%-- dailog提示框 --%>
        <div id="reset_psw_dialog" title="重置密码">
            <p>输入密码：<input type="password" id="password" name="password" /></p>
            <p style="margin-top: 10px">再次输入：<input type="password" id="password_again" name="password_again" /></p>
            <input type="hidden" id="reset_psw_username" />
        </div>
        <div id="create_user_dialog" title="创建用户">
            <form id="create_user_form">
                <table class="table_input" width="100%">
                    <tr>
                        <td width="40%" align="right">用户名：</td>
                        <td>
                            <input type="text" id="username" name="username" fn="username('用户名','#create_user_form #username')" />
                            <span style="color: red">*</span>
                        </td>
                    </tr>
                    <tr>
                        <td width="40%" align="right">密码：</td>
                        <td>
                            <input type="password" id="password" name="password" fn="password('密码','#create_user_form #password')" />
                            <span style="color: red">*</span>
                        </td>
                    </tr>
                    <tr>
                        <td width="40%" align="right">再次输入：</td>
                        <td>
                            <input type="password" id="password_again" name="password_again" />
                            <span style="color: red">*</span>
                        </td>
                    </tr>
                    <tr>
                        <td width="40%" align="right">姓名：</td>
                        <td>
                            <input type="text" id="name" name="name" fn="isChinese('姓名', 2,'#create_user_form #name')" />
                            <span style="color: red">*</span>
                        </td>
                    </tr>
                    <tr>
                        <td width="40%" align="right">手机号：</td>
                        <td>
                            <input type="text" id="tel" name="tel" />
                        </td>
                    </tr>
                    <tr>
                        <td width="40%" align="right">角色：</td>
                        <td>
                            <select id="role_id" name="role_id">
                                <c:forEach items="${roles.value}" var="role">
                                    <option value="${role.role_id.value}">${role.role_name.value}</option>
                                </c:forEach>
                            </select>
                            <span style="color: red">*</span>
                        </td>
                        </td>
                    </tr>
                    <tr>
                        <td width="40%" align="right">部门：</td>
                        <td>
                            <select id="dep_id" name="dep_id">
                                <c:forEach items="${deps.value}" var="dep">
                                    <option value="${dep.dep_id.value}">${dep.dep_name.value}</option>
                                </c:forEach>
                            </select>
                            <span style="color: red">*</span>
                        </td>
                    </tr>
                    <tr>
                        <td width="40%" align="right">职务一：</td>
                        <td>
                            <select id="zw1" name="zw1">
                                <option value="">无</option>
                                <c:forEach items="${requestScope['pl_user.zw']}" var="zw">
                                    <option value="${zw.colValue}">${zw.valueDesc}</option>
                                </c:forEach>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td width="40%" align="right">职务二：</td>
                        <td>
                            <select id="zw2" name="zw2">
                                <option value="">无</option>
                                <c:forEach items="${requestScope['pl_user.zw']}" var="zw">
                                    <option value="${zw.colValue}">${zw.valueDesc}</option>
                                </c:forEach>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td width="40%" align="right">职务三：</td>
                        <td>
                            <select id="zw3" name="zw3">
                                <option value="">无</option>
                                <c:forEach items="${requestScope['pl_user.zw']}" var="zw">
                                    <option value="${zw.colValue}">${zw.valueDesc}</option>
                                </c:forEach>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td width="40%" align="right">职称：</td>
                        <td><input type="text" id="rank" name="rank" /></td>
                    </tr>
                    <tr>
                        <td width="40%" align="right">备注：</td>
                        <td><input type="text" id="bz" name="bz" /></td>
                    </tr>
                </table>
            </form>
        </div>
        <div id="user_info_dialog" class="info_dialog">
            <div style="padding-left: 30%">
            <p><strong>用户名：</strong><span id="username"></span></p>
            <p><strong>姓名：</strong><span id="name"></span></p>
            <p><strong>手机号：</strong><span id="tel"></span></p>
            <p><strong>角色：</strong><span id="role_name"></span></p>
            <p><strong>部门：</strong><span id="dep_name"></span></p>
            <p><strong>职务：</strong><span id="zw1_desc"></span> &nbsp;&nbsp; <span id="zw2_desc"> &nbsp;&nbsp; </span><span id="zw3_desc"></span></p>
            <p><strong>职称：</strong><span id="rank"></span></p>
            <p><strong>创建时间：</strong><span id="create_time"></span></p>
            <p><strong>创建人：</strong><span id="create_user"></span></p>
            <p><strong>状态：</strong><span id="zt_desc"></span></p>
            <p><strong>备注：</strong><span id="bz"></span></p>
            <p><strong>最后登录时间：</strong><span id="last_login_time"></span></p>
            <p><strong>最后登录IP：</strong><span id="last_login_ip"></span></p>
            </div>
        </div>

        <div id="update_user_dialog" class="info_dialog">
            <form id="update_user_form">
                <table class="table_input" width="100%">
                    <tr>
                        <td width="40%" align="right">用户名：</td>
                        <td id="username"></td>
                    </tr>
                    <tr>
                        <td width="40%" align="right">姓名：</td>
                        <td>
                            <input type="text" id="name" name="name" fn="isChinese('姓名', 2,'#update_user_form #name')" />
                            <span style="color: red">*</span>
                        </td>
                    </tr>
                    <tr>
                        <td width="40%" align="right">手机号：</td>
                        <td>
                            <input type="text" id="tel" name="tel" />
                        </td>
                    </tr>
                    <tr>
                        <td width="40%" align="right">角色：</td>
                        <td>
                            <select id="role_id" name="role_id">
                                <c:forEach items="${roles.value}" var="role">
                                    <option value="${role.role_id.value}">${role.role_name.value}</option>
                                </c:forEach>
                            </select>
                            <span style="color: red">*</span>
                        </td>
                        </td>
                    </tr>
                    <tr>
                        <td width="40%" align="right">部门：</td>
                        <td>
                            <select id="dep_id" name="dep_id">
                                <c:forEach items="${deps.value}" var="dep">
                                    <option value="${dep.dep_id.value}">${dep.dep_name.value}</option>
                                </c:forEach>
                            </select>
                            <span style="color: red">*</span>
                        </td>
                    </tr>
                    <tr>
                        <td width="40%" align="right">职务一：</td>
                        <td>
                            <select id="zw1" name="zw1">
                                <option value="">无</option>
                                <c:forEach items="${requestScope['pl_user.zw']}" var="zw">
                                    <option value="${zw.colValue}">${zw.valueDesc}</option>
                                </c:forEach>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td width="40%" align="right">职务二：</td>
                        <td>
                            <select id="zw2" name="zw2">
                                <option value="">无</option>
                                <c:forEach items="${requestScope['pl_user.zw']}" var="zw">
                                    <option value="${zw.colValue}">${zw.valueDesc}</option>
                                </c:forEach>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td width="40%" align="right">职务三：</td>
                        <td>
                            <select id="zw3" name="zw3">
                                <option value="">无</option>
                                <c:forEach items="${requestScope['pl_user.zw']}" var="zw">
                                    <option value="${zw.colValue}">${zw.valueDesc}</option>
                                </c:forEach>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td width="40%" align="right">职称：</td>
                        <td><input type="text" id="rank" name="rank" /></td>
                    </tr>
                    <tr>
                        <td width="40%" align="right">备注：</td>
                        <td><input type="text" id="bz" name="bz" /></td>
                    </tr>
                </table>
            </form>
        </div>
        <!-- InstanceEndEditable -->
    </body>
    <!-- InstanceEnd --></html>
