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
 * 描述：ID路径 NAME路径 CODE路径 实体对象
 * 构建组：basic
 * 作者:lengzj
 * 邮箱:mail@com
 * 日期:2018-06-21 17:19:12
 * 版权：companyName
 * </pre>
 */
public class Area extends AbstractModel<Long> implements Serializable,Tree{
	
	
	/**
	* 区域标识
	*/
	protected Long areaId; 
	
	
	/**
	* 父区域ID
	*/
	protected Long parentAreaId; 
	
	
	/**
	* 区域编码
	*/
	protected String areaCode; 
	
	
	/**
	* 区域简称
	*/
	protected String areaAbsName; 
	
	
	/**
	* 区域名称
	*/
	protected String areaName; 
	
	
	/**
	* 区域地址
	*/
	protected String areaAddress; 
	
	
	/**
	* 序
	*/
	protected Long displayIndex; 
	
	
	/**
	* ID路径
	*/
	protected String idPath; 
	
	
	/**
	* NAME路径
	*/
	protected String namePath; 
	
	
	/**
	* CODE路径
	*/
	protected String codePath; 
	
	
	/**
	* 级别，按数据字典编码
	*/
	protected String areaGrade; 
	
	
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
	 * 上级区域编码，非数据库字段
	 */
	protected String parentAreaCode; 
	
	/**
	 * 上级区域简称，非数据库字段
	 */
	protected String parentAreaAbsName; 
	
	/**
	* 区域地址（县）
	*/
	protected String county; 
	
	
	/**
	* 区域地址（区）
	*/
	protected String district; 
	
	
	/**
	* 区域地址（省）
	*/
	protected String province; 
	
	
	/**
	* 区域地址（市）
	*/
	protected String city; 
	
	
	private List<Area> children = new ArrayList<Area>();

	/**
     * 是否父节点
     */
    private boolean isParent;
    
    /**
     * 联系人
     */
    private String contact;
    
    /**
     * 联系电话
     */
    private String contactMobile;
    
    /**
     * 英文名
     */
    private String areaEngName;
    
	
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

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getContactMobile() {
		return contactMobile;
	}

	public void setContactMobile(String contactMobile) {
		this.contactMobile = contactMobile;
	}

	public String getAreaEngName() {
		return areaEngName;
	}

	public void setAreaEngName(String areaEngName) {
		this.areaEngName = areaEngName;
	}

	public String getCounty() {
		return county;
	}

	public void setCounty(String county) {
		this.county = county;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

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

	public boolean isIsParent() {
		return isParent;
	}
    
    public void setParent(boolean isParent) {
		this.isParent = isParent;
	}
    
	public String getParentAreaCode() {
		return parentAreaCode;
	}

	public void setParentAreaCode(String parentAreaCode) {
		this.parentAreaCode = parentAreaCode;
	}

	public String getParentAreaAbsName() {
		return parentAreaAbsName;
	}

	public void setParentAreaAbsName(String parentAreaAbsName) {
		this.parentAreaAbsName = parentAreaAbsName;
	}

	public void setAreaId(Long areaId) {
		this.areaId = areaId;
	}
	
	/**
	 * 返回 区域标识
	 * @return
	 */
	public Long getAreaId() {
		return this.areaId;
	}
	
	public void setParentAreaId(Long parentAreaId) {
		this.parentAreaId = parentAreaId;
	}
	
	/**
	 * 返回 父区域ID
	 * @return
	 */
	public Long getParentAreaId() {
		return this.parentAreaId;
	}
	
	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}
	
	/**
	 * 返回 区域编码
	 * @return
	 */
	public String getAreaCode() {
		return this.areaCode;
	}
	
	public void setAreaAbsName(String areaAbsName) {
		this.areaAbsName = areaAbsName;
	}
	
	/**
	 * 返回 区域简称
	 * @return
	 */
	public String getAreaAbsName() {
		return this.areaAbsName;
	}
	
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	
	/**
	 * 返回 区域名称
	 * @return
	 */
	public String getAreaName() {
		return this.areaName;
	}
	
	public void setAreaAddress(String areaAddress) {
		this.areaAddress = areaAddress;
	}
	
	/**
	 * 返回 区域地址
	 * @return
	 */
	public String getAreaAddress() {
		return this.areaAddress;
	}
	
	public void setDisplayIndex(Long displayIndex) {
		this.displayIndex = displayIndex;
	}
	
	/**
	 * 返回 序
	 * @return
	 */
	public Long getDisplayIndex() {
		return this.displayIndex;
	}
	
	public void setIdPath(String idPath) {
		this.idPath = idPath;
	}
	
	/**
	 * 返回 ID路径
	 * @return
	 */
	public String getIdPath() {
		return this.idPath;
	}
	
	public void setNamePath(String namePath) {
		this.namePath = namePath;
	}
	
	/**
	 * 返回 NAME路径
	 * @return
	 */
	public String getNamePath() {
		return this.namePath;
	}
	
	public void setCodePath(String codePath) {
		this.codePath = codePath;
	}
	
	/**
	 * 返回 CODE路径
	 * @return
	 */
	public String getCodePath() {
		return this.codePath;
	}
	
	public void setAreaGrade(String areaGrade) {
		this.areaGrade = areaGrade;
	}
	
	/**
	 * 返回 级别，按数据字典编码
	 * @return
	 */
	public String getAreaGrade() {
		return this.areaGrade;
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
	
	public void setStopDate(java.util.Date endDate) {
		this.stopDate = endDate;
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
		.append("areaId", this.areaId) 
		.append("parentAreaId", this.parentAreaId) 
		.append("areaCode", this.areaCode) 
		.append("areaAbsName", this.areaAbsName) 
		.append("areaName", this.areaName) 
		.append("areaAddress", this.areaAddress) 
		.append("displayIndex", this.displayIndex) 
		.append("idPath", this.idPath) 
		.append("namePath", this.namePath) 
		.append("codePath", this.codePath) 
		.append("state", this.state) 
		.append("createUserId", this.createUserId) 
		.append("createTime", this.createTime) 
		.append("modifyUserId", this.modifyUserId) 
		.append("modifyTime", this.modifyTime) 
		.append("areaGrade", this.areaGrade) 
		.append("remarks", this.remarks) 
		.append("openStatus", this.openStatus) 
		.append("openDate", this.openDate) 
		.append("endDate", this.stopDate) 
		.toString();
	}

	@Override
	public Long getId() {
		return this.areaId;
	}

	@Override
	public Long getParentId() {
		return this.parentAreaId;
	}

	@Override
	public String getText() {
		return this.areaName;
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