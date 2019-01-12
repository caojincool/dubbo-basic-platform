package com.basic.oaas.model;
import com.basic.framework.common.model.AbstractModel;
import org.apache.commons.lang3.builder.ToStringBuilder;

 /**
 * 
 * <pre> 
 * 描述：角色权限关系表 实体对象
 * 构建组：basic
 * 作者:lengzj
 * 邮箱:mail@com
 * 日期:2018-06-26 09:35:40
 * 版权：companyName
 * </pre>
 */
public class RolePrivate extends AbstractModel<Long>{
	
	
	/**
	* 角色权限标识
	*/
	protected Long rolePrivateId; 
	
	
	/**
	* 角色标识
	*/
	protected Long roleId; 
	
	
	/**
	* 权限ID
	*/
	protected Long privateId; 
	
	
	/**
	* 赋权标识
	*/
	protected Integer empowerFlag; 
	
    /**
     * 权限编码
     */
    private String privateCode;
    /**
     * 权限类型
     */
    private String privateType;
    /**
     * 权限名称
     */
    private String privateName;
    
    /**
     * 菜单ID
     */
    private String menuId;
    

	
	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	public String getPrivateCode() {
		return privateCode;
	}

	public void setPrivateCode(String privateCode) {
		this.privateCode = privateCode;
	}

	public String getPrivateType() {
		return privateType;
	}

	public void setPrivateType(String privateType) {
		this.privateType = privateType;
	}

	public String getPrivateName() {
		return privateName;
	}

	public void setPrivateName(String privateName) {
		this.privateName = privateName;
	}

	@Override
    public String getCreateUserName() {
		return createUserName;
	}

	@Override
    public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
	}

	public void setRolePrivateId(Long rolePrivateId) {
		this.rolePrivateId = rolePrivateId;
	}
	
	/**
	 * 返回 角色权限标识
	 * @return
	 */
	public Long getRolePrivateId() {
		return this.rolePrivateId;
	}
	
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
	
	/**
	 * 返回 角色标识
	 * @return
	 */
	public Long getRoleId() {
		return this.roleId;
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
	
	public void setEmpowerFlag(Integer empowerFlag) {
		this.empowerFlag = empowerFlag;
	}
	
	/**
	 * 返回 赋权标识
	 * @return
	 */
	public Integer getEmpowerFlag() {
		return this.empowerFlag;
	}
	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return new ToStringBuilder(this)
		.append("rolePrivateId", this.rolePrivateId) 
		.append("roleId", this.roleId) 
		.append("privateId", this.privateId) 
		.append("empowerFlag", this.empowerFlag) 
		.append("createUserId", this.createUserId) 
		.append("createTime", this.createTime) 
		.toString();
	}
}