package com.basic.framework.common.api.query;

import java.io.Serializable;
import java.util.List;

/**
 * 字段条件组合查询
 * 
 * @author csx
 * 
 */
public interface FieldLogic extends WhereClause ,Serializable {
	public List<WhereClause> getWhereClauses();
}
