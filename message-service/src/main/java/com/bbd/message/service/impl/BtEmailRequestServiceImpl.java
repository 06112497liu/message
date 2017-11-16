package com.bbd.message.service.impl;

import com.bbd.dao.BtEmailRequestDao;
import com.bbd.domain.BtEmailRequest;
import com.bbd.domain.BtEmailRequestExample;
import com.bbd.message.enums.BtEmailRequestStatusEnum;
import com.bbd.message.service.BtEmailRequestService;
import com.bbd.message.service.BtEmailService;
import com.bbd.message.service.converter.BtEmailRequestConverter;
import com.bbd.message.service.model.BtEmailModel;
import com.bbd.message.service.model.BtEmailRequestModel;
import com.bbd.message.utils.Helpers;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author fisher
 * @version $Id: BtEmailRequestServiceImpl.java, v 0.1 2017/11/7 16:37 fisher Exp $
 */
@Service
public class BtEmailRequestServiceImpl implements BtEmailRequestService {

    @Resource
    private BtEmailService btEmailService;
    @Resource
    private BtEmailRequestDao btEmailRequestDao;

    public void addBtEmailRequest(BtEmailRequestModel btEmailRequestModel) {
        btEmailRequestModel.setBtEmailRequestStatus(BtEmailRequestStatusEnum.NEW);
        BtEmailModel btEmail = btEmailRequestModel.getBtEmailModel();
        btEmailService.addEmail(btEmail);

        BtEmailRequest btEmailRequest = BtEmailRequestConverter.convertToDO(btEmailRequestModel);
        btEmailRequest.setEmailId(btEmail.getId());
        Date now = new Date();
        btEmailRequest.setGmtCreate(now);
        btEmailRequest.setGmtModified(now);
        btEmailRequestDao.insert(btEmailRequest);
    }

    /**
     * 获取一条待发送的邮件
     *
     * @param maxRetry
     * @return
     */
    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public BtEmailRequestModel consumeRequest(int maxRetry) {
        BtEmailRequestModel requestToSend = findRequestToSend(maxRetry);
        if (requestToSend == null)
            return null;

        int retry = requestToSend.getRetry();
        //对于状态为失败的请求， 把重试次数+1
        if (requestToSend.getBtEmailRequestStatus().equals(BtEmailRequestStatusEnum.FAILED)) {
            requestToSend.setRetry(retry + 1);
        }
        requestToSend.setBtEmailRequestStatus(BtEmailRequestStatusEnum.RUNNING);

        requestToSend.setGmtModified(new Date());
        btEmailRequestDao.updateByPrimaryKey(BtEmailRequestConverter.convertToDO(requestToSend));
        return requestToSend;
    }

    @Override
    public BtEmailRequestModel findRequestToSend(int maxRetry) {
        List status = new ArrayList();
        status.add("NEW");
        status.add("FAILED");
        BtEmailRequestExample example = new BtEmailRequestExample();
        example.createCriteria().andStatusIn(status).andRetryLessThan(maxRetry);
        List<BtEmailRequest> btEmailRequestDOs = btEmailRequestDao.selectByExample(example);
        if (Helpers.isNullOrEmpty(btEmailRequestDOs)) {
            return null;
        }
        BtEmailRequest btEmailRequest = btEmailRequestDOs.get(0);

        BtEmailRequestModel model = BtEmailRequestConverter.convertFromDO(btEmailRequest);
        BtEmailModel btEmailModel = btEmailService.getById(btEmailRequest.getEmailId());
        model.setBtEmailModel(btEmailModel);

        return model;
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void afterSend(BtEmailRequestModel btEmailRequestModel) {
        BtEmailRequest request = BtEmailRequestConverter.convertToDO(btEmailRequestModel);
        request.setGmtModified(new Date());
        btEmailRequestDao.updateByPrimaryKey(request);
    }
}
