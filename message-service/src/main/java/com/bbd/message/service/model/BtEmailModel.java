package com.bbd.message.service.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author fisher
 * @version $Id: BtEmailModel.java, v 0.1 2017/11/7 15:48 fisher Exp $
 */
public class BtEmailModel  implements Serializable{
    /** 序列号id */
    private static final long  serialVersionUID = -2871854078237072485L;

    /** 自增主键 */
    private Integer            id;

    /** 摘要，用来验证邮件重复性 */
    private String             digest;

    /** 主题 */
    private String             subject;

    /** 收件人 */
    private List<String> to;

    /** 抄送 */
    private List<String>       cc;

    /** 密送 */
    private List<String>       bcc;

    /** 数据 */
    private String             model;

    /** 模板 */
    private BtTemplateModel    template;

    /** 创建时间 */
    private Date gmtCreate;

    /** 修改时间 */
    private Date               gmtModified;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDigest() {
        return digest;
    }

    public void setDigest(String digest) {
        this.digest = digest;
    }

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

    public BtTemplateModel getTemplate() {
        return template;
    }

    public void setTemplate(BtTemplateModel template) {
        this.template = template;
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
