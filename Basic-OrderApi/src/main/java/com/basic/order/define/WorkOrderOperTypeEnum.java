package com.basic.order.define;

/**
 * 
 *
 * @date 2017年8月17日 下午5:55:31
 * 
 * @Description: 流程任务操作记录-->操作类型
 *
 */
public enum WorkOrderOperTypeEnum {

//	1.提单
//	OPER_TYPE:WO_GET
//	OPER_INFO:提前，原处理人：??，新处理人：？？
//
//	2.转派
//	OPER_TYPE:WO_DISP
//	OPER_INFO:转派，原处理人：??，新处理人：？？
//
//	3.回单
//	OPER_TYPE:WO_FINISH
//	OPER_INFO:回单
//
//	4.任务作废
//	OPER_TYPE:WO_DEL
//	OPER_INFO:作废，
//
//	5.回退
//	OPER_TYPE:WO_RETURN
//	OPER_INFO:回退
	WO_GET("WO_GET","提单"),
	WO_DISP("WO_DISP","转派"),
	WO_FINISH("WO_FINISH","回单"),
	WO_DEL("WO_DEL","任务作废"),
	WO_RETURN("WO_RETURN","回退"),
	;
	
	private String code;
	private String comment;
	
	//构造方法
	private WorkOrderOperTypeEnum(String code, String comment) {
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
	public static WorkOrderOperTypeEnum getByCode(String code) {

		for (WorkOrderOperTypeEnum state : WorkOrderOperTypeEnum.values()) {
			if (state.getCode().equals(code)) {
				return state;
			}
		}
		return null;
	}

	public static String getNameByCode(String code) {

		for (WorkOrderOperTypeEnum state : WorkOrderOperTypeEnum.values()) {
			if (state.getCode().equals(code)) {
				return state.getComment();
			}
		}
		return null;
	}
}
