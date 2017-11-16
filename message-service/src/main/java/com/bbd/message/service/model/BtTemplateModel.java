/*
 * Copyright (c) 2016.
 * BrandBigData.com Inc
 */

package com.bbd.message.service.model;


import com.bbd.message.enums.BtTemplateEngineEnum;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.io.Serializable;
import java.util.Date;

/**
 * 邮件模板模型
 * 
 * @author zhao yuan
 * @version $Id: BtTemplateModel.java, v 0.1 2016/7/21 zhao yuan Exp $
 */
public class BtTemplateModel implements Serializable {

    /** 序列号id */
    private static final long serialVersionUID = -4889200459303529123L;

    /** 自增主键 */
    private Integer           id;

    /** 模板名字 */
    private String            name;

    /** 模板的url */
    private String            url;

    /** 使用的模板引擎：velocity、freemarker等 */
    private BtTemplateEngineEnum templateEngine;

    /** 模板需要的数据结构描述， 最好是json格式的 */
    private String            modelDesc;

    /** 邮件模板自身的作用描述 */
    private String            description;

    /** 创建时间 */
    private Date              gmtCreate;

    /** 修改时间 */
    private Date              gmtModified;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public BtTemplateEngineEnum getTemplateEngine() {
        return templateEngine;
    }

    public void setTemplateEngine(BtTemplateEngineEnum templateEngine) {
        this.templateEngine = templateEngine;
    }

    public String getModelDesc() {
        return modelDesc;
    }

    public void setModelDesc(String modelDesc) {
        this.modelDesc = modelDesc;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
