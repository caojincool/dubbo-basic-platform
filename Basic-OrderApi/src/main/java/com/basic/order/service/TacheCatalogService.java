package com.basic.order.service;

import java.util.List;

import com.basic.order.model.TacheCatalog;

/**
 * 
 *
 * @date 2017年8月17日 上午11:46:50
 * 
 * @Description: 环节目录
 *
 */
public interface TacheCatalogService {
	
	/**
	 * 
	 * @date 2017年8月17日 上午11:47:07
	 * 
	 * @Description: 根据主键删除
	 * @param catalogId
	 * @return
	 *
	 */
	public int removeByPrimaryKey(Long catalogId);

	/**
	 * 
	 * @date 2017年8月17日 上午11:48:09
	 * 
	 * @Description: 新增一条数据
	 * @param record
	 * @return
	 *
	 */
	public int createSelective(TacheCatalog record);

	/**
	 * 
	 * @date 2017年8月17日 上午11:48:19
	 * 
	 * @Description: 根据主键查询
	 * @param catalogId
	 * @return
	 *
	 */
	public TacheCatalog qryByPrimaryKey(Long catalogId);

	/**
	 * 
	 * @date 2017年8月17日 上午11:48:34
	 * 
	 * @Description: 根据主键修改
	 * @param record
	 * @return
	 *
	 */
	public int modifyByPrimaryKeySelective(TacheCatalog record);
	
    /**
     * 
     * @date 2017年9月4日 下午3:39:47
     * 
     * @Description: 根据父id查询有效的数据
     * @param parentCatalogId
     * @return
     *
     */
    public List<TacheCatalog> qryByParentId(Long parentCatalogId);
    
    /**
     * 
     * @date 2017年9月4日 下午4:16:04
     * 
     * @Description: 处理新增修改操作
     * @param ibean
     *
     */
    public void createOrModify(TacheCatalog ibean);
}
