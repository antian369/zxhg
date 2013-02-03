/*
 * Copyright 2011-2020 the original author or authors.
 */
package com.lianzt.commondata;

import com.lianzt.exception.InstanceDataException;
import com.lianzt.util.DateUtil;
import com.lianzt.util.StringUtil;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentFactory;
import org.dom4j.Element;
import org.dom4j.dom.DOMDocumentFactory;
import org.dom4j.io.SAXReader;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * 提供数据包对象到其他各种数据类型的相互转换
 * @author lianzt
 */
public class DataConvertFactory {

    private static final Logger log = Logger.getLogger(DataConvertFactory.class);

    /**
     * 返回数据包的xml形式
     * @return
     */
    public static String praseString(AbstractCommonData acd) {
        return praseDocument(acd).asXML();
    }

    /**
     * 把数据包对象解析为XML (dom4j的Document模型)<br />
     * 数据包中的object类型的值不能被解析<br />
     * 数据中的值只区分三种类型 AbstractCommonData、CommonDataElement和String (基本数据类型全部解析为String)<br />
     * @return 数据包的xml形式
     */
    public static Document praseDocument(AbstractCommonData acd) {
        DocumentFactory factory = DOMDocumentFactory.getInstance();
        Document document = factory.createDocument("UTF-8");
        Element root = document.addElement("root");
        structCommonDataDocument(acd, root);        //装载数据包的递归函数
        return document;
    }

    /**
     * 把数据包转为字节数组(即序列化)
     * @param acd
     * @return
     */
    public static byte[] praseBytes(AbstractCommonData acd) {
        ByteArrayOutputStream bos = null;
        ObjectOutputStream oos = null;
        try {
            bos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(bos);
            oos.writeObject(acd);
            return bos.toByteArray();
        } catch (IOException ex) {
            log.error("转换为字节流出现异常：", ex);
            return null;
        } finally {
            if (oos != null) {
                try {
                    oos.close();
                } catch (Exception e) {
                }
            }
            if (bos != null) {
                try {
                    bos.close();
                } catch (Exception e) {
                }
            }
        }
    }

    /**
     * 装载数据包的递归函数
     * @param acd 数据包对象
     * @param root xml节点
     */
    private static void structCommonDataDocument(AbstractCommonData acd,
                                                 Element root) {
        Iterator<Entry<String, CommonDataElement>> entryList = acd.entrySet().iterator();
        Entry<String, CommonDataElement> temp = null;
        CommonDataElement data = null;
        while (entryList.hasNext()) {
            temp = entryList.next();
            data = temp.getValue();
            Element item = root.addElement("item");
            structElementDataDocument(data, item);    //装载数据包中数据元素的递归函数
        }
    }

    /**
     * 装载数据包中数据元素的递归函数
     * @param data 数据元素
     * @param item xml节点
     */
    private static void structElementDataDocument(CommonDataElement data,
                                                  Element item) {
        item.addAttribute("name", data.getName());
        item.addAttribute("type", data.getType());
        item.addAttribute("length", data.getLength() + "");
        item.addAttribute("className", data.getClassName());
        if (CommonDataElement.ARRAY.equals(data.getType())) {
            for (Object obj : (List) data.getValue()) {
                if (obj instanceof CommonDataElement) {
                    structElementDataDocument((CommonDataElement) obj, item.addElement("item"));    //装载数据包中数据元素的递归函数
                } else if (obj instanceof AbstractCommonData) {
                    structCommonDataDocument((AbstractCommonData) obj, item.addElement("root"));    //装载数据包的递归函数
                } else {
                    Element e = item.addElement("item");
                    e.addAttribute("type", obj.getClass().getSimpleName());
                    e.addAttribute("className", obj.getClass().getName());
                    e.addAttribute("value", obj + "");
                }
            }
        } else if (CommonDataElement.DATE.equals(data.getType())) {
            item.addAttribute("value", DateUtil.detaledFormat(((Date) data.getValue())));
        } else if (CommonDataElement.DATA.equals(data.getType())) {
            structCommonDataDocument((AbstractCommonData) data.getValue(), item);       //装载数据包的递归函数
        } else {
            item.addAttribute("value", data.getValue() + "");
        }

    }

    /**
     * 构造一个带系统头的数据包对象，系统头的key=head
     * @param wsd
     * @return
     */
    public static AbstractCommonData getInstance() {
        DefualtCommonData dcd = new DefualtCommonData();
        DefualtCommonData head = new DefualtCommonData();
        dcd.putDataValue("head", head);
        return dcd;
    }

    /**
     * 使用序列化数组构建数据包
     * @param bytes java对象序列化字节流
     * @return
     * @throws InstanceDataException
     */
    public static AbstractCommonData getInstance(byte[] bytes) throws
            InstanceDataException {

        ByteArrayInputStream bis = null;
        ObjectInputStream ois = null;
        try {
            bis = new ByteArrayInputStream(bytes);
            ois = new ObjectInputStream(bis);
            return (AbstractCommonData) ois.readObject();
        } catch (IOException e) {
            throw new InstanceDataException("读取字节流错误。", e);
        } catch (Exception e) {
            throw new InstanceDataException("其它。", e);
        } finally {
            if (ois != null) {
                try {
                    ois.close();
                } catch (Exception e) {
                }
            }
            if (bis != null) {
                try {
                    bis.close();
                } catch (Exception e) {
                }
            }
        }
    }

    /**
     * 构造一个空的数据包对象
     * @param wsd
     * @return
     */
    public static AbstractCommonData getInstanceEmpty() {
        return new DefualtCommonData();
    }

    /**
     * 构造一个数据包对象
     * @param xml
     * @return
     */
    public static AbstractCommonData getInstance(String xml) throws
            InstanceDataException {
        try {
            return getInstanceDefualt(new SAXReader().read(new StringReader(xml)));
        } catch (DocumentException ex) {
            log.error("解析XML出现异常 ： " + ex);
            throw new InstanceDataException("解析XML出现异常", ex);
        }
    }

    /**
     * 由xml构造一个数据包对象
     * 不支持Object类型的元素
     * 如果数据包中的数组存储有基本数据类型，这些变量会被解析成String
     * @param document
     * @return
     */
    public static AbstractCommonData getInstanceDefualt(Document document)
            throws InstanceDataException {
        AbstractCommonData dcd = DataConvertFactory.getInstanceEmpty();
        Element root = document.getRootElement();
        if (!root.getName().equals("root")) {
            throw new InstanceDataException("XML的根节点名称不是'root'");
        }
        instanceDefualtData(root, dcd);     //递归构造数据包对象
        return dcd;
    }

    /**
     * 递归构造数据包对象
     * @param root xml根节点
     * @param dcd 递归的数据包
     * @throws InstanceDataException xml解析异常或格式有误
     */
    private static void instanceDefualtData(Element root, AbstractCommonData dcd)
            throws InstanceDataException {
        Element item = null;        //
        Iterator<Element> iterator = root.elementIterator();
        while (iterator.hasNext()) {
            item = iterator.next();
            if (!item.getName().equals("item")) {
                throw new InstanceDataException("XML中含有未被识别的结点名'" + item.getName() + "'");
            }
            CommonDataElement cde = instanceDefualtElement(item);       //递归构造数据包中的数据元素
            dcd.put(cde.getName(), cde);
        }
    }

    /**
     * 递归构造数据包中的数据元素
     * @param item xml节点
     * @return 数据元素
     * @throws InstanceDataException xml解析异常或格式有误
     */
    private static CommonDataElement instanceDefualtElement(Element item) throws
            InstanceDataException {
        //初始化变量
        String name = item.attributeValue("name");
        String type = item.attributeValue("type");
        String length = item.attributeValue("length");
        String value = item.attributeValue("value");
        String className = item.attributeValue("className");
        int lengthI = -1;

        if (StringUtil.isNull(name)) {
            throw new InstanceDataException("XML中含有格式异常的结点'" + item.getName() + "'， name属性为空");
        }
        if (StringUtil.isNull(type)) {
            throw new InstanceDataException("XML中含有格式异常的结点'" + item.getName() + "'， type属性为空");
        }
        //字符串或数组的长度，解析异常时赋为-1
        if (StringUtil.isNull(length)) {
            length = null;
        } else {
            try {
                lengthI = Integer.parseInt(length);
            } catch (Exception e) {
                lengthI = -1;
            }
        }
        if (StringUtil.isNull(value)) {
            value = null;
        }
        if (StringUtil.isNull(className)) {
            className = null;
        }
        if (CommonDataElement.STRING.equals(type)) {
            if (lengthI == -1) {
                return new CommonDataElement(name, type, value);
            } else {
                return new CommonDataElement(name, type, value, lengthI);
            }
        } else if (CommonDataElement.INT.equals(type)) {
            return new CommonDataElement(name, type, Integer.parseInt(value));
        } else if (CommonDataElement.LONG.equals(type)) {
            return new CommonDataElement(name, type, Long.parseLong(value));
        } else if (CommonDataElement.DATE.equals(type)) {
            return new CommonDataElement(name, type, DateUtil.parseDate(value));
        } else if (CommonDataElement.BOOLEAN.equals(type)) {
            return new CommonDataElement(name, type, Boolean.parseBoolean(value));
        } else if (CommonDataElement.DOUBLE.equals(type)) {
            return new CommonDataElement(name, type, Double.parseDouble(value));
        } else if (CommonDataElement.FLOAT.equals(type)) {
            return new CommonDataElement(name, type, Float.parseFloat(value));
        } else if (CommonDataElement.DATA.equals(type)) {
            AbstractCommonData d = DataConvertFactory.getInstanceEmpty();
            instanceDefualtData(item, d);       //递归构造数据包对象
            return new CommonDataElement(name, type, d);
        } else if (CommonDataElement.ARRAY.equals(type)) {
            List list = new LinkedList<Object>();
            Iterator<Element> arrElement = item.elementIterator();
            Element e = null;
            while (arrElement.hasNext()) {
                e = arrElement.next();
                if (e.getName().equals("root")) {
                    //数组中存储的是数据包对象
                    AbstractCommonData arrData = DataConvertFactory.getInstanceEmpty();
                    instanceDefualtData(e, arrData);        //递归构造数据包对象
                    list.add(arrData);
                } else if (e.getName().equals("item")) {
                    if (e.attributeValue("name") == null) {
                        //数组中储存的是基本数据类型
                        String elementType = e.attributeValue("type");
                        if ("String".equals(elementType)) {
                            list.add(e.attributeValue("value"));
                        } else if ("Integer".equals(elementType)) {
                            list.add(Integer.parseInt(e.attributeValue("value")));
                        } else if ("Boolean".equals(elementType)) {
                            list.add(Boolean.parseBoolean(e.attributeValue("value")));
                        } else if ("Long".equals(elementType)) {
                            list.add(Long.parseLong(e.attributeValue("value")));
                        } else if ("Double".equals(elementType)) {
                            list.add(Double.parseDouble(e.attributeValue("value")));
                        } else if ("Float".equals(elementType)) {
                            list.add(Float.parseFloat(e.attributeValue("value")));
                        } else {
                            list.add(e.attributeValue("value"));
                        }
                    } else {
                        //数组中存储的是数据元素对象
                        list.add(instanceDefualtElement(e));        //递归构造数据包中的数据元素
                    }
                } else {
                    throw new InstanceDataException("数组中存在未知的类型类型 : " + e.getName());
                }
            }
            return new CommonDataElement(name, type, list, lengthI);
        } else if (CommonDataElement.OBJECT.equals(type)) {
            log.warn("不支持从XML文件中转换为object，" + name + " 项赋为null");
            return new CommonDataElement(name, type, null);
        } else {
            throw new InstanceDataException("xml中含有未知的数据类型 : " + type);
        }
    }

    /**
     * 把数据包对象解析为Json
     * 数据包中的object类型的值不能被解析，转换过程中会丢失date和float格式
     * 数据中的值只区分三种类型 AbstractCommonData、CommonDataElement和String (基本数据类型全部解析为String)
     * @return 数据包的Json形式
     */
    public static String praseJson(AbstractCommonData acd) {
        StringBuilder sb = new StringBuilder();
        structCommonDataJson(acd, sb);        //装载数据包的递归函数
        return sb.toString();
    }

    /**
     * 装载数据包的递归函数
     * @param acd 数据包对象
     * @param sb Json字符串
     */
    private static void structCommonDataJson(AbstractCommonData acd,
                                             StringBuilder sb) {
        Iterator<Entry<String, CommonDataElement>> entryList = acd.entrySet().iterator();
        Entry<String, CommonDataElement> temp = null;
        CommonDataElement data = null;
        int i = 0;
        sb.append('{');
        while (entryList.hasNext()) {
            if (i++ > 0) {
                sb.append(',');
            }
            temp = entryList.next();
            data = temp.getValue();
            structElementDataJson(data, sb);    //装载数据包中数据元素的递归函数
        }
        sb.append('}');
    }

    /**
     * 装载数据包中数据元素的递归函数
     * @param data 数据元素
     * @param sb Json字符串
     */
    private static void structElementDataJson(CommonDataElement data,
                                              StringBuilder sb) {
        sb.append('\'').append(data.getName());
        sb.append("':");
        int i = 0;
        if (CommonDataElement.ARRAY.equals(data.getType())) {
            sb.append('[');
            for (Object obj : (List) data.getValue()) {
                if (i++ > 0) {
                    sb.append(',');
                }
//                sb.append(i++);
//                sb.append(':');
                if (obj instanceof CommonDataElement) {
                    structElementDataJson((CommonDataElement) obj, sb);    //装载数据包中数据元素的递归函数
                } else if (obj instanceof AbstractCommonData) {
                    structCommonDataJson((AbstractCommonData) obj, sb);    //装载数据包的递归函数
                } else if (obj instanceof String) {
                    sb.append("'");
                    sb.append(obj.toString().replace("'", "\\'").replace("\n", "\\n").replace("\r", "\\r"));
                    sb.append("'");
                } else {
                    sb.append(obj.toString().replace("'", "\\'").replace("\n", "\\n").replace("\r", "\\r"));
                }
            }
            sb.append(']');
        } else if (CommonDataElement.DATE.equals(data.getType())) {
            sb.append("'");
            sb.append(DateUtil.detaledFormat((Date) data.getValue()));
            sb.append("'");
        } else if (CommonDataElement.DATA.equals(data.getType())) {
            structCommonDataJson((AbstractCommonData) data.getValue(), sb);       //装载数据包的递归函数
        } else if (CommonDataElement.STRING.equals(data.getType()) || CommonDataElement.OBJECT.equals(data.getType())) {
            sb.append("'");
            sb.append(data.getValue() == null ? "" : data.getValue().toString().replace("'", "\\'").replace("\n", "\\n").replace("\r", "\\r"));
            sb.append("'");
        } else {
            sb.append(data.getValue().toString().replace("'", "\\'").replace("\n", "\\n").replace("\r", "\\r"));
        }
    }

    /**
     * 返回车驾管系统接口可识别的xml形式
     * @param acd 数据库
     * @param type 为'Q'时调用查询类接口，为'W'时调用写入类接口，默认为'Q'
     * @return xml
     */
    public static String praseCgsString(AbstractCommonData acd, String type) {
        return praseCgsDocument(acd, type).asXML();
    }

    /**
     * 把数据包对象解析为车驾管系统接口可识别的XML (dom4j的Document模型)
     * 数据包中的object类型的值不能被解析
     * 所有类型都被转换为String
     * @param acd 数据库
     * @param type 为'Q'时调用查询类接口，写入类接口需要提供具体的节点名
     * @return 数据包的xml形式
     */
    public static Document praseCgsDocument(AbstractCommonData acd, String type) {
        DocumentFactory factory = DOMDocumentFactory.getInstance();
        Document document = factory.createDocument("UTF-8");
        Element root = document.addElement("root");
        Element condition = null;
        if ("Q".equalsIgnoreCase(type)) {
            //查询类接口
            condition = root.addElement("QueryCondition");
        } else {
            //写入类接口
            condition = root.addElement(type);
        }
        Iterator<Entry<String, CommonDataElement>> entryList = acd.entrySet().iterator();
        Entry<String, CommonDataElement> temp = null;
        CommonDataElement data = null;
        while (entryList.hasNext()) {
            temp = entryList.next();
            data = temp.getValue();
            if (data.getName().equals("head")) {
                continue;
            }
            Element item = condition.addElement(temp.getKey());
            //所有类型都被转为String
            if (CommonDataElement.DATE.equals(data.getType())) {
                //对日期类型进行处理
                String dateStr = DateUtil.nsFormat((Date) data.getValue());
                try {
                    item.setText(URLEncoder.encode(dateStr, "UTF-8"));
                } catch (Exception e) {
                    log.warn("字段：" + data.getName() + " = " + dateStr + " 进行UTF-8转码异常，放弃转码。。");
                    item.setText(dateStr);
                }
            } else {
                try {
                    if (data != null) {
                        item.setText(URLEncoder.encode(data.getValue().toString(), "UTF-8"));
                    } else {
                        item.setText("");
                    }
                } catch (Exception e) {
                    log.warn("字段：" + data.getName() + " = " + data.getValue() + " 进行UTF-8转码异常，放弃转码。。");
                    item.setText(data.getValue() == null ? "" : data.getValue().toString());
                }
            }
        }
        return document;
    }

    /**
     * 使用车驾管系统接口的响应xml构造一个数据包对象
     * @param xml
     * @return
     */
    public static AbstractCommonData getInstanceForCgsXml(String xml) throws
            InstanceDataException {
        Document document = null;
        try {
            document = new SAXReader().read(new StringReader(xml));

            AbstractCommonData acd = DataConvertFactory.getInstanceEmpty();
            Element root = document.getRootElement();
            if (!root.getName().equals("root")) {
                throw new InstanceDataException("XML的根节点名称不是'root'");
            }
            Element head = root.element("head");
            if (head == null) {
                throw new InstanceDataException("从响应报文中提取'head'为空！");
            }
            AbstractCommonData acdHead = DataConvertFactory.getInstanceEmpty();
            instanceCgsData(head, acdHead);     //构造数据包的head
            acd.putDataValue("head", acdHead);
            Integer rownum = null;
            if (!StringUtil.isNull(acdHead.getStringValue("rownum"))) {
                try {
                    rownum = Integer.parseInt(acdHead.getStringValue("rownum"));
                } catch (Exception e) {
                    log.warn("数据转换异常：" + e);
                    rownum = null;
                }
            }
            if (rownum == null || rownum == 0) {
                //不包含报文体
                return acd;
            }
            Element body = root.element("body");
            if (body != null) {
                List<AbstractCommonData> list = new LinkedList<AbstractCommonData>();
                Iterator<Element> iterator = body.elementIterator();
                while (iterator.hasNext()) {
                    Element element = iterator.next();
                    AbstractCommonData bodAcd = DataConvertFactory.getInstanceEmpty();
                    instanceCgsData(element, bodAcd);     //构造报文体
                    list.add(bodAcd);
                    acd.putArrayValue(element.getName(), list);
                }
            }
            return acd;
        } catch (DocumentException ex) {
            log.error("解析XML出现异常 ： " + ex);
            throw new InstanceDataException("解析XML出现异常", ex);
        }
    }

    /**
     * 递归构造数据包对象
     * @param root xml根节点
     * @param acd 数据包
     * @throws InstanceDataException xml解析异常或格式有误
     */
    private static void instanceCgsData(Element root, AbstractCommonData acd)
            throws InstanceDataException {
        Element item = null;        //
        Iterator<Element> iterator = root.elementIterator();
        while (iterator.hasNext()) {
            item = iterator.next();
            try {
                acd.putStringValue(item.getName(), URLDecoder.decode(item.getText().trim(), "UTF-8"));
            } catch (UnsupportedEncodingException ex) {
                log.warn("对 " + item.getName() + "=" + item.getText().trim() + " 项使用UTF-8解码时出现异常： " + ex);
                acd.putStringValue(item.getName(), "");
            }
        }
    }

    /**
     * 把CommonData转为Base64编码的字符串
     * @param acd
     * @return
     */
    public static String praseBase64String(AbstractCommonData acd) {
        return new BASE64Encoder().encode(praseBytes(acd));
    }

    /**
     * 把BASE64字符串解码为CommonData
     * @param str
     * @return
     * @throws InstanceDataException
     */
    public static AbstractCommonData getInstanceByBase64(String str) throws
            InstanceDataException {
        byte[] bs = null;
        try {
            bs = new BASE64Decoder().decodeBuffer(str);
        } catch (IOException e) {
            log.warn("把参数字符串按照BASE64解码出现异常！");
            throw new InstanceDataException();
        }
        return getInstance(bs);
    }

    /**
     * 把json字符串转换为CommonData，转换过程中会丢失date和float格式
     * @param json
     * @return
     * @throws InstanceDataException
     */
    public static AbstractCommonData getInstanceByJson(String json) throws
            InstanceDataException {
        AbstractCommonData acd = DataConvertFactory.getInstanceEmpty();
        JSONObject jsonObj = null;
        try {
            jsonObj = new JSONObject(json);
            instanceJsonData(jsonObj, acd);
            return acd;
        } catch (JSONException e) {
            log.warn("使用字符串构建JSON对象异常！");
            throw new InstanceDataException("使用字符串构建JSON对象异常！", e);
        }
    }

    /**
     * 递归构造数据包对象
     * @param json Json对象
     * @param acd 递归的数据包
     * @throws InstanceDataException 解析异常或格式有误
     */
    private static void instanceJsonData(JSONObject json, AbstractCommonData acd)
            throws InstanceDataException {
        if (json == null || json.length() == 0) {
            return;
        }
        Iterator<String> iterator = json.keys();
        String key = null;
        Object value = null;
        try {
            while (iterator.hasNext()) {
                key = iterator.next();
                value = json.get(key);
                if ("JSONObject".equals(value.getClass().getSimpleName())) {
                    AbstractCommonData data = getInstanceEmpty();
                    acd.putDataValue(key, data);
                    instanceJsonData((JSONObject) value, data);
                } else if ("JSONArray".equals(value.getClass().getSimpleName())) {
                    acd.putArrayValue(key, instanceJsonArray((JSONArray) value));
                } else if ("String".equals(value.getClass().getSimpleName())) {
                    acd.putStringValue(key, value.toString());
                } else if ("Integer".equals(value.getClass().getSimpleName())) {
                    acd.putIntValue(key, (Integer) value);
                } else if ("Long".equals(value.getClass().getSimpleName())) {
                    acd.putLongValue(key, (Long) value);
                } else if ("Double".equals(value.getClass().getSimpleName())) {
                    acd.putDoubleValue(key, (Double) value);
                } else if ("Boolean".equals(value.getClass().getSimpleName())) {
                    acd.putBooleanValue(key, (Boolean) value);
                } else {
                    log.warn("json 中的字段 key=" + key + ", value=" + value + ", 未定义的类型，忽略该项。");
                }
            }
        } catch (JSONException e) {
            log.warn("获取 " + key + " 字段出现异常！");
            throw new InstanceDataException("获取 " + key + " 字段出现异常！", e);
        }
    }

    /**
     * 构建Json数据中的数组项，该数组中的每一个元素必须是同一种类型，可以是基本数据类型、CommonData、数组
     * @param array
     * @return list
     */
    public static List instanceJsonArray(JSONArray array) throws JSONException,
                                                                 InstanceDataException {
        if (array == null || array.length() == 0) {
            return new LinkedList<AbstractCommonData>();
        }
        Object value = null;
        List list = new LinkedList();
        for (int i = 0; i < array.length(); i++) {
            value = array.get(i);
            if ("JSONObject".equals(value.getClass().getSimpleName())) {
                AbstractCommonData acd = getInstanceEmpty();
                instanceJsonData((JSONObject) value, acd);
                list.add(acd);
            } else if ("JSONArray".equals(value.getClass().getSimpleName())) {
                list.add(instanceJsonArray((JSONArray) value));
            } else {
                list.add(value);
            }
        }
        return list;
    }

    /**
     * 把数据包对象解析为Json
     * 数据包中的object类型的值不能被解析，转换过程中会丢失date和float格式
     * 数据中的值只区分三种类型 AbstractCommonData、CommonDataElement和String (基本数据类型全部解析为String)
     * @return 数据包的Json形式
     */
    public static String praseSBJson(AbstractCommonData acd) {
        StringBuilder sb = new StringBuilder();
        structCommonDataSBJson(acd, sb);        //装载数据包的递归函数
        return sb.toString();
    }

    /**
     * 装载数据包的递归函数
     * @param acd 数据包对象
     * @param sb Json字符串
     */
    private static void structCommonDataSBJson(AbstractCommonData acd,
                                             StringBuilder sb) {
        Iterator<Entry<String, CommonDataElement>> entryList = acd.entrySet().iterator();
        Entry<String, CommonDataElement> temp = null;
        CommonDataElement data = null;
        int i = 0;
        sb.append('{');
        while (entryList.hasNext()) {
            if (i++ > 0) {
                sb.append(',');
            }
            temp = entryList.next();
            data = temp.getValue();
            structElementDataSBJson(data, sb);    //装载数据包中数据元素的递归函数
        }
        sb.append('}');
    }

    /**
     * 装载数据包中数据元素的递归函数
     * @param data 数据元素
     * @param sb Json字符串
     */
    private static void structElementDataSBJson(CommonDataElement data,
                                              StringBuilder sb) {
        sb.append('\"').append(data.getName());
        sb.append("\":");
        int i = 0;
        if (CommonDataElement.ARRAY.equals(data.getType())) {
            sb.append('[');
            for (Object obj : (List) data.getValue()) {
                if (i++ > 0) {
                    sb.append(',');
                }
//                sb.append(i++);
//                sb.append(':');
                if (obj instanceof CommonDataElement) {
                    structElementDataSBJson((CommonDataElement) obj, sb);    //装载数据包中数据元素的递归函数
                } else if (obj instanceof AbstractCommonData) {
                    structCommonDataSBJson((AbstractCommonData) obj, sb);    //装载数据包的递归函数
                } else if (obj instanceof String) {
                    sb.append("\"");
                    sb.append(obj.toString().replace("\"", "\\\"").replace("\n", "\\n").replace("\r", "\\r"));
                    sb.append("\"");
                } else {
                    sb.append(obj.toString().replace("\"", "\\\"").replace("\n", "\\n").replace("\r", "\\r"));
                }
            }
            sb.append(']');
        } else if (CommonDataElement.DATE.equals(data.getType())) {
            sb.append("\"");
            sb.append(DateUtil.detaledFormat((Date) data.getValue()));
            sb.append("\"");
        } else if (CommonDataElement.DATA.equals(data.getType())) {
            structCommonDataSBJson((AbstractCommonData) data.getValue(), sb);       //装载数据包的递归函数
        } else if (CommonDataElement.STRING.equals(data.getType()) || CommonDataElement.OBJECT.equals(data.getType())) {
            sb.append("\"");
            sb.append(data.getValue() == null ? "" : data.getValue().toString().replace("\"", "\\\"").replace("\n", "\\n").replace("\r", "\\r"));
            sb.append("\"");
        } else {
            sb.append(data.getValue().toString().replace("\"", "\\\"").replace("\n", "\\n").replace("\r", "\\r"));
        }
    }
}
