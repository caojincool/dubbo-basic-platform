package com.basic.framework.messagequeue.modal;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 *
 * @date 2017年7月12日 上午11:00:55
 * 
 * @Description: 消息生产者实体类
 *
 */
public class MsgInfoProducer implements Serializable {

	private static final long serialVersionUID = 8119186527340531149L;
	
	private Long producerId;//消息生产者id
	private String key;//KEY
	private String message;//消息内容
	private String queueCode;//队列编码
	private Integer isCache;//是否缓存，1-缓存，0-不缓存
	private Integer isPersistence;//是否持久化，1-持久化，0-否
	private String persistenceType;//持久化类型，KEY-持久化key，KEY_VALUE-keyValue同时持久化
	private Date createTime;//创建时间
	
	public Long getProducerId() {
		return producerId;
	}
	public void setProducerId(Long producerId) {
		this.producerId = producerId;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getQueueCode() {
		return queueCode;
	}
	public void setQueueCode(String queueCode) {
		this.queueCode = queueCode;
	}
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
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
}
