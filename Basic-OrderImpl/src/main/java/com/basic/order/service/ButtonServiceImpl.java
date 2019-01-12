package com.basic.order.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.basic.framework.common.utils.datatype.JsonUtils;
import com.basic.framework.gid.utils.sequence.GidClientUtils;
import com.basic.order.bean.ButtonIbean;
import com.basic.order.dao.ButtonMapper;
import com.basic.order.define.GidCodes;
import com.basic.order.model.Button;

/**
 * 
 *
 * @date 2017年8月17日 上午11:49:24
 * 
 * @Description: 按钮
 *
 */
@Service("buttonService")
public class ButtonServiceImpl implements ButtonService{

	private Logger logger = LoggerFactory.getLogger(ButtonServiceImpl.class);

	private static final JsonUtils JSON_UTILS = JsonUtils.getInstance();
	
	@Autowired
	private ButtonMapper buttonMapper;

	/* (non-Javadoc)
	 * @see com.basic.order.service.ButtonService#removeByPrimaryKey(java.lang.Long)
	 */
	@Override
	public int removeByPrimaryKey(Long buttonId) {
		if (logger.isDebugEnabled()) {
			logger.debug("removeByPrimaryKey buttonId:{}", buttonId);
		}
		return buttonMapper.deleteByPrimaryKey(buttonId);
	}

	/* (non-Javadoc)
	 * @see com.basic.order.service.ButtonService#createSelective(com.basic.order.model.Button)
	 */
	@Override
	public int createSelective(Button record) {
		if (logger.isDebugEnabled()) {
			logger.debug("createSelective Button:{}", JSON_UTILS.objectToJson(record));
		}

		Long gid = GidClientUtils.getInstance().getGidValue(GidCodes.PF_BUTTON_SEQ);
		record.setButtonId(gid);
		
		return buttonMapper.insertSelective(record);
	}

	/* (non-Javadoc)
	 * @see com.basic.order.service.ButtonService#qryByPrimaryKey(java.lang.Long)
	 */
	@Override
	public Button qryByPrimaryKey(Long buttonId) {
		if (logger.isDebugEnabled()) {
			logger.debug("qryByPrimaryKey buttonId:{}", buttonId);
		}

		return buttonMapper.selectByPrimaryKey(buttonId);
	}

	/* (non-Javadoc)
	 * @see com.basic.order.service.ButtonService#modifyByPrimaryKeySelective(com.basic.order.model.Button)
	 */
	@Override
	public int modifyByPrimaryKeySelective(Button record) {
		if (logger.isDebugEnabled()) {
			logger.debug("modifyByPrimaryKeySelective Button:{}", JSON_UTILS.objectToJson(record));
		}
		
		return buttonMapper.updateByPrimaryKeySelective(record);
	}

	/* (non-Javadoc)
	 * @see com.basic.order.service.ButtonService#qryOrderButtonList(com.basic.order.bean.ButtonIbean)
	 */
	@Override
	public List<Button> qryOrderButtonList(ButtonIbean ibean) {
		if (logger.isDebugEnabled()) {
			logger.debug("qryOrderButtonList ButtonIbean:{}", JSON_UTILS.objectToJson(ibean));
		}
		
		List<Button> list = null;
		if(StringUtils.isNoneBlank(ibean.getOrderType())){
			list = buttonMapper.selectOrderButtonList(ibean);
		}
		return list;
	}

	/* (non-Javadoc)
	 * @see com.basic.order.service.ButtonService#qryWoButtonList(com.basic.order.bean.ButtonIbean)
	 */
	@Override
	public List<Button> qryWoButtonList(ButtonIbean ibean) {
		if (logger.isDebugEnabled()) {
			logger.debug("qryWoButtonList ButtonIbean:{}", JSON_UTILS.objectToJson(ibean));
		}
		
		List<Button> list = null;
		if(ibean.getTacheId() != null){
			list = buttonMapper.selectWoButtonList(ibean);
		}
		return list;
	}


}
