package com.gjl.weixin.utils;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: WilliamJL
 * @Date: 2021/1/8 11:02
 * @Version 1.0
 *
 */
public class BeanReflectUtil {
    /**
     * 将Bean 映射到 Map 中
     * @param
     * @return
     */
    public static Map reflectBeanToMap(Object object){
        Map map = new HashMap();
        try{
            //向上循环object类,包括继承的类
            for(Class<?> clazz = object.getClass(); (clazz != null && clazz != Object.class); clazz = clazz.getSuperclass()){
                if(clazz != null){
                    //获取本类中的所有方法，包括私有的(private、protected、默认以及public)的方法
                    Method[] declaredMethods = clazz.getDeclaredMethods();
                    for(Method method : declaredMethods){
                        if(method.getName().startsWith("get")){
                            String keyName = method.getName().replaceFirst("get","");
                            String key = keyName.substring(0,1).toLowerCase()+keyName.substring(1);
                            Object value = method.invoke(object);
                            map.put(key,value);
                        }
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return map;
    }

    /**
     * 将Map 映射到 Bean 中
     * @param map
     * @param t
     * @param <T>
     * @return
     * @throws Exception
     */
    public static <T> T reflectMapToBean(Map map, T t) throws Exception{
        Object object = null;
        Class myClazz = t.getClass();
        object = myClazz.newInstance();
        for(Class<?> clazz = t.getClass(); clazz != null && clazz != Object.class; clazz = clazz.getSuperclass()){
            if(clazz != null){
                Method[] declaredMethods = clazz.getDeclaredMethods();
                for(Method method : declaredMethods){
                    if(method.getName().startsWith("set")){
                        String keyName = method.getName().replaceFirst("set","");
                        String key = keyName.substring(0,1).toLowerCase()+keyName.substring(1);
                        Object value = map.get(key);
                        //value如果是空,则跳过不处理
                        if(value == null) continue;
                        Class<?>[] parameterTypes = method.getParameterTypes();
                        //将Object类型的值，转换成bean对象 属性里对应的类型值
                        object = convertValType(value,object,method,parameterTypes[0]);
                    }
                }
            }
        }
        return (T)object;
    }

    /**
     * 将Object类型的值，转换成bean对象属性里对应的类型值
     * @param value map中相应的get的值
     * @param object T
     * @param method T的set方法
     * @param parameterType T set方法类型
     * @return
     * @throws Exception
     */
    private static Object convertValType(Object value, Object object, Method method, Class<?> parameterType) throws Exception{
        if(String.class == parameterType) {
            if(!StringUtil.isNullOrEmpty(value)){
                method.invoke(object,String.valueOf(value).trim());
            }
        } else if(BigDecimal.class == parameterType) {
            if(!StringUtil.isNullOrEmpty(value)){
                String temp = (String) value;
                if(!"".equals(temp.trim())){
                    method.invoke(object,new BigDecimal(temp));
                }
            }
        } else if(Date.class == parameterType) {
            if(!StringUtil.isNullOrEmpty(value)){
                if(value instanceof String){
                    String temp = (String) value;
                    if(!"".equals(temp.trim())){
                        method.invoke(object, DateUtil.StringToDate(temp,"yyyy/MM/dd HH:mm:ss"));
                    }
                }else if(value instanceof Date){
                    method.invoke(object, DateUtil.StringToDate(DateUtil.DateToString((Date)value, "yyyy/MM/dd HH:mm:ss"),"yyyy/MM/dd HH:mm:ss"));
                }
            }
        } else if(Short.class == parameterType) {
            if(!StringUtil.isNullOrEmpty(value)){
                method.invoke(object,Short.valueOf(value.toString()));
            }
        }else if(Double.class == parameterType || double.class == parameterType) {
            if(!StringUtil.isNullOrEmpty(value)){
                method.invoke(object,Double.valueOf(value.toString()));
            }
        }else if(Integer.class == parameterType || int.class == parameterType) {
            if(!StringUtil.isNullOrEmpty(value)){
                method.invoke(object,Integer.valueOf(value.toString()));
            }
        }else if(Long.class == parameterType || long.class == parameterType) {
            if(!StringUtil.isNullOrEmpty(value)){
                method.invoke(object,Long.valueOf(value.toString()));
            }
        }
        return object;
    }
}
