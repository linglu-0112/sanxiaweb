package com.sanxia.data.service;

import java.io.UnsupportedEncodingException;

public interface sanxiaDataService {

      void insertEnv(String Tablename);

      void insertEnvTypeMeta(String Tablename);

      void insertDeviceList(String Tablename);

      void insertEnvTypes();

      void insertEnvData(String select_table, String table_name);

      void insertEnvDataBytime(String select_table, String table_name) throws UnsupportedEncodingException;

      void insertRelicData(String envId, String table_name);

      void insertSanxiaData(String select_table, String table_name);

      void insertSanxiaEx(String envId, String table_name);

}
