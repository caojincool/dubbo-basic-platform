/**
 *
 * companyName
 * copyright 2015-2020
 * @date 2017年7月5日 上午10:49:45
 * @author Kevin
 * @Description: 区域实现类
 * 
 */
package com.basic.oaas.service;

import java.text.DecimalFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
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
import com.basic.oaas.bean.AreaIbean;
import com.basic.oaas.dao.AreaMapper;
import com.basic.oaas.define.GidCodes;
import com.basic.oaas.model.Area;
import com.basic.oaas.model.Company;

/**
 *
 * @date 2017年7月5日 上午10:49:45
 * @author Kevin
 * 
 */
@Service("areaService")
public class AreaServiceImpl extends BaseServerImpl<Long, Area> implements AreaService {

	private Logger logger = LoggerFactory.getLogger(AreaServiceImpl.class);
	
	private static JsonUtils jsonUtils = JsonUtils.getInstance();
	
	@Autowired
	private AreaMapper areaMapper;

	/* (non-Javadoc)
	 * @see com.basic.oaas.service.AreaService#addSelective(com.basic.oaas.model.Area)
	 */
	@Override
	public int createSelective(Area area) {
		
		if(logger.isDebugEnabled()){
			logger.debug("addSelective Area:{}",area);
		}
	
		Long gid = GidClientUtils.getInstance().getGidValue(GidCodes.OAAS_AREA_SEQ);
		
		area.setAreaId(gid);
		area.setCreateTime(DateUtils.now());
		//设置启用状态
		if (area.getOpenStatus()==null || area.getOpenStatus() == 1) {
			area.setOpenStatus(1);
			area.setOpenDate(new Date());
		}else if(area.getOpenStatus() == 0) {
			area.setStopDate(new Date());
		}
		//获取父区域
		Area parentArea = areaMapper.selectByPrimaryKey(area.getParentAreaId());
		//生成编码
		Map<String, Object> params = new HashMap<String,Object>();
		params.put("parentId", area.getParentAreaId());
		int num = areaMapper.countArea(params);
		if(parentArea == null){
			area.setAreaCode("R"+new DecimalFormat("00").format(num+1));
			area.setIdPath(gid.toString());
			area.setNamePath(area.getAreaName());
			area.setCodePath(area.getAreaCode());
			
		}else{
			area.setAreaCode(parentArea.getAreaCode()+new DecimalFormat("00").format(num+1));
			area.setIdPath(parentArea.getIdPath()+","+gid.toString());
			area.setNamePath(parentArea.getNamePath()+","+area.getAreaName());
			area.setCodePath(parentArea.getCodePath()+","+area.getAreaCode());
		}
		return areaMapper.insertSelective(area);
	}

	/* (non-Javadoc)
	 * @see com.basic.oaas.service.AreaService#qryAreaById(java.lang.Long)
	 */
	@Override
	public Area qryAreaById(Long areaId) {
		
		if(logger.isDebugEnabled()){
			logger.debug("qryAreaById areaId:{}",areaId);
		}
		
		return areaMapper.selectByPrimaryKey(areaId);
	}

	/* (non-Javadoc)
	 * @see com.basic.oaas.service.AreaService#modifyByPrimaryKey(com.basic.oaas.model.Area)
	 */
	@Override
	public int modifyByPrimaryKey(Area area) {
		
		if(logger.isDebugEnabled()){
			logger.debug("modifyByPrimaryKey area:{}",jsonUtils.objectToJson(area));
		}
		area.setModifyTime(DateUtils.now());
		//获取父区域
		Area parentArea = areaMapper.selectByPrimaryKey(area.getParentAreaId());
		
		if(parentArea == null){
			area.setNamePath(area.getAreaName());
			area.setCodePath(area.getAreaCode());
			area.setIdPath(area.getAreaId().toString());
		}else {
			area.setNamePath(parentArea.getNamePath()+","+area.getAreaName());
			area.setCodePath(parentArea.getCodePath()+","+area.getAreaCode());
			area.setIdPath(parentArea.getIdPath()+","+area.getAreaId());
		}
		
		//获取所有subArea
		List<Area> subArea = areaMapper.selectAllSubAreaById(area);
		for (Area c : subArea) {
			c.setNamePath(area.getNamePath()+","+c.getAreaName());
			c.setCodePath(area.getCodePath()+","+c.getAreaCode());
			c.setIdPath(area.getIdPath()+","+c.getAreaId());
			
			areaMapper.updateByPrimaryKey(c);
		}
		return areaMapper.updateByPrimaryKey(area);
	}

	/* (non-Javadoc)
	 * @see com.basic.oaas.service.AreaService#removeByPrimarykey(java.lang.Long)
	 */
	@Override
	public int removeByPrimarykey(Long areaId) {
		
		if(logger.isDebugEnabled()){
			logger.debug("removeByPrimarykey areaId:{}",areaId);
		}
//		return areaMapper.deleteByPrimaryKey(areaId);
		
		return areaMapper.updateAllStateById(areaId);
	}

	/* (non-Javadoc)
	 * @see com.basic.oaas.service.AreaService#qryByUserId(java.lang.Long)
	 */
	@Override
	public List<Area> qryByArea(AreaIbean iBean) {
		
		if(logger.isDebugEnabled()){
			logger.debug("qryByArea AreaIBean:{}",jsonUtils.objectToJson(iBean));
		}
		return areaMapper.selectByArea(iBean);
	}

	/* (non-Javadoc)
	 * @see com.basic.oaas.service.AreaService#qrySubAreaById(java.lang.Long)
	 */
	@Override
	public List<Area> qrySubAreaById(Long areaId) {
		
		if(logger.isDebugEnabled()){
			logger.debug("qrySubAreaById areaId:{}",areaId);
		}
		return areaMapper.selectSubAreaById(areaId);
	}

	/* (non-Javadoc)
	 * @see com.basic.oaas.service.AreaService#qryAllSubAreaById(com.basic.oaas.model.Area)
	 */
	@Override
	public List<Area> qryAllSubAreaById(Area area) {
		
		if(logger.isDebugEnabled()){
			logger.debug("qryAllSubAreaById area:{}",jsonUtils.objectToJson(area));
		}
		return areaMapper.selectAllSubAreaById(area);
	}

	/* (non-Javadoc)
	 * @see com.basic.oaas.service.AreaService#qryExistSubAreaById(java.lang.Long, java.lang.Long)
	 */
	@Override
	public int qryExistSubAreaById(AreaIbean iBean) {
		
		if(logger.isDebugEnabled()){
			logger.debug("qryExistSubAreaById area:{}",jsonUtils.objectToJson(iBean));
		}
		
		return areaMapper.selectExistSubAreaById(iBean);
	}

	/* (non-Javadoc)
	 * @see com.basic.oaas.service.AreaService#createOrModify(com.basic.oaas.bean.AreaIBean)
	 */
	@Override
	public Area createOrModify(Area area) {
		
		if(logger.isDebugEnabled()){
			logger.debug("createOrModify iBean:{}",jsonUtils.objectToJson(area));
		}
		
		if(area.getAreaId() == null){//新增
			createSelective(area);
		}else{//修改
			modifyByPrimaryKey(area);
		}
		return area;
	}

	/* (non-Javadoc)
	 * @see com.basic.oaas.service.AreaService#qryExistAreaCode(java.lang.String)
	 */
	@Override
	public int qryExistAreaCode(String areaCode) {
		
		if(logger.isDebugEnabled()){
			logger.debug("qryExistAreaCode areaCode:{}",areaCode);
		}
		return areaMapper.selectExistAreaCode(areaCode);
	}

	@Override
	public void create(Area entity) {
		this.createOrModify(entity);
	}

	@Override
	public void update(Area entity) {
		this.createOrModify(entity);
	}

	@Override
	public String getNamespace() {
		return AreaMapper.class.getName();
	}
	
	@Override
	public PageJson queryPage(DefaultQueryFilter queryFilter) {
        return new PageJson(new PageList<>(super.query(queryFilter)));
    }
    
	@Override
    public List<Area> query(DefaultQueryFilter  queryFilter) {
 		queryFilter.setPage(null);
 		return super.query(queryFilter);
 	}
	
}
