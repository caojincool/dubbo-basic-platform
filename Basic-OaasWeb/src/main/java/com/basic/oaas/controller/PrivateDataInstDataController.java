package com.basic.oaas.controller;

import com.alibaba.fastjson.JSONObject;
import com.basic.framework.common.api.db.domain.PageJson;
import com.basic.framework.common.api.query.QueryOP;
import com.basic.framework.common.model.DefaultQueryFilter;
import com.basic.framework.common.utils.result.HttpEntity;
import com.basic.framework.common.utils.result.HttpStatus;
import com.basic.framework.web.BaseController;
import com.basic.oaas.bean.PrivateDataInstDataIbean;
import com.basic.oaas.model.PrivateDataInstData;
import com.basic.oaas.service.PrivateDataInstDataService;
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
 * @Description：数据权限实例数据 控制器类
 * @author:lengzj
 * @email:mail@com
 * @date:2018-06-27 14:52:49
 * @copyright：companyName
 * </pre>
 */
@Controller
@RequestMapping("/basic.oaas/privateDataInstData/")
public class PrivateDataInstDataController extends BaseController{

	private Logger logger = LoggerFactory.getLogger(PrivateDataInstDataController.class);

	@Autowired
	PrivateDataInstDataService privateDataInstDataService;

	/**
	 * 数据权限实例数据列表(分页条件查询)数据
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
        	Long dataInstId = jsonObject.getLong("dataInstId");
        	BeanUtils.notEmpty(dataInstId, "数据权限实例ID不能为空");
        	queryFilter.addFilter("DATA_INST_ID", dataInstId, QueryOP.EQUAL);
			PageJson pageJson  =  privateDataInstDataService.queryPage(queryFilter);
			return new HttpEntity(HttpStatus.OK,true,"请求成功",pageJson);
	 	}catch (IllegalArgumentException e) {
			return new HttpEntity(HttpStatus.OK,false,e.getMessage(),null);
		}catch (Exception e){
    		return new HttpEntity(HttpStatus.OK,false,"请求失败",e);
		}

	}
	

	/**
	 * 保存数据权限实例数据信息
	 * @param  jsonObject
	 * @throws Exception
	 * void
	 * @exception
	 */
	@RequestMapping("save")
	@ResponseBody
	public HttpEntity save(@RequestBody JSONObject jsonObject){
		PrivateDataInstDataIbean ibean = JSONObject.toJavaObject(jsonObject,PrivateDataInstDataIbean.class);
		String resultMsg=null;
		try {
			
			BeanUtils.notEmpty(ibean, "对象不能为空");
			BeanUtils.notEmpty(ibean.getDataInstId(), "数据权限实例ID不能为空");
			if(BeanUtils.isNotEmpty(ibean.getPrivateDataInstDatas())) {
				for ( PrivateDataInstData data : ibean.getPrivateDataInstDatas()) {
					BeanUtils.notEmpty(data, "实例数据不能为空");
					BeanUtils.notEmpty(data.getDataDataId(), "数据ID不能为空");
					BeanUtils.notEmpty(data.getSourceId(), "源数据ID不能为空");
					BeanUtils.notEmpty(data.getSourceName(), "源数据名称不能为空");
					data.setDataInstId(ibean.getDataInstId());
				}
				privateDataInstDataService.createSelective(ibean);
			}
			resultMsg="保存成功";
			return new HttpEntity(HttpStatus.OK,true,resultMsg,null);
		}catch (IllegalArgumentException e) {
			return new HttpEntity(HttpStatus.OK,false,e.getMessage(),null);
		} catch (Exception e) {
			resultMsg="保存失败";
			return new HttpEntity(HttpStatus.OK,false,resultMsg,e);
		}
	}

	/**
	 * 批量删除数据权限实例数据记录
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
			privateDataInstDataService.removeBatchByDataInstDataIds(aryIds);
			resultMsg="删除成功";
			return new HttpEntity(HttpStatus.OK,true,resultMsg,null);
		} catch (Exception e) {
    		resultMsg="删除失败";
		 	return new HttpEntity(HttpStatus.OK,false,resultMsg,e);
		}
	}
}
