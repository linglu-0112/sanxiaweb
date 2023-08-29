package com.sanxia.data.service;

import com.sanxia.data.Dao.DataDao;
import com.sanxia.data.pojo.EnvType;
import com.sanxia.data.pojo.JsonBean;
import com.sanxia.data.pojo.SanxiaData;
import com.sanxia.data.pojo.deviceList;
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
public class sanxiaDataServiceImpl implements sanxiaDataService{
    @Autowired
    private DataDao dd;


    @Override
    public void insertSanxiaData(){
        JsonBean jsonBean = new JsonBean();
        String url = "http://111.207.242.123:10081/api/v1/iot/50010301/env/findList?envId=";
        jsonBean = apiquery(url);
        List<SanxiaData> sanxiaDatalist = new ArrayList<>();
        if (jsonBean.getData().size()!=0){
            sanxiaDatalist = getdatalist(jsonBean);
            dd.insertSanxiaData(sanxiaDatalist);
            System.out.println("执行成功");
        }else {
            System.out.print( " 位置点无数据"+'\n');
        }

    }

    public void insertEnvType(String table_name){
        JsonBean jsonBean = new JsonBean();
        String url = "http://111.207.242.123:10081/api/v1/iot/common/metaData";

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

//          从url获取json数据
            JSONObject json = JSONObject.fromObject(urlString);
            jsonBean.setMsg(json.optString("msg"));
            jsonBean.setCode(json.optString("code"));
            JSONObject data = json.getJSONObject("data");
//          解析json数据，获得dataBeanList
            Iterator<String> keys = data.keys();
            List<JsonBean.DataBean> dataBeanList = new ArrayList<>();
            while (keys.hasNext()){
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
            List<EnvType> envTypeList = new ArrayList<>();
            try{
                for (int i = 0; i < jsonBean.getData().size(); i++) {

                    EnvType envType = new EnvType();

                    envType = envTypeInsert(jsonBean, i);

                    envTypeList.add(envType);
                }
            } catch (Exception e) {
                throw new RuntimeException("解析异常");
            }
            dd.insertEnvType(table_name,envTypeList);
            System.out.println("获得envTypeList，执行成功");
            System.out.println(envTypeList);

        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    public void insertDeviceList(String table_name) {
        JsonBean jsonBean = new JsonBean();
        String url = "http://111.207.242.123:10081/api/v1/iot/50010301/devices/findList";

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
            //          从url获取json数据
            JSONObject json = JSONObject.fromObject(urlString);
            jsonBean.setMsg(json.optString("msg"));
            jsonBean.setCode(json.optString("code"));
            JSONArray dataArray = json.optJSONArray("data");
            //          解析json数据，获得dataBeanList
            List<JsonBean.DataBean> dataBeanList = new ArrayList<>();
            for (int i = 0; i < dataArray.size(); i++){
                JSONObject dataObj_i = dataArray.optJSONObject(i);
                JSONObject data = dataObj_i.optJSONObject("deviceParamsVal");
                Iterator<String> keys = data.keys();
                while (keys.hasNext()){
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
            try{
                for (int i = 0; i < jsonBean.getData().size(); i++) {

                    deviceList deviceList_i = new deviceList();
                    deviceList_i = deviceListInsert(jsonBean, i);
                    deviceList.add(deviceList_i);
                }
            } catch (Exception e) {
                throw new RuntimeException("解析异常");
            }
            dd.insertDeviceList(table_name,deviceList);
            System.out.println("设备列表插入数据库！");
        }catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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
                dataBean.setEnvType(dataObj.getInt("envType"));
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

    public static EnvType envTypeInsert(JsonBean jsonBean, int i){
        EnvType envType = new EnvType();
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


}
