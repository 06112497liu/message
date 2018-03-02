package com.bbd.message.service.impl;

import com.bbd.message.facade.result.SmsResult;
import com.bbd.message.service.BtSmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author fisher
 * @version $Id: BtSmsServiceImpl.java, v 0.1 2017/11/16 16:39 fisher Exp $
 */
@Service
public class BtSmsServiceImpl implements BtSmsService {

    @Autowired
    private SmsServiceImpl smsServiceImpl;

    @Override
    public SmsResult send(String target, String params, String smsTemplateCode) {
        String state = smsServiceImpl.doSend(target, params, smsTemplateCode);
        return new SmsResult(state);
    }
}
