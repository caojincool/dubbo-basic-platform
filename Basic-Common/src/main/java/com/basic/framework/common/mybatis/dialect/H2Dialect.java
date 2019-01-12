package com.basic.framework.common.mybatis.dialect;


import com.basic.framework.common.mybatis.Dialect;

/**
 * H2Dialect数据库方言
 * <pre> 
 * H2Dialect数据库方言
 * 构建组：x5-base-db
 * 作者：ray
 * 邮箱:zhangyg@jee-soft.cn
 * 日期:2014-1-10-上午10:58:40
 * 版权：company
 * </pre>
 */
public class H2Dialect extends Dialect {

    @Override
    public boolean supportsLimit() {
        return true;
    }

	@Override
    public String getLimitString(String sql, int offset, String offsetPlaceholder, int limit, String limitPlaceholder) {
		return new StringBuffer(sql.length() + 40).
			append(sql).
			append((offset > 0) ? " limit "+limitPlaceholder+" offset "+offsetPlaceholder : " limit "+limitPlaceholder).
			toString();
	}

	@Override
	public boolean supportsLimitOffset() {
		return true;
	}
 
    

}