/*
 * Copyright (c) 2016.
 * BrandBigData.com Inc
 */

package com.bbd.message.service.send;


import com.bbd.message.enums.BtTemplateEngineEnum;

import java.io.File;

/**
 * 邮件模板管理器
 * 
 * 模板文件不一定是存在本地文件系统
 * 所以需要模板管理器来屏蔽底层获取文件的操作
 * 目前需要支持：本地获取、fastDFS获取
 * 以后需要切换其他文件系统时， 只需要添加实现类即可
 * 
 * @author zhao yuan
 * @version $Id: EmailTemplateManager.java, v 0.1 2016/7/23 zhao yuan Exp $
 */
public interface EmailTemplateManager {

    /**
     * 获取模板文件
     * 
     * @param templateName      模板名
     * @param version           模板版本
     * @param url               模板地址（可以是网络地址，本地地址，fastDFS地址等）
     * @param templateEngine    模板引擎   @return                  返回模板文件， 如果不存在， 则返回默认模板，如果连默认的模板都找不到，返回null
     */
    File getTemplateFile(String templateName, String version, String url,
                         BtTemplateEngineEnum templateEngine);

    /**
     * 通过模板引擎获取默认文件
     * 
     * @param templateEngine    模板引擎
     * @return                  默认文件
     */
    File getDefaultFile(BtTemplateEngineEnum templateEngine);
}
