package com.basic.order.define;

/**
 * 
 *
 * @date 2017年8月21日 下午5:29:17
 * 
 * @Description: 任务派发规则-->规则类型
 *
 */
public enum RuleTypeEnum {

//	RULE_TYPE
//	AREA 按区域派单
//	ORG 按组织派单
//	USER 按用户派单
//	STA 按员工派单
//	SELF 自定义
	
	AREA("AREA","按区域派单"),
	ORG("ORG","按组织派单"),
	USER("USER","按用户派单"),
	STA("STA","按员工派单"),
	SELF("SELF","自定义"),
	;
	
	private String code;
	private String comment;
	
	//构造方法
	private RuleTypeEnum(String code, String comment) {
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
	public static RuleTypeEnum getByCode(String code) {

		for (RuleTypeEnum state : RuleTypeEnum.values()) {
			if (state.getCode().equals(code)) {
				return state;
			}
		}
		return null;
	}

	public static String getNameByCode(String code) {

		for (RuleTypeEnum state : RuleTypeEnum.values()) {
			if (state.getCode().equals(code)) {
				return state.getComment();
			}
		}
		return null;
	}
}
