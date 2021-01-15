package com.gjl.weixin.utils;

import java.util.Collection;
import java.util.Map;

public class StringUtil {

    public static boolean isNullOrEmpty(Object object){
        if(null == object){
            return true;
        }
        if(object instanceof CharSequence){
            return ((CharSequence)object).length() == 0;
        }
        if(object instanceof Collection){
            return ((Collection) object).isEmpty();
        }
        if(object instanceof Map){
            return ((Map)object).isEmpty();
        }if(object instanceof Object[]){
            Object[] obj = (Object[])object;
            boolean empty = true;
            for(Object ot: obj){
                if(!isNullOrEmpty(ot)){
                    empty = false;
                    break;
                }
            }
            return empty;
        }
        return false;
    }
}
