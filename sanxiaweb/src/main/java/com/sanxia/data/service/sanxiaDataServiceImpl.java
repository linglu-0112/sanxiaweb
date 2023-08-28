package com.sanxia.data.service;

import com.sanxia.data.Dao.DataDao;
import com.sanxia.data.pojo.EnvType;
import com.sanxia.data.pojo.JsonBean;
import com.sanxia.data.pojo.SanxiaData;
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
            String str = "data";
            JSONObject json = JSONObject.fromObject(urlString);
            jsonBean.setMsg(json.optString("msg"));
            jsonBean.setCode(json.optString("code"));
            JSONObject data = json.getJSONObject("data");
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


}
