<%@page import="com.soa.util.AjaxUtil"%>
<%@page import="org.springframework.dao.DataAccessException"%>
<%@page import="com.lianzt.commondata.AbstractCommonData"%>
<%@page contentType="text/html; charset=utf-8" %>
<%@page import="com.lianzt.commondata.DataConvertFactory"%>
<%@page import="com.lianzt.util.LogUtil"%>
<%@page import="com.soa.exception.GlobalException"%>
<%@page import="com.lianzt.util.StringUtil"%>
<%@page import="com.soa.util.SystemUtil"%>
<%@page import="com.soa.service.impl.UtilService"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    //获取异常信息
    Exception e = (Exception) request.getAttribute("exception");
    boolean isSave = true;
    String errMsg = "";
    GlobalException ge = null;
    if (e instanceof GlobalException) {
        ge = (GlobalException) e;
        if (ge.getErrorCode() < 900000) {
            //业务异常
            request.setAttribute("errorMsg", ge.getErrorMsg());
            isSave = false;
        }
        errMsg = ge.getErrorMsg();
    } else {
        errMsg = "系统异常，请稍后重试！";
    }
    if (isSave) {
        UtilService us = (UtilService) SystemUtil.wac.getBean("utilServiceImpl");
        us.saveError(e);//保存异常
    }
    if ("ajax".equals((String) request.getAttribute("_type"))) {
        AbstractCommonData acd = DataConvertFactory.getInstance();
        AbstractCommonData head = acd.getDataValue("head");
        if (e instanceof GlobalException) {
            ge = (GlobalException) e;
            head.putStringValue("response_code", ge.getErrorCode() + "");
            head.putStringValue("response_desc", ge.getErrorMsg());
        } else if (e instanceof DataAccessException) {
            head.putStringValue("response_code", "999998");
            head.putStringValue("response_desc", "数据库异常，请稍后重试！ " + e.toString());
        } else {
            head.putStringValue("response_code", "999999");
            head.putStringValue("response_desc", "系统异常，请稍后重试！ " + e.toString());
        }
        AjaxUtil.sendJsonData(out, acd);
    } else {
        //判断是否为未登录，如果是，跳转到登录页面
        if (ge != null && ge.getErrorCode() == 200023) {
            //--------------------------
            response.sendRedirect(request.getContextPath() + "/index.do");;
        }else if (ge != null && ge.getErrorCode() == 999972) {
            //判断是否为上kindeditor组件上传文件
%>
{'error':1, 'message':'<%=ge.getErrorMsg()%>'}
<%
} else {
            //其它情况返回错误页面
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="application/xhtml+xml;charset=UTF-8"/>
        <meta http-equiv="Cache-control" content="no-cache" />
        <title>新乡中新化工 -- <%=errMsg%></title>
    </head>
    <body>
        <%
            if (LogUtil.isDebug()) {
                out.print("<h1>出错了</h1><br />");
                out.print(StringUtil.getExceptionStack(e).replaceAll("\n", "<br/>"));
        %>
        <br />
        <br />
        <a href="javascript:history.go(-1)">返回上一页</a>
        <a href="<c:url value="/index.do" />">返回主页</a>
        <br />
        <br />
        <%} else {%>
        <br />
        <script type="text/javascript">
            alert("<%=errMsg%>");
            history.go(-1);
        </script>
        <a href="<c:url value="/index.do" />">返回主页</a>
        <%}%>
    </body>
</html>
<%}
    }%>