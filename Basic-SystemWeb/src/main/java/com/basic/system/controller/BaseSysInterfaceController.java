package com.basic.system.controller;

import com.alibaba.fastjson.JSONObject;
import com.basic.framework.common.api.db.domain.PageJson;
import com.basic.framework.common.model.DefaultQueryFilter;
import com.basic.framework.common.utils.result.HttpEntity;
import com.basic.framework.common.utils.result.HttpStatus;
import com.basic.framework.web.BaseController;
import com.basic.system.model.BaseSysInterface;
import com.basic.system.service.BaseSysInterfaceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.basic.framework.common.utils.StringUtil;
import com.basic.system.model.GidServer;

import java.util.List;

/**
 *
 * <pre>
 * 描述：外围系统接口 控制器类
 * 构建组：basic.erp
 * 作者:lengzj
 * 邮箱:mail@com
 * 日期:2018-06-14 14:41:18
 * 版权：companyName
 * </pre>
 */
@Controller
@RequestMapping("/basic.system/baseSysInterface/")
public class BaseSysInterfaceController extends BaseController{

	private Logger logger = LoggerFactory.getLogger(BaseSysInterfaceController.class);

	@Autowired
	BaseSysInterfaceService baseSysInterfaceService;
 	
	/**
	 * 外围系统接口列表(分页条件查询)数据
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
			PageJson pageJson  =  baseSysInterfaceService.queryPage(queryFilter);
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
			List<BaseSysInterface> list = baseSysInterfaceService.query(queryFilter);
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
	    	List<BaseSysInterface> list  =  baseSysInterfaceService.getAll();
	    	return new HttpEntity(HttpStatus.OK,true,"请求成功",list);
	    }catch (Exception e){
	    	return new HttpEntity(HttpStatus.OK,false,"请求失败",e);
    	}
    }

	/**
	 * 外围系统接口明细页面
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
		BaseSysInterface baseSysInterface=null;
 		try{
			Long sysInterfaceId=jsonObject.getLong("id");
			baseSysInterface=baseSysInterfaceService.get(sysInterfaceId);
			return new HttpEntity(HttpStatus.OK,true,"请求成功",baseSysInterface);
	 	}catch (Exception e){
    		return new HttpEntity(HttpStatus.OK,false,"请求失败",baseSysInterface);
		}
	}

	/**
	 * 保存外围系统接口信息
	 * @param  jsonObject
	 * @throws Exception
	 * void
	 * @exception
	 */
	@RequestMapping("save")
	@ResponseBody
	public HttpEntity save(@RequestBody JSONObject jsonObject){
		BaseSysInterface baseSysInterface = JSONObject.toJavaObject(jsonObject.getJSONObject("baseSysInterface"),BaseSysInterface.class);
		String resultMsg=null;
		try {
			if(StringUtil.isEmpty(baseSysInterface.getSysInterfaceId())) {
				baseSysInterfaceService.create(baseSysInterface);
				resultMsg="添加外围系统接口成功";
			}else{
				baseSysInterfaceService.update(baseSysInterface);
				resultMsg="更新外围系统接口成功";
			}
			return new HttpEntity(HttpStatus.OK,true,resultMsg,null);
		} catch (Exception e) {
			resultMsg="对外围系统接口操作失败";
			return new HttpEntity(HttpStatus.OK,false,resultMsg,null);
		}
	}

	/**
	 * 批量删除外围系统接口记录
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
			Long[] aryIds = getLongAryByStr(jsonObject, "sysInterfaceId");
			baseSysInterfaceService.removeByIds(aryIds);
			resultMsg="删除成功";
			return new HttpEntity(HttpStatus.OK,true,resultMsg,null);
		} catch (Exception e) {
    		resultMsg="删除失败";
		 	return new HttpEntity(HttpStatus.OK,false,resultMsg,null);
		}
	}
}
