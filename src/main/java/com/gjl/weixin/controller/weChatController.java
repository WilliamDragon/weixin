package com.gjl.weixin.controller;

import com.gjl.weixin.entity.AccessToken;
import com.gjl.weixin.service.WeiXinService;
import com.gjl.weixin.service.impl.WeixinServiceImpl;
import com.gjl.weixin.utils.HttpUtil;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class weChatController {

    private static Logger logger = Logger.getLogger(weChatController.class);
    @Autowired
    private WeiXinService weiXinService;
    /**
     * 获取access_token
     * access_token_url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
     * @param appid 凭证
     * @param appsecret 密钥
     * @return
     */


    //获取access_token 接口
    public static final String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?"
            + "grant_type=client_credential&appid=APPID&secret=APPSECRET";
    @RequestMapping("/getAccessToken")
    public static String  getAccessToken() {
        AccessToken accessToken = null;
        String appidreal="wx6f6892b83d45974f";
        String appsecretreal="93bd4af5a313e973d246869aa01d4692";
        String appid="wxd1a25bf4be87e6cb";
        String appsecret="98c66119ec6799fb89b0893b72c6271c";
        String requestUrl = ACCESS_TOKEN_URL.replace("APPID", appid).replace("APPSECRET", appsecret);
        JSONObject jsonObject = HttpUtil.doGetstr(requestUrl);
        if (null != jsonObject) {
            try {
                accessToken = new AccessToken();
                accessToken.setAccess_token(jsonObject.getString("access_token"));
                accessToken.setExpires_in(jsonObject.getInt("expires_in"));
            } catch (JSONException e) {
                accessToken = null;
                // 获取token失败
                System.out.println("获取token失败");
                //logger.error("获取token失败 errcode:{} errmsg:{}", jsonObject.getInt("errcode"), jsonObject.getString("errmsg"));
            }
        }
        System.out.println("accessToken="+accessToken.getAccess_token());
        return accessToken.getAccess_token();
    }


    /**
     * 添加微信公众号菜单
     * @return
     */
    @RequestMapping(value="/menuAdd",method= RequestMethod.POST)
    public String menuAdd(){
        boolean b = weiXinService.menuAdd();
        if (b) {
            return "success";
        }
        return "unsuccess";
    }

    //
    /*@RequestMapping(value="/access", method=RequestMethod.POST)
    public String getWeiXinMessage(HttpServletRequest request, HttpServletResponse response)throws Exception{
        logger.info("----------------开始处理微信发过来的消息------------------");
        // 微信服务器POST消息时用的是UTF-8编码，在接收时也要用同样的编码，否则中文会乱码；
        request.setCharacterEncoding("UTF-8");
        // 在响应消息（回复消息给用户）时，也将编码方式设置为UTF-8，原理同上；
        response.setCharacterEncoding("UTF-8");
        String respXml = weixinCoreService.weixinMessageHandelCoreService(request, response);
        if (dataProcess.dataIsNull(respXml)){
            logger.error("-------------处理微信消息失败-----------------------");
            return null;
        }else {
            logger.info("----------返回微信消息处理结果-----------------------:"+respXml);
            return respXml;
        }
    }*/
    @RequestMapping(value = "/weChatConnect",method = RequestMethod.POST)
    public void access(HttpServletRequest req,HttpServletResponse resp)throws IOException {
        String respMessage = weiXinService.processRequest(req);
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(respMessage);
    }
}
