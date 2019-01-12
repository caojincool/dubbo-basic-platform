package com.basic.oaas.define;

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
	
	private static final long serialVersionUID = -4430789796134966620L;
	
	public static final String OAAS_AREA_SEQ = "OAAS_AREA_SEQ";						//区域表
	public static final String OAAS_USER_SEQ = "OAAS_USER_SEQ";						//用户表
	
	public static final String OAAS_ORG_SEQ = "OAAS_ORG_SEQ";	//部门表
	
	public static final String OA_ORG_SEQ = "OA_ORG_SEQ";	
	
	public static final String OAAS_JOB_SEQ ="OAAS_JOB_SEQ";						//职位表
	public static final String OAAS_STAFF_JOB_SEQ = "OAAS_STAFF_JOB_SEQ";			//职位员工表
	public static final String OAAS_STAFF_SEQ = "OAAS_STAFF_SEQ";					//员工表
	public static final String OAAS_USER_STAFF_SEQ = "OAAS_USER_STAFF_SEQ";			//账号员工表
	
	public static final String OAAS_PRIV_CATALOG_SEQ = "OAAS_PRIV_CATALOG_SEQ";		//权限目录表
	public static final String OAAS_PRIVATE_SEQ = "OAAS_PRIVATE_SEQ";				//权限表
	public static final String OAAS_PRIV_DATA_SEQ = "OAAS_PRIV_DATA_SEQ";			//权限数据表
	
	public static final String OAAS_PRIV_MENU_CATALOG_SEQ = "OAAS_PRIV_MENU_CATALOG_SEQ";		//菜单目录数据表
	public static final String OAAS_PRIV_MENU_SEQ = "OAAS_PRIV_MENU_SEQ";			//菜单数据表
	public static final String OAAS_PRIV_FUNC_CATALOG_SEQ = "OAAS_PRIV_FUNC_CATALOG_SEQ";		//功能按钮数据表
	public static final String OAAS_PRIV_FUNC_SEQ = "OAAS_PRIV_FUNC_SEQ";			//按钮数据表
	public static final String OAAS_PRIV_ATTR_SEQ = "OAAS_PRIV_ATTR_SEQ";			//字段数据表
	
	public static final String OAAS_ROLE_SEQ = "OAAS_ROLE_SEQ";						//角色表
	public static final String OAAS_ROLE_CATALOG_SEQ = "OAAS_ROLE_CATALOG_SEQ";		//角色目录表
	public static final String OAAS_ROLE_PRIVATE_SEQ= "OAAS_ROLE_PRIVATE_SEQ";		//角色权限表
	
	public static final String OAAS_USER_ROLE_SEQ= "OAAS_USER_ROLE_SEQ";		//账号权限
	public static final String OAAS_USER_PRIVATE_SEQ= "OAAS_USER_PRIVATE_SEQ";		//账号权限
	public static final String OAAS_PRIV_DATA_INST_SEQ= "OAAS_PRIV_DATA_INST_SEQ";	//数据权限实例
	public static final String OAAS_PRIV_DATA_INST_DATA_SEQ= "OAAS_PRIV_DATA_INST_DATA_SEQ";	//数据权限实例数据
	
	public static final String OAAS_PRIV_DATA_DATA_SEQ= "OAAS_PRIV_DATA_DATA_SEQ";	//数据权限数据
	
	public static final String OAAS_PRIV_DATA_GROUP_SEQ= "OAAS_PRIV_DATA_GROUP_SEQ";	//数据权限分组
	
	public static final String OAAS_PRIV_DATA_GRP_INST_SEQ= "OAAS_PRIV_DATA_GRP_INST_SEQ";	//数据权限分组
	
	public static final String OAAS_USER_DATA_INST_SEQ= "OAAS_USER_DATA_INST_SEQ";	//账号数据权限实例
	public static final String OAAS_USER_DATA_GRP_SEQ= "OAAS_USER_DATA_GRP_SEQ";	//账号数据权限分组
	
	public static final String OAAS_COMPANY_SEQ= "OAAS_COMPANY_SEQ";			//公司信息
	public static final String OAAS_ORG_COMPANY_SEQ= "OAAS_ORG_COMPANY_SEQ";	//公司部门
}
