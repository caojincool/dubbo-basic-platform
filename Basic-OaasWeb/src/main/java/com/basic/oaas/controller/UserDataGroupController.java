package com.basic.oaas.controller;

import com.alibaba.fastjson.JSONObject;
import com.basic.framework.common.api.db.domain.PageJson;
import com.basic.framework.common.model.DefaultQueryFilter;
import com.basic.framework.common.utils.result.HttpEntity;
import com.basic.framework.common.utils.result.HttpStatus;
import com.basic.framework.web.BaseController;
import com.basic.oaas.bean.UserDataGroupIbean;
import com.basic.oaas.model.UserDataGroup;
import com.basic.oaas.service.UserDataGroupService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.basic.framework.common.utils.BeanUtils;
import com.basic.framework.common.utils.StringUtil;

import java.util.Date;
import java.util.List;

/**
 * 
 * <pre> 
 * @Description：帐号数据权限分组关系 控制器类
 * @author:lengzj
 * @email:mail@com
 * @date:2018-06-27 17:46:27
 * @copyright：companyName
 * </pre>
 */
@Controller
@RequestMapping("/basic.oaas/userDataGroup/")
public class UserDataGroupController extends BaseController{

	private Logger logger = LoggerFactory.getLogger(UserDataGroupController.class);

	@Autowired
	UserDataGroupService userDataGroupService;

	
	/**
	 * 帐号数据权限分组关系列表数据(不分页)
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
			Long userId = jsonObject.getLong("userId");
			BeanUtils.notEmpty(userId, "帐号ID不能为空");
			List<UserDataGroup> list = userDataGroupService.query(queryFilter);
			return new HttpEntity(HttpStatus.OK,true,"请求成功",list);
		}catch (IllegalArgumentException e) {
			return new HttpEntity(HttpStatus.OK,false,e.getMessage(),null);
		}catch (Exception e){
			return new HttpEntity(HttpStatus.OK,false,"请求失败",e);
		}
		
	}

	/**
	 * 保存帐号数据权限分组关系信息
	 * @param  jsonObject
	 * @throws Exception
	 * void
	 * @exception
	 */
	@RequestMapping("save")
	@ResponseBody
	public HttpEntity save(@RequestBody JSONObject jsonObject){
		UserDataGroupIbean ibean = JSONObject.toJavaObject(jsonObject,UserDataGroupIbean.class);
		String resultMsg=null;
		try {
			BeanUtils.notEmpty(ibean, "对象不能为空");
			BeanUtils.notEmpty(ibean.getUserId(), "对象不能为空");
			if (BeanUtils.isNotEmpty(ibean.getUserDataGroups())) {
				for ( UserDataGroup group : ibean.getUserDataGroups()) {
					BeanUtils.notEmpty(group, "分组不能为空");
					BeanUtils.notEmpty(group.getDataGroupId(), "分组ID不能为空");
				}
			}
			userDataGroupService.createBatchUserDataGroup(ibean);
			resultMsg="保存成功";
			return new HttpEntity(HttpStatus.OK,true,resultMsg,null);
		}catch (IllegalArgumentException e) {
			return new HttpEntity(HttpStatus.OK,false,e.getMessage(),null);
		} catch (Exception e) {
			resultMsg="保存失败";
			return new HttpEntity(HttpStatus.OK,false,resultMsg,e);
		}
	}

}
