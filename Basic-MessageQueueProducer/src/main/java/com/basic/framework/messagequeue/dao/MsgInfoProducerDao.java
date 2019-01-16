package com.basic.framework.messagequeue.dao;

import java.util.Date;

import com.basic.framework.common.utils.IdUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.basic.framework.common.utils.datatype.JsonUtils;
import com.basic.framework.gid.utils.sequence.GidClientUtils;
import com.basic.framework.messagequeue.modal.MsgInfoProducer;

/**
 * 
 *
 * @date 2017年7月12日 下午12:13:19
 * 
 * @Description: 消息生产者，数据库操作类
 *
 */
@Repository
public class MsgInfoProducerDao {
	
	private Logger logger = LoggerFactory.getLogger(MsgInfoProducerDao.class);
	private static final JsonUtils JSON_UTILS = JsonUtils.getInstance();
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate){
		this.jdbcTemplate = jdbcTemplate;
	}
	

	/**
	 * 
	 * @date 2017年7月12日 下午12:28:08
	 * 
	 * @Description: 新增一条记录
	 * @param msgInfoProducer
	 * @return
	 *
	 */
	public int createMsgInfoProducer(MsgInfoProducer msgInfoProducer){
		logger.debug("createMsgInfoProducer MsgInfoProducer:{}", JSON_UTILS.objectToJson(msgInfoProducer));
		String sql = "INSERT INTO PUB_MSG_INFO_PRODUCER (PRODUCER_ID, `KEY`, MESSAGE, QUEUE_CODE, IS_CACHE, IS_PERSISTENCE, PERSISTENCE_TYPE, CREATE_TIME) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		Long id=IdUtil.next();
		if(null == id){
			throw new NullPointerException("id can not be null!!!");
		}
		
		String key = msgInfoProducer.getKey();
		String message = msgInfoProducer.getMessage();
		String queueCode = msgInfoProducer.getQueueCode();
		Integer isCache = msgInfoProducer.getIsCache();
		Integer isPersistence = msgInfoProducer.getIsPersistence();
		String persistenceType = msgInfoProducer.getPersistenceType();
		Date createTime = msgInfoProducer.getCreateTime();
		
		return jdbcTemplate.update(sql, id, key, message, queueCode, isCache, isPersistence, persistenceType, createTime);
	}
	
}
