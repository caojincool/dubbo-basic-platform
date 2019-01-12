package com.basic.framework.demo.model;

import java.io.Serializable;

/**
 * 演示緩存
 *
 * @date 2016年9月3日 下午3:38:03
 * @author lzj
 * @Description: 演示緩存
 *
 */
public class DemoCacheOrg implements Serializable {

	private static final long serialVersionUID = -6074402180790608929L;
	
	private Long orgId;
	private String orgName;
	public Long getOrgId() {
		return orgId;
	}
	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

}
