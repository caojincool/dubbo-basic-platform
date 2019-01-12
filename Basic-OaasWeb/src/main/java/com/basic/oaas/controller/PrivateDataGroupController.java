package com.basic.oaas.controller;

import com.alibaba.fastjson.JSONObject;
import com.basic.framework.common.api.db.domain.PageJson;
import com.basic.framework.common.api.query.QueryOP;
import com.basic.framework.common.model.DefaultQueryFilter;
import com.basic.framework.common.utils.result.HttpEntity;
import com.basic.framework.common.utils.result.HttpStatus;
import com.basic.framework.web.BaseController;
import com.basic.oaas.model.PrivateDataGroup;
import com.basic.oaas.model.User;
import com.basic.oaas.service.PrivateDataGroupService;
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
 * @Description：数据权限分组 控制器类
 * @author:lengzj
 * @email:mail@com
 * @date:2018-06-27 16:21:49
 * @copyright：companyName
 * </pre>
 */
@Controller
@RequestMapping("/basic.oaas/privateDataGroup/")
public class PrivateDataGroupController extends BaseController{

	private Logger logger = LoggerFactory.getLogger(PrivateDataGroupController.class);

	@Autowired
	PrivateDataGroupService privateDataGroupService;

	/**
	 * 数据权限分组列表(分页条件查询)数据
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
			PageJson pageJson  =  privateDataGroupService.queryPage(queryFilter);
			return new HttpEntity(HttpStatus.OK,true,"请求成功",pageJson);
	 	}catch (Exception e){
    		return new HttpEntity(HttpStatus.OK,false,"请求失败",e);
		}

	}
	
	/**
	 * 数据权限分组列表数据
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
			String userId = jsonObject.getString("userId");
			if (BeanUtils.isNotEmpty(userId)) {
				queryFilter.addFilter("OUDG.USER_ID", userId, QueryOP.EQUAL);
				queryFilter.addParamsFilter("userId", userId);
			}
			List<PrivateDataGroup> list = privateDataGroupService.query(queryFilter);
			return new HttpEntity(HttpStatus.OK,true,"请求成功",list);
		}catch (Exception e){
			return new HttpEntity(HttpStatus.OK,false,"请求失败",e);
		}
		
	}
	
	/**
	 * 数据权限分组明细页面
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
		PrivateDataGroup privateDataGroup=null;
 		try{
			Long dataGroupId=jsonObject.getLong("id");
			privateDataGroup=privateDataGroupService.qryByPrimaryKey(dataGroupId);
			return new HttpEntity(HttpStatus.OK,true,"请求成功",privateDataGroup);
	 	}catch (Exception e){
    		return new HttpEntity(HttpStatus.OK,false,"请求失败",e);
		}
	}

	/**
	 * 保存数据权限分组信息
	 * @param  jsonObject
	 * @throws Exception
	 * void
	 * @exception
	 */
	@RequestMapping("save")
	@ResponseBody
	public HttpEntity save(@RequestBody JSONObject jsonObject){
		PrivateDataGroup privateDataGroup = JSONObject.toJavaObject(jsonObject,PrivateDataGroup.class);
		String resultMsg=null;
		try {
			BeanUtils.notEmpty(privateDataGroup, "对象不能为空");
			BeanUtils.notEmpty(privateDataGroup.getDataGroupCode(), "编码不能为空");
			BeanUtils.notEmpty(privateDataGroup.getDataGroupName(), "名称不能为空");
			
			DefaultQueryFilter queryFilter = new DefaultQueryFilter();
			queryFilter.addFilter("DATA_GROUP_CODE", privateDataGroup.getDataGroupCode(), QueryOP.EQUAL);
			if (BeanUtils.isNotEmpty(privateDataGroup.getDataGroupId())) {
				queryFilter.addFilter("DATA_GROUP_ID", privateDataGroup.getDataGroupId(), QueryOP.NOT_EQUAL);
			}
			if (BeanUtils.isNotEmpty(privateDataGroupService.query(queryFilter))) {
				resultMsg = "编码不可重复";
				return new HttpEntity(HttpStatus.OK,false,resultMsg,null);
			}else {
				privateDataGroup = privateDataGroupService.createOrModify(privateDataGroup);
				resultMsg = "保存成功";
				return new HttpEntity(HttpStatus.OK,true,resultMsg,privateDataGroup);
			}
		} catch (Exception e) {
			resultMsg="对数据权限分组操作失败";
			return new HttpEntity(HttpStatus.OK,false,resultMsg,e);
		}
	}

	/**
	 * 批量删除数据权限分组记录
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
			privateDataGroupService.removeBatchByDataGroupIds(aryIds);
			resultMsg="删除成功";
			return new HttpEntity(HttpStatus.OK,true,resultMsg,null);
		} catch (Exception e) {
    		resultMsg="删除失败";
		 	return new HttpEntity(HttpStatus.OK,false,resultMsg,e);
		}
	}
}
