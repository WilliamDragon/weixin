package com.gjl.weixin.springioc;

import org.dom4j.DocumentException;

import javax.swing.text.Document;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: WilliamJL
 * @Date: 2019/9/11 15:49
 * @Version 1.0
 */
public class ConfigManager {

   /* //读取配置文件， 并返回结果
    public static Map<String ,Bean> getConfig (String path){

        Map<String,Bean> map = new HashMap<String,Bean>();
        //创建解析器
        //SAVReader savReader = new SAVReader();
        //加载配置文件
        InputStream is  = ConfigManager.class.getResourceAsStream(path);
        Document document = null;
        try {
            //document = saxReader.read(is);
        } catch (DocumentException e) {
            e.printStackTrace();
            throw new RuntimeException("请检查xml配置");
        }

    }*/

}
