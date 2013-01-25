/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soa.service.busi.frame;

import com.lianzt.commondata.AbstractCommonData;
import com.soa.exception.GlobalException;
import com.soa.service.BaseService;
import com.soa.util.SystemUtil;
import java.io.File;
import java.util.LinkedList;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 文件上传服务
 * P10002
 * 说明：上传服务应该在具体的服务执行成功后调用，每个文件名之间用分号分隔
 * @author Asus
 */
@Service
public class FileUploadServiceImpl extends BaseService {

    private static final String[] KEY = new String[]{"serial_num", "流水号",
                                                     "file_names", "文件名",
                                                     "m_id", "文件类型"};

    @Override
    public String[] keys() {
        return KEY;
    }

    @Override
    public void check(AbstractCommonData in, AbstractCommonData inHead) {
        super.check(in, inHead);
        String[] fileNames = in.getStringValue("file_names").split(";");
        for (String name : fileNames) {
            if (in.get(name) == null) {
                throw new GlobalException(200026, "文件 《" + name + "》 ");        //!#!不能为空
            }
        }
    }

    @Override
    @Transactional
    public void execute(AbstractCommonData in, AbstractCommonData inHead,
                        AbstractCommonData out, AbstractCommonData outHead) {
        List<Object[]> args = new LinkedList<Object[]>();       //sql语句的参数
        String docType = in.getStringValue("m_id");
        String serialNum = in.getStringValue("serial_num");
        String path = SystemUtil.filePath + File.separator + docType + File.separator;      //路径
        new File(path).mkdir();     //创建目录
        Object[] as = null;
        byte[] file = null;
        String fileName = null;
        String[] fileNames = in.getStringValue("file_names").split(";");
        try {
            for (String name : fileNames) {
                file = (byte[]) in.getObjectValue(name);
                fileName = name.replace(".", serialNum + ".");      //保证文件不重名
                SystemUtil.saveFile(file, path, fileName);      //保存文件
                as = new Object[6];
                as[0] = in.getStringValue("serial_num");
                as[1] = fileName;
                as[2] = path + fileName;
                as[3] = docType;
                as[4] = file.length;
                as[5] = getLoginUser(in);
                args.add(as);
            }
        } catch (Exception e) {
            throw new GlobalException(210032);        //保存文件失败
        }
        batchUpdate("save_file_upload_frame", args);
    }
}
