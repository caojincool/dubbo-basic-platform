package com.basic.framework.common.db.impl;

import com.basic.framework.common.api.Page;
import com.basic.framework.common.api.baseMapper.Mapper;
import com.basic.framework.common.api.query.QueryFilter;
import com.basic.framework.common.model.DefaultQueryFilter;

import java.io.Serializable;
import java.util.List;

/**
 * <pre> 
 * 描述：抽象实体业务管理类实现
 * 构建组：x5-base-db
 * 作者：csx
 * 邮箱:chensx@jee-soft.cn
 * 日期:2013-12-20-下午8:41:38
 * 版权：company
 * </pre>
 */
public abstract class AbstractManagerImpl<PK extends Serializable, T extends Serializable> implements Mapper<PK, T> {
	//获取基础类

    protected abstract Mapper<PK, T > getDao();
    /*
     * (non-Javadoc)
     * @see com.hotent.base.manager.api.Manager#create(java.lang.Object)
     */
    @Override
    public void create(T entity) {
        getDao().create(entity);
    }
    /*
     * (non-Javadoc)
     * @see com.hotent.base.manager.api.Manager#update(java.lang.Object)
     */
    @Override
    public void update(T entity) {
        getDao().update(entity);
    }
    /*
     * (non-Javadoc)
     * @see com.hotent.base.manager.api.Manager#remove(java.io.Serializable)
     */
    @Override
    public void remove(PK entityId) {
        getDao().remove(entityId);
    }
   /*
    * (non-Javadoc)
    * @see com.hotent.base.manager.api.Manager#get(java.io.Serializable)
    */
    @Override
    public T get(PK entityId) {
        return getDao().get(entityId);
    }
    
    @Override
    public List<T> getByIds(List<PK> entityIds, String idColumn) {
    	return getDao().getByIds(entityIds, idColumn);
    }
    
    /*
     * (non-Javadoc)
     * @see com.hotent.base.manager.api.Manager#removeByIds(PK[])
     */
    @Override
    public void removeByIds(PK... ids) {
        if(ids!=null){
            for(PK pk:ids){
                remove(pk);
            }
        }
    }
    /*
     * (non-Javadoc)
     * @see com.hotent.base.manager.api.Manager#query(com.hotent.base.api.query.QueryFilter)
     */
    @Override
    public List<T> query(DefaultQueryFilter queryFilter) {
        return getDao().query(queryFilter);
    }
//    public T getUnique(String column, Object value) {
//    	return getDao().getUniqueByColumn(column, value);
//    }
    /*
     * (non-Javadoc)
     * @see com.hotent.base.manager.api.Manager#getAll()
     */
    @Override
    public List<T> getAll() {
        return getDao().getAll();
    }
    /*
     * (non-Javadoc)
     * @see com.hotent.base.manager.api.Manager#getAllByPage(com.hotent.base.api.Page)
     */
    @Override
    public List<T> getAllByPage(Page page) {
        return getDao().getAllByPage(page);
    }
}
