package com.basic.order.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.basic.framework.common.utils.datatype.JsonUtils;
import com.basic.framework.gid.utils.sequence.GidClientUtils;
import com.basic.order.dao.WoButtonMapper;
import com.basic.order.define.GidCodes;
import com.basic.order.model.WoButton;

/**
 * 
 *
 * @date 2017年8月17日 上午11:49:24
 * 
 * @Description: 任务按钮
 *
 */
@Service("woButtonService")
public class WoButtonServiceImpl implements WoButtonService{

	private Logger logger = LoggerFactory.getLogger(WoButtonServiceImpl.class);

	private static final JsonUtils JSON_UTILS = JsonUtils.getInstance();
	
	@Autowired
	private WoButtonMapper woButtonMapper;

	/* (non-Javadoc)
	 * @see com.basic.order.service.WoButtonService#removeByPrimaryKey(java.lang.Long)
	 */
	@Override
	public int removeByPrimaryKey(Long woButtonId) {
		if (logger.isDebugEnabled()) {
			logger.debug("removeByPrimaryKey woButtonId:{}", woButtonId);
		}
		return woButtonMapper.deleteByPrimaryKey(woButtonId);
	}

	/* (non-Javadoc)
	 * @see com.basic.order.service.WoButtonService#createSelective(com.basic.order.model.WoButton)
	 */
	@Override
	public int createSelective(WoButton record) {
		if (logger.isDebugEnabled()) {
			logger.debug("createSelective WoButton:{}", JSON_UTILS.objectToJson(record));
		}

		Long gid = GidClientUtils.getInstance().getGidValue(GidCodes.PF_WO_BUTTON_SEQ);
		record.setWoButtonId(gid);
		
		return woButtonMapper.insertSelective(record);
	}

	/* (non-Javadoc)
	 * @see com.basic.order.service.WoButtonService#qryByPrimaryKey(java.lang.Long)
	 */
	@Override
	public WoButton qryByPrimaryKey(Long woButtonId) {
		if (logger.isDebugEnabled()) {
			logger.debug("qryByPrimaryKey woButtonId:{}", woButtonId);
		}

		return woButtonMapper.selectByPrimaryKey(woButtonId);
	}

	/* (non-Javadoc)
	 * @see com.basic.order.service.WoButtonService#modifyByPrimaryKeySelective(com.basic.order.model.WoButton)
	 */
	@Override
	public int modifyByPrimaryKeySelective(WoButton record) {
		if (logger.isDebugEnabled()) {
			logger.debug("modifyByPrimaryKeySelective WoButton:{}", JSON_UTILS.objectToJson(record));
		}
		
		return woButtonMapper.updateByPrimaryKeySelective(record);
	}

}
