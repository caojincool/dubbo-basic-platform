package com.basic.oaas.controller;

import com.alibaba.fastjson.JSONObject;
import com.basic.framework.common.utils.result.HttpEntity;
import com.basic.framework.common.utils.result.HttpStatus;
import com.basic.framework.web.BaseController;
import com.basic.oaas.model.DataScopeType;
import com.basic.oaas.service.DataScopeTypeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.List;

/**
 * 
 * <pre> 
 * @Description：范围类型控制器类
 * @author:lengzj
 * @email:mail@com
 * @date:2018-06-27 15:34:42
 * @copyright：companyName
 * </pre>
 */
@Controller
@RequestMapping("/basic.oaas/dataScopeType/")
public class DataScopeTypeController extends BaseController{

	private Logger logger = LoggerFactory.getLogger(DataScopeTypeController.class);

	@Autowired
	DataScopeTypeService dataScopeTypeService;
	
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
	    	List<DataScopeType> list = dataScopeTypeService.qryAllScopeTypeList();
	    	return new HttpEntity(HttpStatus.OK,true,"请求成功",list);
	    }catch (Exception e){
	    	return new HttpEntity(HttpStatus.OK,false,"请求失败",e);
    	}
    }
	

	
}
