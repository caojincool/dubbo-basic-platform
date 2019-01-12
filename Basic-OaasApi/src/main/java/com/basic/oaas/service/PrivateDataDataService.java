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
import java.util.Map;

import com.basic.framework.common.api.db.domain.PageJson;
import com.basic.framework.common.db.serverinterface.BaseServer;
import com.basic.framework.common.model.DefaultQueryFilter;
import com.basic.oaas.bean.PrivateDataDataIbean;
import com.basic.oaas.model.PrivateDataData;


/**
 *
 * @date 2017年8月8日 上午9:55:02
 * @author Kevin
 * @Description: 数据权限数据接口
 * 
 */
public interface PrivateDataDataService extends BaseServer<Long, PrivateDataData> {
	
	/**
	 * 
	 * @date 2017年8月8日 上午9:59:59
	 * @author Kevin
	 * @Description: 根据主键删除
	 * @param dataDataId
	 * @return
	 *
	 */
	public int removeByPrimaryKey(Long dataDataId);

	/**
	 * 
	 * @date 2017年8月8日 上午10:03:38
	 * @author Kevin
	 * @Description: 新增一条数据
	 * @param record
	 * @return
	 *
	 */
	public int createSelective(PrivateDataData record);

	/**
	 * 
	 * @date 2017年8月8日 上午10:04:11
	 * @author Kevin
	 * @Description: 根据主键查询
	 * @param dataDataId
	 * @return
	 *
	 */
	public PrivateDataData qryByPrimaryKey(Long dataDataId);

	/**
	 * 
	 * @date 2017年8月8日 上午10:05:02
	 * @author Kevin
	 * @Description: 根据主键修改
	 * @param record
	 * @return
	 *
	 */
	public int modifyByPrimaryKeySelective(PrivateDataData record);
	

	 /**
	 * 
	 * @date 2017年8月2日 下午2:35:05
	 * @author Kevin
	 * @Description: 根据参数查询
	 * @param ibean
	 * @return
	 *
	 */
    public List<PrivateDataData> qryPrivateDataDataList(PrivateDataDataIbean ibean);
    
    /**
     * 
     * @date 2017年8月2日 下午2:35:17
     * @author Kevin
     * @Description: 根据参数查询的总数
     * @param ibean
     * @return
     *
     */
    public long qryPrivateDataDataListCount(PrivateDataDataIbean ibean);

    /**
     * 分页查询
     * @param queryFilter
     * @return
     */
	PageJson queryPage(DefaultQueryFilter queryFilter);
	
	/**
     * 根据源数据删除相关数据
     * @param params
     * @return
     */
	int deleteBySource(Long sourceId, String sourceType);
	
	/**
	 * 
	 * @Description:根据源数据ID获取数据权限
	 * @author lengzj
	 * @param sourceId
	 * @param sourceType
	 * @return
	 */
	PrivateDataData getBySource(Long sourceId, String sourceType);
	
  
}
