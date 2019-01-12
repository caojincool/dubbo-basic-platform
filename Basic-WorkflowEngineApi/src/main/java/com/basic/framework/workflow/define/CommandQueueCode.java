package com.basic.framework.workflow.define;

/**
 * 
 *
 * @date 2017年8月1日 上午9:59:05
 * 
 * @Description: 流程指令队列编码
 *
 */
public enum CommandQueueCode {
	/**
	 * 发送指令队列
	 */
	WORKFLOW_COMMAND_SENDCOMMAND_QUEUE("workflow.command.sendcommand.queue","发送指令队列"),
	;
	
	private String code;
	private String comment;
	
	private CommandQueueCode(String code, String comment){
		this.setCode(code);
		this.setComment(comment);
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

}
