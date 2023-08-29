package com.sanxia.data.Dao;

import com.sanxia.data.pojo.EnvTypeMeta;
import com.sanxia.data.pojo.EnvTypes;
import com.sanxia.data.pojo.SanxiaData;
import com.sanxia.data.pojo.deviceList;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DataDao {
    public void insertSanxiaData(@Param("sanxiaDataList") List<SanxiaData> sanxiaDataList);

    public void insertEnvTypeMeta(@Param("table_name") String table_name,@Param("envTypeList") List<EnvTypeMeta> envTypeList);

    public void insertDeviceList(@Param("table_name") String table_name,@Param("deviceList") List<deviceList> deviceList);

    public void insertEnvTypes(@Param("envTypes") List<EnvTypes> envTypesList);
}
