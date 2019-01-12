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




import com.basic.framework.common.api.db.domain.PageJson;
import com.basic.framework.common.db.serverinterface.BaseServer;
import com.basic.framework.common.model.DefaultQueryFilter;
import com.basic.oaas.bean.UserDataGroupIbean;
import com.basic.oaas.model.UserDataGroup;

/**
 *
 * @date 2017年7月5日 上午10:18:22
 * @author Kevin
 * @Description: 账号数据权限分组实例
 * 
 */
public interface UserDataGroupService extends BaseServer<Long, UserDataGroup> {
	
	/**
	 * 
	 * @date 2017年7月7日 下午5:18:11
	 * @author Kevin
	 * @Description: 新增
	 * @param UserDataGroup
	 * @return
	 *
	 */
	public int createSelective(UserDataGroup userDataGroup);
	
	/**
	 * 
	 * @date 2017年7月7日 下午5:18:31
	 * @author Kevin
	 * @Description: 主键查询
	 * @param UserDataGroupId
	 * @return
	 *
	 */
	public UserDataGroup qryByPrimaryKey(Long userDataGroupId);
	
	/**
	 * 
	 * @date 2017年7月7日 下午5:18:51
	 * @author Kevin
	 * @Description: 根据主键修改
	 * @param UserDataGroup
	 * @return
	 *
	 */
	public int modifyByPrimaryKey(UserDataGroup userDataGroup);
	
	/**
	 * 
	 * @date 2017年7月7日 下午5:19:07
	 * @author Kevin
	 * @Description: 根据主键删除
	 * @param UserDataGroupId
	 * @return
	 *
	 */
	public int removeByPrimaryKey(Long userDataGroupId);
	
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
    public int createBatchUserDataGroup(UserDataGroupIbean ibean);

    /**
     * 分页查询
     * @param queryFilter
     * @return
     */
	PageJson queryPage(DefaultQueryFilter queryFilter);
    
    
	
	
}
