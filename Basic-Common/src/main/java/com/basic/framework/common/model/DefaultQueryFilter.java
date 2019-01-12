package com.basic.framework.common.model;

import com.basic.framework.common.api.Page;
import com.basic.framework.common.api.db.domain.DefaultFieldSort;
import com.basic.framework.common.api.query.*;
import com.basic.framework.common.mybatis.support.DefaultPage;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


public class DefaultQueryFilter   implements QueryFilter , Serializable  {
	/**
     * 分页组件
     */
    private Page page = new DefaultPage();

    private String whereSql ;

    private String orderBySql;
    /**
     * 排序字段
     */
    private List<FieldSort> fieldSortList = new ArrayList<FieldSort>();
    /**
     * 字段参数构建列表
     */
    private Map<String, Object> params = new LinkedHashMap<String, Object>();
    /**
     * 字段参数组合关系列表
     */
    private FieldLogic fieldLogic = new DefaultFieldLogic();


    @Override
    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }

    @Override
    public Map<String, Object> getParams() {
    	initParams(this.fieldLogic);
    	return params;
    }

    public DefaultQueryFilter() {
    }

    public DefaultQueryFilter(FieldLogic fieldLogic) {
        this.fieldLogic = fieldLogic;
        //initParams(fieldLogic);
    }

    @Override
    public FieldLogic getFieldLogic() {
        return fieldLogic;
    }

    public void setFieldLogic(FieldLogic fieldLogic) {
        this.fieldLogic = fieldLogic;
        //initParams(fieldLogic);
    }

    //初始化参数
    private void initParams(FieldLogic fedLog) {
        if( null != fedLog ){
            List<WhereClause> list = fedLog.getWhereClauses();
            List<String> fields = new ArrayList<String>();
            for (WhereClause clause : list) {
                if (clause instanceof QueryField) {
                    QueryField queryField = (QueryField) clause;
                    if (QueryOP.IS_NULL.equals(queryField.getCompare())
                            || QueryOP.NOTNULL.equals(queryField.getCompare())) {
                        continue;
                    }
                    //如果查询字段包含数据库别名，参数设置去掉别名
                    String fileNameString = queryField.getField();
                    if(fileNameString.indexOf(".")> -1){
                        fileNameString = fileNameString.substring(fileNameString.indexOf(".")+1);
                    }
                    if (fields.contains(fileNameString)) {
                        fileNameString = fileNameString+"#"+queryField.getValue();
                        queryField.setField(queryField.getField()+"#"+queryField.getValue());
                    }
                    fields.add(fileNameString);
                    this.params.put(fileNameString, queryField.getValue());
                } else if (clause instanceof FieldLogic) {
                    FieldLogic fdTemp = (FieldLogic) clause;
                    initParams(fdTemp);
                }
            }
        }

    }

    @Override
    public List<FieldSort> getFieldSortList() {
        return fieldSortList;
    }

    public void setFieldSortList(List<FieldSort> fieldSortList) {
        this.fieldSortList = fieldSortList;
    }
    
    
    /**
     * 添加排序配置。
     * @param orderField	排序字段
     * @param orderSeq		排序
     */
    public void addFieldSort(String orderField,String orderSeq){
    	fieldSortList.add(new DefaultFieldSort(orderField, Direction.fromString(orderSeq)));
    }

	@Override
    public void addFilter(String name, Object obj, QueryOP queryType) {
		fieldLogic.getWhereClauses().add(new DefaultQueryField(name, queryType, obj));
	}
	
	@Override
    public void addParamsFilter(String key, Object obj) {
		this.params.put(key, obj);
	}

    public String getWhereSql() {
        return params.get(whereSql)+"";
    }

    public void setWhereSql(String whereSql) {
        this.whereSql = params.get(whereSql)+"";
    }

    public String getOrderBySql() {
        return orderBySql;
    }

    public void setOrderBySql(String orderBySql) {
        this.orderBySql = orderBySql;
    }
}
