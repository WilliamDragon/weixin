package com.gjl.weixin.common.base;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author guojinlong
 * @Date  2021/1/7  15:09
 * 系统报文头定义，其他RequestDto需要继承此类
 */
public class BaseRequestDto extends BaseObject{

    private String channelId;//频道
    private String requestSeq;//外围系统防重流水号
    private String systemTranscationCode;//系统交易码

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public String getRequestSeq() {
        return requestSeq;
    }

    public void setRequestSeq(String requestSeq) {
        this.requestSeq = requestSeq;
    }

    public String getSystemTranscationCode() {
        return systemTranscationCode;
    }

    public void setSystemTranscationCode(String systemTranscationCode) {
        this.systemTranscationCode = systemTranscationCode;
    }
}
