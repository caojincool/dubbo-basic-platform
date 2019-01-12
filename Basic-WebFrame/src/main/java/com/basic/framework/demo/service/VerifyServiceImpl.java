package com.basic.framework.demo.service;

import com.basic.framework.web.api.VerifyService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.basic.framework.common.utils.datatype.JsonUtils;
import com.basic.framework.web.model.VerifyUniqueCode;
import org.springframework.stereotype.Service;

/**
 * 
 *
 * @date 2017年8月22日 下午5:45:34
 * 
 * @Description: 用于验证
 *
 */

public class VerifyServiceImpl implements VerifyService {
    
	private Logger logger = LoggerFactory.getLogger(VerifyServiceImpl.class);

	private static final JsonUtils JSON_UTILS = JsonUtils.getInstance();
	
	/* (non-Javadoc)
	 * @see com.basic.framework.web.api.VerifyService#verifyUniqueCode(com.basic.framework.web.model.VerifyUniqueCode)
	 */
	@Override
	public int verifyUniqueCode(VerifyUniqueCode ibean) {
		if(logger.isDebugEnabled()){
			logger.debug("verifyUniqueCode VerifyUniqueCode:{}", JSON_UTILS.objectToJson(ibean));
		}
		
		int count = 0;
		if(StringUtils.isNoneBlank(ibean.getTableCode()) 
				&& StringUtils.isNoneBlank(ibean.getColCode()) 
				&& StringUtils.isNoneBlank(ibean.getColValue())){
//			count = 1;
			count = 0;
		}
		return count;
	}

}
