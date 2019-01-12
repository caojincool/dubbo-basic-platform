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
import com.basic.oaas.bean.PrivateFuncIbean;
import com.basic.oaas.model.PrivateFunc;


/**
 *
 * @date 2017年8月8日 上午9:55:02
 * @author Kevin
 * @Description: 功能按钮接口
 * 
 */
public interface PrivateFuncService extends BaseServer<Long, PrivateFunc> {
	
	/**
	 * 
	 * @date 2017年8月8日 上午9:59:59
	 * @author Kevin
	 * @Description: 根据主键删除
	 * @param funcId
	 * @return
	 *
	 */
	public int removeByPrimaryKey(Long funcId);

	/**
	 * 
	 * @date 2017年8月8日 上午10:03:38
	 * @author Kevin
	 * @Description: 新增一条数据
	 * @param record
	 * @return
	 *
	 */
	public int createSelective(PrivateFunc record);

	/**
	 * 
	 * @date 2017年8月8日 上午10:04:11
	 * @author Kevin
	 * @Description: 根据主键查询
	 * @param funcId
	 * @return
	 *
	 */
	public PrivateFunc qryByPrimaryKey(Long funcId);

	/**
	 * 
	 * @date 2017年8月8日 上午10:05:02
	 * @author Kevin
	 * @Description: 根据主键修改
	 * @param record
	 * @return
	 *
	 */
	public int modifyByPrimaryKeySelective(PrivateFunc record);
	
    /**
     * 
     * @date 2017年8月8日 上午10:06:05
     * @author Kevin
     * @Description: 处理新增修改操作
     * @param ibean
     *
     */
    public void createOrModify(PrivateFunc ibean);
    
    /**
     * 
     * @date 2017年8月8日 上午10:06:05
     * @author Kevin
     * @Description: 批量删除，正常情况下，别的模块只修改状态
     * @param funcIds
     * @return
     *
     */
    public int removeBatchByPrivateFuncIds(Long[] funcIds);
    
    /**
     * 
     * @date 2017年9月22日 下午3:23:12
     * @author Kevin
     * @Description: 根据功能编码获取功能按钮
     * @param funcCode
     * @return
     *
     */
    public List<PrivateFunc> qryFuncByFuncCode(String[] funcCode);

	PageJson queryPage(DefaultQueryFilter queryFilter);
}
