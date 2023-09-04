package com.sanxia.data.service;

import com.sanxia.data.Dao.DataDao;
import com.sanxia.data.pojo.*;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
@Service
@Component
@Transactional
public class sanxiaDataServiceImpl implements sanxiaDataService {
    @Autowired
    private DataDao dd;

    public void insertEnvData(String select_table, String table_name){
        List<SanxiaData> sData = dd.selectSanxia(select_table);
        JsonBean jsonBean = new JsonBean();
        List<JsonBean.DataBean> dataBeanList = new ArrayList<>();
        for (int i = 0; i < sData.size(); i++){
            String s = sData.get(i).getEnvId();
//            System.out.println(s);
            String url = "http://111.207.242.123:10081/api/v1/iot/50010301/env/data?envId=";
            JSONObject json = new JSONObject();
            String Url = url + s;
            json = fromurl(Url);
            jsonBean.setMsg(json.optString("msg"));
            jsonBean.setCode(json.optString("code"));
            // 获取外层data
            JSONObject dataobj = json.optJSONObject("data");
            //获取内层data
            JSONArray dataArray = dataobj.optJSONArray("data");
            if(dataArray.size() == 0){
                System.out.println("保存空间中的数据为空");
            }else{
                System.out.println("保存空间中的数据不为空");
                // 根据dataArray的大小进行循环插入数据：
                for (int j = 0; j < dataArray.size(); j++){
                    JsonBean.DataBean dataBean = new JsonBean.DataBean();
                    JSONObject dataobj_i = dataArray.optJSONObject(j);
                    dataBean.setEnvId(dataobj.optString("envId"));
                    dataBean.setParentId(dataobj.optString("parentId"));
                    dataBean.setEnvName(dataobj.optString("envName"));
                    dataBean.setEnvType(dataobj.optString("envType"));
                    dataBean.setEnvTypeName(dataobj.optString("envTypeName"));
                    dataBean.setEnvCoverUrl(dataobj.optString("envCoverUrl"));
                    dataBean.setCollectTime(dataobj_i.optString("collectTime"));
                    dataBean.setEnvirParamType(dataobj_i.optString("envirParamType"));
                    dataBean.setEnvirParamValue(dataobj_i.optString("envirParamValue")) ;

                    dataBeanList.add(dataBean);
                }
            }
        }
        jsonBean.setData(dataBeanList);
//        System.out.println(jsonBean.getData().size());
        List<EnvData> envDataList = new ArrayList<>();
        try {
            for (int i = 0; i < jsonBean.getData().size(); i++) {

                EnvData envData = new EnvData();

                envData = envDataInsert(jsonBean, i);

                envDataList.add(envData);
            }
            System.out.println("数据插入到envDataList中,一共"+envDataList.size()+"条数据");
            dd.insertEnvData(table_name,envDataList);
        } catch (Exception e) {
            throw new RuntimeException("解析异常");
        }
    }


    @Override
    public void insertSanxiaData() {
        JsonBean jsonBean = new JsonBean();
        String url = "http://111.207.242.123:10081/api/v1/iot/50010301/env/findList?envId=";
        jsonBean = apiquery(url);
        List<SanxiaData> sanxiaDatalist = new ArrayList<>();
        if (jsonBean.getData().size() != 0) {
            sanxiaDatalist = getdatalist(jsonBean);
            dd.insertSanxiaData(sanxiaDatalist);
            System.out.println("执行成功");
        } else {
            System.out.print(" 位置点无数据" + '\n');
        }

    }

    public void insertEnvTypeMeta(String table_name) {
        JsonBean jsonBean = new JsonBean();
        String url = "http://111.207.242.123:10081/api/v1/iot/common/metaData";
        JSONObject json = new JSONObject();
        json = fromurl(url);
        jsonBean.setMsg(json.optString("msg"));
        jsonBean.setCode(json.optString("code"));
        JSONObject data = json.getJSONObject("data");
//          解析json数据，获得dataBeanList
        Iterator<String> keys = data.keys();
        List<JsonBean.DataBean> dataBeanList = new ArrayList<>();
        while (keys.hasNext()) {
            String key = keys.next();
//                System.out.println(key);
            JSONObject dataObj = data.optJSONObject(key);

            JsonBean.DataBean dataBean = new JsonBean.DataBean();
            dataBean.setEnvirDataUnit(dataObj.getString("envirDataUnit"));
            dataBean.setEnvirParamName(dataObj.getString("envirParamName"));
            dataBean.setEnvirParamCode(dataObj.getString("envirParamCode"));
            dataBean.setEnvirParamType(dataObj.getString("envirParamType"));

            dataBeanList.add(dataBean);
        }
        jsonBean.setData(dataBeanList);
//          将dataBeanList添加到envTypeList,并送到Dao层
        List<EnvTypeMeta> envTypeList = new ArrayList<>();
        try {
            for (int i = 0; i < jsonBean.getData().size(); i++) {

                EnvTypeMeta envType = new EnvTypeMeta();

                envType = envTypeInsert(jsonBean, i);

                envTypeList.add(envType);
            }
        } catch (Exception e) {
            throw new RuntimeException("解析异常");
        }
        dd.insertEnvTypeMeta(table_name, envTypeList);
        System.out.println("获得envTypeList，执行成功");
        System.out.println(envTypeList);


    }

    @Override
    public void insertDeviceList(String table_name) {
        JsonBean jsonBean = new JsonBean();
        String url = "http://111.207.242.123:10081/api/v1/iot/50010301/devices/findList";

            //          从url获取json数据
        JSONObject json = new JSONObject();
        json = fromurl(url);
        jsonBean.setMsg(json.optString("msg"));
        jsonBean.setCode(json.optString("code"));
        JSONArray dataArray = json.optJSONArray("data");
        //          解析json数据，获得dataBeanList
        List<JsonBean.DataBean> dataBeanList = new ArrayList<>();
        for (int i = 0; i < dataArray.size(); i++) {
            JSONObject dataObj_i = dataArray.optJSONObject(i);
            JSONObject data = dataObj_i.optJSONObject("deviceParamsVal");
            Iterator<String> keys = data.keys();
            while (keys.hasNext()) {
                String key = keys.next();
                JSONObject deviceParamObj = data.optJSONObject(key);
                JsonBean.DataBean dataBean = new JsonBean.DataBean();
                dataBean.setDeviceId(dataObj_i.getString("deviceId"));
                dataBean.setDeviceName(dataObj_i.getString("deviceName"));
                dataBean.setEnvId(dataObj_i.getString("envId"));
                dataBean.setEnvName(dataObj_i.getString("envName"));
                dataBean.setDeviceType(dataObj_i.getString("deviceType"));
                dataBean.setDeviceTypeName(dataObj_i.getString("deviceTypeName"));
                dataBean.setDeviceFactory(dataObj_i.getString("deviceFactory"));
                dataBean.setDeviceRegisterTime(dataObj_i.getString("deviceRegisterTime"));
                dataBean.setDeviceStatus(dataObj_i.getString("deviceStatus"));
//                  deviceParamsVal中的字段赋值
                dataBean.setCollectTime(deviceParamObj.getString("collectTime"));
                dataBean.setEnvirDataUnit(deviceParamObj.getString("envirDataUnit"));
                dataBean.setEnvirParamName(deviceParamObj.getString("envirParamName"));
                dataBean.setEnvirParamCode(deviceParamObj.getString("envirParamCode"));
                dataBean.setEnvirParamType(deviceParamObj.getString("envirParamType"));

                dataBeanList.add(dataBean);
            }
        }
        jsonBean.setData(dataBeanList);
//            System.out.println(dataBeanList.size());
//          将dataBeanList添加到envTypeList,并送到Dao层
        List<deviceList> deviceList = new ArrayList<>();
        try {
            for (int i = 0; i < jsonBean.getData().size(); i++) {

                deviceList deviceList_i = new deviceList();
                deviceList_i = deviceListInsert(jsonBean, i);
                deviceList.add(deviceList_i);
            }
        } catch (Exception e) {
            throw new RuntimeException("解析异常");
        }
        dd.insertDeviceList(table_name, deviceList);
        System.out.println("设备列表插入数据库！");

    }

    @Override
    public void insertEnvTypes() {
        JsonBean jsonBean = new JsonBean();
        String url = "http://111.207.242.123:10081/api/v1/iot/common/envTypes";

        JSONObject json = new JSONObject();
        json = fromurl(url);
        jsonBean.setMsg(json.optString("msg"));
        jsonBean.setCode(json.optString("code"));
        JSONObject data = json.getJSONObject("data");
        Iterator<String> keys = data.keys();
        List<JsonBean.DataBean> dataBeanList = new ArrayList<>();
        while (keys.hasNext()) {
            String key = keys.next();
            JsonBean.DataBean dataBean = new JsonBean.DataBean();
            dataBean.setEnvTypeName(data.getString(key));
            dataBean.setEnvType(key);
            dataBeanList.add(dataBean);
        }
        jsonBean.setData(dataBeanList);
//        System.out.println(dataBeanList.size()); 14条数据
        List<EnvTypes> envTypesList = new ArrayList<>();
        try {
            for (int i = 0; i < jsonBean.getData().size(); i++) {

                EnvTypes env_i = new EnvTypes();
                env_i = envTypesInsert(jsonBean, i);
                envTypesList.add(env_i);
            }
        } catch (Exception e) {
            throw new RuntimeException("解析异常");
        }
        dd.insertEnvTypes(envTypesList);
        System.out.println("环境类型插入数据库！");
    }

    public JSONObject fromurl(String url) {
        JSONObject json;
        try {
            URL httpurl = new URL(url);
            URLConnection urlConnection = httpurl.openConnection();
            HttpURLConnection connection = null;
            if (urlConnection instanceof HttpURLConnection) {
                connection = (HttpURLConnection) urlConnection;
            } else {
                System.out.println("输入urlַ");
            }
            connection.connect();

            //数据存入缓存区
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(connection.getInputStream()));
            String urlString = "";
            String current;
            while ((current = in.readLine()) != null) {
                urlString += current;
            }

            json = JSONObject.fromObject(urlString);

        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return json;
    }


    public JsonBean apiquery(String url) {
        JsonBean jsonBean = new JsonBean();
        try {
            URL httpurl = new URL(url);
            URLConnection urlConnection = httpurl.openConnection();
            HttpURLConnection connection = null;
            if (urlConnection instanceof HttpURLConnection)
            {
                connection = (HttpURLConnection) urlConnection;
            }
            else
            {
                System.out.println("输入urlַ");
                return jsonBean;
            }
            connection.connect();

            //数据存入缓存区
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(connection.getInputStream()));
            String urlString = "";
            String current;
            while ((current = in.readLine()) != null)
            {
                urlString += current;
            }

            JSONObject json = JSONObject.fromObject(urlString);
            jsonBean.setMsg(json.optString("msg"));
            jsonBean.setCode(json.optString("code"));
            JSONArray dataArray = json.optJSONArray("data");

            List<JsonBean.DataBean> dataBeanList = new ArrayList<>();

            for (int i = 0; i < dataArray.size(); i++ ){
                JSONObject dataObj = dataArray.optJSONObject(i);
                JsonBean.DataBean dataBean = new JsonBean.DataBean();
                dataBean.setEnvId(dataObj.getString("envId"));
                dataBean.setParentId(dataObj.getString("parentId"));
                dataBean.setEnvName(dataObj.getString("envName"));
                dataBean.setEnvType(dataObj.getString("envType"));
                dataBean.setEnvTypeName(dataObj.getString("envTypeName"));
                dataBean.setEnvCoverUrl(dataObj.getString("envCoverUrl"));

                dataBeanList.add(dataBean);
            }
            jsonBean.setData(dataBeanList);
            System.out.println("执行成功");
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return jsonBean;
    }

    public List<SanxiaData> getdatalist(JsonBean jsonBean){
        List<SanxiaData> mdlist = new ArrayList<>();
        try{
            for (int i = 0; i < jsonBean.getData().size(); i++) {

                SanxiaData sanxiadata = new SanxiaData();

                sanxiadata = dataListInsert(jsonBean, i);

                mdlist.add(sanxiadata);
            }

        } catch (Exception e) {
            throw new RuntimeException("解析异常");
        }

        return mdlist;
    }

    public static SanxiaData dataListInsert (JsonBean jsonBean, int i){
        SanxiaData sanxiadata = new SanxiaData();
        sanxiadata.setEnvId(jsonBean.getData().get(i).getEnvId());
        sanxiadata.setEnvType(jsonBean.getData().get(i).getEnvType());
        sanxiadata.setParentId(jsonBean.getData().get(i).getParentId());
        sanxiadata.setEnvCoverUrl(jsonBean.getData().get(i).getEnvCoverUrl());
        sanxiadata.setEnvName(jsonBean.getData().get(i).getEnvName());
        sanxiadata.setEnvTypeName(jsonBean.getData().get(i).getEnvTypeName());


        return sanxiadata;
    }

    public static EnvTypeMeta envTypeInsert(JsonBean jsonBean, int i){
        EnvTypeMeta envType = new EnvTypeMeta();
        envType.setEnvirDataUnit(jsonBean.getData().get(i).getEnvirDataUnit());
        envType.setEnvirParamName(jsonBean.getData().get(i).getEnvirParamName());
        envType.setEnvirParamCode(jsonBean.getData().get(i).getEnvirParamCode());
        envType.setEnvirParamType(jsonBean.getData().get(i).getEnvirParamType());

        return envType;
    }

    public static deviceList deviceListInsert(JsonBean jsonBean, int i){
        deviceList devicelist = new deviceList();
//      一共14个字段：
        devicelist.setDeviceId(jsonBean.getData().get(i).getDeviceId());
        devicelist.setDeviceName(jsonBean.getData().get(i).getDeviceName());
        devicelist.setEnvId(jsonBean.getData().get(i).getEnvId());
        devicelist.setEnvName(jsonBean.getData().get(i).getEnvName());
        devicelist.setDeviceType(jsonBean.getData().get(i).getDeviceType());
        devicelist.setDeviceTypeName(jsonBean.getData().get(i).getDeviceTypeName());
        devicelist.setDeviceFactory(jsonBean.getData().get(i).getDeviceFactory());
        devicelist.setCollectTime(jsonBean.getData().get(i).getCollectTime());
        devicelist.setEnvirDataUnit(jsonBean.getData().get(i).getEnvirDataUnit());
        devicelist.setEnvirParamName(jsonBean.getData().get(i).getEnvirParamName());
        devicelist.setEnvirParamCode(jsonBean.getData().get(i).getEnvirParamCode());
        devicelist.setEnvirParamType(jsonBean.getData().get(i).getEnvirParamType());
        devicelist.setDeviceRegisterTime(jsonBean.getData().get(i).getDeviceRegisterTime());
        devicelist.setDeviceStatus(jsonBean.getData().get(i).getDeviceStatus());

        return devicelist;
    }

    public static EnvTypes envTypesInsert(JsonBean jsonBean, int i){
        EnvTypes env = new EnvTypes();
        env.setEnvType(jsonBean.getData().get(i).getEnvType());
        env.setEnvTypeName(jsonBean.getData().get(i).getEnvTypeName());
        return env;
    }

    public static EnvData envDataInsert(JsonBean jsonBean, int i){
        EnvData envData = new EnvData();
        envData.setEnvId(jsonBean.getData().get(i).getEnvId());
        envData.setEnvName(jsonBean.getData().get(i).getEnvName());
        envData.setEnvType(jsonBean.getData().get(i).getEnvType());
        envData.setEnvTypeName(jsonBean.getData().get(i).getEnvTypeName());
        envData.setParentId(jsonBean.getData().get(i).getParentId());
        envData.setEnvCoverUrl(jsonBean.getData().get(i).getEnvCoverUrl());
        envData.setCollectTime(jsonBean.getData().get(i).getCollectTime());
        envData.setEnvirParamType(jsonBean.getData().get(i).getEnvirParamType());
        envData.setEnvirParamValue(jsonBean.getData().get(i).getEnvirParamValue());

        return envData;
    }

}
