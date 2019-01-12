package com.basic.system.bean;

import java.io.Serializable;

import com.basic.framework.common.model.PageBean;

/**
 * 
 *
 * @date 2017年10月10日 上午10:50:34
 * 
 * @Description: gid服务查询参数
 *
 */
public class GidServerIBean extends PageBean implements Serializable {
	
	private static final long serialVersionUID = 7179487648247520682L;
	
	private String gidCode;//编码
    private String gidName;//名称
    
	public String getGidCode() {
		return gidCode;
	}
	public void setGidCode(String gidCode) {
		this.gidCode = gidCode;
	}
	public String getGidName() {
		return gidName;
	}
	public void setGidName(String gidName) {
		this.gidName = gidName;
	}
	
}
