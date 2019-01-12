package com.basic.framework.cache.service;

import java.util.List;

import com.basic.framework.cache.model.EhcacheBean;

/**
 * 
 *
 * @date 2017年8月7日 上午10:03:53
 * 
 * @Description: ehcache
 *
 */
public interface EhcacheService {
	
	/**
	 * 
	 * @date 2017年8月7日 上午10:04:45
	 * 
	 * @Description: 获取缓存名称列表
	 * @return
	 *
	 */
	public List<EhcacheBean> qryCacheList();
	
	/**
	 * 
	 * @date 2017年8月7日 上午10:05:03
	 * 
	 * @Description: 根据cacheName查询Size
	 * @param cacheName
	 * @return
	 *
	 */
	public int qryCacheSizeByCacheName(String cacheName);
	
	/**
	 * 
	 * @date 2017年8月7日 上午10:05:11
	 * 
	 * @Description: 根据cahceName清空缓存
	 * @param cacheName
	 *
	 */
	public void clearCache(String cacheName);
	
	/**
	 * 
	 * @date 2017年8月7日 上午10:05:20
	 * 
	 * @Description: 清空所有
	 *
	 */
	public void clearAll();
	
}
