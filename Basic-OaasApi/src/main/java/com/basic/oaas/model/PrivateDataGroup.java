package com.basic.oaas.model;

import java.io.Serializable;

/**
 * 
 *
 * @date 2017年9月13日 上午10:22:08
 * @author Kevin
 * @Description: 数据权限分组
 *
 */
public class PrivateDataGroup implements Serializable {
	
	private static final long serialVersionUID = 998558045194816963L;

	private Long dataGroupId;

    private String dataGroupName;

    private String dataGroupCode;

    public Long getDataGroupId() {
        return dataGroupId;
    }

    public void setDataGroupId(Long dataGroupId) {
        this.dataGroupId = dataGroupId;
    }

    public String getDataGroupName() {
        return dataGroupName;
    }

    public void setDataGroupName(String dataGroupName) {
        this.dataGroupName = dataGroupName;
    }

    public String getDataGroupCode() {
        return dataGroupCode;
    }

    public void setDataGroupCode(String dataGroupCode) {
        this.dataGroupCode = dataGroupCode;
    }
}