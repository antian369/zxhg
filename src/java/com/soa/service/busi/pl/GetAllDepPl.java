/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soa.service.busi.pl;

import com.lianzt.commondata.AbstractCommonData;
import com.soa.service.BaseService;
import com.soa.util.SystemUtil;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 * 获取所有部门信息
 * service_code : S11002
 * @author Asus
 */
@Service
public class GetAllDepPl extends BaseService{

    @Override
    public String[] keys() {
        return null;
    }

    @Override
    public void execute(AbstractCommonData in, AbstractCommonData inHead,
                        AbstractCommonData out, AbstractCommonData outHead) {
        List<AbstractCommonData> list = queryList("get_all_dep_pl");
        for(AbstractCommonData dep : list){
            dep.putStringValue("dep_type_desc", SystemUtil.getColValueDesc("pl_dep_info.dep_type", dep.getStringValue("dep_type")));
        }
        out.putArrayValue("deps", list);
    }
}
