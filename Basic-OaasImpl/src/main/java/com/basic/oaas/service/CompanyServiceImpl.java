/**
 *
 * companyName
 * copyright 2015-2020
 * @date 2017年7月5日 上午10:49:45
 * @author Kevin
 * @Description: 员工实现类
 * 
 */
package com.basic.oaas.service;

import java.text.DecimalFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.basic.framework.common.api.db.domain.PageJson;
import com.basic.framework.common.api.db.domain.PageList;
import com.basic.framework.common.api.query.QueryOP;
import com.basic.framework.common.baseServerImpl.BaseServerImpl;
import com.basic.framework.common.model.DefaultQueryFilter;
import com.basic.framework.common.utils.datatype.DateUtils;
import com.basic.framework.common.utils.datatype.JsonUtils;
import com.basic.framework.gid.utils.sequence.GidClientUtils;
import com.basic.oaas.bean.CompanyIbean;
import com.basic.oaas.dao.CompanyMapper;
import com.basic.oaas.define.GidCodes;
import com.basic.oaas.model.Area;
import com.basic.oaas.model.Company;
import com.basic.oaas.model.Org;

/**
 *
 * @date 2017年7月5日 上午10:49:45
 * @author Kevin
 * @Description: CompanyService
 * 
 */
@Service("companyService")
public class CompanyServiceImpl extends BaseServerImpl<Long, Company> implements CompanyService {

	private Logger logger = LoggerFactory.getLogger(CompanyServiceImpl.class);

	private static final JsonUtils JSON_UTILS = JsonUtils.getInstance();
	
	@Autowired
	private CompanyMapper companyMapper;
	
	/* (non-Javadoc)
	 * @see com.basic.oaas.service.CompanyService#removeByPrimaryKey(java.lang.Long)
	 */
	@Override
	public int removeByPrimaryKey(Long companyId) {
		if (logger.isDebugEnabled()) {
			logger.debug("removeByPrimaryKey companyId:{}", companyId);
		}
		return companyMapper.updateAllStateById(companyId);
	}

	/* (non-Javadoc)
	 * @see com.basic.oaas.service.CompanyService#createSelective(com.basic.oaas.model.Company)
	 */
	@Override
	public int createSelective(Company record) {
		if (logger.isDebugEnabled()) {
			logger.debug("createSelective record:{}", JSON_UTILS.objectToJson(record));
		}
		
		Long gid = GidClientUtils.getInstance().getGidValue(GidCodes.OAAS_COMPANY_SEQ);
		record.setCompanyId(gid);
		record.setCreateTime(DateUtils.now());
		//设置启用状态
		if (record.getOpenStatus()==null || record.getOpenStatus() == 1) {
			record.setOpenStatus(1);
			record.setOpenDate(new Date());
		}else if(record.getOpenStatus() == 0) {
			record.setStopDate(new Date());
		}
		
		//设置路径
		//获取父公司
		Company parent = companyMapper.selectByPrimaryKey(record.getParentId());
		//生成编码
		Map<String, Object> params = new HashMap<String,Object>();
		params.put("parentId", record.getParentId());
		int num = companyMapper.countCompany(params);
		if(parent == null){
			record.setCompanyCode("G"+new DecimalFormat("00").format(num+1));
			record.setIdPath(gid.toString());
			record.setNamePath(record.getAbsName());
			record.setCodePath(record.getCompanyCode());
		}else{
			record.setCompanyCode(parent.getCompanyCode()+new DecimalFormat("00").format(num+1));
			record.setIdPath(parent.getIdPath()+","+gid.toString());
			record.setNamePath(parent.getNamePath()+","+record.getAbsName());
			record.setCodePath(parent.getCodePath()+","+record.getCompanyCode());
		}
		
		return companyMapper.insertSelective(record);
	}
	

	/* (non-Javadoc)
	 * @see com.basic.oaas.service.CompanyService#qryByPrimaryKey(java.lang.Long)
	 */
	@Override
	public Company qryByPrimaryKey(Long companyId) {
		if (logger.isDebugEnabled()) {
			logger.debug("qryByPrimaryKey companyId:{}", companyId);
		}
		return companyMapper.selectByPrimaryKey(companyId);
	}

	/* (non-Javadoc)
	 * @see com.basic.oaas.service.CompanyService#modifyByPrimaryKeySelective(com.basic.oaas.model.Company)
	 */
	@Override
	public int modifyByPrimaryKeySelective(Company record) {
		if (logger.isDebugEnabled()) {
			logger.debug("modifyByPrimaryKeySelective record:{}", JSON_UTILS.objectToJson(record));
		}
		
		record.setModifyTime(DateUtils.now());
		Company companyDb = companyMapper.selectByPrimaryKey(record.getCompanyId());
		if (companyDb!=null) {
			//与数据库不一致才修改
			if ((record.getOpenStatus()==null || record.getOpenStatus() == 1)
					&& companyDb.getOpenStatus()==0) {
				record.setOpenStatus(1);
				record.setOpenDate(new Date());
			}else if(record.getOpenStatus()!=null
					&& record.getOpenStatus() == 0 
					&& companyDb.getOpenStatus()==1) {
				record.setStopDate(new Date());
				
			}
		}
		
		//设置路径
		//获取父公司
		Company parent = companyMapper.selectByPrimaryKey(record.getParentId());
		if(parent == null){
			record.setIdPath(record.getCompanyId().toString());
			record.setNamePath(record.getAbsName());
			record.setCodePath(record.getCompanyCode());
			
		}else{
			record.setIdPath(parent.getIdPath()+","+record.getCompanyId());
			record.setNamePath(parent.getNamePath()+","+record.getAbsName());
			record.setCodePath(parent.getCodePath()+","+record.getCompanyCode());
		}
		//获取所有subArea
		List<Company> subCompany = companyMapper.selectAllSubCompanyById(record);
		for (Company c : subCompany) {
			c.setNamePath(record.getNamePath()+","+c.getAbsName());
			c.setCodePath(record.getCodePath()+","+c.getCompanyCode());
			c.setIdPath(record.getIdPath()+","+c.getCompanyId());
			
			companyMapper.updateByPrimaryKey(c);
		}
		
		return companyMapper.updateByPrimaryKey(record);

	}

	/* (non-Javadoc)
	 * @see com.basic.oaas.service.CompanyService#qryCompanyList(com.basic.oaas.bean.CompanyIbean)
	 */
	@Override
	public List<Company> qryCompanyList(CompanyIbean ibean) {
		if (logger.isDebugEnabled()) {
			logger.debug("qryPrivateCatalogList PrivateCatalogIbean:{}", JSON_UTILS.objectToJson(ibean));
		}
		return companyMapper.selectCompanyList(ibean);
	}

	/* (non-Javadoc)
	 * @see com.basic.oaas.service.CompanyService#qryCompanyListCount(com.basic.oaas.bean.CompanyIbean)
	 */
	@Override
	public long qryCompanyListCount(CompanyIbean ibean) {
		if (logger.isDebugEnabled()) {
			logger.debug("qryPrivateCatalogListCount PrivateCatalogIbean:{}", JSON_UTILS.objectToJson(ibean));
		}
		return companyMapper.selectCompanyListCount(ibean);
	}

	/* (non-Javadoc)
	 * @see com.basic.oaas.service.CompanyService#createOrModify(com.basic.oaas.model.Company)
	 */
	@Override
	public Company createOrModify(Company ibean) {
		if(logger.isDebugEnabled()){
			logger.debug("createOrModify Company:{}", JSON_UTILS.objectToJson(ibean));
		}
		
		if(ibean.getCompanyId() == null){//新增
			createSelective(ibean);
		}else{//修改
			modifyByPrimaryKeySelective(ibean);
		}
		return ibean;
	}

	/* (non-Javadoc)
	 * @see com.basic.oaas.service.CompanyService#qryByUserId(java.lang.Long)
	 */
	@Override
	public List<Company> qryByUserId(Long userId,Long companyId) {
		return companyMapper.selectByUserId(userId,companyId);
	}

	/* (non-Javadoc)
	 * @see com.basic.oaas.service.CompanyService#qryByOrgId(java.lang.Long)
	 */
	@Override
	public Company qryByOrgId(Long orgId) {
		return companyMapper.selectByOrgId(orgId);
	}
	
	@Override
	public PageJson queryPage(DefaultQueryFilter  queryFilter) {
        return  new PageJson(new PageList<>(super.query(queryFilter)));
    }
    
	@Override
    public List<Company> query(DefaultQueryFilter  queryFilter) {
 		queryFilter.setPage(null);
 		return super.query(queryFilter);
 	}

	@Override
	public void create(Company entity) {
		createOrModify(entity);
		
	}

	@Override
	public void update(Company entity) {
		createOrModify(entity);
	}

	@Override
	public String getNamespace() {
		return CompanyMapper.class.getName();
	}


}
