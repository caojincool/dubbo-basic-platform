package com.basic.framework.demo.service;

import java.util.List;

import com.basic.framework.demo.model.DemoOrg;

public interface DemoOrgService {

	/**
	 * 分步查询组织树
	 * @param nodeid
	 * @return
	 */
	public List<DemoOrg> qryOrgsTreeStep(Long nodeid) throws Exception; 
	
	/**
	 * 查询所有组织
	 * @param nodeid
	 * @return
	 */
	public List<DemoOrg> qryAllOrgs() throws Exception;
	
	/**
	 * 
	 * @date 2016年8月24日 上午9:13:15
	 * @author 杰
	 * @Description: 分步查询部门
	 * @return
	 *
	 */
	public List<DemoOrg> qryOaasOrgTreeStep(Long id);
	
	/**
	 * 
	 * @date 2016年8月24日 上午9:13:15
	 * @author 杰
	 * @Description: 查询全部部门
	 * @return
	 *
	 */
	public List<DemoOrg> qryOaasOrgTree();
}
