package com.basic.framework.demo.service;

import com.basic.framework.web.api.GidService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 *
 * @date 2017年6月28日 下午5:15:13
 * 
 * @Description: gid
 *
 */
//@Service("gidService")
public class GidServiceImpl implements GidService {
    
	private Logger logger = LoggerFactory.getLogger(GidServiceImpl.class);

//	private static final JsonUtils jsonUtils = JsonUtils.getInstance();

	/* (non-Javadoc)
	 * @see com.basic.framework.web.api.GidService#getGidValue(java.lang.String)
	 */
	@Override
	public Long getGidValue(String gidCode) {
		if(logger.isDebugEnabled()){
			logger.debug("getGidValue gidCode:{}", gidCode);
		}
		
		Long gidValue = null;
		
		if(StringUtils.isNotBlank(gidCode)){
			gidValue = 1L;
		}
		
		return gidValue;
	}

}
