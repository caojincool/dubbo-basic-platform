package com.basic.framework.cache.test;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.basic.framework.cache.CacheRedis;
import com.basic.framework.cache.model.RedisCache;
import com.basic.framework.cache.test.util.BeanFactoryUtils;
import com.basic.framework.common.utils.datatype.JsonUtils;

/**
 * 
 *
 * @date 2017年6月20日 下午6:02:10
 * 
 * @Description: 测试redis缓存
 *
 */
public class CacheRedisTest {

	private CacheRedis<String, String> cacheRedis;

	@SuppressWarnings("unchecked")
	@Before
	public void init() {
		cacheRedis = BeanFactoryUtils.getBeanByName("cacheRedis", CacheRedis.class);
	}

	/**
	 * 
	 * @date 2017年6月20日 下午6:02:28
	 * 
	 * @Description: 测试redis缓存字符串
	 *
	 */
//	@Test
//	public void testCacheString() {
//		String originalString = "123";
//		// 设置String
//		cacheRedis.setString("oaasStaffCache", "testString1", originalString);
//		cacheRedis.setString("oaasStaffCache", "testString1", "321");
//		// 获取String
//		String cacheString = cacheRedis.getString("oaasStaffCache", "testString1");
//
//		System.out.println(cacheString);
//		Assert.assertEquals(cacheString, originalString);
//	}

	/**
	 * 
	 * @date 2017年6月20日 下午6:02:46
	 * 
	 * @Description: 测试redis缓存字节流
	 * @throws Exception
	 *
	 */
//	@Test
//	public void testCacheBytes() throws Exception {
//		
//		TestBean testBean = new TestBean();
//		testBean.setId(123);
//		testBean.setName("张三");
//		List<String> list = new ArrayList<>();
//		list.add("123");
//		list.add("345");
//		testBean.setList(list);
//		
//		@SuppressWarnings("unchecked")
//		CacheRedis<String, String> cacheRedis = BeanFactoryUtils.getBeanByName("cacheRedis", CacheRedis.class);
//		byte[] originalBytes = ObjectUtils.toByteArray(testBean);
//		
//		// 设置bytes
//		cacheRedis.setBytes("oaasStaffCache", "testBytes1", originalBytes);
//		// 取bytes
//		byte[] cacheBytes = cacheRedis.getBytes("oaasStaffCache", "testBytes1");
//		Object cacheObj = ObjectUtils.toObject(cacheBytes);
//		
//		System.out.println(JsonUtils.getInstance().objectToJson(cacheObj));
//		Assert.assertArrayEquals(originalBytes, cacheBytes);
//	}

	/**
	 * 
	 * @date 2017年6月21日 上午9:25:01
	 * 
	 * @Description: 测试根据key删除redis缓存
	 *
	 */
//	@Test
//	public void testRemoveByKey() {
//		String originalString = "123";
//		// 设置String
//		cacheRedis.setString("oaasStaffCache", "testString1", originalString);
//		String cacheString = cacheRedis.getString("oaasStaffCache", "testString1");
//		System.out.println(cacheString);
//		cacheRedis.removeByKey("oaasStaffCache", "testString1");
//		System.out.println(cacheRedis.getString("oaasStaffCache", "testString1"));
//	}
	
	/**
	 * 
	 * @date 2017年6月21日 上午9:28:06
	 * 
	 * @Description: 测试根据cacheCode删除redis缓存，可能删除多个
	 *
	 */
//	@Test
//	public void testRemoveByCacheCode(){
//		
//		//两个同集群编码.一个不同集群编码
//		cacheRedis.setString("oaasStaffCache", "testString1", "123");
//		cacheRedis.setString("oaasStaffCache", "testString2", "1234");
//		cacheRedis.setString("oaasStaffCache1", "test", "12345");
//		System.out.println(cacheRedis.getString("oaasStaffCache", "testString1"));
//		System.out.println(cacheRedis.getString("oaasStaffCache", "testString2"));
//		System.out.println(cacheRedis.getString("oaasStaffCache1", "test"));
//		
//		cacheRedis.removeByCacheCode("oaasStaffCache");
//		System.out.println(cacheRedis.getString("oaasStaffCache", "testString1"));
//		System.out.println(cacheRedis.getString("oaasStaffCache", "testString2"));
//		System.out.println(cacheRedis.getString("oaasStaffCache1", "test"));
//	}
	
	@Test
	public void testQryByKeyForFuzzy() {
		// 获取String
		List<Object> list = cacheRedis.qryByKeyForFuzzy("oaasStaffCache", "*");
		System.out.println(JsonUtils.getInstance().objectToJson(list));
	}
	
	/**
	 * 
	 * @date 2017年7月3日 上午10:29:24
	 * 
	 * @Description: 测试缓存序列化与反序列化对象
	 * @throws Exception
	 *
	 */
	@Test
	public void testGetSetSerializeObj() throws Exception{
		
		 RedisCache redisCache= new RedisCache();
		 redisCache.setCacheCode("123");
		 redisCache.setKey("456");
		 redisCache.setValue("789");
		 
		cacheRedis.setObject("oaasStaffCache", "testGetSetSerializeObj", redisCache);
		cacheRedis.setObject("oaasStaffCache", "testGetSetSerializeObj1", "这是一个字符串");
		
		RedisCache resultBean = (RedisCache) cacheRedis.getObject("oaasStaffCache", "testGetSetSerializeObj");
		String str =  (String) cacheRedis.getObject("oaasStaffCache", "testGetSetSerializeObj1");
		System.out.println(JsonUtils.getInstance().objectToJson(resultBean));
		System.out.println(str);
	}
	
}
