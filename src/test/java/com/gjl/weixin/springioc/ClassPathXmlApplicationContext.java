package com.gjl.weixin.springioc;




import org.springframework.aop.framework.autoproxy.BeanNameAutoProxyCreator;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

/**
 * @Author: WilliamJL
 * @Date: 2019/9/10 10:12
 * @Version 1.0
 */
public class ClassPathXmlApplicationContext implements BeanFactory {

    private Map<String, Bean> config;
    //用map来做spring 容器，放置spring所管理的对象
    private Map<String, Object> context = new HashMap<String, Object>();
    @Override
    public Object getBean(String beanName) {
        Object bean = context.get(beanName);
        return bean;
    }
    //根据bean 配置创建bean 对象
    private Object createBean(Bean bean){
        //获得要创建的bean 的 class
          String className = bean.getClassName();
          Class clazz = null;
          try{
              clazz = Class.forName(className);
          }catch (ClassNotFoundException e){
              e.printStackTrace();
          }

          //将class对应的对象创建出来
        Object beanObj = null;
        try {
            beanObj = clazz.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //获得bean的属性， 将其注入
        if(bean.getProperties() != null){
            for(Property property:bean.getProperties()){
                // 1: 简单的属性注入
                //获取要注入的属性名称
                String name = property.getName();
                Method setMethod = BeanUtils.getWriteMethod(beanObj,name);
                //创建一个需要注入bean中的属性
                Object param =null;
                if(property.getValue() != null){
                    //获取要注入的属性值
                    String value = property.getValue();
                    param = value;
                }
                // 其他bean 注入
                if(property.getRef() != null){
                    Object exsitBean = context.get(property.getRef());
                    if(exsitBean == null){
                        // 如果容器中不存在，则要自己创建
                        exsitBean = createBean(config.get(property.getRef()));
                        //将创建好的bean 放入容器
                        context.put(property.getRef(),exsitBean);
                    }
                    param = exsitBean;
                }
                //调用set 方法注入
                try{
                    setMethod.invoke(beanObj,param);
                }catch (Exception e){
                    e.printStackTrace();
                    throw new RuntimeException("bean的属性"+param+"没有对应的set方法，或者参数不正确"+className);
                }

            }
        }

    return beanObj;
    }
}
