package com.basic.framework.threadframe.dto;

/**
 * Created by lzj on 2017/6/29.
 * 派发模式
 */
public enum DispatchMode {

    /**
     * hash散列派发
     */
    HASH_MODE("HASH_CODE","hash散列"),
    /**
     * 负载均衡
     */
    BALANCE_MODE("BALANCE_MODE","负载均衡"),

    /**
     * 随机派发
     */
    RANDOM_MODE("RANDOM_MODE","随机派发"),

       ;
    private String code;
    private String comment;

    //构造方法
    private DispatchMode(String code, String comment) {
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
