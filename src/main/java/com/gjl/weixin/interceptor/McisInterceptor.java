package com.gjl.weixin.interceptor;

import com.gjl.weixin.utils.XmlToMapUtils2;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

public class McisInterceptor extends HandlerInterceptorAdapter {
    /**
     * 进入拦截器后首先进入的方法
     *      * 返回false则不再继续执行
     *      * 返回true则继续执行
     */
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler)throws Exception
    {
        System.out.println("================================== preHandle1 ===========================================");

        request.setCharacterEncoding("UTF-8");//设置字符格式
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        response.setHeader("Access-Control-Allow-Origin", "*");
        // achieve input stream
        InputStream in = request.getInputStream();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        // length of string
        byte[] buffer = new byte[1024];
        int len = 0;
        // achieve data from in
        while ((len = in.read(buffer)) != -1) {
            out.write(buffer, 0, len);
        }
        in.close();
        out.close();
        //获取流对象内容
        String content = new String(out.toByteArray(), "utf-8");
        System.out.println("===========123456789==========="+content);
        Map<String, Object> map = XmlToMapUtils2.parseXmlStringToMap(content);
        Map<String,Object> mapa = (Map<String,Object>)map.get("head");
        Map<String,Object> mapb = (Map<String,Object>)map.get("data");
        System.out.println(mapa.get("namehead")+"              "+mapa.get("agehead"));
        System.out.println(mapb.get("checkup_date")+"              "+mapb.get("age"));

        request.setAttribute("context", mapb);
        request.setAttribute("yu", "we");
        return true;

    }
    /**
     * 生成视图时执行，可以用来处理异常，并记录在日志中
     */
    public void afterCompletion(HttpServletRequest request,HttpServletResponse response,Object arg2, Exception exception){

        System.out.println("================================== afterCompletion ===========================================");
    }
    /** -
     * 生成视图之前执行，可以修改ModelAndView
     */
    public void postHandle(HttpServletRequest request,HttpServletResponse response, Object arg2, ModelAndView arg3)throws Exception{
        System.out.println("================================== postHandle ===========================================");
    }

}
