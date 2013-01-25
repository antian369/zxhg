package com.soa.task;

import com.soa.service.impl.UtilService;
import java.util.Date;
import java.util.TimerTask;
import javax.annotation.Resource;
import org.apache.log4j.Logger;

/**
 * 定时任务的基类，由于定时任务没有实现接口，不能使用Spring进行aop控制，所以定义一个基类做aop类
 * @author lianzt
 */
public abstract class BaseTask extends TimerTask {

    private static final Logger log = Logger.getLogger(BaseTask.class);
    @Resource
    private UtilService utilServiceImpl;

    @Override
    public void run() {
        log.info("开始运行定时任务 : " + this.getClass().getSimpleName());
        Date begin = new Date();
        try {
            runTask();
        } catch (Exception e) {
            log.warn("任务 '" + this.getClass().getSimpleName() + "' 执行异常：" + e);
            utilServiceImpl.saveError(e);
        } finally {
            Date end = new Date();
            log.info("任务 '" + this.getClass().getSimpleName() + "' 执行完成，共使用 " + (end.getTime() - begin.getTime()) + " ms");
        }
    }

    protected abstract void runTask();
}
