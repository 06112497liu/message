package com.bbd.message.service.impl;

import com.bbd.message.service.BtEmailRequestService;
import com.bbd.message.service.BtSmsService;
import com.bbd.message.service.MessageService;
import com.bbd.message.service.converter.BtEmailRequestConverter;
import com.bbd.message.service.model.BtEmailRequestModel;
import com.bbd.message.service.model.EmailRequestVO;
import com.bbd.message.service.result.MessageResult;
import com.bbd.message.service.result.SendEmailResult;
import com.bbd.message.service.result.SmsResult;
import com.bbd.message.utils.Helpers;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author fisher
 * @version $Id: MessageServiceImpl.java, v 0.1 2017/11/7 10:48 fisher Exp $
 */
@Service
public class MessageServiceImpl implements MessageService {

    /** 邮件请求服务 */
    @Resource
    private BtEmailRequestService btEmailRequestService;

    @Resource
    private BtSmsService btSmsService;

    public MessageResult<SendEmailResult> sendEmail(EmailRequestVO request) {
        MessageResult result = new MessageResult();
        Helpers.assertNotNull(request);
        Helpers.assertNotNullOrEmpty(request.getTo());
        SendEmailResult sendEmailResult = new SendEmailResult();
        BtEmailRequestModel btEmailRequestModel = BtEmailRequestConverter
                .convertFromVO(request);
        btEmailRequestService.addBtEmailRequest(btEmailRequestModel);
        sendEmailResult.setEmailRequestId(mixedEmailId(btEmailRequestModel));
        result.setData(sendEmailResult);
        return result;
    }

    @Override
    public MessageResult<SmsResult> sendSMS(String target, String params, String smsTemplateCode) {
        MessageResult result = new MessageResult();
        SmsResult smsResult = btSmsService.send(target,params,smsTemplateCode);
        result.setData(smsResult);
        return result;
    }

    private String mixedEmailId(BtEmailRequestModel btEmailRequestModel) {
        return String.valueOf(btEmailRequestModel.getId());
    }
}
