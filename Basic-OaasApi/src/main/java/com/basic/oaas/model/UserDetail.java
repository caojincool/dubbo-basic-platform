package com.basic.oaas.model;

import java.io.Serializable;
import java.util.List;

import com.basic.oaas.model.Company;
import com.basic.oaas.model.PrivateMenu;
import com.basic.oaas.model.User;

/**
 * 
 *
 * @date 2017年6月28日 下午3:57:59
 * 
 * @Description: 用户详细信息
 *
 */
public class UserDetail extends User implements Serializable   {

	private static final long serialVersionUID = 4710835233306796721L;
	
	/*private Long userId;//账号标识
	private String userName;//登录账号
	private String userText;//账号名称
*/	
	//1个员工可有多个账号，1个账号只有一个员工
	private Long staffId;// 员工标识
	private String staffNumber;// 员工工号
	private String staffName;// 员工名字
	private Long staffIcon;//员工头像
    private Integer sex;//性别
    private String wechat;//qq
    private String qq;//qq
    private String telephone;//手机
    private String email;//邮箱
	
    //以下为默认的岗位-部门-区域
    //每个员工有且只有一个默认岗位
	private Long jobId;//岗位ID
	private String jobCode;//岗位编码
	private String jobName;//岗位名称
    
	private Long orgId;//部门标识
    private Long parentOrgId; //上级部门标识
	private String orgCode;//部门编码
	private String orgName;//部门名称
	private String orgCodePath;//部门编码路径
	private String orgNamePath;//部门名称路径
	
	private Long areaId;//区域标识
	private Long parentAreaId; //上级区域标识
	private String areaCode;//区域编码
	private String areaName;//区域名称
	private String areaCodePath;//区域编码路径
	private String areaNamePath;//区域名称路径
	
	private List<JobOrgArea> jobOrgAreas;//岗位部门区域信息
	
	//private List<PrivateMenu> privateMenus;//菜单权限

	//private List<Role> roles;//角色
	
	//private List<Private> privates;//权限
	
	//private List<Company> companys;//用户公司
	
	private Company company;

	
	/*public List<PrivateMenu> getPrivateMenus() {
		return privateMenus;
	}

	public void setPrivateMenus(List<PrivateMenu> privateMenus) {
		this.privateMenus = privateMenus;
	}*/

	public Long getStaffId() {
		return staffId;
	}

	public void setStaffId(Long staffId) {
		this.staffId = staffId;
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

	public Long getStaffIcon() {
		return staffIcon;
	}

	public void setStaffIcon(Long staffIcon) {
		this.staffIcon = staffIcon;
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

	public Long getJobId() {
		return jobId;
	}

	public void setJobId(Long jobId) {
		this.jobId = jobId;
	}

	public String getJobCode() {
		return jobCode;
	}

	public void setJobCode(String jobCode) {
		this.jobCode = jobCode;
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getOrgCodePath() {
		return orgCodePath;
	}

	public void setOrgCodePath(String orgCodePath) {
		this.orgCodePath = orgCodePath;
	}

	public String getOrgNamePath() {
		return orgNamePath;
	}

	public void setOrgNamePath(String orgNamePath) {
		this.orgNamePath = orgNamePath;
	}

	public Long getAreaId() {
		return areaId;
	}

	public void setAreaId(Long areaId) {
		this.areaId = areaId;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public String getAreaCodePath() {
		return areaCodePath;
	}

	public void setAreaCodePath(String areaCodePath) {
		this.areaCodePath = areaCodePath;
	}

	public String getAreaNamePath() {
		return areaNamePath;
	}

	public void setAreaNamePath(String areaNamePath) {
		this.areaNamePath = areaNamePath;
	}

	public List<JobOrgArea> getJobOrgAreas() {
		return jobOrgAreas;
	}

	public void setJobOrgAreas(List<JobOrgArea> list) {
		this.jobOrgAreas = list;
	}

	/*public List<Private> getPrivates() {
		return privates;
	}

	public void setPrivates(List<Private> privates) {
		this.privates = privates;
	}*/
	
	public Long getParentOrgId() {
		return parentOrgId;
	}

	public void setParentOrgId(Long parentOrgId) {
		this.parentOrgId = parentOrgId;
	}

	public Long getParentAreaId() {
		return parentAreaId;
	}

	public void setParentAreaId(Long parentAreaId) {
		this.parentAreaId = parentAreaId;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}






	

}
