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
import java.io.UnsupportedEncodingException;
import java.net.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
@Service
@Component
@Transactional
public class sanxiaDataServiceImpl implements sanxiaDataService {
    @Autowired
    private DataDao dd;

// 插入sanxia_exihibition
    @Override
    public void insertSanxiaEx(String envId, String table_name){
        String table = "sanxia_museum_levelInfor";
        List<envLevel> envLevels = dd.selectEnvLevel(table,envId); //envId就是num
        JsonBean jsonBean = new JsonBean();
        sanxiaExh sanxiaExh = new sanxiaExh();
        List<sanxiaExh.DataBean> dataBeanList = new ArrayList<>();
        String url = "http://111.207.242.123:9888/collection-plus/relicUnit/findList?page=1&size=10&envId=";
        String Url = url + envId;
        JSONObject json = new JSONObject();
        json = fromurl(Url);
        jsonBean.setMsg(json.optString("msg"));
        jsonBean.setCode(json.optString("code"));
        JSONObject data = json.optJSONObject("data");
        JSONArray dataArray = data.optJSONArray("relicUnitList");
        for(int i = 0; i < dataArray.size(); i++){
            sanxiaExh.DataBean dataBean = new sanxiaExh.DataBean();
            JSONObject data_i = dataArray.optJSONObject(i);
            dataBean.setLocationID(envLevels.get(0).getNum());
            dataBean.setLocationName(envLevels.get(0).getCnName());
            dataBean.setZoneID(envLevels.get(0).getParentID());
            dataBean.setCulturalRelic(data_i.getString("relicName"));
            dataBean.setCulturalRelicID(data_i.getString("relicId"));   
            dataBean.setTexture(data_i.getString("relicTexture"));
            dataBean.setCulturalRelicLevel(data_i.getString("relicLevel"));

            dataBeanList.add(dataBean);
        }
        sanxiaExh.setData(dataBeanList);
        List<sanxiaExh> sanxiaExhLists = new ArrayList<>();
        for(int i = 0 ; i < sanxiaExh.getData().size(); i++){
            sanxiaExh sanxiaExh1 = new sanxiaExh();
            sanxiaExh1 = sanxiaExhIsert(sanxiaExh,i);
            sanxiaExhLists.add(sanxiaExh1);
        }
        System.out.println("数据插入到sanxia_exhibition中,一共"+sanxiaExhLists.size()+"条数据");
        dd.insertSanxiaExList(table_name,sanxiaExhLists);
    }

    // 插入sanxia_Data
    @Override
    public void insertSanxiaData(String select_table, String table_name) {
        List<EnvData> EnvDatas = dd.selecEnvData(select_table);
        List<insertSXData.DataBean> SxDataBean = new ArrayList<>();
        insertSXData sxData = new insertSXData();
        for(int i = 0; i < EnvDatas.size(); i++){
            insertSXData.DataBean dataBean = new insertSXData.DataBean();
            dataBean.setLocationID(EnvDatas.get(i).getEnvId());
            dataBean.setParentId(EnvDatas.get(i).getParentId());
            dataBean.setLocationName(EnvDatas.get(i).getEnvName());
            dataBean.setEnvType(EnvDatas.get(i).getEnvType());
            dataBean.setEnvTypeName(EnvDatas.get(i).getEnvTypeName());
            dataBean.setEnvCoverUrl(EnvDatas.get(i).getEnvCoverUrl());
            dataBean.setTimestmaps(EnvDatas.get(i).getCollectTime());
            String flag = EnvDatas.get(i).getEnvirParamType();
            if(flag.equals("01")){
                dataBean.setSensorPhysicalID("32");
                dataBean.setSensorPhysicalValue(Double.parseDouble(EnvDatas.get(i).getEnvirParamValue()));
                dataBean.setUnits("%");
                dataBean.setCnName("湿度");
            }else if(flag.equals("02")){
                dataBean.setSensorPhysicalID("33");
                dataBean.setSensorPhysicalValue(Double.parseDouble(EnvDatas.get(i).getEnvirParamValue()));
                dataBean.setUnits("℃");
                dataBean.setCnName("温度");
            }
            SxDataBean.add(dataBean);
        }
        sxData.setData(SxDataBean);
        List<insertSXData> sxDataList = new ArrayList<>();
        for(int i = 0; i < sxData.getData().size(); i++){
                insertSXData sxData_i = new insertSXData();
                sxData_i = sxDataInsert(sxData,i);
                sxDataList.add(sxData_i);
            }
            dd.insertSanxiaData(table_name, sxDataList);
    
    }

    private insertSXData sxDataInsert(insertSXData sxData, int i) {
        insertSXData sanxiaData = new insertSXData();
        sanxiaData.setLocationID(sxData.getData().get(i).getLocationID());
        sanxiaData.setParentId(sxData.getData().get(i).getParentId());
        sanxiaData.setLocationName(sxData.getData().get(i).getLocationName());
        sanxiaData.setEnvType(sxData.getData().get(i).getEnvType());
        sanxiaData.setEnvTypeName(sxData.getData().get(i).getEnvTypeName());
        sanxiaData.setEnvCoverUrl(sxData.getData().get(i).getEnvCoverUrl());
        sanxiaData.setTimestmaps(sxData.getData().get(i).getTimestmaps());
        sanxiaData.setSensorPhysicalID(sxData.getData().get(i).getSensorPhysicalID());
        sanxiaData.setSensorPhysicalValue(sxData.getData().get(i).getSensorPhysicalValue());
        sanxiaData.setUnits(sxData.getData().get(i).getUnits());
        sanxiaData.setCnName(sxData.getData().get(i).getCnName());

        return sanxiaData;
    }

    @Override
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
    public void insertEnvDataBytime(String select_table, String table_name) throws UnsupportedEncodingException {
        List<SanxiaData> sData = dd.selectSanxia(select_table);
        JsonBean jsonBean = new JsonBean();
        List<JsonBean.DataBean> dataBeanList = new ArrayList<>();
        // 拼接url，需要计算开始和结束时间（过去一天的零点和24点）
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sj = new SimpleDateFormat("yyyy-MM-dd");
        calendar.add(Calendar.DATE, -1); //当前时间减去一天，即一天前的时间
        String str = sj.format(calendar.getTime());
        String startTime = str + URLEncoder.encode(" ", "utf-8") + "01:00:00";  // 空格需要编码才能识别
        System.out.print(startTime+'\n');
        for (int i = 0; i < sData.size(); i++){
            String s = sData.get(i).getEnvId();
//            System.out.println(s);
            String url = "http://111.207.242.123:10081/api/v1/iot/50010301/env/data?envId=";
            JSONObject json = new JSONObject();
            String Url = url + s + "&startDateTime=" + startTime;
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
            dd.insertEnvDataBytime(table_name,envDataList);
        } catch (Exception e) {
            throw new RuntimeException("解析异常");
        }

    }

    @Override
    public void insertRelicData(String envId, String table_name) {
        String table = "sanxia_museum_levelInfor";
        List<envLevel> envLevels = dd.selectEnvLevel(table,envId);
        JsonBean jsonBean = new JsonBean();
        List<JsonBean.DataBean> dataBeanList = new ArrayList<>();
        String url = "http://111.207.242.123:9888/collection-plus/relicUnit/findList?page=1&size=10&envId=";
        String Url = url + envId;
        JSONObject json = new JSONObject();
        json = fromurl(Url);
        jsonBean.setMsg(json.optString("msg"));
        jsonBean.setCode(json.optString("code"));
        JSONObject data = json.optJSONObject("data");
        JSONArray dataArray = data.optJSONArray("relicUnitList");
        for(int i = 0; i < dataArray.size(); i++){
            JsonBean.DataBean dataBean = new JsonBean.DataBean();
            JSONObject data_i = dataArray.optJSONObject(i);
            dataBean.setRelicId(data_i.getString("relicId"));
            dataBean.setUpdateDate(data_i.getString("updateDate"));
            dataBean.setMuseumName(data_i.getString("museumName"));
            dataBean.setRelicCode(data_i.getString("relicCode"));
            dataBean.setRelicName(data_i.getString("relicName"));
            dataBean.setRelicYears(data_i.getString("relicYears"));
            dataBean.setRelicLevel(data_i.getString("relicLevel"));
            dataBean.setRelicTexture(data_i.getString("relicTexture"));
            dataBean.setRelicState(data_i.getString("relicState"));
            dataBean.setRelicImage(data_i.getString("relicImage"));
            dataBean.setEnvId(envLevels.get(0).getNum());
            dataBean.setEnvName(envLevels.get(0).getCnName());
            // dataBean.setEnvType(envLevels.get(0).getEnvType()); // envLevel中没有envType

            dataBeanList.add(dataBean);

        }
        jsonBean.setData(dataBeanList);
//        System.out.println(jsonBean.getData().size());
//        System.out.println(jsonBean.getData().get(0).getRelicId());
        List<RelicList> relicLists = new ArrayList<>();
        try {
            for (int i = 0; i < jsonBean.getData().size(); i++) {

                RelicList relicData = new RelicList();

                relicData = RelicDataInsert(jsonBean,i);

                relicLists.add(relicData);
            }
            System.out.println("数据插入到envDataList中,一共"+relicLists.size()+"条数据");
            dd.insertRelicList(table_name,relicLists);
        } catch (Exception e) {
            throw new RuntimeException("解析异常");
        }


    }

// 插入sanxia_museum_levelInfor
    @Override
    public void insertEnv() {
        JsonBean jsonBean = new JsonBean();
        String url = "http://111.207.242.123:10081/api/v1/iot/50010301/env/findList?envId=";
        jsonBean = apiquery(url);
        List<SanxiaData> sanxiaDatalist = new ArrayList<>();
        if (jsonBean.getData().size() != 0) {
            sanxiaDatalist = getdatalist(jsonBean);
            dd.insertEnv(sanxiaDatalist);
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
    public static RelicList RelicDataInsert(JsonBean jsonBean, int i){
        RelicList relicList = new RelicList();
        relicList.setEnvId(jsonBean.getData().get(i).getEnvId());
        relicList.setEnvName(jsonBean.getData().get(i).getEnvName());
        relicList.setEnvType(jsonBean.getData().get(i).getEnvType());
        relicList.setRelicId(jsonBean.getData().get(i).getRelicId());
        relicList.setUpdateDate(jsonBean.getData().get(i).getUpdateDate());
        relicList.setMuseumName(jsonBean.getData().get(i).getMuseumName());
        relicList.setRelicCode(jsonBean.getData().get(i).getRelicCode());
        relicList.setRelicName(jsonBean.getData().get(i).getRelicName());
        relicList.setRelicYears(jsonBean.getData().get(i).getRelicYears());
        relicList.setRelicLevel(jsonBean.getData().get(i).getRelicLevel());
        relicList.setRelicTexture(jsonBean.getData().get(i).getRelicTexture());
        relicList.setRelicState(jsonBean.getData().get(i).getRelicState());
        relicList.setRelicImage(jsonBean.getData().get(i).getRelicImage());

        return relicList;
    }
    public static sanxiaExh sanxiaExhIsert(sanxiaExh sanxiaExh, int i){
        sanxiaExh sanxiaExh1 = new sanxiaExh();
        sanxiaExh1.setLocationID(sanxiaExh.getData().get(i).getLocationID());
        sanxiaExh1.setLocationName(sanxiaExh.getData().get(i).getLocationName());
        sanxiaExh1.setTexture(sanxiaExh.getData().get(i).getTexture());
        sanxiaExh1.setCulturalRelicID(sanxiaExh.getData().get(i).getCulturalRelicID());
        sanxiaExh1.setCulturalRelicLevel(sanxiaExh.getData().get(i).getCulturalRelicLevel());
        sanxiaExh1.setZoneID(sanxiaExh.getData().get(i).getZoneID());
        sanxiaExh1.setCulturalRelic(sanxiaExh.getData().get(i).getCulturalRelic());

        return sanxiaExh1;
    }

}
