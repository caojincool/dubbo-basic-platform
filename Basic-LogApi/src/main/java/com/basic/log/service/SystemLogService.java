package com.basic.log.service;

import java.util.List;

import com.basic.log.bean.SystemLogIbean;
import com.basic.log.model.SystemLog;

/**
 * 
 *
 * @date 2017年10月13日 下午3:57:34
 * 
 * @Description: 系统日志
 *
 */
public interface SystemLogService {
	
	/**
	 * 
	 * @date 2017年8月2日 下午2:20:21
	 * 
	 * @Description: 根据主键删除
	 * @param logId
	 * @return
	 *
	 */
	public int removeByPrimaryKey(Long logId);

	/**
	 * 
	 * @date 2017年8月2日 下午2:20:47
	 * 
	 * @Description: 新增一条数据
	 * @param record
	 * @return
	 *
	 */
	public int createSelective(SystemLog record);

	/**
	 * 
	 * @date 2017年8月2日 下午2:21:00
	 * 
	 * @Description: 根据主键查询
	 * @param logId
	 * @return
	 *
	 */
	public SystemLog qryByPrimaryKey(Long logId);

	/**
	 * 
	 * @date 2017年8月2日 下午2:21:09
	 * 
	 * @Description: 根据主键修改
	 * @param record
	 * @return
	 *
	 */
	public int modifyByPrimaryKeySelective(SystemLog record);
	
	/**
	 * 
	 * @date 2017年8月2日 下午2:35:05
	 * 
	 * @Description: 根据参数查询
	 * @param ibean
	 * @return
	 *
	 */
    public List<SystemLog> qrySystemLogList(SystemLogIbean ibean);
    
    /**
     * 
     * @date 2017年8月2日 下午2:35:17
     * 
     * @Description: 根据参数查询的总数
     * @param ibean
     * @return
     *
     */
    public long qrySystemLogListCount(SystemLogIbean ibean);
}
