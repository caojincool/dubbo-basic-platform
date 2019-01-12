package com.basic.order.define;

import java.io.Serializable;

/**
 * 
 *
 * @date 2017年8月14日 下午4:46:04
 * 
 * @Description: 流程单据，获取表主键的gidCode的定义
 *
 */
public class GidCodes implements Serializable{

	private static final long serialVersionUID = -2617279197655016260L;
	
//	public static final String TEST_SEQ = "TEST_SEQ";//测试seq
//	public static final String PF_ORDER_TYPE_SEQ = "PF_ORDER_TYPE_SEQ";//单据类型seq
//	public static final String PF_ORDER_PRIORITY_SEQ = "PF_ORDER_PRIORITY_SEQ";//优先级seq
	public static final String PF_ORDER_STATE_COMPONENT_SEQ = "PF_ORDER_STATE_COMPONENT_SEQ";//单据状态变更组件seq
//	public static final String PF_ORDER_STATE_SEQ = "PF_ORDER_STATE_SEQ";//单据状态seq
//	public static final String PF_WORK_ORDER_STATE_SEQ = "PF_WORK_ORDER_STATE_SEQ";//任务单状态seq
	public static final String PF_ORDER_SERVICE_SEQ = "PF_ORDER_SERVICE_SEQ";//服务seq
	public static final String PF_PROCESS_RULE_SEQ = "PF_PROCESS_RULE_SEQ";//流程适配规则seq
	public static final String PF_TACHE_CATALOG_SEQ = "PF_TACHE_CATALOG_SEQ";//环节目录seq
	public static final String PF_TACHE_SEQ = "PF_TACHE_SEQ";//环节seq
	public static final String PF_WO_COMPONENT_SEQ = "PF_WO_COMPONENT_SEQ";//任务组件seq
	public static final String PF_WO_DISP_RULE_SEQ = "PF_WO_DISP_RULE_SEQ";//任务派发规则seq
	public static final String PF_WO_DISP_RULE_INST_SEQ = "PF_WO_DISP_RULE_INST_SEQ";//任务派发规则实例seq
	
	public static final String PF_BUTTON_SEQ = "PF_BUTTON_SEQ";//按钮seq
	public static final String PF_ORDER_BUTTON_SEQ = "PF_ORDER_BUTTON_SEQ";//单据按钮seq
	public static final String PF_WO_BUTTON_SEQ = "PF_WO_BUTTON_SEQ";//任务按钮seq
	
	public static final String OF_ORDER_SEQ = "OF_ORDER_SEQ";//流程单据seq
	public static final String OF_ORDER_FOLLOW_USER_SEQ = "OF_ORDER_FOLLOW_USER_SEQ";//流程关注人seq
	public static final String OF_ORDER_OPER_SEQ = "OF_ORDER_OPER_SEQ";//流程单据操作记录seq
	public static final String OF_WORK_ORDER_SEQ = "OF_WORK_ORDER_SEQ";//流程任务单seq
	public static final String OF_WORK_ORDER_OPER_SEQ = "OF_WORK_ORDER_OPER_SEQ";//流程任务操作记录seq

	public static final String SYS_NOTICE_SEQ = "SYS_NOTICE_SEQ";

	public static final String SYS_NOTICE_PARTY_SEQ = "SYS_NOTICE_PARTY_SEQ";
	
}
