package com.gjl.weixin.controller;


import com.gjl.weixin.entity.Questionnaire;
import com.gjl.weixin.mapper.QuestionnaireMapper;
import com.gjl.weixin.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/questionnaire")
public class QuestionnaireController {
    @Autowired
    private QuestionnaireMapper questionnaireMapper;

    @GetMapping("/findAll")
    public R findAll(HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST");
        response.setHeader("Access-Control-Allow-Headers", "x-requested-with,content-type");
        List<Questionnaire> list = questionnaireMapper.findAll();
        if(list.size()>0){
            return R.ok(list);
        }
        return R.error("题库中没有题");
    }

}
