package com.basic.order.define;

/**
 * 
 *
 * @date 2017年8月18日 下午2:16:13
 * 
 * @Description: 流程任务单状态
 *
 */
public enum WorkOrderStateEnum {

	//流程任务单状态
	TEN_I("10I","未处理"),
	TEN_G("10G","已提单"),
	TEN_F("10F","已完成"),
	TEN_E("10E","异常"),
	TEN_X("10X","作废"),
	TEN_R("10R","任务回退"),
	TEN_B("10B","驳回"),
	TEN_D("10D","删除"),
	;
	
	private String code;
	private String comment;
	
	//构造方法
	private WorkOrderStateEnum(String code, String comment) {
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
	public static WorkOrderStateEnum getByCode(String code) {

		for (WorkOrderStateEnum state : WorkOrderStateEnum.values()) {
			if (state.getCode().equals(code)) {
				return state;
			}
		}
		return null;
	}

	public static String getNameByCode(String code) {

		for (WorkOrderStateEnum state : WorkOrderStateEnum.values()) {
			if (state.getCode().equals(code)) {
				return state.getComment();
			}
		}
		return null;
	}
}
