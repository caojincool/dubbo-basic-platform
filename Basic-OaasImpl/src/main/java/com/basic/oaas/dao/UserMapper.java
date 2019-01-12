package com.basic.oaas.dao;

import java.util.List;
import java.util.Map;

import com.basic.oaas.bean.UserIBean;
import com.basic.oaas.model.Staff;
import com.basic.oaas.model.User;

public interface UserMapper {
	
   /**
    * 
    * @date 2017年7月21日 上午9:46:39
    * @author Kevin
    * @Description: 主键删除
    * @param userId 
    * @return
    *
    */
   public int deleteByPrimaryKey(Long userId);

   
   /**
    * 
    * @date 2017年7月21日 上午9:46:53
    * @author Kevin
    * @Description: 新增
    * @param record
    * @return
    *
    */
   public int insertSelective(User record);

   /**
    * 
    * @date 2017年7月21日 上午9:47:07
    * @author Kevin
    * @Description: 主键查询
    * @param userId
    * @return
    *
    */
   public User selectByPrimaryKey(Long userId);

   /**
    * 
    * @date 2017年7月21日 上午9:47:33
    * @author Kevin
    * @Description: 根据主键修改
    * @param record
    * @return
    *
    */
   public int updateByPrimaryKeySelective(User record);
   
   /**
    * 
    * @Description:更新所有字段
    * @author lengzj
    * @param record
    * @return
    */
   public int updateByPrimaryKey(User record);
   
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
    * @date 2017年7月21日 上午9:47:54
    * @author Kevin
    * @Description: 根据用户名查找
    * @param username
    * @return
    *
    */
   public User selectByUsername(String username);
   
   /**
    * 
    * @date 2017年7月21日 上午9:48:35
    * @author Kevin
    * @Description: 根据条件查询
    * @param userIBean	 查询条件
    * @return
    *
    */
   public List<User> selectUserList(UserIBean userIBean);
   
   /**
    * 
    * @date 2017年7月21日 下午2:16:52
    * @author Kevin
    * @Description:根据条件查询记录数
    * @param userIBean 查询条件
    * @return
    *
    */
   public long selectUserCount(UserIBean userIBean);
   
   /**
    * 
    * @date 2017年8月3日 下午4:41:05
    * @author Kevin
    * @Description: 批量修改状态
    * @param userIds 
    * @return
    *
    */
   public int updateBatchByUserIds(Long[] userIds);

   /**
    * 
    * @date 2017年11月24日 下午3:01:14
    * 
    * @Description: 查询该部门下面的所有账号
    * @param orgId
    * @return
    *
    */
   public List<User> selectByOrgId(Long orgId);
   
   /**
    * 
    * @date 2017年11月24日 下午3:01:14
    * 
    * @Description: 查询关联该员工的账号
    * @param staffId
    * @return
    *
    */
   public List<User> selectByStaffId(Long staffId);
   
   /**
    * 
    * @date 2017年11月24日 下午3:01:14
    * 
    * @Description: 查询该职位下面的所有账号
    * @param jobId
    * @return
    *
    */
   public List<User> selectByJobId(Long jobId);
   
   /**
    * 
    * @date 2017年11月24日 下午3:01:14
    * 
    * @Description: 查询该角色下面的所有账号
    * @param roleId
    * @return
    *
    */
   public List<User> selectByRoleId(Long roleId);

   /**
    * 
    * @date 2018年2月26日 上午11:31:42
    * @author lhj
    * @Description: 查询全部
    * @return
    *
    */
   public List<User> qryAllUser();

   /**
    * 
    * @date 2018年2月26日 上午11:33:43
    * @author lhj
    * @Description: 根据用户姓名查询员工Id
    * @param account
    * @return
    *
    */
   public Staff qryStaffIdByUsername(String account);

   /**
    * 
    * @date 2018年2月26日 下午12:03:47
    * @author lhj
    * @Description: 根据姓名查询Id
    * @param user
    *
    */
   public List<User> checkByUser(User oaasUser);
   
   /**
    * 获取可选的账号列表
    * @param userType
    * @return
    */
   public List<User> getAvaliableAccounts(Map<String,Object> params);
   
   /**
    * 
    * @Description:获取已绑定的帐号
    * @author Administrator
    * @param params
    * @return
    */
   public List<User> getBindingAccounts(Map<String,Object> params);
   
   /**
    * 根据ID获取帐号
    * @Description:
    * @author Administrator
    * @param id
    * @return
    */
   public User getById(Long id);
   
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