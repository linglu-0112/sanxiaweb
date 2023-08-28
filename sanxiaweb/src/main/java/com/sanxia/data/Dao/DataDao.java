package com.sanxia.data.Dao;

import com.sanxia.data.pojo.EnvType;
import com.sanxia.data.pojo.SanxiaData;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DataDao {
    public void insertSanxiaData(@Param("sanxiaDataList") List<SanxiaData> sanxiaDataList);

    public void insertEnvType(@Param("table_name") String table_name,@Param("envTypeList") List<EnvType> envTypeList);
}
