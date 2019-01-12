package com.basic.framework.messagequeue.impl;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.basic.framework.cache.CacheRedis;
import com.basic.framework.common.utils.datatype.DateUtils;
import com.basic.framework.common.utils.datatype.JsonUtils;
import com.basic.framework.messagequeue.dao.MsgInfoConsumerDao;
import com.basic.framework.messagequeue.modal.MsgInfoConsumer;
import com.basic.framework.messagequeue.modal.MqGruopListener;

/**
 * 
 *
 * @date 2017年7月12日 下午3:42:16
 * 
 * @Description: 消息监听
 *
 */
public class MessageListenerActiveMQ implements MessageListener{
	
	private static final Logger logger = LoggerFactory.getLogger(MessageListenerActiveMQ.class);
	
	private static final JsonUtils JSON_UTILS = JsonUtils.getInstance();
	
	private static final int IS_PERSISTENCE = 1;//是否持久化
	private static final int IS_CACHE = 1;//是否被缓存发送，1-缓存，0-不缓存
	private static final String DEFAULT_REDIS_CODE="defaultActiveMQCache";
	
	private MqGruopListener listener;
	private String messageText;
	private String receiver;
	
	@Autowired
	private CacheRedis<?, ?> cacheRedis;
	@Autowired
	private MsgInfoConsumerDao messageDao;
	
	private MessageListenerActiveMQ(MqGruopListener listener){
		this.listener = listener;
	}
	
	
	@Override
	public void onMessage(Message message) {
		logger.debug("MessageListenerActiveMQ.onMessage 接收的消息:{}", JSON_UTILS.objectToJson(message));
		String redisCdoe  = StringUtils.isBlank(listener.getRedisGroupCode()) ? DEFAULT_REDIS_CODE : listener.getRedisGroupCode();
		try {
			receiver = ((TextMessage) message).getText();
			if(null != listener.getIsCache() && IS_CACHE == listener.getIsCache().intValue()){//被缓存发送
				try {
					messageText = (String) cacheRedis.getObject(redisCdoe, receiver);
				} catch (Exception e) {
					logger.error("redis getObject redisCdoe:{} receiver:{} error:{}", redisCdoe, receiver, e);
				}
				if(null != listener.getIsPersistence() && IS_PERSISTENCE == listener.getIsPersistence().intValue()){//持久化
					MsgInfoConsumer item  = new MsgInfoConsumer();
//					item.setConsumerId(consumerId);
					item.setKey(receiver);
					item.setQueueCode(listener.getQueueCode());
					item.setIsCache(listener.getIsCache());
					item.setIsPersistence(listener.getIsPersistence());
					item.setCreateTime(DateUtils.now());
					messageDao.createMsgInfoConsumer(item);
					
					//处理完成后从redis中删除
					cacheRedis.removeByKey(redisCdoe, receiver);
				}
			}else{
				messageText = receiver; 
				if(null != listener.getIsPersistence() && IS_PERSISTENCE == listener.getIsPersistence().intValue()){//持久化
					MsgInfoConsumer item  = new MsgInfoConsumer();
//					item.setConsumerId(consumerId);
					item.setKey(receiver);
					item.setQueueCode(listener.getQueueCode());
					item.setIsCache(listener.getIsCache());
					item.setIsPersistence(listener.getIsPersistence());
					item.setCreateTime(DateUtils.now());
					messageDao.createMsgInfoConsumer(item);
				}
			}
		} catch (JMSException e) {
			logger.error("onmessage message:{} error:{}", JSON_UTILS.objectToJson(message), e);
		}
		//业务处理
		listener.getHandler().handle(messageText);
	}
	
}
