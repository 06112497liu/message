/*
 * Copyright (c) 2016.
 * BrandBigData.com Inc
 */

package com.bbd.message.service.send;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bbd.domain.BtEmailRequest;
import com.bbd.domain.BtTemplate;
import com.bbd.message.enums.BtTemplateEngineEnum;
import com.bbd.message.service.model.BtEmailModel;
import com.bbd.message.service.model.BtEmailRequestModel;
import com.bbd.message.service.model.BtTemplateModel;
import com.bbd.message.utils.Helpers;
import org.apache.velocity.app.VelocityEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.ui.velocity.VelocityEngineUtils;

import javax.annotation.Resource;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * 使用velocity渲染的模板引擎
 *
 * @author zhao yuan
 * @version $Id: VelocityEmailRender.java, v 0.1 2016/7/23 zhao yuan Exp $
 */
@Service
public class VelocityEmailRender implements EmailRender {

    /** 此字段表示，在vm模板里取原生model串的属性名 */
    public static final String   BT_ORIGIN_EMAIL_MODEL_ATTR_NAME = "bt_origin_email_model";
    /** 日志 */
    private static Logger        logger                          = LoggerFactory
                                                                     .getLogger(VelocityEmailRender.class);
    /** velocity模版引擎 */
    @Resource
    private VelocityEngine       velocityEngine;

    /** 邮件模板管理器 */
    @Resource
    private EmailTemplateManager emailTemplateManager;


    public boolean renderAble(BtTemplateEngineEnum btTemplateEngine) {
        return BtTemplateEngineEnum.VELOCITY == btTemplateEngine;
    }



    public String render(BtEmailRequestModel emailRequestModel) {
        BtEmailModel btEmail = emailRequestModel.getBtEmailModel();
        String modelString = btEmail.getModel();
        BtTemplateModel template = btEmail.getTemplate();
        File templateFile = getTemplateFile(template);

        if (templateFile == null) {
            return modelString;
        }

        Map<String, Object> model = parseEmailModel(modelString);
        String content = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine,
            templateFile.getAbsolutePath(), StandardCharsets.UTF_8.name(), model);

        return content;
    }

    /**
     * 获取邮件模板文件， 如果模板为空，则使用默认模板
     * @param template 模板
     * @return 模板文件
     */
    private File getTemplateFile(BtTemplateModel template) {
        if (Helpers.isNull(template)) {
            return emailTemplateManager.getDefaultFile(BtTemplateEngineEnum.VELOCITY);
        }
        //用模板最后修改时间做为 模板的版本
        String version = String.valueOf(template.getGmtModified().getTime());
        return emailTemplateManager.getTemplateFile(template.getName(), version, template.getUrl(),
            BtTemplateEngineEnum.VELOCITY);
    }

    /**
     * 解析邮件内容为键值对
     * 解析邮件model的时候，除了解析出来的属性， 还会把整个原生串放入model里。
     * 如果解析出来的model刚好包含同名属性， 则会覆盖原生串
     * 
     * @param modelString 原生串
     * @return 键值对形式的同邮件model
     */
    private Map<String, Object> parseEmailModel(String modelString) {
        Map<String, Object> model = new HashMap();
        model.put(BT_ORIGIN_EMAIL_MODEL_ATTR_NAME, modelString);
        try {
            JSONObject jsonModel = JSON.parseObject(modelString);
            model.putAll(jsonModel);
        } catch (Exception e) {
            logger.warn("Parse model error, email model:{}", modelString);
        }
        return model;
    }
}
