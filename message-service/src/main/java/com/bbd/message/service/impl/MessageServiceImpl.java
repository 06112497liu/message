package com.bbd.message.service.impl;

import com.bbd.message.enums.BtEmailRequestStatusEnum;
import com.bbd.message.facade.MessageFacade;
import com.bbd.message.facade.mode.EmailRequestVO;
import com.bbd.message.facade.result.MessageResult;
import com.bbd.message.facade.result.SendEmailResult;
import com.bbd.message.facade.result.SmsResult;
import com.bbd.message.service.BtEmailRequestService;
import com.bbd.message.service.BtSmsService;
import com.bbd.message.service.converter.BtEmailRequestConverter;
import com.bbd.message.service.model.BtEmailRequestModel;
import com.bbd.message.service.send.BbdEmailSender;
import com.bbd.message.utils.Helpers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.MessageFormat;

/**
 * @author fisher
 * @version $Id: MessageServiceImpl.java, v 0.1 2017/11/7 10:48 fisher Exp $
 */
@Service("messageFacade")
public class MessageServiceImpl implements MessageFacade {

    private Logger logger = LoggerFactory.getLogger(MessageServiceImpl.class);

    /** 邮件请求服务 */
    @Resource
    private BtEmailRequestService btEmailRequestService;

    @Resource
    private BtSmsService btSmsService;

    @Resource
    private BbdEmailSender emailSender;

    /**
     * 同步发送邮件
     * @param request
     * @return
     */
    public MessageResult<SendEmailResult> sendEmail(EmailRequestVO request) {
        MessageResult result = new MessageResult();
        Helpers.assertNotNull(request);
        Helpers.assertNotNullOrEmpty(request.getTo());
        BtEmailRequestModel requestToSend = BtEmailRequestConverter.convertFromVO(request);
        logger.debug("To send email:{}", requestToSend);

        try {
            emailSender.doSendHtmlEmail(requestToSend);
            result.setSuccess(true);
            result.setData(BtEmailRequestStatusEnum.SUCCESS);
            logger.debug("Send email success :{}", requestToSend);
        } catch (Exception e) {
            logger.error(MessageFormat.format("第{0,number}次发送邮件失败。邮件详情：{1}",
                    requestToSend.getRetry() + 1, requestToSend), e);
            result.setSuccess(false);
            result.setData(BtEmailRequestStatusEnum.FAILED);
            requestToSend.setFailMessage(e.getMessage());
        }
        return result;
    }

    /**
     * 异步
     * @param request
     * @return
     */
    public MessageResult<SendEmailResult> sendEmailAsync(EmailRequestVO request) {
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
