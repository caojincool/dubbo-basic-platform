package com.basic.framework.log.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.basic.framework.common.utils.datatype.JsonUtils;
import com.basic.framework.messagequeue.service.MessageHandler;
import com.basic.log.model.SystemLog;
import com.basic.log.service.SystemLogService;

/**
 * 
 *
 * @date 2017年10月13日 下午2:49:46
 * 
 * @Description: 系统日志队列接收消息回调类
 *
 */
@Repository("logConsumerListenerHandler")
public class LogConsumerListenerHandler implements MessageHandler{

	private static final Logger logger = LoggerFactory.getLogger(LogConsumerListenerHandler.class);
	
	private static final JsonUtils JSON_UTILS = JsonUtils.getInstance();
	
	@Autowired
	private SystemLogService systemLogService;
	
	@Override
	public void handle(String message) {
    	if (logger.isDebugEnabled()) {
    		logger.debug("handle message:{}", message);
    	}
    	
    	SystemLog bean = JSON_UTILS.jsonToObject(message, SystemLog.class);
    	log(bean);
	}
	
	/**
	 * 
	 * @date 2017年10月13日 下午3:07:26
	 * 
	 * @Description: 记录日志
	 * @param bean
	 *
	 */
	private void log(SystemLog bean){
    	if (logger.isDebugEnabled()) {
    		logger.debug("log SystemLog:{}", JSON_UTILS.objectToJson(bean));
    	}
    	systemLogService.createSelective(bean);
	}
}
