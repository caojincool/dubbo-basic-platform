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
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.basic.framework.common.api.query.QueryOP;
import com.basic.framework.common.baseServerImpl.BaseServerImpl;
import com.basic.framework.common.model.DefaultQueryFilter;
import com.basic.framework.common.utils.BeanUtils;
import com.basic.framework.common.utils.Collections3;
import com.basic.framework.common.utils.datatype.DateUtils;
import com.basic.framework.common.utils.datatype.JsonUtils;
import com.basic.framework.gid.utils.sequence.GidClientUtils;
import com.basic.oaas.bean.JobIbean;
import com.basic.oaas.bean.OrgIbean;
import com.basic.oaas.dao.OrgMapper;
import com.basic.oaas.define.GidCodes;
import com.basic.oaas.define.PrivateType;
import com.basic.oaas.model.Job;
import com.basic.oaas.model.Org;
import com.basic.oaas.model.OrgCompany;
import com.basic.oaas.model.PrivateDataData;
import com.basic.oaas.model.User;

/**
 *
 * @date 2017年7月5日 上午10:49:45
 * @author Kevin
 * 
 */
@Service("orgService")
public class OrgServiceImpl extends BaseServerImpl<Long, Org> implements OrgService {

	private Logger logger = LoggerFactory.getLogger(OrgServiceImpl.class);
	
	private static JsonUtils jsonUtils = JsonUtils.getInstance();
	
	@Autowired
	private OrgMapper orgMapper;
	
	@Autowired
	private OrgCompanyService orgCompanyService;
	
	@Autowired
	private JobService jobService;
	
	@Autowired
	private PrivateDataDataService privateDataDataService;
	
	/*@Resource
	private UserUtils userUtils;*/

	/* (non-Javadoc)
	 * @see com.basic.oaas.service.OrgService#createSelective(com.basic.oaas.model.Org)
	 */
	@Override
	public int createSelective(Org org) {
		
		if(logger.isDebugEnabled()){
			logger.debug("createSelective Org:{}",jsonUtils.objectToJson(org));
		}
	
		//User currentUser = userUtils.getUser();
		Long gid = GidClientUtils.getInstance().getGidValue(GidCodes.OAAS_ORG_SEQ);
		org.setOrgId(gid);
		org.setCreateTime(DateUtils.now());
		/*if (currentUser!=null) {
			org.setCreateUserId(currentUser.getUserId());
		}*/
		if (org.getOpenStatus()==null || org.getOpenStatus() == 1) {
			org.setOpenStatus(1);
			org.setOpenDate(new Date());
		}else if(org.getOpenStatus() == 0) {
			org.setStopDate(new Date());
		}
		//获取父部门
		Org parentOrg = orgMapper.selectByPrimaryKey(org.getParentOrgId());
		//系统维护的才生成编码
		if (org.getSource()!=null && org.getSource()==2) {
			Map<String, Object> params = new HashMap<String,Object>();
			params.put("parentId", org.getParentOrgId());
			params.put("source", 2);
			int count = orgMapper.countOrg(params);
			String code = null;
			boolean valid = false;
			while(!valid) {
				if (parentOrg == null) {
					code = "DEP"+new DecimalFormat("00").format(count+1);
				}else {
					code = parentOrg.getOrgCode()+new DecimalFormat("00").format(count+1);
				}
				params = new HashMap<String,Object>();
				params.put("code", code);
				if (orgMapper.countOrg(params)==0) {
					valid = true;
				}else {
					count++;
				}
			}
			org.setOrgCode(code);
		}
		if(parentOrg == null){
			org.setIdPath(gid.toString());
			org.setNamePath(org.getOrgName());
			org.setCodePath(org.getOrgCode());
		}else{
			org.setIdPath(parentOrg.getIdPath()+","+gid.toString());
			org.setNamePath(parentOrg.getNamePath()+","+org.getOrgName());
			org.setCodePath(parentOrg.getCodePath()+","+org.getOrgCode());
		}
		
		if(org.getCompanyId()!= null){
			Long detailId = GidClientUtils.getInstance().getGidValue(GidCodes.OAAS_ORG_COMPANY_SEQ);
			OrgCompany orgCompany = new OrgCompany();
			orgCompany.setDetailId(detailId);
			orgCompany.setCompanyId(org.getCompanyId());
			orgCompany.setOrgId(org.getOrgId());
			orgCompanyService.createSelective(orgCompany);
			//recursionOrgCompany(org,org.getCompanyId());
		}
		int num = orgMapper.insertSelective(org);
		if (num>0) {
			//TODO 插入数据权限数据,数据权限数据表可以做成视图从相关数据源的表中查询，后面可做优化
			PrivateDataData record = new PrivateDataData();
			Long id = GidClientUtils.getInstance().getGidValue(GidCodes.OAAS_PRIV_DATA_DATA_SEQ);
			record.setDataDataId(id);
			record.setDataDataCode(org.getOrgCode());
			record.setDataDataName(org.getOrgName());
			record.setSourceType("table");
			record.setScopeType(PrivateType.SCOPE_TYPE_ORG.getCode());
			record.setSourceId(org.getOrgId());
			//暂时没发现有什么用处，先跟部门一样，免得重新查表
			record.setParentId(org.getParentId());
			privateDataDataService.createSelective(record);
		}
		return num;
	}

	/* (non-Javadoc)
	 * @see com.basic.oaas.service.OrgService#qryOrgById(java.lang.Long)
	 */
	@Override
	public Org qryOrgById(Long orgId) {
		
		if(logger.isDebugEnabled()){
			logger.debug("qryOrgById orgId:{}",orgId);
		}
		
		return orgMapper.selectByPrimaryKey(orgId);
	}

	/* (non-Javadoc)
	 * @see com.basic.oaas.service.OrgService#modifyByPrimaryKey(com.basic.oaas.model.Org)
	 */
	@Override
	public int modifyByPrimaryKey(Org org) {
		
		if(logger.isDebugEnabled()){
			logger.debug("modifyByPrimaryKey org:{}",jsonUtils.objectToJson(org));
		}
		/*User currentUser = userUtils.getUser();
		if (currentUser!=null) {
			org.setModifyUserId(currentUser.getUserId());
		}*/
		org.setModifyTime(DateUtils.now());
		//获取父区域
		Org parentOrg = orgMapper.selectByPrimaryKey(org.getParentOrgId());
		Org orgDb = orgMapper.selectByPrimaryKey(org.getOrgId());
		if (orgDb!=null) {
			//与数据库不一致才修改
			if ((org.getOpenStatus()==null || org.getOpenStatus() == 1)
					&& orgDb.getOpenStatus()==0) {
				org.setOpenStatus(1);
				org.setOpenDate(new Date());
			}else if(org.getOpenStatus()!=null
					&& org.getOpenStatus() == 0 
					&& orgDb.getOpenStatus()==1) {
				org.setStopDate(new Date());
				
			}
		}
		if(parentOrg == null){
			org.setNamePath(org.getOrgName());
			org.setCodePath(org.getOrgCode());
			org.setIdPath(org.getOrgId().toString());
		}else {
			org.setNamePath(parentOrg.getNamePath()+","+org.getOrgName());
			org.setCodePath(parentOrg.getCodePath()+","+org.getOrgCode());
			org.setIdPath(parentOrg.getIdPath()+","+org.getOrgId());
		}
		
		//如果是从同步来的就不更新子节点
		if (org.getSource()!=1) {
			//获取所有subOrg
			List<Org> subOrg = orgMapper.selectSubOrgByPathId(org.getOrgId());
			for (Org c : subOrg) {
				c.setNamePath(org.getNamePath()+","+c.getOrgName());
				c.setCodePath(org.getCodePath()+","+c.getOrgCode());
				c.setIdPath(org.getIdPath()+","+c.getOrgId());
				
				orgMapper.updateByPrimaryKey(c);
			}
			recursionOrgCompany(org,org.getCompanyId());
		}
		int num = orgMapper.updateByPrimaryKey(org);
		if (num>0) {
			//TODO 插入数据权限数据,数据权限数据表可以做成视图从相关数据源的表中查询，后面可做优化
			PrivateDataData data = privateDataDataService.getBySource(org.getOrgId(), PrivateType.SCOPE_TYPE_ORG.getCode());
			if (data!=null) {
				data.setDataDataName(org.getOrgName());
				data.setParentId(org.getParentId());
				privateDataDataService.modifyByPrimaryKeySelective(data);
			}
		}
		
		return num;
	}

	/* (non-Javadoc)
	 * @see com.basic.oaas.service.OrgService#removeByPrimarykey(java.lang.Long)
	 */
	@Override
	public int removeByPrimarykey(Long orgId) {
		
		if(logger.isDebugEnabled()){
			logger.debug("removeByPrimarykey orgId:{}",orgId);
		}
//		return orgMapper.deleteByPrimaryKey(orgId);
		int num = orgMapper.updateAllStateById(orgId);
		//处理关联的岗位,修改为删除状态
		if (num>0) {
			List<Org> orgs = orgMapper.selectSubOrgByPathId(orgId);
			if (BeanUtils.isNotEmpty(orgs)) {
				List<Long> ids = new ArrayList<Long>();
				for (Org org : orgs) {
					JobIbean ibean = new JobIbean();
					ibean.setOrgId(org.getOrgId());
					List<Job> jobs = jobService.qryJobList(ibean);
					if (BeanUtils.isNotEmpty(jobs)) {
						for (Job job : jobs) {
							ids.add(job.getJobId());
						}
						
					}
				}
				if (BeanUtils.isNotEmpty(ids)) {
					jobService.removeBatchByJobIds(ids.toArray(new Long[ids.size()]));
				}
			}
			privateDataDataService.deleteBySource(orgId,PrivateType.SCOPE_TYPE_ORG.getCode());
			
			
		}
		return num;
	}

	/* (non-Javadoc)
	 * @see com.basic.oaas.service.OrgService#qryByUserId(java.lang.Long)
	 */
	@Override
	public List<Org> qryByOrg(OrgIbean iBean) {
		
		if(logger.isDebugEnabled()){
			logger.debug("qryByOrg OrgIBean:{}",jsonUtils.objectToJson(iBean));
		}
		return orgMapper.selectByOrg(iBean);
	}
	@Override
	public List<Org> qryParentOrgId(Long parentOrgId) {
		if(logger.isDebugEnabled()){
			logger.debug("qryOrgByUserId userId:{}",parentOrgId);
		}
		
		return orgMapper.selectByParentOrgId(parentOrgId);
	}

	/* (non-Javadoc)
	 * @see com.basic.oaas.service.OrgService#qrySubOrgById(java.lang.Long)
	 */
	@Override
	public List<Org> qrySubOrgById(Long orgId) {
		
		if(logger.isDebugEnabled()){
			logger.debug("qrySubOrgById orgId:{}",orgId);
		}
		return orgMapper.selectSubOrgById(orgId);
	}

	/* (non-Javadoc)
	 * @see com.basic.oaas.service.OrgService#qryAllSubOrgById(com.basic.oaas.model.Org)
	 */
	@Override
	public List<Org> qryAllSubOrgById(Org org) {
		
		if(logger.isDebugEnabled()){
			logger.debug("qryAllSubOrgById org:{}",jsonUtils.objectToJson(org));
		}
		return orgMapper.selectSubOrgByPathId(org.getOrgId());
	}

	/* (non-Javadoc)
	 * @see com.basic.oaas.service.OrgService#createOrModify(com.basic.oaas.bean.OrgIBean)
	 */
	@Override
	public Org createOrModify(Org org) {
		
		if(logger.isDebugEnabled()){
			logger.debug("createOrModify iBean:{}",jsonUtils.objectToJson(org));
		}
		
		if(org.getOrgId() == null){//新增
			createSelective(org);
		}else{//修改
			modifyByPrimaryKey(org);
		}
		return org;
	}

	/* (non-Javadoc)
	 * @see com.basic.oaas.service.OrgService#qryExistOrgCode(java.lang.String)
	 */
	@Override
	public int qryExistOrgCode(String orgCode) {
		
		if(logger.isDebugEnabled()){
			logger.debug("qryExistOrgCode orgCode:{}",orgCode);
		}
		return orgMapper.selectExistOrgCode(orgCode);
	}

	/* (non-Javadoc)
	 * @see com.basic.oaas.service.OrgService#qryOrgByUserId(java.lang.Long)
	 */
	@Override
	public Org qryOrgByUserId(Long userId) {
		if(logger.isDebugEnabled()){
			logger.debug("qryOrgByUserId userId:{}",userId);
		}
		
		return orgMapper.selectOrgByUserId(userId);
	}


	@Override
	public List<Org> selectSubOrgByPathId(Long orgId) {
		if(logger.isDebugEnabled()){
			logger.debug("selectSubOrgByPathId orgId:{}",orgId);
		}
		return orgMapper.selectSubOrgByPathId(orgId);
	}
	
	
	private void recursionOrgCompany(Org org,Long companyId){
		List<Org> orglist = orgMapper.selectSubOrgById(org.getOrgId());
		if(!Collections3.isEmpty(orglist)){
			for(Org child :orglist){
				recursionOrgCompany(child,companyId);
			}
		}
		if (BeanUtils.isNotEmpty(companyId)) {
			orgCompanyService.removeByOrgId(org.getOrgId());
			Long detailId = GidClientUtils.getInstance().getGidValue(GidCodes.OAAS_ORG_COMPANY_SEQ);
			OrgCompany orgCompany = new OrgCompany();
			orgCompany.setDetailId(detailId);
			orgCompany.setCompanyId(companyId);
			orgCompany.setOrgId(org.getOrgId());
			orgCompanyService.createSelective(orgCompany);
		}
		
		
	}

	@Override
	public List<Org> selectOrgByOrg(Org org) {
		return orgMapper.selectOrgByOrg(org);
	}

	@Override
	public List<Org> query(DefaultQueryFilter queryFilter) {
		queryFilter.setPage(null);
 		return super.query(queryFilter);
	}

	@Override
	public void create(Org entity) {
		this.createOrModify(entity);
	}

	@Override
	public void update(Org entity) {
		this.createOrModify(entity);
	}

	@Override
	public String getNamespace() {
		return OrgMapper.class.getName();
	}
	
	@Override
	public List<Org> selectFindInSet(String value,String column) {
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("value", value);
		params.put("column", column);
		return orgMapper.selectFindInSet(params);
	}
	
	
}