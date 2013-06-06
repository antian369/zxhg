package com.soa.controller;

import com.lianzt.commondata.AbstractCommonData;
import com.lianzt.commondata.DataConvertFactory;
import com.soa.exception.GlobalException;
import com.soa.service.BaseService;
import com.soa.service.impl.UtilService;
import com.soa.util.AjaxUtil;
import com.lianzt.util.StringUtil;
import com.soa.util.SystemUtil;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 框架级别的controller<br />处理异常等信息
 * @author lianzt
 */
@Controller
public class SystemController {

    private static final Logger log = Logger.getLogger(SystemController.class);
    private static final String PAGE_TYPE = "page";
    @Resource
    private UtilService utilServiceImpl;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public void indexGet(HttpServletRequest request, Map map) {
    }

    /**
     * 通过ajax请求的动态页面，动态页面都保存在template文件夹中
     * @param request
     * @param map
     * @param pageName jsp页面的名称
     * @return
     */
    @RequestMapping(value = "/_page/{pageName}")
    public String getPage(HttpServletRequest request, Map map,
            @PathVariable("pageName") String pageName) {
        if (log.isInfoEnabled()) {
            log.info("请求的页面是：" + pageName);
        }
        AbstractCommonData pageData = (AbstractCommonData) request.getAttribute("page_data");
        AbstractCommonData session = BaseService.getSession(pageData);      //session
        Set<String> limitSet = (Set<String>) session.getObjectValue("limit");       //权限
        if (log.isDebugEnabled()) {
            log.debug("拥有的权限：" + limitSet);
        }
        String[] pageArr = pageName.split("/");
        if (limitSet == null || limitSet.isEmpty()) {
            throw new GlobalException(200023);      //请先登录
        }
        if (pageArr[pageArr.length - 1].length() == 6 && !limitSet.contains(pageArr[pageArr.length - 1])) {
            throw new GlobalException(200007);      //权限不足
        }
        String services = SystemUtil.pageServiceProperties.getProperty(pageName);
        if (log.isInfoEnabled()) {
            log.info("页面需要执行的服务：" + services);
        }
        if (!StringUtil.isNull(services)) {
            services = services.trim().replace(" ", "");        //删除空格
            String[] serviceArr = services.split(",");
            String translationService = null;
            try {
                for (String s : serviceArr) {
                    if (!s.startsWith("S")) {
                        translationService = services.substring(services.indexOf(s));
                        break;
                    }
                }
                AbstractCommonData out = null;
                for (String s : serviceArr) {
                    if (!StringUtil.isNull(translationService)) {        //如果某个服务需要打开事务，就把该服务之后的所有服务放到P10001中
                        if (translationService.startsWith(s)) {
                            break;
                        }
                    }
                    if (out == null) {
                        out = BaseService.runService(pageData, s.trim());
                    } else {
                        pageData.getDataValue("head").putStringValue("service_code", s.trim());
                        BaseService.runService(pageData, pageData.getDataValue("head"), out, out.getDataValue("head"));
                    }
                }

                if (!StringUtil.isNull(translationService)) {       //在同一事务中处理接下来的所有服务
                    pageData.putStringValue("translation_service", translationService);
                    if (out == null) {
                        out = BaseService.runService(pageData, "P10001");
                    } else {
                        pageData.getDataValue("head").putStringValue("service_code", "P10001");
                        BaseService.runService(pageData, pageData.getDataValue("head"), out, out.getDataValue("head"));
                    }
                }
                if (log.isInfoEnabled()) {
                    log.info("响应数据：" + out);
                }
                map.put("out_json", DataConvertFactory.praseJson(out));
                map.putAll(out);
            } catch (GlobalException ge) {
                //........异常处理
                log.warn("业务执行异常：" + ge);
                if (!PAGE_TYPE.equals(pageData.getDataValue("head").getStringValue("_type"))) {
                    //如果页面不是以ajax方式请求，抛出异常
                    if (log.isDebugEnabled()) {
                        log.debug("不是以ajax方式请求，抛出异常，使用spring框架的异常处理");
                    }
                    throw ge;
                }
                map.put("_ge", ge);
                return "/template/page_error";
            }
        }
        map.putAll(SystemUtil.tableMap);
        return "/page/" + pageName;
    }

    /**
     * 通过ajax请求的动态页面，动态页面都保存在template文件夹中，二级目录
     * @param request
     * @param map
     * @param firstPage
     * @param secondPage
     * @return
     */
    @RequestMapping(value = "/_page/{firstPage}/{secondPage}")
    public String getPage(HttpServletRequest request, Map map,
            @PathVariable("firstPage") String firstPage,
            @PathVariable("secondPage") String secondPage) {
        return getPage(request, map, firstPage + "/" + secondPage);
    }

    /**
     * 通过ajax请求的动态页面，动态页面都保存在template文件夹中，三级目录
     * @param request
     * @param map
     * @param firstPage
     * @param secondPage
     * @param thirdPage
     * @return
     */
    @RequestMapping(value = "/_page/{firstPage}/{secondPage}/{thirdPage}")
    public String getPage(HttpServletRequest request, Map map,
            @PathVariable("firstPage") String firstPage,
            @PathVariable("secondPage") String secondPage,
            @PathVariable("thirdPage") String thirdPage) {
        return getPage(request, map, firstPage + "/" + secondPage + "/" + thirdPage);
    }

    /**
     * 通过ajax请求的动态页面，动态页面都保存在template文件夹中，四级目录
     * @param request
     * @param map
     * @param firstPage
     * @param secondPage
     * @param thirdPage
     * @param forthPage
     * @return
     */
    @RequestMapping(value = "/_page/{firstPage}/{secondPage}/{thirdPage}/{forthPage}")
    public String getPage(HttpServletRequest request, Map map,
            @PathVariable("firstPage") String firstPage,
            @PathVariable("secondPage") String secondPage,
            @PathVariable("thirdPage") String thirdPage,
            @PathVariable("forthPage") String forthPage) {
        return getPage(request, map, firstPage + "/" + secondPage + "/" + thirdPage + "/" + forthPage);
    }

    /**
     * 返回一个JSChart构建JSON
     * @param request
     * @param out
     * @param serviceCode
     */
    @RequestMapping(value = "/chart/data/{service_code}")
    public void getChartData(HttpServletRequest request, PrintWriter out, HttpServletResponse response,
            @PathVariable("service_code") String serviceCode) {

        if (log.isDebugEnabled()) {
            log.debug("开始处理 JSChart 图表请求。");
        }
        AbstractCommonData req = null;
        AbstractCommonData reqHead = null;
        AbstractCommonData res = null;
        AbstractCommonData resHead = null;
        AbstractCommonData JSChart = null;
        try {
            req = (AbstractCommonData) request.getAttribute("page_data");
            reqHead = req.getDataValue("head");
            //校验服务码
            if (StringUtil.isNull(serviceCode)) {
                throw new GlobalException(999992);      //服务码为空
            }
            reqHead.putStringValue("service_code", serviceCode);
            reqHead.putDateValue("send_time", new Date());
            res = DataConvertFactory.getInstance();     //响应报文
            resHead = res.getDataValue("head");
            BaseService.runService(req, reqHead, res, resHead);
            //取响应报文
            JSChart = res.getDataValue("JSChart");
            response.setHeader("Pragma", "No-cache");
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expires", 0);
            response.setContentType("application/json");      //设置页面格式为json
            out.print(DataConvertFactory.praseNormJson(JSChart));
            //设置页面不缓存
        } catch (GlobalException ge) {
            if (ge.getErrorCode() > 900000) {
                log.warn("系统异常：", ge);
                utilServiceImpl.saveError(ge);
            } else {
                log.warn(ge);
            }
            throw ge;
        } catch (DataAccessException e) {
            log.warn("数据库异常：", e);
            utilServiceImpl.saveError(e);
            throw new GlobalException(999998, e);
        } catch (Exception e) {
            log.warn("系统未知异常：", e);
            utilServiceImpl.saveError(e);
            throw new GlobalException(999999, e);
        }
    }

    /**
     * ajax请求的分发器，处理所有的ajax请求
     * @param request
     * @param out
     * @param serviceCode
     */
    @RequestMapping(value = "/ajax", method = RequestMethod.POST)
    public void ajaxServicePost(HttpServletRequest request, PrintWriter out) {
        if (log.isDebugEnabled()) {
            log.debug("开始处理ajax请求。");
        }
        AbstractCommonData req = null;
        AbstractCommonData reqHead = null;
        AbstractCommonData res = null;
        AbstractCommonData resHead = null;
        try {
            req = (AbstractCommonData) request.getAttribute("page_data");
            reqHead = req.getDataValue("head");

            //校验服务码
            if (StringUtil.isNull(reqHead.getStringValue("service_code"))) {
                throw new GlobalException(999992);      //服务码为空
            }
            reqHead.putDateValue("send_time", new Date());
            res = DataConvertFactory.getInstance();     //响应报文
            resHead = res.getDataValue("head");
            BaseService.runService(req, reqHead, res, resHead);
        } catch (GlobalException ge) {
            if (ge.getErrorCode() > 900000) {
                log.warn("系统异常：", ge);
                utilServiceImpl.saveError(ge);
            } else {
                log.warn(ge);
            }
            res = SystemUtil.creatErrorCommonData(ge);
        } catch (DataAccessException e) {
            log.warn("数据库异常：", e);
            res = SystemUtil.creatErrorCommonData(new GlobalException(999998));
            utilServiceImpl.saveError(e);
        } catch (Exception e) {
            log.warn("系统未知异常：", e);
            res = SystemUtil.creatErrorCommonData(new GlobalException(999999));
            utilServiceImpl.saveError(e);
        } finally {
            if (res != null) {
                AjaxUtil.sendJsonData(out, res);
            } else {
                log.error("响应报文为空！");
            }
        }
    }

    /**
     * 验证码
     * @param request
     * @param response
     */
    @RequestMapping(value = "/checknum", method = RequestMethod.GET)
    public void checknumGet(HttpServletRequest request,
            HttpServletResponse response) {
        //设置页面不缓存
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/jpeg");      //设置页面格式为图片

        // 在内存中创建图象
        int width = 60, height = 20;
        BufferedImage image = new BufferedImage(width, height,
                BufferedImage.TYPE_INT_RGB);

        // 获取图形上下文
        Graphics g = image.getGraphics();

        //生成随机类
        Random random = new Random();

        // 设定背景色
        g.setColor(getRandColor(200, 250));
        g.fillRect(0, 0, width, height);

        //设定字体
        g.setFont(new Font("Times New Roman", Font.PLAIN, 18));

        //画边框
        //g.setColor(new Color());
        //g.drawRect(0,0,width-1,height-1);

        // 随机产生155条干扰线，使图象中的认证码不易被其它程序探测到
        g.setColor(getRandColor(160, 200));
        for (int i = 0; i < 100; i++) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            int xl = random.nextInt(12);
            int yl = random.nextInt(12);
            g.drawLine(x, y, x + xl, y + yl);
        }

        // 取随机产生的认证码(4位数字)
        String sRand = "";
        for (int i = 0; i < 4; i++) {
            String rand = String.valueOf(random.nextInt(10));
            sRand += rand;
            // 将认证码显示到图象中
            g.setColor(new Color(20 + random.nextInt(110), 20 + random.nextInt(110), 20 + random.nextInt(110)));//调用函数出来的颜色相同，可能是因为种子太接近，所以只能直接生成
            g.drawString(rand, 13 * i + 6, 16);
        }

        // 将认证码存入SESSION
        AbstractCommonData req = (AbstractCommonData) request.getAttribute("page_data");
        AbstractCommonData session = BaseService.getSession(req);
        if (log.isDebugEnabled()) {
            log.debug("生成的验证码：" + sRand);
        }
        session.putStringValue("_checkNum", sRand);

        // 图象生效
        g.dispose();

        try {
            // 输出图象到页面
            ImageIO.write(image, "JPEG", response.getOutputStream());
            response.getOutputStream().flush();
        } catch (IOException e) {
            log.error("验证码生成异常:" + e);
            throw new GlobalException(999975, e);        //验证码生成异常
        }
    }

    /**
     * 获取随机颜色
     * @param fc 给定范围获得随机颜色
     * @param bc 给定范围获得随机颜色
     * @return
     */
    private Color getRandColor(int fc, int bc) {//给定范围获得随机颜色
        Random random = new Random();
        if (fc > 255) {
            fc = 255;
        }
        if (bc > 255) {
            bc = 255;
        }
        int r = fc + random.nextInt(bc - fc);
        int g = fc + random.nextInt(bc - fc);
        int b = fc + random.nextInt(bc - fc);
        return new Color(r, g, b);
    }
}
