package com.basic.framework.workflow.define;

/**
 * Created by lzj on 2017/7/21.
 */
public enum NoticeExecFlag {

	/**
	 * 成功
	 */
    SUCCESS("SUCCESS", "成功"),
    /**
	 * 失败
	 */
    FAILURE("FAILURE", "失败");

    private String code ;
    private String name ;

    private NoticeExecFlag(String code , String name ){
        this.code = code ;
        this.name = name ;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
