package com.basic.framework.cache.test;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.basic.framework.cache.test.model.TestBean;
import com.basic.framework.cache.test.util.BeanFactoryUtils;
import com.basic.framework.common.utils.datatype.JsonUtils;

/**
 * 
 *
 * @date 2017年8月7日 上午11:03:21
 * 
 * @Description: ehcache测试
 *
 */
public class EhcacheTest {

	private static Logger logger = LoggerFactory.getLogger(EhcacheTest.class);
	
	private static final JsonUtils JSON_UTILS = JsonUtils.getInstance();
	
	private TestEhcacheImpl testEhcacheImpl;

	@Before
	public void init() {
		testEhcacheImpl = BeanFactoryUtils.getBeanByName("testEhcacheImpl", TestEhcacheImpl.class);
	}

	/**
	 * 
	 * @date 2017年8月7日 上午11:45:36
	 * 
	 * @Description: 测试ehcache缓存、清空缓存、对象是否可以作为缓存的参数
	 *
	 */
	@Test
	public void testEhcache() {
		Long id1 = 1L;
		Long id2 = 2L;
		
		//一个参数缓存
		logger.debug("第1次 qryById 查询");
		TestBean bean1 = testEhcacheImpl.qryById(id1);
		logger.debug("第1次 qryById 结果:{}", JSON_UTILS.objectToJson(bean1));
		
		logger.debug("第2次 qryById 查询");
		TestBean bean2 = testEhcacheImpl.qryById(id1);
		logger.debug("第2次 qryById 结果:{}", JSON_UTILS.objectToJson(bean2));
		
		//多个参数缓存
		logger.debug("第1次 qryByIds 查询");
		TestBean bean3 = testEhcacheImpl.qryByIds(id1, id2);
		logger.debug("第1次 qryByIds 结果:{}", JSON_UTILS.objectToJson(bean3));
		
		logger.debug("第2次 qryByIds 查询");
		TestBean bean4 = testEhcacheImpl.qryByIds(id1, id2);
		logger.debug("第2次 qryByIds 结果:{}", JSON_UTILS.objectToJson(bean4));
		
		//对象缓存
		logger.debug("第1次 qryByIbean 查询");
		TestBean ibean1 = new TestBean();
		ibean1.setId(1);
		ibean1.setName("jie");
		TestBean bean5 = testEhcacheImpl.qryByIbean(ibean1);
		logger.debug("第1次 qryByIbean 结果:{}", JSON_UTILS.objectToJson(bean5));
		
		//由于对象地址不一致，所以就算对象里面参数的值一致也没法取到之前缓存进去的东西
		logger.debug("第2次 qryByIbean 查询");
		TestBean ibean2 = new TestBean();
		ibean2.setId(1);
		ibean2.setName("jie");
		TestBean bean6 = testEhcacheImpl.qryByIbean(ibean2);
		logger.debug("第2次 qryByIbean 结果:{}", JSON_UTILS.objectToJson(bean6));
		
		//用的是第一个对象，地址一致，所以可以取到之前缓存进去的东西
		logger.debug("第3次 qryByIbean 查询");
		TestBean bean7 = testEhcacheImpl.qryByIbean(ibean2);
		logger.debug("第3次 qryByIbean 结果:{}", JSON_UTILS.objectToJson(bean7));
		
		//清空缓存
		logger.debug("清空缓存");
		testEhcacheImpl.clearCache();
		
		logger.debug("清空缓存后 qryById 查询");
		TestBean bean8 = testEhcacheImpl.qryById(id1);
		logger.debug("清空缓存后 qryById 结果:{}", JSON_UTILS.objectToJson(bean8));
		
		logger.debug("清空缓存后 qryByIds 查询");
		TestBean bean9 = testEhcacheImpl.qryByIds(id1, id2);
		logger.debug("清空缓存后 qryByIds 结果:{}", JSON_UTILS.objectToJson(bean9));
		
		logger.debug("清空缓存后 qryByIbean 查询");
		TestBean bean10 = testEhcacheImpl.qryByIbean(ibean2);
		logger.debug("清空缓存后 qryByIbean 结果:{}", JSON_UTILS.objectToJson(bean10));
		
	}
	
}
