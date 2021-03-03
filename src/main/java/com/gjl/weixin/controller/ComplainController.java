package com.gjl.weixin.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.gjl.weixin.dto.ComplainDto;
import com.gjl.weixin.entity.Complain;
import com.gjl.weixin.entity.ScheduledTask;
import com.gjl.weixin.entity.Student;
import com.gjl.weixin.mapper.ComplainMapper;
import com.gjl.weixin.service.ComplainService;
import com.gjl.weixin.utils.R;
import com.gjl.weixin.utils.listUtil;
import org.apache.http.HttpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: WilliamJL
 * @Date: 2019/5/13 13:47
 * @Version 1.0
 */

@RestController
@RequestMapping("/complain")
public class ComplainController {

    @Autowired
    ComplainMapper complainMapper;
    @Autowired
    private ComplainService complainService;
    @PostMapping("/insert")
    public R insert(Complain complain, HttpServletRequest request){
        HttpSession session=request.getSession();
        //查询当前用户信息
        Student userInfo = (Student)session.getAttribute("userInfo");
        if(userInfo == null){
            return R.error("用户未登录");
        }
        complain.setUserId(userInfo.getId().toString());
        complain.setComplainVideo("0");
        String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        complain.setComplainTime(date);
        int i=complainMapper.insert(complain);
        if( i>0 ){
            return R.ok();
        }
        else{
            return R.error("插入失败");
        }
    }
    @RequestMapping("/findAll")
    public R findAll(){
        List<Complain> list=complainMapper.findAll();
        return R.ok(list);
    }

    @RequestMapping("/findAllByAdmin")
    public R findAllByAdmin(String pageNum, String pageSize,ComplainDto complainDto){
        if(pageNum==null){
            pageNum="1";
        }
        if(pageSize==null){
            pageSize="3";
        }
        PageHelper.startPage( Integer.valueOf(pageNum),Integer.valueOf(pageSize));
        List<ComplainDto> list=complainMapper.findAllByAdmin(complainDto);
        PageInfo pageInfo = new PageInfo<ComplainDto>(list, 3);
        if(list.size()>0){
            return R.ok(pageInfo);
        }
        return R.error("没有投诉信息");
    }
    int num = 0;
    static int num2 =0;
    @PostMapping("/complainTranctionTest")
    public R ComplainTranctionTest(Complain complain){

        int result = complainService.insertComplain(complain);
        if(result >= 1){
            return R.ok();
        }

        return R.build(R.CODE_FAIL,"插入Complain表失败");
        //int result = complainService.updateComplain(complain);
        //return R.ok();


    }
    @PostMapping("/findAllComplain")
    public R findAllComplain(Complain complain){
        HashMap<String,Object> queryParam = new HashMap<>();
        String complainVideo = complain.getComplainVideo();
        queryParam.put("complainVideo",complainVideo);

        List<ComplainDto> allComplain = complainService.findAllComplain(queryParam);
        //使用多线程stream流来代替for循环的操作，可以提升效率
        allComplain.parallelStream().forEach(po->{
            try{
                handlerDealComlain(po);
            }catch (IndexOutOfBoundsException e){
                e.printStackTrace();
            }
        });
        Boolean isdata = listUtil.isData(allComplain);
        if(isdata){
            return R.ok(allComplain);
        }
        return R.build(R.CODE_FAIL,"查询Complain表失败");
    }

    public void handlerDealComlain(ComplainDto complainDto){
        //todo 可更新，必须是主键和索引，才可以按行锁更新，和隔离级别有关系（oracle）
    }
    @PostMapping("/testGlobal")
    public void testGlobal(HttpServletRequest request){

        Map<String,Object> mapb = (Map<String,Object>)request.getAttribute("context");
        System.out.println("controller"+mapb.get("checkup_date")+"              "+mapb.get("age"));
    }

}
