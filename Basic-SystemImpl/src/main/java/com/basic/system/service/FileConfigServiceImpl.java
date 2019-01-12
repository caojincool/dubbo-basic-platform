package com.basic.system.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.basic.framework.common.utils.datatype.JsonUtils;
import com.basic.framework.gid.utils.sequence.GidClientUtils;
import com.basic.framework.web.model.VerifyUniqueCode;
import com.basic.system.dao.FileConfigMapper;
import com.basic.system.define.GidCodes;
import com.basic.system.model.FileConfig;


/**
 * 
 *
 * @date 2017年7月3日 下午5:27:25
 * 
 * @Description: 附件配置
 *
 */
@Service("fileConfigService")
public class FileConfigServiceImpl implements FileConfigService {

	private Logger logger = LoggerFactory.getLogger(FileConfigServiceImpl.class);
	
	private static final JsonUtils JSON_UTILS = JsonUtils.getInstance();

	@Autowired
	private FileConfigMapper fileConfigMapper;

	/* (non-Javadoc)
	 * @see com.basic.system.service.FileConfigService#removeByPrimaryKey(java.lang.Long)
	 */
	@Override
	public int removeByPrimaryKey(Long fileConfigId) {
		if (logger.isDebugEnabled()) {
			logger.debug("removeByPrimaryKey fileConfigId:{}", fileConfigId);
		}

		return fileConfigMapper.deleteByPrimaryKey(fileConfigId);
	}

	/* (non-Javadoc)
	 * @see com.basic.system.service.FileConfigService#createSelective(com.basic.system.model.FileConfig)
	 */
	@Override
	public int createSelective(FileConfig record) {
		if (logger.isDebugEnabled()) {
			logger.debug("createSelective FileConfig:{}", JSON_UTILS.objectToJson(record));
		}

		Long gid = GidClientUtils.getInstance().getGidValue(GidCodes.FILE_CONFIG_SEQ);
		record.setFileConfigId(gid);
		
		return fileConfigMapper.insertSelective(record);
	}

	/* (non-Javadoc)
	 * @see com.basic.system.service.FileConfigService#qryByPrimaryKey(java.lang.Long)
	 */
	@Override
	public FileConfig qryByPrimaryKey(Long fileConfigId) {
		if (logger.isDebugEnabled()) {
			logger.debug("qryByPrimaryKey fileConfigId:{}", fileConfigId);
		}

		return fileConfigMapper.selectByPrimaryKey(fileConfigId);
	}

	/* (non-Javadoc)
	 * @see com.basic.system.service.FileConfigService#modifyByPrimaryKeySelective(com.basic.system.model.FileConfig)
	 */
	@Override
	public int modifyByPrimaryKeySelective(FileConfig record) {
		if (logger.isDebugEnabled()) {
			logger.debug("modifyByPrimaryKeySelective FileConfig:{}", JSON_UTILS.objectToJson(record));
		}
		
		return fileConfigMapper.updateByPrimaryKeySelective(record);
	}

	/* (non-Javadoc)
	 * @see com.basic.system.service.FileConfigService#qryByModuleCode(java.lang.String)
	 */
	@Override
	public FileConfig qryByModuleCode(String moduleCode) {
		if (logger.isDebugEnabled()) {
			logger.debug("qryByModuleCode moduleCode:{}", moduleCode);
		}
		return fileConfigMapper.selectByModuleCode(moduleCode);
	}

	/* (non-Javadoc)
	 * @see com.basic.system.service.FileConfigService#qryCodeCount(com.basic.framework.web.model.VerifyUniqueCode)
	 */
	@Override
	public int qryCodeCount(VerifyUniqueCode ibean) {
		if (logger.isDebugEnabled()) {
			logger.debug("qryCodeCount VerifyUniqueCode:{}", JSON_UTILS.objectToJson(ibean));
		}
		return fileConfigMapper.selectCodeCount(ibean);
	}
	
}
