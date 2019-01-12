package com.basic.system.model;

import java.io.Serializable;

public class ExcelExportTmp  implements Serializable{

	private static final long serialVersionUID = -3743237143138275705L;

	private String templateCode;

    private Long fileInfoId;

    private String templateName;

    private String writeFileInfoName;

    private String comments;

    
    private String pageDateType;//新增：CREATE;修改：UPDATE
    
    public String getTemplateCode() {
        return templateCode;
    }

    public void setTemplateCode(String templateCode) {
        this.templateCode = templateCode;
    }

    public Long getFileInfoId() {
        return fileInfoId;
    }

    public void setFileInfoId(Long fileInfoId) {
        this.fileInfoId = fileInfoId;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public String getWriteFileInfoName() {
        return writeFileInfoName;
    }

    public void setWriteFileInfoName(String writeFileInfoName) {
        this.writeFileInfoName = writeFileInfoName;
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