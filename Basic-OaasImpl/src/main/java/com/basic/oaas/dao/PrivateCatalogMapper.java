package com.basic.oaas.dao;

import java.util.List;

import com.basic.oaas.model.PrivateCatalog;

/**
 * 
 *
 * @date 2017年8月8日 下午2:10:55
 * @author Kevin
 * @Description: 权限目录表
 *
 */
public interface PrivateCatalogMapper {
	
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
    public int insertSelective(PrivateCatalog record);
    
    /**
     * 
     * @date 2017年8月8日 下午2:11:33
     * @author Kevin
     * @Description: 根据主键查询
     * @param catalogId
     * @return
     *
     */
    public PrivateCatalog selectByPrimaryKey(Long catalogId);
    
    /**
     * 
     * @date 2017年8月8日 下午2:11:36
     * @author Kevin
     * @Description: 根据主键修改
     * @param record
     * @return
     *
     */
    public int updateByPrimaryKeySelective(PrivateCatalog record);
    
    /**
     * 
     * @Description:更新所有字段
     * @author lengzj
     * @param record
     * @return
     */
    public int updateByPrimaryKey(PrivateCatalog record);
    
    /**
     * 
     * @date 2017年8月8日 下午2:49:34
     * @author Kevin
     * @Description: 根据parentId 查询子权限目录
     * @param parentCatalogId
     * @return
     *
     */
    public List<PrivateCatalog> selectSubByParentCatalogId(Long parentCatalogId);
    
    /**
     * 
     * @date 2017年8月8日 下午3:13:04
     * @author Kevin
     * @Description: 根据parentId 查询是否存在子目录
     * @param parentCatalogId
     * @return
     *
     */
    public int selectExistSubPrivateCatalog(Long parentCatalogId);
    
    /**
     * 
     * @date 2017年8月8日 下午3:00:23
     * @author Kevin
     * @Description: 批量修改状态
     * @param catalogIds
     * @return
     *
     */
    public int updateBatchByCatalogIds(Long[] catalogIds);
    
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