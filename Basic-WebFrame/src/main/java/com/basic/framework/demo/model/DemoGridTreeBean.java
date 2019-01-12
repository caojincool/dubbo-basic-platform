package com.basic.framework.demo.model;

/**
 * 树型结构
 * 
 * @author lzj
 *
 */
public class DemoGridTreeBean  {
	private Integer level; // 第几层
	private Boolean isLeaf; // 是否为叶子节点
	private Boolean expanded; // 是否展开
	
	private Long parentId; // 父节点标识
	private Long id;//标识
	private String name;//名称
	private String comments;//备注
	
	
	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public Boolean getIsLeaf() {
		return isLeaf;
	}

	public void setIsLeaf(Boolean isLeaf) {
		this.isLeaf = isLeaf;
	}

	public Boolean getExpanded() {
		return expanded;
	}

	public void setExpanded(Boolean expanded) {
		this.expanded = expanded;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}



}
