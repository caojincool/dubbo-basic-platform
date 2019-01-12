package com.basic.log.define;

/**
 * 
 *
 * @date 2017年10月13日 下午3:39:19
 * 
 * @Description: 系统日志，操作类型
 *
 */
public enum LogTypeEnum {

	/**
	 * 登录
	 */
	LOGIN("LOGIN","登录"),
	/**
	 * 菜单
	 */
	MENU("MENU","菜单"),
	/**
	 * 操作
	 */
	FUNC("FUNC","操作"),
	;
	
	private String code;
	private String comment;
	
	//构造方法
	private LogTypeEnum(String code, String comment) {
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
	public static LogTypeEnum getByCode(String code) {

		for (LogTypeEnum state : LogTypeEnum.values()) {
			if (state.getCode().equals(code)) {
				return state;
			}
		}
		return null;
	}

	public static String getNameByCode(String code) {

		for (LogTypeEnum state : LogTypeEnum.values()) {
			if (state.getCode().equals(code)) {
				return state.getComment();
			}
		}
		return null;
	}
}
