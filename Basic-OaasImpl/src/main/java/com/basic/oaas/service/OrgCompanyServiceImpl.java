/**
 *
 * companyName
 * copyright 2015-2020
 * @date 2017年7月5日 上午10:49:45
 * @author Kevin
 * @Description: 员工实现类
 * 
 */
package com.basic.oaas.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.basic.framework.common.utils.datatype.JsonUtils;
import com.basic.framework.gid.utils.sequence.GidClientUtils;
import com.basic.oaas.dao.OrgCompanyMapper;
import com.basic.oaas.define.GidCodes;
import com.basic.oaas.model.OrgCompany;

/**
 *
 * @date 2017年7月5日 上午10:49:45
 * @author Kevin
 * @Description: OrgCompanyService
 * 
 */
@Service("orgCompanyService")
public class OrgCompanyServiceImpl implements OrgCompanyService {

	private Logger logger = LoggerFactory.getLogger(OrgCompanyServiceImpl.class);

	private static final JsonUtils JSON_UTILS = JsonUtils.getInstance();
	
	@Autowired
	private OrgCompanyMapper orgCompanyMapper;
	
	/* (non-Javadoc)
	 * @see com.basic.oaas.service.OrgCompanyService#removeByPrimaryKey(java.lang.Long)
	 */
	@Override
	public int removeByPrimaryKey(Long orgCompanyId) {
		if (logger.isDebugEnabled()) {
			logger.debug("removeByPrimaryKey orgCompanyId:{}", orgCompanyId);
		}
		return orgCompanyMapper.deleteByPrimaryKey(orgCompanyId);
	}

	/* (non-Javadoc)
	 * @see com.basic.oaas.service.OrgCompanyService#createSelective(com.basic.oaas.model.OrgCompany)
	 */
	@Override
	public int createSelective(OrgCompany record) {
		if (logger.isDebugEnabled()) {
			logger.debug("createSelective record:{}", JSON_UTILS.objectToJson(record));
		}
		
		Long gid = GidClientUtils.getInstance().getGidValue(GidCodes.OAAS_STAFF_SEQ);
		record.setDetailId(gid);
		
		
		return orgCompanyMapper.insertSelective(record);
	}

	/* (non-Javadoc)
	 * @see com.basic.oaas.service.OrgCompanyService#qryByPrimaryKey(java.lang.Long)
	 */
	@Override
	public OrgCompany qryByPrimaryKey(Long orgCompanyId) {
		if (logger.isDebugEnabled()) {
			logger.debug("qryByPrimaryKey orgCompanyId:{}", orgCompanyId);
		}
		return orgCompanyMapper.selectByPrimaryKey(orgCompanyId);
	}

	/* (non-Javadoc)
	 * @see com.basic.oaas.service.OrgCompanyService#modifyByPrimaryKeySelective(com.basic.oaas.model.OrgCompany)
	 */
	@Override
	public int modifyByPrimaryKeySelective(OrgCompany record) {
		if (logger.isDebugEnabled()) {
			logger.debug("modifyByPrimaryKeySelective record:{}", JSON_UTILS.objectToJson(record));
		}
		
		
		return orgCompanyMapper.updateByPrimaryKeySelective(record);

	}

	/* (non-Javadoc)
	 * @see com.basic.oaas.service.OrgCompanyService#deleteByOrgId(java.lang.Long)
	 */
	@Override
	public int removeByOrgId(Long orgId) {
		
		if (logger.isDebugEnabled()) {
			logger.debug("deleteByOrgId orgId:{}", JSON_UTILS.objectToJson(orgId));
		}
		return orgCompanyMapper.deleteByOrgId(orgId);
	}


}
