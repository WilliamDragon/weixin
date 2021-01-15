package com.gjl.weixin.controller;

import com.gjl.weixin.dto.Qqhr00001RequestDto;
import com.gjl.weixin.dto.QuestionnaireDto;
import com.gjl.weixin.utils.BeanReflectUtil;
import com.gjl.weixin.utils.RequestToMap;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Component
public class Qqhr00001Controller {

    @PostMapping("/Qqhr00001")
    public void Qqhr00001(HttpServletRequest request) throws Exception {
        Map<String, Object> context = RequestToMap.RequestParseMap(request);
        Qqhr00001RequestDto qqhr00001RequestDto = new Qqhr00001RequestDto();
        qqhr00001RequestDto = BeanReflectUtil.reflectMapToBean(context,qqhr00001RequestDto);


    }

    public static void main(String[] args) throws Exception {
        HashMap<String, Object> context1 = new HashMap<>();
        Qqhr00001RequestDto qqhr00001RequestDto = new Qqhr00001RequestDto();
        context1.put("sysCode","E50006");
        context1.put("sysAffect","影响");
        context1.put("channelId","08");
        qqhr00001RequestDto = BeanReflectUtil.reflectMapToBean(context1,qqhr00001RequestDto);
        System.out.println("channel" + qqhr00001RequestDto.getChannelId());
        System.out.println("syscode" + qqhr00001RequestDto.getSysCode());
        System.out.println("sysaffect" + qqhr00001RequestDto.getSysAffect());
    }

}
