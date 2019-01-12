package com.basic.log.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.basic.framework.common.utils.datatype.JsonUtils;
import com.basic.framework.gid.utils.sequence.GidClientUtils;
import com.basic.log.bean.SystemConfigIbean;
import com.basic.log.dao.SystemConfigMapper;
import com.basic.log.define.GidCodes;
import com.basic.log.model.SystemConfig;

/**
 * 
 *
 * @date 2017年10月13日 下午2:16:14
 * 
 * @Description: 系统日志配置
 *
 */
@Service("systemConfigService")
public class SystemConfigServiceImpl implements SystemConfigService{

	private Logger logger = LoggerFactory.getLogger(SystemConfigServiceImpl.class);

	private static final JsonUtils JSON_UTILS = JsonUtils.getInstance();
	
	@Autowired
	private SystemConfigMapper systemConfigMapper;

	/* (non-Javadoc)
	 * @see com.basic.system.service.SystemConfigService#removeByPrimaryKey(java.lang.Long)
	 */
	@Override
	public int removeByPrimaryKey(Long configId) {
		if (logger.isDebugEnabled()) {
			logger.debug("removeByPrimaryKey configId:{}", configId);
		}
		return systemConfigMapper.deleteByPrimaryKey(configId);
	}

	/* (non-Javadoc)
	 * @see com.basic.system.service.SystemConfigService#createSelective(com.basic.system.model.SystemConfig)
	 */
	@Override
	public int createSelective(SystemConfig record) {
		if (logger.isDebugEnabled()) {
			logger.debug("createSelective SystemConfig:{}", JSON_UTILS.objectToJson(record));
		}

		Long gid = GidClientUtils.getInstance().getGidValue(GidCodes.PUB_SYSTEM_CONFIG_SEQ);
		record.setConfigId(gid);
		
		return systemConfigMapper.insertSelective(record);
	}

	/* (non-Javadoc)
	 * @see com.basic.system.service.SystemConfigService#qryByPrimaryKey(java.lang.Long)
	 */
	@Override
	public SystemConfig qryByPrimaryKey(Long configId) {
		if (logger.isDebugEnabled()) {
			logger.debug("qryByPrimaryKey configId:{}", configId);
		}

		return systemConfigMapper.selectByPrimaryKey(configId);
	}

	/* (non-Javadoc)
	 * @see com.basic.system.service.SystemConfigService#modifyByPrimaryKeySelective(com.basic.system.model.SystemConfig)
	 */
	@Override
	public int modifyByPrimaryKeySelective(SystemConfig record) {
		if (logger.isDebugEnabled()) {
			logger.debug("modifyByPrimaryKeySelective SystemConfig:{}", JSON_UTILS.objectToJson(record));
		}
		
		return systemConfigMapper.updateByPrimaryKeySelective(record);
	}

	/* (non-Javadoc)
	 * @see com.basic.system.service.SystemConfigService#qrySystemConfigList(com.basic.system.bean.SystemConfigIbean)
	 */
	@Override
	public List<SystemConfig> qrySystemConfigList(SystemConfigIbean ibean) {
		if (logger.isDebugEnabled()) {
			logger.debug("qrySystemConfigList SystemConfigIbean:{}", JSON_UTILS.objectToJson(ibean));
		}
		
		List<SystemConfig> list = systemConfigMapper.selectSystemConfigList(ibean);
		return list;
	}

	/* (non-Javadoc)
	 * @see com.basic.system.service.SystemConfigService#qrySystemConfigListCount(com.basic.system.bean.SystemConfigIbean)
	 */
	@Override
	public long qrySystemConfigListCount(SystemConfigIbean ibean) {
		if (logger.isDebugEnabled()) {
			logger.debug("qrySystemConfigListCount SystemConfigIbean:{}", JSON_UTILS.objectToJson(ibean));
		}
		
		long count = systemConfigMapper.selectSystemConfigListCount(ibean);
		return count;
	}

	/* (non-Javadoc)
	 * @see com.basic.system.service.SystemConfigService#createOrModify(com.basic.system.model.SystemConfig)
	 */
	@Override
	public void createOrModify(SystemConfig ibean) {
		if(logger.isDebugEnabled()){
			logger.debug("createOrModify SystemConfig:{}", JSON_UTILS.objectToJson(ibean));
		}
		
		if(ibean.getConfigId() == null){//新增
			createSelective(ibean);
		}else{//修改
			modifyByPrimaryKeySelective(ibean);
		}
	}

	/* (non-Javadoc)
	 * @see com.basic.log.service.SystemConfigService#removeBatchByConfigIds(java.lang.Long[])
	 */
	@Override
	public int removeBatchByConfigIds(Long[] configIds) {
		if(logger.isDebugEnabled()){
			logger.debug("removeBatchByConfigIds configIds:{}", JSON_UTILS.objectToJson(configIds));
		}
		
		int count = 0;
		if(configIds != null && configIds.length > 0){
			count = systemConfigMapper.deleteBatchByConfigIds(configIds);
		}
		return count;
	}

	/* (non-Javadoc)
	 * @see com.basic.log.service.SystemConfigService#qryByRequestUrl(java.lang.String)
	 */
	@Override
	public SystemConfig qryByRequestUrl(String requestUrl) {
		if(logger.isDebugEnabled()){
			logger.debug("qryByRequestUrl requestUrl:{}", requestUrl);
		}
		
		SystemConfig systemConfig = null;
		if(StringUtils.isNotBlank(requestUrl)){
			systemConfig = systemConfigMapper.selectByRequestUrl(requestUrl);
		}
		return systemConfig;
	}

}
