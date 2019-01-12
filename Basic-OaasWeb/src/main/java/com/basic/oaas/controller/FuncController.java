package com.basic.oaas.controller;

import com.alibaba.fastjson.JSONObject;
import com.basic.framework.common.api.db.domain.PageJson;
import com.basic.framework.common.model.DefaultQueryFilter;
import com.basic.framework.common.utils.result.HttpEntity;
import com.basic.framework.common.utils.result.HttpStatus;
import com.basic.framework.web.BaseController;
import com.basic.oaas.model.PrivateFunc;
import com.basic.oaas.service.PrivateFuncService;
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
 * 描述：功能按钮 控制器类
 * 构建组：basic.erp
 * 作者:lengzj
 * 邮箱:mail@com
 * 日期:2018-06-25 09:08:26
 * 版权：companyName
 * </pre>
 */
@Controller
@RequestMapping("/basic.oaas/func/")
public class FuncController extends BaseController{

	private Logger logger = LoggerFactory.getLogger(FuncController.class);

	@Autowired
	PrivateFuncService privateFuncService;

	/**
	 * 功能按钮列表数据(不分页)
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
			List<PrivateFunc> list = privateFuncService.query(queryFilter);
			return new HttpEntity(HttpStatus.OK,true,"请求成功",list);
		}catch (Exception e){
			return new HttpEntity(HttpStatus.OK,false,"请求失败",e);
		}
		
	}

	/**
	 * 功能按钮明细页面
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
		PrivateFunc privateFunc=null;
 		try{
			Long funcId=jsonObject.getLong("id");
			privateFunc=privateFuncService.qryByPrimaryKey(funcId);
			return new HttpEntity(HttpStatus.OK,true,"请求成功",privateFunc);
	 	}catch (Exception e){
    		return new HttpEntity(HttpStatus.OK,false,"请求失败",e);
		}
	}

	/**
	 * 保存功能按钮信息
	 * @param  jsonObject
	 * @throws Exception
	 * void
	 * @exception
	 */
	@RequestMapping("save")
	@ResponseBody
	public HttpEntity save(@RequestBody JSONObject jsonObject){
		PrivateFunc privateFunc = JSONObject.toJavaObject(jsonObject.getJSONObject("privateFunc"),PrivateFunc.class);
		String resultMsg=null;
		try {
			if(StringUtil.isEmpty(privateFunc.getFuncId())) {

				privateFunc.setCreateTime(new Date());
				privateFunc.setState("10A");
				privateFuncService.create(privateFunc);
				resultMsg="添加功能按钮成功";
			}else{
				privateFunc.setModifyTime(new Date());
				privateFuncService.update(privateFunc);
				resultMsg="更新功能按钮成功";
			}
			return new HttpEntity(HttpStatus.OK,true,resultMsg,null);
		} catch (Exception e) {
			resultMsg="对功能按钮操作失败";
			return new HttpEntity(HttpStatus.OK,false,resultMsg,e);
		}
	}

	/**
	 * 批量删除功能按钮记录
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
			privateFuncService.removeBatchByPrivateFuncIds(aryIds);
			resultMsg="删除成功";
			return new HttpEntity(HttpStatus.OK,true,resultMsg,null);
		} catch (Exception e) {
    		resultMsg="删除失败";
		 	return new HttpEntity(HttpStatus.OK,false,resultMsg,e);
		}
	}
}
