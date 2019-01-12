package com.basic.framework.demo.dao;

import com.basic.framework.demo.model.Demo;
import com.basic.framework.demo.model.DemoMenu;

/**
 * 
 * 数据库操作
 * @date 2016年8月4日 下午5:43:43
 * @author lzj
 * @Description: 数据库操作
 *
 */
public interface DemoMapper {
	/**
	 * 根据主键查询
	 * @date 2016年8月4日 下午5:43:30
	 * @author lzj
	 * @Description: 根据主键查询
	 * @param id
	 * @return
	 *
	 */
	public Demo queryByKey(String id);
	
	/**
	 * 
	 * @date 2017年6月21日 下午3:34:33
	 * 
	 * @Description: 查询所有的菜单
	 * @return
	 *
	 */
	public DemoMenu selectMenuAll();
}
