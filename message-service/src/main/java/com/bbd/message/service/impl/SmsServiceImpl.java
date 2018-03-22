/**
 * BBD Service Inc
 * AllRight Reserved @2017
 */
package com.bbd.message.service.impl;


import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.bbd.message.service.SmsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 短信
 * 
 * @author Administrator
 * @version $Id: SmsUtil.java, v 0.1 2016年12月2日 下午2:30:51 Administrator Exp $
 */
@Component
public class SmsServiceImpl implements SmsService {

    private static Logger logger = LoggerFactory.getLogger(SmsServiceImpl.class);

    @Value("${alidayu.domain}")
    private  String       domain;

    @Value("${alidayu.keyid}")
    private  String       keyid;

    @Value("${alidayu.secret}")
    private  String       alidayuSecret;

    @Value("${sms.type}")
    private  String        smsType;

    @Value("${sms.sign}")
    private  String        smsSign;

    final String product = "Dysmsapi";

    /**
     * 获取客户端
     * 
     * @return
     */
    public  IAcsClient getSmsClient() {
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", keyid, alidayuSecret);
        try {
            DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
        } catch (ClientException e) {
            logger.error("",e);
        }
        IAcsClient acsClient = new DefaultAcsClient(profile);
        return acsClient;
    }

    /**
     * 获取请求
     * 
     * @param mobiles
     * @param smsParam
     * @param smsTemplateCode
     * @return
     */
    public SendSmsRequest getSmsRequest(String mobiles, String smsParam, String smsTemplateCode) {
        SendSmsRequest request = new SendSmsRequest();
        request.setMethod(MethodType.POST);
        request.setPhoneNumbers(mobiles);
        //必填:短信签名-可在短信控制台中找到
        request.setSignName("贵阳市工商局");
        //必填:短信模板-可在短信控制台中找到
        request.setTemplateCode(smsTemplateCode);
        //可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
        //友情提示:如果JSON中需要带换行符,请参照标准的JSON协议对换行符的要求,比如短信内容中包含\r\n的情况在JSON中需要表示成\\r\\n,否则会导致JSON在服务端解析失败
        request.setTemplateParam(smsParam);
        //可选-上行短信扩展码(扩展码字段控制在7位或以下，无特殊需求用户请忽略此字段)
        //request.setSmsUpExtendCode("90997");
        //可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
        //request.setOutId("yourOutId");
        return request;
    }



    /**
     * 发送
     * 
     * @param acsClient
     * @param request
     * @return
     */
    public  String sendApi(IAcsClient acsClient, SendSmsRequest request) {
        SendSmsResponse sendSmsResponse = null;
        try {
            sendSmsResponse = acsClient.getAcsResponse(request);
            logger.info(sendSmsResponse.getCode());
            if (sendSmsResponse.getCode()!=null && sendSmsResponse.getCode().equals("OK")) {
                return "1";
            } else {
                return sendSmsResponse.getMessage();
            }
        } catch (ClientException e) {
            logger.error("",e);
            return null;
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
        IAcsClient client = this.getSmsClient();
        SendSmsRequest request = getSmsRequest(mobiles, smsParam, smsTemplateCode);
        return sendApi(client, request);
    }


}
