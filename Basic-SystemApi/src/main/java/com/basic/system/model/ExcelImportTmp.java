package com.basic.system.model;

import java.io.Serializable;

public class ExcelImportTmp  implements Serializable{

	private static final long serialVersionUID = -4499715025465439463L;

	private String templateCode;

    private String templateName;

    private Long readFileInfoId;

    private String readFileInfoName;

    private Long importFileInfoId;

    private String importFileInfoName;

    private String comments;
    
    
    private String pageDateType;//新增：CREATE;修改：UPDATE
    
    public String getTemplateCode() {
        return templateCode;
    }

    public void setTemplateCode(String templateCode) {
        this.templateCode = templateCode;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public Long getReadFileInfoId() {
        return readFileInfoId;
    }

    public void setReadFileInfoId(Long readFileInfoId) {
        this.readFileInfoId = readFileInfoId;
    }

    public String getReadFileInfoName() {
        return readFileInfoName;
    }

    public void setReadFileInfoName(String readFileInfoName) {
        this.readFileInfoName = readFileInfoName;
    }

    public Long getImportFileInfoId() {
        return importFileInfoId;
    }

    public void setImportFileInfoId(Long importFileInfoId) {
        this.importFileInfoId = importFileInfoId;
    }

    public String getImportFileInfoName() {
        return importFileInfoName;
    }

    public void setImportFileInfoName(String importFileInfoName) {
        this.importFileInfoName = importFileInfoName;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

	public String getPageDateType() {
		return pageDateType;
	}

	public void setPageDateType(String pageDateType) {
		this.pageDateType = pageDateType;
	}
}