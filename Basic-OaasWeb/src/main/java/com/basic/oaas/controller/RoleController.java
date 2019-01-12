package com.basic.oaas.controller;

import com.alibaba.dubbo.common.utils.Assert;
import com.alibaba.fastjson.JSONObject;
import com.basic.framework.common.api.db.domain.PageJson;
import com.basic.framework.common.api.query.QueryOP;
import com.basic.framework.common.model.DefaultQueryFilter;
import com.basic.framework.common.utils.result.HttpEntity;
import com.basic.framework.common.utils.result.HttpStatus;
import com.basic.framework.security.jwt.utils.UserUtils;
import com.basic.framework.web.BaseController;
import com.basic.oaas.model.Role;
import com.basic.oaas.model.User;
import com.basic.oaas.service.RoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
 * 描述：角色 控制器类
 * 构建组：basic.erp
 * 作者:lengzj
 * 邮箱:mail@com
 * 日期:2018-06-23 08:49:59
 * 版权：companyName
 * </pre>
 */
@Controller
@RequestMapping("/basic.oaas/role/")
public class RoleController extends BaseController{

	private Logger logger = LoggerFactory.getLogger(RoleController.class);

	@Autowired
	RoleService roleService;
	@Autowired
	UserUtils userUtils;

	/**
	 * 角色列表(分页条件查询)数据
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
        	Long catalogId = jsonObject.getLong("catalogId");
        	if (!StringUtil.isEmpty(catalogId)) {
				queryFilter.addFilter("ROLE.CATALOG_ID", catalogId, QueryOP.EQUAL);
			}
        	String roleName = jsonObject.getString("roleName");
        	if (BeanUtils.isNotEmpty(roleName)) {
				queryFilter.addFilter("ROLE.ROLE_NAME", roleName, QueryOP.LIKE);
			}
			PageJson pageJson  =  roleService.queryPage(queryFilter);
			return new HttpEntity(HttpStatus.OK,true,"请求成功",pageJson);
	 	}catch (Exception e){
    		return new HttpEntity(HttpStatus.OK,false,"请求失败",e);
		}

	}
	
	/**
	 * 角色列表数据(不分页)
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
			List<Role> list = roleService.query(queryFilter);
			return new HttpEntity(HttpStatus.OK,true,"请求成功",list);
		}catch (Exception e){
			return new HttpEntity(HttpStatus.OK,false,"请求失败",e);
		}
		
	}


	/**
	 * 角色明细页面
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
		Role role=null;
 		try{
			Long roleId=jsonObject.getLong("id");
			Assert.notNull(roleId, "角色ID不能为空");
			role=roleService.qryByPrimaryKey(roleId);
			return new HttpEntity(HttpStatus.OK,true,"请求成功",role);
	 	}catch (IllegalArgumentException e) {
			return new HttpEntity(HttpStatus.OK,false,e.getMessage(),null);
		}catch (Exception e){
    		return new HttpEntity(HttpStatus.OK,false,"请求失败",e);
		}
	}

	/**
	 * 保存角色信息
	 * @param  jsonObject
	 * @throws Exception
	 * void
	 * @exception
	 */
	@RequestMapping("save")
	@ResponseBody
	public HttpEntity save(@RequestBody JSONObject jsonObject){
		Role role = JSONObject.toJavaObject(jsonObject,Role.class);
		String resultMsg=null;
		try {
			Assert.notNull(role, "角色对象不能为空");
			Assert.notNull(role.getRoleName(), "角色名称不能为空");
			Assert.notNull(role.getCatalogId(), "角色目录ID不能为空");
			
			User currentUser = userUtils.getUser();
			if (currentUser!=null) {
				if(StringUtil.isEmpty(role.getRoleId())) {
					role.setCreateUserId(currentUser.getUserId());
				}else{
					role.setModifyUserId(currentUser.getUserId());
				}
			}
			
			roleService.createOrModify(role);
			resultMsg = "保存成功";
			return new HttpEntity(HttpStatus.OK,true,resultMsg,null);
		}catch (IllegalArgumentException e) {
			return new HttpEntity(HttpStatus.OK,false,e.getMessage(),null);
		} catch (Exception e) {
			resultMsg="保存失败";
			return new HttpEntity(HttpStatus.OK,false,resultMsg,e);
		}
	}

	/**
	 * 批量删除角色记录
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
			roleService.removeBatchByRoleIds(aryIds);
			resultMsg="删除成功";
			return new HttpEntity(HttpStatus.OK,true,resultMsg,null);
		} catch (Exception e) {
    		resultMsg="删除失败";
		 	return new HttpEntity(HttpStatus.OK,false,resultMsg,e);
		}
	}
	
	/**
	 * 角色明细页面
	 * @param  jsonObject
	 * @return
	 * @throws Exception
	 * HttpEntity
	 * @exception
	 * @since  1.0.0
	 */
	@RequestMapping("getUserRoles")
	@ResponseBody
	public HttpEntity getUserRoles(@RequestBody JSONObject jsonObject){
 		List<Role> roles;
		try{
			String userName = jsonObject.getString("userName");
			Assert.notNull(userName, "帐号ID不能为空");
			roles=roleService.qryRoleByUsername(userName);
			return new HttpEntity(HttpStatus.OK,true,"请求成功",roles);
	 	}catch (IllegalArgumentException e) {
			return new HttpEntity(HttpStatus.OK,false,e.getMessage(),null);
		}catch (Exception e){
    		return new HttpEntity(HttpStatus.OK,false,"请求失败",e);
		}
	}
}
