package com.basic.framework.workflow.define;

/**
 * Created by lzj on 2017/7/21.
 */
public enum NoticeType {

	/**
	 * 流程启动
	 */
    PROCESS_START("PROCESS_START", "流程启动"),
    /**
	 * 任务创建
	 */
    TASK_CREATE("TASK_CREATE", "任务创建"),
    /**
	 * 流程报竣
	 */
    PROCESS_COMPLETE("PROCESS_COMPLETE", "流程报竣"),
    /**
	 * 流程回退完成
	 */
    PROCESS_RETURN_FINISH("PROCESS_RETURN_FINISH", "流程回退完成"),
	;

    private String code ;
    private String name ;

    private NoticeType(String code , String name ){
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
