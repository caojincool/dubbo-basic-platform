package com.basic.oaas.model;
import com.basic.framework.common.model.AbstractModel;
import org.apache.commons.lang3.builder.ToStringBuilder;

 /**
 * 
 * <pre> 
 * 描述：状态
 实体对象
 * 构建组：basic
 * 作者:lengzj
 * 邮箱:mail@com
 * 日期:2018-06-26 15:49:47
 * 版权：companyName
 * </pre>
 */
public class UserRole extends AbstractModel<Long>{
	
	
	/**
	* USER_ROLE_ID
	*/
	protected Long userRoleId; 
	
	
	/**
	* USER_ID
	*/
	protected Long userId; 
	
	
	/**
	* ROLE_ID
	*/
	protected Long roleId; 
	
	
	/**
	* EMPOWER_FLAG
	*/
	protected Integer empowerFlag; 
	
	private String roleCode;
    
    private String roleName;
	

	
	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public void setUserRoleId(Long userRoleId) {
		this.userRoleId = userRoleId;
	}
	
	/**
	 * 返回 USER_ROLE_ID
	 * @return
	 */
	public Long getUserRoleId() {
		return this.userRoleId;
	}
	
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	/**
	 * 返回 USER_ID
	 * @return
	 */
	public Long getUserId() {
		return this.userId;
	}
	
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
	
	/**
	 * 返回 ROLE_ID
	 * @return
	 */
	public Long getRoleId() {
		return this.roleId;
	}
	
	public void setEmpowerFlag(Integer empowerFlag) {
		this.empowerFlag = empowerFlag;
	}
	
	/**
	 * 返回 EMPOWER_FLAG
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
		.append("userRoleId", this.userRoleId) 
		.append("userId", this.userId) 
		.append("roleId", this.roleId) 
		.append("empowerFlag", this.empowerFlag) 
		.toString();
	}
}