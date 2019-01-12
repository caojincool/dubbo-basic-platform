package com.basic.log.dao;

import java.util.List;

import com.basic.log.bean.SystemConfigIbean;
import com.basic.log.model.SystemConfig;

public interface SystemConfigMapper {
    int deleteByPrimaryKey(Long configId);

    int insert(SystemConfig record);

    int insertSelective(SystemConfig record);

    SystemConfig selectByPrimaryKey(Long configId);

    int updateByPrimaryKeySelective(SystemConfig record);

    int updateByPrimaryKey(SystemConfig record);
	
	/**
	 * 
	 * @date 2017年8月2日 下午2:35:05
	 * 
	 * @Description: 根据参数查询
	 * @param ibean
	 * @return
	 *
	 */
    public List<SystemConfig> selectSystemConfigList(SystemConfigIbean ibean);
    
    /**
     * 
     * @date 2017年8月2日 下午2:35:17
     * 
     * @Description: 根据参数查询的总数
     * @param ibean
     * @return
     *
     */
    public long selectSystemConfigListCount(SystemConfigIbean ibean);
    
    /**
     * 
     * @date 2017年8月2日 下午3:38:12
     * 
     * @Description: 批量删除，正常情况下，别的模块只修改状态
     * @param configIds
     * @return
     *
     */
    public int deleteBatchByConfigIds(Long[] configIds);
    
    /**
     * 
     * @date 2017年10月13日 上午11:50:31
     * 
     * @Description: 根据url查询
     * @param requestUrl
     * @return
     *
     */
    public SystemConfig selectByRequestUrl(String requestUrl);
}