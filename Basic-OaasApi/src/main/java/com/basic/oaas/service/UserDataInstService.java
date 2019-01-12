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
import com.basic.oaas.bean.UserDataInstIbean;
import com.basic.oaas.model.UserDataInst;

/**
 *
 * @date 2017年7月5日 上午10:18:22
 * @author Kevin
 * @Description: 账号数据权限实例
 * 
 */
public interface UserDataInstService extends BaseServer<Long, UserDataInst> {
	
	/**
	 * 
	 * @date 2017年7月7日 下午5:18:11
	 * @author Kevin
	 * @Description: 新增
	 * @param UserDataInst
	 * @return
	 *
	 */
	public int createSelective(UserDataInst userDataInst);
	
	/**
	 * 
	 * @date 2017年7月7日 下午5:18:31
	 * @author Kevin
	 * @Description: 主键查询
	 * @param UserDataInstId
	 * @return
	 *
	 */
	public UserDataInst qryByPrimaryKey(Long userDataInstId);
	
	/**
	 * 
	 * @date 2017年7月7日 下午5:18:51
	 * @author Kevin
	 * @Description: 根据主键修改
	 * @param UserDataInst
	 * @return
	 *
	 */
	public int modifyByPrimaryKey(UserDataInst userDataInst);
	
	/**
	 * 
	 * @date 2017年7月7日 下午5:19:07
	 * @author Kevin
	 * @Description: 根据主键删除
	 * @param UserDataInstId
	 * @return
	 *
	 */
	public int removeByPrimaryKey(Long userDataInstId);
	
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
    public int createBatchUserDataInst(UserDataInstIbean ibean);
    
    
    /**
     * 
     * @date 2017年9月5日 下午3:08:18
     * @author Kevin
     * @Description: 根据参数获取账号数据数据权限实例
     * @param ibean
     * @return
     *
     */
    public List<UserDataInst> qryUserDataInstList(UserDataInstIbean ibean);

    /**
     * 分页查询
     * @param queryFilter
     * @return
     */
	PageJson queryPage(DefaultQueryFilter queryFilter);

	/**
	 * 
	 * @Description:获取帐号拥有的数据权限,返回ID
	 * @author lengzj
	 * @param userId 帐号ID
	 * @param scopeType 数据权限范围，枚举PrivateType
	 * @return 数据权限豁免的返回null
	 */
	List<Long> getHasRightIds(Long userId, String scopeType);

	/**
	 * 
	 * @Description:获取帐号拥有的数据权限,返回编码
	 * @author lengzj
	 * @param userId 帐号ID
	 * @param scopeType 数据权限范围，枚举PrivateType
	 * @return 数据权限豁免的返回null
	 */
	List<String> getHasRightCodes(Long userId, String scopeType);
	
	
}
