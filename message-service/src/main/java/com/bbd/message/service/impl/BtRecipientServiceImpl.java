package com.bbd.message.service.impl;

import com.bbd.dao.BtRecipientDao;
import com.bbd.domain.BtRecipient;
import com.bbd.domain.BtRecipientExample;
import com.bbd.message.enums.BtEmailRecipientTypeEnum;
import com.bbd.message.service.BtRecipientService;
import com.bbd.message.service.converter.BtRecipientConverter;
import com.bbd.message.service.model.BtRecipientModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author fisher
 * @version $Id: BtRecipientServiceImpl.java, v 0.1 2017/11/8 16:25 fisher Exp $
 */
@Service
public class BtRecipientServiceImpl implements BtRecipientService {

    @Autowired
    private BtRecipientDao btRecipientDao;

    public void addRecipients(List<BtRecipient> btRecipients) {
        if (btRecipients == null || btRecipients.size() < 1) {
            return;
        }

        for (BtRecipient btRecipient : btRecipients) {
            addRecipient(btRecipient);
        }
    }

    @Override
    public List<BtRecipientModel> findToRecipient(int emailId) {
        return findByRecipientType(emailId, BtEmailRecipientTypeEnum.TO);
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<BtRecipientModel> findCcRecipient(int emailId) {
        return findByRecipientType(emailId, BtEmailRecipientTypeEnum.CC);
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<BtRecipientModel> findBccRecipient(int emailId) {
        return findByRecipientType(emailId, BtEmailRecipientTypeEnum.BCC);
    }

    @Override
    public List<BtRecipientModel> findByRecipientType(int emailId,
                                                      BtEmailRecipientTypeEnum recipientType) {
        BtRecipientExample example = new BtRecipientExample();
        example.createCriteria().andEmailIdEqualTo(emailId).andRecipientTypeEqualTo(recipientType.getCode());
        return BtRecipientConverter.convertFromDo(btRecipientDao.selectByExample(example));
    }


    public void addRecipient(BtRecipient btRecipient) {
        btRecipientDao.insert(btRecipient);
    }
}
