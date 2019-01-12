package com.basic.framework.workflow.exception;

/**
 * Created by lzj on 2017/7/21.
 */
public class WorkflowException extends Exception{

    private String errorCode;
    private String errorMsg;

    public WorkflowException(String errorCode,String errorMsg,Exception e){
        super(e);
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public WorkflowException(String errorCode,String errorMsg){
        super();
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
