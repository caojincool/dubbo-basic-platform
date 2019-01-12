package com.basic.oaas.controller;

import com.alibaba.fastjson.JSONObject;
import com.basic.framework.common.api.db.domain.PageJson;
import com.basic.framework.common.api.query.QueryOP;
import com.basic.framework.common.model.DefaultQueryFilter;
import com.basic.framework.common.utils.result.HttpEntity;
import com.basic.framework.common.utils.result.HttpStatus;
import com.basic.framework.security.jwt.utils.UserUtils;
import com.basic.framework.web.BaseController;
import com.basic.oaas.bean.PrivateDataIbean;
import com.basic.oaas.model.PrivateData;
import com.basic.oaas.model.User;
import com.basic.oaas.service.PrivateDataService;
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
 * @Description：数据权限控制器类
 * @author:lengzj
 * @email:mail@com
 * @date:2018-06-27 14:21:58
 * @copyright：companyName
 * </pre>
 */
@Controller
@RequestMapping("/basic.oaas/privateData/")
public class PrivateDataController extends BaseController{

	private Logger logger = LoggerFactory.getLogger(PrivateDataController.class);

	@Autowired
	PrivateDataService privateDataService;
	@Autowired
	UserUtils userUtils;

	/**
	 * 数据权限列表(分页条件查询)数据
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
        	Long dataGroupId = jsonObject.getLong("dataGroupId");
        	if (BeanUtils.isNotEmpty(dataGroupId)) {
				queryFilter.addFilter("OPDGI.DATA_GROUP_ID", dataGroupId, QueryOP.EQUAL);
				queryFilter.addParamsFilter("dataGroupId", dataGroupId);
			}
        	String dataCode = jsonObject.getString("dataCode");
        	if (BeanUtils.isNotEmpty(dataCode)) {
        		queryFilter.addFilter("OPDGI.DATA_CODE", dataCode, QueryOP.LIKE);
			}
        	String dataName = jsonObject.getString("dataName");
        	if (BeanUtils.isNotEmpty(dataName)) {
        		queryFilter.addFilter("PD.DATA_NAME", dataName, QueryOP.LIKE);
        	}
			PageJson pageJson  =  privateDataService.queryPage(queryFilter);
			return new HttpEntity(HttpStatus.OK,true,"请求成功",pageJson);
	 	}catch (Exception e){
    		return new HttpEntity(HttpStatus.OK,false,"请求失败",e);
		}

	}
	

	/**
	 * 数据权限明细页面
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
		PrivateData privateData=null;
 		try{
			Long dataId=jsonObject.getLong("id");
			BeanUtils.notEmpty(dataId, "数据权限ID不能为空");
			privateData=privateDataService.qryByPrimaryKey(dataId);
			return new HttpEntity(HttpStatus.OK,true,"请求成功",privateData);
	 	}catch (IllegalArgumentException e) {
			return new HttpEntity(HttpStatus.OK,false,e.getMessage(),null);
		}catch (Exception e){
    		return new HttpEntity(HttpStatus.OK,false,"请求失败",e);
		}
	}

	/**
	 * 保存数据权限信息
	 * @param  jsonObject
	 * @throws Exception
	 * void
	 * @exception
	 */
	@RequestMapping("save")
	@ResponseBody
	public HttpEntity save(@RequestBody JSONObject jsonObject){
		PrivateData privateData = JSONObject.toJavaObject(jsonObject,PrivateData.class);
		String resultMsg=null;
		try {
			BeanUtils.notEmpty(privateData, "对象不能为空");
			BeanUtils.notEmpty(privateData.getDataCode(), "编码不能为空");
			BeanUtils.notEmpty(privateData.getDataName(), "名称不能为空");
			BeanUtils.notEmpty(privateData.getScopeType(), "类型不能为空");
			
			DefaultQueryFilter queryFilter = new DefaultQueryFilter();
			queryFilter.addFilter("PD.DATA_CODE", privateData.getDataCode(), QueryOP.EQUAL);
			if (BeanUtils.isNotEmpty(privateData.getDataId())) {
				queryFilter.addFilter("PD.DATA_ID", privateData.getDataId(), QueryOP.NOT_EQUAL);
			}
			if (BeanUtils.isNotEmpty(privateDataService.query(queryFilter))) {
				resultMsg = "编码不可重复";
				return new HttpEntity(HttpStatus.OK,false,resultMsg,null);
			}else {
				User currentUser = userUtils.getUser();
				if (currentUser!=null) {
					if (BeanUtils.isEmpty(privateData.getDataId())) {
						privateData.setCreateUserId(currentUser.getUserId());
					}else {
						privateData.setModifyUserId(currentUser.getUserId());
					}
				}
				privateDataService.createOrModify(privateData);
				resultMsg = "保存成功";
				return new HttpEntity(HttpStatus.OK,true,resultMsg,null);
			}
		}catch (IllegalArgumentException e) {
			return new HttpEntity(HttpStatus.OK,false,e.getMessage(),null);
		} catch (Exception e) {
			resultMsg="保存失败";
			return new HttpEntity(HttpStatus.OK,false,resultMsg,e);
		}
	}

	/**
	 * 批量删除数据权限记录
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
			privateDataService.removeBatchByDataIds(aryIds);
			resultMsg="删除成功";
			return new HttpEntity(HttpStatus.OK,true,resultMsg,null);
		} catch (Exception e) {
    		resultMsg="删除失败";
		 	return new HttpEntity(HttpStatus.OK,false,resultMsg,e);
		}
	}
	
	
	/**
	 * 获取帐号分配关联的数据权限
	 * @param jsonObject
	 * @return
	 * @throws Exception
	 * HttpEntity
	 * @exception
	 */
	@RequestMapping("getUserPrivateData")
	@ResponseBody
    public HttpEntity getUserPrivateData(@RequestBody JSONObject jsonObject){
        try{
			PrivateDataIbean ibean = JSONObject.toJavaObject(jsonObject,PrivateDataIbean.class);
			BeanUtils.notEmpty(ibean, "对象不可为空");
			BeanUtils.notEmpty(ibean.getUserId(), "帐号ID不可为空");
			List<PrivateData> list = privateDataService.qryPrivateDataByUserList(ibean);
			return new HttpEntity(HttpStatus.OK,true,"请求成功",list);
	 	}catch (Exception e){
    		return new HttpEntity(HttpStatus.OK,false,"请求失败",e);
		}

	}
	
}
