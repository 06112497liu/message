package com.bbd.message.service.impl;

import com.bbd.dao.BtEmailDao;
import com.bbd.domain.BtEmail;
import com.bbd.message.service.BtEmailService;
import com.bbd.message.service.BtRecipientService;
import com.bbd.message.service.BtTemplateService;
import com.bbd.message.service.converter.BtEmailConverter;
import com.bbd.message.service.converter.BtRecipientConverter;
import com.bbd.message.service.model.BtEmailModel;
import com.bbd.message.service.model.BtRecipientModel;
import com.bbd.message.service.model.BtTemplateModel;
import com.bbd.message.utils.Helpers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author fisher
 * @version $Id: BtEmailServiceImpl.java, v 0.1 2017/11/8 15:15 fisher Exp $
 */
@Service
public class BtEmailServiceImpl implements BtEmailService {


    /** 邮件模板服务 */
    @Resource
    private BtTemplateService btTemplateService;
    @Autowired
    private BtEmailDao btEmailDao;
    @Autowired
    private BtRecipientService btRecipientService;

    public void addEmail(BtEmailModel emailModel) {
        BtEmail btEmail = BtEmailConverter.convertToDo(emailModel);

        BtTemplateModel templateModel = btTemplateService.getByName(emailModel.getTemplate()
                .getName());
        btEmail.setTemplateId(templateModel == null ? null : templateModel.getId());

        btEmailDao.insert(btEmail);
        emailModel.setId(btEmail.getId());

        btRecipientService.addRecipients(BtEmailConverter.convertToBtRecipients(emailModel));
    }

    @Override
    public BtEmailModel getById(Integer emailId) {
        if (emailId == null)
            return null;
        BtEmail btEmail = btEmailDao.selectByPrimaryKey(emailId);
        BtEmailModel btEmailModel = BtEmailConverter.convertFromDO(btEmail);

        List<BtRecipientModel> toRecipient = btRecipientService.findToRecipient(emailId);
        List<BtRecipientModel> ccRecipient = btRecipientService.findCcRecipient(emailId);
        List<BtRecipientModel> bccRecipient = btRecipientService.findBccRecipient(emailId);

        //处理收件人
        btEmailModel.setTo(BtRecipientConverter.convertStringList(toRecipient));
        btEmailModel.setCc(BtRecipientConverter.convertStringList(ccRecipient));
        btEmailModel.setBcc(BtRecipientConverter.convertStringList(bccRecipient));


        //处理模板
        Integer templateId = btEmail.getTemplateId();
        if (!Helpers.isNull(templateId)) {
            BtTemplateModel btTemplateModel = btTemplateService.getByKey(templateId);
            btEmailModel.setTemplate(btTemplateModel);
        }

        return btEmailModel;
    }
}
