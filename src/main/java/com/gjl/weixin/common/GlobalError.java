package com.gjl.weixin.common;

import org.springframework.stereotype.Controller;

@Controller
public class GlobalError {
    /** 产品系列下已存在产品,不允许修改投资锁定期属性*/
    public static final String ERROR_HASISSUE_LOCKCHANGEFORBID = "error.hasissue.lockchangeforbid";
    /** 系统参数只能为1 或 0*/
    public static final String ERROR_SYSPARAM_VALUE = "参数值不合法只能为1 或 0";
    /** 系统中没有此参数*/
    public static final String ERROR_SYS_PARAM = "系统中没有此参数";
}
