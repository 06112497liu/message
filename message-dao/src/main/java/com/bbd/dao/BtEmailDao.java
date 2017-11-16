package com.bbd.dao;

import com.bbd.domain.BtEmail;
import com.bbd.domain.BtEmailExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BtEmailDao {
    int deleteByPrimaryKey(Integer id);

    int insert(BtEmail record);

    int insertSelective(BtEmail record);

    List<BtEmail> selectByExampleWithBLOBs(BtEmailExample example);

    List<BtEmail> selectByExample(BtEmailExample example);

    BtEmail selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BtEmail record, @Param("example") BtEmailExample example);

    int updateByExampleWithBLOBs(@Param("record") BtEmail record, @Param("example") BtEmailExample example);

    int updateByExample(@Param("record") BtEmail record, @Param("example") BtEmailExample example);

    int updateByPrimaryKeySelective(BtEmail record);

    int updateByPrimaryKeyWithBLOBs(BtEmail record);

    int updateByPrimaryKey(BtEmail record);
}