package com.basic.framework.messagequeue.modal;

import java.io.Serializable;
import java.util.Map;

import org.springframework.jms.core.JmsTemplate;

/**
 * 
 *
 * @date 2017年7月12日 上午11:25:52
 * 
 * @Description: 集群实体类
 *
 */
public class MqGroupSender implements Serializable {

	private static final long serialVersionUID = -8034491716012009869L;
	
	private Integer isCache;//是否缓存，1-缓存，0-不缓存
	private Integer isPersistence;//是否持久化，1-持久化，0-否
	private String persistenceType;//持久化类型，KEY-持久化key，KEY_VALUE-keyValue同时持久化
	private String redisGroupCode;//redis的缓存编码-cacheCode
	private String destinationCode;//消息的目的地编码
	
	private Map<String,JmsTemplate> jmsTamplateScopeMap;//发送模板

	public Integer getIsCache() {
		return isCache;
	}

	public void setIsCache(Integer isCache) {
		this.isCache = isCache;
	}

	public Integer getIsPersistence() {
		return isPersistence;
	}

	public void setIsPersistence(Integer isPersistence) {
		this.isPersistence = isPersistence;
	}

	public String getPersistenceType() {
		return persistenceType;
	}

	public void setPersistenceType(String persistenceType) {
		this.persistenceType = persistenceType;
	}

	public String getRedisGroupCode() {
		return redisGroupCode;
	}

	public void setRedisGroupCode(String redisGroupCode) {
		this.redisGroupCode = redisGroupCode;
	}

	public String getDestinationCode() {
		return destinationCode;
	}

	public void setDestinationCode(String destinationCode) {
		this.destinationCode = destinationCode;
	}

	public Map<String, JmsTemplate> getJmsTamplateScopeMap() {
		return jmsTamplateScopeMap;
	}

	public void setJmsTamplateScopeMap(Map<String, JmsTemplate> jmsTamplateScopeMap) {
		this.jmsTamplateScopeMap = jmsTamplateScopeMap;
	}
	
}
