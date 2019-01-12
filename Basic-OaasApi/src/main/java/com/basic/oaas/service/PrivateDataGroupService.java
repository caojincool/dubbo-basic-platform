/**
 *
 * companyName
 * copyright 2015-2020
 * @date 2017年8月8日 上午9:55:02
 * @author Kevin
 * @Description: TODO(这里用一句话描述这个类的作用)
 * 
 */
package com.basic.oaas.service;

import java.util.List;

import com.basic.framework.common.api.db.domain.PageJson;
import com.basic.framework.common.db.serverinterface.BaseServer;
import com.basic.framework.common.model.DefaultQueryFilter;
import com.basic.oaas.bean.PrivateDataGroupIbean;
import com.basic.oaas.model.PrivateDataGroup;


/**
 *
 * @date 2017年8月8日 上午9:55:02
 * @author Kevin
 * @Description: 数据权限分组接口
 * 
 */
public interface PrivateDataGroupService extends BaseServer<Long, PrivateDataGroup> {
	
	/**
	 * 
	 * @date 2017年9月13日 上午10:25:34
	 * @author Kevin
	 * @Description: 根据主键删除
	 * @param dataGroupId
	 * @return
	 *
	 */
	public int removeByPrimaryKey(Long dataGroupId);

	/**
     * 
     * @date 2017年9月13日 上午10:25:40
     * @author Kevin
     * @Description: 新增一条数据
     * @param record
     * @return
     *
     */
	public int createSelective(PrivateDataGroup record);

	 /**
     * 
     * @date 2017年9月13日 上午10:25:44
     * @author Kevin
     * @Description: 根据主键查询
     * @param dataGroupId
     * @return
     *
     */
	public PrivateDataGroup qryByPrimaryKey(Long dataGroupId);

	/**
	 * 
	 * @date 2017年8月8日 上午10:05:02
	 * @author Kevin
	 * @Description: 根据主键修改
	 * @param record
	 * @return
	 *
	 */
	public int modifyByPrimaryKeySelective(PrivateDataGroup record);
	
	/**
	 * 
	 * @date 2017年8月8日 上午10:05:20
	 * @author Kevin
	 * @Description: 根据参数查询
	 * @param ibean
	 * @return
	 *
	 */
    public List<PrivateDataGroup> qryPrivateDataGroupList(PrivateDataGroupIbean ibean);
    
    /**
     * 
     * @date 2017年8月8日 上午10:05:44
     * @author Kevin
     * @Description: 根据参数查询总数
     * @param ibean
     * @return
     *
     */
    public long qryPrivateDataGroupListCount(PrivateDataGroupIbean ibean);
    
    /**
     * 
     * @date 2017年8月8日 上午10:06:05
     * @author Kevin
     * @Description: 处理新增修改操作
     * @param ibean
     * @return 
     *
     */
    public PrivateDataGroup createOrModify(PrivateDataGroup ibean);
    
    /**
     * 
     * @date 2017年8月8日 上午10:06:05
     * @author Kevin
     * @Description: 批量删除
     * @param dataGroupIds
     * @return
     *
     */
    public int removeBatchByDataGroupIds(Long[] dataGroupIds);

    /**
     * 分页查询
     * @param queryFilter
     * @return
     */
	PageJson queryPage(DefaultQueryFilter queryFilter);
    
    
}
