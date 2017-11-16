/*
 * Copyright (c) 2016.
 * BrandBigData.com Inc
 */

package com.bbd.message.service.send;

import com.bbd.domain.BtEmailRequest;
import com.bbd.message.enums.BtTemplateEngineEnum;
import com.bbd.message.service.model.BtEmailRequestModel;

/**
 * 邮件内容渲染器
 * 
 * @author zhao yuan
 * @version $Id: EmailRender.java, v 0.1 2016/7/23 zhao yuan Exp $
 */
public interface EmailRender {

    /**
     * 此渲染器是否支持指定模板引擎
     * 
     * @param btTemplateEngine      模版引擎
     * @return
     */
    boolean renderAble(BtTemplateEngineEnum btTemplateEngine);

    /**
     * 通过邮件请求模板进行渲染
     * 
     * @param emailRequestModel     模板请求服务
     * @return
     */
    String render(BtEmailRequestModel emailRequestModel);
}
