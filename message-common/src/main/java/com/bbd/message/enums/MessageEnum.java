package com.bbd.message.enums;

/**
 * @author fisher
 * @version $Id: MessageEnum.java, v 0.1 2017/11/7 11:31 fisher Exp $
 */
public enum MessageEnum {
    /**
     * 请求成功接收
     */
    SUCCESS(200, "成功"),
    SUCCESS_DATA_NLL(201, "暂无数据"),

    SYSTEM_ERROR(401, "系统异常");

    /**
     * 编码
     */
    private int code;

    /**
     * 消息
     */
    private String message;

    MessageEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
