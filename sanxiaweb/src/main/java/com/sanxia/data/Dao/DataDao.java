package com.sanxia.data.Dao;

import com.sanxia.data.pojo.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DataDao {
    public void insertSanxiaData(@Param("sanxiaDataList") List<SanxiaData> sanxiaDataList);

    public void insertEnvTypeMeta(@Param("table_name") String table_name,@Param("envTypeList") List<EnvTypeMeta> envTypeList);

    public void insertDeviceList(@Param("table_name") String table_name,@Param("deviceList") List<deviceList> deviceList);

    public void insertEnvTypes(@Param("envTypes") List<EnvTypes> envTypesList);

    public void insertEnvData(@Param("table_name") String table_name, @Param("envData")List<EnvData> envDataList);

    public void insertEnvDataBytime(@Param("table_name") String table_name, @Param("envData")List<EnvData> envDataList);

    public List<SanxiaData> selectSanxia(@Param("table_name") String table_name); 
}
