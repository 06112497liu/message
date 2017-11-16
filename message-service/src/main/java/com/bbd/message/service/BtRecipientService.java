package com.bbd.message.service;

import com.bbd.domain.BtRecipient;
import com.bbd.message.enums.BtEmailRecipientTypeEnum;
import com.bbd.message.service.model.BtRecipientModel;

import java.util.List;

/**
 * @author fisher
 * @version $Id: BtRecipientService.java, v 0.1 2017/11/8 16:25 fisher Exp $
 */
public interface BtRecipientService {
    /**
     * 批量添加收件人
     *
     * @param btRecipients      收件人列表
     */
    void addRecipients(List<BtRecipient> btRecipients);


    /**
     * 查询直接收件人
     *
     * @param emailId           邮件id
     * @return                  直接收件人列表
     */
    List<BtRecipientModel> findToRecipient(int emailId);

    /**
     * 查询抄送
     *
     * @param emailId           邮件id
     * @return                  抄送人列表
     */
    List<BtRecipientModel> findCcRecipient(int emailId);

    /**
     * 查询密送
     *
     * @param emailId           邮件id
     * @return                  密送人列表
     */
    List<BtRecipientModel> findBccRecipient(int emailId);

    /**
     * 查询邮件的某种类型的收件人
     *
     * @param emailId           邮件id
     * @param recipientType     收件人类型
     * @return                  指定类型的收件人列表
     */
    List<BtRecipientModel> findByRecipientType(int emailId, BtEmailRecipientTypeEnum recipientType);

}
