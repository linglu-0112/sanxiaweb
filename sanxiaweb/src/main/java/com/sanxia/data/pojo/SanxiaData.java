package com.sanxia.data.pojo;

public class SanxiaData {
    private String envId;
    private String parentId;
    private String envName;
    private String envType;
    private String envTypeName;
    private String envCoverUrl;

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

    public void data(String envId, String parentId, String envName, String envType, String envTypeName,
                String envCoverUrl){
        this.envId = envId;
        this.parentId = parentId;
        this.envName = envName;
        this.envType = envType;
        this.envTypeName = envTypeName;
        this.envCoverUrl = envCoverUrl;
    }
}
