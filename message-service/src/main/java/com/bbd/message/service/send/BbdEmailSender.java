/*
 * Copyright (c) 2016.
 * BrandBigData.com Inc
 */

package com.bbd.message.service.send;


import com.bbd.message.exception.MessageException;
import com.bbd.message.service.model.BtEmailModel;
import com.bbd.message.service.model.BtEmailRequestModel;
import com.google.common.base.Joiner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.MessageFormat;
import java.util.List;

/* 
 * 邮件发送
 * 
 * @author zhao yuan
 * @version $Id: BbdEmailSender.java, v 0.1 2016/8/4 zhao yuan Exp $
 */
@Service
public class BbdEmailSender {

    /** 日志 */
    private final Logger       logger                        = LoggerFactory
                                                                 .getLogger(BbdEmailSender.class);

    /** 邮件地址分隔符 */
    public static final String EMAIL_ADDRESS_SPLIT_SEPARATOR = ",";

    /** 默认发件人 */
    @Value("${mail.sender.username}")
    private String             defaultSender;

    /** 邮件渲染器 */
    @Autowired
    private EmailRender        emailRender;

    /** 实际执行发送邮件的工具 */
    @Autowired
    private JavaMailSender     mailSender;




    public void setDefaultSender(String defaultSender) {
        this.defaultSender = defaultSender;
    }

    public boolean doSendHtmlEmail(BtEmailRequestModel btEmailRequestModel) {
        try {
            BtEmailModel btEmailModel = btEmailRequestModel.getBtEmailModel();
            MimeMessage message = mailSender.createMimeMessage();
            message.setSubject(btEmailModel.getSubject());

            // 发件人
            message.setFrom(new InternetAddress(defaultSender));

            //收件人
            addRecipients(message, Message.RecipientType.TO, btEmailModel.getTo());

            //抄送
            addRecipients(message, Message.RecipientType.CC, btEmailModel.getCc());

            //密送
            addRecipients(message, Message.RecipientType.BCC, btEmailModel.getBcc());

            Multipart multipart = buildEmailContent(btEmailRequestModel);

            message.setContent(multipart);
            // 保存邮件
            //message.saveChanges();
            mailSender.send(message);

            logger.info("send email success:{}", btEmailRequestModel);
        } catch (AddressException e) {
            String errorMsg = MessageFormat.format("发件人地址解析出错：{0}", defaultSender);
            logger.error(errorMsg, e);
            throw new MessageException(errorMsg, e);
        } catch (MessagingException e) {
            String errorMsg = MessageFormat.format("创建邮件内容出错：{0}", btEmailRequestModel);
            logger.error(errorMsg, e);
            throw new MessageException(errorMsg, e);
        } catch (IOException e) {
            String errorMsg = MessageFormat.format("处理附件错误：{0}", btEmailRequestModel);
            logger.error(errorMsg, e);
            throw new MessageException(errorMsg, e);
        }
        return true;
    }

    /**
     * 渲染邮件内容,构造邮件content
     * 
     * @param btEmailRequestModel
     * @return
     * @throws MessagingException
     * @throws UnsupportedEncodingException
     */
    private Multipart buildEmailContent(BtEmailRequestModel btEmailRequestModel)
                                                                                throws MessagingException,
                                                                                IOException {
        // 向multipart对象中添加邮件的各个部分内容，包括文本内容和附件
        Multipart multipart = new MimeMultipart();

        // 添加邮件正文
        BodyPart contentPart = new MimeBodyPart();
        String emailText = emailRender.render(btEmailRequestModel);
        contentPart.setContent(emailText, "text/html;charset=UTF-8");
        multipart.addBodyPart(contentPart);

        return multipart;
    }



    /**
     * 向邮件中添加收件人
     * 
     * @param message 邮件主体
     * @param type 收件人类型
     * @param urls 收件人地址列表
     * @throws MessagingException
     */
    private void addRecipients(MimeMessage message, Message.RecipientType type, List<String> urls)
                                                                                                  throws MessagingException {
        if (urls == null || urls.isEmpty())
            return;
        String addresses = Joiner.on(EMAIL_ADDRESS_SPLIT_SEPARATOR).skipNulls().join(urls);
        message.addRecipients(type, addresses);
    }

}
