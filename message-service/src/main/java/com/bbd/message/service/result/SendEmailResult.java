package com.bbd.message.service.result;

import java.io.Serializable;

/**
 * @author fisher
 * @version $Id: SendEmailResult.java, v 0.1 2017/11/7 10:42 fisher Exp $
 */
public class SendEmailResult implements Serializable {

    /** 邮件请求的id，以便client以后根据此id查询邮件发送状态 */
    private String            emailRequestId;

    /** 邮件使用的模板 */
    private String            emailTemplate;

    /** 邮件主题 */
    private String            subject;

    public String getEmailRequestId() {
        return emailRequestId;
    }

    public void setEmailRequestId(String emailRequestId) {
        this.emailRequestId = emailRequestId;
    }

    public String getEmailTemplate() {
        return emailTemplate;
    }

    public void setEmailTemplate(String emailTemplate) {
        this.emailTemplate = emailTemplate;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
