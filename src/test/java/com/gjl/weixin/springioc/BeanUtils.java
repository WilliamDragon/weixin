package com.gjl.weixin.springioc;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;

/**
 * @Author: WilliamJL
 * @Date: 2019/9/10 16:34
 * @Version 1.0
 */
public class BeanUtils {

    public static Method getWriteMethod(Object beanObject, String name){
        Method method = null;
        try{
            //分析bean 对象
            BeanInfo beanInfo = Introspector.getBeanInfo(beanObject.getClass());
            // 根据 beaninfo 获取所有属性的描述器
            PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();
            // 遍历描述器
            if(pds !=null){
                for(PropertyDescriptor pd : pds){
                    //判断当前属性是否是我们要找的属性
                    String pName = pd.getName();
                    if(pName.equals(name)){
                        method = pd.getWriteMethod();
                    }
                }
            }


        }catch (Exception e){
            e.printStackTrace();
        }
        if(method == null){
            throw new RuntimeException("请检查 name属性的set方法是否创建");
        }
        return method;
    }
}
