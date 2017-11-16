package com.bbd.message.job;

import com.bbd.message.service.ConsumerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author fisher
 * @version $Id: SendEmailJob.java, v 0.1 2017/11/13 16:09 fisher Exp $
 */
@Service
public class SendEmailJob {
    /** 日志 */
    private static Logger logger = LoggerFactory.getLogger(SendEmailJob.class);

    /** 邮件请求消费端服务 */
    @Resource
    private ConsumerService emailConsumerService;

    /**
     * 发送邮件任务
     */
    public void doSendJod() {
        logger.debug("SendEmailJob start...");
        try {
            emailConsumerService.consume();
            logger.info("SendEmailJob success...");
        } catch (Exception e) {
            logger.error("SendEmailJob failed.", e);
        }
    }
}
