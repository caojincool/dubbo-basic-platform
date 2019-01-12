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

import com.basic.framework.common.db.serverinterface.BaseServer;
import com.basic.oaas.bean.UserStaffIbean;
import com.basic.oaas.model.UserStaff;

/**
 *
 * @date 2017年7月5日 上午10:18:22
 * @author Kevin
 * @Description: 账号员工表
 * 
 */
public interface UserStaffService extends BaseServer<Long, UserStaff> {
	
	/**
	 * 
	 * @date 2017年8月8日 上午9:59:59
	 * @author Kevin
	 * @Description: 根据主键删除
	 * @param userId
	 * @return
	 *
	 */
	public int removeByPrimaryKey(Long userId);

	/**
	 * 
	 * @date 2017年8月8日 上午10:03:38
	 * @author Kevin
	 * @Description: 新增一条数据
	 * @param record
	 * @return
	 *
	 */
	public int createSelective(UserStaff record);

	/**
	 * 
	 * @date 2017年8月8日 上午10:04:11
	 * @author Kevin
	 * @Description: 根据主键查询
	 * @param userId
	 * @return
	 *
	 */
	public UserStaff qryByPrimaryKey(Long userId);

	/**
	 * 
	 * @date 2017年8月8日 上午10:05:02
	 * @author Kevin
	 * @Description: 根据主键修改
	 * @param record
	 * @return
	 *
	 */
	public int modifyByPrimaryKeySelective(UserStaff record);
	
	/**
	 * 
	 * @date 2017年8月8日 上午10:05:20
	 * @author Kevin
	 * @Description: 根据参数查询
	 * @param ibean
	 * @return
	 *
	 */
    public List<UserStaff> qryUserStaffList(UserStaffIbean ibean);
    
    /**
	 * 
	 * @date 2017年8月8日 上午10:05:20
	 * @author Kevin
	 * @Description: 根据参数查询
	 * @param ibean
	 * @return
	 *
	 */
    public long qryUserStaffListCount(UserStaffIbean ibean);
    
    
    /**
	 * 
	 * @date 2017年8月8日 上午9:59:59
	 * @author Kevin
	 * @Description: 批量删除
	 * @param userIds
	 * @return
	 *
	 */
	public int removeBatchByUserIds(Long[] userIds);
    
}
