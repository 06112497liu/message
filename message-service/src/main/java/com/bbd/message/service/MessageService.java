package com.bbd.message.service;

import com.bbd.message.service.model.EmailRequestVO;
import com.bbd.message.service.result.MessageResult;
import com.bbd.message.service.result.SendEmailResult;
import com.bbd.message.service.result.SmsResult;

/**
 * @author fisher
 * @version $Id: MessageService.java, v 0.1 2017/11/7 10:08 fisher Exp $
 */
public interface MessageService {

    MessageResult<SendEmailResult> sendEmail(EmailRequestVO request);

    MessageResult<SmsResult> sendSMS(String target , String params , String smsTemplateCode);
}

