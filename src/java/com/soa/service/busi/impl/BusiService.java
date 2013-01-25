package com.soa.service.busi.impl;

import com.lianzt.commondata.AbstractCommonData;

/**
 * 服务
 * @author lianzt
 */
public interface BusiService {

    /**
     * 执行服务
     * @param in
     * @param inHead
     * @param out
     * @param outHead
     * @throws Exception 建议抛出处理后的异常<br />数据库异常：DataAccessException<br />服务异常：GlobalException
     */
    public void execute(AbstractCommonData in, AbstractCommonData inHead,
                        AbstractCommonData out, AbstractCommonData outHead);

    /**
     * 执行服务前的校验
     * @param in
     * @param inHead
     * @throws Exception 建议抛出处理后的异常<br />数据校验异常：GlobalException
     */
    public void check(AbstractCommonData in, AbstractCommonData inHead);
}
