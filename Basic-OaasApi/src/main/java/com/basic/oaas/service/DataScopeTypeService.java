/**
 *
 * companyName
 * copyright 2015-2020
 * @date 2017年8月8日 上午9:55:02
 * @author Kevin
 * @Description: 数据范围类型接口
 * 
 */
package com.basic.oaas.service;

import java.util.List;

import com.basic.oaas.model.DataScopeType;


/**
 *
 * @date 2017年8月8日 上午9:55:02
 * @author Kevin
 * @Description: 数据范围类型接口
 * 
 */
public interface DataScopeTypeService {
	
	/**
	 * 
	 * @date 2017年8月8日 上午9:59:59
	 * @author Kevin
	 * @Description: 根据主键删除
	 * @param scopeType
	 * @return
	 *
	 */
	public int removeByPrimaryKey(String scopeType);

	/**
	 * 
	 * @date 2017年8月8日 上午10:03:38
	 * @author Kevin
	 * @Description: 新增一条数据
	 * @param record
	 * @return
	 *
	 */
	public int createSelective(DataScopeType record);

	/**
	 * 
	 * @date 2017年8月8日 上午10:04:11
	 * @author Kevin
	 * @Description: 根据主键查询
	 * @param scopeType
	 * @return
	 *
	 */
	public DataScopeType qryByPrimaryKey(String scopeType);

	/**
	 * 
	 * @date 2017年8月8日 上午10:05:02
	 * @author Kevin
	 * @Description: 根据主键修改
	 * @param record
	 * @return
	 *
	 */
	public int modifyByPrimaryKeySelective(DataScopeType record);
	
	/**
	 * 
	 * @date 2017年8月8日 上午10:05:20
	 * @author Kevin
	 * @Description: 获取所有数据范围类型
	 * @param ibean
	 * @return
	 *
	 */
    public List<DataScopeType> qryAllScopeTypeList();
    
}
