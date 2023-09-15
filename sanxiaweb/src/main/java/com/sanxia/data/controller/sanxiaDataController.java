package com.sanxia.data.controller;

import com.sanxia.data.service.sanxiaDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
@Component

public class sanxiaDataController {
    @Autowired
    private sanxiaDataService sds;

    @RequestMapping("/hello")
    public String hello(){
        System.out.println("hello world");
        return "hello world!";
    }

//  插入sanxia_museum_levelInfo表
    @RequestMapping("/insertMuseumData/{TableName}")
    public String insertEnv(@PathVariable String TableName){
        try{
            sds.insertEnv(TableName);
            System.out.println("执行成功！");
        }catch(Exception e)
        {   e.printStackTrace();
            return "error";}
        // 控制跳转
        return "ok";
    }

    @RequestMapping("/insertEnvtypeMeta/{TableName}")
    public String insertEnvtype(@PathVariable String TableName){
        try{
            sds.insertEnvTypeMeta(TableName);
            System.out.println("输入envtype数据成功");
        }catch(Exception e)
        {   e.printStackTrace();
            return "error";}
        return "输入envTypeMetadata数据成功";
    }

    @RequestMapping("/insertDevicelist/{TableName}")
    public String insertDeviceList(@PathVariable String TableName){
        try{
            sds.insertDeviceList(TableName);
            System.out.println("输入device列表数据成功");
        }catch(Exception e)
        {   e.printStackTrace();
            return "error";}
        return "输入device列表数据成功";
    }

    @RequestMapping("/insertEnvTypes")
    public String insertEnvTypes(){
        try{
            sds.insertEnvTypes();
            System.out.println("输入环境类型数据成功");
        }catch(Exception e)
        {   e.printStackTrace();
            return "error";}
        return "插入环境类型数据成功";
    }

    @RequestMapping("/insertEnvData/{selectTable}/{table_name}")
    public String insertEnvData(@PathVariable String selectTable,@PathVariable String table_name){
        try{
            sds.insertEnvData(selectTable,table_name);

        }catch(Exception e)
        {   e.printStackTrace();
            return "error";}
        return "输入保存环境空间数据成功！";
    }

    @RequestMapping("/insertEnvDataBytime/{selectTable}/{table_name}")
    public String insertEnvDataBytime(@PathVariable String selectTable,@PathVariable String table_name){
        try{
            sds.insertEnvDataBytime(selectTable,table_name);

        }catch(Exception e)
        {   e.printStackTrace();
            return "error";}
        return "输入保存环境空间数据成功！";
    }
    @RequestMapping("/insertRelicData/{table_name}/{envId}")
    public String insertRelicData(@PathVariable String table_name,@PathVariable String envId){
        try{
            sds.insertRelicData(envId,table_name);
        }catch(Exception e)
        {   e.printStackTrace();
            return "error";}
        return "输入文物数据成功";
    }
//    插入sanxia_Data表
    @RequestMapping("/insertSanxiaData/{selectTable}/{table_name}")
    public String insertSanxiaData(@PathVariable String selectTable,@PathVariable String table_name){
        try{
            sds.insertSanxiaData(selectTable,table_name);
        }catch(Exception e)
        {   e.printStackTrace();
            return "error";}
        return "输入三峡数据成功";
    }
//  插入sanxia_exhibition表
    @RequestMapping("/insertSanxiaEx/{table_name}/{envId}")
    public String insertSanxiaEx(@PathVariable String table_name,@PathVariable String envId){
        try {
            sds.insertSanxiaEx(envId,table_name);
        }catch(Exception e)
        {   e.printStackTrace();
            return "error";}
        return "输入sanxia_exhibition成功";
    }

}
