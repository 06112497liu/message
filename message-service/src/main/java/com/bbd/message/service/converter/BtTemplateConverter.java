/*
 * Copyright (c) 2016.
 * BrandBigData.com Inc
 */

package com.bbd.message.service.converter;

import com.bbd.domain.BtTemplate;
import com.bbd.message.enums.BtTemplateEngineEnum;
import com.bbd.message.service.model.BtTemplateModel;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 邮件模板转换器
 * 
 * @author zhao yuan
 * @version $Id: BtTemplateConverter.java, v 0.1 2016/7/21 zhao yuan Exp $
 */
public class BtTemplateConverter {



    /**
     * 邮件模板DO转Model
     * 
     * @param template
     * @return
     */
    public static BtTemplateModel convertFromDO(BtTemplate template) {
        if (template == null)
            return null;
        BtTemplateModel model = new BtTemplateModel();
        model.setId(template.getId());
        model.setGmtModified(template.getGmtModified());
        model.setGmtCreate(template.getGmtCreate());
        model.setDescription(template.getDescription());
        model.setModelDesc(template.getModelDesc());
        model.setName(template.getName());
        model.setUrl(template.getUrl());
        model.setTemplateEngine(BtTemplateEngineEnum.valueOf(template.getTemplateEngine()));
        return model;
    }

}
