package com.bbd.message.service.impl;

import com.bbd.message.enums.BtEmailRequestStatusEnum;
import com.bbd.message.service.BtEmailRequestService;
import com.bbd.message.service.ConsumerService;
import com.bbd.message.service.model.BtEmailRequestModel;
import com.bbd.message.service.send.BbdEmailSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.MessageFormat;

/**
 * @author fisher
 * @version $Id: ConsumerServiceImpl.java, v 0.1 2017/11/10 16:26 fisher Exp $
 */
@Service
public class ConsumerServiceImpl implements ConsumerService {


    /** 最大重试次数 */
    private final int             maxRetry = 3;

    @Resource
    private BbdEmailSender emailSender;

    @Autowired
    private BtEmailRequestService btEmailRequestService;

    private Logger logger = LoggerFactory.getLogger(ConsumerServiceImpl.class);

    public void consume() {
        //获取一条待发送的邮件请求
        BtEmailRequestModel requestToSend = btEmailRequestService.consumeRequest(maxRetry);
        if (requestToSend == null)
            return;
        logger.debug("To send email:{}", requestToSend);

        try {
            emailSender.doSendHtmlEmail(requestToSend);
            requestToSend.setBtEmailRequestStatus(BtEmailRequestStatusEnum.SUCCESS);
            logger.debug("Send email success :{}", requestToSend);
        } catch (Exception e) {
            logger.error(MessageFormat.format("第{0,number}次发送邮件失败。邮件详情：{1}",
                    requestToSend.getRetry() + 1, requestToSend), e);
            requestToSend.setBtEmailRequestStatus(BtEmailRequestStatusEnum.FAILED);
            requestToSend.setFailMessage(e.getMessage());
        }

        try {
            //发完完成后，记录发送的状态
            btEmailRequestService.afterSend(requestToSend);
            logger.debug("Update email request db success :{}", requestToSend);
        } catch (Exception e) {
            logger.error(
                    MessageFormat.format("Update email request db success : {} ", requestToSend), e);
        }
    }
}
