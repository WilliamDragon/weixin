package com.gjl.weixin.singleton;

public class DoubleCheck {

    private static volatile DoubleCheck instance;
    //（11）为什么必须加volatile 关键字
    //必须加volatile （1）保证线程可见性（2）禁止指令重拍
    //因为instance = new DoubleCheck(); 不是原子操作
    //(1)分配内存（2）初始化操作（3）返回引用 ，不加volatile，可能会导致（1）（3）（2）操作
    /*  1.给 instance 分配内存
        2.调用 Singleton 的构造函数来初始化成员变量
        3.将instance对象指向分配的内存空间（执行完这步 instance 就为非 null 了）*/
    //如果执行到（1）（3）时，此时另一个线程来调用会得到一个为初始化的对象，导致问题的出现
    //所以必须加volatile 让其顺序执行（1）（2）（3）

    //synchronized 也具有禁止指令重排的特性，但是这代码里禁止的是DoubleCheck有关的禁止，不禁止instance
    //volatile 是禁止和 instance 有关的指令重排

    //(22) 为什么必须在synchronized里面重新判断instance == null 可避免生成多个实例对象

    //第一次检验是在线程执行 getInstance() 方法时，如果不为 null 就直接返回。那么第二次检验如代码所示，
    // 位于 synchronized 修饰的代码块中。
    //这是由于假设此刻 instance 为 null，如果A，B两个线程同时判断 instance == null 成立，
    // 那么两个线程都会进行锁资源的争夺，如果 A 获取到锁资源，则 B 进行阻塞，待 A 完成实例化操作释放掉锁资源后，
    // B 被唤醒，而此刻必须重新判断 instance 的状态，否则 B 会依旧认为 instance 为 null，进行实例化操作，
    // 创建新的对象，那么便违背了单例模式只有一个实例对象的原则。

    public DoubleCheck(){};
    public static DoubleCheck getInstance(){
        if(instance == null){
            synchronized (DoubleCheck.class){
                if(instance == null){
                    instance = new DoubleCheck();
                }
            }
        }
        return instance;
    }

}
