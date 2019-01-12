package com.basic.oaas.dao;

import java.util.List;

import com.basic.oaas.model.RolePrivate;

/**
 * 
 *
 * @date 2017年8月31日 上午9:23:28
 * @author Kevin
 * @Description: 角色权限表
 *
 */
public interface RolePrivateMapper {
	/**
	 * 
	 * @date 2017年8月16日 上午11:47:55
	 * @author Kevin
	 * @Description: 根据主键删除
	 * @param rolePrivateId
	 * @return
	 *
	 */
    public int deleteByPrimaryKey(Long rolePrivateId);

    /**
     * 
     * @date 2017年8月16日 上午11:48:13
     * @author Kevin
     * @Description: 新增一条数据
     * @param record
     * @return
     *
     */
    public int insertSelective(RolePrivate record);

    /**
     * 
     * @date 2017年8月16日 上午11:48:34
     * @author Kevin
     * @Description: 根据主键查询
     * @param rolePrivateId
     * @return
     *
     */
    public RolePrivate selectByPrimaryKey(Long rolePrivateId);

    /**
     * 
     * @date 2017年8月16日 上午11:50:37
     * @author Kevin
     * @Description: 根据主键修改
     * @param record
     * @return
     *
     */
    public int updateByPrimaryKeySelective(RolePrivate record);
    
    /**
     * 
     * @Description:更新所有字段
     * @author lengzj
     * @param record
     * @return
     */
    public int updateByPrimaryKey(RolePrivate record);
    
    /**
     * 
     * @date 2017年8月2日 下午3:38:12
     * @author Kevin
     * @Description: 根据角色ID批量删除
     * @param roleId
     * @return
     *
     */
    public int deleteBatchByRoleId(Long roleId);
    
    /**
     * 
     * @date 2017年9月4日 下午4:00:08
     * @author Kevin
     * @Description: 批量插入
     * @param record
     * @return
     *
     */
    public int insertBatchRolePrivate(List<RolePrivate> record);
    
    /**
     * 
     * @date 2017年9月5日 下午3:08:18
     * @author Kevin
     * @Description: 根据用户Id获取用户的的权限
     * @param roleId
     * @return
     *
     */
    public List<RolePrivate> selectRolePrivateByRoleId(Long roleId);
    
    /**
     * 
     * @date 2017年9月17日 下午8:55:14
     * @author Kevin
     * @Description: 批量删除用户角色
     * @param rolePrivateIds
     * @return
     *
     */
    public int deleteBatchByRolePrivateIds(Long[] rolePrivateIds);

}