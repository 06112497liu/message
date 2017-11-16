/*
 * Copyright (c) 2016.
 * BrandBigData.com Inc
 */

package com.bbd.message.service.converter;

import com.bbd.domain.BtRecipient;
import com.bbd.message.enums.BtEmailRecipientTypeEnum;
import com.bbd.message.service.model.BtRecipientModel;

import java.util.LinkedList;
import java.util.List;

/**
 * 邮件接收人模型
 * 
 * @author zhao yuan
 * @version $Id: BtRecipientConverter.java, v 0.1 2016/7/21 zhao yuan Exp $
 */
public class BtRecipientConverter {

    /**
     * 邮件接受人DO转Model
     * 
     * @param btRecipient
     * @return
     */
    public static BtRecipientModel convertFromDo(BtRecipient btRecipient) {
        if (btRecipient == null)
            return null;
        BtRecipientModel model = new BtRecipientModel();
        model.setId(btRecipient.getId());
        model.setGmtModified(btRecipient.getGmtModified());
        model.setGmtCreate(btRecipient.getGmtCreate());
        model.setEmailAddr(btRecipient.getEmailAddr());
        model.setEmailId(btRecipient.getEmailId());
        model.setRecipientType(BtEmailRecipientTypeEnum.valueOf(btRecipient.getRecipientType()));
        return model;
    }

    /**
     * 邮件接收人List<BtRecipientDO>转List<BtRecipientModel>
     * 
     * @param btRecipientList
     * @return
     */
    public static List<BtRecipientModel> convertFromDo(List<BtRecipient> btRecipientList) {
        if (btRecipientList == null || btRecipientList.isEmpty())
            return null;
        List<BtRecipientModel> models = new LinkedList<>();
        for (BtRecipient btRecipient : btRecipientList) {
            BtRecipientModel model = new BtRecipientModel();
            model.setId(btRecipient.getId());
            model.setGmtModified(btRecipient.getGmtModified());
            model.setGmtCreate(btRecipient.getGmtCreate());
            model.setEmailAddr(btRecipient.getEmailAddr());
            model.setEmailId(btRecipient.getEmailId());
            model.setRecipientType(BtEmailRecipientTypeEnum.valueOf(btRecipient.getRecipientType()));
            models.add(model);
        }
        return models;
    }

    /**
     * 获取邮件地址列表
     * 
     * @param models
     * @return
     */
    public static List<String> convertStringList(List<BtRecipientModel> models) {
        if (models == null || models.isEmpty())
            return null;

        List<String> strings = new LinkedList<>();
        for (BtRecipientModel model : models) {
            strings.add(model.getEmailAddr());
        }

        return strings;
    }
}
