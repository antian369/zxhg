package com.soa.service.impl;

import com.soa.bean.StServiceBean;
import com.soa.bean.StTableParamet;
import java.util.List;
import java.util.Map;

public interface UtilService {

    public Map<Integer, String> readErrMsg();

    public Map<String, List<StTableParamet>> readTablePara();

    public Map<String, StServiceBean> readServiceBean();

    public boolean saveError(Exception e);
}
