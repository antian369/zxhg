/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soa.service.busi.pl;

import com.lianzt.commondata.AbstractCommonData;
import com.lianzt.commondata.DataConvertFactory;
import com.lianzt.exception.InstanceDataException;
import com.lianzt.util.StringUtil;
import com.soa.service.BaseService;
import com.soa.util.SystemUtil;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

/**
 * 获取用户权限<br />
 * service_code : S11001<br />
 * @author Asus
 */
@Service
public class GetUserAllLimitPl extends BaseService {

    private static final Logger log = Logger.getLogger(GetUserAllLimitPl.class);

    @Override
    public String[] keys() {
        return null;
    }

    @Override
    public void execute(AbstractCommonData in, AbstractCommonData inHead,
                        AbstractCommonData out, AbstractCommonData outHead) {
        List<AbstractCommonData> limitList = queryList("get_user_all_limit_pl", getLoginUser(in));
        AbstractCommonData session = getSession(in);
        Set<String> limitSet = (Set<String>) session.getObjectValue("limit");       //在session中保存用户权限
        for (AbstractCommonData acd : limitList) {
            limitSet.add(acd.getStringValue("m_id"));
            if ("1".equals(acd.getStringValue("m_type"))) {     //给所有目录项增加一个子节点数组
                acd.putArrayValue("chidren", new LinkedList<AbstractCommonData>());
            }
        }
        out.putArrayValue("menus", marge(limitList));
        out.putStringValue("menus_json", DataConvertFactory.praseJson(out));
    }

    /**
     * 递归整理菜单树，把线性表整理成树
     * @param list
     * @return
     */
    public static List<AbstractCommonData> marge(List<AbstractCommonData> list) {
        List<Integer> remove = new LinkedList<Integer>();
        String mSuper = null;
        for (int i = 0; i < list.size(); i++) {
            mSuper = list.get(i).getStringValue("m_super");
            if (!StringUtil.isNull(mSuper)) {       //当该菜单存在父节点时，把菜单放入父节点目录中
                for (AbstractCommonData m : list) {
                    if ("1".equals(m.getStringValue("m_type"))) {     //寻找父节点
                        //是目录时再进行判断
                        if (m.getStringValue("m_id").equals(mSuper)) {
                            //属于该目录
                            m.getArrayValue("chidren").add(list.get(i));        //把该项放入目录中
                            remove.add(i);  //循环结束后删除该项
                        }
                    }
                }
            }
        }
        if (log.isDebugEnabled()) {
            log.debug("整理前的菜单项：" + list);
            log.debug("需要删除的菜单序号：" + remove);
        }
        if (remove.size() > 0) {    //如果存在可以被删除的节点，则先删除节点，然后再继续递归
            for (int i = remove.size() - 1; i >= 0; i--) {      //从后向前删除，否则会出现数组越界异常
                list.remove(remove.get(i).intValue());
            }
            marge(list);
        }
        return list;        //由于参数参与了递归，而且过程中没有对参数进行重新赋值，所以递归完成后直接返回即可
    }
}
