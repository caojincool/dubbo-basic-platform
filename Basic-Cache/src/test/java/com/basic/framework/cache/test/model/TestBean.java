/**
 *
 * companyName
 * copyright 2015-2020
 * @date 2017年3月12日 下午2:29:53
 * 
 * @Description: TODO(这里用一句话描述这个类的作用)
 * 
 */
package com.basic.framework.cache.test.model;

import java.io.Serializable;
import java.util.List;

/**
 * 
 *
 * @date 2017年8月7日 上午11:31:44
 * 
 * @Description: 测试bean
 *
 */
public class TestBean implements Serializable {

	
	private static final long serialVersionUID = 2133903243708918981L;

	private int id;
	
	private String name;
	
	private List<String> list;

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the list
	 */
	public List<String> getList() {
		return list;
	}

	/**
	 * @param list the list to set
	 */
	public void setList(List<String> list) {
		this.list = list;
	}
	
	
}
