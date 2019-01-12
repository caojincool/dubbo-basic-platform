package com.basic.oaas.model;
import com.basic.framework.common.model.AbstractModel;
import com.basic.framework.common.model.Tree;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;

 /**
 * 
 * <pre> 
 * 描述：菜单表 实体对象
 * 构建组：basic
 * 作者:lengzj
 * 邮箱:mail@com
 * 日期:2018-06-23 14:48:01
 * 版权：companyName
 * </pre>
 */
public class PrivateMenu extends AbstractModel<Long> implements Tree{
	
	
	/**
	* 菜单ID
	*/
	protected Long menuId; 
	
	
	/**
	* 菜单编码
	*/
	protected String menuCode; 
	
	
	/**
	* 菜单名称
	*/
	protected String menuName; 
	
	
	/**
	* 权限ID
	*/
	protected Long privateId; 
	
	
	/**
	* 菜单URL
	*/
	protected String menuUrl; 
	
	
	/**
	* 菜单图标
	*/
	protected String menuIcon; 
	
	
	/**
	* 菜单顺序
	*/
	protected Long displayIndex; 
	
	
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
	 * 父菜单ID
	 */
	protected Long parentMenuId;
	
	/**
	 * ID路径
	 */
	protected String idPath;
	
	/**
	 * 是否父节点，非数据库字段
	 */
	private boolean isParent;

	private List<PrivateMenu> children = new ArrayList<PrivateMenu>();
	
	private List<PrivateFunc> funcs = new ArrayList<PrivateFunc>();
	
	private List<PrivateAttr> attrs = new ArrayList<PrivateAttr>();
	
	public String getIdPath() {
		return idPath;
	}

	public void setIdPath(String idPath) {
		this.idPath = idPath;
	}

	public Long getParentMenuId() {
		return parentMenuId;
	}

	public void setParentMenuId(Long parentMenuId) {
		this.parentMenuId = parentMenuId;
	}

	public List<PrivateAttr> getAttrs() {
		return attrs;
	}

	public void setAttrs(List<PrivateAttr> attrs) {
		this.attrs = attrs;
	}

	public List<PrivateFunc> getFuncs() {
		return funcs;
	}

	public void setFuncs(List<PrivateFunc> funcs) {
		this.funcs = funcs;
	}

	public boolean isIsParent() {
		return isParent;
	}

	public void setParent(boolean isParent) {
		this.isParent = isParent;
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
	
	public void setMenuCode(String menuCode) {
		this.menuCode = menuCode;
	}
	
	/**
	 * 返回 菜单编码
	 * @return
	 */
	public String getMenuCode() {
		return this.menuCode;
	}
	
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	
	/**
	 * 返回 菜单名称
	 * @return
	 */
	public String getMenuName() {
		return this.menuName;
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
	
	
	public void setMenuUrl(String menuUrl) {
		this.menuUrl = menuUrl;
	}
	
	/**
	 * 返回 菜单URL
	 * @return
	 */
	public String getMenuUrl() {
		return this.menuUrl;
	}
	
	public void setMenuIcon(String menuIcon) {
		this.menuIcon = menuIcon;
	}
	
	/**
	 * 返回 菜单图标
	 * @return
	 */
	public String getMenuIcon() {
		return this.menuIcon;
	}
	
	public void setDisplayIndex(Long displayIndex) {
		this.displayIndex = displayIndex;
	}
	
	/**
	 * 返回 菜单顺序
	 * @return
	 */
	public Long getDisplayIndex() {
		return this.displayIndex;
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
		.append("menuId", this.menuId) 
		.append("menuCode", this.menuCode) 
		.append("menuName", this.menuName) 
		.append("privateId", this.privateId) 
		.append("menuUrl", this.menuUrl) 
		.append("menuIcon", this.menuIcon) 
		.append("displayIndex", this.displayIndex) 
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

	@Override
	public Long getId() {
		return this.menuId;
	}

	@Override
	public Long getParentId() {
		return this.parentMenuId;
	}

	@Override
	public String getText() {
		return this.menuName;
	}

	@Override
	public List getChildren() {
		return this.children;
	}

	@Override
	public void setChildren(List<Tree> list) {
		this.children = this.children;
		
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof PrivateMenu) {
			PrivateMenu temp = (PrivateMenu)obj;
			return temp.getMenuCode().equals(this.menuCode);
		}
		return false;
	}
}