/*
 * Copyright 2011-2020 the original author or authors.
 */
package com.lianzt.util;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

/**
 * xml工具
 * @author lianzt
 */
public class XmlUtil {

    private static final Logger log = Logger.getLogger(XmlUtil.class);

    /**
     * 格式化xml
     * @param xml xml字符串
     * @return 格式化后的xml
     */
    public static String formatXml(String xml) {
        StringReader sr = new StringReader(xml);
        SAXReader saxReader = new SAXReader();
        Document document;
        try {
            document = saxReader.read(sr);
            return formatXml(document);
        } catch (DocumentException ex) {
            log.error("解析xml出现异常 : " + ex);
            return null;
        }
    }

    /**
     * 格式化xml
     * @param doc
     * @return 格式化后的xml
     */
    public static String formatXml(Document doc) {
        String str = "";
        XMLWriter output = null;
        /** 格式化输出,类型IE浏览一样 */
        OutputFormat format = OutputFormat.createPrettyPrint();
        format.setEncoding("UTF-8");
        StringWriter writer = null;
        try {
            writer = new StringWriter();
            output = new XMLWriter(writer, format);
            output.write(doc);
            str = writer.toString();
            return str;
        } catch (Exception ex) {
            log.error("格式化xml出现异常" + ex);
            return null;
        } finally {
            if (output != null) {
                try {
                    output.close();
                } catch (IOException ex) {
                }
            }
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException ex) {
                }
            }
        }
    }
}
