/**
 *
 * companyName
 * copyright 2015-2020
 * @date 2017年8月8日 上午9:57:54
 * @author Kevin
 * @Description: 
 * 
 */
package com.basic.oaas.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.basic.framework.common.utils.datatype.DateUtils;
import com.basic.framework.common.utils.datatype.JsonUtils;
import com.basic.framework.gid.utils.sequence.GidClientUtils;
import com.basic.oaas.dao.PrivateFuncCatalogMapper;
import com.basic.oaas.define.GidCodes;
import com.basic.oaas.model.PrivateFuncCatalog;

/**
 *
 * @date 2017年8月8日 上午9:57:54
 * @author Kevin
 * @Description: 功能按钮目录接口实现类
 * 
 */
@Service("privateFuncCatalogService")
public class PrivateFuncCatalogServiceImpl implements PrivateFuncCatalogService{

	private Logger logger = LoggerFactory.getLogger(PrivateFuncCatalogServiceImpl.class);

	private static final JsonUtils JSON_UTILS = JsonUtils.getInstance();
	
	@Autowired
	private PrivateFuncCatalogMapper privateFuncCatalogMapper;
	/* (non-Javadoc)
	 * @see com.basic.oaas.service.PrivateFuncCatalogService#removeByPrimaryKey(java.lang.Long)
	 */
	@Override
	public int removeByPrimaryKey(Long catalogId) {
		if (logger.isDebugEnabled()) {
			logger.debug("removeByPrimaryKey catalogId:{}", catalogId);
		}
		
		return privateFuncCatalogMapper.deleteByPrimaryKey(catalogId);
	}

	/* (non-Javadoc)
	 * @see com.basic.oaas.service.PrivateFuncCatalogService#createSelective(com.basic.oaas.model.PrivateFuncCatalog)
	 */
	@Override
	public int createSelective(PrivateFuncCatalog record) {
		if (logger.isDebugEnabled()) {
			logger.debug("createSelective record:{}", JSON_UTILS.objectToJson(record));
		}
		
		Long gid = GidClientUtils.getInstance().getGidValue(GidCodes.OAAS_PRIV_FUNC_CATALOG_SEQ);
		record.setCatalogId(gid);
		record.setCreateTime(DateUtils.now());
		//获取父权限目录
		PrivateFuncCatalog parentCatalog = privateFuncCatalogMapper.selectByPrimaryKey(record.getParentCatalogId());
		
		if(parentCatalog == null){
			record.setIdPath(gid.toString());
			
		}else{
			record.setIdPath(parentCatalog.getIdPath()+","+gid.toString());
		}
		
		return privateFuncCatalogMapper.insertSelective(record);
	}

	/* (non-Javadoc)
	 * @see com.basic.oaas.service.PrivateFuncCatalogService#qryByPrimaryKey(java.lang.Long)
	 */
	@Override
	public PrivateFuncCatalog qryByPrimaryKey(Long catalogId) {
		
		if (logger.isDebugEnabled()) {
			logger.debug("qryByPrimaryKey catalogId:{}", catalogId);
		}
		
		return privateFuncCatalogMapper.selectByPrimaryKey(catalogId);
	}

	/* (non-Javadoc)
	 * @see com.basic.oaas.service.PrivateFuncCatalogService#modifyByPrimaryKeySelective(com.basic.oaas.model.PrivateFuncCatalog)
	 */
	@Override
	public int modifyByPrimaryKeySelective(PrivateFuncCatalog record) {
		
		if (logger.isDebugEnabled()) {
			logger.debug("modifyByPrimaryKeySelective record:{}", JSON_UTILS.objectToJson(record));
		}
		record.setModifyTime(DateUtils.now());
		
		return privateFuncCatalogMapper.updateByPrimaryKeySelective(record);
	}


	/* (non-Javadoc)
	 * @see com.basic.oaas.service.PrivateFuncCatalogService#createOrModify(com.basic.oaas.model.PrivateFuncCatalog)
	 */
	@Override
	public void createOrModify(PrivateFuncCatalog ibean) {
		if(logger.isDebugEnabled()){
			logger.debug("createOrModify PrivateFuncCatalog:{}", JSON_UTILS.objectToJson(ibean));
		}
		
		if(ibean.getCatalogId() == null){//新增
			createSelective(ibean);
		}else{//修改
			modifyByPrimaryKeySelective(ibean);
		}
	}


	/* (non-Javadoc)
	 * @see com.basic.oaas.service.PrivateFuncCatalogService#qrySubPrivateFuncCatalog(java.lang.Long)
	 */
	@Override
	public List<PrivateFuncCatalog> qrySubFuncCatalog(Long parentCatalogId) {
		if(logger.isDebugEnabled()){
			logger.debug("qrySubPrivateFuncCatalog parentCatalogId:{}", parentCatalogId);
		}
		return privateFuncCatalogMapper.selectSubByParentCatalogId(parentCatalogId);
	}

	/* (non-Javadoc)
	 * @see com.basic.oaas.service.PrivateFuncCatalogService#qryExistSubPrivateFuncCatalog(java.lang.Long)
	 */
	@Override
	public int qryExistSubFuncCatalog(Long parentCatalogId) {
		if(logger.isDebugEnabled()){
			logger.debug("qryExistSubPrivateFuncCatalog parentCatalogId:{}", parentCatalogId);
		}
		
		return privateFuncCatalogMapper.selectExistSubFuncCatalog(parentCatalogId);
	}

	/* (non-Javadoc)
	 * @see com.basic.oaas.service.PrivateFuncCatalogService#ModifyAllStateById(java.lang.Long)
	 */
	@Override
	public int modifyAllStateById(Long catalogId) {
		
		if(logger.isDebugEnabled()){
			logger.debug("modifyAllStateById CatalogId:{}", catalogId);
		}
		
		return privateFuncCatalogMapper.updateAllStateById(catalogId);
	}

}
