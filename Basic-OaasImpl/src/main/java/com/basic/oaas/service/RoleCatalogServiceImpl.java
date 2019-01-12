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

import com.basic.framework.common.api.db.domain.PageJson;
import com.basic.framework.common.api.db.domain.PageList;
import com.basic.framework.common.baseServerImpl.BaseServerImpl;
import com.basic.framework.common.model.DefaultQueryFilter;
import com.basic.framework.common.utils.datatype.DateUtils;
import com.basic.framework.common.utils.datatype.JsonUtils;
import com.basic.framework.gid.utils.sequence.GidClientUtils;
import com.basic.oaas.dao.RoleCatalogMapper;
import com.basic.oaas.define.GidCodes;
import com.basic.oaas.model.Role;
import com.basic.oaas.model.RoleCatalog;

/**
 *
 * @date 2017年8月8日 上午9:57:54
 * @author Kevin
 * @Description: 菜单目录接口实现类
 * 
 */
@Service("roleCatalogService")
public class RoleCatalogServiceImpl extends BaseServerImpl<Long, RoleCatalog> implements RoleCatalogService{

	private Logger logger = LoggerFactory.getLogger(RoleCatalogServiceImpl.class);

	private static final JsonUtils JSON_UTILS = JsonUtils.getInstance();
	
	@Autowired
	private RoleCatalogMapper roleCatalogMapper;
	/* (non-Javadoc)
	 * @see com.basic.oaas.service.RoleCatalogService#removeByPrimaryKey(java.lang.Long)
	 */
	@Override
	public int removeByPrimaryKey(Long catalogId) {
		if (logger.isDebugEnabled()) {
			logger.debug("removeByPrimaryKey catalogId:{}", catalogId);
		}
		
		return roleCatalogMapper.deleteByPrimaryKey(catalogId);
	}

	/* (non-Javadoc)
	 * @see com.basic.oaas.service.RoleCatalogService#createSelective(com.basic.oaas.model.RoleCatalog)
	 */
	@Override
	public int createSelective(RoleCatalog record) {
		if (logger.isDebugEnabled()) {
			logger.debug("createSelective record:{}", JSON_UTILS.objectToJson(record));
		}
		
		Long gid = GidClientUtils.getInstance().getGidValue(GidCodes.OAAS_ROLE_CATALOG_SEQ);
		record.setCatalogId(gid);
		record.setCreateTime(DateUtils.now());
		record.setState("10A");
		//获取父权限目录
		RoleCatalog parentCatalog = roleCatalogMapper.selectByPrimaryKey(record.getParentCatalogId());
		
		if(parentCatalog == null){
			record.setIdPath(gid.toString());
		}else{
			record.setIdPath(parentCatalog.getIdPath()+","+gid.toString());
		}
		
		return roleCatalogMapper.insertSelective(record);
	}

	/* (non-Javadoc)
	 * @see com.basic.oaas.service.RoleCatalogService#qryByPrimaryKey(java.lang.Long)
	 */
	@Override
	public RoleCatalog qryByPrimaryKey(Long catalogId) {
		
		if (logger.isDebugEnabled()) {
			logger.debug("qryByPrimaryKey catalogId:{}", catalogId);
		}
		
		return roleCatalogMapper.selectByPrimaryKey(catalogId);
	}

	/* (non-Javadoc)
	 * @see com.basic.oaas.service.RoleCatalogService#modifyByPrimaryKeySelective(com.basic.oaas.model.RoleCatalog)
	 */
	@Override
	public int modifyByPrimaryKeySelective(RoleCatalog record) {
		
		if (logger.isDebugEnabled()) {
			logger.debug("modifyByPrimaryKeySelective record:{}", JSON_UTILS.objectToJson(record));
		}
		record.setModifyTime(DateUtils.now());
		//获取父权限目录
		RoleCatalog parentCatalog = roleCatalogMapper.selectByPrimaryKey(record.getParentCatalogId());
		
		if(parentCatalog == null){
			record.setIdPath(record.getCatalogId().toString());
		}else{
			record.setIdPath(parentCatalog.getIdPath()+","+record.getCatalogId());
		}
		return roleCatalogMapper.updateByPrimaryKey(record);
	}


	/* (non-Javadoc)
	 * @see com.basic.oaas.service.RoleCatalogService#createOrModify(com.basic.oaas.model.RoleCatalog)
	 */
	@Override
	public RoleCatalog createOrModify(RoleCatalog ibean) {
		if(logger.isDebugEnabled()){
			logger.debug("createOrModify RoleCatalog:{}", JSON_UTILS.objectToJson(ibean));
		}
		
		if(ibean.getCatalogId() == null){//新增
			createSelective(ibean);
		}else{//修改
			modifyByPrimaryKeySelective(ibean);
		}
		return ibean;
	}


	/* (non-Javadoc)
	 * @see com.basic.oaas.service.RoleCatalogService#qrySubRoleCatalog(java.lang.Long)
	 */
	@Override
	public List<RoleCatalog> qrySubRoleCatalog(Long parentCatalogId) {
		if(logger.isDebugEnabled()){
			logger.debug("qrySubRoleCatalog parentCatalogId:{}", parentCatalogId);
		}
		return roleCatalogMapper.selectSubByParentCatalogId(parentCatalogId);
	}

	/* (non-Javadoc)
	 * @see com.basic.oaas.service.RoleCatalogService#qryExistSubRoleCatalog(java.lang.Long)
	 */
	@Override
	public int qryExistSubRoleCatalog(Long parentCatalogId) {
		if(logger.isDebugEnabled()){
			logger.debug("qryExistSubRoleCatalog parentCatalogId:{}", parentCatalogId);
		}
		
		return roleCatalogMapper.selectExistSubRoleCatalog(parentCatalogId);
	}

	/* (non-Javadoc)
	 * @see com.basic.oaas.service.RoleCatalogService#ModifyAllStateById(java.lang.Long)
	 */
	@Override
	public int modifyAllStateById(Long catalogId) {
		
		if(logger.isDebugEnabled()){
			logger.debug("modifyAllStateById CatalogId:{}", catalogId);
		}
		
		return roleCatalogMapper.updateAllStateById(catalogId);
	}

	@Override
	public void create(RoleCatalog entity) {
		createOrModify(entity);
		
	}

	@Override
	public void update(RoleCatalog entity) {
		createOrModify(entity);
	}

	@Override
	public String getNamespace() {
		return RoleCatalogMapper.class.getName();
	}
	
	@Override
	public PageJson queryPage(DefaultQueryFilter queryFilter) {
		return new PageJson(new PageList<>(super.query(queryFilter)));
	}
	
	@Override
	public List<RoleCatalog> query(DefaultQueryFilter queryFilter) {
		queryFilter.setPage(null);
 		return super.query(queryFilter);
	}

}
