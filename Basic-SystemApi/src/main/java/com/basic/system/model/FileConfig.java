package com.basic.system.model;

import java.io.Serializable;

public class FileConfig  implements Serializable{

	private static final long serialVersionUID = 441821007671401068L;

	private Long fileConfigId;

    private String type;

    private String ip;

    private Long port;

    private String storeDir;

    private String username;

    private String password;

    private String moduleCode;

    public Long getFileConfigId() {
        return fileConfigId;
    }

    public void setFileConfigId(Long fileConfigId) {
        this.fileConfigId = fileConfigId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Long getPort() {
        return port;
    }

    public void setPort(Long port) {
        this.port = port;
    }

    public String getStoreDir() {
        return storeDir;
    }

    public void setStoreDir(String storeDir) {
        this.storeDir = storeDir;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getModuleCode() {
        return moduleCode;
    }

    public void setModuleCode(String moduleCode) {
        this.moduleCode = moduleCode;
    }
}