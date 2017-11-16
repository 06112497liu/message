package com.bbd.message.service.converter;

import com.bbd.domain.BtEmail;
import com.bbd.domain.BtRecipient;
import com.bbd.message.enums.BtEmailRecipientTypeEnum;
import com.bbd.message.service.model.BtEmailModel;
import com.bbd.message.service.model.BtTemplateModel;
import com.google.common.base.Joiner;

import java.util.LinkedList;
import java.util.List;

/**
 * @author fisher
 * @version $Id: BtEmailConverter.java, v 0.1 2017/11/8 15:49 fisher Exp $
 */
public class BtEmailConverter {

    /**
     * 邮件Model转DO
     *
     * @param emailModel
     * @return
     */
    public static BtEmail convertToDo(BtEmailModel emailModel) {
        if (emailModel == null) {
            return null;
        }
        BtEmail btEmail = new BtEmail();
        btEmail.setId(emailModel.getId());
        btEmail.setGmtCreate(emailModel.getGmtCreate());
        btEmail.setGmtModified(emailModel.getGmtModified());
        btEmail.setSubject(emailModel.getSubject());
        btEmail.setModel(emailModel.getModel());
        btEmail.setRecipient(Joiner.on(",").join(emailModel.getTo()));
        BtTemplateModel template = emailModel.getTemplate();
        btEmail.setTemplateId(template == null ? null : template.getId());
        return btEmail;
    }

    public static List<BtRecipient> convertToBtRecipients(BtEmailModel btEmailModel) {
        List<BtRecipient> btRecipients = new LinkedList<BtRecipient>();
        List<String> toRecipients = btEmailModel.getTo();
        handleConvert(btEmailModel, btRecipients, toRecipients, BtEmailRecipientTypeEnum.TO);

        List<String> ccRecipients = btEmailModel.getCc();
        handleConvert(btEmailModel, btRecipients, ccRecipients, BtEmailRecipientTypeEnum.CC);

        List<String> bccRecipients = btEmailModel.getCc();
        handleConvert(btEmailModel, btRecipients, bccRecipients, BtEmailRecipientTypeEnum.BCC);

        return btRecipients;
    }

    /**
     * 转换
     *
     * @param btEmailModel
     * @param btRecipients
     * @param recipients
     * @param btEmailRecipientType
     */
    private static void handleConvert(BtEmailModel btEmailModel, List<BtRecipient> btRecipients,
                                      List<String> recipients,
                                      BtEmailRecipientTypeEnum btEmailRecipientType) {
        if (recipients != null) {
            for (String address : recipients) {
                BtRecipient recipient = new BtRecipient();
                recipient.setEmailId(btEmailModel.getId());
                recipient.setEmailAddr(address);
                recipient.setRecipientType(btEmailRecipientType.getCode());
                btRecipients.add(recipient);
            }
        }
    }

    /**
     * 邮件DO转Model
     *
     * @param btEmail
     * @return
     */
    public static BtEmailModel convertFromDO(BtEmail btEmail) {

        if (btEmail == null) {
            return null;
        }

        BtEmailModel btEmailModel = new BtEmailModel();
        btEmailModel.setId(btEmail.getId());
        btEmailModel.setSubject(btEmail.getSubject());
        btEmailModel.setGmtCreate(btEmail.getGmtCreate());
        btEmailModel.setGmtModified(btEmail.getGmtModified());
        btEmailModel.setModel(btEmail.getModel());

        return btEmailModel;
    }
}
