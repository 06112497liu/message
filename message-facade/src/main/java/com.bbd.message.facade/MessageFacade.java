package com.bbd.message.facade;

import com.bbd.message.facade.mode.EmailRequestVO;
import com.bbd.message.facade.result.MessageResult;
import com.bbd.message.facade.result.SendEmailResult;
import com.bbd.message.facade.result.SmsResult;

/**
 * @author fisher
 * @version $Id: MessageFacade.java, v 0.1 01/03/2018 10:59 fisher Exp $
 */
public interface MessageFacade {

    MessageResult<SendEmailResult> sendEmail(EmailRequestVO request);

    MessageResult<SmsResult> sendSMS(String target , String params , String smsTemplateCode);
}
