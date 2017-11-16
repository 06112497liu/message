/*
 * Copyright (c) 2016.
 * BrandBigData.com Inc
 */

package com.bbd.message.enums;

/**
 * 收件人类型枚举
 * 
 * @author zhao yuan
 * @version $Id: BtEmailRecipientType.java, v 0.1 2016/7/21 zhao yuan Exp $
 */
public enum BtEmailRecipientTypeEnum implements EnumInterface {

    /** 收件人 */
    TO("TO", "recipient"),

    /** 抄送 */
    CC("CC", "carbon copy"),

    /** 密送 */
    BCC("BCC", "blind carbon copy");

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
    BtEmailRecipientTypeEnum(String code, String desc) {
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
