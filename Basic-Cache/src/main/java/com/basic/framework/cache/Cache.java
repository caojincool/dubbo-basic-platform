/**
 *
 * companyName
 * copyright 2015-2020
 * @date 2017年3月7日 上午11:43:12
 * 
 * @Description: 操作缓存接口
 * 
 */
package com.basic.framework.cache;

import java.util.List;

import com.basic.framework.cache.model.RedisCache;

/**
 *
 * @date 2017年3月7日 上午11:43:12
 * 
 * @Description: 操作缓存接口
 * 
 */
public interface Cache {

	/**
	 * 
	 * @date 2017年3月7日 上午11:45:04
	 * 
	 * @Description: 从缓存里获取String类型的值
	 * @param cacheCode 缓存编码
	 * @param key 缓存键
	 * @return
	 *
	 */
//	String getString(String cacheCode,String key);
	
	/**
	 * 
	 * @date 2017年3月7日 上午11:47:07
	 * 
	 * @Description: 从缓存里获取byte数组
	 * @param cacheCode 缓存编码
	 * @param key 缓存键
	 * @return
	 *
	 */
//	byte[] getBytes(String cacheCode,String key);
	
	/**
	 * 
	 * @date 2017年3月7日 上午11:48:27
	 * 
	 * @Description: 设置String类型的缓存
	 * @param cacheCode 缓存编码
	 * @param key 缓存键
	 * @param value 缓存值
	 *
	 */
//	void setString(String cacheCode,String key,String value);
	
	/**
	 * 
	 * @date 2017年3月7日 上午11:49:47
	 * 
	 * @Description: 设置byte数组类型的缓存
	 * @param cacheCode 缓存编码
	 * @param key 缓存键
	 * @param value 缓存值
	 *
	 */
//	void setBytes(String cacheCode,String key,byte[] value);
	
	/**
	 * 
	 * @date 2017年3月12日 上午11:27:44
	 * 
	 * @Description: 根据key删除缓存
	 * @param cacheCode
	 * @param key
	 *
	 */
	void removeByKey(String cacheCode,String key);
	
	/**
	 * 
	 * @date 2017年3月12日 上午11:43:56
	 * 
	 * @Description: 根据cacheCode删除所有缓存。
	 * @param cacheCode
	 *
	 */
	void removeByCacheCode(String cacheCode);

	/**
	 * 
	 * @date 2017年6月26日 下午4:37:57
	 * 
	 * @Description: 根据key，模糊查询一个集群里面的所有的值
	 * cacheCode只是决定用来拿到对应的连接，并不能缩小拿值的范围
	 * @param cacheCode
	 * @param key
	 * @return
	 *
	 */
	public <T> List<T> qryByKeyForFuzzy(String cacheCode, String key);
	
	/**
	 * 
	 * @date 2017年7月3日 上午10:25:20
	 * 
	 * @Description: 缓存一个序列化之后的对象
	 * @param cacheCode
	 * @param key
	 * @param obj
	 * @throws Exception
	 *
	 */
	public void setObject(String cacheCode,String key,Object obj) throws Exception;
	
	/**
	 * 
	 * @date 2017年7月3日 上午10:26:02
	 * 
	 * @Description: 从缓存中取得一个反序列化之后的对象
	 * @param cacheCode
	 * @param key
	 * @return
	 * @throws Exception
	 *
	 */
	public Object getObject(String cacheCode,String key) throws Exception;
	
	/**
	 * 
	 * @date 2017年7月20日 下午5:29:17
	 * 
	 * @Description: 根据key，模糊查询一个集群里面的所有的值
	 * cacheCode只是决定用来拿到对应的连接，并不能缩小拿值的范围
	 * @param cacheCode
	 * @param key
	 * @return
	 *
	 */
	public List<RedisCache> getAllByKey(String cacheCode, String key) throws Exception;
}
