package com.gjl.weixin.service;

import java.util.Date;

/**
 * @Author WilliamDragon
 * @Date 2021/2/22 9:53
 * @Version 1.0
 */

public interface SynchronizeCifinfo {

    public void updateCifInfo(String tableName, Date cifDate);
}
