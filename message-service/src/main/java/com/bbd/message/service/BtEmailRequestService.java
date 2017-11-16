package com.bbd.message.service;

import com.bbd.message.service.model.BtEmailRequestModel;

/**
 * @author fisher
 * @version $Id: BtEmailRequestService.java, v 0.1 2017/11/7 16:36 fisher Exp $
 */
public interface BtEmailRequestService {

    /**
     * 新增邮件请求
     *
     * @param btEmailRequestModel       邮件请求模型
     */
    void addBtEmailRequest(BtEmailRequestModel btEmailRequestModel);

    /**
     * 从数据库消费一条request请求
     * 消费的请求会把状态设置为Running
     *
     * @param maxRetry          最大重试次数
     * @return
     */
    BtEmailRequestModel consumeRequest(int maxRetry);

    /**
     * 获取一个待发送的邮件请求
     *
     * @param maxRetry          最大重试次数
     * @return
     */
    BtEmailRequestModel findRequestToSend(int maxRetry);

    void afterSend(BtEmailRequestModel requestToSend);
}
