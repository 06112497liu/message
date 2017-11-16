package com.bbd.dao;

import com.bbd.domain.BtEmailRequest;
import com.bbd.domain.BtEmailRequestExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BtEmailRequestDao {
    int deleteByPrimaryKey(Integer id);

    int insert(BtEmailRequest record);

    int insertSelective(BtEmailRequest record);

    List<BtEmailRequest> selectByExample(BtEmailRequestExample example);

    BtEmailRequest selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BtEmailRequest record, @Param("example") BtEmailRequestExample example);

    int updateByExample(@Param("record") BtEmailRequest record, @Param("example") BtEmailRequestExample example);

    int updateByPrimaryKeySelective(BtEmailRequest record);

    int updateByPrimaryKey(BtEmailRequest record);
}