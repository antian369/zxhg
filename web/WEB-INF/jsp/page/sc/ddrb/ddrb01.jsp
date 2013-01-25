
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
        <title><%=SystemUtil.serverDesc%> -- 生产调度日报</title>
        <!-- InstanceEndEditable -->
        <!-- InstanceBeginEditable name="head" -->
        <script type="text/javascript">
            var dep = '${sessionScope.ses.dep_id.value}';
        </script>
        <!-- InstanceEndEditable -->
    </head>
    <body>
        <!-- InstanceBeginEditable name="content" -->

        <div style="margin: 10px auto 10px auto; text-align: center">
            <h2><span id="scrq_span" style="font-family:Tahoma,Arial,Helvetica,sans-serif"></span>生产调度日报</h2></div>
        <hr />
        <form id="search_form" name="search_form" class="sub_form" style="width: 80%;">
            <div align="center">
                日期：
                <input type="text" id="scrq" name="scrq" data-role="date" size="12" fn="notNull('日期', '#search_form #scrq')" />
                <input type="button" id="create" name="create" value="生成" />
                <input type="button" id="auto_create" name="auto_create" value="自动生成" />
                <input type="button" id="save" name="save" value="保存" />
            </div>
        </form>
        <table cellpadding="0" cellspacing="0" border="0" class="tablelist" id="ddrb_table" width="90%" style="margin: 10px auto 5px auto;">
            <tr>
                <td colspan="2" align="left"><strong>一、主要产量消耗完成情况：</strong></td>
                <td colspan="4" align="center"><strong>生产安全运行：</strong></td>
                <td colspan="4" align="center"><strong>连续运行：</strong></td>
            </tr>
            <tr>
                <th width="5%" rowspan="14">产量</th>
                <th width="22%">班次</th>
                <th width="9%">中班</th>
                <th width="9%">夜班</th>
                <th width="9%">白班</th>
                <th width="9%">日合计</th>
                <th width="9%">月累计</th>
                <th width="9%">日超欠</th>
                <th width="9%">月超欠</th>
                <th>库存</th>
            </tr>
            <tr>
                <td>粗醇（t）</td>
                <td id="z_001">&nbsp;</td>
                <td id="y_001">&nbsp;</td>
                <td id="b_001">&nbsp;</td>
                <td id="rhj_001">&nbsp;</td>
                <td id="yhj_001">&nbsp;</td>
                <td id="rcq_001">&nbsp;</td>
                <td id="ycq_001">&nbsp;</td>
                <td id="kc_001">&nbsp;</td>
            </tr>
            <tr>
                <td>精醇（t）</td>
                <td id="z_002">&nbsp;</td>
                <td id="y_002">&nbsp;</td>
                <td id="b_002">&nbsp;</td>
                <td id="rhj_002">&nbsp;</td>
                <td id="yhj_002">&nbsp;</td>
                <td id="rcq_002">&nbsp;</td>
                <td id="ycq_002">&nbsp;</td>
                <td id="kc_002">&nbsp;</td>
            </tr>
            <tr>
                <td>送乙二醇合成气（kNm³）</td>
                <td id="z_012">&nbsp;</td>
                <td id="y_012">&nbsp;</td>
                <td id="b_012">&nbsp;</td>
                <td id="rhj_012">&nbsp;</td>
                <td id="yhj_012">&nbsp;</td>
                <td id="rcq_012">&nbsp;</td>
                <td id="ycq_012">&nbsp;</td>
                <td id="kc_012">&nbsp;</td>
            </tr>
            <tr>
                <td>液氧（t）</td>
                <td id="z_003">&nbsp;</td>
                <td id="y_003">&nbsp;</td>
                <td id="b_003">&nbsp;</td>
                <td id="rhj_003">&nbsp;</td>
                <td id="yhj_003">&nbsp;</td>
                <td id="rcq_003">&nbsp;</td>
                <td id="ycq_003">&nbsp;</td>
                <td id="kc_003">&nbsp;</td>
            </tr>
            <tr>
                <td>液氮（t）</td>
                <td id="z_004">&nbsp;</td>
                <td id="y_004">&nbsp;</td>
                <td id="b_004">&nbsp;</td>
                <td id="rhj_004">&nbsp;</td>
                <td id="yhj_004">&nbsp;</td>
                <td id="rcq_004">&nbsp;</td>
                <td id="ycq_004">&nbsp;</td>
                <td id="kc_004">&nbsp;</td>
            </tr>
            <tr>
                <td>蒸汽（t)</td>
                <td id="z_005">&nbsp;</td>
                <td id="y_005">&nbsp;</td>
                <td id="b_005">&nbsp;</td>
                <td id="rhj_005">&nbsp;</td>
                <td id="yhj_005">&nbsp;</td>
                <td id="rcq_005">&nbsp;</td>
                <td id="ycq_005">&nbsp;</td>
                <td id="kc_005">&nbsp;</td>
            </tr>
            <tr>
                <td>送永金蒸汽（t)</td>
                <td id="z_013">&nbsp;</td>
                <td id="y_013">&nbsp;</td>
                <td id="b_013">&nbsp;</td>
                <td id="rhj_013">&nbsp;</td>
                <td id="yhj_013">&nbsp;</td>
                <td id="rcq_013">&nbsp;</td>
                <td id="ycq_013">&nbsp;</td>
                <td id="kc_013">&nbsp;</td>
            </tr>
            <tr>
                <td>金山化工用蒸汽（t）</td>
                <td id="z_006">&nbsp;</td>
                <td id="y_006">&nbsp;</td>
                <td id="b_006">&nbsp;</td>
                <td id="rhj_006">&nbsp;</td>
                <td id="yhj_006">&nbsp;</td>
                <td id="rcq_006">&nbsp;</td>
                <td id="ycq_006">&nbsp;</td>
                <td id="kc_006">&nbsp;</td>
            </tr>
            <tr>
                <td>硫磺（t）</td>
                <td id="z_007">&nbsp;</td>
                <td id="y_007">&nbsp;</td>
                <td id="b_007">&nbsp;</td>
                <td id="rhj_007">&nbsp;</td>
                <td id="yhj_007">&nbsp;</td>
                <td id="rcq_007">&nbsp;</td>
                <td id="ycq_007">&nbsp;</td>
                <td id="kc_007">&nbsp;</td>
            </tr>
            <tr>
                <td>硫酸铵（t）</td>
                <td id="z_008">&nbsp;</td>
                <td id="y_008">&nbsp;</td>
                <td id="b_008">&nbsp;</td>
                <td id="rhj_008">&nbsp;</td>
                <td id="yhj_008">&nbsp;</td>
                <td id="rcq_008">&nbsp;</td>
                <td id="ycq_008">&nbsp;</td>
                <td id="kc_008">&nbsp;</td>
            </tr>
            <tr>
                <td>自发电（万度 )</td>
                <td id="z_009">&nbsp;</td>
                <td id="y_009">&nbsp;</td>
                <td id="b_009">&nbsp;</td>
                <td id="rhj_009">&nbsp;</td>
                <td id="yhj_009">&nbsp;</td>
                <td id="rcq_009">&nbsp;</td>
                <td id="ycq_009">&nbsp;</td>
                <td id="kc_009">&nbsp;</td>
            </tr>
            <tr>
                <td>有效煤气（kNm³）</td>
                <td id="z_010">&nbsp;</td>
                <td id="y_010">&nbsp;</td>
                <td id="b_010">&nbsp;</td>
                <td id="rhj_010">&nbsp;</td>
                <td id="yhj_010">&nbsp;</td>
                <td id="rcq_010">&nbsp;</td>
                <td id="ycq_010">&nbsp;</td>
                <td id="kc_010">&nbsp;</td>
            </tr>
            <tr>
                <td>液氩（t）</td>
                <td id="z_011">&nbsp;</td>
                <td id="y_011">&nbsp;</td>
                <td id="b_011">&nbsp;</td>
                <td id="rhj_011">&nbsp;</td>
                <td id="yhj_011">&nbsp;</td>
                <td id="rcq_011">&nbsp;</td>
                <td id="ycq_011">&nbsp;</td>
                <td id="kc_011">&nbsp;</td>
            </tr>
            <tr>
                <th rowspan="4">日消耗</th>
                <td>原料煤（t)</td>
                <td id="z_104">&nbsp;</td>
                <td id="y_104">&nbsp;</td>
                <td id="b_104">&nbsp;</td>
                <td id="rhj_104">&nbsp;</td>
                <td id="yhj_104">&nbsp;</td>
                <td id="rcq_104">&nbsp;</td>
                <td id="ycq_104">&nbsp;</td>
                <td id="kc_104">&nbsp;</td>
            </tr>
            <tr>
                <td>燃料煤（t)</td>
                <td id="z_105">&nbsp;</td>
                <td id="y_105">&nbsp;</td>
                <td id="b_105">&nbsp;</td>
                <td id="rhj_105">&nbsp;</td>
                <td id="yhj_105">&nbsp;</td>
                <td id="rcq_105">&nbsp;</td>
                <td id="ycq_105">&nbsp;</td>
                <td id="kc_105">&nbsp;</td>
            </tr>
            <tr>
                <td>用电量（万度）</td>
                <td id="z_106">&nbsp;</td>
                <td id="y_106">&nbsp;</td>
                <td id="b_106">&nbsp;</td>
                <td id="rhj_106">&nbsp;</td>
                <td id="yhj_106">&nbsp;</td>
                <td id="rcq_106">&nbsp;</td>
                <td id="ycq_106">&nbsp;</td>
                <td id="kc_106">&nbsp;</td>
            </tr>
            <tr>
                <td>原水量（t）</td>
                <td id="z_107">&nbsp;</td>
                <td id="y_107">&nbsp;</td>
                <td id="b_107">&nbsp;</td>
                <td id="rhj_107">&nbsp;</td>
                <td id="yhj_107">&nbsp;</td>
                <td id="rcq_107">&nbsp;</td>
                <td id="ycq_107">&nbsp;</td>
                <td id="kc_107">&nbsp;</td>
            </tr>
            <tr>
                <th rowspan="8">单耗</th>
                <th>&nbsp;</th>
                <th colspan="4">日耗</th>
                <th colspan="4">月耗</th>
            </tr>
            <tr>
                <td>吨甲醇耗有效气（kNm³）</td>
                <td id="hq_dh_z">&nbsp;</td>
                <td id="hq_dh_y">&nbsp;</td>
                <td id="hq_dh_b">&nbsp;</td>
                <td id="hq_dh_d">&nbsp;</td>
                <td id="hq_dh_m" colspan="4">&nbsp;</td>
            </tr>
            <tr>
                <td>千方有效气耗原煤（t）</td>
                <td id="qhym_dh_z">&nbsp;</td>
                <td id="qhym_dh_y">&nbsp;</td>
                <td id="qhym_dh_b">&nbsp;</td>
                <td id="qhym_dh_d">&nbsp;</td>
                <td id="qhym_dh_m" colspan="4">&nbsp;</td>
            </tr>
            <tr>
                <td>吨蒸汽耗燃料煤（t）</td>
                <td id="zqhm_dh_z">&nbsp;</td>
                <td id="zqhm_dh_y">&nbsp;</td>
                <td id="zqhm_dh_b">&nbsp;</td>
                <td id="zqhm_dh_d">&nbsp;</td>
                <td id="zqhm_dh_m" colspan="4">&nbsp;</td>
            </tr>
            <tr>
                <td>精甲醇（t）耗原煤（t/t）</td>
                <td id="hym_dh_z">&nbsp;</td>
                <td id="hym_dh_y">&nbsp;</td>
                <td id="hym_dh_b">&nbsp;</td>
                <td id="hym_dh_d">&nbsp;</td>
                <td id="hym_dh_m" colspan="4">&nbsp;</td>
            </tr>
            <tr>
                <td>精甲醇（t）耗燃煤（t/t）</td>
                <td id="hrm_dh_z">&nbsp;</td>
                <td id="hrm_dh_y">&nbsp;</td>
                <td id="hrm_dh_b">&nbsp;</td>
                <td id="hrm_dh_d">&nbsp;</td>
                <td id="hrm_dh_m" colspan="4">&nbsp;</td>
            </tr>
            <tr>
                <td>精甲醇（t）耗原水（t/t）</td>
                <td id="hs_dh_z">&nbsp;</td>
                <td id="hs_dh_y">&nbsp;</td>
                <td id="hs_dh_b">&nbsp;</td>
                <td id="hs_dh_d">&nbsp;</td>
                <td id="hs_dh_m" colspan="4">&nbsp;</td>
            </tr>
            <tr>
                <td>精甲醇（t）耗外电（kwh/吨）</td>
                <td id="hwd_dh_z">&nbsp;</td>
                <td id="hwd_dh_y">&nbsp;</td>
                <td id="hwd_dh_b">&nbsp;</td>
                <td id="hwd_dh_d">&nbsp;</td>
                <td id="hwd_dh_m" colspan="4">&nbsp;</td>
            </tr>
            <tr>
                <th rowspan="8">销售</th>
                <th>&nbsp;</td>
                <th colspan="4">日售</th>
                <th colspan="4">月售</th>
            </tr>
            <tr>
                <td>粗甲醇（t）</td>
                <td colspan="4" id="cjc_xs">&nbsp;</td>
                <td colspan="4" id="cjc_xs_hj">&nbsp;</td>
            </tr>
            <tr>
                <td>精甲醇（t）</td>
                <td colspan="4" id="jjc_xs">&nbsp;</td>
                <td colspan="4" id="jjc_xs_hj">&nbsp;</td>
            </tr>
            <tr>
                <td>液氧（t）</td>
                <td colspan="4" id="yy_xs">&nbsp;</td>
                <td colspan="4" id="yy_xs_hj">&nbsp;</td>
            </tr>
            <tr>
                <td>液氮（t）</td>
                <td colspan="4" id="yd_xs">&nbsp;</td>
                <td colspan="4" id="yd_xs_hj">&nbsp;</td>
            </tr>
            <tr>
                <td>液氩（t）</td>
                <td colspan="4" id="ya_xs">&nbsp;</td>
                <td colspan="4" id="ya_xs_hj">&nbsp;</td>
            </tr>
            <tr>
                <td>硫磺（t）</td>
                <td colspan="4" id="lh_xs">&nbsp;</td>
                <td colspan="4" id="lh_xs_hj">&nbsp;</td>
            </tr>
            <tr>
                <td>硫酸铵（t）</td>
                <td colspan="4" id="lsa_xs">&nbsp;</td>
                <td colspan="4" id="lsa_xs_hj">&nbsp;</td>
            </tr>
            <tr>
                <td colspan="10"><strong>二、生产情况综述：</strong></td>
            </tr>
            <tr>
                <th height="50">热电</th>
                <td height="50" colspan="9" id="rdzs">&nbsp;</td>
            </tr>
            <tr>
                <th height="50">气化</th>
                <td colspan="9" id="qhzs">&nbsp;</td>
            </tr>
            <tr>
                <th height="50">甲醇</th>
                <td colspan="9" id="jczs">&nbsp;</td>
            </tr>
            <tr>
                <th height="50">其它</th>
                <td colspan="9" id="qt">&nbsp;</td>
            </tr>
        </table>
        <br />
        <!-- InstanceEndEditable -->
    </body>
    <!-- InstanceEnd --></html>
