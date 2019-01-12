package com.basic.framework.common.baseServerImpl;

import com.basic.framework.common.api.Page;
import com.basic.framework.common.api.baseDao.Dao;
import com.basic.framework.common.api.db.domain.PageList;
import com.basic.framework.common.api.query.FieldSort;
import com.basic.framework.common.api.query.QueryFilter;
import com.basic.framework.common.api.query.QueryOP;
import com.basic.framework.common.model.DefaultQueryFilter;
import com.basic.framework.common.mybatis.support.DefaultPage;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public abstract class BaseServerImpl<PK extends Serializable,T extends Serializable> implements Dao<PK, T> {
    @Resource
    protected SqlSessionTemplate sqlSession;


    /**
     * 按ID获取单一记录
     */
    protected final static String OP_GET=".get";
    /**
     * 按业务主键获取单一记录
     */
    protected final static String OP_GET_BY_ENTITY_KEY=".getByEntityKey";
    /**
     * 按ID删除记录
     */
    protected final static String OP_DEL=".remove";
    /**
     * 按ID更新记录
     */
    protected final static String OP_UPD=".update";
    /**
     * 添加记录
     */
    protected final static String OP_CREATE=".create";
    /**
     * 查询记录列表
     */
    protected final static String OP_QUERY=".query";

    /**
     * 返回当前实体的命名空间字符串名称
     */
    public abstract String getNamespace();

    /**
     * 添加记录
     */
//    public void create(T entity) {
//        if(entity instanceof AbstractModel){
//        	@SuppressWarnings("unchecked")
//			AbstractModel<String> model = (AbstractModel<String>)entity;
//            if(model.getId()==null){
//                model.setId(idGenerator.getSuid());
//            }
//            if(model.getCreateTime()==null){
//            	model.setCreateTime(new java.util.Date());
//            }
//            publishEvent(model,0);
//
//        }
//        sqlSession.insert(getNamespace() + OP_CREATE, entity);
//    }
//
    /**
     * 更新记录
     */
//    public void update(T entity) {
//        if(entity instanceof AbstractModel){
//        	AbstractModel model = (AbstractModel)entity;
//            model.setUpdateTime(new java.util.Date());
//            publishEvent(model,1);
//        }
//        sqlSession.update(getNamespace() + OP_UPD, entity);
//    }
//
//    private void publishEvent(AbstractModel model,int type){
//    	EntityEvent event=new EntityEvent(model);
//        event.setActionType(type);
//        AppUtil.publishEvent(event);
//    }

    /**
     * 根据ID删除记录。
     */
    @Override
    public void remove(PK entityId) {
        sqlSession.delete(getNamespace() + OP_DEL, entityId);
    }

    /**
     * 根据ID获取记录。
     */
    @Override
    public T get(PK entityId) {
        @SuppressWarnings("unchecked")
        T selectOne = (T)sqlSession.selectOne(getNamespace() + OP_GET,entityId);
        return selectOne;
    }

    @Override
    public List<T> getByIds(List<PK> entityIds, String idColumn) {
        DefaultQueryFilter queryFilter = new DefaultQueryFilter();
        if (idColumn == null) {
            idColumn = "ID_";
        }
        queryFilter.addFilter(idColumn, entityIds, QueryOP.IN);
        return query(queryFilter);
    }

    /**
     * 根据ID删除记录。
     */
    @Override
    public void removeByIds(PK... ids) {
        if(ids!=null){
            for(PK pk:ids){
                remove(pk);
            }
        }
    }

    /**
     * 查询所有记录不做分页。
     */
    @Override
    public List<T> getAll() {
        return sqlSession.selectList(getNamespace() + OP_QUERY, null);
    }

    /**
     * 分页查询记录。
     */
    @Override
    public PageList getAllByPage(Page page){
        DefaultPage p=(DefaultPage)page;

        return (PageList) sqlSession.selectList(getNamespace() + OP_QUERY, null,p);
    }

    /**
     * 根据queryFilter查询，查询key固定为query。
     */
    @Override
    public List<T> query(DefaultQueryFilter queryFilter) {

        Map<String, Object> params = queryFilter.getParams();
        //构建动态条件SQL
        String dynamicWhereSql = queryFilter.getFieldLogic().getSql();

        //默认条件过虑
        String defaultWhere = params.containsKey("defaultWhere") ? params.get("defaultWhere").toString() : "";
        if (StringUtils.isNotEmpty(defaultWhere)) {
            dynamicWhereSql = StringUtils.isNotEmpty(dynamicWhereSql) ? dynamicWhereSql + " and " + defaultWhere : defaultWhere;
        }

        if (StringUtils.isNotEmpty(dynamicWhereSql)) {
            params.put("whereSql", dynamicWhereSql);
        }
        //构建排序SQL
        if (queryFilter.getFieldSortList().size() > 0) {
            StringBuffer sb = new StringBuffer();
            for (FieldSort fieldSort : queryFilter.getFieldSortList()) {
                sb.append(fieldSort.getField()).append(" ").append(fieldSort.getDirection()).append(",");
            }
            sb.deleteCharAt(sb.length() - 1);
            params.put("orderBySql", sb.toString());
        }
        List<T> list = null;

        if (queryFilter.getPage() == null) {
            return sqlSession.selectList(getNamespace() + OP_QUERY, params);
        } else {

            DefaultPage p = new DefaultPage(new RowBounds(queryFilter.getPage().getStartIndex(), queryFilter.getPage().getPageSize()));
            return sqlSession.selectList(getNamespace() + OP_QUERY, params, p);


        }
    }

    @Override
    public T getUniqueByColumn(String column, Object value) {
        DefaultQueryFilter queryFilter = new DefaultQueryFilter();
        queryFilter.addFilter(column, value, QueryOP.EQUAL);
        List<T> list = query(queryFilter);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    /**
     * 根据查询key,QueryFilter返回数据。
     * @param sqlKey
     * @param queryFilter
     * @return
     * List
     */
    public List getByQueryFilter(String sqlKey,QueryFilter queryFilter){
        Map<String,Object> params=queryFilter.getParams();
        //构建动态条件SQL
        String dynamicWhereSql=queryFilter.getFieldLogic().getSql();
        if(StringUtils.isNotEmpty(dynamicWhereSql)){
            params.put("whereSql", dynamicWhereSql);
        }
        //构建排序SQL
        if(queryFilter.getFieldSortList().size()>0){
            StringBuffer sb=new StringBuffer();
            for(FieldSort fieldSort: queryFilter.getFieldSortList()){
                sb.append(fieldSort.getField()).append(" ").append(fieldSort.getDirection()).append(",");
            }
            sb.deleteCharAt(sb.length()-1);
            params.put("orderBySql", sb.toString());
        }

        if(queryFilter.getPage()==null){
            return sqlSession.selectList(getNamespace() +"."+ sqlKey, params);
        }else{
            DefaultPage p=new DefaultPage(new RowBounds(queryFilter.getPage().getStartIndex(), queryFilter.getPage().getPageSize()));
            return sqlSession.selectList(getNamespace() +"."+ sqlKey, params,p);
        }
    }




    /**
     * 根据在Map配置文件中的Sql Key及参数获取唯一实体
     * @param sqlKey Mapping文件的sql id
     * @param params 参数集合
     * @return 有唯一值则返回一个实体，无值返回null，结果大于1，则抛错。
     */
    public T getUnique(String sqlKey,Map<String,Object> params){
        return (T) sqlSession.selectOne(getNamespace() + "." +sqlKey, params);

    }

    /**
     * 返回列表。
     * @param sqlKey
     * @param params
     * @return List
     */
    public List getList(String sqlKey,Map<String,Object> params){
        return  sqlSession.selectList(getNamespace() + "." +sqlKey, params);

    }
    /**
     * 根据在Map配置文件中的Sql Key及参数获取唯一实体
     * @param sqlKey Mapping文件的sql id
     * @param params 参数
     * @return 有唯一值则返回一个实体，无值返回null，结果大于1，则抛错。
     */
    public T getUnique(String sqlKey,Object params){
        return (T) sqlSession.selectOne(getNamespace() + "."+sqlKey, params);

    }

    /**
     * 返回单条数据，如 select count(*) from table_a
     * @param sqlKey
     * @param params
     * @return
     */
    public Object getOne(String sqlKey,Object params)
    {
        return sqlSession.selectOne(getNamespace() + "." + sqlKey, params);
    }

    /**
     * 根据在Map配置文件中的Sql Key及参数获取实列表
     * @param sqlKey
     * @param params
     * @return
     */
    public List<T> getByKey(String sqlKey,Map<String,Object> params){
        return sqlSession.selectList(getNamespace() + "." + sqlKey , params);
    }
    /**
     * 根据在Map配置文件中的Sql Key及参数分页获取实列表
     * @param sqlKey
     * @param params
     * @param page
     * @return
     */
    public PageList getByKey(String sqlKey, Map<String,Object> params, Page page){
        return (PageList)sqlSession.selectList(getNamespace() + "." + sqlKey , params,new RowBounds(page.getStartIndex(), page.getPageSize()));
    }


    /**
     * 根据在Map配置文件中的Sql Key及参数获取实列表
     * @param sqlKey
     * @param param
     * @return
     */
    public List<T> getByKey(String sqlKey,Object param){
        return sqlSession.selectList(getNamespace() + "." + sqlKey , param);
    }


    /**
     * 根据在Map配置文件中的Sql Key及参数分页获取实列表
     * @param sqlKey
     * @param param
     * @param page
     * @return
     */
    public List<T> getByKey(String sqlKey,Object param,Page page){
        return sqlSession.selectList(getNamespace() + "." + sqlKey , param,new RowBounds(page.getStartIndex(), page.getPageSize()));
    }

    /**
     * 根据在Map配置文件中的Sql Key及参数更新数据
     * @param sqlKey
     * @param params
     */
    public int updateByKey(String sqlKey,Map<String,Object> params){
        return sqlSession.update(getNamespace() + "." + sqlKey, params);

    }

    /**
     * 根据在Map配置文件中的Sql Key及参数更新数据
     * @param sqlKey
     * @param params
     */
    public int updateByKey(String sqlKey,Object params){
        return  sqlSession.update(getNamespace() + "." + sqlKey, params);

    }

    /**
     * 根据在Map配置文件中的Sql Key更新数据
     * @param sqlKey
     */
    public int updateByKey(String sqlKey){
        return sqlSession.update(getNamespace() + "." + sqlKey);
    }

    public int insertByKey(String sqlKey,Object params){
        return  sqlSession.insert(getNamespace() + "." + sqlKey, params);
    }
    /**
     * 根据在Map配置文件中的Sql Key及参数删除数据
     * @param sqlKey
     * @param params
     */
    public int deleteByKey(String sqlKey,Map<String,Object> params){
        return sqlSession.delete(getNamespace() + "." + sqlKey, params);
    }

    /**
     * 根据在Map配置文件中的Sql Key及参数删除数据
     * @param sqlKey
     * @param params
     */
    public int deleteByKey(String sqlKey,Object params){
        return  sqlSession.delete(getNamespace() + "." + sqlKey, params);
    }

    /**
     * 根据在Map配置文件中的Sql Key删除数据
     * @param sqlKey
     */
    public int deleteByKey(String sqlKey){
        return sqlSession.delete(getNamespace() + "." + sqlKey);
    }
    protected Map<String,Object> buildMap(String paramName,Object paramValue){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(paramName, paramValue);
        return map;
    }
    /**
     * 获得一个MapBuilder，通过它快速添加参数。
     * @return
     */
//    protected MyBatisDaoImpl.MapBuilder buildMapBuilder() {
//        return new MyBatisDaoImpl.MapBuilder();
//    }
    /**
     * <pre>
     * 描述：使用例子：buildMapBuilder().addParam("procDefId",procDefId).addParam("nodeId",nodeId).getParams()
     * 构建组：x5-base-db
     * 作者：winston yan
     * 邮箱:yancm@jee-soft.cn
     * 日期:2014-1-18-上午10:47:36
     * 版权：company
     * </pre>
     */
//    protected class MapBuilder{
//        Map<String, Object> map = new HashMap<String, Object>();
//        protected MapBuilder(){
//        }
//        public MyBatisDaoImpl.MapBuilder addParam(String paramKey, Object paramValue){
//            map.put(paramKey, paramValue);
//            return this;
//        }
//        public Map<String, Object> getParams(){
//            return map;
//        }
//    }

    /**
     * 返回sqlSession
     * @return
     */
    public SqlSessionTemplate getsqlSession()
    {
        return sqlSession;
    }

}
