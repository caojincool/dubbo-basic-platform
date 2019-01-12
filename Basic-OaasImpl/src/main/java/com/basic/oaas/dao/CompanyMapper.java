package com.basic.oaas.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.basic.oaas.bean.CompanyIbean;
import com.basic.oaas.model.Company;

public interface CompanyMapper {
	/**
	 * 
	 * @date 2017年11月6日 下午3:17:13
	 * @author Kevin
	 * @Description: 新增
	 * @param companyId
	 * @return
	 *
	 */
	public int deleteByPrimaryKey(Long companyId);

	/**
	 * 
	 * @date 2017年11月6日 下午3:17:21
	 * @author Kevin
	 * @Description: 删除
	 * @param record
	 * @return
	 *
	 */
	public int insertSelective(Company record);

	/**
	 * 
	 * @date 2017年11月6日 下午3:17:28
	 * @author Kevin
	 * @Description: 根据主键查询
	 * @param companyId
	 * @return
	 *
	 */
	public Company selectByPrimaryKey(Long companyId);

	/**
	 * 
	 * @date 2017年11月6日 下午3:17:39
	 * @author Kevin
	 * @Description: 根据主键修改
	 * @param record
	 * @return
	 *
	 */
	public int updateByPrimaryKeySelective(Company record);
	
	/**
	 * 
	 * @Description:更新所有信息
	 * @author lengzj
	 * @param record
	 * @return
	 */
	public int updateByPrimaryKey(Company record);

	/**
	 * 
	 * @date 2017年11月6日 下午3:18:54
	 * @author Kevin
	 * @Description: 根据条件查询
	 * @param ibean
	 * @return
	 *
	 */
	public List<Company> selectCompanyList(CompanyIbean ibean);

	/**
	 * 
	 * @date 2017年11月6日 下午3:19:15
	 * @author Kevin
	 * @Description: 根据条件查询数量
	 * @param ibean
	 * @return
	 *
	 */
	public long selectCompanyListCount(CompanyIbean ibean);

	/**
	 * 
	 * @date 2017年11月6日 下午3:19:39
	 * @author Kevin
	 * @Description: 批量修改状态
	 * @param companyIds
	 * @return
	 *
	 */
	public int updateBatchByCompanyIds(Long[] companyIds);

	/**
	 * 
	 * @date 2018年2月6日 下午2:27:23
	 * @author LGK
	 * @Description: 根据用户ID查询
	 * @param userId
	 * @return
	 *
	 */
	public List<Company> selectByUserId(@Param("userId")Long userId,@Param("companyId")Long companyId);

	/**
	 * @date 2018年2月6日 下午2:33:45
	 * @author LGK
	 * @Description: 根据orgID查询
	 * @param orgId
	 * @return
	 * 
	 */
	public Company selectByOrgId(Long orgId);
	
	/**
	 * 获取所有子公司
	 * @param company
	 * @return
	 */
	public List<Company> selectAllSubCompanyById(Company company);

	/**
	 * 根据ID修改所有当前以及子公司的状态
	 * @param companyId
	 * @return
	 */
	public int updateAllStateById(Long companyId);
	
	/**
	 * 
	 * @Description:根据参数统计公司数
	 * @author lengzj
	 * @param params
	 * @return
	 */
	public int countCompany(Map<String,Object> params);
	
	
}