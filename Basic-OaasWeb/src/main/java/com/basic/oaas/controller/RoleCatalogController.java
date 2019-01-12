package com.basic.oaas.controller;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.alibaba.fastjson.JSONObject;
import com.basic.framework.common.api.query.QueryOP;
import com.basic.framework.common.model.DefaultQueryFilter;
import com.basic.framework.common.utils.result.HttpEntity;
import com.basic.framework.common.utils.result.HttpStatus;
import com.basic.framework.security.jwt.utils.UserUtils;
import com.basic.framework.web.BaseController;
import com.basic.oaas.model.RoleCatalog;
import com.basic.oaas.model.User;
import com.basic.oaas.service.RoleCatalogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.basic.framework.common.utils.BeanUtils;
import java.util.Collections;
import java.util.List;

/**
 *
 * <pre>
 * 描述：角色目录 控制器类
 * 构建组：basic.erp
 * 作者:lengzj
 * 邮箱:mail@com
 * 日期:2018-06-23 09:28:09
 * 版权：companyName
 * </pre>
 */
@Controller
@RequestMapping("/basic.oaas/roleCatalog/")
public class RoleCatalogController extends BaseController{

	private Logger logger = LoggerFactory.getLogger(RoleCatalogController.class);

	@Autowired
	RoleCatalogService roleCatalogService;
	@Autowired
	UserUtils userUtils;

	
	/**
     * 获取目录树
     * @param jsonObject
     * @return
     * @throws Exception
     * HttpEntity
     * @exception
     */
    @RequestMapping("queryTree")
    @ResponseBody
    public HttpEntity queryTree(@RequestBody JSONObject jsonObject){
	    try{
	    	DefaultQueryFilter queryFilter = new DefaultQueryFilter();
	    	String parentId = jsonObject.getString("parentId");
	    	if (StringUtils.isNotEmpty(parentId)) {
				queryFilter.addFilter("PARENT_CATALOG_ID", parentId, QueryOP.EQUAL);
			}
			List<RoleCatalog> list  =  roleCatalogService.query(queryFilter);
			if (BeanUtils.isNotEmpty(list)) {
				return new HttpEntity(HttpStatus.OK,true,"请求成功",BeanUtils.listToTree(list));
			}else {
				return new HttpEntity(HttpStatus.OK,true,"请求成功",Collections.EMPTY_LIST);
			}
	    }catch (Exception e){
	    	return new HttpEntity(HttpStatus.OK,false,"请求失败",e);
    	}
    }
    

	/**
	 * 角色目录明细页面
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
		RoleCatalog roleCatalog=null;
 		try{
			Long catalogId=jsonObject.getLong("id");
			BeanUtils.notEmpty(catalogId,"目录ID不能为空");
			roleCatalog=roleCatalogService.qryByPrimaryKey(catalogId);
			return new HttpEntity(HttpStatus.OK,true,"请求成功",roleCatalog);
	 	}catch (IllegalArgumentException e) {
			return new HttpEntity(HttpStatus.OK,false,e.getMessage(),null);
		}catch (Exception e){
    		return new HttpEntity(HttpStatus.OK,false,"请求失败",e);
		}
	}

	/**
	 * 保存角色目录信息
	 * @param  jsonObject
	 * @throws Exception
	 * void
	 * @exception
	 */
	@RequestMapping("save")
	@ResponseBody
	public HttpEntity save(@RequestBody JSONObject jsonObject){
		RoleCatalog roleCatalog = JSONObject.toJavaObject(jsonObject,RoleCatalog.class);
		String resultMsg=null;
		try {
			BeanUtils.notEmpty(roleCatalog, "目录对象不能为空");
			BeanUtils.notEmpty(roleCatalog.getCatalogName(), "目录名称不能为空");
			User currentUser = userUtils.getUser();
			if (currentUser!=null) {
				if (BeanUtils.isEmpty(roleCatalog.getCatalogId())) {
					roleCatalog.setCreateUserId(currentUser.getUserId());
				}else {
					roleCatalog.setModifyUserId(currentUser.getUserId());
				}
			}
			if (BeanUtils.isEmpty(roleCatalog.getParentCatalogId())) {
				roleCatalog.setParentCatalogId(-1L);
			}
			roleCatalog = roleCatalogService.createOrModify(roleCatalog);
			resultMsg = "保存成功";
			return new HttpEntity(HttpStatus.OK,true,resultMsg,roleCatalog);
		}catch (IllegalArgumentException e) {
			return new HttpEntity(HttpStatus.OK,false,e.getMessage(),null);
		}catch (Exception e) {
			resultMsg="保存失败";
			return new HttpEntity(HttpStatus.OK,false,resultMsg,e);
		}
	}

	/**
	 * 批量删除角色目录记录
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
			if (BeanUtils.isNotEmpty(aryIds)) {
				for (int i = 0; i < aryIds.length; i++) {
					roleCatalogService.modifyAllStateById(aryIds[i]);
				}
			}
			resultMsg="删除成功";
			return new HttpEntity(HttpStatus.OK,true,resultMsg,null);
		} catch (Exception e) {
    		resultMsg="删除失败";
		 	return new HttpEntity(HttpStatus.OK,false,resultMsg,e);
		}
	}
}
