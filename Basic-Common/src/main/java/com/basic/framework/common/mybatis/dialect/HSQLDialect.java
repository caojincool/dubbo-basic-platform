package com.basic.framework.common.mybatis.dialect;


import com.basic.framework.common.mybatis.Dialect;
/**
 * HSQL数据库方言
 * <pre> 
 * HSQL数据库方言
 * 构建组：x5-base-db
 * 作者：ray
 * 邮箱:zhangyg@jee-soft.cn
 * 日期:2014-1-10-上午11:05:03
 * 版权：company
 * </pre>
 */
public class HSQLDialect extends Dialect {

	@Override
    public boolean supportsLimit() {
		return true;
	}

	@Override
    public boolean supportsLimitOffset() {
		return true;
	}

	@Override
    public String getLimitString(String sql, int offset, String offsetPlaceholder, int limit, String limitPlaceholder) {
		boolean hasOffset = offset>0;
		return new StringBuffer( sql.length() + 10 )
		.append( sql )
		.insert( sql.toLowerCase().indexOf( "select" ) + 6, hasOffset ? " limit "+offsetPlaceholder+" "+limitPlaceholder : " top "+limitPlaceholder )
		.toString();
	}
    
}
