package com.basic.oaas.model;
import com.basic.framework.common.model.AbstractModel;
import org.apache.commons.lang3.builder.ToStringBuilder;

 /**
 * 
 * <pre> 
 * 描述：权限类型 PRIVATE_TYPE 实体对象
 * 构建组：basic
 * 作者:lengzj
 * 邮箱:mail@com
 * 日期:2018-06-25 09:54:37
 * 版权：companyName
 * </pre>
 */
public class Private extends AbstractModel<Long>{
	
	
	/**
	* 权限ID
	*/
	protected Long privateId; 
	
	
	
	/**
	* 权限类型
	*/
	protected String privateType; 
	
	
	/**
	* 权限名称
	*/
	protected String privateName; 
	
	
	/**
	* 权限编码
	*/
	protected String privateCode; 
	
	
	/**
	* 备注
	*/
	protected String remarks; 
	
	/**
	 * 权限来源，非数据库字段
	 */
	protected String privateSource;
	
	/**
	 * 链接，非数据库字段
	 */
	protected String uri;
	
	/**
	 * 图标，非数据库字段
	 */
	protected String icon;
	
	/**
	 * 开启状态，非数据库字段
	 */
	protected Integer openStatus;
	
	/**
	 * 菜单ID
	 */
	protected Long menuId;
	
	
	public Long getMenuId() {
		return menuId;
	}

	public void setMenuId(Long menuId) {
		this.menuId = menuId;
	}

	@Override
    public String getCreateUserName() {
		return createUserName;
	}

	@Override
    public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
	}

	@Override
    public String getModifyUserName() {
		return modifyUserName;
	}

	@Override
    public void setModifyUserName(String modifyUserName) {
		this.modifyUserName = modifyUserName;
	}

	public Integer getOpenStatus() {
		return openStatus;
	}

	public void setOpenStatus(Integer openStatus) {
		this.openStatus = openStatus;
	}

	public String getPrivateSource() {
		return privateSource;
	}

	public void setPrivateSource(String privateSource) {
		this.privateSource = privateSource;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
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
	
	
	public void setPrivateType(String privateType) {
		this.privateType = privateType;
	}
	
	/**
	 * 返回 权限类型
	 * @return
	 */
	public String getPrivateType() {
		return this.privateType;
	}
	
	public void setPrivateName(String privateName) {
		this.privateName = privateName;
	}
	
	/**
	 * 返回 权限名称
	 * @return
	 */
	public String getPrivateName() {
		return this.privateName;
	}
	
	public void setPrivateCode(String privateCode) {
		this.privateCode = privateCode;
	}
	
	/**
	 * 返回 权限编码
	 * @return
	 */
	public String getPrivateCode() {
		return this.privateCode;
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
		.append("privateId", this.privateId) 
		.append("privateType", this.privateType) 
		.append("privateName", this.privateName) 
		.append("privateCode", this.privateCode) 
		.append("state", this.state) 
		.append("createUserId", this.createUserId) 
		.append("createTime", this.createTime) 
		.append("modifyUserId", this.modifyUserId) 
		.append("modifyTime", this.modifyTime) 
		.append("remarks", this.remarks) 
		.toString();
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Private) {
			Private temp = (Private)obj;
			return temp.getPrivateId().equals(this.privateId);
		}
		return false;
	}
	
	
}