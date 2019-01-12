package com.basic.order.define;

/**
 * 
 *
 * @date 2017年8月17日 下午3:25:51
 * 
 * @Description: 流程单据状态
 *
 */
public enum OrderStateEnum {

	//流程单据状态
	NOT_START("10I","流程未启动"),
	STARTING("1I","启动处理中"),
	NORMAL("10N","正常执行中"),
	FINISH("10F","竣工"),
	INVALID("10X","已作废"),
	ERROR("10E","异常"),//目前只有创建任务时产生的异常
	RETURN("10R","已回退"),
	;
	
	private String code;
	private String comment;
	
	//构造方法
	private OrderStateEnum(String code, String comment) {
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
	public static OrderStateEnum getByCode(String code) {

		for (OrderStateEnum state : OrderStateEnum.values()) {
			if (state.getCode().equals(code)) {
				return state;
			}
		}
		return null;
	}

	public static String getNameByCode(String code) {

		for (OrderStateEnum state : OrderStateEnum.values()) {
			if (state.getCode().equals(code)) {
				return state.getComment();
			}
		}
		return null;
	}
}
