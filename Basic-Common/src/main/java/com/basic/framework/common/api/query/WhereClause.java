package com.basic.framework.common.api.query;

import java.io.Serializable;

/**
 * 构建SQL语句中的Where条件组件的SQL片段
 * @author csx
 */
public interface WhereClause  extends  Serializable {
    /**
     * 返回SQL片段
     */
    public String getSql();
}
