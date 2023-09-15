package com.sanxia.data.pojo;

import java.util.List;

public class sanxiaExh {
    private String locationID;
    private String locationName;
    private String culturalRelicID;
    private String texture;
    private String culturalRelicLevel;
    private String culturalRelic;
    private String zoneID;
    public String getCulturalRelic() {
        return culturalRelic;
    }

    public void setCulturalRelic(String culturalRelic) {
        this.culturalRelic = culturalRelic;
    }

    public String getZoneID() {
        return zoneID;
    }

    public void setZoneID(String zoneID) {
        this.zoneID = zoneID;
    }

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public String getLocationID() {
        return locationID;
    }

    public void setLocationID(String locationID) {
        this.locationID = locationID;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public String getCulturalRelicID() {
        return culturalRelicID;
    }

    public void setCulturalRelicID(String culturalRelicID) {
        this.culturalRelicID = culturalRelicID;
    }

    public String getTexture() {
        return texture;
    }

    public void setTexture(String texture) {
        this.texture = texture;
    }

    public String getCulturalRelicLevel() {
        return culturalRelicLevel;
    }

    public void setCulturalRelicLevel(String culturalRelicLevel) {
        this.culturalRelicLevel = culturalRelicLevel;
    }

    public static class DataBean{
        private String locationID;
        private String locationName;
        private String culturalRelicID;
        private String texture;
        private String culturalRelicLevel;
        private String culturalRelic;
        private String zoneID;
        public String getCulturalRelic() {
            return culturalRelic;
        }

        public void setCulturalRelic(String culturalRelic) {
            this.culturalRelic = culturalRelic;
        }

        public String getZoneID() {
            return zoneID;
        }

        public void setZoneID(String zoneID) {
            this.zoneID = zoneID;
        }

        public String getLocationID() {
            return locationID;
        }

        public void setLocationID(String locationID) {
            this.locationID = locationID;
        }

        public String getLocationName() {
            return locationName;
        }

        public void setLocationName(String locationName) {
            this.locationName = locationName;
        }

        public String getCulturalRelicID() {
            return culturalRelicID;
        }

        public void setCulturalRelicID(String culturalRelicID) {
            this.culturalRelicID = culturalRelicID;
        }

        public String getTexture() {
            return texture;
        }

        public void setTexture(String texture) {
            this.texture = texture;
        }

        public String getCulturalRelicLevel() {
            return culturalRelicLevel;
        }

        public void setCulturalRelicLevel(String culturalRelicLevel) {
            this.culturalRelicLevel = culturalRelicLevel;
        }
    }
}
