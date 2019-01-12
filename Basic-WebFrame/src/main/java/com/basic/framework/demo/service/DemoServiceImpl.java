package com.basic.framework.demo.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.basic.framework.demo.dao.DemoMapper;
import com.basic.framework.demo.model.Demo;
import com.basic.framework.demo.service.DemoService;

//@Service("demoService")
public class DemoServiceImpl implements DemoService {

	@Autowired
	private DemoMapper demoMapper;
	
	@Override
	public Demo queryByKey(String id) throws Exception {
		// TODO Auto-generated method stub
		Demo demo = demoMapper.queryByKey(id);
		return demo;
	}

}
