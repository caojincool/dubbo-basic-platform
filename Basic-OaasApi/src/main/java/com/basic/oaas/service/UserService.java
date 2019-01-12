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
import java.util.Map;

import com.basic.framework.common.api.db.domain.PageJson;
import com.basic.framework.common.db.serverinterface.BaseServer;
import com.basic.framework.common.model.DefaultQueryFilter;
import com.basic.oaas.bean.UserIBean;
import com.basic.oaas.model.User;

/**
 *
 * @date 2017年7月5日 上午10:18:22
 * @author Kevin
 * @Description: 账号
 * 
 */
public interface UserService extends BaseServer<Long, User> {
	
	/**
	 * 
	 * @date 2017年7月7日 下午5:18:11
	 * @author Kevin
	 * @Description: 新增
	 * @param User
	 * @return
	 *
	 */
	public User createSelective(User user);
	
	/**
	 * 
	 * @date 2017年7月7日 下午5:18:31
	 * @author Kevin
	 * @Description: 主键查询
	 * @param UserId
	 * @return
	 *
	 */
	public User qryUserById(Long userId);
	
	/**
	 * 
	 * @date 2017年7月7日 下午5:18:51
	 * @author Kevin
	 * @Description: 根据主键修改
	 * @param User
	 * @return
	 *
	 */
	public int modifyByPrimaryKey(User user);
	
	/**
	 * 
	 * @date 2017年7月7日 下午5:19:07
	 * @author Kevin
	 * @Description: 根据主键删除
	 * @param UserId
	 * @return
	 *
	 */
	public int removeByPrimarykey(Long userId);
	
	/**
	 * 
	 * @date 2017年7月20日 上午11:06:00
	 * @author Kevin
	 * @Description: 根据用户名（用户账号）查找用户
	 * @param username
	 * @return
	 *
	 */
	public User qryUserByUsername(String username);
	
	/**
	 * 
	 * @date 2017年7月21日 上午9:45:39
	 * @author Kevin
	 * @Description: 查询用户列表
	 * @param userIBean
	 * @return
	 *
	 */
	public List<User> qryUserList(UserIBean userIBean);
	
	/**]
	 * 
	 * @date 2017年7月21日 下午2:14:57
	 * @author Kevin
	 * @Description: 根据条件获取记录数
	 * @param userIBean 查询条件
	 * @return
	 *
	 */
	public long qryUserCount(UserIBean userIBean);
	
	/**
	 * 
	 * @date 2017年8月3日 下午4:34:20
	 * @author Kevin
	 * @Description: 批量删除
	 * @param userIds
	 *
	 */
	public int removeBatchByUserIds(Long[] userIds);
	
	   /**
	    * 
	    * @date 2017年11月24日 下午3:01:14
	    * 
	    * @Description: 查询该部门下面的所有账号
	    * @param orgId
	    * @return
	    *
	    */
	   public List<User> qryByOrgId(Long orgId);
	   
	   /**
	    * 
	    * @date 2017年11月24日 下午3:01:14
	    * 
	    * @Description: 查询关联该员工的账号
	    * @param staffId
	    * @return
	    *
	    */
	   public User qryByStaffId(Long staffId);
	   
	   /**
	    * 
	    * @date 2017年11月24日 下午3:01:14
	    * 
	    * @Description: 查询该职位下面的所有账号
	    * @param jobId
	    * @return
	    *
	    */
	   public List<User> qryByJobId(Long jobId);
	   
	   /**
	    * 
	    * @date 2017年11月24日 下午3:01:14
	    * 
	    * @Description: 查询该角色下面的所有账号
	    * @param roleId
	    * @return
	    *
	    */
	   public List<User> qryByRoleId(Long roleId);
	   
	   /**
		 * 分页查询
		 * @param queryFilter
		 * @return
		 */
		PageJson queryPage(DefaultQueryFilter queryFilter);
		
		/**
	    * 获取可选的账号列表
	    * @param userType
	    * @return
	    */
		List<User> getAvaliableAccounts(Map<String, Object> params);
		
		/**
	    * 
	    * @Description:获取已绑定的帐号
	    * @author lengzj
	    * @param bindId 绑定的ID
	    * @param type 帐号类型，0-内部帐号，1-外部帐号
	    * @return
	    */
		List<User> getBindingAccounts(Map<String, Object> params);
		
		/**
		 * 
		 * @Description:根据ID获取
		 * @author lengzj
		 * @param id
		 * @return
		 */
		User getById(Long id);
		
		/**
	    * 
	    * @Description:更改密码
	    * @author lengzj
	    * @param record
	    * @return
	    */
	   public int changePwd(User record);
	   
	   /**
	    * 
	    * @Description:根据组织ID获取帐号
	    * @author lengzj
	    * @param orgIds
	    * @return
	    */
	   public List<User> selectByOrgIds(List<Long> orgIds);

    /**
     * 获取外部账号关联的客户或者供应商 公司ID
     * @param userId
     * @return
     */
    List<String> getOutSideCode(Long userId);
}
