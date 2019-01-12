package com.basic.framework.common.mybatis.dialect;

import com.basic.framework.common.mybatis.Dialect;

/**
 * mysql方言。
 * <pre> 
 * 描述：mysql方言。
 * 构建组：x5-base-db
 * 作者：ray
 * 邮箱:zhangyg@jee-soft.cn
 * 日期:2014-1-10-上午11:07:39
 * 版权：company
 * </pre>
 */
public class MySQLDialect extends Dialect {

	@Override
    public boolean supportsLimitOffset(){
		return true;
	}
	
    @Override
    public boolean supportsLimit() {
        return true;   
    }  
    
	@Override
    public String getLimitString(String sql, int offset, String offsetPlaceholder, int limit, String limitPlaceholder) {
        if (offset > 0) {   
        	return sql + " limit "+offsetPlaceholder+","+limitPlaceholder; 
        } else {   
            return sql + " limit "+limitPlaceholder;
        }  
	}   
  
}
