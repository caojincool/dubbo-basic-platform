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
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
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
import com.basic.oaas.dao.PrivateMenuMapper;
import com.basic.oaas.define.GidCodes;
import com.basic.oaas.define.PrivateType;
import com.basic.oaas.model.Private;
import com.basic.oaas.model.PrivateAttr;
import com.basic.oaas.model.PrivateFunc;
import com.basic.oaas.model.PrivateMenu;

/**
 *
 * @date 2017年8月8日 上午9:57:38
 * @author Kevin
 * @Description: 菜单接口实现类
 * 
 */
@Service("privateMenuService")
public class PrivateMenuServiceImpl extends BaseServerImpl<Long, PrivateMenu> implements PrivateMenuService {

	private Logger logger = LoggerFactory.getLogger(PrivateMenuServiceImpl.class);

	private static final JsonUtils JSON_UTILS = JsonUtils.getInstance();
	
	@Autowired
	private PrivateMenuMapper privateMenuMapper;
	
	@Autowired
	private PrivateService privateService;
	
	@Autowired
	private PrivateFuncService privateFuncService;
	
	@Autowired
	PrivateAttrService privateAttrService;
	
	/* (non-Javadoc)
	 * @see com.basic.oaas.service.PrivateService#removeByPrimaryKey(java.lang.Long)
	 */
	@Override
	public int removeByPrimaryKey(Long menuId) {
		if (logger.isDebugEnabled()) {
			logger.debug("removeByPrimaryKey catalogId:{}", menuId);
		}
		return privateMenuMapper.deleteByPrimaryKey(menuId);
	}

	/* (non-Javadoc)
	 * @see com.basic.oaas.service.PrivateService#createSelective(com.basic.oaas.model.Private)
	 */
	@Override
	public int createSelective(PrivateMenu record) {
		if (logger.isDebugEnabled()) {
			logger.debug("createSelective record:{}", JSON_UTILS.objectToJson(record));
		}
		Private ibean = new Private();
		if (record.getOpenStatus()==null || record.getOpenStatus() == 1) {
			record.setOpenStatus(1);
			record.setOpenDate(new Date());
		}else if(record.getOpenStatus() == 0) {
			record.setStopDate(new Date());
		}
		
		ibean.setPrivateName(record.getMenuName());
		ibean.setPrivateCode(record.getMenuCode());
		ibean.setPrivateType(PrivateType.MENU.getCode());
		ibean.setCreateTime(DateUtils.now());
		ibean.setCreateUserId(record.getCreateUserId());
		ibean.setRemarks(record.getRemarks());
		ibean.setState("10A");
		privateService.createSelective(ibean);
		
		
		Long gid = GidClientUtils.getInstance().getGidValue(GidCodes.OAAS_PRIV_MENU_SEQ);
		record.setMenuId(gid);
		record.setCreateTime(DateUtils.now());
		record.setPrivateId(ibean.getPrivateId());
		
		//获取父节点
		PrivateMenu parent = privateMenuMapper.selectByPrimaryKey(record.getParentMenuId());
		if(parent == null){
			record.setIdPath(gid.toString());
		}else{
			record.setIdPath(parent.getIdPath()+","+gid.toString());
		}
		
		int num = privateMenuMapper.insertSelective(record);
		
		saveFuncAndAttr(record,true);
		
		return num;
	}

	/* (non-Javadoc)
	 * @see com.basic.oaas.service.PrivateService#qryByPrimaryKey(java.lang.Long)
	 */
	@Override
	public PrivateMenu qryByPrimaryKey(Long menuId) {
		if (logger.isDebugEnabled()) {
			logger.debug("qryByPrimaryKey menuId:{}", menuId);
		}
		PrivateMenu privateMenu = privateMenuMapper.selectByPrimaryKey(menuId);
		if (privateMenu!=null) {
			//获取功能按钮
			DefaultQueryFilter funcQueryFilter = new DefaultQueryFilter();
			funcQueryFilter.addFilter("MENU_ID", privateMenu.getMenuId(), QueryOP.EQUAL);
			privateMenu.setFuncs(privateFuncService.query(funcQueryFilter));
			
			//获取字段
			DefaultQueryFilter attrQueryFilter = new DefaultQueryFilter();
			attrQueryFilter.addFilter("MENU_ID", privateMenu.getMenuId(), QueryOP.EQUAL);
			privateMenu.setAttrs(privateAttrService.query(attrQueryFilter));
			
		}
		return privateMenu;
	}

	/* (non-Javadoc)
	 * @see com.basic.oaas.service.PrivateService#modifyByPrimaryKeySelective(com.basic.oaas.model.Private)
	 */
	@Override
	public int modifyByPrimaryKeySelective(PrivateMenu record) {
		if (logger.isDebugEnabled()) {
			logger.debug("modifyByPrimaryKeySelective record:{}", JSON_UTILS.objectToJson(record));
		}
	
		record.setModifyTime(DateUtils.now());
		
		Private priv =  privateService.qryByPrimaryKey(record.getPrivateId());
		if (priv!=null) {
			priv.setModifyUserId(record.getModifyUserId());
			priv.setPrivateName(record.getMenuName());
			privateService.modifyByPrimaryKeySelective(priv);
		}
		
		
		PrivateMenu recordDb = privateMenuMapper.selectByPrimaryKey(record.getMenuId());
		if (recordDb!=null) {
			//与数据库不一致才修改
			if ((record.getOpenStatus()==null || record.getOpenStatus() == 1)
					&& recordDb.getOpenStatus()==0) {
				record.setOpenStatus(1);
				record.setOpenDate(new Date());
			}else if(record.getOpenStatus()!=null
					&& record.getOpenStatus() == 0 
					&& recordDb.getOpenStatus()==1) {
				record.setStopDate(new Date());
				//批量关闭子菜单
				Map<String, Object> params = new HashMap<String,Object>();
				params.put("value", record.getId());
				params.put("column", "ID_PATH");
				List<PrivateMenu> list = privateMenuMapper.selectFindInSet(params);
				if (BeanUtils.isNotEmpty(list)) {
					for (PrivateMenu children : list) {
						if (children.getOpenStatus()==1) {
							children.setOpenStatus(0);
							children.setStopDate(new Date());
							children.setModifyUserId(record.getModifyUserId());
							children.setModifyTime(new Date());
							privateMenuMapper.updateByPrimaryKey(children);
						}
					}
				}
			}
		}
		
		//获取父节点
		PrivateMenu parent = privateMenuMapper.selectByPrimaryKey(record.getParentMenuId());
		if(parent == null){
			record.setIdPath(record.getMenuId().toString());
		}else{
			record.setIdPath(parent.getIdPath()+","+record.getMenuId());
		}
		
		int num = privateMenuMapper.updateByPrimaryKey(record);
		
		saveFuncAndAttr(record,false);
		
		return num;

	}


	/* (non-Javadoc)
	 * @see com.basic.oaas.service.PrivateService#createOrModify(com.basic.oaas.model.Private)
	 */
	@Override
	public PrivateMenu createOrModify(PrivateMenu ibean) {
		if(logger.isDebugEnabled()){
			logger.debug("createOrModify Private:{}", JSON_UTILS.objectToJson(ibean));
		}
		
		if(BeanUtils.isEmpty(ibean.getMenuId())){//新增
			createSelective(ibean);
		}else{//修改
			modifyByPrimaryKeySelective(ibean);
		}
		return ibean;
	}
	
	private void saveFuncAndAttr(PrivateMenu menu,boolean isCreate) {
		//保存按钮信息
		if (BeanUtils.isNotEmpty(menu.getFuncs())) {
			for (PrivateFunc func : menu.getFuncs()) {
				if (isCreate) {
					func.setCreateUserId(menu.getCreateUserId());
				}else {
					func.setModifyUserId(menu.getModifyUserId());
				}
				func.setMenuId(menu.getMenuId());
				privateFuncService.createOrModify(func);
			}
		}
		//保存字段信息
		if (BeanUtils.isNotEmpty(menu.getAttrs())) {
			for (PrivateAttr attr : menu.getAttrs()) {
				attr.setMenuId(menu.getMenuId());
				if (isCreate) {
					attr.setCreateUserId(menu.getCreateUserId());
					privateAttrService.create(attr);
				}else {
					attr.setModifyUserId(menu.getModifyUserId());
					privateAttrService.update(attr);
				}
			}
		}
	}

	/* (non-Javadoc)
	 * @see com.basic.oaas.service.PrivateService#removeBatchBymenuIds(java.lang.Long[])
	 */
	@Override
	public int removeBatchByPrivateMenuIds(Long[] menuIds) {
		if(logger.isDebugEnabled()){
			logger.debug("removeBatchByPrivateIds menuIds:{}", JSON_UTILS.objectToJson(menuIds));
		}
		List<Long> ids = new ArrayList<Long>();
		List<Long> funcIds = new ArrayList<Long>();
		List<Long> attrIds = new ArrayList<Long>();
		if (BeanUtils.isNotEmpty(menuIds)) {
			for (int i = 0; i < menuIds.length; i++) {
				Map<String, Object> params = new HashMap<String,Object>();
				params.put("value", menuIds[i]);
				params.put("column", "ID_PATH");
				List<PrivateMenu> list = privateMenuMapper.selectFindInSet(params);
				if (BeanUtils.isNotEmpty(list)) {
					for (PrivateMenu privateMenu : list) {
						ids.add(privateMenu.getMenuId());
						//获取功能按钮
						DefaultQueryFilter funcQueryFilter = new DefaultQueryFilter();
						funcQueryFilter.addFilter("MENU_ID", privateMenu.getMenuId(), QueryOP.EQUAL);
						List<PrivateFunc> funcs = privateFuncService.query(funcQueryFilter);
						if (BeanUtils.isNotEmpty(funcs)) {
							for (PrivateFunc privateFunc : funcs) {
								funcIds.add(privateFunc.getFuncId());
							}
						}
						
						//获取字段
						DefaultQueryFilter attrQueryFilter = new DefaultQueryFilter();
						attrQueryFilter.addFilter("MENU_ID", privateMenu.getMenuId(), QueryOP.EQUAL);
						List<PrivateAttr> attrs = privateAttrService.query(attrQueryFilter);
						if (BeanUtils.isNotEmpty(attrs)) {
							for (PrivateAttr privateAttr : attrs) {
								attrIds.add(privateAttr.getAttrId());
							}
						}
					}
					
				}
			}
		}
		int num = 0;
		if (BeanUtils.isNotEmpty(ids)) {
			privateService.removeBatchByMenuIds(ids.toArray(new Long[ids.size()]));
			num = privateMenuMapper.updateBatchByMenuIds(ids.toArray(new Long[ids.size()]));
		}
		if (BeanUtils.isNotEmpty(funcIds)) {
			//删除关联的功能按钮
			privateFuncService.removeBatchByPrivateFuncIds(funcIds.toArray(new Long[funcIds.size()]));
		}
		if (BeanUtils.isNotEmpty(attrIds)) {
			//删除关联的字段权限
			privateAttrService.removeByIds(attrIds.toArray(new Long[attrIds.size()]));
		}
		return num;
	}

	/* (non-Javadoc)
	 * @see com.basic.oaas.service.PrivateMenuService#qryMenuByCode(java.util.List)
	 */
	@Override
	public List<PrivateMenu> qryMenuByCode(List<String> codes) {
		if(logger.isDebugEnabled()){
			logger.debug("qryMenuByCode codes:{}", JSON_UTILS.objectToJson(codes));
		}
		
		return privateMenuMapper.selectMenuByCode(codes);
	}

	@Override
	public void create(PrivateMenu entity) {
		this.createOrModify(entity);
	}

	@Override
	public void update(PrivateMenu entity) {
		this.createOrModify(entity);
		
	}

	@Override
	public String getNamespace() {
		return PrivateMenuMapper.class.getName();
	}
	
	@Override
	public PageJson queryPage(DefaultQueryFilter  queryFilter) {
        return  new PageJson(new PageList<>(super.query(queryFilter)));
    }
    
	@Override
    public List<PrivateMenu> query(DefaultQueryFilter  queryFilter) {
 		queryFilter.setPage(null);
 		return super.query(queryFilter);
 	}

	@Override
	public List<PrivateMenu> getPrivateMenus(Long userId,Long menuId) {
		Map<String, Object> params = new HashMap<String,Object>();
		params.put("userId", userId);
		params.put("menuId", menuId);
		return privateMenuMapper.getPrivateMenus(params);
	}
	
	@Override
	public void getChildren(JSONArray children) {
		for (int i = 0; i < children.size(); i++) {
			JSONObject object = children.getJSONObject(i);
			PrivateMenu cMenu = JSON.parseObject(object.toJSONString(), PrivateMenu.class);
			if (BeanUtils.isEmpty(cMenu.getParentId())) {
				cMenu.setParentMenuId(-1L);
			}
			if (BeanUtils.isNotEmpty(cMenu.getMenuId())) {
				PrivateMenu dbRecord = this.qryByPrimaryKey(cMenu.getMenuId());
				dbRecord.setMenuName(cMenu.getMenuName());
				dbRecord.setMenuCode(cMenu.getMenuCode());
				dbRecord.setMenuIcon(cMenu.getMenuIcon());
				dbRecord.setMenuUrl(cMenu.getMenuUrl());
				dbRecord.setFuncs(cMenu.getFuncs());
				cMenu = dbRecord;
			}
			cMenu = this.createOrModify(cMenu);
			JSONArray children2 = object.getJSONArray("children");
			if (BeanUtils.isNotEmpty(children2)) {
				for (int j = 0; j < children2.size(); j++) {
					JSONObject cObj = children2.getJSONObject(j);
					cObj.put("parentMenuId", cMenu.getMenuId());
				}
				this.getChildren(children2);
			}
		}
	}

}
