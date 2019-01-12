package com.basic.oaas.dao;

import java.util.List;
import java.util.Map;

import com.basic.oaas.model.UserPrivate;

/**
 * 
 *
 * @date 2017年9月1日 下午5:30:43
 * @author Kevin
 * @Description: 账号权限表
 *
 */
public interface UserPrivateMapper {
	/**
	 * 
	 * @date 2017年8月16日 上午11:47:55
	 * @author Kevin
	 * @Description: 根据主键删除
	 * @param userPrivateId
	 * @return
	 *
	 */
    public int deleteByPrimaryKey(Long userPrivateId);

    /**
     * 
     * @date 2017年8月16日 上午11:48:13
     * @author Kevin
     * @Description: 新增一条数据
     * @param record
     * @return
     *
     */
    public int insertSelective(UserPrivate record);

    /**
     * 
     * @date 2017年8月16日 上午11:48:34
     * @author Kevin
     * @Description: 根据主键查询
     * @param userPrivateId
     * @return
     *
     */
    public UserPrivate selectByPrimaryKey(Long userPrivateId);

    /**
     * 
     * @date 2017年8月16日 上午11:50:37
     * @author Kevin
     * @Description: 根据主键修改
     * @param record
     * @return
     *
     */
    public int updateByPrimaryKeySelective(UserPrivate record);
    
    /**
     * 
     * @Description:更新所有字段
     * @author lengzj
     * @param record
     * @return
     */
    public int updateByPrimaryKey(UserPrivate record);
    

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
    public int insertBatchUserPrivate(List<UserPrivate> record);
    
    /**
     * 
     * @date 2017年9月5日 下午3:08:18
     * @author Kevin
     * @Description: 根据用户Id获取用户的的权限
     * @param userId
     * @return
     *
     */
    public List<UserPrivate> selectUserPrivateByUserId(Long userId);
    
    /**
     * 
     * @Description:检查是否已经存在
     * @author lengzj
     * @param params
     * @return
     */
    public int checkExist(Map<String,Object> params);
    
    /**
     * 
     * @Description:批量删除
     * @author lengzj
     * @param ids
     * @return
     */
    public int deleteBatchByIds(Map<String,Object> params);
}