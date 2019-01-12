package com.basic.order.define;

/**
 * 
 *
 * @date 2017年8月18日 下午2:21:59
 * 
 * @Description: 流程单据操作记录-->操作类型
 *
 */
public enum OrderOperTypeEnum {

//	1. 启动流程
//	OPER_TYPE:FLOW_START
//	OPER_INFO:流程启动
//
//
//	2.重置流程
//	OPER_TYPE:FLOW_RESET
//	OPER_INFO:流程重置
//
//	3.流程跳转
//	OPER_TYPE:FLOW_JUMP
//	OPER_INFO:流程跳转，当前环节:??，目标环节:??
//
//	4.单据作废
//	OPER_TYPE:ORDER_DEL
//	OPER_COMMENTS:单据作废，当前环节:??
	FLOW_START("FLOW_START","流程启动"),
	FLOW_RESET("FLOW_RESET","流程重置"),
	FLOW_JUMP("FLOW_JUMP","流程跳转"),
	ORDER_DEL("ORDER_DEL","单据作废"),
	;
	
	private String code;
	private String comment;
	
	//构造方法
	private OrderOperTypeEnum(String code, String comment) {
		this.code = code;
		this.comment = comment;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return the comment
	 */
	public String getComment() {
		return comment;
	}

	/**
	 * @param comment the comment to set
	 */
	public void setComment(String comment) {
		this.comment = comment;
	}
	public static OrderOperTypeEnum getByCode(String code) {

		for (OrderOperTypeEnum state : OrderOperTypeEnum.values()) {
			if (state.getCode().equals(code)) {
				return state;
			}
		}
		return null;
	}

	public static String getNameByCode(String code) {

		for (OrderOperTypeEnum state : OrderOperTypeEnum.values()) {
			if (state.getCode().equals(code)) {
				return state.getComment();
			}
		}
		return null;
	}
}
