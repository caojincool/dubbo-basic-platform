package com.basic.oaas.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.basic.framework.common.model.DefaultQueryFilter;
import com.basic.oaas.bean.JobIbean;
import com.basic.oaas.model.Job;
import com.basic.oaas.model.Staff;

/**
 * 
 *
 * @date 2017年8月16日 上午11:45:51
 * @author Kevin
 * @Description: 职位表
 *
 */
public interface JobMapper {
	
	/**
	 * 
	 * @date 2017年8月16日 上午11:47:55
	 * @author Kevin
	 * @Description: 根据主键删除
	 * @param jobId
	 * @return
	 *
	 */
	public int deleteByPrimaryKey(Long jobId);

    /**
     * 
     * @date 2017年8月16日 上午11:48:13
     * @author Kevin
     * @Description: 新增一条数据
     * @param record
     * @return
     *
     */
    public int insertSelective(Job record);
    
    
    /**
     * 
     * @date 2017年8月16日 上午11:48:34
     * @author Kevin
     * @Description: 根据主键查询
     * @param jobId
     * @return
     *
     */
    public Job selectByPrimaryKey(Long jobId);

    /**
     * 
     * @date 2017年8月16日 上午11:50:37
     * @author Kevin
     * @Description: 根据主键修改
     * @param record
     * @return
     *
     */
    public int updateByPrimaryKeySelective(Job record);
    
    /**
     * 
     * @Description:更新所有字段
     * @author lengzj
     * @param record
     * @return
     */
    public int updateByPrimaryKey(Job record);
    
    /**
	 * 
	 * @date 2017年8月2日 下午2:35:05
	 * @author Kevin
	 * @Description: 根据参数查询
	 * @param ibean
	 * @return
	 *
	 */
    public List<Job> selectJobList(JobIbean ibean);
    
    /**
     * 
     * @date 2017年8月2日 下午2:35:17
     * @author Kevin
     * @Description: 根据参数查询的总数
     * @param ibean
     * @return
     *
     */
    public long selectJobListCount(JobIbean ibean);
    
    /**
     * 
     * @date 2017年8月2日 下午3:38:12
     * @author Kevin
     * @Description: 批量删除，正常情况下，别的模块只修改状态
     * @param jobIds
     * @return
     *
     */
    public int deleteBatchByJobIds(Long[] jobIds);
    
    /**
     * 
     * @date 2017年8月2日 下午3:38:12
     * @author Kevin
     * @Description: 批量修改状态
     * @param jobIds
     * @return
     *
     */
    public int updateBatchByJobIds(Long[] jobIds);
    
    /**
     * 
     * @date 2017年8月16日 下午6:06:01
     * @author Kevin
     * @Description: 检查是否存相同的职位编码
     * @param jobCode
     * @return
     *
     */
    public int selectExistJobCode(String jobCode);
    
    /**
     * 
     * @date 2017年8月25日 下午5:08:31
     * @author Kevin
     * @Description: 根据userId获取默认职位部门区域
     * @param userId
     * @return
     *
     */
    public Job selectDefaultJobByUserId(Long userId);
    
    public List<Job> selectJobAndOrgByUserId(Long userId);
    
    /**
     * 
     * @date 2017年8月25日 下午5:08:31
     * @author Kevin
     * @Description: 根据userId获取职位部门区域
     * @param userId
     * @return
     *
     */
    public List<Job> selectJobByUserId(Long userId);

	/**
	 * @date 2018年2月6日 下午4:26:09
	 * @author LGK
	 * @Description: 根据公司ID查询
	 * @param userId
	 * @param companyId
	 * @return
	 * 
	 */
	public Job qryJobByUserIdAndCompanyId(Long userId, Long companyId);

	/**
	 * @date 2018年2月6日 下午5:37:48
	 * @author LGK
	 * @Description: 根据用户ID，公司ID查询
	 * @param userId
	 * @param companyId
	 * @return
	 * 
	 */
	public List<Job> selectJobByUserIdCompany(@Param("userId")Long userId, @Param("companyId")Long companyId);
	/**
	 * 
	 * @date 2018年2月26日 下午7:14:00
	 * @author lhj
	 * @Description: 根据岗位查询
	 * @param oaasJob
	 * @return
	 *
	 */
	public List<Job> selectByJob(Job oaasJob);
	/**
	 * 
	 * @date 2018年2月26日 下午7:20:38
	 * @author lhj
	 * @Description: 根据岗位编码查询该岗位下的员工
	 * @param jobCode
	 * @return
	 *
	 */
	public int qryJobStaffCount(String jobCode);

	/**
	 * 
	 * @Description:组装查询
	 * @author lengzj
	 * @param queryFilter
	 * @return
	 */
	public List<Job> query(DefaultQueryFilter queryFilter);
	
	/**
	 * 查询组织岗位用户关系，外部接口用
	 * @param params
	 * @return
	 */
	public List<Job> queryJobOrgUserRels(Map<String,Object> params);
	
	

	/**
	 * 
	 * @Description:根据参数统计
	 * @author lengzj
	 * @param params
	 * @return
	 */
	public int countJob(Map<String,Object> params);
	

}