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

import com.basic.oaas.bean.UserPrivateIbean;
import com.basic.oaas.model.UserPrivate;

/**
 *
 * @date 2017年7月5日 上午10:18:22
 * @author Kevin
 * @Description: 账号权限
 * 
 */
public interface UserPrivateService {
	
	/**
	 * 
	 * @date 2017年7月7日 下午5:18:11
	 * @author Kevin
	 * @Description: 新增
	 * @param UserPrivate
	 * @return
	 *
	 */
	public int createSelective(UserPrivate userPrivate);
	
	/**
	 * 
	 * @date 2017年7月7日 下午5:18:31
	 * @author Kevin
	 * @Description: 主键查询
	 * @param UserPrivateId
	 * @return
	 *
	 */
	public UserPrivate qryByPrimaryKey(Long userPrivateId);
	
	/**
	 * 
	 * @date 2017年7月7日 下午5:18:51
	 * @author Kevin
	 * @Description: 根据主键修改
	 * @param UserPrivate
	 * @return
	 *
	 */
	public int modifyByPrimaryKey(UserPrivate userPrivate);
	
	/**
	 * 
	 * @date 2017年7月7日 下午5:19:07
	 * @author Kevin
	 * @Description: 根据主键删除
	 * @param UserPrivateId
	 * @return
	 *
	 */
	public int removeByPrimaryKey(Long userPrivateId);
	
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
    public int createBatchUserPrivate(UserPrivateIbean ibean);
    
    
    /**
     * 
     * @date 2017年9月5日 下午3:08:18
     * @author Kevin
     * @Description: 根据用户Id获取用户的的权限
     * @param userId
     * @return
     *
     */
    public List<UserPrivate> qryUserPrivateByUserId(Long userId);
	
    /**
     * 
     * @Description:批量删除
     * @author lengzj
     * @param userId 
     * @param ids
     * @return
     */
    public int deleteBatchByIds(Long userId, Long[] ids);
}
