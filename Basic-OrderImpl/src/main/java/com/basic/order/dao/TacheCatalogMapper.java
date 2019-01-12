package com.basic.order.dao;

import java.util.List;

import com.basic.order.model.TacheCatalog;

public interface TacheCatalogMapper {
    int deleteByPrimaryKey(Long catalogId);

    int insertSelective(TacheCatalog record);

    TacheCatalog selectByPrimaryKey(Long catalogId);

    int updateByPrimaryKeySelective(TacheCatalog record);
    
    /**
     * 
     * @date 2017年9月4日 下午3:39:47
     * 
     * @Description: 根据父id查询有效的数据
     * @param parentCatalogId
     * @return
     *
     */
    public List<TacheCatalog> selectByParentId(Long parentCatalogId);
}