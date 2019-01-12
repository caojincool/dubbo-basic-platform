package com.basic.log.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 *
 * @date 2017年10月13日 上午11:18:21
 * 
 * @Description: 系统日志
 *
 */
public class SystemLog implements Serializable {

	private static final long serialVersionUID = 3726894516896443103L;

	private Long logId;

    private String requestUrl;

    private String requestContent;

    private String logType;

    private Long userId;

    private Date createTime;

    private String custId;
    
    private String userText;

    public Long getLogId() {
        return logId;
    }

    public void setLogId(Long logId) {
        this.logId = logId;
    }

    public String getRequestUrl() {
        return requestUrl;
    }

    public void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl;
    }

    public String getRequestContent() {
        return requestContent;
    }

    public void setRequestContent(String requestContent) {
        this.requestContent = requestContent;
    }

    public String getLogType() {
        return logType;
    }

    public void setLogType(String logType) {
        this.logType = logType;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCustId() {
        return custId;
    }

    public void setCustId(String custId) {
        this.custId = custId;
    }

	/**
	 * @return the userText
	 */
	public String getUserText() {
		return userText;
	}

	/**
	 * @param userText the userText to set
	 */
	public void setUserText(String userText) {
		this.userText = userText;
	}
    
}