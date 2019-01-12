package com.basic.order.service;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.basic.framework.common.utils.datatype.DateUtils;
import com.basic.framework.common.utils.datatype.JsonUtils;
import com.basic.framework.gid.utils.sequence.GidClientUtils;
import com.basic.order.dao.TacheCatalogMapper;
import com.basic.order.define.BasicStateEnum;
import com.basic.order.define.GidCodes;
import com.basic.order.define.PageDateTypeEnum;
import com.basic.order.model.TacheCatalog;

/**
 * 
 *
 * @date 2017年8月17日 上午11:49:24
 * 
 * @Description: 环节目录
 *
 */
@Service("tacheCatalogService")
public class TacheCatalogServiceImpl implements TacheCatalogService{

	private Logger logger = LoggerFactory.getLogger(TacheCatalogServiceImpl.class);

	private static final JsonUtils JSON_UTILS = JsonUtils.getInstance();
	
	@Autowired
	private TacheCatalogMapper tacheCatalogMapper;

	/* (non-Javadoc)
	 * @see com.basic.order.service.TacheCatalogService#removeByPrimaryKey(java.lang.Long)
	 */
	@Override
	public int removeByPrimaryKey(Long catalogId) {
		if (logger.isDebugEnabled()) {
			logger.debug("removeByPrimaryKey catalogId:{}", catalogId);
		}
		return tacheCatalogMapper.deleteByPrimaryKey(catalogId);
	}

	/* (non-Javadoc)
	 * @see com.basic.order.service.TacheCatalogService#createSelective(com.basic.order.model.TacheCatalog)
	 */
	@Override
	public int createSelective(TacheCatalog record) {
		if (logger.isDebugEnabled()) {
			logger.debug("createSelective TacheCatalog:{}", JSON_UTILS.objectToJson(record));
		}

		Long gid = GidClientUtils.getInstance().getGidValue(GidCodes.PF_TACHE_CATALOG_SEQ);
		record.setCatalogId(gid);
		
		return tacheCatalogMapper.insertSelective(record);
	}

	/* (non-Javadoc)
	 * @see com.basic.order.service.TacheCatalogService#qryByPrimaryKey(java.lang.Long)
	 */
	@Override
	public TacheCatalog qryByPrimaryKey(Long catalogId) {
		if (logger.isDebugEnabled()) {
			logger.debug("qryByPrimaryKey catalogId:{}", catalogId);
		}

		return tacheCatalogMapper.selectByPrimaryKey(catalogId);
	}

	/* (non-Javadoc)
	 * @see com.basic.order.service.TacheCatalogService#modifyByPrimaryKeySelective(com.basic.order.model.TacheCatalog)
	 */
	@Override
	public int modifyByPrimaryKeySelective(TacheCatalog record) {
		if (logger.isDebugEnabled()) {
			logger.debug("modifyByPrimaryKeySelective TacheCatalog:{}", JSON_UTILS.objectToJson(record));
		}
		
		return tacheCatalogMapper.updateByPrimaryKeySelective(record);
	}

	/* (non-Javadoc)
	 * @see com.basic.order.service.TacheCatalogService#qryByParentId(java.lang.Long)
	 */
	@Override
	public List<TacheCatalog> qryByParentId(Long parentCatalogId) {
		if (logger.isDebugEnabled()) {
			logger.debug("qryByParentId parentCatalogId:{}", parentCatalogId);
		}
		
		List<TacheCatalog> list = null;
		if(parentCatalogId != null){
			list = tacheCatalogMapper.selectByParentId(parentCatalogId);
		}
		return list;
	}

	/* (non-Javadoc)
	 * @see com.basic.order.service.TacheCatalogService#createOrModify(com.basic.order.model.TacheCatalog)
	 */
	@Override
	public void createOrModify(TacheCatalog ibean) {
		if(logger.isDebugEnabled()){
			logger.debug("createOrModify TacheCatalog:{}", JSON_UTILS.objectToJson(ibean));
		}
		
		Date now = DateUtils.now();
//		if(StringUtils.isBlank(ibean.getOrderType())){//新增
		if(PageDateTypeEnum.CREATE.getCode().equals(ibean.getPageDateType())){//新增
			ibean.setCreateTime(now);
			ibean.setState(BasicStateEnum.NORMAL.getCode());
			createSelective(ibean);
		}else{//修改
			ibean.setModifyTime(now);
			modifyByPrimaryKeySelective(ibean);
		}			
	}

}
