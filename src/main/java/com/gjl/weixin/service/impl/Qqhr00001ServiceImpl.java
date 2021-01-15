package com.gjl.weixin.service.impl;

import com.gjl.weixin.dto.Qqhr00001RequestDto;
import com.gjl.weixin.service.Qqhr00001Service;
import com.gjl.weixin.utils.R;

import java.util.Map;

/**
 * @Author WilliamDragon
 * @Date 2021/1/11 15:25
 * @Version 1.0
 */

public class Qqhr00001ServiceImpl implements Qqhr00001Service {
    @Override
    public R Qqhr00001(Qqhr00001RequestDto qqhr00001RequestDto) {

        String channel = qqhr00001RequestDto.getChannelId();

        return R.ok();
    }
}
