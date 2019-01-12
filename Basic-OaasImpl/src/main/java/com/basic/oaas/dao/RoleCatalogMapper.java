package com.basic.oaas.dao;

import java.util.List;

import com.basic.oaas.model.RoleCatalog;

/**
 * 
 *
 * @date 2017年8月31日 上午9:23:05
 * @author Kevin
 * @Description:角色目录表
 *
 */
public interface RoleCatalogMapper {
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
    public int insertSelective(RoleCatalog record);

    /**
     * 
     * @date 2017年8月8日 下午2:11:33
     * @author Kevin
     * @Description: 根据主键查询
     * @param catalogId
     * @return
     *
     */
    public RoleCatalog selectByPrimaryKey(Long catalogId);

    /**
     * 
     * @date 2017年8月8日 下午2:11:36
     * @author Kevin
     * @Description: 根据主键修改
     * @param record
     * @return
     *
     */
    public int updateByPrimaryKeySelective(RoleCatalog record);
    
    /**
     * 
     * @Description:更新所有字段
     * @author lengzj
     * @param record
     * @return
     */
    public int updateByPrimaryKey(RoleCatalog record);
    
    /**
     * 
     * @date 2017年8月8日 下午2:49:34
     * @author Kevin
     * @Description: 根据parentId 查询子权限目录
     * @param parentCatalogId
     * @return
     *
     */
    public List<RoleCatalog> selectSubByParentCatalogId(Long parentCatalogId);
    
    /**
     * 
     * @date 2017年8月8日 下午3:13:04
     * @author Kevin
     * @Description: 根据parentId 查询是否存在子目录
     * @param parentCatalogId
     * @return
     *
     */
    public int selectExistSubRoleCatalog(Long parentCatalogId);
    
    
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