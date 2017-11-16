package com.bbd.message.service.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author fisher
 * @version $Id: EmailRequestVO.java, v 0.1 2017/11/7 10:34 fisher Exp $
 */
public class EmailRequestVO implements Serializable {

    /** 主题 */
    private String              subject;

    /** 收件人 */
    private List<String>        to;

    /** 抄送 */
    private List<String>        cc;

    /** 密送 */
    private List<String>        bcc;

    /** 数据 */
    private String              model;

    /** 模板 */
    private String              template;

    /** 重试次数 */
    private int                 retry;

    /** 错误信息 */
    private String              failMessage;

    /** 邮件请求的处理状态 */
    private String              btEmailRequestStatus;

    /** 创建时间 */
    private Date gmtCreate;

    /** 更新时间 */
    private Date                gmtModified;

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public List<String> getTo() {
        return to;
    }

    public void setTo(List<String> to) {
        this.to = to;
    }

    public List<String> getCc() {
        return cc;
    }

    public void setCc(List<String> cc) {
        this.cc = cc;
    }

    public List<String> getBcc() {
        return bcc;
    }

    public void setBcc(List<String> bcc) {
        this.bcc = bcc;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public int getRetry() {
        return retry;
    }

    public void setRetry(int retry) {
        this.retry = retry;
    }

    public String getFailMessage() {
        return failMessage;
    }

    public void setFailMessage(String failMessage) {
        this.failMessage = failMessage;
    }

    public String getBtEmailRequestStatus() {
        return btEmailRequestStatus;
    }

    public void setBtEmailRequestStatus(String btEmailRequestStatus) {
        this.btEmailRequestStatus = btEmailRequestStatus;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }
}
