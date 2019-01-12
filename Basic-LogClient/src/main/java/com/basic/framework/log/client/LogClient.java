package com.basic.framework.log.client;

import com.basic.framework.common.utils.datatype.JsonUtils;
import com.basic.framework.messagequeue.impl.MessageSenderActiveMQ;
import com.basic.log.model.SystemConfig;
import com.basic.log.model.SystemLog;
import com.basic.log.service.SystemConfigService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 
 *
 * @date 2017年10月13日 上午11:20:15
 * 
 * @Description: 系统日志记录客户端
 *
 */
@Component("logClient")
public class LogClient {

    private static final Logger logger = LoggerFactory.getLogger(LogClient.class);

    private static final JsonUtils JSON_UTILS = JsonUtils.getInstance();
    private static final String REQUEST_FLAG = "1";//是否记录日志，1记录，0不记录
    private static final String REQUEST_CONTENT_FLAG = "1";//是否记录内容，1记录，0不记录
    private static final String LOG_NOTICE_SENDNOTICE_QUEUE = "log.notice.sendnotice.queue";
    
	@Autowired
	private MessageSenderActiveMQ messageSenderActiveMQ;
	@Autowired
	private SystemConfigService systemConfigService;

    /**
     * 发送指令
     * @param bean
     * @throws Exception
     */
    public void sendLog(SystemLog bean){
    	if (logger.isDebugEnabled()) {
    		logger.debug("sendLog SystemLog:{}", JSON_UTILS.objectToJson(bean));
    	}
    	
    	SystemConfig systemConfig = systemConfigService.qryByRequestUrl(bean.getRequestUrl());
    	if(systemConfig != null){
    		if(REQUEST_FLAG.equals(systemConfig.getRequestFlag())){//记录日志
    			if(REQUEST_CONTENT_FLAG.equals(systemConfig.getRequestContentFlag())){//记录日志内容
        			
        		}else{//不记录日志内容
        			bean.setRequestContent(null);
        		}
    			try{
    				//写入队列
    				String key = bean.getRequestUrl();////不缓存，直接发送，key没作用
    				String message = JSON_UTILS.objectToJson(bean);
    				messageSenderActiveMQ.sendMessage(key, LOG_NOTICE_SENDNOTICE_QUEUE, message);
    			}catch (Exception e){
    				logger.error("sendLog ERROR e:{}", JSON_UTILS.objectToJson(e));
    			}
    		}
    	}
    }

}
