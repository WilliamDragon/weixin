package com.gjl.weixin.utils;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class XmlToMapUtils2 {
    public static Map<String, Object> parseXmlStringToMap(String str) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        Document doc = DocumentHelper.parseText(str);
        Element rootElement = doc.getRootElement();//根目录  个数肯定为1
        List<Element> childrenElementList = rootElement.elements();//二级目录 个数大于等于1
        for (Element e : childrenElementList) {
            System.out.println(e.getQualifiedName());
            System.out.println(e.getTextTrim());
            List<Element> thirdElementList=e.elements();//三级目录
            if(thirdElementList.size()>0){//三级目录大于0
                element2Map(resultMap,e);
            }else{// 三级目录元等于0直接将二级的素存到map
                resultMap.put(e.getName(),e.getText());
            }
        }
        return resultMap;
    }
    /**
     * XML解析为Map
     *
     * @param
     * @return
     * @throws DocumentException
     * @throws IOException
     */
    public static Map<String, Object> element2Map(Map<String, Object> map, Element rootElement) throws Exception {
        Map<String, Object> tempMap = new HashMap<>();
        List<Element> childrenElementList = rootElement.elements();//三级目录（肯定大于0因为外层已经判断了）
        //遍历所有子节点
        for (Element e : childrenElementList) {
            List<Element> nextElementList=e.elements();//四级目录
            if(nextElementList.size()>0){//四级目录大于0则继续循环
                element2Map(tempMap,e);
            }else{//四级目录等于0则将三级目录存进去
                tempMap.put(e.getName(),e.getText());
            }
        }
        map.put(rootElement.getName(),tempMap);
        return map;
    }
}
