/**
 *
 * companyName
 * copyright 2015-2020
 * @date 2017年8月8日 上午9:55:02
 * @author Kevin
 * @Description: 
 * 
 */
package com.basic.oaas.service;

import java.util.List;

import com.basic.framework.common.api.db.domain.PageJson;
import com.basic.framework.common.db.serverinterface.BaseServer;
import com.basic.framework.common.model.DefaultQueryFilter;
import com.basic.oaas.bean.PrivateDataIbean;
import com.basic.oaas.model.PrivateData;


/**
 *
 * @date 2017年8月8日 上午9:55:02
 * @author Kevin
 * @Description: 数据权限接口
 * 
 */
public interface PrivateDataService extends BaseServer<Long, PrivateData> {
	
	/**
	 * 
	 * @date 2017年8月8日 上午9:59:59
	 * @author Kevin
	 * @Description: 根据主键删除
	 * @param dataId
	 * @return
	 *
	 */
	public int removeByPrimaryKey(Long dataId);

	/**
	 * 
	 * @date 2017年8月8日 上午10:03:38
	 * @author Kevin
	 * @Description: 新增一条数据
	 * @param record
	 * @return
	 *
	 */
	public int createSelective(PrivateData record);

	/**
	 * 
	 * @date 2017年8月8日 上午10:04:11
	 * @author Kevin
	 * @Description: 根据主键查询
	 * @param dataId
	 * @return
	 *
	 */
	public PrivateData qryByPrimaryKey(Long dataId);

	/**
	 * 
	 * @date 2017年8月8日 上午10:05:02
	 * @author Kevin
	 * @Description: 根据主键修改
	 * @param record
	 * @return
	 *
	 */
	public int modifyByPrimaryKeySelective(PrivateData record);
	
	/**
	 * 
	 * @date 2017年8月8日 上午10:05:20
	 * @author Kevin
	 * @Description: 根据参数查询
	 * @param ibean
	 * @return
	 *
	 */
    public List<PrivateData> qryPrivateDataList(PrivateDataIbean ibean);
    
    /**
     * 
     * @date 2017年8月8日 上午10:05:44
     * @author Kevin
     * @Description: 根据参数查询总数
     * @param ibean
     * @return
     *
     */
    public long qryPrivateDataListCount(PrivateDataIbean ibean);
    
    /**
     * 
     * @date 2017年8月8日 上午10:06:05
     * @author Kevin
     * @Description: 处理新增修改操作
     * @param ibean
     *
     */
    public void createOrModify(PrivateData ibean);
    
    /**
     * 
     * @date 2017年8月8日 上午10:06:05
     * @author Kevin
     * @Description: 批量删除，正常情况下，别的模块只修改状态
     * @param dataIds
     * @return
     *
     */
    public int removeBatchByDataIds(Long[] dataIds);
    
    /**
	 * 
	 * @date 2017年8月2日 下午2:35:05
	 * @author Kevin
	 * @Description: 根据参数查询
	 * @param ibean
	 * @return
	 *
	 */
    public List<PrivateData> qryPrivateDataByUserList(PrivateDataIbean ibean);
    
    /**
     * 
     * @date 2017年8月2日 下午2:35:17
     * @author Kevin
     * @Description: 根据参数查询的总数
     * @param ibean
     * @return
     *
     */
    public long qryPrivateDataByUserListCount(PrivateDataIbean ibean);

    /**
     * 分页查询
     * @param queryFilter
     * @return
     */
	PageJson queryPage(DefaultQueryFilter queryFilter);
    
}
