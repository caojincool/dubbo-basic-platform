package com.basic.system.model;
import com.basic.framework.common.model.AbstractModel;
import org.apache.commons.lang3.builder.ToStringBuilder;

 /**
 * 
 * <pre> 
 * 描述：外围系统接口 实体对象
 * 构建组：basic
 * 作者:lengzj
 * 邮箱:mail@com
 * 日期:2018-06-14 12:58:25
 * 版权：companyName
 * </pre>
 */
public class BaseSysInterface extends AbstractModel<Long>{
	
	
	/**
	* 接口ID
	*/
	protected Long sysInterfaceId; 
	
	
	/**
	* 系统编码
	*/
	protected String sysCode; 
	
	
	/**
	* 接口类型
	*/
	protected String interfaceType; 
	
	
	/**
	* 接口地址
	*/
	protected String uri; 
	
	
	/**
	* 启用状态，0-停用，1-启用
	*/
	protected Integer openStatus; 
	
	
	/**
	* 备注
	*/
	protected String remarks; 
	

	
	public void setSysInterfaceId(Long sysInterfaceId) {
		this.sysInterfaceId = sysInterfaceId;
	}
	
	/**
	 * 返回 接口ID
	 * @return
	 */
	public Long getSysInterfaceId() {
		return this.sysInterfaceId;
	}
	
	public void setSysCode(String sysCode) {
		this.sysCode = sysCode;
	}
	
	/**
	 * 返回 系统编码
	 * @return
	 */
	public String getSysCode() {
		return this.sysCode;
	}
	
	public void setInterfaceType(String interfaceType) {
		this.interfaceType = interfaceType;
	}
	
	/**
	 * 返回 接口类型
	 * @return
	 */
	public String getInterfaceType() {
		return this.interfaceType;
	}
	
	public void setUri(String uri) {
		this.uri = uri;
	}
	
	/**
	 * 返回 接口地址
	 * @return
	 */
	public String getUri() {
		return this.uri;
	}
	
	public void setOpenStatus(Integer openStatus) {
		this.openStatus = openStatus;
	}
	
	/**
	 * 返回 启用状态，0-停用，1-启用
	 * @return
	 */
	public Integer getOpenStatus() {
		return this.openStatus;
	}
	
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	/**
	 * 返回 备注
	 * @return
	 */
	public String getRemarks() {
		return this.remarks;
	}
	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
    public String toString() {
		return new ToStringBuilder(this)
		.append("sysInterfaceId", this.sysInterfaceId) 
		.append("sysCode", this.sysCode) 
		.append("interfaceType", this.interfaceType) 
		.append("uri", this.uri) 
		.append("openStatus", this.openStatus) 
		.append("createUserId", this.createUserId) 
		.append("createTime", this.createTime) 
		.append("modifyUserId", this.modifyUserId) 
		.append("modifyTime", this.modifyTime) 
		.append("state", this.state) 
		.append("remarks", this.remarks) 
		.append("backup1", this.backup1) 
		.append("backup2", this.backup2) 
		.append("backup3", this.backup3) 
		.append("backup4", this.backup4) 
		.append("backup5", this.backup5) 
		.append("accountSet", this.accountSet) 
		.toString();
	}
}