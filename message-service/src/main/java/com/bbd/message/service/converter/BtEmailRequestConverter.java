package com.bbd.message.service.converter;

import com.bbd.domain.BtEmailRequest;
import com.bbd.message.enums.BtEmailRequestStatusEnum;
import com.bbd.message.facade.mode.EmailRequestVO;
import com.bbd.message.service.model.BtEmailModel;
import com.bbd.message.service.model.BtEmailRequestModel;
import com.bbd.message.service.model.BtTemplateModel;

/**
 * @author fisher
 * @version $Id: BtEmailRequestConverter.java, v 0.1 2017/11/7 16:13 fisher Exp $
 */
public class BtEmailRequestConverter {
    /**
     * 邮件请求VO转Model
     *
     * @param vo
     * @return
     */
    public static BtEmailRequestModel convertFromVO(EmailRequestVO vo) {
        if (vo == null) {
            return null;
        }
        BtEmailRequestModel model = new BtEmailRequestModel();
        BtEmailModel btEmailModel = new BtEmailModel();
        btEmailModel.setSubject(vo.getSubject());
        btEmailModel.setModel(vo.getModel());
        btEmailModel.setTo(vo.getTo());
        btEmailModel.setCc(vo.getCc());
        btEmailModel.setBcc(vo.getBcc());

        BtTemplateModel btTemplateModel = new BtTemplateModel();
        btTemplateModel.setName(vo.getTemplate());
        btEmailModel.setTemplate(btTemplateModel);

        model.setBtEmailModel(btEmailModel);

        return model;
    }

    /**
     * 邮件请求Model转DO
     *
     * @param model
     * @return
     */
    public static BtEmailRequest convertToDO(BtEmailRequestModel model) {
        if (model == null) {
            return null;
        }
        BtEmailRequest request = new BtEmailRequest();
        BtEmailModel btEmail = model.getBtEmailModel();
        request.setId(model.getId());
        request.setEmailId(btEmail == null ? null : btEmail.getId());
        request.setGmtCreate(model.getGmtCreate());
        request.setGmtModified(model.getGmtModified());
        request.setRetry(model.getRetry());
        request.setStatus(model.getBtEmailRequestStatus().getCode());
        request.setFailMessage(model.getFailMessage());
        return request;
    }


    /**
     * 邮件请求DO转Model
     *
     * @param o
     * @return
     */
    public static BtEmailRequestModel convertFromDO(BtEmailRequest o) {
        if (o == null) {
            return null;
        }
        BtEmailRequestModel model = new BtEmailRequestModel();
        model.setId(o.getId());
        model.setBtEmailRequestStatus(BtEmailRequestStatusEnum.valueOf(o.getStatus()));
        model.setFailMessage(o.getFailMessage());
        model.setGmtCreate(o.getGmtCreate());
        model.setGmtModified(o.getGmtModified());
        model.setRetry(o.getRetry());
        return model;
    }
}
