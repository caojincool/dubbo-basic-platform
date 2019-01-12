package com.basic.oaas.bean;

import java.io.Serializable;
import java.util.List;

import com.basic.framework.common.model.PageBean;
import com.basic.oaas.model.PrivateDataInstData;

/**
 * 
 *
 * @date 2017年9月8日 上午10:38:06
 * @author Kevin
 * @Description: 数据权限实例数据
 *
 */
public class PrivateDataInstDataIbean extends PageBean implements Serializable {

	private static final long serialVersionUID = 6253393416942814197L;
	
	 private Long dataInstId; //实例Id
	 
	 private List<PrivateDataInstData> privateDataInstDatas;
	/**
	 * @return the dataInstId
	 */
	public Long getDataInstId() {
		return dataInstId;
	}

	/**
	 * @param dataInstId the dataInstId to set
	 */
	public void setDataInstId(Long dataInstId) {
		this.dataInstId = dataInstId;
	}

	/**
	 * @return the privateDataInstDatas
	 */
	public List<PrivateDataInstData> getPrivateDataInstDatas() {
		return privateDataInstDatas;
	}

	/**
	 * @param privateDataInstDatas the privateDataInstDatas to set
	 */
	public void setPrivateDataInstDatas(List<PrivateDataInstData> privateDataInstDatas) {
		this.privateDataInstDatas = privateDataInstDatas;
	}
	
	
	 
}