package com.basic.framework.web.impl;

import com.basic.framework.common.utils.datatype.JsonUtils;
import com.basic.framework.security.SecurityCommon;
import com.basic.framework.web.api.UserDetailService;
import com.basic.oaas.model.*;
import com.basic.oaas.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 *
 * @date 2017年6月28日 下午4:32:41
 * 
 * @Description: 用户详细信息
 *
 */
@Service("userDetailService")
public class UserDetailServiceImpl implements UserDetailService {
    
	private Logger logger = LoggerFactory.getLogger(UserDetailServiceImpl.class);

	private static final JsonUtils JSON_UTILS = JsonUtils.getInstance();
	
	@Autowired
	private UserService userService;
	@Autowired
	private StaffService staffService;
	@Autowired
	private JobService jobService;
	@Autowired
	private CompanyService companyService;
	@Autowired
	private PrivateMenuService privateMenuService;
	
	/* (non-Javadoc)
	 * @see com.basic.framework.web.api.UserDetailService#qryUserDetail(java.lang.Long, java.lang.Long)
	 */
	@Override
	public UserDetail qryUserDetail(Long userId, Long companyId) {
		if(logger.isDebugEnabled()){
			logger.debug("qryUserDetail userId:{}", userId);
		}
		
		UserDetail userDetail =  new UserDetail();
		User user = userService.getById(userId);
		if(user != null ){
			BeanUtils.copyProperties(user, userDetail);
			/**
			 * 员工
			 */
			Staff staff = staffService.qryStaffByUserId(userId);
			if(staff != null){
				userDetail.setStaffId(staff.getStaffId());
				userDetail.setStaffNumber(staff.getStaffNumber());
				userDetail.setStaffName(staff.getStaffName());
				userDetail.setStaffIcon(staff.getStaffIcon());
				userDetail.setSex(staff.getSex());
				userDetail.setWechat(staff.getWechat());
				userDetail.setQq(staff.getQq());
				userDetail.setTelephone(staff.getTelephone());
				userDetail.setEmail(staff.getEmail());
			}
			
			
			List<Job> jobList = jobService.qryJobByUserIdCompany(userId,companyId);
			
			if(jobList != null){
				
				List<JobOrgArea> list = new ArrayList<JobOrgArea>();
				Job defaultJob = null;
				for (Job job : jobList) {
					if (companyId!=null) {
						defaultJob = jobList.get(0);
					}
					if (job.getDefaultJob()!=null && job.getDefaultJob()==0) {
						JobOrgArea jobOrgArea = new JobOrgArea();
						
						jobOrgArea.setJobId(job.getJobId());
						jobOrgArea.setJobCode(job.getJobCode());
						jobOrgArea.setJobName(job.getJobName());
						jobOrgArea.setDefaultJob(job.getDefaultJob());
						
						jobOrgArea.setOrgId(job.getOrgId());
						jobOrgArea.setOrgCode(job.getOrgCode());
						jobOrgArea.setOrgName(job.getOrgName());
						jobOrgArea.setOrgCodePath(job.getOrgCodePath());
						jobOrgArea.setOrgNamePath(job.getOrgNamePath());
						
						jobOrgArea.setAreaId(job.getAreaId());
						jobOrgArea.setAreaCode(job.getAreaCode());
						jobOrgArea.setAreaName(job.getAreaName());
						jobOrgArea.setAreaCodePath(job.getAreaCodePath());
						jobOrgArea.setAreaNamePath(job.getAreaNamePath());
						
						list.add(jobOrgArea);
					}else {
						defaultJob = job;
					}
				}
				userDetail.setJobOrgAreas(list);
				if (defaultJob!=null) {
					//设置主岗
					userDetail.setJobId(defaultJob.getJobId());
					userDetail.setJobCode(defaultJob.getJobCode());
					userDetail.setJobName(defaultJob.getJobName());
					
					userDetail.setOrgId(defaultJob.getOrgId());
					Company company = companyService.qryByOrgId(defaultJob.getOrgId());
					userDetail.setCompany(company);
					userDetail.setOrgCode(defaultJob.getOrgCode());
					userDetail.setOrgName(defaultJob.getOrgName());
					userDetail.setOrgCodePath(defaultJob.getOrgCodePath());
					userDetail.setOrgNamePath(defaultJob.getOrgNamePath());
					userDetail.setParentOrgId(defaultJob.getParentOrgId());
					
					userDetail.setAreaId(defaultJob.getAreaId());
					userDetail.setAreaCode(defaultJob.getAreaCode());
					userDetail.setAreaName(defaultJob.getAreaName());
					userDetail.setAreaCodePath(defaultJob.getAreaCodePath());
					userDetail.setAreaNamePath(defaultJob.getAreaNamePath());
					userDetail.setParentAreaId(defaultJob.getParentAreaId());
				}
			}
			
			/**
			 * 获取菜单权限
			 */
			//userDetail.setPrivateMenus(privateMenuService.getPrivateMenus(userId,null));
			
		}
		
		if(logger.isDebugEnabled()){
			logger.debug("qryUserDetail userDetail:{}", JSON_UTILS.objectToJson(userDetail));
		}
		return userDetail;
	}

	/* (non-Javadoc)
	 * @see com.basic.framework.web.api.UserDetailService#qryUserDetailByRequest(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public UserDetail qryUserDetailByRequest(HttpServletRequest request) {
		if(logger.isDebugEnabled()){
			logger.debug("qryUserDetailByRequest request:{}");
		}
		
		logger.debug("getSession sessionKey:{}",SecurityCommon.SESSION_USER_KEY);
		HttpSession session = request.getSession();		

		//userSession的信息在用户登录成功后获取并加入HttpSession
		Object sessionObj = session.getAttribute(SecurityCommon.SESSION_USER_KEY);
		if(logger.isDebugEnabled()){
			logger.debug("getSession sessionKey:{},sessionObj:{}",SecurityCommon.SESSION_USER_KEY,JSON_UTILS.objectToJson(sessionObj));
		}
		if(null == sessionObj){//这种情况应该是免登录的情况
			
			logger.debug("重新设置自定义session");
			
//			SecurityContext springSecurityContext = (SecurityContext) session.getAttribute("SPRING_SECURITY_CONTEXT");
			UserDetail userDetail = null;
//			if(springSecurityContext != null){
////				MyUserDetail  userDetail  = (MyUserDetail) springSecurityContext.getAuthentication().getPrincipal();
//				com.basic.framework.security.api.User  user = sessionManager.getSessionUser(session);
//				request.getSession().setAttribute(SecurityCommon.SESSION_USER_KEY,user);	
//				
//				userDetail = new UserDetail();
//				userDetail.setUserId(user.getUserId());
//				userDetail.setUserName(user.getUserAccount());
//				userDetail.setUserText(user.getUserName());
//			}
			
			return userDetail;			
		}else{
			UserDetail userDetail = new UserDetail();
			com.basic.framework.security.api.User user =  (com.basic.framework.security.api.User) sessionObj;
			userDetail.setUserId(user.getUserId());
			userDetail.setUsername(user.getUserAccount());
			userDetail.setUserText(user.getUserName());
			
			return userDetail ;
		}
		
	}

}
