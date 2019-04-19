package com.gjl.weixin.common;

import org.springframework.stereotype.Controller;

@Controller
public class GlobalContext {
    /** 缓存已存在 */
    public static final String CACHE_EXIST = "cache_exist";
    /** 缓存不存在 */
    public static final String CACHE_EXIST_OFF = "1580";
    /**不允许多次提交*/
    public static final String SUBMIT_MORE_OFF = "不允许多次提交";

}
