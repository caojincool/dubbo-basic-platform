/**
 *
 * companyName
 * copyright 2015-2020
 * @date 2017年8月8日 上午9:57:38
 * @author Kevin
 * @Description: 
 * 
 */
package com.basic.oaas.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.basic.framework.common.api.db.domain.PageJson;
import com.basic.framework.common.api.db.domain.PageList;
import com.basic.framework.common.api.query.QueryOP;
import com.basic.framework.common.baseServerImpl.BaseServerImpl;
import com.basic.framework.common.model.DefaultQueryFilter;
import com.basic.framework.common.utils.BeanUtils;
import com.basic.framework.common.utils.datatype.DateUtils;
import com.basic.framework.common.utils.datatype.JsonUtils;
import com.basic.framework.gid.utils.sequence.GidClientUtils;
import com.basic.oaas.bean.PrivateIbean;
import com.basic.oaas.dao.PrivateMapper;
import com.basic.oaas.define.GidCodes;
import com.basic.oaas.define.PrivateType;
import com.basic.oaas.model.Private;

/**
 *
 * @date 2017年8月8日 上午9:57:38
 * @author Kevin
 * @Description: 权限目录接口实现类
 * 
 */
@Service("privateService")
public class PrivateServiceImpl extends BaseServerImpl<Long, Private> implements PrivateService {

	private Logger logger = LoggerFactory.getLogger(PrivateServiceImpl.class);

	private static final JsonUtils JSON_UTILS = JsonUtils.getInstance();
	
	@Autowired
	private PrivateMapper privateMapper;
	/* (non-Javadoc)
	 * @see com.basic.oaas.service.PrivateService#removeByPrimaryKey(java.lang.Long)
	 */
	@Override
	public int removeByPrimaryKey(Long privateId) {
		if (logger.isDebugEnabled()) {
			logger.debug("removeByPrimaryKey catalogId:{}", privateId);
		}
		return privateMapper.deleteByPrimaryKey(privateId);
	}

	/* (non-Javadoc)
	 * @see com.basic.oaas.service.PrivateService#createSelective(com.basic.oaas.model.Private)
	 */
	@Override
	public int createSelective(Private record) {
		if (logger.isDebugEnabled()) {
			logger.debug("createSelective record:{}", JSON_UTILS.objectToJson(record));
		}
		
		Long gid = GidClientUtils.getInstance().getGidValue(GidCodes.OAAS_PRIVATE_SEQ);
		record.setPrivateId(gid);
		record.setCreateTime(DateUtils.now());
		return privateMapper.insertSelective(record);
	}

	/* (non-Javadoc)
	 * @see com.basic.oaas.service.PrivateService#qryByPrimaryKey(java.lang.Long)
	 */
	@Override
	public Private qryByPrimaryKey(Long privateId) {
		if (logger.isDebugEnabled()) {
			logger.debug("qryByPrimaryKey privateId:{}", privateId);
		}
		return privateMapper.selectByPrimaryKey(privateId);
	}

	/* (non-Javadoc)
	 * @see com.basic.oaas.service.PrivateService#modifyByPrimaryKeySelective(com.basic.oaas.model.Private)
	 */
	@Override
	public int modifyByPrimaryKeySelective(Private record) {
		if (logger.isDebugEnabled()) {
			logger.debug("modifyByPrimaryKeySelective record:{}", JSON_UTILS.objectToJson(record));
		}
		record.setModifyTime(DateUtils.now());
		return privateMapper.updateByPrimaryKey(record);

	}


	/* (non-Javadoc)
	 * @see com.basic.oaas.service.PrivateService#createOrModify(com.basic.oaas.model.Private)
	 */
	@Override
	public void createOrModify(Private ibean) {
		if(logger.isDebugEnabled()){
			logger.debug("createOrModify Private:{}", JSON_UTILS.objectToJson(ibean));
		}
		
		if(ibean.getPrivateId() == null){//新增
			createSelective(ibean);
		}else{//修改
			modifyByPrimaryKeySelective(ibean);
		}
	}

	/* (non-Javadoc)
	 * @see com.basic.oaas.service.PrivateService#removeBatchByprivateIds(java.lang.Long[])
	 */
	@Override
	public int removeBatchByPrivateIds(Long[] privateIds) {
		if(logger.isDebugEnabled()){
			logger.debug("removeBatchByPrivateIds privateIds:{}", JSON_UTILS.objectToJson(privateIds));
		}
		return privateMapper.updateBatchByPrivateIds(privateIds);
	}

	/* (non-Javadoc)
	 * @see com.basic.oaas.service.PrivateService#removeBatchByMenuIds(java.lang.Long[])
	 */
	@Override
	public int removeBatchByMenuIds(Long[] menuIds) {
		if(logger.isDebugEnabled()){
			logger.debug("removeBatchByMenuIds menuIds:{}", JSON_UTILS.objectToJson(menuIds));
		}
		return privateMapper.updateBatchByMenuIds(menuIds);
	}

	/* (non-Javadoc)
	 * @see com.basic.oaas.service.PrivateService#removeBatchByFuncIds(java.lang.Long[])
	 */
	@Override
	public int removeBatchByFuncIds(Long[] funcIds) {
		if(logger.isDebugEnabled()){
			logger.debug("removeBatchByFuncIds funcIds:{}", JSON_UTILS.objectToJson(funcIds));
		}
		return privateMapper.updateBatchByFuncIds(funcIds);
	}
	
	/* (non-Javadoc)
	 * @see com.basic.oaas.service.PrivateService#removeBatchByFuncIds(java.lang.Long[])
	 */
	@Override
	public int removeBatchByAttrIds(Long[] attrIds) {
		if(logger.isDebugEnabled()){
			logger.debug("removeBatchByAttrIds attrIds:{}", JSON_UTILS.objectToJson(attrIds));
		}
		return privateMapper.updateBatchByAttrIds(attrIds);
	}

	/* (non-Javadoc)
	 * @see com.basic.oaas.service.PrivateService#qryPrivateByUserId(java.lang.Long)
	 */
	@Override
	public List<Private> qryPrivateByUserId(Long userId) {
		if(logger.isDebugEnabled()){
			logger.debug("qryPrivateByUserId userId:{}", JSON_UTILS.objectToJson(userId));
		}
		return privateMapper.selectPrivateByUserId(userId);
	}

	/* (non-Javadoc)
	 * @see com.basic.oaas.service.PrivateService#qryPrivateByRequest(java.lang.String)
	 */
	@Override
	public List<String> qryPrivateByRequest(String requestUrl) {
		if(logger.isDebugEnabled()){
			logger.debug("qryPrivateByRequest requestUrl:{}", requestUrl);
		}
		return privateMapper.selectPrivateByRequest(requestUrl);
	}

	/* (non-Javadoc)
	 * @see com.basic.oaas.service.PrivateService#qryPrivateByUsername(java.lang.String)
	 */
	@Override
	public List<String> qryPrivateByUsername(String username) {
		if(logger.isDebugEnabled()){
			logger.debug("qryPrivateByUsername username:{}", username);
		}
		return privateMapper.selectPrivateByUsername(username);
	}

	/* (non-Javadoc)
	 * @see com.basic.oaas.service.PrivateService#qryAllPrivate()
	 */
	@Override
	public List<String> qryAllPrivate() {
		if(logger.isDebugEnabled()){
			logger.debug("qryAllPrivate :{}");
		}
		return privateMapper.selectAllPrivate();
	}

	@Override
	public void create(Private entity) {
		this.createOrModify(entity);
	}

	@Override
	public void update(Private entity) {
		this.createOrModify(entity);
	}

	@Override
	public String getNamespace() {
		return PrivateMapper.class.getName();
	}
	
	@Override
	public PageJson queryPage(DefaultQueryFilter  queryFilter) {
        return  new PageJson(new PageList<>(super.query(queryFilter)));
    }
    
	@Override
    public List<Private> query(DefaultQueryFilter  queryFilter) {
 		queryFilter.setPage(null);
 		return super.query(queryFilter);
 	}
	
	@Override
	public JSONObject getPrivates(Long userId,Long menuId) {
		JSONObject jsonObject = new JSONObject();
		//查询分配的权限
		DefaultQueryFilter queryFilter = new DefaultQueryFilter();
		queryFilter.addFilter("RES.USER_ID", userId,  QueryOP.EQUAL);
		queryFilter.addParamsFilter("userId", userId);
		
		if (BeanUtils.isNotEmpty(menuId)) {
			queryFilter.addFilter("RES.MENU_ID", menuId,  QueryOP.EQUAL);
		}
		List<Private> list = this.query(queryFilter);
		Set<Private> menuList = new HashSet<Private>();
		Set<Private> funcList = new HashSet<Private>();
		Set<Private> attrList = new HashSet<Private>();
		if (BeanUtils.isNotEmpty(list)) {
			for (Private pri : list) {
				if (PrivateType.MENU.getCode().equals(pri.getPrivateType())) {
					menuList.add(pri);
				}else if (PrivateType.FUNC.getCode().equals(pri.getPrivateType())) {
					funcList.add(pri);
				}else if (PrivateType.ATTR.getCode().equals(pri.getPrivateType())) {
					attrList.add(pri);
				}
			}
		}
		jsonObject.put("menus", menuList);
		jsonObject.put("funcs", funcList);
		jsonObject.put("attrs", attrList);
		
		return jsonObject;
	}
	

}
