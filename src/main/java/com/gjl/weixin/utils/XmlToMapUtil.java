package com.gjl.weixin.utils;

import com.gjl.weixin.common.exception.FinancialException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

public class XmlToMapUtil {
    private Map<String, Object> rstMap;
    private Map<String, Object> listrstMap;
    private List<Map> rstMapList;
    protected String bodyList="list";
    public void getParseXmlrstMap(String recXml){
        try {
            Document document = DocumentHelper.parseText(recXml);
            Element root  = document.getRootElement();
            for (Iterator it = root.elements().iterator(); it.hasNext();) {
                Element ele = (Element) it.next();
                parseXml(ele);
            }
            rstMap.put(bodyList, rstMapList==null?new ArrayList<Object>():rstMapList);
        } catch (Exception e) {
            throw new FinancialException(" Response  error：" + recXml + e.getMessage());
        }
    }
    /**
     * 单包解析,解析节点
     * @param element
     */
    private final void parseXml(Element element) {
        if (rstMap == null) {
            rstMap = new HashMap<String, Object>();
        }
        List elements = element.elements();
        if (elements.size() == 0) {
            String key = element.getQualifiedName().trim().toUpperCase();
            //trim().toUpperCase();
            String value = element.getTextTrim() == null ? null : element.getTextTrim();
            rstMap.put(key, value);
        } else {
            for (Iterator it = elements.iterator(); it.hasNext();) {
                Element elem = (Element) it.next();
                if (elem.getQualifiedName().trim().equals(bodyList)) {
                    parseXmlList(elem);
                }else{
                    parseXml(elem);
                }
            }
        }
    }
    /***
     * @Title: parseXmlList
     * @Description: 解析boyd下的list信息
     * @param element void
     */
    private final void parseXmlList(Element element) {
        if (rstMapList == null) {
            rstMapList = new ArrayList();
        }
        List elements = element.elements();
        if (elements.size() != 0){
            for (Iterator it = element.elements().iterator(); it.hasNext();) {
                Element ele = (Element) it.next();
                listrstMap = new HashMap<String, Object>();
                parselistXml(ele);
                Map<String, Object> entityMap = new HashMap<String, Object>();
                entityMap.putAll(listrstMap);
                rstMapList.add(entityMap);
            }
        }

    }
    /**
     * list下单包解析,解析节点
     * @param element
     */
    private final void parselistXml(Element element) {
        List elements = element.elements();
        if (elements.size() == 0) {
            String key = element.getQualifiedName().trim().toUpperCase();
            String value = element.getTextTrim() == null ? null : element.getTextTrim();
            listrstMap.put(key, value);
        } else {
            for (Iterator it = elements.iterator(); it.hasNext();) {
                Element elem = (Element) it.next();
                parselistXml(elem);
            }
        }
    }

    public Map<String, Object> getRstMap() {
        return rstMap;
    }
    public Map<String, Object> getListrstMap() {
        return listrstMap;
    }
    public void setListrstMap(Map<String, Object> listrstMap) {
        this.listrstMap = listrstMap;
    }
    public List<Map> getRstMapList() {
        return rstMapList;
    }
    public void setRstMapList(List<Map> rstMapList) {
        this.rstMapList = rstMapList;
    }
    public String getBodyList() {
        return bodyList;
    }
    public void setBodyList(String bodyList) {
        this.bodyList = bodyList;
    }
    public void setRstMap(Map<String, Object> rstMap) {
        this.rstMap = rstMap;
    }

    public static void main(String args[]) {
        Map<String, Object> ipdpContentMap = new HashMap<>();
        String ipdpContent = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\r\n" +
                "<req>\r\n" +
                "    <header>\r\n" +
                "        <channelId>01</channelId>\r\n" +
                "        <branchNo>00361</branchNo>\r\n" +
                "        <tellerId>9880100</tellerId>\r\n" +
                "        <requestSeq></requestSeq>\r\n" +
                "        <frontTransactionCode>XCE50006</frontTransactionCode>\r\n" +
                "        <systemTransactionCode>XCE50006</systemTransactionCode>\r\n" +
                "        <subChannelId>01</subChannelId>\r\n" +
                "    </header>\r\n" +
                "    <body>\r\n" +
                "        <BANKCODE></BANKCODE>\r\n" +
                "        <JOBTYPE>SY</JOBTYPE>\r\n" +
                "        <ACCNO>113001387591</ACCNO>\r\n" +
                "        <PROID>RJYL-001</PROID>\r\n" +
                "        <CHARCODE>1</CHARCODE>>\r\n" +
                "        <PURAMT>2333.33</PURAMT>\r\n" +
                "        <MAKNO>1</MAKNO>\r\n" +
                "        <ISRENEW></ISRENEW>\r\n" +
                "        <CYCCNT></CYCCNT>\r\n" +
                "        <MSG></MSG>\r\n" +
                "        <ORDERREDDATE></ORDERREDDATE>\r\n" +
                "        <KIND></KIND>\r\n" +
                "    </body>\r\n" +
                "</req>";
        String code = "";
        XmlToMapUtil xmlMap = new XmlToMapUtil();
        // ipdp上送XML转MAP对象
        xmlMap.getParseXmlrstMap(ipdpContent);
        ipdpContentMap = xmlMap.getRstMap();
        ipdpContentMap.get("channelId");
        ipdpContentMap.get("JOBTYPE");
        System.out.println("channelId :" + ipdpContentMap.get("CHANNELID"));
        System.out.println("JOBTYPE :" + ipdpContentMap.get("JOBTYPE"));

        System.out.println("code :" + ipdpContentMap.get("SYSTEMTRANSACTIONCODE").toString());


        System.out.println("ipdpContentMap :" + ipdpContentMap);
        code = ipdpContentMap.get("SYSTEMTRANSACTIONCODE").toString();
    }

}

