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

import com.basic.framework.common.api.db.domain.PageJson;
import com.basic.framework.common.db.serverinterface.BaseServer;
import com.basic.framework.common.model.DefaultQueryFilter;
import com.basic.oaas.bean.PrivateDataGroupInstIbean;
import com.basic.oaas.model.PrivateDataGroupInst;


/**
 *
 * @date 2017年8月8日 上午9:55:02
 * @author Kevin
 * @Description: 数据权限分组接口
 * 
 */
public interface PrivateDataGroupInstService extends BaseServer<Long, PrivateDataGroupInst> {
	
	/**
	 * 
	 * @date 2017年9月13日 上午10:25:34
	 * @author Kevin
	 * @Description: 根据主键删除
	 * @param dataGrpInstId
	 * @return
	 *
	 */
	public int removeByPrimaryKey(Long dataGrpInstId);

	/**
     * 
     * @date 2017年9月13日 上午10:25:40
     * @author Kevin
     * @Description: 新增一条数据
     * @param record
     * @return
     *
     */
	int createSelective(PrivateDataGroupInstIbean record);

	 /**
     * 
     * @date 2017年9月13日 上午10:25:44
     * @author Kevin
     * @Description: 根据主键查询
     * @param dataGroupId
     * @return
     *
     */
	public PrivateDataGroupInst qryByPrimaryKey(Long dataGrpInstId);

	/**
	 * 
	 * @date 2017年8月8日 上午10:05:02
	 * @author Kevin
	 * @Description: 根据主键修改
	 * @param record
	 * @return
	 *
	 */
	public int modifyByPrimaryKeySelective(PrivateDataGroupInst record);
	
	/**
     * 
     * @date 2017年8月8日 上午10:06:05
     * @author Kevin
     * @Description: 批量删除
     * @param dataGrpInstId
     * @return
     *
     */
    public int removeBatchByDataGrpInstId(Long[] dataGrpInstId);

    /**
     * 分页查询
     * @param queryFilter
     * @return
     */
	PageJson queryPage(DefaultQueryFilter queryFilter);

	
}
