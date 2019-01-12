package com.basic.system.service;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.basic.framework.common.utils.datatype.DateUtils;
import com.basic.framework.common.utils.datatype.JsonUtils;
import com.basic.framework.gid.utils.sequence.GidClientUtils;
import com.basic.system.dao.FileInfoMapper;
import com.basic.system.define.GidCodes;
import com.basic.system.model.FileInfo;


/**
 * 
 *
 * @date 2017年7月3日 下午5:27:25
 * 
 * @Description: 附件
 *
 */
@Service("fileInfoService")
public class FileInfoServiceImpl implements FileInfoService {

	private Logger logger = LoggerFactory.getLogger(FileInfoServiceImpl.class);
	
	private static final JsonUtils JSON_UTILS = JsonUtils.getInstance();

	@Autowired
	private FileInfoMapper fileInfoMapper;

	/* (non-Javadoc)
	 * @see com.basic.system.service.FileInfoService#removeByPrimaryKey(java.lang.Long)
	 */
	@Override
	public int removeByPrimaryKey(Long fileInfoId) {
		if (logger.isDebugEnabled()) {
			logger.debug("removeByPrimaryKey fileInfoId:{}", fileInfoId);
		}

		return fileInfoMapper.deleteByPrimaryKey(fileInfoId);
	}

	/* (non-Javadoc)
	 * @see com.basic.system.service.FileInfoService#createSelective(com.basic.system.model.FileInfo)
	 */
	@Override
	public Long createSelective(FileInfo record) {
		if (logger.isDebugEnabled()) {
			logger.debug("createSelective FileInfo:{}", JSON_UTILS.objectToJson(record));
		}

		Long gid = GidClientUtils.getInstance().getGidValue(GidCodes.FILE_INFO_SEQ);
		Date now = DateUtils.now();
		record.setFileInfoId(gid);
		record.setCreateTime(now);
		
		fileInfoMapper.insertSelective(record);
		
		return gid;
	}

	/* (non-Javadoc)
	 * @see com.basic.system.service.FileInfoService#qryByPrimaryKey(java.lang.Long)
	 */
	@Override
	public FileInfo qryByPrimaryKey(Long fileInfoId) {
		if (logger.isDebugEnabled()) {
			logger.debug("qryByPrimaryKey fileInfoId:{}", fileInfoId);
		}

		return fileInfoMapper.selectByPrimaryKey(fileInfoId);
	}

	/* (non-Javadoc)
	 * @see com.basic.system.service.FileInfoService#modifyByPrimaryKeySelective(com.basic.system.model.FileInfo)
	 */
	@Override
	public int modifyByPrimaryKeySelective(FileInfo record) {
		if (logger.isDebugEnabled()) {
			logger.debug("modifyByPrimaryKeySelective FileInfo:{}", JSON_UTILS.objectToJson(record));
		}
		
		return fileInfoMapper.updateByPrimaryKeySelective(record);
	}
	
}
