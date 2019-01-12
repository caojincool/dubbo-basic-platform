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



import java.util.List;

import com.basic.framework.common.api.db.domain.PageJson;
import com.basic.framework.common.db.serverinterface.BaseServer;
import com.basic.framework.common.model.DefaultQueryFilter;
import com.basic.oaas.bean.CompanyIbean;
import com.basic.oaas.model.Company;

/**
 *
 * @date 2017年7月5日 上午10:18:22
 * @author Kevin
 * @Description: 公司
 * 
 */
public interface CompanyService extends BaseServer<Long, Company>{
	
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
	public int createSelective(Company record);

	/**
	 * 
	 * @date 2017年8月8日 上午10:04:11
	 * @author Kevin
	 * @Description: 根据主键查询
	 * @param companyId
	 * @return
	 *
	 */
	public Company qryByPrimaryKey(Long companyId);

	/**
	 * 
	 * @date 2017年8月8日 上午10:05:02
	 * @author Kevin
	 * @Description: 根据主键修改
	 * @param record
	 * @return
	 *
	 */
	public int modifyByPrimaryKeySelective(Company record);
	
	/**
	 * 
	 * @date 2017年8月8日 上午10:05:20
	 * @author Kevin
	 * @Description: 根据参数查询
	 * @param ibean
	 * @return
	 *
	 */
    public List<Company> qryCompanyList(CompanyIbean ibean);
    
    /**
     * 
     * @date 2017年8月8日 上午10:05:44
     * @author Kevin
     * @Description: 根据参数查询总数
     * @param ibean
     * @return
     *
     */
    public long qryCompanyListCount(CompanyIbean ibean);
    
    /**
     * 
     * @date 2017年8月8日 上午10:06:05
     * @author Kevin
     * @Description: 处理新增修改操作
     * @param ibean
     * @return 
     *
     */
    public Company createOrModify(Company ibean);
    
    
    /**
     * 
     * @date 2018年2月6日 下午2:28:24
     * @author LGK
     * @Description:根据用户Id查询
     * @param userId
     * @return
     *
     */
    public List<Company> qryByUserId(Long userId,Long companyId);

	/**
	 * @date 2018年2月6日 下午2:32:54
	 * @author LGK
	 * @Description: 默认公司
	 * @param orgId
	 * @return
	 * 
	 */
	public Company qryByOrgId(Long orgId);

	PageJson queryPage(DefaultQueryFilter queryFilter);
    
    
}
