package com.basic.system.model;

import java.io.Serializable;

public class GidServer implements Serializable {
	
	private static final long serialVersionUID = -8340702771486854856L;

	private String gidCode;

    private String gidName;

    private Integer isUse;

    private Long currValue;

    private Integer cacheSize;

    private Integer clientCacheSize;

    private Integer increamentBy;

    private String remark;

    private String pageDateType;//新增：CREATE;修改：UPDATE
    
    public String getGidCode() {
        return gidCode;
    }

    public void setGidCode(String gidCode) {
        this.gidCode = gidCode;
    }

    public String getGidName() {
        return gidName;
    }

    public void setGidName(String gidName) {
        this.gidName = gidName;
    }

    public Integer getIsUse() {
        return isUse;
    }

    public void setIsUse(Integer isUse) {
        this.isUse = isUse;
    }

    public Long getCurrValue() {
        return currValue;
    }

    public void setCurrValue(Long currValue) {
        this.currValue = currValue;
    }

    public Integer getCacheSize() {
        return cacheSize;
    }

    public void setCacheSize(Integer cacheSize) {
        this.cacheSize = cacheSize;
    }

    public Integer getClientCacheSize() {
        return clientCacheSize;
    }

    public void setClientCacheSize(Integer clientCacheSize) {
        this.clientCacheSize = clientCacheSize;
    }

    public Integer getIncreamentBy() {
        return increamentBy;
    }

    public void setIncreamentBy(Integer increamentBy) {
        this.increamentBy = increamentBy;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

	public String getPageDateType() {
		return pageDateType;
	}

	public void setPageDateType(String pageDateType) {
		this.pageDateType = pageDateType;
	}
}