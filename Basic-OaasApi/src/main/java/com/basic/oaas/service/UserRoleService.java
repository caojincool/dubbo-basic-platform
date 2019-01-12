/**
 *
 * companyName
 * copyright 2015-2020
 * @date 2017年7月5日 上午10:18:22
 * @author Kevin
 * @Description: 用户接口
 * 
 */
package com.basic.oaas.service;




import java.util.List;

import com.basic.framework.common.api.db.domain.PageJson;
import com.basic.framework.common.db.serverinterface.BaseServer;
import com.basic.framework.common.model.DefaultQueryFilter;
import com.basic.oaas.bean.UserRoleIbean;
import com.basic.oaas.model.UserRole;

/**
 *
 * @date 2017年7月5日 上午10:18:22
 * @author Kevin
 * @Description: 账号角色
 * 
 */
public interface UserRoleService extends BaseServer<Long, UserRole> {
	
	/**
	 * 
	 * @date 2017年7月7日 下午5:18:11
	 * @author Kevin
	 * @Description: 新增
	 * @param UserRole
	 * @return
	 *
	 */
	public int createSelective(UserRole userRole);
	
	/**
	 * 
	 * @date 2017年7月7日 下午5:18:31
	 * @author Kevin
	 * @Description: 主键查询
	 * @param UserRoleId
	 * @return
	 *
	 */
	public UserRole qryByPrimaryKey(Long userRoleId);
	
	/**
	 * 
	 * @date 2017年7月7日 下午5:18:51
	 * @author Kevin
	 * @Description: 根据主键修改
	 * @param UserRole
	 * @return
	 *
	 */
	public int modifyByPrimaryKey(UserRole userRole);
	
	/**
	 * 
	 * @date 2017年7月7日 下午5:19:07
	 * @author Kevin
	 * @Description: 根据主键删除
	 * @param UserRoleId
	 * @return
	 *
	 */
	public int removeByPrimaryKey(Long userRoleId);
	
	/**
     * 
     * @date 2017年8月2日 下午3:38:12
     * @author Kevin
     * @Description: 根据用户ID批量删除
     * @param userId
     * @return
     *
     */
    public int removeBatchByUserId(Long userId);
    
    /**
     * 
     * @date 2017年9月4日 下午4:00:08
     * @author Kevin
     * @Description: 批量插入
     * @param record
     * @return
     *
     */
    public int createBatchUserRole(UserRoleIbean ibean);
    
    
    /**
     * 
     * @date 2017年9月5日 下午3:08:18
     * @author Kevin
     * @Description: 根据用户Id获取用户的的角色
     * @param userId
     * @return
     *
     */
    public List<UserRole> qryUserRoleByUserId(Long userId);

	PageJson queryPage(DefaultQueryFilter queryFilter);
	
	
}
