package com.basic.framework.messagequeue.modal;

import java.io.Serializable;

import com.basic.framework.messagequeue.service.MessageHandler;

/**
 * 
 *
 * @date 2017年7月12日 下午3:18:50
 * 
 * @Description: 消息监听实体类
 *
 */
public class MqGruopListener implements Serializable {

	private static final long serialVersionUID = 7461498261197892250L;
	
	private Integer isCache;//是否被缓存发送，1-缓存，0-不缓存
	private Integer isPersistence;//是否持久化，1-持久化，0-否
	private String redisGroupCode;//redis的缓存编码-cacheCode
	private String queueCode;//队列编码，没作用，目前只为了数据库记录
	
	private MessageHandler handler;//业务类回调接口

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

	public String getRedisGroupCode() {
		return redisGroupCode;
	}

	public void setRedisGroupCode(String redisGroupCode) {
		this.redisGroupCode = redisGroupCode;
	}

	public String getQueueCode() {
		return queueCode;
	}

	public void setQueueCode(String queueCode) {
		this.queueCode = queueCode;
	}

	public MessageHandler getHandler() {
		return handler;
	}

	public void setHandler(MessageHandler handler) {
		this.handler = handler;
	}

}
