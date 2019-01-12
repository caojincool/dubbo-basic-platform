package com.basic.oaas.model;

import java.io.Serializable;

/**
 * 
 *
 * @date 2017年11月6日 下午2:37:52
 * @author Kevin
 * @Description: 部门公司关系
 *
 */
public class OrgCompany implements Serializable {
	
	private static final long serialVersionUID = -1761855912799860719L;

	private Long detailId;

    private Long orgId;

    private Long companyId;

    public Long getDetailId() {
        return detailId;
    }

    public void setDetailId(Long detailId) {
        this.detailId = detailId;
    }

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }
}