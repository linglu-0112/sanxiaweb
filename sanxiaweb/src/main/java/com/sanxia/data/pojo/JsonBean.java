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
        
        private String relicId;

        private String updateDate;

        private String museumName;

        private String relicCode;

        private String relicName;

        private String relicYears;

        private String relicLevel;

        private String relicTexture;

        private String relicState;

        private String relicImage;

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

        public String getRelicId() {
            return relicId;
        }
    
        public void setRelicId(String relicId) {
            this.relicId = relicId;
        }
    
        public String getUpdateDate() {
            return updateDate;
        }
    
        public void setUpdateDate(String updateDate) {
            this.updateDate = updateDate;
        }
    
        public String getMuseumName() {
            return museumName;
        }
    
        public void setMuseumName(String museumName) {
            this.museumName = museumName;
        }
    
        public String getRelicCode() {
            return relicCode;
        }
    
        public void setRelicCode(String relicCode) {
            this.relicCode = relicCode;
        }
    
        public String getRelicName() {
            return relicName;
        }
    
        public void setRelicName(String relicName) {
            this.relicName = relicName;
        }
    
        public String getRelicYears() {
            return relicYears;
        }
    
        public void setRelicYears(String relicYears) {
            this.relicYears = relicYears;
        }
    
        public String getRelicLevel() {
            return relicLevel;
        }
    
        public void setRelicLevel(String relicLevel) {
            this.relicLevel = relicLevel;
        }
    
        public String getRelicTexture() {
            return relicTexture;
        }
    
        public void setRelicTexture(String relicTexture) {
            this.relicTexture = relicTexture;
        }
    
        public String getRelicState() {
            return relicState;
        }
    
        public void setRelicState(String relicState) {
            this.relicState = relicState;
        }
    
        public String getRelicImage() {
            return relicImage;
        }
    
        public void setRelicImage(String relicImage) {
            this.relicImage = relicImage;
        }
    }
}
