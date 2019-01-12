package com.basic.log.bean;

import java.io.Serializable;

import com.basic.framework.common.model.PageBean;

/**
 * 
 *
 * @date 2017年10月13日 上午11:17:46
 * 
 * @Description: 系统日志配置查询参数
 *
 */
public class SystemConfigIbean extends PageBean implements Serializable {

	private static final long serialVersionUID = 891747024959067969L;
	
	private String requestUrl;

    public String getRequestUrl() {
        return requestUrl;
    }

    public void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl;
    }

}