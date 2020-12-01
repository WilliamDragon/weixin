package com.gjl.weixin.utils;

import java.util.List;

public class listUtil {

    public static Boolean isData(List list){
        Boolean isSuccessDao =false;
        if(list != null){
            if(list.size()>=1){
                isSuccessDao = true;
            }
            isSuccessDao =false;
        }
        isSuccessDao =false;
        return isSuccessDao;
    }
}
