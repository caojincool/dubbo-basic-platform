/**
 *
 * companyName
 * copyright 2015-2020
 * @date 2017年8月8日 上午9:55:02
 * @author Kevin
 * @Description: TODO(这里用一句话描述这个类的作用)
 * 
 */
package com.basic.oaas.service;

import java.util.List;
import java.util.Map;

import com.basic.framework.common.api.db.domain.PageJson;
import com.basic.framework.common.db.serverinterface.BaseServer;
import com.basic.framework.common.model.DefaultQueryFilter;
import com.basic.oaas.bean.JobIbean;
import com.basic.oaas.model.Job;


/**
 *
 * @date 2017年8月8日 上午9:55:02
 * @author Kevin
 * @Description: 职位接口
 * 
 */
public interface JobService  extends BaseServer<Long, Job>{
	
	/**
	 * 
	 * @date 2017年8月8日 上午9:59:59
	 * @author Kevin
	 * @Description: 根据主键删除
	 * @param jobId
	 * @return
	 *
	 */
	public int removeByPrimaryKey(Long jobId);

	/**
	 * 
	 * @date 2017年8月8日 上午10:03:38
	 * @author Kevin
	 * @Description: 新增一条数据
	 * @param record
	 * @return
	 *
	 */
	public int createSelective(Job record);

	/**
	 * 
	 * @date 2017年8月8日 上午10:04:11
	 * @author Kevin
	 * @Description: 根据主键查询
	 * @param jobId
	 * @return
	 *
	 */
	public Job qryByPrimaryKey(Long jobId);

	/**
	 * 
	 * @date 2017年8月8日 上午10:05:02
	 * @author Kevin
	 * @Description: 根据主键修改
	 * @param record
	 * @return
	 *
	 */
	public int modifyByPrimaryKeySelective(Job record);
	
	/**
	 * 
	 * @date 2017年8月8日 上午10:05:20
	 * @author Kevin
	 * @Description: 根据参数查询
	 * @param ibean
	 * @return
	 *
	 */
    public List<Job> qryJobList(JobIbean ibean);
    
    /**
     * 
     * @date 2017年8月8日 上午10:05:44
     * @author Kevin
     * @Description: 根据参数查询总数
     * @param ibean
     * @return
     *
     */
    public long qryJobListCount(JobIbean ibean);
    
    /**
     * 
     * @date 2017年8月8日 上午10:06:05
     * @author Kevin
     * @Description: 处理新增修改操作
     * @param ibean
     *
     */
    public void createOrModify(Job ibean);
    
    /**
     * 
     * @date 2017年8月8日 上午10:06:05
     * @author Kevin
     * @Description: 批量删除，正常情况下，别的模块只修改状态
     * @param jobIds
     * @return
     *
     */
    public int removeBatchByJobIds(Long[] jobIds);
    
    /**
     * 
     * @date 2017年8月8日 上午10:06:05
     * @author Kevin
     * @Description: 批量修改状态
     * @param jobIds
     * @return
     *
     */
//    public int updateBatchByJobIds(Long[] jobIds);
    /**
     * 
     * @date 2017年8月16日 下午6:04:10
     * @author Kevin
     * @Description: 检查是否存在区域编码
     * @param jobCode
     * @return
     *
     */
    public int qryExistJobCode(String jobCode);
    
    /**
     * 
     * @date 2017年8月25日 下午5:17:18
     * @author Kevin
     * @Description: 根据用户Id获取默认职位部门区域
     * @param userId
     * @return
     *
     */
    public Job qryDefaultJobByUserId(Long userId);
    
    /**
     * 
     * @date 2017年8月25日 下午5:17:18
     * @author Kevin
     * @Description: 根据用户Id获取职位部门区域
     * @param userId
     * @return
     *
     */
    public List<Job> qryJobByUserId(Long userId);

	/**
	 * @date 2018年2月6日 下午5:36:17
	 * @author LGK
	 * @Description: 根据公司，用户ID查询
	 * @param userId
	 * @param companyId
	 * @return
	 * 
	 */
	public List<Job> qryJobByUserIdCompany(Long userId, Long companyId);

	public List<Job> selectJobAndOrgByUserId(Long partyId);

	/**
	 * 分页查询
	 * @param queryFilter
	 * @return
	 */
	PageJson queryPage(DefaultQueryFilter queryFilter);
	
	/**
	 * 查询组织岗位用户关系，外部接口用
	 * @param params
	 * @return
	 */
	public List<Job> queryJobOrgUserRels(Map<String,Object> params);
}
