package com.basic.framework.workflow.define;

/**
 * 
 *
 * @date 2017年8月1日 上午9:59:52
 * 
 * @Description: 流程通知队列编码
 *
 */
public enum NoticeQueueCode {
	/**
	 * 发送通知队列
	 */
	WORKFLOW_NOTICE_SENDNOTICE_QUEUE("workflow.notice.sendnotice.queue","发送通知队列");
	
	private String code;
	private String comment;
	
	private NoticeQueueCode(String code, String comment){
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
