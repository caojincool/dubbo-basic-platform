/**
 *
 * companyName
 * copyright 2015-2020
 * @date 2017年8月8日 上午9:57:38
 * @author Kevin
 * @Description: TODO(这里用一句话描述这个类的作用)
 * 
 */
package com.basic.oaas.service;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.basic.framework.common.api.db.domain.PageJson;
import com.basic.framework.common.api.db.domain.PageList;
import com.basic.framework.common.api.query.QueryFilter;
import com.basic.framework.common.baseServerImpl.BaseServerImpl;
import com.basic.framework.common.model.DefaultQueryFilter;
import com.basic.framework.common.utils.BeanUtils;
import com.basic.framework.common.utils.datatype.DateUtils;
import com.basic.framework.common.utils.datatype.JsonUtils;
import com.basic.framework.gid.utils.sequence.GidClientUtils;
import com.basic.oaas.bean.JobIbean;
import com.basic.oaas.bean.StaffJobIbean;
import com.basic.oaas.dao.JobMapper;
import com.basic.oaas.dao.StaffJobMapper;
import com.basic.oaas.define.GidCodes;
import com.basic.oaas.model.Job;
import com.basic.oaas.model.Role;
import com.basic.oaas.model.StaffJob;

/**
 *
 * @date 2017年8月8日 上午9:57:38
 * @author Kevin
 * @Description: 职位接口实现类
 * 
 */
@Service("jobService")
public class JobServiceImpl extends BaseServerImpl<Long, Job> implements JobService {

	private Logger logger = LoggerFactory.getLogger(JobServiceImpl.class);

	private static final JsonUtils JSON_UTILS = JsonUtils.getInstance();
	
	@Autowired
	private JobMapper jobMapper;
	
	@Autowired
	private StaffJobMapper staffJobMapper;
	
	/* (non-Javadoc)
	 * @see com.basic.oaas.service.JobService#removeByPrimaryKey(java.lang.Long)
	 */
	@Override
	public int removeByPrimaryKey(Long jobId) {
		if (logger.isDebugEnabled()) {
			logger.debug("removeByPrimaryKey catalogId:{}", jobId);
		}
		int num = jobMapper.deleteByPrimaryKey(jobId);
		if (num>0) {
			StaffJobIbean ibean = new StaffJobIbean();
			ibean.setJobId(jobId);
			List<StaffJob> list = staffJobMapper.selectStaffJobList(ibean);
			if (BeanUtils.isNotEmpty(list)) {
				List<Long> staffJobIds = new ArrayList<Long>();
				for (StaffJob sf : list) {
					staffJobIds.add(sf.getStaffJobId());
				}
				staffJobMapper.deleteBatchByStaffJobIds(staffJobIds.toArray(new Long[staffJobIds.size()]));
			}
		}
		return num;
	}

	/* (non-Javadoc)
	 * @see com.basic.oaas.service.JobService#createSelective(com.basic.oaas.model.Job)
	 */
	@Override
	public int createSelective(Job record) {
		if (logger.isDebugEnabled()) {
			logger.debug("createSelective record:{}", JSON_UTILS.objectToJson(record));
		}
		
		Long gid = GidClientUtils.getInstance().getGidValue(GidCodes.OAAS_JOB_SEQ);
		record.setJobId(gid);
		record.setCreateTime(DateUtils.now());
		
		//通过系统维护的才生成编码
		if (record.getSource()!=null && record.getSource()==2) {
			String code = null;
			boolean valid = false;
			Map<String, Object> params = new HashMap<String,Object>();
			params.put("source", 2);
			int count = jobMapper.countJob(params);
			while(!valid) {
				code = "B"+new DecimalFormat("000").format(count+1);
				params = new HashMap<String,Object>();
				params.put("code", code);
				if (jobMapper.countJob(params)==0) {
					valid = true;
				}else {
					count++;
				}
			}
			record.setJobCode(code);
		}
		return jobMapper.insertSelective(record);
	}

	/* (non-Javadoc)
	 * @see com.basic.oaas.service.JobService#qryByPrimaryKey(java.lang.Long)
	 */
	@Override
	public Job qryByPrimaryKey(Long jobId) {
		if (logger.isDebugEnabled()) {
			logger.debug("qryByPrimaryKey jobId:{}", jobId);
		}
		return jobMapper.selectByPrimaryKey(jobId);
	}

	/* (non-Javadoc)
	 * @see com.basic.oaas.service.JobService#modifyByPrimaryKeySelective(com.basic.oaas.model.Job)
	 */
	@Override
	public int modifyByPrimaryKeySelective(Job record) {
		if (logger.isDebugEnabled()) {
			logger.debug("modifyByPrimaryKeySelective record:{}", JSON_UTILS.objectToJson(record));
		}
		record.setModifyTime(DateUtils.now());
		return jobMapper.updateByPrimaryKey(record);

	}

	/* (non-Javadoc)
	 * @see com.basic.oaas.service.JobService#qryJobList(com.basic.oaas.bean.JobIbean)
	 */
	@Override
	public List<Job> qryJobList(JobIbean ibean) {
		if (logger.isDebugEnabled()) {
			logger.debug("qryPrivateCatalogList PrivateCatalogIbean:{}", JSON_UTILS.objectToJson(ibean));
		}
		return jobMapper.selectJobList(ibean);
	}

	/* (non-Javadoc)
	 * @see com.basic.oaas.service.JobService#qryJobListCount(com.basic.oaas.bean.JobIbean)
	 */
	@Override
	public long qryJobListCount(JobIbean ibean) {
		if (logger.isDebugEnabled()) {
			logger.debug("qryPrivateCatalogListCount PrivateCatalogIbean:{}", JSON_UTILS.objectToJson(ibean));
		}
		return jobMapper.selectJobListCount(ibean);
	}

	/* (non-Javadoc)
	 * @see com.basic.oaas.service.JobService#createOrModify(com.basic.oaas.model.Job)
	 */
	@Override
	public void createOrModify(Job ibean) {
		if(logger.isDebugEnabled()){
			logger.debug("createOrModify Job:{}", JSON_UTILS.objectToJson(ibean));
		}
		
		if(ibean.getJobId() == null){//新增
			createSelective(ibean);
		}else{//修改
			modifyByPrimaryKeySelective(ibean);
		}
	}

	/* (non-Javadoc)
	 * @see com.basic.oaas.service.JobService#removeBatchByjobIds(java.lang.Long[])
	 */
	@Override
	public int removeBatchByJobIds(Long[] jobIds) {
		if(logger.isDebugEnabled()){
			logger.debug("removeBatchByJobIds jobIds:{}", JSON_UTILS.objectToJson(jobIds));
		}
		
		int num = jobMapper.updateBatchByJobIds(jobIds);
		if (num>0) {
			//处理岗位员工关系
			for (int i = 0; i < jobIds.length; i++) {
				StaffJobIbean ibean = new StaffJobIbean();
				ibean.setJobId(jobIds[i]);
				List<StaffJob> list = staffJobMapper.selectStaffJobList(ibean);
				if (BeanUtils.isNotEmpty(list)) {
					List<Long> staffJobIds = new ArrayList<Long>();
					for (StaffJob sf : list) {
						staffJobIds.add(sf.getStaffJobId());
					}
					staffJobMapper.deleteBatchByStaffJobIds(staffJobIds.toArray(new Long[staffJobIds.size()]));
				}
			}
		}
		return num;
	}

	/* (non-Javadoc)
	 * @see com.basic.oaas.service.JobService#qryExistJobCode(java.lang.String)
	 */
	@Override
	public int qryExistJobCode(String jobCode) {
		if(logger.isDebugEnabled()){
			logger.debug("qryExistJobCode jobCode:{}", jobCode);
		}
		return jobMapper.selectExistJobCode(jobCode);
	}

	/* (non-Javadoc)
	 * @see com.basic.oaas.service.JobService#qryDefaultJobByUserId(java.lang.Long)
	 */
	@Override
	public Job qryDefaultJobByUserId(Long userId) {
		if(logger.isDebugEnabled()){
			logger.debug("qryDefaultJobByUserId userId:{}", userId);
		}
		
		
		return jobMapper.selectDefaultJobByUserId(userId);
	}

	/* (non-Javadoc)
	 * @see com.basic.oaas.service.JobService#qryJobByUserId(java.lang.Long)
	 */
	@Override
	public List<Job> qryJobByUserId(Long userId) {
		
		if(logger.isDebugEnabled()){
			logger.debug("qryJobByUserId userId:{}", userId);
		}
		return jobMapper.selectJobByUserId(userId);
	}

	/* (non-Javadoc)
	 * @see com.basic.oaas.service.JobService#qryJobByUserIdCompany(java.lang.Long, java.lang.Long)
	 */
	@Override
	public List<Job> qryJobByUserIdCompany(Long userId, Long companyId) {
		return jobMapper.selectJobByUserIdCompany(userId,companyId);
	}

	@Override
	public List<Job> selectJobAndOrgByUserId(Long partyId) {
		if (logger.isDebugEnabled()) {
			logger.debug("selectJobAndOrgByUserId Long:{}", partyId);
		}
		return jobMapper.selectJobAndOrgByUserId(partyId);
	}
	
	@Override
	public PageJson queryPage(DefaultQueryFilter  queryFilter) {
        return  new PageJson(new PageList<>(super.query(queryFilter)));
    }
    
	@Override
    public List<Job> query(DefaultQueryFilter  queryFilter) {
 		queryFilter.setPage(null);
 		return super.query(queryFilter);
 	}

	@Override
	public void create(Job entity) {
		this.createOrModify(entity);
	}

	@Override
	public void update(Job entity) {
		this.createOrModify(entity);
	}

	@Override
	public String getNamespace() {
		return JobMapper.class.getName();
	}
	
	@Override
	public List<Job> queryJobOrgUserRels(Map<String,Object> params) {
		return jobMapper.queryJobOrgUserRels(params);
	}


}
