package com.basic.framework.messagequeue.modal;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 *
 * @date 2017年7月12日 下午2:43:43
 * 
 * @Description: 消息消费者实体类
 *
 */
public class MsgInfoConsumer implements Serializable {

	private static final long serialVersionUID = -7880772388424156769L;
	
	private Long consumerId;//消息消费者id
	private String key;//KEY
	private String queueCode;//队列编码
	private Integer isCache;//是否被缓存发送，1-缓存，0-不缓存
	private Integer isPersistence;//是否持久化，1-持久化，0-否
	private Date createTime;//创建时间
	
	public Long getConsumerId() {
		return consumerId;
	}
	public void setConsumerId(Long consumerId) {
		this.consumerId = consumerId;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getQueueCode() {
		return queueCode;
	}
	public void setQueueCode(String queueCode) {
		this.queueCode = queueCode;
	}
	public Integer getIsPersistence() {
		return isPersistence;
	}
	public void setIsPersistence(Integer isPersistence) {
		this.isPersistence = isPersistence;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Integer getIsCache() {
		return isCache;
	}
	public void setIsCache(Integer isCache) {
		this.isCache = isCache;
	}
	
}
