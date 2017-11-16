package com.bbd.message.service;

import com.bbd.message.service.model.BtTemplateModel;

/**
 * @author fisher
 * @version $Id: BtTemplateService.java, v 0.1 2017/11/8 15:58 fisher Exp $
 */
public interface BtTemplateService {
    /**
     * 根据模板名字
     *
     * @param templateName      模板名
     * @return                  模板基本信息
     */
    BtTemplateModel getByName(String templateName);

    BtTemplateModel getByKey(Integer templateId);
}
