package com.basic.framework.demo.service;

import com.basic.framework.demo.model.Demo;

public interface DemoService {

	/**
	 * 按主键查询
	 * @param id
	 * @throws Exception
	 */
	public Demo queryByKey(String id) throws Exception;
}
