package com.gjl.weixin.singleton;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedLongSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author WilliamDragon
 * @Date 2021/3/18 9:58
 * @Version 1.0
 * 基于AQS 实现互斥锁
 */

public class MyAqsLock {

    ReentrantLock reentrantLock = new ReentrantLock();

    private AqsSync aqsSync = new AqsSync();

    public void lock() {
        aqsSync.acquire(1);
    }


    public void unlock() {
        aqsSync.release(1);
    }

    private class AqsSync extends AbstractQueuedLongSynchronizer{
        @Override
        protected boolean tryAcquire(long arg) {
            assert arg == 1;
            if(compareAndSetState(0,1)){
                setExclusiveOwnerThread(Thread.currentThread());
                return true;
            }
            return false;
        }

        @Override
        protected boolean tryRelease(long arg) {
            assert arg == 1;
            if(!isHeldExclusively()){
                //如果其他的线程把 state 变成1 ，此时就会释放其他线程的锁了，这显然是不对的，要释放当前线程的锁
                throw new IllegalMonitorStateException();
            }
            setExclusiveOwnerThread(null);
            setState(0);
            return true;
        }

        @Override
        protected boolean isHeldExclusively() {
            return getExclusiveOwnerThread() == Thread.currentThread();
            // 错误的做法 return getState() == 1
            //如果其他的线程把 state 变成1 ，此时就会释放其他线程的锁了，这显然是不对的，要释放当前线程的锁
        }

    }


}
