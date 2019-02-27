package com.gjl.weixin.service.impl;

import com.gjl.weixin.entity.Statistic;
import com.gjl.weixin.mapper.StatisticMapper;
import com.gjl.weixin.mapper.StudentMapper;
import com.gjl.weixin.service.StatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatisticServiceImpl implements StatisticService {

    @Autowired
    private StatisticMapper statisticMapper;
    @Override
    public List<Statistic> findStatisticByGroupPxclass(String className) {
        List<Statistic> list=statisticMapper.findStatisticByGroupPxclass(className);
        return list;
    }
}
