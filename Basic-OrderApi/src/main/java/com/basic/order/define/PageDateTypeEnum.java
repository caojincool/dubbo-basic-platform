package com.basic.order.define;

/**
 * 
 *
 * @date 2017年8月31日 上午11:54:37
 * 
 * @Description: 页面操作类型
 *
 */
public enum PageDateTypeEnum {

	//页面操作类型
	CREATE("CREATE","新增"),
	UPDATE("UPDATE","修改"),
	;
	
	private String code;
	private String comment;
	
	//构造方法
	private PageDateTypeEnum(String code, String comment) {
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
	public static PageDateTypeEnum getByCode(String code) {

		for (PageDateTypeEnum state : PageDateTypeEnum.values()) {
			if (state.getCode().equals(code)) {
				return state;
			}
		}
		return null;
	}

	public static String getNameByCode(String code) {

		for (PageDateTypeEnum state : PageDateTypeEnum.values()) {
			if (state.getCode().equals(code)) {
				return state.getComment();
			}
		}
		return null;
	}
}
