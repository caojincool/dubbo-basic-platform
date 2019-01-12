package com.basic.oaas.controller;

import com.alibaba.fastjson.JSONObject;
import com.basic.framework.common.api.db.domain.PageJson;
import com.basic.framework.common.api.query.QueryOP;
import com.basic.framework.common.model.DefaultQueryFilter;
import com.basic.framework.common.utils.result.HttpEntity;
import com.basic.framework.common.utils.result.HttpStatus;
import com.basic.framework.web.BaseController;
import com.basic.oaas.model.PrivateDataData;
import com.basic.oaas.service.PrivateDataDataService;
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
 * @Description：数据权限数据控制器类
 * @author:lengzj
 * @email:mail@com
 * @date:2018-06-27 15:18:10
 * @copyright：companyName
 * </pre>
 */
@Controller
@RequestMapping("/basic.oaas/privateDataData/")
public class PrivateDataDataController extends BaseController{

	private Logger logger = LoggerFactory.getLogger(PrivateDataDataController.class);

	@Autowired
	PrivateDataDataService privateDataDataService;

	/**
	 * 数据权限数据列表(分页条件查询)数据
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
        	String scopeType = jsonObject.getString("scopeType");
        	BeanUtils.notEmpty(scopeType, "范围类型不能为空");
        	queryFilter.addFilter("DD.SCOPE_TYPE", scopeType, QueryOP.EQUAL);
			PageJson pageJson  =  privateDataDataService.queryPage(queryFilter);
			return new HttpEntity(HttpStatus.OK,true,"请求成功",pageJson);
	 	}catch (IllegalArgumentException e) {
			return new HttpEntity(HttpStatus.OK,false,e.getMessage(),null);
		} catch (Exception e){
    		return new HttpEntity(HttpStatus.OK,false,"请求失败",e);
		}

	}
}
