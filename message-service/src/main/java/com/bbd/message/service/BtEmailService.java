package com.bbd.message.service;

import com.bbd.message.service.model.BtEmailModel;

/**
 * @author fisher
 * @version $Id: BtEmailService.java, v 0.1 2017/11/8 15:15 fisher Exp $
 */
public interface BtEmailService {

    void addEmail(BtEmailModel btEmail);

    BtEmailModel getById(Integer emailId);
}
