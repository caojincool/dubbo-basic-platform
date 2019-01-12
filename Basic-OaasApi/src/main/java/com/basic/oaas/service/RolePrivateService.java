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
import com.basic.oaas.bean.RolePrivateIbean;
import com.basic.oaas.model.RolePrivate;
import com.basic.oaas.model.User;

/**
 *
 * @date 2017年7月5日 上午10:18:22
 * @author Kevin
 * @Description: 角色权限
 * 
 */
public interface RolePrivateService extends BaseServer<Long, RolePrivate>{
	
	/**
	 * 
	 * @date 2017年7月7日 下午5:18:11
	 * @author Kevin
	 * @Description: 新增
	 * @param RolePrivate
	 * @return
	 *
	 */
	public int createSelective(RolePrivate rolePrivate);
	
	/**
	 * 
	 * @date 2017年7月7日 下午5:18:31
	 * @author Kevin
	 * @Description: 主键查询
	 * @param RolePrivateId
	 * @return
	 *
	 */
	public RolePrivate qryByPrimaryKey(Long rolePrivateId);
	
	/**
	 * 
	 * @date 2017年7月7日 下午5:18:51
	 * @author Kevin
	 * @Description: 根据主键修改
	 * @param RolePrivate
	 * @return
	 *
	 */
	public int modifyByPrimaryKey(RolePrivate rolePrivate);
	
	/**
	 * 
	 * @date 2017年7月7日 下午5:19:07
	 * @author Kevin
	 * @Description: 根据主键删除
	 * @param RolePrivateId
	 * @return
	 *
	 */
	public int removeByPrimaryKey(Long rolePrivateId);
	
	/**
     * 
     * @date 2017年8月2日 下午3:38:12
     * @author Kevin
     * @Description: 根据用户ID批量删除
     * @param roleId
     * @return
     *
     */
    public int removeBatchByRoleId(Long roleId);
    
    /**
     * 
     * @date 2017年9月4日 下午4:00:08
     * @author Kevin
     * @param user 
     * @Description: 批量插入
     * @param record
     * @return
     *
     */
    public int createBatchRolePrivate(RolePrivateIbean ibean, User user);
    
    
    /**
     * 
     * @date 2017年9月5日 下午3:08:18
     * @author Kevin
     * @Description: 根据用户Id获取用户的的权限
     * @param roleId
     * @return
     *
     */
    public List<RolePrivate> qryRolePrivateByRoleId(Long roleId);
	
	/**
	 * 
	 * @date 2017年9月17日 下午8:57:56
	 * @author Kevin
	 * @Description: 批量删除
	 * @param rolePrivateIds
	 * @return
	 *
	 */
    public int removeBatchByRolePrivateIds(Long [] rolePrivateIds);

	PageJson queryPage(DefaultQueryFilter queryFilter);
}
