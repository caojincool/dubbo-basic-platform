package com.basic.framework.cache.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import com.basic.framework.cache.test.model.TestBean;

/**
 * 
 *
 * @date 2017年8月7日 上午11:24:44
 * 
 * @Description: ehcache测试
 *
 */
@Component("testEhcacheImpl")
public class TestEhcacheImpl{
	
	private Logger logger = LoggerFactory.getLogger(TestEhcacheImpl.class);
	
	@Cacheable(value="demoEhcache", key="#id+'_DEMO'")
	public TestBean qryById(Long id) {
		logger.info("通过数据库查询");
		TestBean testBean = new TestBean();
		testBean.setId(1);
		testBean.setName("jie");
		return testBean;
	}
	
	@Cacheable(value="demoEhcache", key="#id1+#id2+'_DEMO'")
	public TestBean qryByIds(Long id1, Long id2) {
		logger.info("通过数据库查询");
		TestBean testBean = new TestBean();
		testBean.setId(1);
		testBean.setName("jie");
		return testBean;
	}
	
	
	@CacheEvict(value="demoEhcache", allEntries=true)
	public void clearCache(){
		logger.info("清空ehcache缓存");
	}

	@Cacheable(value="demoEhcache", key="#ibean+'_DEMO'")
	public TestBean qryByIbean(TestBean ibean) {
		logger.info("通过数据库查询");
		TestBean testBean = new TestBean();
		testBean.setId(1);
		testBean.setName("jie");
		return testBean;
	}

	

	
}
