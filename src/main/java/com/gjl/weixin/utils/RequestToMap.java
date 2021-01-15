package com.gjl.weixin.utils;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class RequestToMap {

    public static Map<String,Object> RequestParseMap(HttpServletRequest request){
        Map<String,Object> context = (Map<String,Object>)request.getAttribute("context");
        return context;
    }
}
