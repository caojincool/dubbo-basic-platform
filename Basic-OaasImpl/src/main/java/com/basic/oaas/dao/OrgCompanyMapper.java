package com.basic.oaas.dao;

import com.basic.oaas.model.OrgCompany;

public interface OrgCompanyMapper {
	/**
	 * 
	 * @date 2017年11月6日 下午3:28:06
	 * @author Kevin
	 * @Description: 删除
	 * @param detailId
	 * @return
	 *
	 */
    public int deleteByPrimaryKey(Long detailId);

    /**
     * 
     * @date 2017年11月6日 下午3:28:50
     * @author Kevin
     * @Description: 新增
     * @param record
     * @return
     *
     */
    public int insertSelective(OrgCompany record);

    /**
     * 
     * @date 2017年11月6日 下午3:29:06
     * @author Kevin
     * @Description: 根据主键查询
     * @param detailId
     * @return
     *
     */
    public OrgCompany selectByPrimaryKey(Long detailId);

    /**
     * 
     * @date 2017年11月6日 下午3:29:21
     * @author Kevin
     * @Description: 修改
     * @param record
     * @return
     *
     */
    public int updateByPrimaryKeySelective(OrgCompany record);
    
    /**
     * 
     * @date 2017年11月8日 上午10:33:46
     * @author Kevin
     * @Description: 根据部门删除
     * @param orgId
     * @return
     *
     */
    public int deleteByOrgId(Long orgId);
    /**
     * 
     * @date 2018年3月16日 下午2:41:11
     * @author lhj
     * @Description: 根据组织Id
     * @param parentOrgId
     * @return
     *
     */
	public OrgCompany selectByOrgId(Long orgId);
    
}