package com.basic.oaas.controller;

import com.alibaba.fastjson.JSONObject;
import com.basic.framework.common.api.db.domain.PageJson;
import com.basic.framework.common.api.query.QueryOP;
import com.basic.framework.common.model.DefaultQueryFilter;
import com.basic.framework.common.utils.result.HttpEntity;
import com.basic.framework.common.utils.result.HttpStatus;
import com.basic.framework.web.BaseController;
import com.basic.oaas.bean.PrivateDataGroupInstIbean;
import com.basic.oaas.model.PrivateDataGroupInst;
import com.basic.oaas.service.PrivateDataGroupInstService;
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
 * @Description：数据权限分组实例 控制器类
 * @author:lengzj
 * @email:mail@com
 * @date:2018-06-27 16:21:49
 * @copyright：companyName
 * </pre>
 */
@Controller
@RequestMapping("/basic.oaas/privateDataGroupInst/")
public class PrivateDataGroupInstController extends BaseController{

	private Logger logger = LoggerFactory.getLogger(PrivateDataGroupInstController.class);

	@Autowired
	PrivateDataGroupInstService privateDataGroupInstService;

	/**
	 * 数据权限分组实例列表(分页条件查询)数据
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
        	Long groupId = jsonObject.getLong("groupId");
        	BeanUtils.notEmpty(groupId, "分组ID不能为空");
        	queryFilter.addFilter("DATA_GROUP_ID", groupId, QueryOP.EQUAL);
			PageJson pageJson  =  privateDataGroupInstService.queryPage(queryFilter);
			return new HttpEntity(HttpStatus.OK,true,"请求成功",pageJson);
	 	}catch (IllegalArgumentException e) {
			return new HttpEntity(HttpStatus.OK,false,e.getMessage(),null);
		} catch (Exception e){
    		return new HttpEntity(HttpStatus.OK,false,"请求失败",e);
		}

	}
	

	/**
	 * 数据权限分组实例明细页面
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
		PrivateDataGroupInst privateDataGroupInst=null;
 		try{
			Long dataGrpInstId=jsonObject.getLong("id");
			privateDataGroupInst=privateDataGroupInstService.get(dataGrpInstId);
			return new HttpEntity(HttpStatus.OK,true,"请求成功",privateDataGroupInst);
	 	}catch (Exception e){
    		return new HttpEntity(HttpStatus.OK,false,"请求失败",e);
		}
	}

	/**
	 * 保存数据权限分组实例信息
	 * @param  jsonObject
	 * @throws Exception
	 * void
	 * @exception
	 */
	@RequestMapping("save")
	@ResponseBody
	public HttpEntity save(@RequestBody JSONObject jsonObject){
		PrivateDataGroupInstIbean ibean = JSONObject.toJavaObject(jsonObject,PrivateDataGroupInstIbean.class);
		String resultMsg=null;
		try {
			BeanUtils.notEmpty(ibean, "对象不能为空");
			BeanUtils.notEmpty(ibean.getDataGroupId(), "分组ID不能为空");
			if (BeanUtils.isNotEmpty(ibean.getPrivateDataGroupInstDatas())) {
				for ( PrivateDataGroupInst data : ibean.getPrivateDataGroupInstDatas()) {
					BeanUtils.notEmpty(data, "实例对象不能为空");
					BeanUtils.notEmpty(data.getDataInstId(), "实例ID不能为空");
					data.setDataGroupId(ibean.getDataGroupId());
				}
				privateDataGroupInstService.createSelective(ibean);
			}
			resultMsg = "保存成功";
			return new HttpEntity(HttpStatus.OK,true,resultMsg,null);
		}catch (IllegalArgumentException e) {
			return new HttpEntity(HttpStatus.OK,false,e.getMessage(),null);
		}  catch (Exception e) {
			resultMsg="保存失败";
			return new HttpEntity(HttpStatus.OK,false,resultMsg,e);
		}
	}

	/**
	 * 批量删除数据权限分组实例记录
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
			privateDataGroupInstService.removeBatchByDataGrpInstId(aryIds);
			resultMsg="删除成功";
			return new HttpEntity(HttpStatus.OK,true,resultMsg,null);
		} catch (Exception e) {
    		resultMsg="删除失败";
		 	return new HttpEntity(HttpStatus.OK,false,resultMsg,e);
		}
	}
}
