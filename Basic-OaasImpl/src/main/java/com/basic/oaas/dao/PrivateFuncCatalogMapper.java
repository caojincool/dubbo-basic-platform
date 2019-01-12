package com.basic.oaas.dao;

import com.basic.oaas.model.PrivateFuncCatalog;

import java.util.List;

/**
 * 
 *
 * @date 2017年8月30日 下午2:21:04
 * @author Kevin
 * @Description: 功能按钮目录表
 *
 */
public interface PrivateFuncCatalogMapper {
	
	/**
	 * 
	 * @date 2017年8月8日 下午2:11:17
	 * @author Kevin
	 * @Description: 根据主键删除
	 * @param catalogId
	 * @return
	 *
	 */
	public int deleteByPrimaryKey(Long catalogId);

	/**
     * 
     * @date 2017年8月8日 下午2:11:29
     * @author Kevin
     * @Description: 新增一条数据
     * @param record
     * @return
     *
     */
	public int insertSelective(PrivateFuncCatalog record);

	/**
     * 
     * @date 2017年8月8日 下午2:11:33
     * @author Kevin
     * @Description: 根据主键查询
     * @param catalogId
     * @return
     *
     */
	public PrivateFuncCatalog selectByPrimaryKey(Long catalogId);

	/**
     * 
     * @date 2017年8月8日 下午2:11:36
     * @author Kevin
     * @Description: 根据主键修改
     * @param record
     * @return
     *
     */
	public int updateByPrimaryKeySelective(PrivateFuncCatalog record);
	
	/**
     * 
     * @date 2017年8月8日 下午2:49:34
     * @author Kevin
     * @Description: 根据parentId 查询子权限目录
     * @param parentCatalogId
     * @return
     *
     */
    public List<PrivateFuncCatalog> selectSubByParentCatalogId(Long parentCatalogId);
    
    /**
     * 
     * @date 2017年8月8日 下午3:13:04
     * @author Kevin
     * @Description: 根据parentId 查询是否存在子目录
     * @param parentCatalogId
     * @return
     *
     */
    public int selectExistSubFuncCatalog(Long parentCatalogId);
    
    
    /**
     * 
     * @date 2017年8月25日 上午9:29:55
     * @author Kevin
     * @Description: 修改节点下的全部状态
     * @param catalogId
     * @return
     *
     */
    public int updateAllStateById(Long catalogId);

}