package com.bbd.message.service.model;

import com.bbd.message.enums.BtEmailRequestStatusEnum;

import java.io.Serializable;
import java.util.Date;

/**
 * @author fisher
 * @version $Id: BtEmailRequestModel.java, v 0.1 2017/11/7 15:47 fisher Exp $
 */
public class BtEmailRequestModel implements Serializable {

    /** 主键 */
    private Integer              id;

    /** 邮件model */
    private BtEmailModel         btEmailModel;

    /** 重试次数 */
    private int                  retry;

    /** 错误信息 */
    private String               failMessage;

    /** 邮件请求的处理状态 */
    private BtEmailRequestStatusEnum btEmailRequestStatus;

    /** 创建时间 */
    private Date gmtCreate;

    /** 更新时间 */
    private Date                 gmtModified;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public BtEmailModel getBtEmailModel() {
        return btEmailModel;
    }

    public void setBtEmailModel(BtEmailModel btEmailModel) {
        this.btEmailModel = btEmailModel;
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

    public BtEmailRequestStatusEnum getBtEmailRequestStatus() {
        return btEmailRequestStatus;
    }

    public void setBtEmailRequestStatus(BtEmailRequestStatusEnum btEmailRequestStatus) {
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
