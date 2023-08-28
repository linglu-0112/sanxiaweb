package com.sanxia.data.pojo;
import java.util.List;

public class JsonBean {
    private String msg;
    private String code;
    private List<DataBean> data;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

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

        private String envirDataUnit;

        private String envirParamName;

        private String envirParamCode;

        private String envirParamType;

        public String getEnvId() {
            return envId;
        }

        public void setEnvId(String envId) {
            this.envId = envId;
        }

        public String getParentId() {
            return parentId;
        }

        public void setParentId(String parentId) {
            this.parentId = parentId;
        }

        public String getEnvName() {
            return envName;
        }

        public void setEnvName(String envName) {
            this.envName = envName;
        }

        public int getEnvType() {
            return envType;
        }

        public void setEnvType(int envType) {
            this.envType = envType;
        }

        public String getEnvTypeName() {
            return envTypeName;
        }

        public void setEnvTypeName(String envTypeName) {
            this.envTypeName = envTypeName;
        }

        public String getEnvCoverUrl() {
            return envCoverUrl;
        }

        public void setEnvCoverUrl(String envCoverUrl) {
            this.envCoverUrl = envCoverUrl;
        }

        public String getEnvirDataUnit() {return envirDataUnit;}

        public void setEnvirDataUnit(String envirDataUnit){this.envirDataUnit = envirDataUnit;}

        public String getEnvirParamName(){return envirParamName;}

        public void setEnvirParamName(String envirParamName){this.envirParamName = envirParamName;}

        public String getEnvirParamCode(){return envirParamCode;}

        public void setEnvirParamCode(String envirParamCode){this.envirParamCode = envirParamCode;}

        public String getEnvirParamType(){return envirParamType;}

        public void setEnvirParamType(String envirParamType) {this.envirParamType = envirParamType;}
    }
}
