/**
 * BBD Service Inc
 * AllRight Reserved @2017
 */
package com.bbd.message.service.impl;


import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.AlibabaAliqinFcSmsNumSendRequest;
import com.taobao.api.response.AlibabaAliqinFcSmsNumSendResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * 短信
 * 
 * @author Administrator
 * @version $Id: SmsUtil.java, v 0.1 2016年12月2日 下午2:30:51 Administrator Exp $
 */
@Component
public class SmsServiceImpl {

    private static Logger logger = LoggerFactory.getLogger(SmsServiceImpl.class);

    @Value("${alidayu.url}")
    private  String       alidayuUrl;

    @Value("${alidayu.appkey}")
    private  String       alidayuAppKey;

    @Value("${alidayu.secret}")
    private  String       alidayuSecret;

    @Value("${sms.type}")
    private  String        smsType;

    @Value("${sms.sign}")
    private  String        smsSign;

    /**
     * 获取客户端
     * 
     * @return
     */
    public  TaobaoClient getSmsClient() {
        TaobaoClient client = new DefaultTaobaoClient(alidayuUrl, alidayuAppKey, alidayuSecret);
        return client;
    }

    /**
     * 获取请求
     * 
     * @param mobiles
     * @param smsParam
     * @param smsTemplateCode
     * @return
     */
    public  AlibabaAliqinFcSmsNumSendRequest getSmsRequest(String mobiles, String smsParam, String smsTemplateCode) {
        AlibabaAliqinFcSmsNumSendRequest request = new AlibabaAliqinFcSmsNumSendRequest();
        // 设置公共回传参数
        // req.setExtend("13547899969");
        // 设置短信类型，传入值请填写normal
        request.setSmsType(smsType);
        // 设置短信签名
        request.setSmsFreeSignName(smsSign);
        // 设置短信接收号码。支持单个或多个手机号码，传入号码为11位手机号码，不能加0或+86。群发短信需传入多个号码，以英文逗号分隔，一次调用最多传入200个号码。
        request.setRecNum(mobiles);
        // 设置短信模板ID，传入的模板必须是在阿里大鱼“管理中心-短信模板管理”中的可用模板。
        request.setSmsTemplateCode(smsTemplateCode);
        // 设置短信模板变量，传参规则{"key":"value"}，key的名字须和申请模板中的变量名一致，多个变量之间以逗号隔开。
        request.setSmsParam(smsParam);
        return request;
    }

    /**
     * 获取请求
     * 
     * @param mobiles
     * @param smsTemplateCode
     * @return
     */
    public  AlibabaAliqinFcSmsNumSendRequest getSmsRequest(String mobiles, String smsTemplateCode) {
        AlibabaAliqinFcSmsNumSendRequest request = new AlibabaAliqinFcSmsNumSendRequest();
        // 设置公共回传参数
        // req.setExtend("13547899969"); 
        // 设置短信类型，传入值请填写normal
        request.setSmsType(smsType);
        // 设置短信签名
        request.setSmsFreeSignName(smsSign);
        // 设置短信接收号码。支持单个或多个手机号码，传入号码为11位手机号码，不能加0或+86。群发短信需传入多个号码，以英文逗号分隔，一次调用最多传入200个号码。
        request.setRecNum(mobiles);
        // 设置短信模板ID，传入的模板必须是在阿里大鱼“管理中心-短信模板管理”中的可用模板。
        request.setSmsTemplateCode(smsTemplateCode);
        return request;
    }

    /**
     * 发送
     * 
     * @param client
     * @param request
     * @return
     */
    public  String sendApi(TaobaoClient client, AlibabaAliqinFcSmsNumSendRequest request) {
        try {
            AlibabaAliqinFcSmsNumSendResponse response = client.execute(request);
            logger.info(response.getBody());
            if (response.isSuccess()) {
                return "1";
            } else {
                return response.getMsg();
            }
        } catch (ApiException e) {
            logger.error(e.getMessage(), e);
            return e.getMessage();
        }

    }


    /**
     * 根据参数发送短信
     * @param mobiles 电话号码
     * @param smsParam 短信模板参数
     * @param smsTemplateCode 模板号
     * @return
     */
    public  String doSend(String mobiles, String smsParam, String smsTemplateCode) {
        TaobaoClient client = this.getSmsClient();
        AlibabaAliqinFcSmsNumSendRequest request = getSmsRequest(mobiles, smsParam, smsTemplateCode);
        return sendApi(client, request);
    }


}
