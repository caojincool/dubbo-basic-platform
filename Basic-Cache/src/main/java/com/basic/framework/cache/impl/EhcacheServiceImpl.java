package com.basic.framework.cache.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.basic.framework.cache.model.EhcacheBean;
import com.basic.framework.cache.service.EhcacheService;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;

/**
 * 
 *
 * @date 2017年8月7日 上午10:06:38
 * 
 * @Description: ehcache
 *
 */
//@Service("ehCacheService")
public class EhcacheServiceImpl implements EhcacheService {

	private static final Logger logger = LoggerFactory.getLogger(EhcacheServiceImpl.class);
	
	private CacheManager cacheManager;

	public void setCacheManager(org.springframework.cache.ehcache.EhCacheCacheManager cacheManager) {
		this.cacheManager = cacheManager.getCacheManager();
	}

	@Override
	public List<EhcacheBean> qryCacheList() {
		if(logger.isDebugEnabled()){
			logger.debug("qryCacheList");
		}
		
		List<EhcacheBean> list = new ArrayList<EhcacheBean>();
		
		String[] cacheNames = cacheManager.getCacheNames();
		for (String cacheName : cacheNames) {
			
			EhcacheBean ehCacheBean = new EhcacheBean();
			ehCacheBean.setCacheName(cacheName);
			
			Cache cache = cacheManager.getCache(cacheName);

			int cacheSize = cache.getSize();
			ehCacheBean.setCacheSize(cacheSize);
			
			list.add(ehCacheBean);
		}
		return list;
	}
	
	@Override
	public int qryCacheSizeByCacheName(String cacheName) {
		if(logger.isDebugEnabled()){
			logger.debug("qryCacheSizeByCacheName cacheName:{}", cacheName);
		}
		
		Cache cache = cacheManager.getCache(cacheName);
		int cacheSize = cache.getSize();
		return cacheSize;
	}

	@Override
	public void clearCache(String cacheName) {
		if(logger.isDebugEnabled()){
			logger.debug("clearCache cacheName:{}", cacheName);
		}
		
		Cache cache = cacheManager.getCache(cacheName);
		cache.removeAll();
	}

	@Override
	public void clearAll() {
		if(logger.isDebugEnabled()){
			logger.debug("clearAll");
		}
		
		cacheManager.clearAll();
	}



}
