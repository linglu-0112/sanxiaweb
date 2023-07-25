package com.sanxia.data.pojo;
import java.util.List;

public class JsonBean {
    private List<DataBean> data;
    public  List<DataBean> getData(){
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }
    //Data数据封装
    public static class DataBean{
        private String envId;
        private String parentId;
        private String envName;
        private int envType;
        private String envTypeName;
        private String envCoverUrl;
        
    }
}
