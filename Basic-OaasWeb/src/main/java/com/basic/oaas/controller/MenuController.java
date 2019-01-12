package com.basic.oaas.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.basic.framework.common.api.db.domain.PageJson;
import com.basic.framework.common.api.query.QueryOP;
import com.basic.framework.common.model.DefaultQueryFilter;
import com.basic.framework.common.utils.result.HttpEntity;
import com.basic.framework.common.utils.result.HttpStatus;
import com.basic.framework.security.jwt.utils.UserUtils;
import com.basic.framework.web.BaseController;
import com.basic.oaas.bean.PrivateIbean;
import com.basic.oaas.define.PrivateType;
import com.basic.oaas.model.Private;
import com.basic.oaas.model.PrivateFunc;
import com.basic.oaas.model.PrivateMenu;
import com.basic.oaas.model.User;
import com.basic.oaas.service.PrivateAttrService;
import com.basic.oaas.service.PrivateFuncService;
import com.basic.oaas.service.PrivateMenuService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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


/**
 *
 * <pre>
 * 描述：菜单表 控制器类
 * 构建组：basic.erp
 * 作者:lengzj
 * 邮箱:mail@com
 * 日期:2018-06-23 14:48:01
 * 版权：companyName
 * </pre>
 */
@Controller
@RequestMapping("/basic.oaas/menu/")
public class MenuController extends BaseController{

	private Logger logger = LoggerFactory.getLogger(MenuController.class);

	@Autowired
	PrivateMenuService privateMenuService;
	@Autowired
	UserUtils userUtils;
	@Autowired
	PrivateFuncService privateFuncService;
	@Autowired
	PrivateAttrService privateAttrService;

	/**
	 * 获取菜单树
	 * @param jsonObject
	 * @return
	 * @throws Exception
	 * HttpEntity
	 * @exception
	 */
	@RequestMapping("queryTree")
	@ResponseBody
	public HttpEntity queryTree(@RequestBody JSONObject jsonObject){
		DefaultQueryFilter queryFilter =getQueryFilter(jsonObject);
		try{
			Long parentId = jsonObject.getLong("parentId");
			if (!StringUtil.isEmpty(parentId)) {
				queryFilter.addFilter("MENU.PARENT_ID", parentId, QueryOP.EQUAL);
			}
			List<PrivateMenu> list = privateMenuService.query(queryFilter);
			return new HttpEntity(HttpStatus.OK,true,"请求成功",BeanUtils.listToTree(list));
		}catch (Exception e){
			return new HttpEntity(HttpStatus.OK,false,"请求失败",e);
		}
		
	}
	
	
	/**
	 * 菜单表明细页面
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
		PrivateMenu privateMenu=null;
 		try{
			Long menuId=jsonObject.getLong("id");
			privateMenu=privateMenuService.qryByPrimaryKey(menuId);
			return new HttpEntity(HttpStatus.OK,true,"请求成功",privateMenu);
	 	}catch (Exception e){
    		return new HttpEntity(HttpStatus.OK,false,"请求失败",e);
		}
	}

	/**
	 * 保存菜单表信息
	 * @param  jsonObject
	 * @throws Exception
	 * void
	 * @exception
	 */
	@RequestMapping("save")
	@ResponseBody
	public HttpEntity save(@RequestBody JSONObject jsonObject){
		PrivateMenu menu = JSONObject.toJavaObject(jsonObject,PrivateMenu.class);
		String resultMsg=null;
		try {
			BeanUtils.notEmpty(menu, "菜单对象不能为空");
			BeanUtils.notEmpty(menu.getMenuCode(),"菜单编码不能为空");
			BeanUtils.notEmpty(menu.getMenuName(),"菜单名称不能为空");
			
			DefaultQueryFilter queryFilter = new DefaultQueryFilter();
			queryFilter.addFilter("MENU.MENU_CODE", menu.getMenuCode(), QueryOP.EQUAL);
			if (BeanUtils.isNotEmpty(menu.getMenuId())) {
				queryFilter.addFilter("MENU.MENU_ID", menu.getMenuId(), QueryOP.NOT_EQUAL);
			}
			if (BeanUtils.isNotEmpty(privateMenuService.query(queryFilter))) {
				resultMsg = "编码不可重复";
				return new HttpEntity(HttpStatus.OK,false,resultMsg,null);
			}else {
				User currentUser = userUtils.getUser();
				if (currentUser!=null) {
					if (BeanUtils.isEmpty(menu.getMenuId())) {
						menu.setCreateUserId(currentUser.getUserId());
					}else {
						menu.setModifyUserId(currentUser.getUserId());
					}
				}
			}
			if (BeanUtils.isEmpty(menu.getParentMenuId())) {
				menu.setParentMenuId(-1L);
			}
			menu = privateMenuService.createOrModify(menu);
			resultMsg = "保存成功";
			return new HttpEntity(HttpStatus.OK,true,resultMsg,menu);
		}catch (IllegalArgumentException e) {
			return new HttpEntity(HttpStatus.OK,false,e.getMessage(),null);
		} catch (Exception e) {
			resultMsg="保存失败";
			return new HttpEntity(HttpStatus.OK,false,resultMsg,e);
		}
	}

	/**
	 * 批量删除菜单表记录
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
			privateMenuService.removeBatchByPrivateMenuIds(aryIds);
			resultMsg="删除成功";
			return new HttpEntity(HttpStatus.OK,true,resultMsg,null);
		} catch (Exception e) {
    		resultMsg="删除失败";
		 	return new HttpEntity(HttpStatus.OK,false,resultMsg,e);
		}
	}
	
	/**
	 * 获取菜单权限
	 * @param ibean
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping("getPrivateMenus")
	@ResponseBody
	public HttpEntity getPrivateMenus(@RequestBody JSONObject jsonObject){
		try {
			Long userId = jsonObject.getLong("userId");
			BeanUtils.notEmpty(userId, "帐号ID不可为空");
			List<PrivateMenu> list = userUtils.getPrivateMenus(userId);
			return new HttpEntity(HttpStatus.OK,true,"请求成功",list);
		}catch (IllegalArgumentException e) {
			return new HttpEntity(HttpStatus.OK,false,e.getMessage(),null);
		} catch (Exception e) {
			return new HttpEntity(HttpStatus.OK,false,"请求失败",e);
		}
	}
	
	/**
	 * 初始化权限
	 * @Description:
	 * @author lengzj
	 * @param jsonObject
	 * @return
	 */
	@RequestMapping("init")
	@ResponseBody
	public HttpEntity init(@RequestBody JSONArray array){
		try {
			privateMenuService.getChildren(array);
			return new HttpEntity(HttpStatus.OK,true,"请求成功",null);
		}catch (IllegalArgumentException e) {
			return new HttpEntity(HttpStatus.OK,false,e.getMessage(),null);
		} catch (Exception e) {
			return new HttpEntity(HttpStatus.OK,false,"请求失败",e);
		}
	}
	
	
	
}
