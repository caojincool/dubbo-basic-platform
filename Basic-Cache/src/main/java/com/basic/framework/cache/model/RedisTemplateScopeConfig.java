/**
 *
 * companyName
 * copyright 2015-2020
 * @date 2017年3月12日 上午11:53:16
 * 
 * @Description: redis组的配置
 * 
 */
package com.basic.framework.cache.model;

import java.io.Serializable;
import java.util.Map;

import org.springframework.data.redis.core.RedisTemplate;

/**
 *
 * @date 2017年3月12日 上午11:53:16
 * 
 * @param <V>
 * @Description: redis组的配置
 * 
 */
public class RedisTemplateScopeConfig<T, V> implements Serializable {

	private static final long serialVersionUID = 6331525257130303222L;

	private Long liveTime;
	
	private Map<String, RedisTemplate<String, V>> redisTemplateScope;


	/**
	 * @return the liveTime
	 */
	public Long getLiveTime() {
		return liveTime;
	}

	/**
	 * @param liveTime the liveTime to set
	 */
	public void setLiveTime(Long liveTime) {
		this.liveTime = liveTime;
	}

	/**
	 * @return the redisTemplateScope
	 */
	public Map<String, RedisTemplate<String, V>> getRedisTemplateScope() {
		return redisTemplateScope;
	}

	/**
	 * @param redisTemplateScope the redisTemplateScope to set
	 */
	public void setRedisTemplateScope(Map<String, RedisTemplate<String, V>> redisTemplateScope) {
		this.redisTemplateScope = redisTemplateScope;
	}
	
	
	
	
}
