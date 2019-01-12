package com.basic.framework.messagequeue.dao;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.basic.framework.common.utils.datatype.JsonUtils;
import com.basic.framework.gid.utils.sequence.GidClientUtils;
import com.basic.framework.messagequeue.modal.MsgInfoConsumer;

/**
 * 
 *
 * @date 2017年7月12日 下午3:43:01
 * 
 * @Description: 消息消费者，数据库操作类
 *
 */
@Repository
public class MsgInfoConsumerDao {
	
	private Logger logger = LoggerFactory.getLogger(MsgInfoConsumerDao.class);
	
	private static final JsonUtils JSON_UTILS = JsonUtils.getInstance();
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate){
		this.jdbcTemplate = jdbcTemplate;
	}

	/**
	 * 
	 * @date 2017年7月12日 下午3:52:18
	 * 
	 * @Description: 新增一条记录
	 * @param msgInfoConsumer
	 * @return
	 *
	 */
	public int createMsgInfoConsumer(MsgInfoConsumer msgInfoConsumer){
		logger.debug("createMsgInfoConsumer MsgInfoConsumer:{}", JSON_UTILS.objectToJson(msgInfoConsumer));
		String sql = "INSERT INTO MSG_INFO_CONSUMER (CONSUMER_ID, KEY, QUEUE_CODE, IS_CACHE, IS_PERSISTENCE, CREATE_TIME) VALUES (?, ?, ?, ?, ?, ?)";
		Long id = GidClientUtils.getInstance().getGidValue("MSG_INFO_CONSUMER_SEQ");
		if(null == id){
			throw new NullPointerException("id can not be null!!!");
		}
		
		String key = msgInfoConsumer.getKey();
		String queueCode = msgInfoConsumer.getQueueCode();
		Integer isCache = msgInfoConsumer.getIsCache();
		Integer isPersistence = msgInfoConsumer.getIsPersistence();
		Date createTime = msgInfoConsumer.getCreateTime();
		
		return jdbcTemplate.update(sql, id, key, queueCode, isCache, isPersistence, createTime);
	}
	
	
}
