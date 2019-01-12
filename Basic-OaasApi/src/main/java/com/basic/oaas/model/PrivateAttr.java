package com.basic.oaas.model;
import com.basic.framework.common.model.AbstractModel;
import org.apache.commons.lang3.builder.ToStringBuilder;

 /**
 * 
 * <pre> 
 * 描述：字段权限配置 实体对象
 * 构建组：basic
 * 作者:lengzj
 * 邮箱:mail@com
 * 日期:2018-06-25 09:08:26
 * 版权：companyName
 * </pre>
 */
public class PrivateAttr extends AbstractModel<Long>{
	
	
	/**
	* 唯一标识
	*/
	protected Long attrId; 
	
	
	/**
	* 权限ID
	*/
	protected Long privateId; 
	
	
	/**
	* 菜单ID
	*/
	protected Long menuId; 
	
	
	/**
	* 字段编码
	*/
	protected String attrCode; 
	
	
	/**
	* 字段名称
	*/
	protected String attrName; 
	
	
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
	

	
	public void setAttrId(Long attrId) {
		this.attrId = attrId;
	}
	
	/**
	 * 返回 唯一标识
	 * @return
	 */
	public Long getAttrId() {
		return this.attrId;
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
	
	public void setAttrCode(String attrCode) {
		this.attrCode = attrCode;
	}
	
	/**
	 * 返回 字段编码
	 * @return
	 */
	public String getAttrCode() {
		return this.attrCode;
	}
	
	public void setAttrName(String attrName) {
		this.attrName = attrName;
	}
	
	/**
	 * 返回 字段名称
	 * @return
	 */
	public String getAttrName() {
		return this.attrName;
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
		.append("attrId", this.attrId) 
		.append("privateId", this.privateId) 
		.append("menuId", this.menuId) 
		.append("attrCode", this.attrCode) 
		.append("attrName", this.attrName) 
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