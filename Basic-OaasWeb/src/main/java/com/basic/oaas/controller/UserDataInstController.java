package com.basic.oaas.controller;

import com.alibaba.fastjson.JSONObject;
import com.basic.framework.common.api.db.domain.PageJson;
import com.basic.framework.common.api.query.QueryOP;
import com.basic.framework.common.model.DefaultQueryFilter;
import com.basic.framework.common.utils.result.HttpEntity;
import com.basic.framework.common.utils.result.HttpStatus;
import com.basic.framework.web.BaseController;
import com.basic.oaas.bean.UserDataInstIbean;
import com.basic.oaas.model.UserDataInst;
import com.basic.oaas.service.UserDataInstService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.basic.framework.common.utils.BeanUtils;
import java.util.List;

/**
 * 
 * <pre> 
 * @Description：帐号数据权限关系 控制器类
 * @author:lengzj
 * @email:mail@com
 * @date:2018-06-27 17:46:27
 * @copyright：companyName
 * </pre>
 */
@Controller
@RequestMapping("/basic.oaas/userDataInst/")
public class UserDataInstController extends BaseController{

	private Logger logger = LoggerFactory.getLogger(UserDataInstController.class);

	@Autowired
	UserDataInstService userDataInstService;

	/**
	 * 帐号数据权限关系列表(分页条件查询)数据
	 * @param jsonObject
	 * @return
	 * @throws Exception
	 * HttpEntity
	 * @exception
	 */
	@RequestMapping("queryPage")
	@ResponseBody
    public HttpEntity listPageJson(@RequestBody JSONObject jsonObject){
        DefaultQueryFilter queryFilter =getQueryFilter(jsonObject);
        try{
        	Long userId = jsonObject.getLong("userId");
			BeanUtils.notEmpty(userId, "帐号ID不能为空");
			PageJson pageJson  =  userDataInstService.queryPage(queryFilter);
			return new HttpEntity(HttpStatus.OK,true,"请求成功",pageJson);
	 	}catch (IllegalArgumentException e) {
			return new HttpEntity(HttpStatus.OK,false,e.getMessage(),null);
		}catch (Exception e){
    		return new HttpEntity(HttpStatus.OK,false,"请求失败",e);
		}

	}
	
	/**
	 * 帐号数据权限关系列表数据(不分页)
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
			queryFilter.addFilter("A.USER_ID", userId, QueryOP.EQUAL);
			List<UserDataInst> list = userDataInstService.query(queryFilter);
			return new HttpEntity(HttpStatus.OK,true,"请求成功",list);
		}catch (IllegalArgumentException e) {
			return new HttpEntity(HttpStatus.OK,false,e.getMessage(),null);
		}catch (Exception e){
			return new HttpEntity(HttpStatus.OK,false,"请求失败",e);
		}
		
	}

	/**
	 * 保存帐号数据权限关系信息
	 * @param  jsonObject
	 * @throws Exception
	 * void
	 * @exception
	 */
	@RequestMapping("save")
	@ResponseBody
	public HttpEntity save(@RequestBody JSONObject jsonObject){
		UserDataInstIbean ibean = JSONObject.toJavaObject(jsonObject,UserDataInstIbean.class);
		String resultMsg=null;
		try {
			BeanUtils.notEmpty(ibean, "对象不能为空");
			BeanUtils.notEmpty(ibean.getUserId(), "对象不能为空");
			if (BeanUtils.isNotEmpty(ibean.getUserDataInsts())) {
				for (UserDataInst inst : ibean.getUserDataInsts()) {
					BeanUtils.notEmpty(inst, "实例不能为空");
					BeanUtils.notEmpty(inst.getDataInstId(), "实例ID不能为空");
				}
			}
			userDataInstService.createBatchUserDataInst(ibean);
			resultMsg="保存成功";
			return new HttpEntity(HttpStatus.OK,true,resultMsg,null);
		} catch (Exception e) {
			resultMsg="对帐号数据权限关系操作失败";
			return new HttpEntity(HttpStatus.OK,false,resultMsg,e);
		}
	}

}
