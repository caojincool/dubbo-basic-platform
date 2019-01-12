package com.basic.oaas.controller;

import com.alibaba.fastjson.JSONObject;
import com.basic.framework.common.api.db.domain.PageJson;
import com.basic.framework.common.api.query.QueryOP;
import com.basic.framework.common.model.DefaultQueryFilter;
import com.basic.framework.common.utils.result.HttpEntity;
import com.basic.framework.common.utils.result.HttpStatus;
import com.basic.framework.security.jwt.utils.UserUtils;
import com.basic.framework.web.BaseController;
import com.basic.oaas.model.PrivateDataInst;
import com.basic.oaas.model.User;
import com.basic.oaas.service.PrivateDataInstService;
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
 * @Description：数据权限实例 控制器类
 * @author:lengzj
 * @email:mail@com
 * @date:2018-06-27 14:38:12
 * @copyright：companyName
 * </pre>
 */
@Controller
@RequestMapping("/basic.oaas/privateDataInst/")
public class PrivateDataInstController extends BaseController{

	private Logger logger = LoggerFactory.getLogger(PrivateDataInstController.class);

	@Autowired
	PrivateDataInstService privateDataInstService;
	@Autowired
	UserUtils userUtils;

	/**
	 * 数据权限实例列表(分页条件查询)数据
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
        	Long dataId = jsonObject.getLong("dataId");
        	if (BeanUtils.isNotEmpty(dataId)) {
        		queryFilter.addFilter("OPDI.DATA_ID", dataId, QueryOP.EQUAL);
			}
			PageJson pageJson  =  privateDataInstService.queryPage(queryFilter);
			return new HttpEntity(HttpStatus.OK,true,"请求成功",pageJson);
	 	}catch (IllegalArgumentException e) {
			return new HttpEntity(HttpStatus.OK,false,e.getMessage(),null);
		}catch (Exception e){
    		return new HttpEntity(HttpStatus.OK,false,"请求失败",e);
		}

	}
	
	/**
	 * 数据权限实例列表数据
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
			List<PrivateDataInst> list = privateDataInstService.query(queryFilter);
			return new HttpEntity(HttpStatus.OK,true,"请求成功",list);
		}catch (IllegalArgumentException e) {
			return new HttpEntity(HttpStatus.OK,false,e.getMessage(),null);
		}catch (Exception e){
			return new HttpEntity(HttpStatus.OK,false,"请求失败",e);
		}
		
	}
	


	/**
	 * 保存数据权限实例信息
	 * @param  jsonObject
	 * @throws Exception
	 * void
	 * @exception
	 */
	@RequestMapping("save")
	@ResponseBody
	public HttpEntity save(@RequestBody JSONObject jsonObject){
		PrivateDataInst privateDataInst = JSONObject.toJavaObject(jsonObject,PrivateDataInst.class);
		String resultMsg=null;
		try {
			BeanUtils.notEmpty(privateDataInst, "对象不能为空");
			BeanUtils.notEmpty(privateDataInst.getDataId(), "数据权限ID不能为空");
			BeanUtils.notEmpty(privateDataInst.getDataInstName(), "数据权限实例名称不能为空");
			
			DefaultQueryFilter queryFilter = new DefaultQueryFilter();
			queryFilter.addFilter("OPDI.DATA_INST_NAME", privateDataInst.getDataInstName(), QueryOP.EQUAL);
			queryFilter.addFilter("OPDI.DATA_ID", privateDataInst.getDataId(), QueryOP.EQUAL);
			if (BeanUtils.isNotEmpty(privateDataInstService.query(queryFilter))) {
				resultMsg = "名称不可重复";
				return new HttpEntity(HttpStatus.OK,false,resultMsg,null);
			}else {
				privateDataInstService.createSelective(privateDataInst);
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
	 * 批量删除数据权限实例记录
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
			privateDataInstService.removeBatchByDataInstIds(aryIds);
			resultMsg="删除成功";
			return new HttpEntity(HttpStatus.OK,true,resultMsg,null);
		} catch (Exception e) {
    		resultMsg="删除失败";
		 	return new HttpEntity(HttpStatus.OK,false,resultMsg,e);
		}
	}
}
