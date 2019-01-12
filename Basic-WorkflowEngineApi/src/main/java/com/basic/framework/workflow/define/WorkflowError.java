package com.basic.framework.workflow.define;

/**
 * Created by lzj on 2017/7/21.
 */
public enum WorkflowError {

	/**
	 * 发送指令异常
	 */
    SEND_COMMAND_ERROR_11001("WF_11001", "发送指令异常"),
    /**
	 * 发送通知异常
	 */
    SEND_NOTICE_ERROR_12001("WF_12001","发送通知异常"),
    /**
	 * 流程启动-模板编码为空
	 */
    PROCESS_START_ERROR_01001("WF_01001", "流程启动-模板编码为空"),
    /**
	 * 流程启动-无法找到模板
	 */
    PROCESS_START_ERROR_01002("WF_01002", "流程启动-无法找到模板"),
    /**
	 * 引擎内部错误
	 */
    ENGINE_ERROR_10001("WF_10001", "引擎内部错误"),
    /**
	 * 客户端业务逻辑异常
	 */
    CLIENT_ERROR_20001("WF_20001", "客户端业务逻辑异常");


    private String code ;
    private String name ;

    private WorkflowError(String code , String name ){
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
