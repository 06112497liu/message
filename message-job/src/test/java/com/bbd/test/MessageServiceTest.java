package com.bbd.test;

import com.bbd.message.facade.MessageFacade;
import com.bbd.message.facade.mode.EmailRequestVO;
import com.bbd.message.facade.result.MessageResult;
import com.bbd.message.facade.result.SendEmailResult;
import com.bbd.message.service.impl.MessageServiceImpl;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author fisher
 * @version $Id: MessageServiceTest.java, v 0.1 2017/11/10 14:19 fisher Exp $
 */
public class MessageServiceTest extends BaseJobTest {
    @Resource
    private MessageFacade messageService;

    @Test
    public void exeTest(){
        EmailRequestVO request = new EmailRequestVO();
        request.setSubject("test");
        List to = new ArrayList();
        to.add("caoyu@bbdservice.com");
        request.setTo(to);
        request.setCc(null);
        request.setBcc(null);
        MessageResult<SendEmailResult> result =  messageService.sendEmail(request);
        System.out.print("");
    }
}
