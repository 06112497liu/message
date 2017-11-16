package com.bbd.message.service.impl;

import com.bbd.message.service.BtEmailRequestService;
import com.bbd.message.service.MessageService;
import com.bbd.message.service.converter.BtEmailRequestConverter;
import com.bbd.message.service.model.BtEmailRequestModel;
import com.bbd.message.service.model.EmailRequestVO;
import com.bbd.message.service.result.MessageResult;
import com.bbd.message.service.result.SendEmailResult;
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

    public MessageResult<SendEmailResult> sendEmail(EmailRequestVO request) {
        Helpers.assertNotNull(request);
        Helpers.assertNotNullOrEmpty(request.getTo());
        SendEmailResult sendEmailResult = new SendEmailResult();
        BtEmailRequestModel btEmailRequestModel = BtEmailRequestConverter
                .convertFromVO(request);
        btEmailRequestService.addBtEmailRequest(btEmailRequestModel);
        return null;
    }
}
