package com.basic.log.service;

import java.util.List;

import com.basic.log.bean.SystemConfigIbean;
import com.basic.log.model.SystemConfig;

/**
 * 
 *
 * @date 2017年10月13日 下午3:57:46
 * 
 * @Description: 系统日志配置
 *
 */
public interface SystemConfigService {
	
	/**
	 * 
	 * @date 2017年8月2日 下午2:20:21
	 * 
	 * @Description: 根据主键删除
	 * @param configId
	 * @return
	 *
	 */
	public int removeByPrimaryKey(Long configId);

	/**
	 * 
	 * @date 2017年8月2日 下午2:20:47
	 * 
	 * @Description: 新增一条数据
	 * @param record
	 * @return
	 *
	 */
	public int createSelective(SystemConfig record);

	/**
	 * 
	 * @date 2017年8月2日 下午2:21:00
	 * 
	 * @Description: 根据主键查询
	 * @param configId
	 * @return
	 *
	 */
	public SystemConfig qryByPrimaryKey(Long configId);

	/**
	 * 
	 * @date 2017年8月2日 下午2:21:09
	 * 
	 * @Description: 根据主键修改
	 * @param record
	 * @return
	 *
	 */
	public int modifyByPrimaryKeySelective(SystemConfig record);
	
	/**
	 * 
	 * @date 2017年8月2日 下午2:35:05
	 * 
	 * @Description: 根据参数查询
	 * @param ibean
	 * @return
	 *
	 */
    public List<SystemConfig> qrySystemConfigList(SystemConfigIbean ibean);
    
    /**
     * 
     * @date 2017年8月2日 下午2:35:17
     * 
     * @Description: 根据参数查询的总数
     * @param ibean
     * @return
     *
     */
    public long qrySystemConfigListCount(SystemConfigIbean ibean);
    
    /**
     * 
     * @date 2017年8月2日 下午2:36:59
     * 
     * @Description: 处理新增修改操作
     * @param ibean
     *
     */
    public void createOrModify(SystemConfig ibean);
    
    /**
     * 
     * @date 2017年8月2日 下午3:38:12
     * 
     * @Description: 批量删除，正常情况下，别的模块只修改状态
     * @param configIds
     * @return
     *
     */
    public int removeBatchByConfigIds(Long[] configIds);
    
    /**
     * 
     * @date 2017年10月13日 上午11:50:31
     * 
     * @Description: 根据url查询
     * @param requestUrl
     * @return
     *
     */
    public SystemConfig qryByRequestUrl(String requestUrl);
    
}
