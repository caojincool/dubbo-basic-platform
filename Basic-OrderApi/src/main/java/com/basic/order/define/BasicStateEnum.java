package com.basic.order.define;

/**
 * 
 *
 * @date 2017年8月30日 下午4:38:05
 * 
 * @Description: 基本状态
 *
 */
public enum BasicStateEnum {

	/**
	 * 正常
	 */
	NORMAL("10A","正常"),//删除状态 - 正常
	/**
	 * 失效
	 */
	INVALID("10X","失效"),//删除状态 - 失效
	;
	
	private String code;
	private String comment;
	
	//构造方法
	private BasicStateEnum(String code, String comment) {
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
	public static BasicStateEnum getByCode(String code) {

		for (BasicStateEnum state : BasicStateEnum.values()) {
			if (state.getCode().equals(code)) {
				return state;
			}
		}
		return null;
	}

	public static String getNameByCode(String code) {

		for (BasicStateEnum state : BasicStateEnum.values()) {
			if (state.getCode().equals(code)) {
				return state.getComment();
			}
		}
		return null;
	}
}
