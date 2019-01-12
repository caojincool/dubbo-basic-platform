package com.basic.oaas.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.basic.framework.common.api.db.domain.PageJson;
import com.basic.framework.common.api.query.QueryOP;
import com.basic.framework.common.model.DefaultQueryFilter;
import com.basic.framework.common.utils.result.HttpEntity;
import com.basic.framework.common.utils.result.HttpStatus;
import com.basic.framework.security.jwt.utils.UserUtils;
import com.basic.framework.web.BaseController;
import com.basic.oaas.bean.UserStaffIbean;
import com.basic.oaas.model.UserStaff;
import com.basic.oaas.service.UserStaffService;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.basic.framework.common.utils.BeanUtils;

/**
 * 
 * <pre> 
 * @Description：帐号员工关系表 控制器类
 * @author:lengzj
 * @email:mail@com
 * @date:2018-06-29 09:09:20
 * @copyright：companyName
 * </pre>
 */
@Controller
@RequestMapping("/basic.oaas/userStaff/")
public class UserStaffController extends BaseController{

	private Logger logger = LoggerFactory.getLogger(UserStaffController.class);

	@Autowired
	UserStaffService userStaffService;
	@Autowired
	UserUtils userUtils;


	/**
	 * 保存帐号员工关系表信息
	 * @param  jsonObject
	 * @throws Exception
	 * void
	 * @exception
	 */
	@RequestMapping("save")
	@ResponseBody
	public HttpEntity save(@RequestBody JSONObject jsonObject){
		UserStaffIbean ibean = JSONObject.toJavaObject(jsonObject,UserStaffIbean.class);
		String resultMsg=null;
		try {
			BeanUtils.notEmpty(ibean, "对象不能为空");
			BeanUtils.notEmpty(ibean.getStaffId(), "绑定对象的ID不能为空");
			if (BeanUtils.isNotEmpty(ibean.getUserStaffs())) {
				for (UserStaff us : ibean.getUserStaffs()) {
					BeanUtils.notEmpty(us.getUserId(), "帐号ID不能为空");
					BeanUtils.notEmpty(us.getType(), "类型不能为空");
					
					DefaultQueryFilter queryFilter= new DefaultQueryFilter();
					queryFilter.addFilter("OUS.USER_ID", us.getUserId(), QueryOP.EQUAL);
					if (BeanUtils.isNotEmpty(us.getStaffId())) {
						queryFilter.addFilter("OUS.STAFF_ID", us.getStaffId(), QueryOP.NOT_EQUAL);
					}
					if (BeanUtils.isNotEmpty(userStaffService.query(queryFilter))) {
						resultMsg = "帐号已被绑定！";
						return new HttpEntity(HttpStatus.OK,false,resultMsg,null);
					}
					us.setStaffId(ibean.getStaffId());
					us.setSource(2);
					userStaffService.createSelective(us);
					userUtils.refreshUserDetail(us.getUserId());
				}
			}
			resultMsg = "保存成功";
			return new HttpEntity(HttpStatus.OK,true,resultMsg,null);
		}catch (IllegalArgumentException e) {
			return new HttpEntity(HttpStatus.OK,false,e.getMessage(),null);
		} catch (Exception e) {
			resultMsg="对帐号员工关系表操作失败";
			return new HttpEntity(HttpStatus.OK,false,resultMsg,e);
		}
	}
	
	/**
	 * 批量删除帐号员工关系表记录
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
			userStaffService.removeBatchByUserIds(aryIds);
			for (Long id : aryIds) {
				userUtils.refreshUserDetail(id);
			}
			resultMsg="删除成功";
			return new HttpEntity(HttpStatus.OK,true,resultMsg,null);
		} catch (Exception e) {
    		resultMsg="删除失败";
		 	return new HttpEntity(HttpStatus.OK,false,resultMsg,e);
		}
	}

}
