package com.basic.log.dao;

import java.util.List;

import com.basic.log.bean.SystemLogIbean;
import com.basic.log.model.SystemLog;

public interface SystemLogMapper {
    int deleteByPrimaryKey(Long logId);

    int insert(SystemLog record);

    int insertSelective(SystemLog record);

    SystemLog selectByPrimaryKey(Long logId);

    int updateByPrimaryKeySelective(SystemLog record);

    int updateByPrimaryKey(SystemLog record);
    
	
	/**
	 * 
	 * @date 2017年8月2日 下午2:35:05
	 * 
	 * @Description: 根据参数查询
	 * @param ibean
	 * @return
	 *
	 */
    public List<SystemLog> selectSystemLogList(SystemLogIbean ibean);
    
    /**
     * 
     * @date 2017年8月2日 下午2:35:17
     * 
     * @Description: 根据参数查询的总数
     * @param ibean
     * @return
     *
     */
    public long selectSystemLogListCount(SystemLogIbean ibean);
    
}