package com.bbd.message.job;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bbd.message.service.MessageService;
import com.bbd.message.service.model.EmailRequestVO;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.listener.MessageListener;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author fisher
 * @version $Id: MessageListenerJob.java, v 0.1 2017/11/15 17:48 fisher Exp $
 */
@Service
public class MessageListenerJob implements MessageListener<String,String> {

    private static final Logger logger = LoggerFactory.getLogger(MessageListenerJob.class);

    @Autowired
    private MessageService messageService;

    @Override
    public void onMessage(ConsumerRecord<String, String> data) {

        String message =  data.value();
        System.out.println("消息： " + message);

        EmailRequestVO vo = new EmailRequestVO();
        vo.setSubject("fisher 测试");
        List to = new ArrayList();
        to.add("570366997@qq.com");
        to.add("18782020177@163.com");
        vo.setTo(to);
        vo.setCc(null);
        vo.setBcc(null);
        vo.setModel("{\"username\":\"fisher_111\",\"level_one\":\"100\",\"level_two\":\"200\",\"level_three\":\"300\",\"score\":\"100\",\"link\":\"www.baidu.com\"}");
        vo.setTemplate("37");

        String jj = JSONObject.toJSONString(vo);
        System.out.print(jj);
        EmailRequestVO requestVO= JSON.parseObject(jj,EmailRequestVO.class);
        messageService.sendEmail(requestVO);
    }
}
