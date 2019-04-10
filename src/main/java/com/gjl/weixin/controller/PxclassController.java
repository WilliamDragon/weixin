package com.gjl.weixin.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.gjl.weixin.entity.Pxclass;
import com.gjl.weixin.entity.Student;
import com.gjl.weixin.mapper.PxclassMapper;
import com.gjl.weixin.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/pxclass")
public class PxclassController {

    @Autowired
    private PxclassMapper pxclassMapper;

    @RequestMapping("/findAll")
    public R findAll(String pageNum, String pageSize){
        if(pageNum==null){
            pageNum="1";
        }
        if(pageSize==null){
            pageSize="3";
        }

        PageHelper.startPage( Integer.valueOf(pageNum),Integer.valueOf(pageSize));
        List<Pxclass> list = pxclassMapper.findAll();
        PageInfo pageInfo = new PageInfo<Pxclass>(list, 3);

        if(list.size()>0){
            return R.ok(pageInfo);
        }
        return R.error("没有培训班");
    }
    @RequestMapping("/insert")
    public R insert(Pxclass pxclass){
        //Pxclass pxclass = new Pxclass();
        int i= pxclassMapper.insertSelective(pxclass);
        if(i>0){
            return R.ok("成功");
        }
        return R.error("插入失败");
    }
}
