package com.sanxia.data.pojo;

import java.util.List;

public class insertSXData {
    private String locationID;
    private String parentId;
    private String locationName;
    private String envType;
    private String envTypeName;
    private String envCoverUrl;
    private String timestamps;
    private String sensorPhysicalID;
    private String sensorPhysicalValue;
    private String units;
    private String cnName;
    public String getLocationID() {
        return locationID;
    }
    public void setLocationID(String locationID) {
        this.locationID = locationID;
    }
    public String getParentId() {
        return parentId;
    }
    public void setParentId(String parentId) {
        this.parentId = parentId;
    }
    public String getLocationName() {
        return locationName;
    }
    public void setLocationName(String locationName) {
        this.locationName = locationName;
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
    public String getTimestmaps() {
        return timestamps;
    }
    public void setTimestmaps(String timestamps) {
        this.timestamps = timestamps;
    }
    public String getSensorPhysicalID() {
        return sensorPhysicalID;
    }
    public void setSensorPhysicalID(String sensorPhysicalID) {
        this.sensorPhysicalID = sensorPhysicalID;
    }
    public String getSensorPhysicalValue() {
        return sensorPhysicalValue;
    }
    public void setSensorPhysicalValue(String sensorPhysicalValue) {
        this.sensorPhysicalValue = sensorPhysicalValue;
    }
    public String getUnits() {
        return units;
    }
    public void setUnits(String units) {
        this.units = units;
    }
    public String getCnName() {
        return cnName;
    }
    public void setCnName(String cnName) {
        this.cnName = cnName;
    }

    private List<DataBean> data;
    public List<DataBean> getData() {
        return data;
    }
    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean{
        private String locationID;
        private String parentId;
        private String locationName;
        private String envType;
        private String envTypeName;
        private String envCoverUrl;
        private String timestmaps;
        private String sensorPhysicalID;
        private String sensorPhysicalValue;
        private String units;
        private String cnName;
        public String getLocationID() {
            return locationID;
        }
        public void setLocationID(String locationID) {
            this.locationID = locationID;
        }
        public String getParentId() {
            return parentId;
        }
        public void setParentId(String parentId) {
            this.parentId = parentId;
        }
        public String getLocationName() {
            return locationName;
        }
        public void setLocationName(String locationName) {
            this.locationName = locationName;
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
        public String getTimestmaps() {
            return timestmaps;
        }
        public void setTimestmaps(String timestmaps) {
            this.timestmaps = timestmaps;
        }
        public String getSensorPhysicalID() {
            return sensorPhysicalID;
        }
        public void setSensorPhysicalID(String sensorPhysicalID) {
            this.sensorPhysicalID = sensorPhysicalID;
        }
        public String getSensorPhysicalValue() {
            return sensorPhysicalValue;
        }
        public void setSensorPhysicalValue(String sensorPhysicalValue) {
            this.sensorPhysicalValue = sensorPhysicalValue;
        }
        public String getUnits() {
            return units;
        }
        public void setUnits(String units) {
            this.units = units;
        }
        public String getCnName() {
            return cnName;
        }
        public void setCnName(String cnName) {
            this.cnName = cnName;
        }

    }
}
