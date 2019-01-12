package com.basic.framework.common.api.baseDao;

import com.basic.framework.common.api.Page;
import com.basic.framework.common.api.db.domain.PageList;
import com.basic.framework.common.api.query.QueryFilter;
import com.basic.framework.common.model.DefaultQueryFilter;

import java.io.Serializable;
import java.util.List;

/**
 * 数据库访问基础类
 * @author csx
 */
public interface Dao<PK extends Serializable,T> {
    /**
     * 创建实体对象
     * @param entity
     * @return 
     */
    public void create(T entity);
    /**
     * 更新实体对象
     * @param entity
     * @return 
     */
    public void update(T entity);
    /**
     * 按实体ID删除对象
     * @param entityId 
     */
    public void remove(PK entityId);    
    
    /**
     * 按实体ID获取实体
     * @param entityId 
     */
    public T get(PK entityId);
    
	public List<T> getByIds(List<PK> entityIds, String idColumn);    
//    /**
//     * 按实体业务主键获取实体
//     * @param entityKey 
//     */
//    public T getByEntityKey(String entityKey);
    /**
     * 按实体IDs删除记录
     * @param ids 
     */
    public void removeByIds(PK... ids);
    /**
     * 查询实体对象
     * @param queryFilter
     * @return 
     */
    public List<T> query(DefaultQueryFilter queryFilter);
    /**
     * 查询单个实体对象
     * @param
     * @return 
     */
	public T getUniqueByColumn(String column, Object value);
    
    /**
     * 取得所有查询对象
     * @return 
     */
    public List<T> getAll();
    /**
     * 取得所有查询对象并分页查询
     * @param page
     * @return 
     */
    public PageList getAllByPage(Page page);
}
