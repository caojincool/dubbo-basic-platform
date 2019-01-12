package com.basic.system.dao;

import java.util.List;

import com.basic.system.bean.TableDictIbean;
import com.basic.system.model.TableDict;

/**
 * 
 *
 * @date 2017年8月2日 下午2:19:55
 * 
 * @Description: 字典表
 *
 */
public interface TableDictMapper {
	
	/**
	 * 
	 * @date 2017年8月2日 下午2:20:21
	 * 
	 * @Description: 根据主键删除
	 * @param dictId
	 * @return
	 *
	 */
	public int deleteByPrimaryKey(Long dictId);

	/**
	 * 
	 * @date 2017年8月2日 下午2:20:47
	 * 
	 * @Description: 新增一条数据
	 * @param record
	 * @return
	 *
	 */
	public int insertSelective(TableDict record);

	/**
	 * 
	 * @date 2017年8月2日 下午2:21:00
	 * 
	 * @Description: 根据主键查询
	 * @param dictId
	 * @return
	 *
	 */
	public TableDict selectByPrimaryKey(Long dictId);

	/**
	 * 
	 * @date 2017年8月2日 下午2:21:09
	 * 
	 * @Description: 根据主键修改
	 * @param record
	 * @return
	 *
	 */
	public int updateByPrimaryKeySelective(TableDict record);
	
	/**
	 * 
	 * @date 2017年8月2日 下午2:35:05
	 * 
	 * @Description: 根据参数查询
	 * @param ibean
	 * @return
	 *
	 */
    public List<TableDict> selectTableDictList(TableDictIbean ibean);
    
    /**
     * 
     * @date 2017年8月2日 下午2:35:17
     * 
     * @Description: 根据参数查询的总数
     * @param ibean
     * @return
     *
     */
    public long selectTableDictListCount(TableDictIbean ibean);
    
    /**
     * 
     * @date 2017年8月2日 下午3:38:12
     * 
     * @Description: 批量删除，正常情况下，别的模块只修改状态
     * @param dictIds
     * @return
     *
     */
    public int deleteBatchByDictIds(Long[] dictIds);
}