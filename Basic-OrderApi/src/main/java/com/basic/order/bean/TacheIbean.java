package com.basic.order.bean;

import java.io.Serializable;

import com.basic.framework.common.model.PageBean;

/**
 * 
 *
 * @date 2017年9月4日 下午3:58:32
 * 
 * @Description: 环节查询参数
 *
 */
public class TacheIbean extends PageBean implements Serializable {

	private static final long serialVersionUID = -8187678479921407963L;
	
	private Long catalogId;//环节目录id
    private String tacheCode;//环节编码
    private String tacheName;//环节名称
    
	public Long getCatalogId() {
		return catalogId;
	}
	public void setCatalogId(Long catalogId) {
		this.catalogId = catalogId;
	}
	public String getTacheCode() {
		return tacheCode;
	}
	public void setTacheCode(String tacheCode) {
		this.tacheCode = tacheCode;
	}
	public String getTacheName() {
		return tacheName;
	}
	public void setTacheName(String tacheName) {
		this.tacheName = tacheName;
	}

}