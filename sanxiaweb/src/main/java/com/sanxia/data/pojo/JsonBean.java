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
        private String envType;
        private String envTypeName;
        private String envCoverUrl;

        private String envirDataUnit;

        private String envirParamName;

        private String envirParamCode;

        private String envirParamType;

        private String envirParamValue;

        private String deviceId;

        private String deviceName;

        private String deviceType;

        private String deviceTypeName;

        private String deviceFactory;

        private String collectTime;

        private String deviceRegisterTime;

        private String deviceStatus;

        public String getDeviceId() {
            return deviceId;
        }

        public void setDeviceId(String deviceId) {
            this.deviceId = deviceId;
        }

        public String getDeviceName() {
            return deviceName;
        }

        public void setDeviceName(String deviceName) {
            this.deviceName = deviceName;
        }

        public String getDeviceType() {
            return deviceType;
        }

        public void setDeviceType(String deviceType) {
            this.deviceType = deviceType;
        }

        public String getDeviceTypeName() {
            return deviceTypeName;
        }

        public void setDeviceTypeName(String deviceTypeName) {
            this.deviceTypeName = deviceTypeName;
        }

        public String getDeviceFactory() {
            return deviceFactory;
        }

        public void setDeviceFactory(String deviceFactory) {
            this.deviceFactory = deviceFactory;
        }

        public String getCollectTime() {
            return collectTime;
        }

        public void setCollectTime(String collectTime) {
            this.collectTime = collectTime;
        }

        public String getDeviceRegisterTime() {
            return deviceRegisterTime;
        }

        public void setDeviceRegisterTime(String deviceRegisterTime) {
            this.deviceRegisterTime = deviceRegisterTime;
        }

        public String getDeviceStatus() {
            return deviceStatus;
        }

        public void setDeviceStatus(String deviceStatus) {
            this.deviceStatus = deviceStatus;
        }

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

        public String getEnvType() {
            return envType;
        }

        public void setEnvType(String envType) {
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

        public String getEnvirParamValue() {return envirParamValue;}

        public void setEnvirParamValue(String envirParamValue) {this.envirParamValue = envirParamValue;}
    }
}
