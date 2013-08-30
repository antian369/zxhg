
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
    <title><%=SystemUtil.serverDesc%> -- 用户登录历史</title>
    <!-- InstanceEndEditable -->
    <!-- InstanceBeginEditable name="head" -->
    <!-- InstanceEndEditable -->
  </head>
  <body>
    <!-- InstanceBeginEditable name="content" -->
    <div style="margin: 10px auto 10px auto; text-align: center"><h2>登录历史</h2></div>
    <hr />
    <form id="search_form" name="search_form" class="sub_form" style="width: 80%;">
      <table width="100%" border="0">
        <tr>
          <td width="55%" align="right">
            日期：
            <select id="year" name="year">
              <option value="2012">2012</option>
              <option value="2013">2013</option>
              <option value="2014">2014</option>
              <option value="2015">2015</option>
              <option value="2016">2016</option>
              <option value="2017">2017</option>
              <option value="2018">2018</option>
            </select>年
            <select id="month" name="month">
              <option value="01">1</option>
              <option value="02">2</option>
              <option value="03">3</option>
              <option value="04">4</option>
              <option value="05">5</option>
              <option value="06">6</option>
              <option value="07">7</option>
              <option value="08">8</option>
              <option value="09">9</option>
              <option value="10">10</option>
              <option value="11">11</option>
              <option value="12">12</option>
            </select>月
          <td>
            <input type="button" id="search" value="查询" />
          </td>
        </tr>
      </table>
    </form>
    <div id="container" style="width: 95%;margin: 10px auto 10px auto;" align="center">
      <table class="tablelist" id="users">
        <thead>
          <tr>
            <th width="20%" data-key="username">用户名</th>
            <th width="20%" data-key="name">姓名</th>
            <th width="15%" data-key="lt">登录次数</th>
            <th width="15%" data-key="d">活跃天数</th>
            <th width="20%" data-key="ol">在线总时长(分钟)</th>
            <th data-key="opt">操作</th>
          </tr>
        </thead>
        <tbody>
        </tbody>
      </table>
    </div>

    <%-- 登录详细 --%>
    <div id="login_dialog" title="登录历史详细">
      <table id="logins" class="tablelist" width="95%">
        <thead>
          <tr>
            <th width="15%" data-key="name">姓名</th>
            <th width="20%" data-key="login_ip">登录IP</th>
            <th width="20%" data-key="login_time">登录时间</th>
            <th width="20%" data-key="logout_time">退出时间</th>
            <th width="10%" data-key="on_line">在线时长</th>
            <th data-key="zt_desc">状态</th>
          </tr>
        </thead>
        <tbody></tbody>
      </table>
    </div>
    <!-- InstanceEndEditable -->
  </body>
  <!-- InstanceEnd --></html>
