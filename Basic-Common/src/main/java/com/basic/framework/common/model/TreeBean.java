package com.basic.framework.common.model;

/**
 * 用于实现树型结构的展示
 * 在树型结构展示时，每个节点的dto应该继承此类
 */
public class TreeBean {
	
	//private Long parent;
	
	//private String isLeaf;//是否为叶子节点，"true"是，"false"不是
	
	//private String expanded;//是否展开
	
	//private Integer level;//层级
	
	
	//以下为ztree的属性
	public Long id;//当前节点id
	
	private Long pId;//父节点id
	
	private String name;//节点显示名称
	
	private String isParent;//是否是父节点，"true"是，"false"不是
	
	private String open;//是否展开，"true"是，"false"不是
	
	private String checked;//是否勾选，"true"是，"false"不是
	
	private String isHidden;//是否隐藏，"true"是，"false"不是
	
/*	public TreeBean(Long id,Long parent){
		this.id = id;
		this.parent = parent;
	}*/
	
	public TreeBean(Long id,Long pId){
		this.id = id;
		this.pId = pId;
	}
	
	public TreeBean(){
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getpId() {
		return pId;
	}

	public void setpId(Long pId) {
		this.pId = pId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIsParent() {
		return isParent;
	}

	public void setIsParent(String isParent) {
		this.isParent = isParent;
	}

	public String getOpen() {
		return open;
	}

	public void setOpen(String open) {
		this.open = open;
	}

	public String getChecked() {
		return checked;
	}

	public void setChecked(String checked) {
		this.checked = checked;
	}

	public String getIsHidden() {
		return isHidden;
	}

	public void setIsHidden(String isHidden) {
		this.isHidden = isHidden;
	}

	
}
