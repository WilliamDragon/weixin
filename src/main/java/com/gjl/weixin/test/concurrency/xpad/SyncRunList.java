package com.gjl.weixin.test.concurrency.xpad;

import com.gjl.weixin.test.concurrency.TestModel;
import com.sun.webkit.LoadListenerClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.task.TaskExecutor;
import org.springframework.core.task.TaskRejectedException;

import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author WilliamDragon
 * @Date 2021/4/26 10:57
 * @Version 1.0
 * 同步数组多线程处理抽象类
 */

public abstract class SyncRunList<E> {
    private static final Logger logger = LoggerFactory.getLogger(SyncRunList.class);

    private final ReentrantLock lock;

    private List<? extends E> list;

    private transient int taskId;

    private Map<String, Object> args;
    //构造方法 传入List数组 待多线程处理
    protected SyncRunList(List<? extends E> list) {
        this(list,null);
    }
    //构造方法，初始化赋值 赋值ReetrantLock,List
    protected SyncRunList(List<? extends E> list,Map<String,Object> args){
        this.lock =  new ReentrantLock(false);
        this.list = list;
        this.args = args;
    }
    //获取下一个list元素
    public E poll(){
        final ReentrantLock lock = this.lock;
        lock.lock();
        try{
            if(taskId >= list.size()){
                return null;
            }
            return list.get(taskId++);
        }finally {
            lock.unlock();
        }
    }

    protected class SyncRunnb implements Runnable{
        @Override
        public void run() {
            try{
                E item = null;
                while((item = poll()) != null){
                    runItem(item);
                    item = null;
                }
            }finally {
                afterRun(this);
            }
        }
    }

    /*protected class SyncRunnbSingleton implements Runnable{

        private TestModel testModel;

        public SyncRunnbSingleton(TestModel testModel){
            this.testModel = testModel;
        }
        @Override
        public void run() {
            E item = (E)testModel;
            runItem(item);
        }
    }*/

    public void executorTasks2(TaskExecutor taskExecutor,TestModel testModel){
           taskExecutor.execute(new SyncRunnbSingleton(testModel));

    }

    public void executorTasks(TaskExecutor taskExecutor, int taskCount){
        for(int i=0; i < taskCount;){
            try{
                taskExecutor.execute(newRunnb());
                i++;
            }catch (TaskRejectedException e){
                logger.info("任务不能被接受");
                try{
                    Thread.sleep(1000L);
                }catch (InterruptedException ee){
                    ee.printStackTrace();
                }
            }
        }
    }
    public SyncRunnb newRunnb(){
        return new SyncRunnb();
    }

    //删除数组中指定元素
    public boolean remove(E o){
        if(o == null) return false;
        final List<? extends E> list = this.list;
        final ReentrantLock lock = this.lock;
        lock.lock();
        try{
            int idx = list.indexOf(o);
            list.remove(idx);
            if(idx < taskId){
                taskId--;
            }
            return true;
        }finally {
            lock.unlock();
        }
    }

    public void toStopRunnb(){
        final ReentrantLock lock = this.lock;
        lock.lock();
        try{
            taskId = list.size();
        }finally {
            lock.unlock();
        }
    }


    protected abstract void runItem(E item);
    protected void afterRun(SyncRunnb syncRunnb){};

    public ReentrantLock getLock() {
        return lock;
    }

    public List<? extends E> getList() {
        return list;
    }

    public int getTaskId() {
        final ReentrantLock lock = this.lock;
        lock.lock();
        try{
            return taskId;
        }finally {
            lock.unlock();
        }
    }

    public Map<String, Object> getArgs() {
        return args;
    }
}
