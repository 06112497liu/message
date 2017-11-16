package com.bbd.message.service.result;

import java.io.Serializable;

/**
 * @author fisher
 * @version $Id: MessageResult.java, v 0.1 2017/11/7 10:31 fisher Exp $
 */
public class MessageResult<T extends Serializable> implements Serializable {

    /** 操作是否成功 */
    private boolean           success;

    /** 详细信息 */
    private String            msg;

    /** 结果状态码 */
    private String            statusCode;

    /** 数据 */
    private T                 data;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
