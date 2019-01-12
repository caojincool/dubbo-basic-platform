package com.basic.system.controller;

import com.alibaba.fastjson.JSONObject;
import com.basic.framework.common.api.db.domain.PageJson;
import com.basic.framework.common.model.DefaultQueryFilter;
import com.basic.framework.common.utils.result.HttpEntity;
import com.basic.framework.common.utils.result.HttpStatus;
import com.basic.framework.web.BaseController;
import com.basic.oaas.model.Job;
import com.basic.oaas.model.Org;
import com.basic.oaas.model.Staff;
import com.basic.oaas.service.JobService;
import com.basic.oaas.service.OrgService;
import com.basic.oaas.service.StaffService;
import com.basic.oaas.service.UserService;
import com.basic.system.model.BaseSysSystem;
import com.basic.system.service.BaseSysSystemService;

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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * <pre>
 * 描述：提供给外围系统同步数据的接口控制器类
 * 构建组：basic.erp
 * 作者:lengzj
 * 邮箱:mail@com
 * 日期:2018-06-14 14:41:18
 * 版权：companyName
 * </pre>
 */
@Controller
@RequestMapping("/basic.api")
public class ApiController extends BaseController{

	private Logger logger = LoggerFactory.getLogger(ApiController.class);

	@Autowired
	BaseSysSystemService baseSysSystemService;
	
	@Autowired
	OrgService orgService;
	
	@Autowired
	JobService jobService;
	
	@Autowired
	StaffService staffService;
	
	@Autowired
	UserService userService;
	
 	
	/**
	 * 获取所有部门
	 * @param jsonObject
	 * @return
	 * @throws Exception
	 * HttpEntity
	 * @exception
	 */
	@RequestMapping("/getAllOrgs")
	@ResponseBody
    public HttpEntity getAllOrgs(@RequestBody JSONObject jsonObject){
		List<Org> dataList = new ArrayList<Org>();
		StringBuilder msg = new StringBuilder();
		String orgCodes = getOrgCodes(jsonObject, msg);
		if (StringUtils.isNotEmpty(orgCodes)) {
			List<Org> orgs = orgService.selectFindInSet("ORG_CODE", "'"+orgCodes+"'");
			if (BeanUtils.isNotEmpty(orgs)) {
				for (Org org : orgs) {
					List<Org> childrens = orgService.selectSubOrgByPathId(org.getOrgId());
					if (BeanUtils.isNotEmpty(childrens)) {
						dataList.addAll(childrens);
					}
				}
			}
		}
		if (StringUtils.isNotEmpty(msg.toString())) {
			return new HttpEntity(HttpStatus.OK,false,"请求失败",msg.toString());
		}else {
			return new HttpEntity(HttpStatus.OK,true,"请求成功",dataList);
		}
	}
	
	/**
	 * 获取所有岗位包括与部门、员工关系
	 * @param jsonObject
	 * @return
	 * @throws Exception
	 * HttpEntity
	 * @exception
	 */
	@RequestMapping("/getAllJobs")
	@ResponseBody
	public HttpEntity getAllJobs(@RequestBody JSONObject jsonObject){
		List<Job> dataList = new ArrayList<Job>();
		StringBuilder msg = new StringBuilder();
		String orgCodes = getOrgCodes(jsonObject, msg);
		if (StringUtils.isNotEmpty(orgCodes)) {
			List<Long> orgIds = new ArrayList<Long>();
			List<Org> orgs = orgService.selectFindInSet("ORG_CODE", "'"+orgCodes+"'");
			if (BeanUtils.isNotEmpty(orgs)) {
				for (Org org : orgs) {
					List<Org> childrens = orgService.selectSubOrgByPathId(org.getOrgId());
					if (BeanUtils.isNotEmpty(childrens)) {
						for (Org org2 : childrens) {
							orgIds.add(org2.getOrgId());
						}
					}
				}
			}
			if (BeanUtils.isNotEmpty(orgIds)) {
				Map<String, Object> params = new HashMap<String,Object>();
				params.put("orgIds", orgIds);
				dataList = jobService.queryJobOrgUserRels(params);
			}
		}
		if (StringUtils.isNotEmpty(msg.toString())) {
			return new HttpEntity(HttpStatus.OK,false,"请求失败",msg.toString());
		}else {
			return new HttpEntity(HttpStatus.OK,true,"请求成功",dataList);
		}
	}
	
	/**
	 * 获取所有员工
	 * @param jsonObject
	 * @return
	 * @throws Exception
	 * HttpEntity
	 * @exception
	 */
	@RequestMapping("/getAllUsers")
	@ResponseBody
	public HttpEntity getAllUsers(@RequestBody JSONObject jsonObject){
		List<Staff> dataList = new ArrayList<Staff>();
		StringBuilder msg = new StringBuilder();
		String orgCodes = getOrgCodes(jsonObject, msg);
		if (StringUtils.isNotEmpty(orgCodes)) {
			List<Long> orgIds = new ArrayList<Long>();
			List<Org> orgs = orgService.selectFindInSet("ORG_CODE", "'"+orgCodes+"'");
			if (BeanUtils.isNotEmpty(orgs)) {
				for (Org org : orgs) {
					List<Org> childrens = orgService.selectSubOrgByPathId(org.getOrgId());
					if (BeanUtils.isNotEmpty(childrens)) {
						for (Org org2 : childrens) {
							orgIds.add(org2.getOrgId());
						}
					}
				}
			}
			if (BeanUtils.isNotEmpty(orgIds)) {
				Map<String,Object> params = new HashMap<String,Object>();
				params.put("orgIds", orgIds);
				dataList = staffService.queryAccountByOrgIds(params);
			}
		}
		if (StringUtils.isNotEmpty(msg.toString())) {
			return new HttpEntity(HttpStatus.OK,false,"请求失败",msg.toString());
		}else {
			return new HttpEntity(HttpStatus.OK,true,"请求成功",dataList);
		}
	}
	
	private String getOrgCodes(JSONObject jsonObject,StringBuilder msg) {
		String systemCode = jsonObject.getString("systemCode");
		
		if (StringUtils.isEmpty(systemCode)) {
			msg.append("系统编码不能为空");
		}else {
			BaseSysSystem system = baseSysSystemService.getBySyscode(systemCode);
			if (system==null) {
				msg.append("系统标识未定义");
			}else {
				return system.getOrgCodes();
			}
		}
		return null;
	}
	
	/**
	 * 获取所有员工
	 * @param jsonObject
	 * @return
	 * @throws Exception
	 * HttpEntity
	 * @exception
	 */
	@RequestMapping("/getUsersByOrgIds")
	@ResponseBody
	public HttpEntity getUsersByOrgIds(@RequestBody JSONObject jsonObject){
		try{
			if (logger.isDebugEnabled()) {
				logger.debug("paramters:{}", jsonObject);//分页参数与条件参数
			}
			StringBuilder msg = new StringBuilder();
			String orgCodes = getOrgCodes(jsonObject, msg);
			if (StringUtils.isNotEmpty(orgCodes)) {
				List<Long> orgIds = new ArrayList<Long>();
				List<Org> orgs = orgService.selectFindInSet("ORG_CODE", "'"+orgCodes+"'");
				if (BeanUtils.isNotEmpty(orgs)) {
					for (Org org : orgs) {
						List<Org> childrens = orgService.selectSubOrgByPathId(org.getOrgId());
						if (BeanUtils.isNotEmpty(childrens)) {
							for (Org org2 : childrens) {
								orgIds.add(org2.getOrgId());
							}
						}
					}
				}
				
				if (BeanUtils.isNotEmpty(orgIds)) {
					DefaultQueryFilter queryFilter = getQueryFilter(jsonObject);
					queryFilter.addParamsFilter("staffName", jsonObject.get("name"));
					queryFilter.addParamsFilter("orgIds", orgIds);
					PageJson pageJson  =  staffService.queryUserByOrgIds("queryUserByOrgIds",queryFilter);
					return new HttpEntity(HttpStatus.OK,true,"请求成功",pageJson);
				}
			}
			if (StringUtils.isNotEmpty(msg.toString())) {
				return new HttpEntity(HttpStatus.OK,false,"请求失败",msg.toString());
			}else {
				return new HttpEntity(HttpStatus.OK,true,"请求成功","ERP找不到物流系统接口配置");
			}
		}catch(Exception e) {
			logger.error("物流查询操作员角色页面数据失败：{}",e);
			return new HttpEntity(HttpStatus.OK,false,"请求失败",e);
		}
			
	}
	
}
