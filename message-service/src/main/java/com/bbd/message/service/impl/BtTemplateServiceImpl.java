package com.bbd.message.service.impl;

import com.bbd.dao.BtTemplateDao;
import com.bbd.domain.BtTemplate;
import com.bbd.domain.BtTemplateExample;
import com.bbd.higgs.utils.StringUtils;
import com.bbd.message.service.BtTemplateService;
import com.bbd.message.service.converter.BtTemplateConverter;
import com.bbd.message.service.model.BtTemplateModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author fisher
 * @version $Id: BtTemplateServiceImpl.java, v 0.1 2017/11/8 15:58 fisher Exp $
 */

@Service
public class BtTemplateServiceImpl implements BtTemplateService {

    @Autowired
    private BtTemplateDao btTemplateDao;

    public BtTemplateModel getByName(String templateName) {
        if (StringUtils.isNullOrEmpty(templateName)) {
            return null;
        }
        BtTemplateExample example = new BtTemplateExample();
        example.createCriteria().andNameEqualTo(templateName);
        List<BtTemplate>  templates = btTemplateDao.selectByExample(example);
        BtTemplate template = templates == null ? null : templates.get(0);
        return BtTemplateConverter.convertFromDO(template);
    }


    /**
     * 通过id获取邮件模板
     *
     * @param id                邮件模板id
     * @return                  模板基本信息
     */
    @Override
    public BtTemplateModel getByKey(Integer id) {
        return BtTemplateConverter.convertFromDO(btTemplateDao.selectByPrimaryKey(id));
    }
}
