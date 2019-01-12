package com.basic.oaas.dao;

import java.util.List;
import java.util.Map;

import com.basic.oaas.bean.UserDataInstIbean;
import com.basic.oaas.model.UserDataInst;

/**
 * 
 *
 * @date 2017年9月17日 下午9:26:35
 * @author Kevin
 * @Description: 账号权限实例
 *
 */
public interface UserDataInstMapper {
	/**
	 * 
	 * @date 2017年8月16日 上午11:47:55
	 * @author Kevin
	 * @Description: 根据主键删除
	 * @param userDataInstId
	 * @return
	 *
	 */
    public int deleteByPrimaryKey(Long userDataInstId);

    /**
     * 
     * @date 2017年8月16日 上午11:48:13
     * @author Kevin
     * @Description: 新增一条数据
     * @param record
     * @return
     *
     */
    public int insertSelective(UserDataInst record);

    /**
     * 
     * @date 2017年8月16日 上午11:48:34
     * @author Kevin
     * @Description: 根据主键查询
     * @param userDataInstId
     * @return
     *
     */
    public UserDataInst selectByPrimaryKey(Long userDataInstId);

    /**
     * 
     * @date 2017年8月16日 上午11:50:37
     * @author Kevin
     * @Description: 根据主键修改
     * @param record
     * @return
     *
     */
    public int updateByPrimaryKeySelective(UserDataInst record);
    
    
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
    public int insertBatchUserDataInst(List<UserDataInst> record);
    
    /**
     * 
     * @date 2017年9月5日 下午3:08:18
     * @author Kevin
     * @Description: 根据用户Id获取用户的的权限
     * @param ibean
     * @return
     *
     */
    public List<UserDataInst> selectUserDataInstList(Map<String,Object> parms);

}