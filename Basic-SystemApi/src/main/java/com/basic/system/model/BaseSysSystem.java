package com.basic.system.model;
import com.basic.framework.common.model.AbstractModel;
import org.apache.commons.lang3.builder.ToStringBuilder;

 /**
 * 
 * <pre> 
 * 描述：外围系统管理 实体对象
 * 构建组：basic
 * 作者:lengzj
 * 邮箱:mail@com
 * 日期:2018-06-14 12:58:25
 * 版权：companyName
 * </pre>
 */
public class BaseSysSystem extends AbstractModel<Long>{
	
	
	/**
	* 系统标识
	*/
	protected Long sysId; 
	
	
	/**
	* 系统编码
	*/
	protected String sysCode; 
	
	
	/**
	* 系统名称
	*/
	protected String sysName; 
	
	
	/**
	* 内网地址
	*/
	protected String innerHost; 
	
	
	/**
	* 外网地址
	*/
	protected String extranetHost; 
	
	
	/**
	* 系统logo
	*/
	protected String logo; 
	
	
	/**
	* 是否需要切换，0-否，1-是
	*/
	protected Integer isswitch; 
	
	
	/**
	* 备注
	*/
	protected String remarks; 
	
	
	/**
	* 启用状态
	*/
	protected Integer openStatus; 
	
	
	/**
	* 启用日期
	*/
	protected java.util.Date openDate; 
	
	
	/**
	* 停用日期
	*/
	protected java.util.Date endDate; 
	
	/**
	 * 系统编码
	 */
	protected String orgCodes; 
	

	
	public String getOrgCodes() {
		return orgCodes;
	}

	public void setOrgCodes(String orgCodes) {
		this.orgCodes = orgCodes;
	}

	public void setSysId(Long sysId) {
		this.sysId = sysId;
	}
	
	/**
	 * 返回 系统标识
	 * @return
	 */
	public Long getSysId() {
		return this.sysId;
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
	
	public void setSysName(String sysName) {
		this.sysName = sysName;
	}
	
	/**
	 * 返回 系统名称
	 * @return
	 */
	public String getSysName() {
		return this.sysName;
	}
	
	public void setInnerHost(String innerHost) {
		this.innerHost = innerHost;
	}
	
	/**
	 * 返回 内网地址
	 * @return
	 */
	public String getInnerHost() {
		return this.innerHost;
	}
	
	public void setExtranetHost(String extranetHost) {
		this.extranetHost = extranetHost;
	}
	
	/**
	 * 返回 外网地址
	 * @return
	 */
	public String getExtranetHost() {
		return this.extranetHost;
	}
	
	public void setLogo(String logo) {
		this.logo = logo;
	}
	
	/**
	 * 返回 系统logo
	 * @return
	 */
	public String getLogo() {
		return this.logo;
	}
	
	public void setIsswitch(Integer isswitch) {
		this.isswitch = isswitch;
	}
	
	/**
	 * 返回 是否需要切换，0-否，1-是
	 * @return
	 */
	public Integer getIsswitch() {
		return this.isswitch;
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
	
	public void setOpenStatus(Integer openStatus) {
		this.openStatus = openStatus;
	}
	
	/**
	 * 返回 启用状态
	 * @return
	 */
	public Integer getOpenStatus() {
		return this.openStatus;
	}
	
	public void setOpenDate(java.util.Date openDate) {
		this.openDate = openDate;
	}
	
	/**
	 * 返回 启用日期
	 * @return
	 */
	public java.util.Date getOpenDate() {
		return this.openDate;
	}
	
	public void setEndDate(java.util.Date endDate) {
		this.endDate = endDate;
	}
	
	/**
	 * 返回 停用日期
	 * @return
	 */
	public java.util.Date getEndDate() {
		return this.endDate;
	}
	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
    public String toString() {
		return new ToStringBuilder(this)
		.append("sysId", this.sysId) 
		.append("sysCode", this.sysCode) 
		.append("sysName", this.sysName) 
		.append("innerHost", this.innerHost) 
		.append("extranetHost", this.extranetHost) 
		.append("logo", this.logo) 
		.append("isswitch", this.isswitch) 
		.append("state", this.state) 
		.append("createUserId", this.createUserId) 
		.append("createTime", this.createTime) 
		.append("modifyUserId", this.modifyUserId) 
		.append("modifyTime", this.modifyTime) 
		.append("remarks", this.remarks) 
		.append("openStatus", this.openStatus) 
		.append("openDate", this.openDate) 
		.append("endDate", this.endDate) 
		.append("backup1", this.backup1) 
		.append("backup2", this.backup2) 
		.append("backup3", this.backup3) 
		.append("backup4", this.backup4) 
		.append("backup5", this.backup5) 
		.append("accountSet", this.accountSet) 
		.toString();
	}
}