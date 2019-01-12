package com.basic.oaas.model;
import com.basic.framework.common.model.AbstractModel;
import org.apache.commons.lang3.builder.ToStringBuilder;

 /**
 * 
 * <pre> 
 * 描述：功能按钮 实体对象
 * 构建组：basic
 * 作者:lengzj
 * 邮箱:mail@com
 * 日期:2018-06-25 09:08:26
 * 版权：companyName
 * </pre>
 */
public class PrivateFunc extends AbstractModel<Long>{
	
	
	/**
	* 按钮标识
	*/
	protected Long funcId; 
	
	
	/**
	* 按钮编码
	*/
	protected String funcCode; 
	
	
	/**
	* 按钮名称
	*/
	protected String funcName; 
	
	
	/**
	* 按钮URL
	*/
	protected String funcUrl; 
	
	
	/**
	* 按钮图标
	*/
	protected String funcIcon; 
	
	
	/**
	* 按钮顺序
	*/
	protected Long displayIndex; 
	
	
	/**
	* 权限ID
	*/
	protected Long privateId; 
	
	
	/**
	* 菜单ID
	*/
	protected Long menuId; 
	
	
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
	protected java.util.Date stopDate; 
	

	
	public void setFuncId(Long funcId) {
		this.funcId = funcId;
	}
	
	/**
	 * 返回 按钮标识
	 * @return
	 */
	public Long getFuncId() {
		return this.funcId;
	}
	
	public void setFuncCode(String funcCode) {
		this.funcCode = funcCode;
	}
	
	/**
	 * 返回 按钮编码
	 * @return
	 */
	public String getFuncCode() {
		return this.funcCode;
	}
	
	public void setFuncName(String funcName) {
		this.funcName = funcName;
	}
	
	/**
	 * 返回 按钮名称
	 * @return
	 */
	public String getFuncName() {
		return this.funcName;
	}
	
	public void setFuncUrl(String funcUrl) {
		this.funcUrl = funcUrl;
	}
	
	/**
	 * 返回 按钮URL
	 * @return
	 */
	public String getFuncUrl() {
		return this.funcUrl;
	}
	
	public void setFuncIcon(String funcIcon) {
		this.funcIcon = funcIcon;
	}
	
	/**
	 * 返回 按钮图标
	 * @return
	 */
	public String getFuncIcon() {
		return this.funcIcon;
	}
	
	public void setDisplayIndex(Long displayIndex) {
		this.displayIndex = displayIndex;
	}
	
	/**
	 * 返回 按钮顺序
	 * @return
	 */
	public Long getDisplayIndex() {
		return this.displayIndex;
	}
	
	public void setPrivateId(Long privateId) {
		this.privateId = privateId;
	}
	
	/**
	 * 返回 权限ID
	 * @return
	 */
	public Long getPrivateId() {
		return this.privateId;
	}
	
	public void setMenuId(Long menuId) {
		this.menuId = menuId;
	}
	
	/**
	 * 返回 菜单ID
	 * @return
	 */
	public Long getMenuId() {
		return this.menuId;
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
	
	public void setStopDate(java.util.Date stopDate) {
		this.stopDate = stopDate;
	}
	
	/**
	 * 返回 停用日期
	 * @return
	 */
	public java.util.Date getStopDate() {
		return this.stopDate;
	}
	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return new ToStringBuilder(this)
		.append("funcId", this.funcId) 
		.append("funcCode", this.funcCode) 
		.append("funcName", this.funcName) 
		.append("funcUrl", this.funcUrl) 
		.append("funcIcon", this.funcIcon) 
		.append("displayIndex", this.displayIndex) 
		.append("privateId", this.privateId) 
		.append("menuId", this.menuId) 
		.append("state", this.state) 
		.append("createUserId", this.createUserId) 
		.append("createTime", this.createTime) 
		.append("modifyUserId", this.modifyUserId) 
		.append("modifyTime", this.modifyTime) 
		.append("remarks", this.remarks) 
		.append("openStatus", this.openStatus) 
		.append("openDate", this.openDate) 
		.append("stopDate", this.stopDate) 
		.toString();
	}
}