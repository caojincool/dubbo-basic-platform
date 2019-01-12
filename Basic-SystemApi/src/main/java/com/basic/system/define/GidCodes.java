package com.basic.system.define;

import java.io.Serializable;

/**
 * 
 *
 * @date 2017年7月3日 下午5:36:55
 * 
 * @Description: 系统设置，获取表主键的gidCode的定义
 *
 */
public class GidCodes implements Serializable{
	
	private static final long serialVersionUID = -4430789796134966610L;
	
	public static final String FILE_CONFIG_SEQ = "FILE_CONFIG_SEQ";//附件配置表
	public static final String FILE_INFO_SEQ = "FILE_INFO_SEQ";//附件表
	public static final String PUB_EXCEL_IMPORT_TMP_SEQ = "PUB_EXCEL_IMPORT_TMP_SEQ";//Excel上传模板表
	public static final String PUB_EXCEL_EXPORT_TMP_SEQ = "PUB_EXCEL_EXPORT_TMP_SEQ";//Excel导出模板表
	public static final String PUB_TABLE_DICT_SEQ = "PUB_TABLE_DICT_SEQ";//字典表
	public static final String BASE_SYS_INTERFACE = "BASE_SYS_INTERFACE_SEQ";//外围接口
	public static final String BASE_SYS_SYSTEM = "BASE_SYS_SYSTEM_SEQ";//系统标识
	public static final String BASE_SYS_SYSLOG = "BASE_SYS_SYSLOG_SEQ";//系统日志
	
}
