/*
 * Copyright (c) 2016.
 * BrandBigData.com Inc
 */

package com.bbd.message.enums;

import java.text.MessageFormat;

/**
 * 模板枚举
 * 
 * @author zhao yuan
 * @version $Id: BtTemplateEngine.java, v 0.1 2016/7/21 zhao yuan Exp $
 */
public enum BtTemplateEngineEnum implements EnumInterface {

    /** 模板 */
    VELOCITY("vm", "empty", "VELOCITY", "velocity template engine");

    /** 模板文件后缀 */
    private String suffix;

    /** 默认模板文件名 */
    private String defaultTemplate;

    /** 枚举值 */
    private String code;

    /** 枚举描述 */
    private String desc;

    /**
     * 构造函数
     * 
     * @param suffix
     * @param defaultTemplate
     * @param code
     * @param desc
     */
    BtTemplateEngineEnum(String suffix, String defaultTemplate, String code, String desc) {
        this.suffix = suffix;
        this.defaultTemplate = defaultTemplate;
        this.code = code;
        this.desc = desc;
    }

    public String getSuffix() {
        return suffix;
    }

    /**
     * 获取默认模板名，不含后缀
     * @return 默认模板名
     */
    public String getDefaultTemplate() {
        return defaultTemplate;
    }

    /**
     * 获取默认模板的文件名
     * @return 默认模板的文件名
     */
    public String getDefaultTemplateFileName() {
        return defaultTemplate + "." + suffix;
    }


    public String getCode() {
        return code;
    }


    public String getDesc() {
        return desc;
    }

    /**
     * 根据模板名，构造模板文件名
     * 模板文件名： 模板名-版本.后缀
     * @param templateName 模板名
     * @param version  模板版本
     * @return 模板文件名
     */
    public String buildFullFileName(String templateName, String version) {
        return MessageFormat.format("{0}-{1}.{2}", templateName, version, suffix);
    }
}
