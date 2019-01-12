package com.basic.log.bean;

import java.io.Serializable;
import java.util.Date;

import com.basic.framework.common.model.PageBean;

/**
 * 
 *
 * @date 2017年10月13日 上午11:18:21
 * 
 * @Description: 系统日志查询参数
 *
 */
public class SystemLogIbean extends PageBean implements Serializable {

	private static final long serialVersionUID = 6742183745853177675L;
	
	private String requestUrl;
    private String logType;
    private String userText;
    private Date beginTime;
    private Date endTime;
    
	public String getRequestUrl() {
		return requestUrl;
	}
	public void setRequestUrl(String requestUrl) {
		this.requestUrl = requestUrl;
	}
	public String getLogType() {
		return logType;
	}
	public void setLogType(String logType) {
		this.logType = logType;
	}
	public String getUserText() {
		return userText;
	}
	public void setUserText(String userText) {
		this.userText = userText;
	}
	public Date getBeginTime() {
		return beginTime;
	}
	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

}