package com.basic.oaas.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 
 *
 * @date 2017年8月18日 下午2:52:44
 * @author Kevin
 * @Description: 员工表
 *
 */
public class Staff implements Serializable {
	
	private static final long serialVersionUID = 6101842144851915643L;

	private Long staffId;

    private Long staffIcon;

    private String staffNumber;

    private String staffName;

    private Integer sex;

    private String wechat;

    private String qq;

    private String telephone;

    private String email;

    private String state;

    private Long createUserId;

    private Date createTime;

    private Long modifyUserId;

    private Date modifyTime;

    private String remarks;
    
    private String createUserName; // 创建人姓名
	
	private String modifyUserName;	//修改人姓名
	
	private String jobName; //默认职位名
	
	private Long jobId;		//默认职位Id
	
	/**
	 * EIP帐号
	 */
	private String eipAccount;
	
	/**
	 * 年龄
	 */
	private Integer age;
	
	/**
	 * 职务
	 */
	private String post;
	
	/**
	 * 固定电话
	 */
	private String fixedTelephone;
	
	/**
	 * 居住地址
	 */
	private String residentialAddress;
	
	/**
	 * 常住地址
	 */
	private String permanentAddress;
	
	/**
	 * 来源,1-EIP同步，2-系统维护
	 */
	private Integer source;
	
	/**
     * 同步时间
     */
    private Date syncDate;
    
    /**
     * 启用状态，0-不启用，1-启用
     */
    private Integer openStatus;
    
    /**
     * 启用日期
     */
    private Date openDate;
    
    /**
     * 停用日期
     */
    private Date stopDate;
    
    /**
	* 常住地址（县）
	*/
	protected String permanentCounty; 
	
	
	/**
	* 常住地址（区）
	*/
	protected String permanentDistrict; 
	
	
	/**
	* 常住地址（市）
	*/
	protected String permanentCity; 
	
	
	/**
	* 常住地址（省）
	*/
	protected String permanentProvince; 
	
	
	/**
	* 居住地址（县）
	*/
	protected String residentialCounty; 
	
	
	/**
	* 居住地址（区）
	*/
	protected String residentialDistrict; 
	
	
	/**
	* 居住地址（省）
	*/
	protected String residentialProvince; 
	
	
	/**
	* 居住地址（市）
	*/
	protected String residentialCity; 
    
    /**
     * 关联的帐号,非数据库字段
     */
    private List<UserStaff> userStaffs;
    
    /**
     * 关联的角色,非数据库字段
     */
    private List<Role> roles;
    
    /**
     * 关联的岗位,非数据库字段
     */
    private List<StaffJob> staffJobs;
    
    /**
     * 帐号名称,非数据库字段
     */
    private String userText;
    
    /**
     * 密码,非数据库字段
     */
    private String userPwd;
    
    /**
     * 帐号,非数据库字段
     */
    private String userName;
    
	/**
	 * 用户id
	 * @return
	 */
    private Long userId;
    
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getPermanentCounty() {
		return permanentCounty;
	}

	public void setPermanentCounty(String permanentCounty) {
		this.permanentCounty = permanentCounty;
	}

	public String getPermanentDistrict() {
		return permanentDistrict;
	}

	public void setPermanentDistrict(String permanentDistrict) {
		this.permanentDistrict = permanentDistrict;
	}

	public String getPermanentCity() {
		return permanentCity;
	}

	public void setPermanentCity(String permanentCity) {
		this.permanentCity = permanentCity;
	}

	public String getPermanentProvince() {
		return permanentProvince;
	}

	public void setPermanentProvince(String permanentProvince) {
		this.permanentProvince = permanentProvince;
	}

	public String getResidentialCounty() {
		return residentialCounty;
	}

	public void setResidentialCounty(String residentialCounty) {
		this.residentialCounty = residentialCounty;
	}

	public String getResidentialDistrict() {
		return residentialDistrict;
	}

	public void setResidentialDistrict(String residentialDistrict) {
		this.residentialDistrict = residentialDistrict;
	}

	public String getResidentialProvince() {
		return residentialProvince;
	}

	public void setResidentialProvince(String residentialProvince) {
		this.residentialProvince = residentialProvince;
	}

	public String getResidentialCity() {
		return residentialCity;
	}

	public void setResidentialCity(String residentialCity) {
		this.residentialCity = residentialCity;
	}

	public String getUserText() {
		return userText;
	}

	public void setUserText(String userText) {
		this.userText = userText;
	}

	public String getUserPwd() {
		return userPwd;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getOpenStatus() {
		return openStatus;
	}

	public void setOpenStatus(Integer openStatus) {
		this.openStatus = openStatus;
	}

	public Date getOpenDate() {
		return openDate;
	}

	public void setOpenDate(Date openDate) {
		this.openDate = openDate;
	}

	public Date getStopDate() {
		return stopDate;
	}

	public void setStopDate(Date stopDate) {
		this.stopDate = stopDate;
	}

	public List<StaffJob> getStaffJobs() {
		return staffJobs;
	}

	public void setStaffJobs(List<StaffJob> staffJobs) {
		this.staffJobs = staffJobs;
	}

	public List<UserStaff> getUserStaffs() {
		return userStaffs;
	}

	public void setUserStaffs(List<UserStaff> userStaffs) {
		this.userStaffs = userStaffs;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}


	public Date getSyncDate() {
		return syncDate;
	}

	public void setSyncDate(Date syncDate) {
		this.syncDate = syncDate;
	}

	public String getFixedTelephone() {
		return fixedTelephone;
	}

	public void setFixedTelephone(String fixedTelephone) {
		this.fixedTelephone = fixedTelephone;
	}

	public String getResidentialAddress() {
		return residentialAddress;
	}

	public void setResidentialAddress(String residentialAddress) {
		this.residentialAddress = residentialAddress;
	}

	public String getPermanentAddress() {
		return permanentAddress;
	}

	public void setPermanentAddress(String permanentAddress) {
		this.permanentAddress = permanentAddress;
	}

	public Integer getSource() {
		return source;
	}

	public void setSource(Integer source) {
		this.source = source;
	}

	public String getEipAccount() {
		return eipAccount;
	}

	public void setEipAccount(String eipAccount) {
		this.eipAccount = eipAccount;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getPost() {
		return post;
	}

	public void setPost(String post) {
		this.post = post;
	}

	public Long getStaffId() {
        return staffId;
    }

    public void setStaffId(Long staffId) {
        this.staffId = staffId;
    }

    public Long getStaffIcon() {
        return staffIcon;
    }
    public void setStaffIcon(Long staffIcon) {
        this.staffIcon = staffIcon;
    }

    public String getStaffNumber() {
        return staffNumber;
    }

    public void setStaffNumber(String staffNumber) {
        this.staffNumber = staffNumber;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getWechat() {
        return wechat;
    }

    public void setWechat(String wechat) {
        this.wechat = wechat;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Long getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getModifyUserId() {
        return modifyUserId;
    }

    public void setModifyUserId(Long modifyUserId) {
        this.modifyUserId = modifyUserId;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

	/**
	 * @return the createUserName
	 */
	public String getCreateUserName() {
		return createUserName;
	}

	/**
	 * @param createUserName the createUserName to set
	 */
	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
	}

	/**
	 * @return the modifyUserName
	 */
	public String getModifyUserName() {
		return modifyUserName;
	}

	/**
	 * @param modifyUserName the modifyUserName to set
	 */
	public void setModifyUserName(String modifyUserName) {
		this.modifyUserName = modifyUserName;
	}

	/**
	 * @return the jobName
	 */
	public String getJobName() {
		return jobName;
	}

	/**
	 * @param jobName the jobName to set
	 */
	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	/**
	 * @return the jobId
	 */
	public Long getJobId() {
		return jobId;
	}

	/**
	 * @param jobId the jobId to set
	 */
	public void setJobId(Long jobId) {
		this.jobId = jobId;
	}

    
}