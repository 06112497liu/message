package com.bbd.message.service;

import com.bbd.message.service.result.MessageResult;
import com.bbd.message.service.result.SmsResult;

/**
 * @author fisher
 * @version $Id: BtSmsService.java, v 0.1 2017/11/16 16:39 fisher Exp $
 */
public interface BtSmsService {

    SmsResult send(String target, String params, String smsTemplateCode);
}
