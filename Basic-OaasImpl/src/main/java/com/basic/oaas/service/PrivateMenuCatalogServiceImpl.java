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
import com.basic.oaas.dao.PrivateMenuCatalogMapper;
import com.basic.oaas.define.GidCodes;
import com.basic.oaas.model.PrivateMenuCatalog;

/**
 *
 * @date 2017年8月8日 上午9:57:54
 * @author Kevin
 * @Description: 菜单目录接口实现类
 * 
 */
@Service("privateMenuCatalogService")
public class PrivateMenuCatalogServiceImpl implements PrivateMenuCatalogService{

	private Logger logger = LoggerFactory.getLogger(PrivateMenuCatalogServiceImpl.class);

	private static final JsonUtils JSON_UTILS = JsonUtils.getInstance();
	
	@Autowired
	private PrivateMenuCatalogMapper privateMenuCatalogMapper;
	/* (non-Javadoc)
	 * @see com.basic.oaas.service.PrivateMenuCatalogService#removeByPrimaryKey(java.lang.Long)
	 */
	@Override
	public int removeByPrimaryKey(Long catalogId) {
		if (logger.isDebugEnabled()) {
			logger.debug("removeByPrimaryKey catalogId:{}", catalogId);
		}
		
		return privateMenuCatalogMapper.deleteByPrimaryKey(catalogId);
	}

	/* (non-Javadoc)
	 * @see com.basic.oaas.service.PrivateMenuCatalogService#createSelective(com.basic.oaas.model.PrivateMenuCatalog)
	 */
	@Override
	public int createSelective(PrivateMenuCatalog record) {
		if (logger.isDebugEnabled()) {
			logger.debug("createSelective record:{}", JSON_UTILS.objectToJson(record));
		}
		
		Long gid = GidClientUtils.getInstance().getGidValue(GidCodes.OAAS_PRIV_MENU_CATALOG_SEQ);
		record.setCatalogId(gid);
		record.setCreateTime(DateUtils.now());
		//获取父权限目录
		PrivateMenuCatalog parentCatalog = privateMenuCatalogMapper.selectByPrimaryKey(record.getParentCatalogId());
		
		if(parentCatalog == null){
			record.setIdPath(gid.toString());
			
		}else{
			record.setIdPath(parentCatalog.getIdPath()+","+gid.toString());
		}
		
		return privateMenuCatalogMapper.insertSelective(record);
	}

	/* (non-Javadoc)
	 * @see com.basic.oaas.service.PrivateMenuCatalogService#qryByPrimaryKey(java.lang.Long)
	 */
	@Override
	public PrivateMenuCatalog qryByPrimaryKey(Long catalogId) {
		
		if (logger.isDebugEnabled()) {
			logger.debug("qryByPrimaryKey catalogId:{}", catalogId);
		}
		
		return privateMenuCatalogMapper.selectByPrimaryKey(catalogId);
	}

	/* (non-Javadoc)
	 * @see com.basic.oaas.service.PrivateMenuCatalogService#modifyByPrimaryKeySelective(com.basic.oaas.model.PrivateMenuCatalog)
	 */
	@Override
	public int modifyByPrimaryKeySelective(PrivateMenuCatalog record) {
		
		if (logger.isDebugEnabled()) {
			logger.debug("modifyByPrimaryKeySelective record:{}", JSON_UTILS.objectToJson(record));
		}
		record.setModifyTime(DateUtils.now());
		
		return privateMenuCatalogMapper.updateByPrimaryKeySelective(record);
	}


	/* (non-Javadoc)
	 * @see com.basic.oaas.service.PrivateMenuCatalogService#createOrModify(com.basic.oaas.model.PrivateMenuCatalog)
	 */
	@Override
	public void createOrModify(PrivateMenuCatalog ibean) {
		if(logger.isDebugEnabled()){
			logger.debug("createOrModify PrivateMenuCatalog:{}", JSON_UTILS.objectToJson(ibean));
		}
		
		if(ibean.getCatalogId() == null){//新增
			createSelective(ibean);
		}else{//修改
			modifyByPrimaryKeySelective(ibean);
		}
	}


	/* (non-Javadoc)
	 * @see com.basic.oaas.service.PrivateMenuCatalogService#qrySubPrivateMenuCatalog(java.lang.Long)
	 */
	@Override
	public List<PrivateMenuCatalog> qrySubMenuCatalog(Long parentCatalogId) {
		if(logger.isDebugEnabled()){
			logger.debug("qrySubPrivateMenuCatalog parentCatalogId:{}", parentCatalogId);
		}
		return privateMenuCatalogMapper.selectSubByParentCatalogId(parentCatalogId);
	}

	/* (non-Javadoc)
	 * @see com.basic.oaas.service.PrivateMenuCatalogService#qryExistSubPrivateMenuCatalog(java.lang.Long)
	 */
	@Override
	public int qryExistSubMenuCatalog(Long parentCatalogId) {
		if(logger.isDebugEnabled()){
			logger.debug("qryExistSubPrivateMenuCatalog parentCatalogId:{}", parentCatalogId);
		}
		
		return privateMenuCatalogMapper.selectExistSubMenuCatalog(parentCatalogId);
	}

	/* (non-Javadoc)
	 * @see com.basic.oaas.service.PrivateMenuCatalogService#ModifyAllStateById(java.lang.Long)
	 */
	@Override
	public int modifyAllStateById(Long catalogId) {
		
		if(logger.isDebugEnabled()){
			logger.debug("modifyAllStateById CatalogId:{}", catalogId);
		}
		
		return privateMenuCatalogMapper.updateAllStateById(catalogId);
	}

	/* (non-Javadoc)
	 * @see com.basic.oaas.service.PrivateMenuCatalogService#qryAllCatalog()
	 */
	@Override
	public List<PrivateMenuCatalog> qryAllCatalog() {
		
		if(logger.isDebugEnabled()){
			logger.debug("qryAllCatalog {}");
		}
		return privateMenuCatalogMapper.selectAllCatalog();
	}
	
	

}
