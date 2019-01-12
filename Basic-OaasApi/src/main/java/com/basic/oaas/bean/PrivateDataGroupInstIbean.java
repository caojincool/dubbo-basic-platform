package com.basic.oaas.bean;

import java.io.Serializable;
import java.util.List;

import com.basic.framework.common.model.PageBean;
import com.basic.oaas.model.PrivateDataGroupInst;
import com.basic.oaas.model.PrivateDataInstData;

/**
 * 
 * <pre> 
 * @Description：数据权限分组实例 控制器类
 * @author:lengzj
 * @email:mail@com
 * @date:2018-06-27 16:21:49
 * @copyright：companyName
 * </pre>
 */
public class PrivateDataGroupInstIbean extends PageBean implements Serializable {

	
	 public Long getDataGroupId() {
		return dataGroupId;
	}

	public void setDataGroupId(Long dataGroupId) {
		this.dataGroupId = dataGroupId;
	}

	public List<PrivateDataGroupInst> getPrivateDataGroupInstDatas() {
		return privateDataGroupInstDatas;
	}

	public void setPrivateDataGroupInstDatas(List<PrivateDataGroupInst> privateDataGroupInstDatas) {
		this.privateDataGroupInstDatas = privateDataGroupInstDatas;
	}

	private Long dataGroupId; //实例Id
	 
	 private List<PrivateDataGroupInst> privateDataGroupInstDatas;
	 
	 
	
	 
}