package com.basic.oaas.controller;

import com.alibaba.fastjson.JSONObject;
import com.basic.framework.common.api.db.domain.PageJson;
import com.basic.framework.common.model.DefaultQueryFilter;
import com.basic.framework.common.utils.result.HttpEntity;
import com.basic.framework.common.utils.result.HttpStatus;
import com.basic.framework.web.BaseController;
import com.basic.oaas.model.PrivateAttr;
import com.basic.oaas.service.PrivateAttrService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.basic.framework.common.utils.StringUtil;

import java.util.Date;
import java.util.List;

/**
 *
 * <pre>
 * 描述：字段权限配置 控制器类
 * 构建组：basic.erp
 * 作者:lengzj
 * 邮箱:mail@com
 * 日期:2018-06-25 09:08:26
 * 版权：companyName
 * </pre>
 */
@Controller
@RequestMapping("/basic.oaas/privateAttr/")
public class AttrController extends BaseController{

	private Logger logger = LoggerFactory.getLogger(AttrController.class);

	@Autowired
	PrivateAttrService privateAttrService;

	/**
	 * 字段权限配置列表(分页条件查询)数据
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
			PageJson pageJson  =  privateAttrService.queryPage(queryFilter);
			return new HttpEntity(HttpStatus.OK,true,"请求成功",pageJson);
	 	}catch (Exception e){
    		return new HttpEntity(HttpStatus.OK,false,"请求失败",e);
		}

	}
	
	/**
	 * 字段权限配置列表数据(不分页)
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
			List<PrivateAttr> list = privateAttrService.query(queryFilter);
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
	    	List<PrivateAttr> list  =  privateAttrService.getAll();
	    	return new HttpEntity(HttpStatus.OK,true,"请求成功",list);
	    }catch (Exception e){
	    	return new HttpEntity(HttpStatus.OK,false,"请求失败",e);
    	}
    }

	/**
	 * 字段权限配置明细页面
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
		PrivateAttr privateAttr=null;
 		try{
			Long attrId=jsonObject.getLong("id");
			privateAttr=privateAttrService.get(attrId);
			return new HttpEntity(HttpStatus.OK,true,"请求成功",privateAttr);
	 	}catch (Exception e){
    		return new HttpEntity(HttpStatus.OK,false,"请求失败",e);
		}
	}

	/**
	 * 保存字段权限配置信息
	 * @param  jsonObject
	 * @throws Exception
	 * void
	 * @exception
	 */
	@RequestMapping("save")
	@ResponseBody
	public HttpEntity save(@RequestBody JSONObject jsonObject){
		PrivateAttr privateAttr = JSONObject.toJavaObject(jsonObject,PrivateAttr.class);
		String resultMsg=null;
		try {
			if(StringUtil.isEmpty(privateAttr.getAttrId())) {

				privateAttr.setCreateTime(new Date());
				privateAttr.setState("10A");
				privateAttrService.create(privateAttr);
				resultMsg="添加字段权限配置成功";
			}else{
				privateAttr.setModifyTime(new Date());
				privateAttrService.update(privateAttr);
				resultMsg="更新字段权限配置成功";
			}
			return new HttpEntity(HttpStatus.OK,true,resultMsg,null);
		} catch (Exception e) {
			resultMsg="对字段权限配置操作失败";
			return new HttpEntity(HttpStatus.OK,false,resultMsg,e);
		}
	}

	/**
	 * 批量删除字段权限配置记录
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
			privateAttrService.removeByIds(aryIds);
			resultMsg="删除成功";
			return new HttpEntity(HttpStatus.OK,true,resultMsg,null);
		} catch (Exception e) {
    		resultMsg="删除失败";
		 	return new HttpEntity(HttpStatus.OK,false,resultMsg,e);
		}
	}
}
