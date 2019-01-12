package com.basic.oaas.dao;

import java.util.List;

import com.basic.oaas.model.DataScopeType;

/**
 * 
 *
 * @date 2017年9月7日 下午4:07:01
 * @author Kevin
 * @Description: 数据范围类型
 *	
 */
public interface DataScopeTypeMapper {
	
	/**
	 * 
	 * @date 2017年8月16日 上午11:47:55
	 * @author Kevin
	 * @Description: 根据主键删除
	 * @param scopeType
	 * @return
	 *
	 */
	public int deleteByPrimaryKey(String scopeType);

    /**
     * 
     * @date 2017年8月16日 上午11:48:13
     * @author Kevin
     * @Description: 新增一条数据
     * @param record
     * @return
     *
     */
	public int insertSelective(DataScopeType record);

    /**
     * 
     * @date 2017年8月16日 上午11:48:34
     * @author Kevin
     * @Description: 根据主键查询
     * @param scopeType
     * @return
     *
     */
	public DataScopeType selectByPrimaryKey(String scopeType);

    /**
     * 
     * @date 2017年8月16日 上午11:50:37
     * @author Kevin
     * @Description: 根据主键修改
     * @param record
     * @return
     *
     */
	public int updateByPrimaryKeySelective(DataScopeType record);
	
	/**
	 * 
	 * @date 2017年8月2日 下午2:35:05
	 * @author Kevin
	 * @Description: 根据参数查询
	 * @param ibean
	 * @return
	 *
	 */
    public List<DataScopeType> selectAllTypeList();

}