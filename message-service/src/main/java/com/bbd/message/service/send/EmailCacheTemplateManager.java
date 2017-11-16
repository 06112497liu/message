package com.bbd.message.service.send;

import com.bbd.message.enums.BtTemplateEngineEnum;
import com.bbd.message.utils.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.File;
import java.text.MessageFormat;

/**
 * @author fisher
 * @version $Id: EmailCacheTemplateManager.java, v 0.1 2017/11/16 10:21 fisher Exp $
 */
@Service
public class EmailCacheTemplateManager implements EmailTemplateManager {
    /** 日志 */
    private static Logger logger = LoggerFactory.getLogger(EmailCacheTemplateManager.class);

    private File defalut_dir = null;

    @PostConstruct
    public void initBaseDir() {
        defalut_dir = FileUtils.getDirFromClassPath("template");
    }
    @Override
    public File getTemplateFile(String templateName, String version, String url,
                                BtTemplateEngineEnum templateEngine) {
        try {
            String fileName = MessageFormat.format("{0}.{1}", templateName, templateEngine.getSuffix());
            return new File(defalut_dir,fileName);
        } catch (Exception e) {
            logger.error(MessageFormat.format("获取模板文件：{0}出错，将使用默认模板发送.", url), e);
            return getDefaultFile(templateEngine);
        }
    }

    @Override
    public File getDefaultFile(BtTemplateEngineEnum templateEngine) {
        logger.debug("使用默认模板.");
        File file = new File(defalut_dir,
                templateEngine.getDefaultTemplateFileName());
        if (!file.exists()) {
            logger.error("默认邮件模板缺失!!!");
            return null;
        }
        return file;
    }
}
