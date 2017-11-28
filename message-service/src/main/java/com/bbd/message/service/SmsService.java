package com.bbd.message.service;


import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;

/**
 * @author fisher
 * @version $Id: SmsService.java, v 0.1 2017/11/17 17:16 fisher Exp $
 */
public interface SmsService {

    public SendSmsRequest getSmsRequest(String mobiles, String smsParam, String smsTemplateCode);

    public  String sendApi(IAcsClient acsClient, SendSmsRequest request);

    public  String doSend(String mobiles, String smsParam, String smsTemplateCode);
}
