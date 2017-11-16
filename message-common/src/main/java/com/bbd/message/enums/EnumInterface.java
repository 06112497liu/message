package com.bbd.message.enums;

/**
 * @author fisher
 * @version $Id: EnumInterface.java, v 0.1 2017/11/7 15:49 fisher Exp $
 */
public interface EnumInterface {
    /**
     * 获取枚举中定义的值
     *
     * @return 枚举代码
     */
    public String getCode();

    /**
     * 获取枚举中定义的备注信息
     *
     * @return 枚举代码描述
     */
    public String getDesc();
}
