/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soa.service.busi.sc;

import com.lianzt.commondata.AbstractCommonData;
import com.lianzt.commondata.CommonDataElement;
import com.lianzt.commondata.DataConvertFactory;
import com.lianzt.util.DateUtil;
import com.soa.bean.StTableParamet;
import com.soa.exception.GlobalException;
import com.soa.service.BaseService;
import com.soa.util.SystemUtil;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 创建生产调度日报
 * P13011
 * @author lianzt
 */
@Service
public class CreateRbSc extends BaseService {

    private static final String[] KEY = new String[]{"scrq", "生产日期"};
    private static final Logger log = LoggerFactory.getLogger(CreateRbSc.class);
    private static final int LENGTH = 2;

    @Override
    public String[] keys() {
        return KEY;
    }

    @Override
    @Transactional
    public void execute(AbstractCommonData in, AbstractCommonData inHead, AbstractCommonData out, AbstractCommonData outHead) {
        Date scrq = in.getDateValue("scrq");
        if (scrq.getTime() > new Date().getTime()) {
            throw new GlobalException(210035);      //调度日报的时间不能大于今天！！
        }
        //1.取该日期当月的生产与消耗指标
        Map<String, Double> rzb = getRzb(scrq);     //日生产指标， key:编号，value:值
        //查询产品产量信息
        Map<String, AbstractCommonData> table = initTable();        //表格容器，key:编号，value：表格中的一行
        //获取每天的产量与消耗量
        readCLAndXh(table, scrq);
        //计算日超欠
        Map<String, List<Object[]>> argsMap = countCqe(table, rzb, scrq);        //返回 批量更新超欠额的sql语句参数
        //更新产量超欠、消耗品超欠
        if (argsMap != null && !argsMap.isEmpty()) {
            batchUpdate("update_cp_rcq_sc", argsMap.get("cp"));     //此处的更新放在execute函数中
            batchUpdate("update_xhp_rcq_sc", argsMap.get("xhp"));     //此处的更新放在execute函数中
        }
        //查询产量、消耗量的月累计
        readTotal(table, scrq);
        //查询超欠的月累计
        readCqe(table, scrq);
        //查询产量、消耗量库存
        readKc(table, scrq);
        //到此，产量与消耗计算完成，开始处理结果，把二维的表结构转为一维结构
        AbstractCommonData tableData = changeTable(table);
        //查询销售、累计
        readXs(tableData, scrq);
        //查询单耗、累计
        Object[][] args = countDh(tableData, scrq);
        update("del_dh_sc", scrq);              //先删除
        batchUpdate("save_dh_sc", args);        //更新单耗信息
        //查询生产情况综述
        readZs(tableData, scrq);
        //到此，表格生成完成
        out.putDataValue("table", tableData);
        out.putStringValue("table_json", DataConvertFactory.praseJson(tableData));
    }

    /**
     * 查询生产情况综述
     * @param tableData
     * @param scrq
     */
    private void readZs(AbstractCommonData tableData, Date scrq) {
        AbstractCommonData acd = queryData("get_zs_sc", scrq);
        if (acd != null && !acd.isEmpty()) {
            tableData.put("rdzs", acd.get("rdzs"));
            tableData.put("qhzs", acd.get("qhzs"));
            tableData.put("jczs", acd.get("jczs"));
            tableData.put("qt", acd.get("bz"));       //其它
        }
    }

    /**
     * 查询单耗、累计
     * @param tableData
     * @param scrq
     * @return 日单耗的批量更新sql语句参数
     */
    private Object[][] countDh(AbstractCommonData tableData, Date scrq) {
        Object[][] args = new Object[5][9];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 9; j++) {
                args[i][j] = Double.valueOf(0);
            }
        }
        //scrq,dhlx,hym,hrm,hs,hwd,hq,qhym,zqhm
        //生产日期
        args[0][0] = scrq;
        args[1][0] = scrq;
        args[2][0] = scrq;
        args[3][0] = scrq;
        args[4][0] = scrq;
        //单耗类型
        args[0][1] = "z";       //中班单耗
        args[1][1] = "y";       //夜班单耗
        args[2][1] = "b";       //白班单耗
        args[3][1] = "d";       //全天单耗
        args[4][1] = "m";       //当月单耗
        //千方有效气耗原煤=原料煤/有效煤气
        if (tableData.getDoubleValue("z_104") != null
                && tableData.getDoubleValue("y_104") != null
                && tableData.getDoubleValue("b_104") != null
                && tableData.getDoubleValue("rhj_104") != null
                && tableData.getDoubleValue("yhj_104") != null
                && tableData.getDoubleValue("z_010") != null
                && tableData.getDoubleValue("y_010") != null
                && tableData.getDoubleValue("b_010") != null
                && tableData.getDoubleValue("rhj_010") != null
                && tableData.getDoubleValue("yhj_010") != null) {
            if (log.isDebugEnabled()) {
                log.debug("计算：千方有效气耗原煤");
            }
            //千方有效气耗原煤=原料煤/有效煤气
            //当除数不为0计算
            if (tableData.getDoubleValue("z_010") > 0) {
                args[0][7] = round(tableData.getDoubleValue("z_104") / tableData.getDoubleValue("z_010"));
            }
            if (tableData.getDoubleValue("y_010") > 0) {
                args[1][7] = round(tableData.getDoubleValue("y_104") / tableData.getDoubleValue("y_010"));
            }
            if (tableData.getDoubleValue("b_010") > 0) {
                args[2][7] = round(tableData.getDoubleValue("b_104") / tableData.getDoubleValue("b_010"));
            }
            if (tableData.getDoubleValue("rhj_010") > 0) {
                args[3][7] = round(tableData.getDoubleValue("rhj_104") / tableData.getDoubleValue("rhj_010"));
            }
            if (tableData.getDoubleValue("yhj_010") > 0) {
                args[4][7] = round(tableData.getDoubleValue("yhj_104") / tableData.getDoubleValue("yhj_010"));
            }
            tableData.putDoubleValue("qhym_dh_z", (Double) args[0][7]);
            tableData.putDoubleValue("qhym_dh_y", (Double) args[1][7]);
            tableData.putDoubleValue("qhym_dh_b", (Double) args[2][7]);
            tableData.putDoubleValue("qhym_dh_d", (Double) args[3][7]);
            tableData.putDoubleValue("qhym_dh_m", (Double) args[4][7]);
        }

        //吨蒸汽耗燃料煤=燃料煤/蒸汽
        if (tableData.getDoubleValue("z_105") != null
                && tableData.getDoubleValue("y_105") != null
                && tableData.getDoubleValue("b_105") != null
                && tableData.getDoubleValue("rhj_105") != null
                && tableData.getDoubleValue("yhj_105") != null
                && tableData.getDoubleValue("z_005") != null
                && tableData.getDoubleValue("y_005") != null
                && tableData.getDoubleValue("b_005") != null
                && tableData.getDoubleValue("rhj_005") != null
                && tableData.getDoubleValue("yhj_005") != null) {
            if (log.isDebugEnabled()) {
                log.debug("计算：吨蒸汽耗燃料煤");
            }
            //吨蒸汽耗燃料煤=燃料煤/蒸汽
            //当除数不为0计算
            if (tableData.getDoubleValue("z_005") != 0) {
                args[0][8] = round(tableData.getDoubleValue("z_105") / tableData.getDoubleValue("z_005"));
            }
            if (tableData.getDoubleValue("y_005") != 0) {
                args[1][8] = round(tableData.getDoubleValue("y_105") / tableData.getDoubleValue("y_005"));
            }
            if (tableData.getDoubleValue("b_005") != 0) {
                args[2][8] = round(tableData.getDoubleValue("b_105") / tableData.getDoubleValue("b_005"));
            }
            if (tableData.getDoubleValue("rhj_005") != 0) {
                args[3][8] = round(tableData.getDoubleValue("rhj_105") / tableData.getDoubleValue("rhj_005"));
            }
            if (tableData.getDoubleValue("yhj_005") != 0) {
                args[4][8] = round(tableData.getDoubleValue("yhj_105") / tableData.getDoubleValue("yhj_005"));
            }
            tableData.putDoubleValue("zqhm_dh_z", (Double) args[0][8]);
            tableData.putDoubleValue("zqhm_dh_y", (Double) args[1][8]);
            tableData.putDoubleValue("zqhm_dh_b", (Double) args[2][8]);
            tableData.putDoubleValue("zqhm_dh_d", (Double) args[3][8]);
            tableData.putDoubleValue("zqhm_dh_m", (Double) args[4][8]);
        }
        //吨甲醇耗有效气=（有效煤气-送乙二醇有效气）/（粗醇*0.97）
        if (tableData.getDoubleValue("z_001") != null
                && tableData.getDoubleValue("y_001") != null
                && tableData.getDoubleValue("b_001") != null
                && tableData.getDoubleValue("rhj_001") != null
                && tableData.getDoubleValue("yhj_001") != null
                && tableData.getDoubleValue("z_012") != null
                && tableData.getDoubleValue("y_012") != null
                && tableData.getDoubleValue("b_012") != null
                && tableData.getDoubleValue("rhj_012") != null
                && tableData.getDoubleValue("yhj_012") != null
                && tableData.getDoubleValue("z_010") != null
                && tableData.getDoubleValue("y_010") != null
                && tableData.getDoubleValue("b_010") != null
                && tableData.getDoubleValue("rhj_010") != null
                && tableData.getDoubleValue("yhj_010") != null) {
            if (log.isDebugEnabled()) {
                log.debug("计算：吨甲醇耗有效气");
            }
            //吨甲醇耗有效气=（有效煤气-送乙二醇有效气）/（粗醇*0.97）
            //当除数不为0计算
            if (tableData.getDoubleValue("z_001") != 0) {
                args[0][6] = round((tableData.getDoubleValue("z_010") - tableData.getDoubleValue("z_012")) / (tableData.getDoubleValue("z_001") * 0.97));
            }
            if (tableData.getDoubleValue("y_001") != 0) {
                args[1][6] = round((tableData.getDoubleValue("y_010") - tableData.getDoubleValue("y_012")) / (tableData.getDoubleValue("y_001") * 0.97));
            }
            if (tableData.getDoubleValue("b_001") != 0) {
                args[2][6] = round((tableData.getDoubleValue("b_010") - tableData.getDoubleValue("b_012")) / (tableData.getDoubleValue("b_001") * 0.97));
            }
            if (tableData.getDoubleValue("rhj_001") != 0) {
                args[3][6] = round((tableData.getDoubleValue("rhj_010") - tableData.getDoubleValue("rhj_012")) / (tableData.getDoubleValue("rhj_001") * 0.97));
            }
            if (tableData.getDoubleValue("yhj_001") != 0) {
                args[4][6] = round((tableData.getDoubleValue("yhj_010") - tableData.getDoubleValue("yhj_012")) / (tableData.getDoubleValue("yhj_001") * 0.97));
            }
            tableData.putDoubleValue("hq_dh_z", (Double) args[0][6]);
            tableData.putDoubleValue("hq_dh_y", (Double) args[1][6]);
            tableData.putDoubleValue("hq_dh_b", (Double) args[2][6]);
            tableData.putDoubleValue("hq_dh_d", (Double) args[3][6]);
            tableData.putDoubleValue("hq_dh_m", (Double) args[4][6]);
        }
        //精甲醇（t）耗原煤（t/t）=[原料煤-（送乙二醇合成气*千标方有效气耗原煤）]/粗甲醇*0.97
        if (tableData.getDoubleValue("z_104") != null
                && tableData.getDoubleValue("y_104") != null
                && tableData.getDoubleValue("b_104") != null
                && tableData.getDoubleValue("rhj_104") != null
                && tableData.getDoubleValue("yhj_104") != null
                && tableData.getDoubleValue("z_012") != null
                && tableData.getDoubleValue("y_012") != null
                && tableData.getDoubleValue("b_012") != null
                && tableData.getDoubleValue("rhj_012") != null
                && tableData.getDoubleValue("yhj_012") != null
                && tableData.getDoubleValue("qhym_dh_z") != null
                && tableData.getDoubleValue("qhym_dh_y") != null
                && tableData.getDoubleValue("qhym_dh_b") != null
                && tableData.getDoubleValue("qhym_dh_d") != null
                && tableData.getDoubleValue("qhym_dh_m") != null
                && tableData.getDoubleValue("z_001") != null
                && tableData.getDoubleValue("y_001") != null
                && tableData.getDoubleValue("b_001") != null
                && tableData.getDoubleValue("rhj_001") != null
                && tableData.getDoubleValue("yhj_001") != null) {
            if (log.isDebugEnabled()) {
                log.debug("计算：精甲醇（t）耗原煤（t/t）");
            }
            //精甲醇（t）耗原煤（t/t）=[原料煤-（送乙二醇合成气*千标方有效气耗原煤）]/粗甲醇*0.97
            //当除数不为0计算
            if (tableData.getDoubleValue("z_001") != 0) {
                args[0][2] = round((tableData.getDoubleValue("z_104") - (tableData.getDoubleValue("z_012") * tableData.getDoubleValue("qhym_dh_z"))) / (tableData.getDoubleValue("z_001") * 0.97));
            }
            if (tableData.getDoubleValue("y_001") != 0) {
                args[1][2] = round((tableData.getDoubleValue("y_104") - (tableData.getDoubleValue("y_012") * tableData.getDoubleValue("qhym_dh_y"))) / (tableData.getDoubleValue("y_001") * 0.97));
            }
            if (tableData.getDoubleValue("b_001") != 0) {
                args[2][2] = round((tableData.getDoubleValue("b_104") - (tableData.getDoubleValue("b_012") * tableData.getDoubleValue("qhym_dh_b"))) / (tableData.getDoubleValue("b_001") * 0.97));
            }
            if (tableData.getDoubleValue("rhj_001") != 0) {
                args[3][2] = round((tableData.getDoubleValue("rhj_104") - (tableData.getDoubleValue("rhj_012") * tableData.getDoubleValue("qhym_dh_d"))) / (tableData.getDoubleValue("rhj_001") * 0.97));
            }
            if (tableData.getDoubleValue("yhj_001") != 0) {
                args[4][2] = round((tableData.getDoubleValue("yhj_104") - (tableData.getDoubleValue("yhj_012") * tableData.getDoubleValue("qhym_dh_m"))) / (tableData.getDoubleValue("yhj_001") * 0.97));
            }
            tableData.putDoubleValue("hym_dh_z", (Double) args[0][2]);
            tableData.putDoubleValue("hym_dh_y", (Double) args[1][2]);
            tableData.putDoubleValue("hym_dh_b", (Double) args[2][2]);
            tableData.putDoubleValue("hym_dh_d", (Double) args[3][2]);
            tableData.putDoubleValue("hym_dh_m", (Double) args[4][2]);
        }
        //精甲醇（t）耗燃煤（t/t）=[燃料煤-（金山化工用蒸汽*吨蒸汽耗燃料煤）-（乙二醇用蒸汽*吨蒸汽耗燃料煤）]/粗醇*0.97
        //乙二醇用蒸汽是 送永金蒸汽
        if (tableData.getDoubleValue("z_105") != null
                && tableData.getDoubleValue("y_105") != null
                && tableData.getDoubleValue("b_105") != null
                && tableData.getDoubleValue("rhj_105") != null
                && tableData.getDoubleValue("yhj_105") != null
                && tableData.getDoubleValue("z_006") != null
                && tableData.getDoubleValue("y_006") != null
                && tableData.getDoubleValue("b_006") != null
                && tableData.getDoubleValue("rhj_006") != null
                && tableData.getDoubleValue("yhj_006") != null
                && tableData.getDoubleValue("zqhm_dh_z") != null
                && tableData.getDoubleValue("zqhm_dh_y") != null
                && tableData.getDoubleValue("zqhm_dh_b") != null
                && tableData.getDoubleValue("zqhm_dh_d") != null
                && tableData.getDoubleValue("zqhm_dh_m") != null
                && tableData.getDoubleValue("z_013") != null
                && tableData.getDoubleValue("y_013") != null
                && tableData.getDoubleValue("b_013") != null
                && tableData.getDoubleValue("rhj_013") != null
                && tableData.getDoubleValue("yhj_013") != null
                && tableData.getDoubleValue("z_001") != null
                && tableData.getDoubleValue("y_001") != null
                && tableData.getDoubleValue("b_001") != null
                && tableData.getDoubleValue("rhj_001") != null
                && tableData.getDoubleValue("yhj_001") != null) {
            if (log.isDebugEnabled()) {
                log.debug("计算：精甲醇（t）耗燃煤（t/t）");
            }
            //精甲醇（t）耗燃煤（t/t）=[燃料煤-（金山化工用蒸汽*吨蒸汽耗燃料煤）-（乙二醇用蒸汽*吨蒸汽耗燃料煤）]/粗醇*0.97
            //当除数不为0计算
            if (tableData.getDoubleValue("z_001") != 0) {
                args[0][3] = round((tableData.getDoubleValue("z_105") - (tableData.getDoubleValue("z_006") * tableData.getDoubleValue("zqhm_dh_z")) - (tableData.getDoubleValue("z_013") * tableData.getDoubleValue("zqhm_dh_z"))) / (tableData.getDoubleValue("z_001") * 0.97));
            }
            if (tableData.getDoubleValue("y_001") != 0) {
                args[1][3] = round((tableData.getDoubleValue("y_105") - (tableData.getDoubleValue("y_006") * tableData.getDoubleValue("zqhm_dh_y")) - (tableData.getDoubleValue("y_013") * tableData.getDoubleValue("zqhm_dh_y"))) / (tableData.getDoubleValue("y_001") * 0.97));
            }
            if (tableData.getDoubleValue("b_001") != 0) {
                args[2][3] = round((tableData.getDoubleValue("b_105") - (tableData.getDoubleValue("b_006") * tableData.getDoubleValue("zqhm_dh_b")) - (tableData.getDoubleValue("b_013") * tableData.getDoubleValue("zqhm_dh_b"))) / (tableData.getDoubleValue("b_001") * 0.97));
            }
            if (tableData.getDoubleValue("rhj_001") != 0) {
                args[3][3] = round((tableData.getDoubleValue("rhj_105") - (tableData.getDoubleValue("rhj_006") * tableData.getDoubleValue("zqhm_dh_d")) - (tableData.getDoubleValue("rhj_013") * tableData.getDoubleValue("zqhm_dh_d"))) / (tableData.getDoubleValue("rhj_001") * 0.97));
            }
            if (tableData.getDoubleValue("yhj_001") != 0) {
                args[4][3] = round((tableData.getDoubleValue("yhj_105") - (tableData.getDoubleValue("yhj_006") * tableData.getDoubleValue("zqhm_dh_m")) - (tableData.getDoubleValue("yhj_013") * tableData.getDoubleValue("zqhm_dh_m"))) / (tableData.getDoubleValue("yhj_001") * 0.97));
            }
            tableData.putDoubleValue("hrm_dh_z", (Double) args[0][3]);
            tableData.putDoubleValue("hrm_dh_y", (Double) args[1][3]);
            tableData.putDoubleValue("hrm_dh_b", (Double) args[2][3]);
            tableData.putDoubleValue("hrm_dh_d", (Double) args[3][3]);
            tableData.putDoubleValue("hrm_dh_m", (Double) args[4][3]);
        }
        //精甲醇（t）耗原水（t/t）=原水量/（粗醇*0.97+送乙二醇合成气/2.25）
        if (tableData.getDoubleValue("z_001") != null
                && tableData.getDoubleValue("y_001") != null
                && tableData.getDoubleValue("b_001") != null
                && tableData.getDoubleValue("rhj_001") != null
                && tableData.getDoubleValue("yhj_001") != null
                && tableData.getDoubleValue("z_012") != null
                && tableData.getDoubleValue("y_012") != null
                && tableData.getDoubleValue("b_012") != null
                && tableData.getDoubleValue("rhj_012") != null
                && tableData.getDoubleValue("yhj_012") != null
                && tableData.getDoubleValue("z_107") != null
                && tableData.getDoubleValue("y_107") != null
                && tableData.getDoubleValue("b_107") != null
                && tableData.getDoubleValue("rhj_107") != null
                && tableData.getDoubleValue("yhj_107") != null) {
            if (log.isDebugEnabled()) {
                log.debug("计算：精甲醇（t）耗原水（（t/t）");
            }
            //精甲醇（t）耗原水（t/t）=原水量/（粗醇*0.97+送乙二醇合成气/2.25）
            //当除数不为0计算
            if (tableData.getDoubleValue("z_001") != 0 || tableData.getDoubleValue("z_012") != 0) {
                args[0][4] = round(tableData.getDoubleValue("z_107") / ((tableData.getDoubleValue("z_001") * 0.97) + (tableData.getDoubleValue("z_012") / 2.25)));
            }
            if (tableData.getDoubleValue("y_001") != 0 || tableData.getDoubleValue("y_012") != 0) {
                args[1][4] = round(tableData.getDoubleValue("y_107") / ((tableData.getDoubleValue("y_001") * 0.97) + (tableData.getDoubleValue("y_012") / 2.25)));
            }
            if (tableData.getDoubleValue("b_001") != 0 || tableData.getDoubleValue("b_012") != 0) {
                args[2][4] = round(tableData.getDoubleValue("b_107") / ((tableData.getDoubleValue("b_001") * 0.97) + (tableData.getDoubleValue("b_012") / 2.25)));
            }
            if (tableData.getDoubleValue("rhj_001") != 0 || tableData.getDoubleValue("rhj_012") != 0) {
                args[3][4] = round(tableData.getDoubleValue("rhj_107") / ((tableData.getDoubleValue("rhj_001") * 0.97) + (tableData.getDoubleValue("rhj_012") / 2.25)));
            }
            if (tableData.getDoubleValue("yhj_001") != 0 || tableData.getDoubleValue("yhj_012") != 0) {
                args[4][4] = round(tableData.getDoubleValue("yhj_107") / ((tableData.getDoubleValue("yhj_001") * 0.97) + (tableData.getDoubleValue("yhj_012") / 2.25)));
            }
            tableData.putDoubleValue("hs_dh_z", (Double) args[0][4]);
            tableData.putDoubleValue("hs_dh_y", (Double) args[1][4]);
            tableData.putDoubleValue("hs_dh_b", (Double) args[2][4]);
            tableData.putDoubleValue("hs_dh_d", (Double) args[3][4]);
            tableData.putDoubleValue("hs_dh_m", (Double) args[4][4]);
        }
        //精甲醇（t）耗外电（kwh/吨）=用电量*10000/（粗醇*0.97+送乙二醇合成气/2.25）
        if (tableData.getDoubleValue("z_001") != null
                && tableData.getDoubleValue("y_001") != null
                && tableData.getDoubleValue("b_001") != null
                && tableData.getDoubleValue("rhj_001") != null
                && tableData.getDoubleValue("yhj_001") != null
                && tableData.getDoubleValue("z_012") != null
                && tableData.getDoubleValue("y_012") != null
                && tableData.getDoubleValue("b_012") != null
                && tableData.getDoubleValue("rhj_012") != null
                && tableData.getDoubleValue("yhj_012") != null
                && tableData.getDoubleValue("z_106") != null
                && tableData.getDoubleValue("y_106") != null
                && tableData.getDoubleValue("b_106") != null
                && tableData.getDoubleValue("rhj_106") != null
                && tableData.getDoubleValue("yhj_106") != null) {
            if (log.isDebugEnabled()) {
                log.debug("计算：精甲醇（t）耗外电（kwh/吨）");
            }
            //精甲醇（t）耗外电（kwh/吨）=用电量*10000/（粗醇*0.97+送乙二醇合成气/2.25）
            //当除数不为0计算
            if (tableData.getDoubleValue("z_001") != 0 || tableData.getDoubleValue("z_012") != 0) {
                args[0][5] = round(tableData.getDoubleValue("z_106") * 10000 / ((tableData.getDoubleValue("z_001") * 0.97) + (tableData.getDoubleValue("z_012") / 2.25)));
            }
            if (tableData.getDoubleValue("y_001") != 0 || tableData.getDoubleValue("y_012") != 0) {
                args[1][5] = round(tableData.getDoubleValue("y_106") * 10000 / ((tableData.getDoubleValue("y_001") * 0.97) + (tableData.getDoubleValue("y_012") / 2.25)));
            }
            if (tableData.getDoubleValue("b_001") != 0 || tableData.getDoubleValue("b_012") != 0) {
                args[2][5] = round(tableData.getDoubleValue("b_106") * 10000 / ((tableData.getDoubleValue("b_001") * 0.97) + (tableData.getDoubleValue("b_012") / 2.25)));
            }
            if (tableData.getDoubleValue("rhj_001") != 0 || tableData.getDoubleValue("rhj_012") != 0) {
                args[3][5] = round(tableData.getDoubleValue("rhj_106") * 10000 / ((tableData.getDoubleValue("rhj_001") * 0.97) + (tableData.getDoubleValue("rhj_012") / 2.25)));
            }
            if (tableData.getDoubleValue("yhj_001") != 0 || tableData.getDoubleValue("yhj_012") != 0) {
                args[4][5] = round(tableData.getDoubleValue("yhj_106") * 10000 / ((tableData.getDoubleValue("yhj_001") * 0.97) + (tableData.getDoubleValue("yhj_012") / 2.25)));
            }
            tableData.putDoubleValue("hwd_dh_z", (Double) args[0][5]);
            tableData.putDoubleValue("hwd_dh_y", (Double) args[1][5]);
            tableData.putDoubleValue("hwd_dh_b", (Double) args[2][5]);
            tableData.putDoubleValue("hwd_dh_d", (Double) args[3][5]);
            tableData.putDoubleValue("hwd_dh_m", (Double) args[4][5]);
        }
        return args;
    }

    /**
     * 查询销售、累计
     * @param tableData
     * @param scrq
     */
    private void readXs(AbstractCommonData tableData, Date scrq) {
        AbstractCommonData acd = queryData("get_xs_sc", scrq);
        if (acd != null && !acd.isEmpty()) {
            tableData.put("cjc_xs", acd.get("cjc"));
            tableData.put("jjc_xs", acd.get("jjc"));
            tableData.put("yy_xs", acd.get("yy"));
            tableData.put("yd_xs", acd.get("yd"));
            tableData.put("ya_xs", acd.get("ya"));
            tableData.put("lh_xs", acd.get("lh"));
            tableData.put("lsa_xs", acd.get("lsa"));
        }
        Date begin = getTotalBegin(scrq);      //本月开始
        Date end = scrq;     //下月开始
        acd = queryData("get_total_xs_sc", begin, end);
        if (acd != null && !acd.isEmpty()) {
            if (acd.getDoubleValue("cjc") != null) {
                tableData.putDoubleValue("cjc_xs_hj", round(acd.getDoubleValue("cjc")));
            }
            if (acd.getDoubleValue("jjc") != null) {
                tableData.putDoubleValue("jjc_xs_hj", round(acd.getDoubleValue("jjc")));
            }
            if (acd.getDoubleValue("yy") != null) {
                tableData.putDoubleValue("yy_xs_hj", round(acd.getDoubleValue("yy")));
            }
            if (acd.getDoubleValue("yd") != null) {
                tableData.putDoubleValue("yd_xs_hj", round(acd.getDoubleValue("yd")));
            }
            if (acd.getDoubleValue("ya") != null) {
                tableData.putDoubleValue("ya_xs_hj", round(acd.getDoubleValue("ya")));
            }
            if (acd.getDoubleValue("lh") != null) {
                tableData.putDoubleValue("lh_xs_hj", round(acd.getDoubleValue("lh")));
            }
            if (acd.getDoubleValue("lsa") != null) {
                tableData.putDoubleValue("lsa_xs_hj", round(acd.getDoubleValue("lsa")));
            }
        }
    }

    /**
     * 把二维的表结构转为一维结构
     * @param table 表格
     * @return 一维表格
     */
    private AbstractCommonData changeTable(Map<String, AbstractCommonData> table) {
        AbstractCommonData json = DataConvertFactory.getInstanceEmpty();
        for (Map.Entry<String, AbstractCommonData> e : table.entrySet()) {    //key为编号
            for (Map.Entry<String, CommonDataElement> el : e.getValue().entrySet()) {     //key为表格的列
                json.put(el.getKey() + "_" + e.getKey(), el.getValue());        //把二维表格的key转为key_bh
            }
        }
        return json;
    }

    /**
     * 查询产量、消耗量库存
     * @param table
     * @param csrq
     */
    private void readKc(Map<String, AbstractCommonData> table, Date csrq) {
        AbstractCommonData row = null;
        //把产量超欠月累计放入表格
        List<AbstractCommonData> list = queryList("get_day_cl_kc_sc", csrq);
        if (list != null && !list.isEmpty()) {
            for (AbstractCommonData acd : list) {
                row = table.get(acd.getStringValue("cpbh"));
                row.putDoubleValue("kc", acd.getDoubleValue("kc"));
            }
        }
        //把月消耗超欠放入表格
        list = queryList("get_day_xhp_kc_sc", csrq);
        if (list != null && !list.isEmpty()) {
            for (AbstractCommonData acd : list) {
                row = table.get(acd.getStringValue("xhpbh"));
                row.putDoubleValue("kc", acd.getDoubleValue("kc"));
            }
        }
    }

    /**
     * 查询超欠的月累计
     * @param table 表格信息
     * @param csrq 生产日期
     */
    private void readCqe(Map<String, AbstractCommonData> table, Date csrq) {
        Date begin = getTotalBegin(csrq);      //本月开始
        Date end = csrq;     //下月开始
        AbstractCommonData row = null;
        //把产量超欠月累计放入表格
        List<AbstractCommonData> list = queryList("get_total_clcq_sc", begin, end);
        if (log.isDebugEnabled()) {
            log.debug("更新数据库后的数据：" + list);
        }
        if (list != null && !list.isEmpty()) {
            for (AbstractCommonData acd : list) {
                row = table.get(acd.getStringValue("cpbh"));
                if (acd.getDoubleValue("total") != null) {
                    row.putDoubleValue("ycq", round(acd.getDoubleValue("total")));
                }
            }
        }
        //把月消耗超欠放入表格
        list = queryList("get_total_xhpcq_sc", begin, end);
        if (list != null && !list.isEmpty()) {
            for (AbstractCommonData acd : list) {
                row = table.get(acd.getStringValue("xhpbh"));
                if (acd.getDoubleValue("total") != null) {
                    row.putDoubleValue("ycq", round(acd.getDoubleValue("total")));
                }
            }
        }
    }

    /**
     * 查询产量、消耗量的月累计
     * @param table 表格信息
     * @param csrq 生产日期
     */
    private void readTotal(Map<String, AbstractCommonData> table, Date csrq) {
        Date begin = getTotalBegin(csrq);      //本月开始
        Date end = csrq;     //下月开始
        AbstractCommonData row = null;
        //把产量月累计放入表格
        List<AbstractCommonData> list = queryList("get_total_cl_sc", begin, end);
        if (list != null && !list.isEmpty()) {
            for (AbstractCommonData acd : list) {
                row = table.get(acd.getStringValue("cpbh"));
                if (acd.getDoubleValue("total") != null) {
                    row.putDoubleValue("yhj", round(acd.getDoubleValue("total")));
                }
            }
        }
        //把月消耗放入表格
        list = queryList("get_total_xhp_sc", begin, end);
        if (list != null && !list.isEmpty()) {
            for (AbstractCommonData acd : list) {
                row = table.get(acd.getStringValue("xhpbh"));
                if (acd.getDoubleValue("total") != null) {
                    row.putDoubleValue("yhj", round(acd.getDoubleValue("total")));
                }
            }
        }
    }

    /**
     * 计算日超欠
     * @param table 计算产量后的表格
     * @param rzb 产量与消耗的日指标
     * @param scrq 生产日期
     * @return 批量更新超欠额的sql语句参数
     */
    private Map<String, List<Object[]>> countCqe(Map<String, AbstractCommonData> table, Map<String, Double> rzb, Date scrq) {
        if (rzb == null || rzb.isEmpty()) {     //如果指标为空，不计算
            return null;
        }
        Map<String, List<Object[]>> map = new HashMap<String, List<Object[]>>();
        List<Object[]> cp = new LinkedList<Object[]>();
        List<Object[]> xhp = new LinkedList<Object[]>();
        AbstractCommonData temp = null;
        double rhj = 0;
        for (Map.Entry<String, Double> e : rzb.entrySet()) {
            temp = table.get(e.getKey());
            rhj = temp.getDoubleValue("rhj") - e.getValue();
            rhj = round(rhj);           //保留3位小数
            temp.putDoubleValue("rcq", rhj);      //日合计 - 日指标
            if (e.getKey().startsWith("0")) {     //产品
                //日超欠、生产日期、编号
                cp.add(new Object[]{rhj, scrq, e.getKey()});
            } else if (e.getKey().startsWith("1")) {       //消耗
                //日超欠、生产日期、编号
                xhp.add(new Object[]{rhj, scrq, e.getKey()});
            }
        }
        map.put("cp", cp);
        map.put("xhp", xhp);
        return map;
    }

    /**
     * 四舍五入，LENGTH 位小数
     * @param d
     * @return
     */
    private double round(double d) {
        BigDecimal bd = new BigDecimal(d);
        return bd.setScale(LENGTH, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * 获取某生产日期当月各项指标
     * @param scrq
     * @return 指标
     */
    private Map<String, Double> getRzb(Date scrq) {
        Date jhyfDate = getBegin(scrq);
        String jhyf = DateUtil.defualtFormat(jhyfDate).substring(0, 7).replace("-0", "-");      //计划月份
        HashMap<String, Double> rzb = new HashMap<String, Double>();        //日生产指标
        //1.取该日期当月的生产与消耗指标
        List<AbstractCommonData> jh = queryList("get_cljh_sc", jhyf);
        if (jh != null && !jh.isEmpty()) {
            for (AbstractCommonData acd : jh) {
                rzb.put(acd.getStringValue("cpbh"), acd.getDoubleValue("rzb"));
            }
        }
        jh = queryList("get_xhpjh_sc", jhyf);
        if (jh != null && !jh.isEmpty()) {
            for (AbstractCommonData acd : jh) {
                rzb.put(acd.getStringValue("xhpbh"), acd.getDoubleValue("rzb"));
            }
        }
        if (log.isDebugEnabled()) {
            log.debug("日指标：" + rzb);
        }
        return rzb;
    }

    /**
     * 生成表格容器
     * @return
     */
    private Map<String, AbstractCommonData> initTable() {
        Map<String, AbstractCommonData> table = new HashMap<String, AbstractCommonData>();
        List<StTableParamet> list = SystemUtil.tableMap.get("sc.cpbh");
        for (StTableParamet s : list) {
            table.put(s.getColValue(), DataConvertFactory.getInstanceEmpty());
        }
        list = SystemUtil.tableMap.get("sc.xhpbh");
        for (StTableParamet s : list) {
            table.put(s.getColValue(), DataConvertFactory.getInstanceEmpty());
        }
        if (log.isDebugEnabled()) {
            log.debug("初始化表格：" + table);
        }
        return table;
    }

    /**
     * 读取日产量和日消耗
     * @param table
     */
    private void readCLAndXh(Map<String, AbstractCommonData> table, Date scrq) {
        List<AbstractCommonData> list = queryList("get_day_cl_sc", scrq);
        if (list != null && !list.isEmpty()) {
            for (AbstractCommonData acd : list) {
                //使用产品编号，从table中取出该产品对应的数据，再把产量放入班次做为key，产量做为value
                table.get(acd.getStringValue("cpbh")).putDoubleValue(acd.getStringValue("bc"), acd.getDoubleValue("cl"));
            }
        }
        list = queryList("get_day_xh_sc", scrq);
        if (list != null && !list.isEmpty()) {
            for (AbstractCommonData acd : list) {
                //使用产品编号，从table中取出该产品对应的数据，再把产量放入班次做为key，产量做为value
                table.get(acd.getStringValue("xhpbh")).putDoubleValue(acd.getStringValue("bc"), acd.getDoubleValue("xhl"));
            }
        }
        if (log.isDebugEnabled()) {
            log.debug("获取产量与消耗量后的表格：" + table);
        }
        AbstractCommonData temp;
        //计算日合计
        double rhj = 0;
        for (Map.Entry<String, AbstractCommonData> e : table.entrySet()) {
            temp = e.getValue();
            rhj = 0;
            if (temp.getDoubleValue("z") != null) {
                rhj += temp.getDoubleValue("z");
            }
            if (temp.getDoubleValue("y") != null) {
                rhj += temp.getDoubleValue("y");
            }
            if (temp.getDoubleValue("b") != null) {
                rhj += temp.getDoubleValue("b");
            }
            temp.putDoubleValue("rhj", round(rhj));
        }
        if (log.isDebugEnabled()) {
            log.debug("计算日合计后的表格：" + table);
        }
    }

    /**
     * 获取月份开始，26号
     * @param scrq
     * @return
     */
    private Date getBegin(Date scrq) {
        Date begin = DateUtil.setDay(scrq, 26);
        if (begin.getTime() <= scrq.getTime()) {       //如果当天超过26号，返回下月26号
            return DateUtil.addMonth(begin, 1);
        } else {      //如果当天小于26号，返回本月26号。
            return begin;
        }
    }

    /**
     * 获取统计时sql的月份开始，26号
     * @param scrq
     * @return
     */
    private Date getTotalBegin(Date scrq) {
        Date begin = DateUtil.setDay(scrq, 26);
        if (begin.getTime() <= scrq.getTime()) {       //如果当天超过26号，返回本月26号
            return begin;
        } else {      //如果当天小于26号，返回上月26号。
            return DateUtil.addMonth(begin, -1);
        }
    }

    public static void main(String[] args) {
//        double d1 = Double.parseDouble("20.5");
//        double d2 = Double.parseDouble("12.8");
//        double d3 = Double.parseDouble("3.3");
//        BigDecimal bd = new BigDecimal(d1 + d2 + d3);
//        System.out.println( bd.setScale(LENGTH, BigDecimal.ROUND_HALF_UP).doubleValue());

        Date scrq = new Date();

        Date begin = DateUtil.setDay(scrq, 26);
        if (begin.getTime() < scrq.getTime()) {       //如果当天超过26号，返回26号
            System.out.println(begin);
        } else {      //如果当天小于26号，返回上月26号。
            System.out.println(DateUtil.addMonth(begin, -1));
        }
    }
}
