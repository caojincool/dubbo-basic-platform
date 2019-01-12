package com.basic.oaas.controller;

import com.alibaba.fastjson.JSONObject;
import com.basic.framework.common.api.db.domain.PageJson;
import com.basic.framework.common.api.query.QueryOP;
import com.basic.framework.common.model.DefaultQueryFilter;
import com.basic.framework.common.utils.result.HttpEntity;
import com.basic.framework.common.utils.result.HttpStatus;
import com.basic.framework.security.jwt.utils.UserUtils;
import com.basic.framework.web.BaseController;
import com.basic.oaas.bean.RoleIbean;
import com.basic.oaas.bean.StaffJobIbean;
import com.basic.oaas.bean.UserStaffIbean;
import com.basic.oaas.model.Role;
import com.basic.oaas.model.Staff;
import com.basic.oaas.model.StaffJob;
import com.basic.oaas.model.User;
import com.basic.oaas.model.UserStaff;
import com.basic.oaas.service.RoleService;
import com.basic.oaas.service.StaffJobService;
import com.basic.oaas.service.StaffService;
import com.basic.oaas.service.UserStaffService;
import io.jsonwebtoken.lang.Assert;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.basic.framework.common.utils.BeanUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * <pre>
 * 描述：员工信息 控制器类
 * 构建组：basic.erp
 * 作者:lengzj
 * 邮箱:mail@com
 * 日期:2018-06-19 09:55:02
 * 版权：companyName
 * </pre>
 */
@Controller
@RequestMapping("/basic.oaas/staff/")
public class StaffController extends BaseController{

	private Logger logger = LoggerFactory.getLogger(StaffController.class);

	@Autowired
	StaffService staffService;
	
	@Autowired
	UserStaffService userStaffService;
	
	@Autowired
	StaffJobService staffJobService;
	
	@Autowired
	RoleService roleService;
	
	@Autowired
	UserUtils userUtils;

	/**
	 * 员工列表(分页条件查询)数据
	 * @param jsonObject
	 * @return
	 * @throws Exception
	 * HttpEntity
	 * @exception
	 */
	@RequestMapping("queryPage")
	@ResponseBody
    public HttpEntity listPageJson(@RequestBody JSONObject jsonObject){
        try{
        	DefaultQueryFilter queryFilter =getQueryFilter(jsonObject);
        	String orgId = jsonObject.getString("orgId");
        	if (StringUtils.isNotEmpty(orgId)) {
        		queryFilter.addFilter("ORG.ORG_ID", orgId, QueryOP.EQUAL);
        	}
        	String staffName = jsonObject.getString("staffName");
        	if (BeanUtils.isNotEmpty(staffName)) {
        		queryFilter.addFilter("STAFF.STAFF_NAME", staffName, QueryOP.LIKE);
			}
        	String staffNumber = jsonObject.getString("staffNumber");
        	if (BeanUtils.isNotEmpty(staffNumber)) {
        		queryFilter.addFilter("STAFF.STAFF_NUMBER", staffNumber, QueryOP.LIKE);
			}
        	String userName = jsonObject.getString("userName");
        	if (BeanUtils.isNotEmpty(userName)) {
        		queryFilter.addFilter("U.USERNAME", userName, QueryOP.LIKE);
        	}
        	String telephone = jsonObject.getString("telephone");
        	if (BeanUtils.isNotEmpty(telephone)) {
        		queryFilter.addFilter("STAFF.TELEPHONE", telephone, QueryOP.EQUAL);
        	}
        	String defaultJob = jsonObject.getString("defaultJob");
        	if (BeanUtils.isNotEmpty(defaultJob)) {
        		queryFilter.addFilter("OJ.JOB_NAME", defaultJob, QueryOP.LIKE);
        	}
			PageJson pageJson  =  staffService.queryPage(queryFilter);
			return new HttpEntity(HttpStatus.OK,true,"请求成功",pageJson);
	 	}catch (Exception e){
    		return new HttpEntity(HttpStatus.OK,false,"请求失败",e);
		}

	}
	
	/**
	 * 员工信息 列表数据(不分页)
	 * @param jsonObject
	 * @return
	 * @throws Exception
	 * HttpEntity
	 * @exception
	 */
	@RequestMapping("queryList")
	@ResponseBody
	public HttpEntity listJson(@RequestBody JSONObject jsonObject){
		DefaultQueryFilter queryFilter =getQueryFilter(jsonObject);
		try{
			List<Staff> list = staffService.query(queryFilter);
			return new HttpEntity(HttpStatus.OK,true,"请求成功",list);
		}catch (Exception e){
			return new HttpEntity(HttpStatus.OK,false,"请求失败",e);
		}
		
	}

	/**
	 * 员工信息 明细页面
	 * @param  jsonObject
	 * @return
	 * @throws Exception
	 * HttpEntity
	 * @exception
	 * @since  1.0.0
	 */
	@RequestMapping("get")
	@ResponseBody
	public HttpEntity get(@RequestBody JSONObject jsonObject){
		Staff staff=null;
 		try{
			Long staffId=jsonObject.getLong("id");
			BeanUtils.notEmpty(staffId, "员工ID不能为空");
			staff=staffService.qryByPrimaryKey(staffId);
			if (staff!=null) {
				//获取关联的帐号
				UserStaffIbean usBean= new UserStaffIbean();
				usBean.setStaffId(staff.getStaffId());
				List<UserStaff> userStaffs = userStaffService.qryUserStaffList(usBean);
				staff.setUserStaffs(userStaffs);
				
				//获取关联的职位
				StaffJobIbean sjBean = new StaffJobIbean();
				sjBean.setStaffId(staff.getStaffId());
				List<StaffJob> staffJobs = staffJobService.qryStaffJobList(sjBean);
				staff.setStaffJobs(staffJobs);
				
				//获取关联的角色
				if (BeanUtils.isNotEmpty(userStaffs)) {
					Set<Role> roleSet = new HashSet<Role>();
					for ( UserStaff userStaff : userStaffs) {
						RoleIbean roleBean = new RoleIbean();
						roleBean.setUserId(userStaff.getUserId());
						List<Role> roles = roleService.qryRoleList(roleBean);
						if (BeanUtils.isNotEmpty(roles)) {
							roleSet.addAll(roles);
						}
					}
				}
			}
			return new HttpEntity(HttpStatus.OK,true,"请求成功",staff);
	 	}catch (IllegalArgumentException e) {
			return new HttpEntity(HttpStatus.OK,false,e.getMessage(),null);
		}catch (Exception e){
    		return new HttpEntity(HttpStatus.OK,false,"请求失败",e);
		}
	}

	/**
	 * 保存员工信息
	 * @param  jsonObject
	 * @throws Exception
	 * void
	 * @exception
	 */
	@RequestMapping("save")
	@ResponseBody
	public HttpEntity save(@RequestBody JSONObject jsonObject){
		Staff staff = JSONObject.toJavaObject(jsonObject,Staff.class);
		String resultMsg=null;
		try {
			BeanUtils.notEmpty(staff, "员工对象不能为空");
			BeanUtils.notEmpty(staff.getStaffName(), "名称不能为空");
			if (BeanUtils.isNotEmpty(staff.getUserStaffs())) {
				for ( UserStaff us : staff.getUserStaffs()) {
					DefaultQueryFilter queryFilter= new DefaultQueryFilter();
					queryFilter.addFilter("OUS.USER_ID", us.getUserId(), QueryOP.EQUAL);
					if (BeanUtils.isNotEmpty(us.getStaffId())) {
						queryFilter.addFilter("OUS.STAFF_ID", us.getStaffId(), QueryOP.NOT_EQUAL);
					}
					if (BeanUtils.isNotEmpty(userStaffService.query(queryFilter))) {
						resultMsg = "帐号已绑定其他员工！";
					}
				}
			}
			if (StringUtils.isEmpty(resultMsg)) {
				//通过系统维护的都是2
				
				User currentUser = userUtils.getUser();
				if (currentUser!=null) {
					if (BeanUtils.isEmpty(staff.getStaffId())) {
						staff.setCreateUserId(currentUser.getUserId());
					}else {
						staff.setModifyUserId(currentUser.getUserId());
					}
				}
				if (BeanUtils.isEmpty(staff.getStaffId())) {
					staff.setSource(2);
				}
				staffService.createOrModify(staff);
				if (BeanUtils.isNotEmpty(staff.getUserStaffs())) {
					for ( UserStaff us : staff.getUserStaffs()) {
						userUtils.refreshUserDetail(us.getUserId());
					}
				}
				resultMsg="保存成功";
				return new HttpEntity(HttpStatus.OK,true,resultMsg,null);
			}else {
				return new HttpEntity(HttpStatus.OK,false,resultMsg,null);
			}
			
		}catch (IllegalArgumentException e) {
			return new HttpEntity(HttpStatus.OK,false,e.getMessage(),null);
		} catch (Exception e) {
			resultMsg="操作失败";
			return new HttpEntity(HttpStatus.OK,false,resultMsg,e);
		}
		
	}

	/**
	 * 批量删除员工记录
	 * @param jsonObject
	 * @throws Exception
	 * void
	 * @exception
	 */
	@RequestMapping("remove")
	@ResponseBody
	public HttpEntity remove(@RequestBody JSONObject jsonObject){
		String resultMsg=null;
		try {
			Long[] aryIds = getLongAryByStr(jsonObject, "ids");
			List<UserStaff> list = new ArrayList<UserStaff>();
			for (Long id : aryIds) {
				UserStaffIbean ibean = new UserStaffIbean();
				ibean.setUserId(id);
				List<UserStaff> temp = userStaffService.qryUserStaffList(ibean);
				if (BeanUtils.isNotEmpty(temp)) {
					list.addAll(temp);
				}
				
			}
			staffService.removeBatchByStaffIds(aryIds);
			for (UserStaff userStaff : list) {
				userUtils.refreshUserDetail(userStaff.getUserId());
			}
			resultMsg="删除成功";
			return new HttpEntity(HttpStatus.OK,true,resultMsg,null);
		} catch (Exception e) {
    		resultMsg="删除失败";
		 	return new HttpEntity(HttpStatus.OK,false,resultMsg,e);
		}
	}
	
}
