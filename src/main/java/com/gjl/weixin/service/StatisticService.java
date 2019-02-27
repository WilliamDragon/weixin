package com.gjl.weixin.service;

import com.gjl.weixin.entity.Statistic;

import java.util.List;

public interface StatisticService {

    public List<Statistic> findStatisticByGroupPxclass(String className);
}
