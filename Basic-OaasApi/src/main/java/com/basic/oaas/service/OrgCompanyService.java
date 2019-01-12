/**
 *
 * companyName
 * copyright 2015-2020
 * @date 2017年7月5日 上午10:18:22
 * @author Kevin
 * @Description: 员工接口
 * 
 */
package com.basic.oaas.service;



import com.basic.oaas.model.OrgCompany;

/**
 *
 * @date 2017年7月5日 上午10:18:22
 * @author Kevin
 * @Description: 公司
 * 
 */
public interface OrgCompanyService {
	
	/**
	 * 
	 * @date 2017年8月8日 上午9:59:59
	 * @author Kevin
	 * @Description: 根据主键删除
	 * @param companyId
	 * @return
	 *
	 */
	public int removeByPrimaryKey(Long companyId);

	/**
	 * 
	 * @date 2017年8月8日 上午10:03:38
	 * @author Kevin
	 * @Description: 新增一条数据
	 * @param record
	 * @return
	 *
	 */
	public int createSelective(OrgCompany record);

	/**
	 * 
	 * @date 2017年8月8日 上午10:04:11
	 * @author Kevin
	 * @Description: 根据主键查询
	 * @param companyId
	 * @return
	 *
	 */
	public OrgCompany qryByPrimaryKey(Long companyId);

	/**
	 * 
	 * @date 2017年8月8日 上午10:05:02
	 * @author Kevin
	 * @Description: 根据主键修改
	 * @param record
	 * @return
	 *
	 */
	public int modifyByPrimaryKeySelective(OrgCompany record);
	
	/**
	 * 
	 * @date 2017年11月8日 上午10:36:39
	 * @author Kevin
	 * @Description: 根据部门删除
	 * @param orgId
	 * @return
	 *
	 */
	public int removeByOrgId(Long orgId);
    
}
