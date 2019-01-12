package com.basic.oaas.dao;

import java.util.List;
import java.util.Map;

import com.basic.oaas.bean.RoleIbean;
import com.basic.oaas.model.Role;

/**
 * 
 *
 * @date 2017年8月31日 上午9:22:42
 * @author Kevin
 * @Description: 角色表
 *
 */
public interface RoleMapper {
	/**
	 * 
	 * @date 2017年8月8日 上午10:56:56
	 * @author Kevin
	 * @Description: 根据主键删除
	 * @param roleId
	 * @return
	 *
	 */
    public int deleteByPrimaryKey(Long roleId);

    /**
	 * 
	 * @date 2017年8月8日 上午10:57:22
	 * @author Kevin
	 * @Description: 新增一条数据
	 * @param record
	 * @return
	 *
	 */
    public int insertSelective(Role record);

    /**
   	 * 
   	 * @date 2017年8月8日 上午10:57:57
   	 * @author Kevin
   	 * @Description: 根据主键查询
   	 * @param roleId
   	 * @return
   	 *
   	 */
    public Role selectByPrimaryKey(Long roleId);

    /**
	 * 
	 * @date 2017年8月8日 上午10:58:20
	 * @author Kevin
	 * @Description: 根据主键修改
	 * @param record
	 * @return
	 *
	 */
    public int updateByPrimaryKeySelective(Role record);
    
    /**
     * 
     * @Description:更新所有字段
     * @author lengzj
     * @param record
     * @return
     */
    public int updateByPrimaryKey(Role record);
    
    /**
	 * 
	 * @date 2017年8月8日 上午10:59:03
	 * @author Kevin
	 * @Description: 根据参数查询
	 * @param ibean
	 * @return
	 *
	 */
	public List<Role> selectRoleList(RoleIbean ibean);
	
	/**
	 * 
	 * @date 2017年8月8日 上午10:59:24
	 * @author Kevin
	 * @Description: 根据参数查询的总数
	 * @param ibean
	 * @return
	 *
	 */
	public Long selectRoleListCount(RoleIbean ibean);
	
	/**
	 * 
	 * @date 2017年8月8日 上午11:01:38
	 * @author Kevin
	 * @Description: 批量删除，正常情况下，别的模块只修改状态
	 * @param roleIds
	 * @return
	 *
	 */
	public int deleteBatchByRoleIds(Long[] roleIds);
	
	/**
	 * 
	 * @date 2017年8月8日 上午11:02:46
	 * @author Kevin
	 * @Description: 批量修改状态
	 * @param roleIds
	 * @return
	 *
	 */
	public int updateBatchByRoleIds(Long[] roleIds);
	
	/**
	 * 
	 * @date 2017年9月19日 下午4:50:54
	 * @author Kevin
	 * @Description: 根据用户名获取用户角色
	 * @param username
	 * @return
	 *
	 */
	public List<Role> selectRoleByUsername(String username);
	
	/**
	 * 
	 * @Description:根据参数统计角色数
	 * @author lengzj
	 * @param params
	 * @return
	 */
	public int countRole(Map<String,Object> params);

}