package com.gjl.weixin.prototypePattern;

import java.io.Serializable;

/**
 * @Author WilliamDragon
 * @Date 2021/4/23 13:58
 * @Version 1.0
 * @Param  实现cloneable 是浅克隆， 深克隆可通过实现Serializable接口实现，详见ThreadPoolInfo
 */
public class ThreadPoolInfo implements Serializable,Cloneable{

    private static final long serialVersionUID = -804524385376531348L;
    private String name;//线程名称
    private int coreSize;//核心线程数
    private int maxSize;//最大线程数
    private long keepAlive;// 线程空闲生存时间
    private int queueNum;//队列深度

    public ThreadPoolInfo(String name, int coreSize, int maxSize, long keepAlive, int queueNum) {
        this.name = name;
        this.coreSize = coreSize;
        this.maxSize = maxSize;
        this.keepAlive = keepAlive;
        this.queueNum = queueNum;
    }

    public ThreadPoolInfo() {
    }

    @Override
    protected ThreadPoolInfo clone() throws CloneNotSupportedException {
        ThreadPoolInfo threadPoolInfo = new ThreadPoolInfo();
        threadPoolInfo.name = this.name;
        threadPoolInfo.coreSize = this.coreSize;
        threadPoolInfo.maxSize = this.maxSize;
        threadPoolInfo.keepAlive = this.keepAlive;
        threadPoolInfo.queueNum = this.queueNum;
        return threadPoolInfo;
    }

    @Override
    public int hashCode() {
        //todo
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj){
            return true;
        }
        if(obj == null){
            return false;
        }
        if(!(obj instanceof ThreadPoolInfo)){
            return false;
        }
        ThreadPoolInfo threadPoolInfo = (ThreadPoolInfo)obj;
        if(name == null){
            if(threadPoolInfo.name != null){
                return false;
            }
        }
        else if(!name.equals(threadPoolInfo.name)){
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}


