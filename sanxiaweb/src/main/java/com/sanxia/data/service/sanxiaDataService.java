package com.sanxia.data.service;

public interface sanxiaDataService {

      void insertSanxiaData();

      void insertEnvTypeMeta(String Tablename);

      void insertDeviceList(String Tablename);

      void insertEnvTypes();

      void insertEnvData(String select_table, String table_name);


}
