package com.bbd.dao;

import com.bbd.domain.BtTemplate;
import com.bbd.domain.BtTemplateExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BtTemplateDao {
    int deleteByPrimaryKey(Integer id);

    int insert(BtTemplate record);

    int insertSelective(BtTemplate record);

    List<BtTemplate> selectByExample(BtTemplateExample example);

    BtTemplate selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BtTemplate record, @Param("example") BtTemplateExample example);

    int updateByExample(@Param("record") BtTemplate record, @Param("example") BtTemplateExample example);

    int updateByPrimaryKeySelective(BtTemplate record);

    int updateByPrimaryKey(BtTemplate record);
}