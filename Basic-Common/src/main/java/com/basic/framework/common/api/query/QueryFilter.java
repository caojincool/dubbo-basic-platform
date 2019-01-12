package com.basic.framework.common.api.query;

import com.basic.framework.common.api.Page;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 
 * <pre> 
 * 描述：组合条件查询过滤
 * 构建组：x5-base-api
 * 作者：csx
 * 邮箱:chensx@jee-soft.cn
 * 日期:2014-1-3-下午4:01:18
 * 版权：company
 * </pre>
 */
public interface QueryFilter extends  Serializable{
	/**
	 * 返回分页信息
	 * @return
	 */
	public Page getPage();
//	/**
//	 * 返回字段组合查询逻辑
//	 * @return
//	 */
	public FieldLogic getFieldLogic();

	/**
	 * 返回组合的参数映射
	 * @return
	 */
	public Map<String,Object> getParams();
	
	/**
	 * 返回字段排序列表
	 * @return
	 */
	public List<FieldSort> getFieldSortList();
	/**
	 * 添加自定义过滤条件（用于自动组装条件：whereSql）
	 * @param name
	 * @param obj
	 * @param queryTypeBaseServer
	 */
	public void addFilter(String name, Object obj, QueryOP queryType);

	/**
	 * 添加自定义过滤条件（用于手动组装条件，在MAPPING文件判断用的参数）
	 * @param
	 * @param obj
	 */
	public void addParamsFilter(String key, Object obj);

}
