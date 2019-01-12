package com.basic.system.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.basic.framework.common.utils.datatype.JsonUtils;
import com.basic.framework.gid.utils.sequence.GidClientUtils;
import com.basic.system.bean.GidServerIBean;
import com.basic.system.dao.GidServerMapper;
import com.basic.system.model.GidServer;


/**
 * 
 *
 * @date 2017年7月3日 下午5:27:25
 * 
 * @Description: gid服务
 *
 */
@Service("gidServerService")
public class GidServerServiceImpl implements GidServerService {

	private Logger logger = LoggerFactory.getLogger(GidServerServiceImpl.class);
	
	private static final JsonUtils JSON_UTILS = JsonUtils.getInstance();

	@Autowired
	private GidServerMapper gidServerMapper;

	/* (non-Javadoc)
	 * @see com.basic.system.service.GidServerService#removeByPrimaryKey(java.lang.Long)
	 */
	@Override
	public int removeByPrimaryKey(String gidCode) {
		if (logger.isDebugEnabled()) {
			logger.debug("removeByPrimaryKey gidCode:{}", gidCode);
		}

		return gidServerMapper.deleteByPrimaryKey(gidCode);
	}

	/* (non-Javadoc)
	 * @see com.basic.system.service.GidServerService#createSelective(com.basic.system.model.GidServer)
	 */
	@Override
	public int createSelective(GidServer record) {
		if (logger.isDebugEnabled()) {
			logger.debug("createSelective GidServer:{}", JSON_UTILS.objectToJson(record));
		}

		return gidServerMapper.insertSelective(record);
	}

	/* (non-Javadoc)
	 * @see com.basic.system.service.GidServerService#qryByPrimaryKey(java.lang.Long)
	 */
	@Override
	public GidServer qryByPrimaryKey(String gidCode) {
		if (logger.isDebugEnabled()) {
			logger.debug("qryByPrimaryKey gidCode:{}", gidCode);
		}

		return gidServerMapper.selectByPrimaryKey(gidCode);
	}

	/* (non-Javadoc)
	 * @see com.basic.system.service.GidServerService#modifyByPrimaryKeySelective(com.basic.system.model.GidServer)
	 */
	@Override
	public int modifyByPrimaryKeySelective(GidServer record) {
		if (logger.isDebugEnabled()) {
			logger.debug("modifyByPrimaryKeySelective GidServer:{}", JSON_UTILS.objectToJson(record));
		}
		
		return gidServerMapper.updateByPrimaryKeySelective(record);
	}

	/* (non-Javadoc)
	 * @see com.basic.system.service.GidServerService#qryGidServerList(com.basic.system.bean.GidServerIBean)
	 */
	@Override
	public List<GidServer> qryGidServerList(GidServerIBean ibean) {
		if (logger.isDebugEnabled()) {
			logger.debug("qryGidServerList GidServerIBean:{}", JSON_UTILS.objectToJson(ibean));
		}
		
		List<GidServer> list = gidServerMapper.selectGidServerList(ibean);
		return list;
	}

	/* (non-Javadoc)
	 * @see com.basic.system.service.GidServerService#qryGidServerListCount(com.basic.system.bean.GidServerIBean)
	 */
	@Override
	public long qryGidServerListCount(GidServerIBean ibean) {
		if (logger.isDebugEnabled()) {
			logger.debug("qryGidServerListCount GidServerIBean:{}", JSON_UTILS.objectToJson(ibean));
		}
		
		long count = gidServerMapper.selectGidServerListCount(ibean);
		return count;
	}

	/* (non-Javadoc)
	 * @see com.basic.system.service.GidServerService#createOrModify(com.basic.system.model.GidServer)
	 */
	@Override
	public void createOrModify(GidServer ibean) {
		if (logger.isDebugEnabled()) {
			logger.debug("createOrModify GidServer:{}", JSON_UTILS.objectToJson(ibean));
		}
		
		if("CREATE".equals(ibean.getPageDateType())){//新增
			createSelective(ibean);
		}else{//修改
			modifyByPrimaryKeySelective(ibean);
		}
		
	}

	/* (non-Javadoc)
	 * @see com.basic.system.service.GidServerService#getGidValue(java.lang.String)
	 */
	@Override
	public Long getGidValue(String gidCode) {
		if(logger.isDebugEnabled()){
			logger.debug("getGidValue gidCode:{}", gidCode);
		}
		
		Long gidValue = null;
		
		if(StringUtils.isNotBlank(gidCode)){
			gidValue = GidClientUtils.getInstance().getGidValue(gidCode);
		}
		
		return gidValue;
	}
	
}
