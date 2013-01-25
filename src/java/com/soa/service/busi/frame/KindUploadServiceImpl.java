/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soa.service.busi.frame;

import com.lianzt.commondata.AbstractCommonData;
import com.lianzt.util.StringUtil;
import com.soa.exception.GlobalException;
import com.soa.service.BaseService;
import com.soa.util.SystemUtil;
import java.util.Date;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

/**
 * kindeditor组件上传文件<br />
 * service_code : S10005<br />
 * 响应：url:文件上传后的url
 * @author lianzt
 */
@Service
public class KindUploadServiceImpl extends BaseService {

    private static final String[] KEY = new String[]{"localUrl", "文件名",
                                                     "imgFile", "文件"};
    public static int MAX_SIZE = 20 * 1024;
    public static String FIX = "jpg,jpeg,gif,png";
    private static final Logger log = Logger.getLogger(KindUploadServiceImpl.class);

    @Override
    public String[] keys() {
        return KEY;
    }

    /**
     * 重写默认的检验函数，只抛出 999972 文件上传异常
     * @param in
     * @param inHead
     */
    @Override
    public void check(AbstractCommonData in, AbstractCommonData inHead) {
        if (StringUtil.isNull(in.getStringValue("localUrl"))) {
            throw new GlobalException(999972, "文件名为空！");
        }
        byte[] bs = (byte[]) in.getObjectValue("imgFile");
        if (bs == null || bs.length < 1) {
            throw new GlobalException(999972, "文件内容为空！");
        }
        if (bs.length > MAX_SIZE) {
            throw new GlobalException(999972, "图片不能超过 " + (MAX_SIZE / 1024) + " kb");
        }
    }

    /**
     * 只抛出 999972 文件上传异常
     * @param in
     * @param inHead
     * @param out
     * @param outHead
     */
    @Override
    public void execute(AbstractCommonData in, AbstractCommonData inHead,
                        AbstractCommonData out, AbstractCommonData outHead) {
        try {
            String fileName = in.getStringValue("localUrl");
            String[] cutFileName = fileName.split("\\.");
            String fix = cutFileName[cutFileName.length - 1];
            if (log.isDebugEnabled()) {
                log.debug("上传文件的扩展名：" + fix);
            }
            if (StringUtil.isNull(fix)) {
                if (log.isDebugEnabled()) {
                    log.debug("上传文件的扩展名为空，强制赋值为jpg");
                }
                fix = "jpg";
            }
            fix = fix.toLowerCase();        //转小写
            if (FIX.indexOf(fix) == -1) {
                throw new GlobalException(999972, "只允许 " + FIX + " 格式的图片");
            }
            fileName = getLoginUser(in) + new Date().getTime() + "." + fix;
            SystemUtil.saveFile((byte[]) in.getObjectValue("imgFile"), fileName);
            fileName = SystemUtil.fileWebPath + "/" + fileName;
            out.putStringValue("url", fileName.replace("/", "\\/"));
        } catch (GlobalException e) {
            throw e;
        } catch (Exception e) {
            throw new GlobalException(999972, e, e.toString());
        }
    }
}
