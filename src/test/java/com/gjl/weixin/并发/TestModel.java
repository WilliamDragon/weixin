package com.gjl.weixin.并发;

/**
 * @Author WilliamDragon
 * @Date 2021/4/25 11:23
 * @Version 1.0
 */

public class TestModel {

    private String coreSize;
    private String maxSize;
    private long aliveTimeSecond;
    private int queueCapacity;

    public String getCoreSize() {
        return coreSize;
    }

    public void setCoreSize(String coreSize) {
        this.coreSize = coreSize;
    }

    public String getMaxSize() {
        return maxSize;
    }

    public void setMaxSize(String maxSize) {
        this.maxSize = maxSize;
    }

    public long getAliveTimeSecond() {
        return aliveTimeSecond;
    }

    public void setAliveTimeSecond(long aliveTimeSecond) {
        this.aliveTimeSecond = aliveTimeSecond;
    }

    public int getQueueCapacity() {
        return queueCapacity;
    }

    public void setQueueCapacity(int queueCapacity) {
        this.queueCapacity = queueCapacity;
    }
}
