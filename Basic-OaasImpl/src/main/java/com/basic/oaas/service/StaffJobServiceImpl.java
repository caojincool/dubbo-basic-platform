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

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.basic.framework.common.baseServerImpl.BaseServerImpl;
import com.basic.framework.common.model.DefaultQueryFilter;
import com.basic.framework.common.utils.datatype.JsonUtils;
import com.basic.framework.gid.utils.sequence.GidClientUtils;
import com.basic.oaas.bean.StaffJobIbean;
import com.basic.oaas.dao.StaffJobMapper;
import com.basic.oaas.define.GidCodes;
import com.basic.oaas.model.Org;
import com.basic.oaas.model.StaffJob;

/**
 *
 * @date 2017年7月5日 上午10:49:45
 * @author Kevin
 * @Description: StaffJobService
 * 
 */
@Service("staffJobService")
public class StaffJobServiceImpl extends BaseServerImpl<Long, StaffJob> implements StaffJobService {

	private Logger logger = LoggerFactory.getLogger(StaffJobServiceImpl.class);

	private static final JsonUtils JSON_UTILS = JsonUtils.getInstance();
	
	
	@Autowired
	private StaffJobMapper staffJobMapper;
	/* (non-Javadoc)
	 * @see com.basic.oaas.service.StaffJobService#removeByPrimaryKey(java.lang.Long)
	 */
	@Override
	public int removeByPrimaryKey(Long staffJobId) {
		if (logger.isDebugEnabled()) {
			logger.debug("removeByPrimaryKey staffJobId:{}", staffJobId);
		}
		return staffJobMapper.deleteByPrimaryKey(staffJobId);
	}

	/* (non-Javadoc)
	 * @see com.basic.oaas.service.StaffJobService#createSelective(com.basic.oaas.model.StaffJob)
	 */
	@Override
	public int createSelective(StaffJob record) {
		if (logger.isDebugEnabled()) {
			logger.debug("createSelective record:{}", JSON_UTILS.objectToJson(record));
		}
		
		Long gid = GidClientUtils.getInstance().getGidValue(GidCodes.OAAS_STAFF_JOB_SEQ);
		record.setStaffJobId(gid);
		return staffJobMapper.insertSelective(record);
	}

	/* (non-Javadoc)
	 * @see com.basic.oaas.service.StaffJobService#qryByPrimaryKey(java.lang.Long)
	 */
	@Override
	public StaffJob qryByPrimaryKey(Long staffJobId) {
		if (logger.isDebugEnabled()) {
			logger.debug("qryByPrimaryKey staffJobId:{}", staffJobId);
		}
		return staffJobMapper.selectByPrimaryKey(staffJobId);
	}

	/* (non-Javadoc)
	 * @see com.basic.oaas.service.StaffJobService#modifyByPrimaryKeySelective(com.basic.oaas.model.StaffJob)
	 */
	@Override
	public int modifyByPrimaryKeySelective(StaffJob record) {
		if (logger.isDebugEnabled()) {
			logger.debug("modifyByPrimaryKeySelective record:{}", JSON_UTILS.objectToJson(record));
		}
		return staffJobMapper.updateByPrimaryKey(record);

	}

	/* (non-Javadoc)
	 * @see com.basic.oaas.service.StaffJobService#qryStaffJobList(com.basic.oaas.bean.StaffJobIbean)
	 */
	@Override
	public List<StaffJob> qryStaffJobList(StaffJobIbean ibean) {
		if (logger.isDebugEnabled()) {
			logger.debug("qryStaffJobList StaffJobIbean:{}", JSON_UTILS.objectToJson(ibean));
		}
		return staffJobMapper.selectStaffJobList(ibean);
	}
	
	/* (non-Javadoc)
	 * @see com.basic.oaas.service.StaffJobService#qryStaffJobListClount(com.basic.oaas.bean.StaffJobIbean)
	 */
	@Override
	public long qryStaffJobListCount(StaffJobIbean ibean) {
		if (logger.isDebugEnabled()) {
			logger.debug("qryStaffJobListCount StaffJobIbean:{}", JSON_UTILS.objectToJson(ibean));
		}
		return staffJobMapper.selectStaffJobListCount(ibean);
	}

	/* (non-Javadoc)
	 * @see com.basic.oaas.service.StaffJobService#createOrModify(com.basic.oaas.model.StaffJob)
	 */
	@Override
	public void createOrModify(StaffJob ibean) {
		if(logger.isDebugEnabled()){
			logger.debug("createOrModify StaffJob:{}", JSON_UTILS.objectToJson(ibean));
		}
		
		if(ibean.getStaffJobId() == null){//新增
			createSelective(ibean);
		}else{//修改
			modifyByPrimaryKeySelective(ibean);
		}
	}

	/* (non-Javadoc)
	 * @see com.basic.oaas.service.StaffJobService#removeByStaffDefaultJob(java.lang.Long)
	 */
	@Override
	public int removeByStaffDefaultJob(Long staffId) {
		
		if(logger.isDebugEnabled()){
			logger.debug("removeByStaffDefaultJob staffId:{}", staffId);
		}
		
		return staffJobMapper.deleteByStaffDefaultJob(staffId);
	}

	/* (non-Javadoc)
	 * @see com.basic.oaas.service.StaffJobService#removeBatchByStaffJobIds(java.lang.Long[])
	 */
	@Override
	public int removeBatchByStaffJobIds(Long[] staffJobIds) {
		if(logger.isDebugEnabled()){
			logger.debug("removeBatchByStaffJobIds staffJobIds:{}", JSON_UTILS.objectToJson(staffJobIds));
		}
		return staffJobMapper.deleteBatchByStaffJobIds(staffJobIds);
	}

	/* (non-Javadoc)
	 * @see com.basic.oaas.service.StaffJobService#qryExistStaffJob(com.basic.oaas.bean.StaffJobIbean)
	 */
	@Override
	public int qryExistStaffJob(StaffJobIbean ibean) {
		
		if(logger.isDebugEnabled()){
			logger.debug("qryExistStaffJob StaffJobIbean:{}", JSON_UTILS.objectToJson(ibean));
		}
		return staffJobMapper.selectExistStaffJob(ibean) ;
	}

	@Override
	public int deleteByStaff(Long staffId) {
		return staffJobMapper.deleteByStaff(staffId);
	}

	@Override
	public void create(StaffJob entity) {
		this.createOrModify(entity);
	}

	@Override
	public void update(StaffJob entity) {
		this.createOrModify(entity);
		
	}

	@Override
	public String getNamespace() {
		return StaffJobMapper.class.getName();
	}
	
	@Override
	public List<StaffJob> query(DefaultQueryFilter queryFilter) {
		queryFilter.setPage(null);
 		return super.query(queryFilter);
	}

	




}
