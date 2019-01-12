package com.basic.oaas.model;
import com.basic.framework.common.model.AbstractModel;
import org.apache.commons.lang3.builder.ToStringBuilder;

 /**
 * 
 * <pre> 
 * 描述：帐号权限关系表 实体对象
 * 构建组：basic
 * 作者:lengzj
 * 邮箱:mail@com
 * 日期:2018-06-26 16:09:26
 * 版权：companyName
 * </pre>
 */
public class UserPrivate extends AbstractModel<Long>{
	
	
	/**
	* 账号权限标识
	*/
	protected Long userPrivateId; 
	
	
	/**
	* 账号标识
	*/
	protected Long userId; 
	
	
	/**
	* 权限ID
	*/
	protected Long privateId; 
	
	
	/**
	* 赋权标识
	*/
	protected Integer empowerFlag; 
	

	
	public void setUserPrivateId(Long userPrivateId) {
		this.userPrivateId = userPrivateId;
	}
	
	/**
	 * 返回 账号权限标识
	 * @return
	 */
	public Long getUserPrivateId() {
		return this.userPrivateId;
	}
	
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	/**
	 * 返回 账号标识
	 * @return
	 */
	public Long getUserId() {
		return this.userId;
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
		.append("userPrivateId", this.userPrivateId) 
		.append("userId", this.userId) 
		.append("privateId", this.privateId) 
		.append("empowerFlag", this.empowerFlag) 
		.append("createUserId", this.createUserId) 
		.append("createTime", this.createTime) 
		.toString();
	}
}