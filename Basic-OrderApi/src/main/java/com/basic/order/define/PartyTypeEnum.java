package com.basic.order.define;

/**
 * 
 *
 * @date 2017年8月18日 下午3:11:08
 * 
 * @Description: 接收人类型
 *
 */
public enum PartyTypeEnum {

//	PARTY_TYPE
//	STA：表示派给员工，PARTY_ID 为OAAS_STAFF.STAFFF_ID
//	USER：表示派给用户，PARTY_ID为OAAS_USER.USER_ID
//	ORG;表示派给部门，PARTY_ID为OAAS_ORG.ORG_ID
//	JOB：表示派给岗位，PARTY_ID为OAAS_JOB.JOB_ID
//	SYS：表示系统，PARTY_ID为外系统，目前为预留类型
	
	STA("STA","员工"),
	USER("USER","用户"),
	ORG("ORG","部门"),
	JOB("JOB","岗位"),
	SYS("SYS","系统"),
	;
	
	private String code;
	private String comment;
	
	//构造方法
	private PartyTypeEnum(String code, String comment) {
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
	public static PartyTypeEnum getByCode(String code) {

		for (PartyTypeEnum state : PartyTypeEnum.values()) {
			if (state.getCode().equals(code)) {
				return state;
			}
		}
		return null;
	}

	public static String getNameByCode(String code) {

		for (PartyTypeEnum state : PartyTypeEnum.values()) {
			if (state.getCode().equals(code)) {
				return state.getComment();
			}
		}
		return null;
	}
}
