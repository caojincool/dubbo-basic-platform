package com.basic.log.model;

import java.io.Serializable;

/**
 * 
 *
 * @date 2017年10月13日 上午11:17:46
 * 
 * @Description: 系统日志配置
 *
 */
public class SystemConfig implements Serializable {

	private static final long serialVersionUID = 7526451956892679836L;

	private Long configId;

    private String requestUrl;

    private String requestFlag;

    private String requestContentFlag;

    public Long getConfigId() {
        return configId;
    }

    public void setConfigId(Long configId) {
        this.configId = configId;
    }

    public String getRequestUrl() {
        return requestUrl;
    }

    public void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl;
    }

    public String getRequestFlag() {
        return requestFlag;
    }

    public void setRequestFlag(String requestFlag) {
        this.requestFlag = requestFlag;
    }

    public String getRequestContentFlag() {
        return requestContentFlag;
    }

    public void setRequestContentFlag(String requestContentFlag) {
        this.requestContentFlag = requestContentFlag;
    }
}