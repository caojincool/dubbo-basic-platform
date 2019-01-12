/**
 *
 * companyName
 * copyright 2015-2020
 * @date 2016年1月19日 下午9:09:13
 * @author liao.chunqiao
 * @Description: TODO(这里用一句话描述这个类的作用)
 * 
 */
package com.basic.framework.common.model;

public class FTPConfig {
	
	private String ip;// IP
	private String username;// 用户
	private String password;// 用户密码
	private String workdir;// 文件上传下载的目录
	private String type;//类型
	
	public FTPConfig(){
		
	}
	
	public FTPConfig(String ip, String username, String password, String workdir,String type) {
		super();
		this.ip = ip;
		this.username = username;
		this.password = password;
		this.workdir = workdir;
		this.type = type;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getWorkdir() {
		return workdir;
	}

	public void setWorkdir(String workdir) {
		this.workdir = workdir;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
}
