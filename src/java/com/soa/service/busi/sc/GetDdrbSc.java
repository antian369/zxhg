/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soa.service.busi.sc;

import com.lianzt.commondata.AbstractCommonData;
import com.lianzt.util.DateUtil;
import com.lianzt.util.StringUtil;
import com.soa.service.BaseService;
import java.math.BigDecimal;
import java.util.Date;
import org.springframework.stereotype.Service;

/**
 * 获取调试日报
 * S13007
 * @author Asus
 */
@Service
public class GetDdrbSc extends BaseService {

    @Override
    public String[] keys() {
        return null;
    }

    @Override
    public void execute(AbstractCommonData in, AbstractCommonData inHead,
                        AbstractCommonData out, AbstractCommonData outHead) {
        String today = in.getStringValue("ddrq");
        if (StringUtil.isNull(today)) {
            today = DateUtil.defualtFormat(new Date());
        }
        out.putStringValue("ddrq", today);
        BigDecimal bigDouble = null;
        //................
        out.putDoubleValue("cl_cc_bb", getRandom());
        out.putDoubleValue("cl_cc_yb", getRandom());
        bigDouble = new BigDecimal(out.getDoubleValue("cl_cc_bb") + out.getDoubleValue("cl_cc_yb"));
        out.putDoubleValue("cl_cc_rhj", bigDouble.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
        bigDouble = new BigDecimal(getRandom() * 30);
        out.putDoubleValue("cl_cc_ylj", bigDouble.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
        out.putDoubleValue("cl_cc_rcq", getRandom());
        out.putDoubleValue("cl_cc_ycq", getRandom());
        out.putDoubleValue("cl_cc_kc", getRandom());
        //...............
        out.putDoubleValue("cl_jc_bb", getRandom());
        out.putDoubleValue("cl_jc_yb", getRandom());
        bigDouble = new BigDecimal(out.getDoubleValue("cl_jc_bb") + out.getDoubleValue("cl_jc_yb"));
        out.putDoubleValue("cl_jc_rhj", bigDouble.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
        bigDouble = new BigDecimal(getRandom() * 30);
        out.putDoubleValue("cl_jc_ylj", bigDouble.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
        out.putDoubleValue("cl_jc_rcq", getRandom());
        out.putDoubleValue("cl_jc_ycq", getRandom());
        out.putDoubleValue("cl_jc_kc", getRandom());
        //............
        out.putDoubleValue("cl_yy_bb", getRandom());
        out.putDoubleValue("cl_yy_yb", getRandom());
        bigDouble = new BigDecimal(out.getDoubleValue("cl_yy_bb") + out.getDoubleValue("cl_yy_yb"));
        out.putDoubleValue("cl_yy_rhj", bigDouble.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
        bigDouble = new BigDecimal(getRandom() * 30);
        out.putDoubleValue("cl_yy_ylj", bigDouble.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
        out.putDoubleValue("cl_yy_rcq", getRandom());
        out.putDoubleValue("cl_yy_ycq", getRandom());
        out.putDoubleValue("cl_yy_kc", getRandom());
        //............
        out.putDoubleValue("cl_yd_bb", getRandom());
        out.putDoubleValue("cl_yd_yb", getRandom());
        bigDouble = new BigDecimal(out.getDoubleValue("cl_yd_bb") + out.getDoubleValue("cl_yd_yb"));
        out.putDoubleValue("cl_yd_rhj", bigDouble.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
        bigDouble = new BigDecimal(getRandom() * 30);
        out.putDoubleValue("cl_yd_ylj", bigDouble.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
        out.putDoubleValue("cl_yd_rcq", getRandom());
        out.putDoubleValue("cl_yd_ycq", getRandom());
        out.putDoubleValue("cl_yd_kc", getRandom());
        //............
        out.putDoubleValue("cl_zq_bb", getRandom());
        out.putDoubleValue("cl_zq_yb", getRandom());
        bigDouble = new BigDecimal(out.getDoubleValue("cl_zq_bb") + out.getDoubleValue("cl_zq_yb"));
        out.putDoubleValue("cl_zq_rhj", bigDouble.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
        bigDouble = new BigDecimal(getRandom() * 30);
        out.putDoubleValue("cl_zq_ylj", bigDouble.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
        out.putDoubleValue("cl_zq_rcq", getRandom());
        out.putDoubleValue("cl_zq_ycq", getRandom());
        out.putDoubleValue("cl_zq_kc", getRandom());
        //............
        out.putDoubleValue("cl_jshgzq_bb", getRandom());
        out.putDoubleValue("cl_jshgzq_yb", getRandom());
        bigDouble = new BigDecimal(out.getDoubleValue("cl_jshgzq_bb") + out.getDoubleValue("cl_jshgzq_yb"));
        out.putDoubleValue("cl_jshgzq_rhj", bigDouble.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
        bigDouble = new BigDecimal(getRandom() * 30);
        out.putDoubleValue("cl_jshgzq_ylj", bigDouble.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
        out.putDoubleValue("cl_jshgzq_rcq", getRandom());
        out.putDoubleValue("cl_jshgzq_ycq", getRandom());
        out.putDoubleValue("cl_jshgzq_kc", getRandom());
        //............
        out.putDoubleValue("cl_lh_bb", getRandom());
        out.putDoubleValue("cl_lh_yb", getRandom());
        bigDouble = new BigDecimal(out.getDoubleValue("cl_lh_bb") + out.getDoubleValue("cl_lh_yb"));
        out.putDoubleValue("cl_lh_rhj", bigDouble.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
        bigDouble = new BigDecimal(getRandom() * 30);
        out.putDoubleValue("cl_lh_ylj", bigDouble.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
        out.putDoubleValue("cl_lh_rcq", getRandom());
        out.putDoubleValue("cl_lh_ycq", getRandom());
        out.putDoubleValue("cl_lh_kc", getRandom());
        //............
        out.putDoubleValue("cl_lsa_bb", getRandom());
        out.putDoubleValue("cl_lsa_yb", getRandom());
        bigDouble = new BigDecimal(out.getDoubleValue("cl_lsa_bb") + out.getDoubleValue("cl_lsa_yb"));
        out.putDoubleValue("cl_lsa_rhj", bigDouble.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
        bigDouble = new BigDecimal(getRandom() * 30);
        out.putDoubleValue("cl_lsa_ylj", bigDouble.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
        out.putDoubleValue("cl_lsa_rcq", getRandom());
        out.putDoubleValue("cl_lsa_ycq", getRandom());
        out.putDoubleValue("cl_lsa_kc", getRandom());
        //............
        out.putDoubleValue("cl_zfd_bb", getRandom());
        out.putDoubleValue("cl_zfd_yb", getRandom());
        bigDouble = new BigDecimal(out.getDoubleValue("cl_zfd_bb") + out.getDoubleValue("cl_zfd_yb"));
        out.putDoubleValue("cl_zfd_rhj", bigDouble.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
        bigDouble = new BigDecimal(getRandom() * 30);
        out.putDoubleValue("cl_zfd_ylj", bigDouble.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
        out.putDoubleValue("cl_zfd_rcq", getRandom());
        out.putDoubleValue("cl_zfd_ycq", getRandom());
        out.putDoubleValue("cl_zfd_kc", getRandom());
        //............
        out.putDoubleValue("cl_yxmq_bb", getRandom());
        out.putDoubleValue("cl_yxmq_yb", getRandom());
        bigDouble = new BigDecimal(out.getDoubleValue("cl_yxmq_bb") + out.getDoubleValue("cl_yxmq_yb"));
        out.putDoubleValue("cl_yxmq_rhj", bigDouble.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
        bigDouble = new BigDecimal(getRandom() * 30);
        out.putDoubleValue("cl_yxmq_ylj", bigDouble.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
        out.putDoubleValue("cl_yxmq_rcq", getRandom());
        out.putDoubleValue("cl_yxmq_ycq", getRandom());
        out.putDoubleValue("cl_yxmq_kc", getRandom());
        //............  日消耗
        out.putDoubleValue("rxh_djchq_bb", getRandom());
        out.putDoubleValue("rxh_djchq_yb", getRandom());
        bigDouble = new BigDecimal(out.getDoubleValue("rxh_djchq_bb") + out.getDoubleValue("rxh_djchq_yb"));
        out.putDoubleValue("rxh_djchq_rhj", bigDouble.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
        bigDouble = new BigDecimal(getRandom() * 30);
        out.putDoubleValue("rxh_djchq_ylj", bigDouble.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
        out.putDoubleValue("rxh_djchq_rcq", getRandom());
        out.putDoubleValue("rxh_djchq_ycq", getRandom());
        out.putDoubleValue("rxh_djchq_kc", getRandom());
        //............  日消耗
        out.putDoubleValue("rxh_yxqhym_bb", getRandom());
        out.putDoubleValue("rxh_yxqhym_yb", getRandom());
        bigDouble = new BigDecimal(out.getDoubleValue("rxh_yxqhym_bb") + out.getDoubleValue("rxh_yxqhym_yb"));
        out.putDoubleValue("rxh_yxqhym_rhj", bigDouble.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
        bigDouble = new BigDecimal(getRandom() * 30);
        out.putDoubleValue("rxh_yxqhym_ylj", bigDouble.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
        out.putDoubleValue("rxh_yxqhym_rcq", getRandom());
        out.putDoubleValue("rxh_yxqhym_ycq", getRandom());
        out.putDoubleValue("rxh_yxqhym_kc", getRandom());
        //............  日消耗
        out.putDoubleValue("rxh_dzqhrlm_bb", getRandom());
        out.putDoubleValue("rxh_dzqhrlm_yb", getRandom());
        bigDouble = new BigDecimal(out.getDoubleValue("rxh_dzqhrlm_bb") + out.getDoubleValue("rxh_dzqhrlm_yb"));
        out.putDoubleValue("rxh_dzqhrlm_rhj", bigDouble.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
        bigDouble = new BigDecimal(getRandom() * 30);
        out.putDoubleValue("rxh_dzqhrlm_ylj", bigDouble.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
        out.putDoubleValue("rxh_dzqhrlm_rcq", getRandom());
        out.putDoubleValue("rxh_dzqhrlm_ycq", getRandom());
        out.putDoubleValue("rxh_dzqhrlm_kc", getRandom());
        //............  日消耗
        out.putDoubleValue("rxh_ylm_bb", getRandom());
        out.putDoubleValue("rxh_ylm_yb", getRandom());
        bigDouble = new BigDecimal(out.getDoubleValue("rxh_ylm_bb") + out.getDoubleValue("rxh_ylm_yb"));
        out.putDoubleValue("rxh_ylm_rhj", bigDouble.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
        bigDouble = new BigDecimal(getRandom() * 30);
        out.putDoubleValue("rxh_ylm_ylj", bigDouble.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
        out.putDoubleValue("rxh_ylm_rcq", getRandom());
        out.putDoubleValue("rxh_ylm_ycq", getRandom());
        out.putDoubleValue("rxh_ylm_kc", getRandom());
        //............  日消耗
        out.putDoubleValue("rxh_rlm_bb", getRandom());
        out.putDoubleValue("rxh_rlm_yb", getRandom());
        bigDouble = new BigDecimal(out.getDoubleValue("rxh_rlm_bb") + out.getDoubleValue("rxh_rlm_yb"));
        out.putDoubleValue("rxh_rlm_rhj", bigDouble.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
        bigDouble = new BigDecimal(getRandom() * 30);
        out.putDoubleValue("rxh_rlm_ylj", bigDouble.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
        out.putDoubleValue("rxh_rlm_rcq", getRandom());
        out.putDoubleValue("rxh_rlm_ycq", getRandom());
        out.putDoubleValue("rxh_rlm_kc", getRandom());
        //............  日消耗
        out.putDoubleValue("rxh_ydl_bb", getRandom());
        out.putDoubleValue("rxh_ydl_yb", getRandom());
        bigDouble = new BigDecimal(out.getDoubleValue("rxh_ydl_bb") + out.getDoubleValue("rxh_ydl_yb"));
        out.putDoubleValue("rxh_ydl_rhj", bigDouble.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
        bigDouble = new BigDecimal(getRandom() * 30);
        out.putDoubleValue("rxh_ydl_ylj", bigDouble.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
        out.putDoubleValue("rxh_ydl_rcq", getRandom());
        out.putDoubleValue("rxh_ydl_ycq", getRandom());
        out.putDoubleValue("rxh_ydl_kc", getRandom());
        //............  日消耗
        out.putDoubleValue("rxh_ysl_bb", getRandom());
        out.putDoubleValue("rxh_ysl_yb", getRandom());
        bigDouble = new BigDecimal(out.getDoubleValue("rxh_ysl_bb") + out.getDoubleValue("rxh_ysl_yb"));
        out.putDoubleValue("rxh_ysl_rhj", bigDouble.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
        bigDouble = new BigDecimal(getRandom() * 30);
        out.putDoubleValue("rxh_ysl_ylj", bigDouble.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
        out.putDoubleValue("rxh_ysl_rcq", getRandom());
        out.putDoubleValue("rxh_ysl_ycq", getRandom());
        out.putDoubleValue("rxh_ysl_kc", getRandom());
        //............  单耗
        out.putDoubleValue("dh_ym_rh", getRandom());
        out.putDoubleValue("dh_ym_yh", getRandom());

        out.putDoubleValue("dh_ys_rh", getRandom());
        out.putDoubleValue("dh_ys_yh", getRandom());

        out.putDoubleValue("dh_wd_rh", getRandom());
        out.putDoubleValue("dh_wd_yh", getRandom());

        out.putDoubleValue("dh_rm_rh", getRandom());
        out.putDoubleValue("dh_rm_yh", getRandom());

        //............  销售
        out.putDoubleValue("xs_cjc_rs", getRandom());
        bigDouble = new BigDecimal(getRandom() * 30);
        out.putDoubleValue("xs_cjc_ys", bigDouble.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());

        out.putDoubleValue("xs_jjc_rs", getRandom());
        bigDouble = new BigDecimal(getRandom() * 30);
        out.putDoubleValue("xs_jjc_ys", bigDouble.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());

        out.putDoubleValue("xs_yy_rs", getRandom());
        bigDouble = new BigDecimal(getRandom() * 30);
        out.putDoubleValue("xs_yy_ys", bigDouble.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());

        out.putDoubleValue("xs_yd_rs", getRandom());
        bigDouble = new BigDecimal(getRandom() * 30);
        out.putDoubleValue("xs_yd_ys", bigDouble.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());

        //生产情况综述
        out.putStringValue("rd", "B、C#炉、A#汽机运行(发电负荷2.5万kwh)，水系统运行，A、D#炉检修，除尘器压差正常。");
        out.putStringValue("qh", "系统106%氧负荷运行，K1301B摄值31um/s，1XV0223、12XV0131阀强制开，全天双费运行两次。");
        out.putStringValue("jc", "二甲醚131%负荷，精馏预共塔、回收塔运行，新氢回收停车检修，K15201段间冷却器漏特护运行，硫回收接酸气量1100Nm3/h。");

        //状态 0未审核、1已审核
        if (DateUtil.defualtFormat(new Date()).equals(today)) {
            out.putStringValue("ddrbzt", "0");
        } else {
            out.putStringValue("ddrbzt", "1");
        }

    }

    private double getRandom() {
        double d = Math.random();
        BigDecimal bigDouble = new BigDecimal(Math.random() * 10);
        return bigDouble.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
    }
}
