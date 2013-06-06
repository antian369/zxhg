package com.soa.filter;

import com.lianzt.commondata.AbstractCommonData;
import com.lianzt.commondata.DataConvertFactory;
import com.lianzt.util.StringUtil;
import com.soa.exception.GlobalException;
import com.soa.service.BaseService;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Enumeration;
import java.util.List;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.apache.tomcat.util.http.fileupload.DeferredFileOutputStream;
import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

/**
 * 设置字符集
 * @author lianzt
 */
public class EncodingFilter implements Filter {

    private static final Logger log = Logger.getLogger(EncodingFilter.class);
    private FilterConfig filterConfig = null;
    private String encoding = null;

    public EncodingFilter() {
    }

    public String getEncoding() {
        return encoding;
    }

    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }

    /**
     * 当项目编码为GBK时使用，发送普通的post请求时使用GBK，发送ajax时使用UTF-8
     * @param request The servlet request we are processing
     * @param response The servlet response we are creating
     * @param chain The filter chain we are processing
     *
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet error occurs
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        //设置请求的中文字符集
        if (request.getCharacterEncoding() != null) {
            if (log.isDebugEnabled()) {
                log.debug("\n提交的数据 encoding：" + request.getCharacterEncoding() + "， 使用设置的encoding");
            }
        } else {
            request.setCharacterEncoding(encoding);
            if (log.isDebugEnabled()) {
                log.debug("\n提交的数据 encoding 为空，使用默认encoding： " + encoding);
            }
        }

        //设置响应的数据格式，默认为文本，如果需要改变，可以在后面的控制器中重新设置
        response.setContentType("text/html;charset=" + encoding);
        //把所有的请求数据都放到一个CommonData中，先从request中取，如果没有再创建新的，这样可以在其他过滤器或拦截器中使用该CommonData
        AbstractCommonData acd = (AbstractCommonData) request.getAttribute("page_data");
        if (acd == null) {
            acd = DataConvertFactory.getInstance();
        }
        AbstractCommonData head = acd.getDataValue("head");

        head.putStringValue("_url", req.getRequestURI());
        head.putStringValue("_ip", req.getRemoteAddr());
        head.putStringValue("_user_agent", req.getHeader("User-Agent"));
        HttpSession session = req.getSession();
        acd.putObjectValue("session", session);
        if (log.isDebugEnabled()) {
            log.debug("把session放入请求报文");
        }
        //表单类型
        String contentType = request.getContentType();
        if (log.isDebugEnabled()) {
            log.debug("表单类型:" + contentType);
            log.debug("表单判断：" + StringUtil.isNull(contentType));
        }
        //文本表单
        if (StringUtil.isNull(contentType) || !contentType.trim().startsWith("multipart")) {
            Enumeration<String> en = request.getParameterNames();
            String[] valueArr = null;
            String name = "";
            String value = "";
            //请求中的参数
            while (en.hasMoreElements()) {
                name = en.nextElement();
                valueArr = request.getParameterValues(name);
                value = StringUtil.connectArray(valueArr, ",");
                request.setAttribute(name, value);
                acd.putStringValue(name, value);
            }
        } else {
            //二进制文件
            byte[] bs = null;       //文件的二进制流
            long filesize = 0;      //文件长度
            DiskFileItemFactory factory = new DiskFileItemFactory();    //uploadfile工厂
            InputStream inputStream = null;
            ByteArrayOutputStream op = null;
            DeferredFileOutputStream dfo = null;
            try {
                factory.setSizeThreshold(4096);// 设置缓冲,这个值决定了是fileinputstream还是bytearrayinputstream
                ServletFileUpload sfileUpLoad = new ServletFileUpload(factory);
                sfileUpLoad.setSizeMax(10 * 1024 * 1024);//10M
                List<FileItem> items = sfileUpLoad.parseRequest(req);     //表单项，包括文本与二进制
                //获取表单项
                for (FileItem fileItem : items) {
                    if (!fileItem.isFormField()) {
                        //如果是文件
                        inputStream = fileItem.getInputStream();
                        filesize = fileItem.getSize();
                        try {
                            if (inputStream.available() > 0) {
                                if (log.isDebugEnabled()) {
                                    log.debug("文件名：" + fileItem.getFieldName() + ",长度： " + inputStream.available());
                                }
                                bs = new byte[(int) filesize];
                                inputStream.read(bs);
                                acd.putObjectValue(fileItem.getFieldName(), bs);
                            }
                        } catch (IOException e) {
                            log.error("文件名：" + fileItem.getFieldName() + "， 处理失败", e);
                            acd.putObjectValue(fileItem.getFieldName(), null);
                        } finally {
                            if (inputStream != null) {
                                inputStream.close();
                            }
                        }
                    } else {
                        //如果是文本
                        dfo = (DeferredFileOutputStream) fileItem.getOutputStream();
                        acd.putStringValue(fileItem.getFieldName(), new String(dfo.getData(), encoding));
                    }
                }
            } catch (FileUploadException e) {
                log.error("提取上传的文件信息出现异常：", e);
                throw new GlobalException(999999, e);
            } finally {
                if (dfo != null) {
                    dfo.close();
                }
                if (op != null) {
                    op.close();
                }
            }
        }
        //把请求参数的CommonData再放入request
        request.setAttribute("page_data", acd);
        if (log.isInfoEnabled()) {
            log.info("\n================ 新请求开始 =================\n用户的请求数据：" + acd);
        }
        chain.doFilter(request, response);
        BaseService.flushSession(acd);       //更新Session
    }

    public FilterConfig getFilterConfig() {
        return (this.filterConfig);
    }

    public void setFilterConfig(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
    }

    @Override
    public void destroy() {
    }

    @Override
    public void init(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
        this.encoding = filterConfig.getInitParameter("encoding");
    }

    /**
     * Return a String representation of this object.
     */
    @Override
    public String toString() {
        if (filterConfig == null) {
            return ("EncodingFilter()");
        }
        StringBuilder sb = new StringBuilder("EncodingFilter(");
        sb.append(filterConfig);
        sb.append(")");
        return (sb.toString());
    }

    private void sendProcessingError(Throwable t, ServletResponse response) {
        String stackTrace = getStackTrace(t);

        if (stackTrace != null && !stackTrace.equals("")) {
            try {
                response.setContentType("text/html");
                PrintStream ps = new PrintStream(response.getOutputStream());
                PrintWriter pw = new PrintWriter(ps);
                pw.print("<html>\n<head>\n<title>Error</title>\n</head>\n<body>\n"); //NOI18N

                // PENDING! Localize this for next official release
                pw.print("<h1>The resource did not process correctly</h1>\n<pre>\n");
                pw.print(stackTrace);
                pw.print("</pre></body>\n</html>"); //NOI18N
                pw.close();
                ps.close();
                response.getOutputStream().close();
            } catch (Exception ex) {
            }
        } else {
            try {
                PrintStream ps = new PrintStream(response.getOutputStream());
                t.printStackTrace(ps);
                ps.close();
                response.getOutputStream().close();
            } catch (Exception ex) {
            }
        }
    }

    public static String getStackTrace(Throwable t) {
        String stackTrace = null;
        try {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            t.printStackTrace(pw);
            pw.close();
            sw.close();
            stackTrace = sw.getBuffer().toString();
        } catch (Exception ex) {
        }
        return stackTrace;
    }

    public void log(String msg) {
        filterConfig.getServletContext().log(msg);
    }
}
