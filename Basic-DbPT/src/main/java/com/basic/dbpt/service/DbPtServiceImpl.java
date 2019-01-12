package com.basic.dbpt.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.basic.dbpt.dao.DbPtMapper;
import com.basic.dbpt.model.DbPt;
import com.basic.framework.common.utils.datatype.JsonUtils;

@Service("dbPtService")
public class DbPtServiceImpl implements DbPtService{

	private Logger logger = LoggerFactory.getLogger(DbPtServiceImpl.class);

	private static final JsonUtils JSON_UTILS = JsonUtils.getInstance();
	
	@Autowired
	private DbPtMapper dbPtMapper;

	/* (non-Javadoc)
	 * @see com.basic.dbpt.service.DbPtService#create(com.basic.dbpt.model.DbPt)
	 */
	@Override
	public int create(DbPt record) {
		if (logger.isDebugEnabled()) {
			logger.debug("create DbPt:{}", JSON_UTILS.objectToJson(record));
		}
		return dbPtMapper.insert(record);
	}

	/* (non-Javadoc)
	 * @see com.basic.dbpt.service.DbPtService#qryIsExistTable(com.basic.dbpt.model.DbPt)
	 */
	@Override
	public long qryIsExistTable(DbPt record) {
		if (logger.isDebugEnabled()) {
			logger.debug("qryIsExistTable DbPt:{}", JSON_UTILS.objectToJson(record));
		}
		return dbPtMapper.selectIsExistTable(record);
	}

	/* (non-Javadoc)
	 * @see com.basic.dbpt.service.DbPtService#removeTable(com.basic.dbpt.model.DbPt)
	 */
	@Override
	public void removeTable(DbPt record) {
		if (logger.isDebugEnabled()) {
			logger.debug("removeTable DbPt:{}", JSON_UTILS.objectToJson(record));
		}
		dbPtMapper.deleteTable(record);
	}

	/* (non-Javadoc)
	 * @see com.basic.dbpt.service.DbPtService#createTable(com.basic.dbpt.model.DbPt)
	 */
	@Override
	public void createTable(DbPt record) {
		if (logger.isDebugEnabled()) {
			logger.debug("createTable DbPt:{}", JSON_UTILS.objectToJson(record));
		}
		dbPtMapper.createTable(record);
	}


}
