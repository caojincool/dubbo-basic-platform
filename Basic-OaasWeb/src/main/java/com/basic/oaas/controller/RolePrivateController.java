package com.basic.oaas.controller;

import com.alibaba.fastjson.JSONObject;
import com.basic.framework.common.api.db.domain.PageJson;
import com.basic.framework.common.api.query.QueryOP;
import com.basic.framework.common.model.DefaultQueryFilter;
import com.basic.framework.common.utils.result.HttpEntity;
import com.basic.framework.common.utils.result.HttpStatus;
import com.basic.framework.security.jwt.utils.UserUtils;
import com.basic.framework.web.BaseController;
import com.basic.oaas.bean.RolePrivateIbean;
import com.basic.oaas.model.RolePrivate;
import com.basic.oaas.model.User;
import com.basic.oaas.service.RolePrivateService;
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
 * 描述：角色权限
 控制器类
 * 构建组：basic.erp
 * 作者:lengzj
 * 邮箱:mail@com
 * 日期:2018-06-26 08:59:20
 * 版权：companyName
 * </pre>
 */
@Controller
@RequestMapping("/basic.oaas/rolePrivate/")
public class RolePrivateController extends BaseController{

	private Logger logger = LoggerFactory.getLogger(RolePrivateController.class);

	@Autowired
	RolePrivateService rolePrivateService;
	@Autowired
	UserUtils userUtils;

	/**
	 * 角色权限列表(分页条件查询)数据
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
        	Long roleId = jsonObject.getLong("roleId");
        	BeanUtils.notEmpty("roleId", "角色ID不可为空");
        	queryFilter.addFilter("RES.ROLE_ID", roleId, QueryOP.EQUAL);
        	String privateName = jsonObject.getString("privateName");
        	if (BeanUtils.isNotEmpty(privateName)) {
        		queryFilter.addFilter("OP.PRIVATE_NAME", privateName, QueryOP.LIKE);
			}
        	Long menuId = jsonObject.getLong("menuId");
        	if (BeanUtils.isNotEmpty(menuId)) {
        		queryFilter.addFilter("RES.menuId", menuId, QueryOP.EQUAL);
			}
			PageJson pageJson  =  rolePrivateService.queryPage(queryFilter);
			return new HttpEntity(HttpStatus.OK,true,"请求成功",pageJson);
	 	}catch (IllegalArgumentException e) {
			return new HttpEntity(HttpStatus.OK,false,e.getMessage(),null);
		} catch (Exception e){
    		return new HttpEntity(HttpStatus.OK,false,"请求失败",e);
		}

	}
	
	/**
	 * 角色权限列表数据(不分页)
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
			Long roleId = jsonObject.getLong("roleId");
        	BeanUtils.notEmpty(roleId, "角色ID不可为空");
        	queryFilter.addFilter("RES.ROLE_ID", roleId, QueryOP.EQUAL);
			List<RolePrivate> list = rolePrivateService.query(queryFilter);
			return new HttpEntity(HttpStatus.OK,true,"请求成功",list);
		}catch (IllegalArgumentException e) {
			return new HttpEntity(HttpStatus.OK,false,e.getMessage(),null);
		} catch (Exception e){
			return new HttpEntity(HttpStatus.OK,false,"请求失败",e);
		}
		
	}

	/**
	 * 保存角色权限信息
	 * @param  jsonObject
	 * @throws Exception
	 * void
	 * @exception
	 */
	@RequestMapping("save")
	@ResponseBody
	public HttpEntity save(@RequestBody JSONObject jsonObject){
		RolePrivateIbean ibean = JSONObject.toJavaObject(jsonObject,RolePrivateIbean.class);
		String resultMsg=null;
		try {
			BeanUtils.notEmpty(ibean, "对象不可为空");
			BeanUtils.notEmpty(ibean.getRoleId(), "角色ID不可为空");
			rolePrivateService.createBatchRolePrivate(ibean,userUtils.getUser());
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
	 * 批量删除角色权限记录
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
			rolePrivateService.removeBatchByRolePrivateIds(aryIds);
			resultMsg="删除成功";
			return new HttpEntity(HttpStatus.OK,true,resultMsg,null);
		} catch (Exception e) {
    		resultMsg="删除失败";
		 	return new HttpEntity(HttpStatus.OK,false,resultMsg,e);
		}
	}
}
