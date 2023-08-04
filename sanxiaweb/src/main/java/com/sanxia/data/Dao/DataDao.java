package com.sanxia.data.Dao;

import com.sanxia.data.pojo.SanxiaData;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DataDao {
    public void insertSanxiaData(List<SanxiaData> sanxiaDataList);
}
