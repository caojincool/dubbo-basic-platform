package com.basic.framework.web.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 *
 * @date 2017年6月28日 下午5:51:19
 * 
 * @Description: 获取系统当前时间
 *
 */
public class DateNow implements Serializable {
	
	private static final long serialVersionUID = -5847437833716440229L;
	
	private Date now;//当前时间
	private long millisecond;//当前时间毫秒数
	private String ymd;//年月日
	private String hms;//时分秒
	
	public Date getNow() {
		return now;
	}
	public void setNow(Date now) {
		this.now = now;
	}
	public long getMillisecond() {
		return millisecond;
	}
	public void setMillisecond(long millisecond) {
		this.millisecond = millisecond;
	}
	public String getYmd() {
		return ymd;
	}
	public void setYmd(String ymd) {
		this.ymd = ymd;
	}
	public String getHms() {
		return hms;
	}
	public void setHms(String hms) {
		this.hms = hms;
	}

}