package com.basic.framework.common.datasource;

import com.basic.framework.common.utils.PropertyUtil;

/**
 * spring数据源，设置当前数据源。<br>
 * 
 * 设置方法：<br/>
 * 
 * <pre>
 * ApplicationContext c = new ClassPathXmlApplicationContext(locations);
 * 
 * DbContextHolder.setDbType(&quot;dataSource_Default2&quot;);
 * manager.addOracle();
 * 
 * DbContextHolder.setDefaultDbType();
 * manager.addMysql();
 * </pre>
 */
public class DbContextHolder {
	private static final ThreadLocal<String> CONTEXT_HOLDER_ALIAS = new ThreadLocal<String>();
	private static final ThreadLocal<String> CONTEXT_HOLDER_DB_TYPE = new ThreadLocal<String>();

	/**
	 * 
	 * 设置当前数据库。
	 * 
	 * @param dbAlias
	 *            :数据源别名
	 * @param dbType
	 *            ：数据源的类型：oracle,mysql... void
	 */
	public static void setDataSource(String dbAlias, String dbType) {
		CONTEXT_HOLDER_ALIAS.set(dbAlias);
		CONTEXT_HOLDER_DB_TYPE.set(dbType);
	}

	public static void setDefaultDataSource() {
		String dbType = PropertyUtil.getJdbcType();
		CONTEXT_HOLDER_ALIAS.set(DataSourceUtil.DEFAULT_DATASOURCE);
		CONTEXT_HOLDER_DB_TYPE.set(dbType);
	}

	/**
	 * 取得当前数据源。
	 * 
	 * @return
	 */
	public static String getDataSource() {
		String str = (String) CONTEXT_HOLDER_ALIAS.get();
		return str;
	}

	public static String getDbType() {
		String str = (String) CONTEXT_HOLDER_DB_TYPE.get();
		return str;
	}

	/**
	 * 清除上下文数据
	 */
	public static void clearDataSource() {
		CONTEXT_HOLDER_ALIAS.remove();
		CONTEXT_HOLDER_DB_TYPE.remove();
	}

}
