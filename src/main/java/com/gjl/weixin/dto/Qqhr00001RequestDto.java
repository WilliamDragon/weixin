package com.gjl.weixin.dto;

import com.gjl.weixin.common.base.BaseRequestDto;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
/**
 * @author guojinlong
 * @Date  2021/1/7  15:17
 * 系统第一个接口 现只是测试用
 */
public class Qqhr00001RequestDto extends BaseRequestDto implements Serializable {
    private static final long serialVersionUID = -5904063417997768176L;

    private String sysCode;
    private String sysAffect;

    public String getSysCode() {
        return sysCode;
    }

    public void setSysCode(String sysCode) {
        this.sysCode = sysCode;
    }

    public String getSysAffect() {
        return sysAffect;
    }

    public void setSysAffect(String sysAffect) {
        this.sysAffect = sysAffect;
    }
}
