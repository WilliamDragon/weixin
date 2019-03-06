package com.gjl.weixin.controller.export;


import ch.qos.logback.core.util.FileUtil;
import cn.afterturn.easypoi.word.WordExportUtil;
import com.gjl.weixin.utils.ExportWordUtil;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;

@Controller
public class ExportWord {

    @Autowired
    private ExportWordUtil exportWordUtil;

    @RequestMapping("export")
    public void export(HttpServletRequest request, HttpServletResponse response){
        Map<String,Object> params = new HashMap<>();
        params.put("title","这是标题");
        params.put("name","李四");
        //这里是我说的一行代码
        exportWordUtil.exportWord("C:/Users/ODAACC/Desktop/guojinlong.docx","C:/Users/ODAACC/Desktop","aaa.docx",params,request,response);

        System.out.println("export成功");

        /*Map<String, Object> map = new HashMap<String, Object>();
        map.put("department", "Easypoi");
        map.put("person", "JueYue");
        //map.put("time", Format.format(new Date()));
        map.put("me","JueYue");
        map.put("date", "2015-01-03");
        map.put("title","rsg");
        map.put("name", "dfh");
        try {
            XWPFDocument doc = WordExportUtil.exportWord07(
                    "/word/guojinlong.docx", map);
            FileOutputStream fos = new FileOutputStream("D:/simple.docx");
            doc.write(fos);
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }*/
    }
}
