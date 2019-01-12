package com.basic.framework.demo.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.basic.framework.common.utils.datatype.JsonUtils;
import com.basic.framework.demo.model.DemoOrg;
import org.springframework.stereotype.Service;

/**
 * 
 * @author lzj
 */

@Service("demoOrgService")
public class DemoOrgServiceImpl implements DemoOrgService{

	private Logger logger = LoggerFactory.getLogger(DemoOrgServiceImpl.class);
	private static final JsonUtils JSON_UTILS = JsonUtils.getInstance();
	
//	@Autowired
//	private DemoOrgMapper demoOrgMapper;
	
	/**
	 * 分步查询组织树
	 * @param nodeId
	 * @return
	 */
	@Override
	public List<DemoOrg> qryOrgsTreeStep(Long nodeId) throws Exception {
		if(logger.isDebugEnabled()){
			logger.debug("qryOrgsTreeStep nodeId:{}",nodeId);
		}
		//业务逻辑开始
//		List<DemoOrg> list = this.demoOrgMapper.selectOrgsTreeStep(nodeId);
		List<DemoOrg> list = null;
		//业务逻辑结束
		if(logger.isDebugEnabled()){
			logger.debug("qryOrgsTreeStep nodeId:{},list:{}",nodeId, JSON_UTILS.objectToJson(list));
		}
		
		return list;
	}

	/**
	 * 查询所有组织
	 * @param nodeid
	 * @return
	 */
	@Override
	public List<DemoOrg> qryAllOrgs() throws Exception {
//		return this.demoOrgMapper.selectAllOrgs();
		return null;
	}

	/* (non-Javadoc)
	 * @see com.basic.framework.demo.service.DemoOrgService#qryOaasOrgTree()
	 */
	@Override
	public List<DemoOrg> qryOaasOrgTree() {
//		return demoOrgMapper.selectOaasOrgTree();
		return null;
	}

	/* (non-Javadoc)
	 * @see com.basic.framework.demo.service.DemoOrgService#qryOaasOrgTreeStep(java.lang.Long)
	 */
	@Override
	public List<DemoOrg> qryOaasOrgTreeStep(Long id) {
		if(logger.isDebugEnabled()){
			logger.debug("qryOrgsTreeStep id:{}",id);
		}
		//业务逻辑开始
//		return this.demoOrgMapper.selectOaasOrgTreeStep(id);
		return null;
	}

}
