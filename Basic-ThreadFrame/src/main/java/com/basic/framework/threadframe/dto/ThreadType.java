package com.basic.framework.threadframe.dto;

/**
 * Created by lzj on 2017/6/29.
 */
public enum ThreadType {

	/**
	 * 生产者线程
	 */
    PRODUCER_THREAD("PRODUCER_THREAD","生产者线程"),
    /**
	 * 消费者线程
	 */
    CONSUMER_THREAD("CONSUMER_THREAD","消费者线程"),

    ;
    private String code;
    private String comment;

    //构造方法
    private ThreadType(String code, String comment) {
        this.code = code;
        this.comment = comment;
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
