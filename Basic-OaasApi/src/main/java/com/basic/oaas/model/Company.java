package com.basic.oaas.model;
import com.basic.framework.common.model.AbstractModel;
import com.basic.framework.common.model.Tree;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;

 /**
 * 
 * <pre> 
 * 描述：公司信息表 实体对象
 * 作者:lengzj
 * 邮箱:mail@com
 * 日期:2018-06-21 16:37:38
 * 版权：companyName
 * </pre>
 */
public class Company extends AbstractModel<Long> implements Serializable,Tree{
	
	
	/**
	* 公司ID
	*/
	protected Long companyId; 
	
	
	/**
	* 父节点ID
	*/
	protected Long parentId; 
	
	
	/**
	* 公司编码
	*/
	protected String companyCode; 
	
	
	/**
	* 公司简称
	*/
	protected String absName; 
	
	
	/**
	* 公司全称
	*/
	protected String fullName; 
	
	
	/**
	* 公司中文名
	*/
	protected String chineseName; 
	
	
	/**
	* 公司英文名
	*/
	protected String englishName; 
	
	
	/**
	* 公司logo文件路径
	*/
	protected String logo; 
	
	
	/**
	* 公司法人
	*/
	protected String legal; 
	
	
	/**
	* 主营业务
	*/
	protected String business; 
	
	
	/**
	* 注册地址
	*/
	protected String address; 
	
	/**
	 * 注册地址，省
	 */
	protected String province; 
	
	/**
	 * 注册地址，市
	 */
	protected String city; 
	
	/**
	 * 注册地址，区
	 */
	protected String district; 
	
	/**
	 * 注册地址，县
	 */
	protected String county; 
	
	
	/**
	* 社会信用代码
	*/
	protected String socialCreditCode; 
	
	
	/**
	* 公司邮箱
	*/
	protected String email; 
	
	
	/**
	* 公司电话
	*/
	protected String telephone; 
	
	
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
	
	
	/**
	* 注册资本
	*/
	protected Double registeredCappital; 
	
	
	/**
	* 营业执照号
	*/
	protected String businessLicense; 
	
	
	/**
	* 纳税登记号
	*/
	protected String taxRegistrationNumber; 
	
	
	/**
	* 联系人
	*/
	protected String contact; 
	
	
	/**
	* 联系人电话
	*/
	protected String contactMobile; 
	
	
	/**
	* 联系地址
	*/
	protected String contactAddress; 
	
	/**
	 * 联系地址，省
	 */
	protected String contactProvince; 
	
	/**
	 * 联系地址，市
	 */
	protected String contactCity; 
	
	/**
	 * 联系地址，区
	 */
	protected String contactDistrict; 
	
	/**
	 * 联系地址，县
	 */
	protected String contactCounty; 
	
	
	/**
	* 公司传真
	*/
	protected String fax; 
	
	
	/**
	* 公司网址
	*/
	protected String website; 
	
	/**
	 * 编码路径
	 */
	protected String codePath;
	/**
	 * ID路径
	 */
	protected String idPath;
	/**
	 * 名称路径
	 */
	protected String namePath;
	
	/**
	* 序
	*/
	protected Long displayIndex; 
	
	/**
	 * 上级公司编码，非数据库字段
	 */
	protected String parentCompanyCode; 
	
	/**
	 * 上级公司简称，非数据库字段
	 */
	protected String parentCompanyAbsName; 
	
	private List<Company> children = new ArrayList<Company>();

	
	/**
     * 是否父节点
     */
    private boolean isParent;
    
    public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getCounty() {
		return county;
	}

	public void setCounty(String county) {
		this.county = county;
	}

	public String getContactProvince() {
		return contactProvince;
	}

	public void setContactProvince(String contactProvince) {
		this.contactProvince = contactProvince;
	}

	public String getContactCity() {
		return contactCity;
	}

	public void setContactCity(String contactCity) {
		this.contactCity = contactCity;
	}

	public String getContactDistrict() {
		return contactDistrict;
	}

	public void setContactDistrict(String contactDistrict) {
		this.contactDistrict = contactDistrict;
	}

	public String getContactCounty() {
		return contactCounty;
	}

	public void setContactCounty(String contactCounty) {
		this.contactCounty = contactCounty;
	}

	public boolean isIsParent() {
		return isParent;
	}
    
    public void setParent(boolean isParent) {
		this.isParent = isParent;
	}
    
	public String getParentCompanyCode() {
		return parentCompanyCode;
	}

	public void setParentCompanyCode(String parentCompanyCode) {
		this.parentCompanyCode = parentCompanyCode;
	}

	public String getParentCompanyAbsName() {
		return parentCompanyAbsName;
	}

	public void setParentCompanyAbsName(String parentCompanyAbsName) {
		this.parentCompanyAbsName = parentCompanyAbsName;
	}

	public Long getDisplayIndex() {
		return displayIndex;
	}

	public void setDisplayIndex(Long displayIndex) {
		this.displayIndex = displayIndex;
	}

	public String getIdPath() {
		return idPath;
	}

	public void setIdPath(String idPath) {
		this.idPath = idPath;
	}

	public String getNamePath() {
		return namePath;
	}

	public void setNamePath(String namePath) {
		this.namePath = namePath;
	}

	public String getCodePath() {
		return codePath;
	}

	public void setCodePath(String codePath) {
		this.codePath = codePath;
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

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}
	
	/**
	 * 返回 公司ID
	 * @return
	 */
	public Long getCompanyId() {
		return this.companyId;
	}
	
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	
	/**
	 * 返回 父节点ID
	 * @return
	 */
	@Override
	public Long getParentId() {
		return this.parentId;
	}
	
	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}
	
	/**
	 * 返回 公司编码
	 * @return
	 */
	public String getCompanyCode() {
		return this.companyCode;
	}
	
	public void setAbsName(String absName) {
		this.absName = absName;
	}
	
	/**
	 * 返回 公司简称
	 * @return
	 */
	public String getAbsName() {
		return this.absName;
	}
	
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	
	/**
	 * 返回 公司全称
	 * @return
	 */
	public String getFullName() {
		return this.fullName;
	}
	
	public void setChineseName(String chineseName) {
		this.chineseName = chineseName;
	}
	
	/**
	 * 返回 公司中文名
	 * @return
	 */
	public String getChineseName() {
		return this.chineseName;
	}
	
	public void setEnglishName(String englishName) {
		this.englishName = englishName;
	}
	
	/**
	 * 返回 公司英文名
	 * @return
	 */
	public String getEnglishName() {
		return this.englishName;
	}
	
	public void setLogo(String logo) {
		this.logo = logo;
	}
	
	/**
	 * 返回 公司logo文件路径
	 * @return
	 */
	public String getLogo() {
		return this.logo;
	}
	
	public void setLegal(String legal) {
		this.legal = legal;
	}
	
	/**
	 * 返回 公司法人
	 * @return
	 */
	public String getLegal() {
		return this.legal;
	}
	
	public void setBusiness(String business) {
		this.business = business;
	}
	
	/**
	 * 返回 主营业务
	 * @return
	 */
	public String getBusiness() {
		return this.business;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	/**
	 * 返回 注册地址
	 * @return
	 */
	public String getAddress() {
		return this.address;
	}
	
	public void setSocialCreditCode(String socialCreditCode) {
		this.socialCreditCode = socialCreditCode;
	}
	
	/**
	 * 返回 社会信用代码
	 * @return
	 */
	public String getSocialCreditCode() {
		return this.socialCreditCode;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	/**
	 * 返回 公司邮箱
	 * @return
	 */
	public String getEmail() {
		return this.email;
	}
	
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	
	/**
	 * 返回 公司电话
	 * @return
	 */
	public String getTelephone() {
		return this.telephone;
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
	
	public void setRegisteredCappital(Double registeredCappital) {
		this.registeredCappital = registeredCappital;
	}
	
	/**
	 * 返回 注册资本
	 * @return
	 */
	public Double getRegisteredCappital() {
		return this.registeredCappital;
	}
	
	public void setBusinessLicense(String businessLicense) {
		this.businessLicense = businessLicense;
	}
	
	/**
	 * 返回 营业执照号
	 * @return
	 */
	public String getBusinessLicense() {
		return this.businessLicense;
	}
	
	public void setTaxRegistrationNumber(String taxRegistrationNumber) {
		this.taxRegistrationNumber = taxRegistrationNumber;
	}
	
	/**
	 * 返回 纳税登记号
	 * @return
	 */
	public String getTaxRegistrationNumber() {
		return this.taxRegistrationNumber;
	}
	
	public void setContact(String contact) {
		this.contact = contact;
	}
	
	/**
	 * 返回 联系人
	 * @return
	 */
	public String getContact() {
		return this.contact;
	}
	
	public void setContactMobile(String contactMobile) {
		this.contactMobile = contactMobile;
	}
	
	/**
	 * 返回 联系人电话
	 * @return
	 */
	public String getContactMobile() {
		return this.contactMobile;
	}
	
	public void setContactAddress(String contactAddress) {
		this.contactAddress = contactAddress;
	}
	
	/**
	 * 返回 联系地址
	 * @return
	 */
	public String getContactAddress() {
		return this.contactAddress;
	}
	
	public void setFax(String fax) {
		this.fax = fax;
	}
	
	/**
	 * 返回 公司传真
	 * @return
	 */
	public String getFax() {
		return this.fax;
	}
	
	public void setWebsite(String website) {
		this.website = website;
	}
	
	/**
	 * 返回 公司网址
	 * @return
	 */
	public String getWebsite() {
		return this.website;
	}
	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return new ToStringBuilder(this)
		.append("companyId", this.companyId) 
		.append("parentId", this.parentId) 
		.append("companyCode", this.companyCode) 
		.append("absName", this.absName) 
		.append("fullName", this.fullName) 
		.append("chineseName", this.chineseName) 
		.append("englishName", this.englishName) 
		.append("logo", this.logo) 
		.append("legal", this.legal) 
		.append("business", this.business) 
		.append("address", this.address) 
		.append("socialCreditCode", this.socialCreditCode) 
		.append("email", this.email) 
		.append("telephone", this.telephone) 
		.append("state", this.state) 
		.append("createUserId", this.createUserId) 
		.append("createTime", this.createTime) 
		.append("modifyUserId", this.modifyUserId) 
		.append("modifyTime", this.modifyTime) 
		.append("remarks", this.remarks) 
		.append("openStatus", this.openStatus) 
		.append("openDate", this.openDate) 
		.append("stopDate", this.stopDate) 
		.append("registeredCappital", this.registeredCappital) 
		.append("businessLicense", this.businessLicense) 
		.append("taxRegistrationNumber", this.taxRegistrationNumber) 
		.append("contact", this.contact) 
		.append("contactMobile", this.contactMobile) 
		.append("contactAddress", this.contactAddress) 
		.append("fax", this.fax) 
		.append("website", this.website) 
		.toString();
	}

	@Override
	public Long getId() {
		return this.companyId;
	}

	@Override
	public String getText() {
		return this.fullName;
	}

	@Override
	public List getChildren() {
		return this.children;
	}

	@Override
	public void setChildren(List<Tree> list) {
		this.children = this.children;
	}
}