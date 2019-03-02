package com.gjl.weixin.service.impl;

import com.gjl.weixin.controller.AccessTokenThread;
import com.gjl.weixin.entity.Article;
import com.gjl.weixin.entity.NewsMessage;
import com.gjl.weixin.entity.TextMessage;
import com.gjl.weixin.service.WeiXinService;
import com.gjl.weixin.utils.HttpUtil;
import com.gjl.weixin.utils.MsgUtil;
import com.gjl.weixin.utils.WeiXinUtil;
import com.gjl.weixin.utils.WeixinMessageUtil;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Service
public class WeixinServiceImpl implements WeiXinService {

    private static Logger logger = Logger.getLogger(WeixinServiceImpl.class);
    /**
     * 添加自定义菜单
     */
    @Override
    public boolean menuAdd(){


        //String url = WeiXinUtil.ADD_MENU_URL.replace("ACCESS_TOKEN", WeiXinUtil.getAccessToken());
        String url = WeiXinUtil.ADD_MENU_URL.replace("ACCESS_TOKEN", AccessTokenThread.accessToken.getAccess_token());
        String menu = JSONObject.fromObject(MsgUtil.initMenu()).toString();
        JSONObject result = HttpUtil.doPoststr(url,menu);
        if("ok".equals(result.getString("errmsg"))){
            //logger.info("添加菜单结果：{}", result);
            System.out.println("true添加菜单结果：{}"+result);
            return true;
        }
        //logger.info("添加菜单结果：{}", result);
        System.out.println("false添加菜单结果：{}"+result);
        return false;
    }

    @Override
    public String processRequest(HttpServletRequest request) {
        String respMessage = null;
        try{
            // 默认返回的文本消息内容
            String respContent = "请求处理异常，请稍候尝试！";
            // xml请求解析
            Map<String, String> requestMap = WeixinMessageUtil.parseXml(request);
            // 发送方帐号（open_id）
            String fromUserName = requestMap.get("FromUserName");
            // 公众帐号
            String toUserName = requestMap.get("ToUserName");
            // 消息类型
            String msgType = requestMap.get("MsgType");
            // 回复文本消息
            TextMessage textMessage = new TextMessage();
            textMessage.setToUserName(fromUserName);
            textMessage.setFromUserName(toUserName);
            textMessage.setCreateTime(new Date().getTime());
            textMessage.setMsgType(WeixinMessageUtil.RESP_MESSAGE_TYPE_TEXT);
            textMessage.setFuncFlag(0);
            // 创建图文消息
            NewsMessage newsMessage = new NewsMessage();
            newsMessage.setToUserName(fromUserName);
            newsMessage.setFromUserName(toUserName);
            newsMessage.setCreateTime(new Date().getTime());
            newsMessage.setMsgType(WeixinMessageUtil.RESP_MESSAGE_TYPE_NEWS);
            newsMessage.setFuncFlag(0);
            List<Article> articleList = new ArrayList<Article>();
            // 接收文本消息内容
            String content = requestMap.get("Content");
            // 自动回复文本消息
           if(msgType.equals(WeixinMessageUtil.REQ_MESSAGE_TYPE_TEXT)) {
                //如果用户发送表情，则回复同样表情。
               if(isQqFace(content)){
                   respContent = content;
                   textMessage.setContent(respContent);
                   respMessage = WeixinMessageUtil.textMessageToXml(textMessage);
               }
               else{
                   switch (content){
                       case "1":{
                           StringBuffer buffer = new StringBuffer();
                           buffer.append("您好，我是小8，请回复数字选择服务：").append("\n\n");
                           buffer.append("11 可查看测试单图文").append("\n");
                           buffer.append("12  可测试多图文发送").append("\n");
                           buffer.append("13  可测试网址").append("\n");
                           buffer.append("或者您可以尝试发送表情").append("\n\n");
                           buffer.append("回复“1”显示此帮助菜单").append("\n");
                           respContent = String.valueOf(buffer);
                           textMessage.setContent(respContent);
                           respMessage = WeixinMessageUtil.textMessageToXml(textMessage);
                           break;
                       }
                       case "11":{
                           Article article = new Article();
                           article.setTitle("微信公众帐号开发教程Java版");
                           article.setDescription("这是测试有没有换行\n\n如果有空行就代表换行成功\n\n点击图文可以跳转到百度首页");
                           // 将图片置为空
                           article.setPicUrl("http://www.sinaimg.cn/dy/slidenews/31_img/2016_38/28380_733695_698372.jpg");
                           article.setUrl("http://www.baidu.com");
                           articleList.add(article);
                           newsMessage.setArticleCount(articleList.size());
                           newsMessage.setArticles(articleList);
                           respMessage = WeixinMessageUtil.newsMessageToXml(newsMessage);
                           break;
                       }
                       case "12":{
                           //多图文发送
                           /*Article article1 = new Article();
                           article1.setTitle("紧急通知，不要捡这种钱！湛江都已经传疯了！\n");
                           article1.setDescription("");
                           article1.setPicUrl("http://www.sinaimg.cn/dy/slidenews/31_img/2016_38/28380_733695_698372.jpg");
                           article1.setUrl("http://mp.weixin.qq.com/s?__biz=MjM5Njc2OTI4NQ==&mid=2650924309&idx=1&sn=8bb6ae54d6396c1faa9182a96f30b225&chksm=bd117e7f8a66f769dc886d38ca2d4e4e675c55e6a5e01e768b383f5859e09384e485da7bed98&scene=4#wechat_redirect");
                           */
                           Article article2 = new Article();
                           article2.setTitle("dfzrtsfgjr");
                           article2.setDescription("");
                           article2.setPicUrl("http://www.sinaimg.cn/dy/slidenews/31_img/2016_38/28380_733695_698372.jpg");
                           article2.setUrl("http://mp.weixin.qq.com/s?__biz=MjM5Njc2OTI4NQ==&mid=2650924309&idx=2&sn=d7ffc840c7e6d91b0a1c886b16797ee9&chksm=bd117e7f8a66f7698d094c2771a1114853b97dab9c172897c3f9f982eacb6619fba5e6675ea3&scene=4#wechat_redirect");
                           Article article3 = new Article();
                           article3.setTitle("湛江谁有这种女儿，请给我来一打！");
                           article3.setDescription("");
                           article3.setPicUrl("http://www.sinaimg.cn/dy/slidenews/31_img/2016_38/28380_733695_698372.jpg");
                           article3.setUrl("http://baidu.com");

                           //articleList.add(article1);
                           //articleList.add(article2);
                           articleList.add(article3);
                           int a=articleList.size();
                           newsMessage.setArticleCount(articleList.size());
                           newsMessage.setArticles(articleList);
                           System.out.println(newsMessage);
                           respMessage = WeixinMessageUtil.newsMessageToXml(newsMessage);
                           break;
                       }
                       case "13": {
                           //测试网址回复
                           respContent = "<a href=\"http://www.baidu.com\">百度主页</a>";
                           textMessage.setContent(respContent);
                           // 将文本消息对象转换成xml字符串
                           respMessage = WeixinMessageUtil.textMessageToXml(textMessage);
                           break;
                       } default: {
                           respContent = "（这是里面的）很抱歉，现在小8暂时无法提供此功能给您使用。\n\n回复“1”显示帮助信息";
                           textMessage.setContent(respContent);
                           // 将文本消息对象转换成xml字符串
                           respMessage = WeixinMessageUtil.textMessageToXml(textMessage);
                       }
                   }
               }
           }else if (msgType.equals(WeixinMessageUtil.REQ_MESSAGE_TYPE_IMAGE)) {
               respContent = "您发送的是图片消息！";
               textMessage.setContent(respContent);
               // 将文本消息对象转换成xml字符串
               respMessage = WeixinMessageUtil.textMessageToXml(textMessage);
           }
           // 地理位置消息
            else if (msgType.equals(WeixinMessageUtil.REQ_MESSAGE_TYPE_LOCATION)) {
                respContent = "您发送的是地理位置消息！";
                textMessage.setContent(respContent);
                // 将文本消息对象转换成xml字符串
                respMessage = WeixinMessageUtil.textMessageToXml(textMessage);
            }
           // 链接消息
           else if (msgType.equals(WeixinMessageUtil.REQ_MESSAGE_TYPE_LINK)) {
               respContent = "您发送的是链接消息！";textMessage.setContent(respContent);
               // 将文本消息对象转换成xml字符串
               respMessage = WeixinMessageUtil.textMessageToXml(textMessage);
           }
           // 音频消息
           else if (msgType.equals(WeixinMessageUtil.REQ_MESSAGE_TYPE_VOICE)) {
               respContent = "您发送的是音频消息！";
               textMessage.setContent(respContent);
               // 将文本消息对象转换成xml字符串
               respMessage = WeixinMessageUtil.textMessageToXml(textMessage);
           }
        }catch (Exception e){
            e.printStackTrace();
        }
        return respMessage;
    }

    public static boolean isQqFace(String content){
        boolean result = false;
        // 判断QQ表情的正则表达式
        String qqfaceRegex = "/::\\)|/::~|/::B|/::\\||/:8-\\)|/::<|/::$|/::X|/::Z|/::'\\(|/::-\\||/::@|/::P|/::D|/::O|/::\\(|/::\\+|/:--b|/::Q|/::T|/:,@P|/:,@-D|/::d|/:,@o|/::g|/:\\|-\\)|/::!|/::L|/::>|/::,@|/:,@f|/::-S|/:\\?|/:,@x|/:,@@|/::8|/:,@!|/:!!!|/:xx|/:bye|/:wipe|/:dig|/:handclap|/:&-\\(|/:B-\\)|/:<@|/:@>|/::-O|/:>-\\||/:P-\\(|/::'\\||/:X-\\)|/::\\*|/:@x|/:8\\*|/:pd|/:<W>|/:beer|/:basketb|/:oo|/:coffee|/:eat|/:pig|/:rose|/:fade|/:showlove|/:heart|/:break|/:cake|/:li|/:bome|/:kn|/:footb|/:ladybug|/:shit|/:moon|/:sun|/:gift|/:hug|/:strong|/:weak|/:share|/:v|/:@\\)|/:jj|/:@@|/:bad|/:lvu|/:no|/:ok|/:love|/:<L>|/:jump|/:shake|/:<O>|/:circle|/:kotow|/:turn|/:skip|/:oY|/:#-0|/:hiphot|/:kiss|/:<&|/:&>";
        Pattern p = Pattern.compile(qqfaceRegex);
        Matcher m = p.matcher(content);
        if (m.matches()) {
            result = true;
        }
        return result;
    }
}
