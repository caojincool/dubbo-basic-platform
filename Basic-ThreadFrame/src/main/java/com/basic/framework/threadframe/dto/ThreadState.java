package com.basic.framework.threadframe.dto;

/**
 * Created by lzj on 2017/6/29.
 * 线程状态,只在查询时使用
 */
public class ThreadState {

    private String threadType;
    private int threadIndex;
    private int dataBufferSize;
    private Long curMsgId;
    private String threadStateName;

    public String getThreadType() {
        return threadType;
    }

    public void setThreadType(String threadType) {
        this.threadType = threadType;
    }


    public int getThreadIndex() {
        return threadIndex;
    }

    public void setThreadIndex(int threadIndex) {
        this.threadIndex = threadIndex;
    }

    public int getDataBufferSize() {
        return dataBufferSize;
    }

    public void setDataBufferSize(int dataBufferSize) {
        this.dataBufferSize = dataBufferSize;
    }

    public Long getCurMsgId() {
        return curMsgId;
    }

    public void setCurMsgId(Long curMsgId) {
        this.curMsgId = curMsgId;
    }

    public String getThreadStateName() {
        return threadStateName;
    }

    public void setThreadStateName(String threadStateName) {
        this.threadStateName = threadStateName;
    }
}
