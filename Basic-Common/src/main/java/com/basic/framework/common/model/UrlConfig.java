package com.basic.framework.common.model;

import java.io.Serializable;

public class UrlConfig implements Serializable {
    protected static final long serialVersionUID = -1563590072023033889L;

    private String smsUrl;

    private String x5Url;

    public String getSmsUrl() {
        return smsUrl;
    }

    public void setSmsUrl(String smsUrl) {
        this.smsUrl = smsUrl;
    }

    public String getX5Url() {
        return x5Url;
    }

    public void setX5Url(String x5Url) {
        this.x5Url = x5Url;
    }
}
