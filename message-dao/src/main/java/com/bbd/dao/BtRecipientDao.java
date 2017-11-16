package com.bbd.dao;

import com.bbd.domain.BtRecipient;
import com.bbd.domain.BtRecipientExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BtRecipientDao {
    int deleteByPrimaryKey(Integer id);

    int insert(BtRecipient record);

    int insertSelective(BtRecipient record);

    List<BtRecipient> selectByExample(BtRecipientExample example);

    BtRecipient selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BtRecipient record, @Param("example") BtRecipientExample example);

    int updateByExample(@Param("record") BtRecipient record, @Param("example") BtRecipientExample example);

    int updateByPrimaryKeySelective(BtRecipient record);

    int updateByPrimaryKey(BtRecipient record);
}