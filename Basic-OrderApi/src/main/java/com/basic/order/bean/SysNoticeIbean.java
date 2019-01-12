/**
 *
 * companyName
 * copyright 2015-2020
 * @date 2018年1月25日 下午2:39:34
 * @author Administrator
 * @Description: TODO(这里用一句话描述这个类的作用)
 * 
 */
package com.basic.order.bean;

import java.io.Serializable;
import java.util.Date;

import com.basic.framework.common.model.PageBean;

/**
 *
 * @date 2018年1月25日 下午2:39:34
 * @author wufenghao
 * @Description: 消息盒子查询Bean
 * 
 */
public class SysNoticeIbean extends PageBean implements Serializable{
	
	private static final long serialVersionUID = -2120710765960959184L;

	private Date dateTime1;//查询头时间
	
	private Date dateTime2;//查询尾时间
	
	private String sysNoticeContent;//消息内容
	
	private String templateCode;//导出模板编码
	
	
	

	/**
	 * @return the templateCode
	 */
	public String getTemplateCode() {
		return templateCode;
	}

	/**
	 * @param templateCode the templateCode to set
	 */
	public void setTemplateCode(String templateCode) {
		this.templateCode = templateCode;
	}

	public Date getDateTime1() {
		return dateTime1;
	}

	public void setDateTime1(Date dateTime1) {
		this.dateTime1 = dateTime1;
	}

	public Date getDateTime2() {
		return dateTime2;
	}

	public void setDateTime2(Date dateTime2) {
		this.dateTime2 = dateTime2;
	}

	public String getSysNoticeContent() {
		return sysNoticeContent;
	}

	public void setSysNoticeContent(String sysNoticeContent) {
		this.sysNoticeContent = sysNoticeContent;
	}

}
