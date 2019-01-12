package com.basic.oaas.dao;

import java.util.List;

import com.basic.oaas.bean.UserDataGroupIbean;
import com.basic.oaas.model.UserDataGroup;

/**
 * 
 *
 * @date 2017年9月18日 上午10:17:36
 * @author Kevin
 * @Description: 用户数据分组
 *
 */
public interface UserDataGroupMapper {
	
	/**
	 * 
	 * @date 2017年8月16日 上午11:47:55
	 * @author Kevin
	 * @Description: 根据主键删除
	 * @param userDataGrpId
	 * @return
	 *
	 */
	public int deleteByPrimaryKey(Long userDataGrpId);

	/**
     * 
     * @date 2017年8月16日 上午11:48:13
     * @author Kevin
     * @Description: 新增一条数据
     * @param record
     * @return
     *
     */
	public int insertSelective(UserDataGroup record);

	 /**
     * 
     * @date 2017年8月16日 上午11:48:34
     * @author Kevin
     * @Description: 根据主键查询
     * @param userDataInstId
     * @return
     *
     */
	public UserDataGroup selectByPrimaryKey(Long userDataGrpId);

	/**
     * 
     * @date 2017年8月16日 上午11:50:37
     * @author Kevin
     * @Description: 根据主键修改
     * @param record
     * @return
     *
     */
	public int updateByPrimaryKeySelective(UserDataGroup record);
	
	/**
     * 
     * @date 2017年8月2日 下午3:38:12
     * @author Kevin
     * @Description: 根据用户ID批量删除
     * @param userId
     * @return
     *
     */
    public int deleteBatchByUserId(Long userId);
    
    /**
     * 
     * @date 2017年9月4日 下午4:00:08
     * @author Kevin
     * @Description: 批量插入
     * @param record
     * @return
     *
     */
    public int insertBatchUserDataGroup(List<UserDataGroup> record);
    

}