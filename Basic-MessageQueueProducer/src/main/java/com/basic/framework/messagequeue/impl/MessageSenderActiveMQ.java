package com.basic.framework.messagequeue.impl;

import java.util.HashMap;
import java.util.Map;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import com.basic.framework.cache.CacheRedis;
import com.basic.framework.common.utils.datatype.DateUtils;
import com.basic.framework.common.utils.datatype.IntegerUtils;
import com.basic.framework.messagequeue.dao.MsgInfoProducerDao;
import com.basic.framework.messagequeue.modal.MsgInfoProducer;
import com.basic.framework.messagequeue.modal.MqGroupSender;
import com.basic.framework.messagequeue.service.MessageSender;

/**
 * 
 *
 * @date 2017年7月12日 下午12:09:44
 * 
 * @Description: 发送消息到消息队列
 *
 */
public class MessageSenderActiveMQ implements MessageSender {

	private static final Logger logger = LoggerFactory.getLogger(MessageSenderActiveMQ.class);
	
	private static final String SPLIT_STR = "-";// SCOPE分隔符
	private static final int IS_CACHE = 1;//是否缓存
	private static final int IS_PERSISTENCE = 1;//是否持久化
	private static final String PERSISTENCE_KEY = "KEY";//持久化类型：KEY
	private static final String PERSISTENCE_KEY_VALUE = "KEY_VALUE";//持久化类型：KEY_VALUE
	private static final String STUFFIX  = ".topic";
	private static final String DEFAULT_REDIS_CODE="defaultActiveMQCache";

	private Map<String, JmsTemplate> jmsTemplateMap;
	private Map<String, MqGroupSender> groupMap;
	

	@Autowired
	private MsgInfoProducerDao messageDao;
		
	private Map<String,Destination> destinationMap = new HashMap<String,Destination>();
	
	@Autowired
	private CacheRedis<?, ?> cacheRedis;
		
	public MessageSenderActiveMQ(Map<String, MqGroupSender> groupMap){
		logger.info("=============>MessageSenderActiveMQ init start");
		this.groupMap = groupMap;
		this.jmsTemplateMap = new HashMap<String, JmsTemplate>();
		for ( String queueCode : groupMap.keySet()) {
			logger.debug("queueCode :{}",queueCode);
			MqGroupSender mqGroup = groupMap.get(queueCode);
			Map<String,JmsTemplate> jmsTamplateScopeMap = mqGroup.getJmsTamplateScopeMap();
			//取得目的地编码-destinationCode
			String destinationCode  = mqGroup.getDestinationCode();
			if(StringUtils.isBlank(destinationCode)){
				logger.error("=============>MessageSenderActiveMQ destinationCode is null");
				throw new NullPointerException("the destinationCode cannot be null!!!");
			}
			if(StringUtils.endsWith(destinationCode, STUFFIX)){
				Destination destination  = new ActiveMQTopic(destinationCode);
				destinationMap.put(queueCode, destination);
			}else{
				Destination destination  = new ActiveMQQueue(destinationCode);
				destinationMap.put(queueCode, destination);
			}
			
			//初始化发送模板
			for (String scope : jmsTamplateScopeMap.keySet()) {
				logger.debug("queueCode :{},scope :{}",queueCode,scope);
				String[] s = scope.split(SPLIT_STR);
				int min = IntegerUtils.valueOf(s[0]);
				int max = IntegerUtils.valueOf(s[1]); 
				JmsTemplate template = jmsTamplateScopeMap.get(scope);
				for(int i=min;i<max;i++){
					jmsTemplateMap.put(i+queueCode, template);
				}
			}
		}
		
		logger.info("=============>MessageSenderActiveMQ init success");
	}
	
	@Override
	public void sendMessage(final String key, String queueCode,final String message) {
		//根据queueCode得到destination
		Destination destination = destinationMap.get(queueCode);
		JmsTemplate jmsTemplate = this.getJmsTemplate(key, queueCode);
		MqGroupSender mqGroup = groupMap.get(queueCode);
		if(null != mqGroup.getIsPersistence() 
				&& IS_PERSISTENCE == mqGroup.getIsPersistence().intValue()){//持久化
			MsgInfoProducer item = new MsgInfoProducer();
//			item.setProducerId(producerId);
			item.setQueueCode(queueCode);
			item.setIsCache(mqGroup.getIsCache());
			item.setIsPersistence(mqGroup.getIsPersistence());
			item.setPersistenceType(mqGroup.getPersistenceType());
			item.setCreateTime(DateUtils.now());
			if(StringUtils.isNotBlank(mqGroup.getPersistenceType()) 
					&& PERSISTENCE_KEY_VALUE.equals(mqGroup.getPersistenceType())){//持久化key和value
				item.setKey(key);
				item.setMessage(message);
			}else if(PERSISTENCE_KEY.equals(mqGroup.getPersistenceType())){//持久化key
				item.setKey(key);
			}else{//默认持久化key
				item.setKey(key);
			}
			persistenceMSG(item);
		}
		if(null != mqGroup.getIsCache() 
				&& IS_CACHE == mqGroup.getIsCache().intValue()){//缓存，将key存入队列发送，value存入分布式缓存
			String redisCdoe  = StringUtils.isBlank(mqGroup.getRedisGroupCode())?DEFAULT_REDIS_CODE:mqGroup.getRedisGroupCode();
			try {
				cacheRedis.setObject(redisCdoe,key, message);
			} catch (Exception e) {
				logger.error("redis setObject redisCdoe:{} key:{} message:{} error:{}", redisCdoe, key, message, e);
			}
			//将key作为消息放入队列
			jmsTemplate.send(destination,new MessageCreator() {
				@Override
                public Message createMessage(Session session) throws JMSException {
					return session.createTextMessage(key);
				}
			});
		}else{//不缓存，直接发送
			jmsTemplate.send(destination,new MessageCreator() {
				@Override
                public Message createMessage(Session session) throws JMSException {
					return session.createTextMessage(message);
				}
			});
		}
		
	}
	
	/**
	 * 
	 * @date 2017年7月12日 下午12:07:38
	 * 
	 * @Description: 根据key.hashCode%1024+queueCode，取发送模板
	 * @param key
	 * @param queueCode
	 * @return
	 *
	 */
	private JmsTemplate getJmsTemplate(String key, String queueCode) {
		int hashCode = Math.abs(key.hashCode());
		String scopeKey = hashCode % 1024+queueCode;
		JmsTemplate jmsTemplate = jmsTemplateMap.get(scopeKey);
		if(jmsTemplate==null){
			logger.error("jmsTemplate is null. hashCode:{},scopeKey:{}",hashCode,scopeKey);
		}
		return jmsTemplate;
	}
	
	/**
	 * 
	 * @date 2017年7月12日 下午12:08:13
	 * 
	 * @Description: 持久化消息
	 * @param msgInfoProducer
	 * @return
	 *
	 */
	private boolean persistenceMSG(MsgInfoProducer msgInfoProducer){
		return messageDao.createMsgInfoProducer(msgInfoProducer)==1?true:false;		
	}
	
}
