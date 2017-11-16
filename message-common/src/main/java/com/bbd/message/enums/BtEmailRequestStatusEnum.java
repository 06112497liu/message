package com.bbd.message.enums;

/**
 * @author fisher
 * @version $Id: BtEmailRequestStatusEnum.java, v 0.1 2017/11/7 15:54 fisher Exp $
 */
public enum  BtEmailRequestStatusEnum implements EnumInterface{

    /** 新任务状态 */
    NEW("NEW", "new"),

    /** 运行状态 */
    RUNNING("RUNNING", "The request running"),

    /** 成功 */
    SUCCESS("SUCCESS", "Has bean send successfully"),

    /** 失败 */
    FAILED("FAILED", "Send failed");

    /** 枚举值 */
    private String code;

    /** 枚举描述 */
    private String desc;

    /**
     * 构造函数
     *
     * @param code
     * @param desc
     */
    BtEmailRequestStatusEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
