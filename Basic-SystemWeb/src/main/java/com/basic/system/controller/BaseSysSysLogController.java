package com.basic.system.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.basic.framework.common.api.db.domain.PageJson;
import com.basic.framework.common.model.DefaultQueryFilter;
import com.basic.framework.common.utils.StringUtil;
import com.basic.framework.common.utils.result.HttpEntity;
import com.basic.framework.common.utils.result.HttpStatus;
import com.basic.framework.web.BaseController;
import com.basic.system.model.BaseSysSysLog;
import com.basic.system.service.BaseSysSysLogService;

/**
 *
 * <pre>
 * 描述：记录与外围系统接口调用的日志 控制器类
 * 构建组：basic.erp
 * 作者:lengzj
 * 邮箱:mail@com
 * 日期:2018-06-14 14:41:17
 * 版权：companyName
 * </pre>
 */
@Controller
@RequestMapping("/basic.system/baseSysSysLog/")
public class BaseSysSysLogController extends BaseController{

	private Logger logger = LoggerFactory.getLogger(BaseSysSysLogController.class);

	@Autowired
	BaseSysSysLogService baseSysSysLogService;
 

	/**
	 * 记录与外围系统接口调用的日志列表(分页条件查询)数据
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
			PageJson pageJson  =  baseSysSysLogService.queryPage(queryFilter);
			return new HttpEntity(HttpStatus.OK,true,"请求成功",pageJson);
	 	}catch (Exception e){
    		return new HttpEntity(HttpStatus.OK,false,"请求失败",e);
		}

	}
	
	/**
	 * 外围系统接口列表(分页条件查询)数据
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
			List<BaseSysSysLog> list = baseSysSysLogService.query(queryFilter);
			return new HttpEntity(HttpStatus.OK,true,"请求成功",list);
		}catch (Exception e){
			return new HttpEntity(HttpStatus.OK,false,"请求失败",e);
		}
		
	}

 	/**
     * 获取所有
     * @param jsonObject
     * @return
     * @throws Exception
     * HttpEntity
     * @exception
     */
    @RequestMapping("getAll")
    @ResponseBody
    public HttpEntity getAll(@RequestBody JSONObject jsonObject){
	    try{
	    	List<BaseSysSysLog> list  =  baseSysSysLogService.getAll();
	    	return new HttpEntity(HttpStatus.OK,true,"请求成功",list);
	    }catch (Exception e){
	    	return new HttpEntity(HttpStatus.OK,false,"请求失败",e);
    	}
    }

	/**
	 * 记录与外围系统接口调用的日志明细页面
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
		BaseSysSysLog baseSysSysLog=null;
 		try{
			Long logId=jsonObject.getLong("id");
			baseSysSysLog=baseSysSysLogService.get(logId);
			return new HttpEntity(HttpStatus.OK,true,"请求成功",baseSysSysLog);
	 	}catch (Exception e){
    		return new HttpEntity(HttpStatus.OK,false,"请求失败",baseSysSysLog);
		}
	}

	/**
	 * 保存记录与外围系统接口调用的日志信息
	 * @param  jsonObject
	 * @throws Exception
	 * void
	 * @exception
	 */
	@RequestMapping("save")
	@ResponseBody
	public HttpEntity save(@RequestBody JSONObject jsonObject){
		BaseSysSysLog baseSysSysLog = JSONObject.toJavaObject(jsonObject.getJSONObject("baseSysSysLog"),BaseSysSysLog.class);
		String resultMsg=null;
		try {
			if(StringUtil.isEmpty(baseSysSysLog.getLogId())) {
				baseSysSysLogService.create(baseSysSysLog);
				resultMsg="添加记录与外围系统接口调用的日志成功";
			}else{
				baseSysSysLogService.update(baseSysSysLog);
				resultMsg="更新记录与外围系统接口调用的日志成功";
			}
			return new HttpEntity(HttpStatus.OK,true,resultMsg,null);
		} catch (Exception e) {
			resultMsg="对记录与外围系统接口调用的日志操作失败";
			return new HttpEntity(HttpStatus.OK,false,resultMsg,null);
		}
	}

	/**
	 * 批量删除记录与外围系统接口调用的日志记录
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
			Long[] aryIds = getLongAryByStr(jsonObject, "logId");
			baseSysSysLogService.removeByIds(aryIds);
			resultMsg="删除成功";
			return new HttpEntity(HttpStatus.OK,true,resultMsg,null);
		} catch (Exception e) {
    		resultMsg="删除失败";
		 	return new HttpEntity(HttpStatus.OK,false,resultMsg,null);
		}
	}
}
