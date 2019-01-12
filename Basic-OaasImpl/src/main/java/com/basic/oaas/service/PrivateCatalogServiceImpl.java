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
import com.basic.oaas.dao.PrivateCatalogMapper;
import com.basic.oaas.define.GidCodes;
import com.basic.oaas.model.PrivateCatalog;

/**
 *
 * @date 2017年8月8日 上午9:57:54
 * @author Kevin
 * @Description: 权限目录接口实现类
 * 
 */
@Service("privateCatalogService")
public class PrivateCatalogServiceImpl implements PrivateCatalogService{

	private Logger logger = LoggerFactory.getLogger(PrivateCatalogServiceImpl.class);

	private static final JsonUtils JSON_UTILS = JsonUtils.getInstance();
	
	@Autowired
	private PrivateCatalogMapper privateCatalogMapper;
	/* (non-Javadoc)
	 * @see com.basic.oaas.service.PrivateCatalogService#removeByPrimaryKey(java.lang.Long)
	 */
	@Override
	public int removeByPrimaryKey(Long catalogId) {
		if (logger.isDebugEnabled()) {
			logger.debug("removeByPrimaryKey catalogId:{}", catalogId);
		}
		
		return privateCatalogMapper.deleteByPrimaryKey(catalogId);
	}

	/* (non-Javadoc)
	 * @see com.basic.oaas.service.PrivateCatalogService#createSelective(com.basic.oaas.model.PrivateCatalog)
	 */
	@Override
	public int createSelective(PrivateCatalog record) {
		if (logger.isDebugEnabled()) {
			logger.debug("createSelective record:{}", JSON_UTILS.objectToJson(record));
		}
		
		Long gid = GidClientUtils.getInstance().getGidValue(GidCodes.OAAS_PRIV_CATALOG_SEQ);
		record.setCatalogId(gid);
		record.setCreateTime(DateUtils.now());
		//获取父权限目录
		PrivateCatalog parentCatalog = privateCatalogMapper.selectByPrimaryKey(record.getParentCatalogId());
		
		if(parentCatalog == null){
			record.setIdPath(gid.toString());
			
		}else{
			record.setIdPath(parentCatalog.getIdPath()+","+gid.toString());
		}
		
		return privateCatalogMapper.insertSelective(record);
	}

	/* (non-Javadoc)
	 * @see com.basic.oaas.service.PrivateCatalogService#qryByPrimaryKey(java.lang.Long)
	 */
	@Override
	public PrivateCatalog qryByPrimaryKey(Long catalogId) {
		
		if (logger.isDebugEnabled()) {
			logger.debug("qryByPrimaryKey catalogId:{}", catalogId);
		}
		
		return privateCatalogMapper.selectByPrimaryKey(catalogId);
	}

	/* (non-Javadoc)
	 * @see com.basic.oaas.service.PrivateCatalogService#modifyByPrimaryKeySelective(com.basic.oaas.model.PrivateCatalog)
	 */
	@Override
	public int modifyByPrimaryKeySelective(PrivateCatalog record) {
		
		if (logger.isDebugEnabled()) {
			logger.debug("modifyByPrimaryKeySelective record:{}", JSON_UTILS.objectToJson(record));
		}
		record.setModifyTime(DateUtils.now());
		
		return privateCatalogMapper.updateByPrimaryKey(record);
	}


	/* (non-Javadoc)
	 * @see com.basic.oaas.service.PrivateCatalogService#createOrModify(com.basic.oaas.model.PrivateCatalog)
	 */
	@Override
	public void createOrModify(PrivateCatalog ibean) {
		if(logger.isDebugEnabled()){
			logger.debug("createOrModify PrivateCatalog:{}", JSON_UTILS.objectToJson(ibean));
		}
		
		if(ibean.getCatalogId() == null){//新增
			createSelective(ibean);
		}else{//修改
			modifyByPrimaryKeySelective(ibean);
		}
	}

	/* (non-Javadoc)
	 * @see com.basic.oaas.service.PrivateCatalogService#removeBatchBycatalogIds(java.lang.Long[])
	 */
	@Override
	public int removeBatchByCatalogIds(Long[] catalogIds) {
		if(logger.isDebugEnabled()){
			logger.debug("removeBatchBycatalogIds catalogIds:{}", JSON_UTILS.objectToJson(catalogIds));
		}
		//批量修改状态
		return privateCatalogMapper.updateBatchByCatalogIds(catalogIds);
	}

	/* (non-Javadoc)
	 * @see com.basic.oaas.service.PrivateCatalogService#qrySubPrivateCatalog(java.lang.Long)
	 */
	@Override
	public List<PrivateCatalog> qrySubPrivateCatalog(Long parentCatalogId) {
		if(logger.isDebugEnabled()){
			logger.debug("qrySubPrivateCatalog parentCatalogId:{}", parentCatalogId);
		}
		return privateCatalogMapper.selectSubByParentCatalogId(parentCatalogId);
	}

	/* (non-Javadoc)
	 * @see com.basic.oaas.service.PrivateCatalogService#qryExistSubPrivateCatalog(java.lang.Long)
	 */
	@Override
	public int qryExistSubPrivateCatalog(Long parentCatalogId) {
		if(logger.isDebugEnabled()){
			logger.debug("qryExistSubPrivateCatalog parentCatalogId:{}", parentCatalogId);
		}
		
		return privateCatalogMapper.selectExistSubPrivateCatalog(parentCatalogId);
	}

	/* (non-Javadoc)
	 * @see com.basic.oaas.service.PrivateCatalogService#ModifyAllStateById(java.lang.Long)
	 */
	@Override
	public int modifyAllStateById(Long catalogId) {
		
		if(logger.isDebugEnabled()){
			logger.debug("modifyAllStateById CatalogId:{}", catalogId);
		}
		
		return privateCatalogMapper.updateAllStateById(catalogId);
	}

}
