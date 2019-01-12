package com.basic.system.bean;

import java.io.Serializable;

import com.basic.framework.common.model.PageBean;

/**
 * 
 *
 * @date 2017年7月3日 下午6:04:56
 * 
 * @Description: Excel上传模板查询参数
 *
 */
public class ExcelImportTmpIBean extends PageBean implements Serializable {
	
	private static final long serialVersionUID = 2486757450339126466L;
	
	private String templateCode;//模板编码
	private String templateName;//模板名称
	
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
}
