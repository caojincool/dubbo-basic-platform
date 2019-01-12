package com.basic.framework.workflow.define;

/**
 * Created by lzj on 2017/7/21.
 */
public enum CommandType {

	/**
	 * 流程启动
	 */
    PROCESS_START("PROCESS_START", "流程启动"),
    /**
	 * 流程跳转
	 */
    PROCESS_JUMP("PROCESS_JUMP", "流程跳转"),
    /**
	 * 任务完成
	 */
    TASK_COMPLETE("TASK_COMPLETE", "任务完成"),
    /**
	 * 任务回退
	 */
    TASK_ROLLBACK("TASK_ROLLBACK", "任务回退"),
    /**
	 * 流程挂起
	 */
    PROCESS_SUSPEND("PROCESS_SUSPEND", "流程挂起"),
    ;

    private String code ;
    private String name ;

    private CommandType( String code , String name ){
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
