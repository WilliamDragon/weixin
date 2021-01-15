package com.gjl.weixin.test;

import com.gjl.weixin.utils.XmlToMapUtils2;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.util.Map;
/*#ertyuio*/
public class XmlToMapTest {
    public static final String FILE_PATH = "D:\\\\weixin\\\\src\\\\main\\\\resources\\\\xml\\";

    public static final String FILE_NAME = FILE_PATH + "main_exam_call.xml";

    public static void main(String[] args) throws Exception {
        String xml = FileUtils.readFileToString(new File(FILE_NAME), "UTF-8");

        //Map<String, Object> map2 = XmlToMapUtils.xmlToMap(xml);

        Map<String, Object> map = XmlToMapUtils2.parseXmlStringToMap(xml);
       // Map<String, Object> map = XmlToMapUtils.xmlToMap(xml);
        System.out.println(map.get("checkup_date")+"              "+map.get("age"));
        System.out.println(map.get("namehead")+"              "+map.get("agehead"));
        System.out.println(map.get("head")+"              "+map.get("data"));
        Map<String,Object> mapa = (Map<String,Object>)map.get("head");
        Map<String,Object> mapb = (Map<String,Object>)map.get("data");
        System.out.println(mapa.get("namehead")+"              "+mapa.get("agehead"));
        System.out.println(mapb.get("checkup_date")+"              "+mapb.get("age"));
        System.out.println("=======");


    }

}
