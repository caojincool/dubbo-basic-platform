package com.basic.log.service;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.basic.framework.common.utils.datatype.DateUtils;
import com.basic.framework.common.utils.datatype.JsonUtils;
import com.basic.framework.gid.utils.sequence.GidClientUtils;
import com.basic.log.bean.SystemLogIbean;
import com.basic.log.dao.SystemLogMapper;
import com.basic.log.define.GidCodes;
import com.basic.log.model.SystemLog;

/**
 * 
 *
 * @date 2017年10月13日 下午2:16:14
 * 
 * @Description: 系统日志
 *
 */
@Service("systemLogService")
public class SystemLogServiceImpl implements SystemLogService{

	private Logger logger = LoggerFactory.getLogger(SystemLogServiceImpl.class);

	private static final JsonUtils JSON_UTILS = JsonUtils.getInstance();
	
	@Autowired
	private SystemLogMapper systemLogMapper;

	/* (non-Javadoc)
	 * @see com.basic.system.service.SystemLogService#removeByPrimaryKey(java.lang.Long)
	 */
	@Override
	public int removeByPrimaryKey(Long logId) {
		if (logger.isDebugEnabled()) {
			logger.debug("removeByPrimaryKey logId:{}", logId);
		}
		return systemLogMapper.deleteByPrimaryKey(logId);
	}

	/* (non-Javadoc)
	 * @see com.basic.system.service.SystemLogService#createSelective(com.basic.system.model.SystemLog)
	 */
	@Override
	public int createSelective(SystemLog record) {
		if (logger.isDebugEnabled()) {
			logger.debug("createSelective SystemLog:{}", JSON_UTILS.objectToJson(record));
		}

		Long gid = GidClientUtils.getInstance().getGidValue(GidCodes.PUB_SYSTEM_LOG_SEQ);
		Date now = DateUtils.now();
		record.setLogId(gid);
		if(record.getCreateTime() == null){
			record.setCreateTime(now);
		}
		
		return systemLogMapper.insertSelective(record);
	}

	/* (non-Javadoc)
	 * @see com.basic.system.service.SystemLogService#qryByPrimaryKey(java.lang.Long)
	 */
	@Override
	public SystemLog qryByPrimaryKey(Long logId) {
		if (logger.isDebugEnabled()) {
			logger.debug("qryByPrimaryKey logId:{}", logId);
		}

		return systemLogMapper.selectByPrimaryKey(logId);
	}

	/* (non-Javadoc)
	 * @see com.basic.system.service.SystemLogService#modifyByPrimaryKeySelective(com.basic.system.model.SystemLog)
	 */
	@Override
	public int modifyByPrimaryKeySelective(SystemLog record) {
		if (logger.isDebugEnabled()) {
			logger.debug("modifyByPrimaryKeySelective SystemLog:{}", JSON_UTILS.objectToJson(record));
		}
		
		return systemLogMapper.updateByPrimaryKeySelective(record);
	}

	/* (non-Javadoc)
	 * @see com.basic.system.service.SystemLogService#qrySystemLogList(com.basic.system.bean.SystemLogIbean)
	 */
	@Override
	public List<SystemLog> qrySystemLogList(SystemLogIbean ibean) {
		if (logger.isDebugEnabled()) {
			logger.debug("qrySystemLogList SystemLogIbean:{}", JSON_UTILS.objectToJson(ibean));
		}
		
		List<SystemLog> list = systemLogMapper.selectSystemLogList(ibean);
		return list;
	}

	/* (non-Javadoc)
	 * @see com.basic.system.service.SystemLogService#qrySystemLogListCount(com.basic.system.bean.SystemLogIbean)
	 */
	@Override
	public long qrySystemLogListCount(SystemLogIbean ibean) {
		if (logger.isDebugEnabled()) {
			logger.debug("qrySystemLogListCount SystemLogIbean:{}", JSON_UTILS.objectToJson(ibean));
		}
		
		long count = systemLogMapper.selectSystemLogListCount(ibean);
		return count;
	}

}
