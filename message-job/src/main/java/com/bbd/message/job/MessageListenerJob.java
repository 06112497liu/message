package com.bbd.message.job;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bbd.message.facade.MessageFacade;
import com.bbd.message.facade.mode.EmailRequestVO;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.listener.MessageListener;
import org.springframework.stereotype.Service;

/**
 * @author fisher
 * @version $Id: MessageListenerJob.java, v 0.1 2017/11/15 17:48 fisher Exp $
 */
@Service
public class MessageListenerJob implements MessageListener<String,String> {

    private static final Logger logger = LoggerFactory.getLogger(MessageListenerJob.class);

    @Autowired
    private MessageFacade messageService;


    @Override
    public void onMessage(ConsumerRecord<String, String> data) {

        String message =  data.value();
        System.out.println("消息： " + message);
        JSONObject mes = null;
        try {
             mes = (JSONObject) JSON.parse(message);
        }catch (Exception e){
            logger.error("",e);
            return;
        }
        if("".equals(mes)){
            return;
        }
        String type = mes.getString("type");
        if("email".equals(type)){
            String content = mes.getString("content");
            EmailRequestVO request = JSON.parseObject(content,EmailRequestVO.class);
            messageService.sendEmailAsync(request);
        }else if("sms".equals(type)){
            String content = mes.getString("content");
            JSONObject obj = JSON.parseObject(content);
            messageService.sendSMS(obj.getString("tel"),obj.getString("model"),obj.getString("templateCode"));
        }else{
            return;
        }
    }
}
