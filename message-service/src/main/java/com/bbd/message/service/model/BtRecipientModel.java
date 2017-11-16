/*
 * Copyright (c) 2016.
 * BrandBigData.com Inc
 */

package com.bbd.message.service.model;

import com.bbd.message.enums.BtEmailRecipientTypeEnum;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.io.Serializable;
import java.util.Date;

/**
 * 邮件接收人模型
 * 
 * @author zhao yuan
 * @version $Id: BtRecipientModel.java, v 0.1 2016/7/21 zhao yuan Exp $
 */
public class BtRecipientModel implements Serializable {

    /** 序列号id */
    private static final long    serialVersionUID = 2993658863276264921L;

    /** 主键 */
    private Integer              id;

    /** 邮件id */
    private Integer              emailId;

    /** 收件人类型 */
    private BtEmailRecipientTypeEnum recipientType;

    /** 邮箱地址 */
    private String               emailAddr;

    /** 创建时间 */
    private Date                 gmtCreate;

    /** 更新时间 */
    private Date                 gmtModified;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getEmailId() {
        return emailId;
    }

    public void setEmailId(Integer emailId) {
        this.emailId = emailId;
    }

    public BtEmailRecipientTypeEnum getRecipientType() {
        return recipientType;
    }

    public void setRecipientType(BtEmailRecipientTypeEnum recipientType) {
        this.recipientType = recipientType;
    }

    public String getEmailAddr() {
        return emailAddr;
    }

    public void setEmailAddr(String emailAddr) {
        this.emailAddr = emailAddr;
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

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
