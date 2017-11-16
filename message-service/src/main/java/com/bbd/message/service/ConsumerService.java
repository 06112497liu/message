package com.bbd.message.service;

/**
 * @author fisher
 * @version $Id: ConsumerService.java, v 0.1 2017/11/10 16:26 fisher Exp $
 */
public interface ConsumerService {
    /**
     * 消费一个请求
     *
     * @return
     */
    void consume();
}
