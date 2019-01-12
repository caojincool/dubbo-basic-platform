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
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.basic.framework.common.api.db.domain.PageJson;
import com.basic.framework.common.api.db.domain.PageList;
import com.basic.framework.common.api.query.QueryFilter;
import com.basic.framework.common.baseServerImpl.BaseServerImpl;
import com.basic.framework.common.model.DefaultQueryFilter;
import com.basic.framework.common.utils.BeanUtils;
import com.basic.framework.common.utils.datatype.DateUtils;
import com.basic.framework.common.utils.datatype.JsonUtils;
import com.basic.framework.gid.utils.sequence.GidClientUtils;
import com.basic.oaas.bean.StaffIbean;
import com.basic.oaas.dao.StaffMapper;
import com.basic.oaas.dao.UserMapper;
import com.basic.oaas.dao.UserStaffMapper;
import com.basic.oaas.define.GidCodes;
import com.basic.oaas.model.Org;
import com.basic.oaas.model.Staff;
import com.basic.oaas.model.StaffJob;
import com.basic.oaas.model.User;
import com.basic.oaas.model.UserStaff;

/**
 *
 * @date 2017年7月5日 上午10:49:45
 * @author Kevin
 * @Description: StaffService
 * 
 */
@SuppressWarnings("unchecked")
@Service("staffService")
public class StaffServiceImpl extends BaseServerImpl<Long, Staff> implements StaffService {

	private Logger logger = LoggerFactory.getLogger(StaffServiceImpl.class);

	private static final JsonUtils JSON_UTILS = JsonUtils.getInstance();
	
	@Autowired
	private StaffMapper staffMapper;
	
	@Autowired
	private StaffJobService staffJobService ;
	
	@Autowired
	private UserStaffMapper userStaffMapper;
	
	@Autowired
	private UserMapper userMapper;
	/* (non-Javadoc)
	 * @see com.basic.oaas.service.StaffService#removeByPrimaryKey(java.lang.Long)
	 */
	@Override
	public int removeByPrimaryKey(Long staffId) {
		if (logger.isDebugEnabled()) {
			logger.debug("removeByPrimaryKey staffId:{}", staffId);
		}
		int num = staffMapper.deleteByPrimaryKey(staffId);
		
		if (num>0) {
			//删除员工对应帐号
			List<User> users = userMapper.selectByStaffId(staffId);
			if (BeanUtils.isNotEmpty(users)) {
				List<Long> userIds = new ArrayList<Long>();
				for (User user : users) {
					userIds.add(user.getUserId());
				}
				userMapper.updateBatchByUserIds(userIds.toArray(new Long[userIds.size()]));
			}
			
			//关联删除帐号员工关系
			UserStaff example = new UserStaff();
			example.setStaffId(staffId);
			userStaffMapper.deleteByUser(example);
		}
		return num;
	}

	/* (non-Javadoc)
	 * @see com.basic.oaas.service.StaffService#createSelective(com.basic.oaas.model.Staff)
	 */
	@Override
	public int createSelective(Staff record) {
		if (logger.isDebugEnabled()) {
			logger.debug("createSelective record:{}", JSON_UTILS.objectToJson(record));
		}
		
		Long gid = GidClientUtils.getInstance().getGidValue(GidCodes.OAAS_STAFF_SEQ);
		record.setStaffId(gid);
		record.setCreateTime(DateUtils.now());
		if (record.getOpenStatus()==null || record.getOpenStatus()==1) {
			record.setOpenStatus(1);
			record.setOpenDate(new Date());
		}else if(record.getOpenStatus()==0) {
			record.setStopDate(new Date());
		}
		handleRelations(record, true);
		
		//通过系统维护的才生成编码
		if (record.getSource()!=null && record.getSource()==2) {
			String number = null;
			boolean valid = false;
			Map<String, Object> params = new HashMap<String,Object>();
			params.put("source", 2);
			int count = staffMapper.countStaff(params);
			while(!valid) {
				number = "P"+new DecimalFormat("0000").format(count+1);
				params = new HashMap<String,Object>();
				params.put("number", number);
				if (staffMapper.countStaff(params)==0) {
					valid = true;
				}else {
					count++;
				}
			}
			record.setStaffNumber(number);
		}
		return staffMapper.insertSelective(record);
	}

	/* (non-Javadoc)
	 * @see com.basic.oaas.service.StaffService#qryByPrimaryKey(java.lang.Long)
	 */
	@Override
	public Staff qryByPrimaryKey(Long staffId) {
		if (logger.isDebugEnabled()) {
			logger.debug("qryByPrimaryKey staffId:{}", staffId);
		}
		return staffMapper.selectByPrimaryKey(staffId);
	}

	/* (non-Javadoc)
	 * @see com.basic.oaas.service.StaffService#modifyByPrimaryKeySelective(com.basic.oaas.model.Staff)
	 */
	@Override
	public int modifyByPrimaryKeySelective(Staff record) {
		if (logger.isDebugEnabled()) {
			logger.debug("modifyByPrimaryKeySelective record:{}", JSON_UTILS.objectToJson(record));
		}
		record.setModifyTime(DateUtils.now());
		Staff staffDb = staffMapper.selectByPrimaryKey(record.getStaffId());
		if (staffDb!=null) {
			if ((record.getOpenStatus()==null || record.getOpenStatus()==1)
					&& staffDb.getOpenStatus()==0) {
				record.setOpenStatus(1);
				record.setOpenDate(new Date());
			}else if(record.getOpenStatus()!=null
					&& record.getOpenStatus()==0
					&& staffDb.getOpenStatus()==1) {
				record.setStopDate(new Date());
			}
		}
		handleRelations(record, false);
		
		return staffMapper.updateByPrimaryKey(record);

	}
	
	private void handleRelations(Staff staff,boolean isCreate) {
		//先删除旧的关联再建立新的
		if (!isCreate) {
			//删除员工帐号关系
			UserStaff example = new UserStaff();
			example.setStaffId(staff.getStaffId());
			userStaffMapper.deleteByUser(example);
			//删除员工岗位关系
			staffJobService.deleteByStaff(staff.getStaffId());
		}
		//处理员工帐号关系
		if (BeanUtils.isNotEmpty(staff.getUserStaffs())) {
			for (UserStaff us : staff.getUserStaffs()) {
				us.setStaffId(staff.getStaffId());
				us.setType(0);
				us.setSource(2);
				userStaffMapper.insertSelective(us);
			}
		}
		//处理员工岗位关系
		if (BeanUtils.isNotEmpty(staff.getStaffJobs())) {
			for (StaffJob sj : staff.getStaffJobs()) {
				sj.setStaffId(staff.getStaffId());
				sj.setSource(2);
				staffJobService.createSelective(sj);
			}
		}
	}

	/* (non-Javadoc)
	 * @see com.basic.oaas.service.StaffService#qryStaffList(com.basic.oaas.bean.StaffIbean)
	 */
	@Override
	public List<Staff> qryStaffList(StaffIbean ibean) {
		if (logger.isDebugEnabled()) {
			logger.debug("qryPrivateCatalogList PrivateCatalogIbean:{}", JSON_UTILS.objectToJson(ibean));
		}
		return staffMapper.selectStaffList(ibean);
	}

	/* (non-Javadoc)
	 * @see com.basic.oaas.service.StaffService#qryStaffListCount(com.basic.oaas.bean.StaffIbean)
	 */
	@Override
	public long qryStaffListCount(StaffIbean ibean) {
		if (logger.isDebugEnabled()) {
			logger.debug("qryPrivateCatalogListCount PrivateCatalogIbean:{}", JSON_UTILS.objectToJson(ibean));
		}
		return staffMapper.selectStaffListCount(ibean);
	}

	/* (non-Javadoc)
	 * @see com.basic.oaas.service.StaffService#createOrModify(com.basic.oaas.model.Staff)
	 */
	@Override
	public void createOrModify(Staff ibean) {
		if(logger.isDebugEnabled()){
			logger.debug("createOrModify Staff:{}", JSON_UTILS.objectToJson(ibean));
		}
		
		if(ibean.getStaffId() == null){//新增
			createSelective(ibean);
		}else{//修改
			modifyByPrimaryKeySelective(ibean);
		}
	}

	/* (non-Javadoc)
	 * @see com.basic.oaas.service.StaffService#removeBatchBystaffIds(java.lang.Long[])
	 */
	@Override
	public int removeBatchByStaffIds(Long[] staffIds) {
		if(logger.isDebugEnabled()){
			logger.debug("removeBatchByStaffIds staffIds:{}", JSON_UTILS.objectToJson(staffIds));
		}
		int num = staffMapper.updateBatchByStaffIds(staffIds);
		//关联删除帐号员工关系
		if (num>0) {
			for (int i = 0; i < staffIds.length; i++) {
				//删除员工帐号关系
				UserStaff example = new UserStaff();
				example.setStaffId(staffIds[i]);
				userStaffMapper.deleteByUser(example);
				List<User> users = userMapper.selectByStaffId(staffIds[i]);
				//删除对应帐号
				if (BeanUtils.isNotEmpty(users)) {
					List<Long> userIds = new ArrayList<Long>();
					for (User user : users) {
						userIds.add(user.getUserId());
					}
					userMapper.updateBatchByUserIds(userIds.toArray(new Long[userIds.size()]));
				}
				//删除员工岗位关系
				staffJobService.deleteByStaff(staffIds[i]);
			}
		}
		return num;
	}

	/* (non-Javadoc)
	 * @see com.basic.oaas.service.StaffService#qryExistStaffCode(java.lang.String)
	 */
	@Override
	public int qryExistStaffNumber(String jobCode) {
		if(logger.isDebugEnabled()){
			logger.debug("qryExistStaffCode jobCode:{}", jobCode);
		}
		return staffMapper.selectExistStaffNumber(jobCode);
	}

	/* (non-Javadoc)
	 * @see com.basic.oaas.service.StaffService#qryStaffByUserId(java.lang.Long)
	 */
	@Override
	public Staff qryStaffByUserId(Long userId) {
		
		if(logger.isDebugEnabled()){
			logger.debug("qryStaffByUserId userId:{}", userId);
		}
		
		return staffMapper.selectStaffByUserId(userId);
	}

	@Override
	public void create(Staff entity) {
		this.createOrModify(entity);
	}

	@Override
	public void update(Staff entity) {
		this.createOrModify(entity);
		
	}

	@Override
	public String getNamespace() {
		return StaffMapper.class.getName();
	}

	@Override
	public PageJson queryPage(DefaultQueryFilter queryFilter) {
		return new PageJson(new PageList<>(super.query(queryFilter)));
	}
	
	@Override
	public List<Staff> query(DefaultQueryFilter queryFilter) {
		queryFilter.setPage(null);
 		return super.query(queryFilter);
	}

	@Override
	public List<Staff> queryAccountByOrgIds(Map<String,Object> params) {
		return staffMapper.queryAccountByOrgIds(params);
	}
	
	@Override
	public PageJson queryUserByOrgIds(String sqlKey,QueryFilter queryFilter) {
		return new PageJson(new PageList<>(super.getByQueryFilter(sqlKey, queryFilter)));
	}

}
