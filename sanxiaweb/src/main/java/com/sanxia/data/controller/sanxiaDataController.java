package com.sanxia.data.controller;

import com.sanxia.data.service.sanxiaDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
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

    @RequestMapping("/insertMuseumData")
    public String insertMuseumData(){
        try{
            sds.insertSanxiaData();
            System.out.println("执行成功！");
        }catch(Exception e)
        {   e.printStackTrace();
            return "error";}
        // 控制跳转
        return "ok";
    }

}
