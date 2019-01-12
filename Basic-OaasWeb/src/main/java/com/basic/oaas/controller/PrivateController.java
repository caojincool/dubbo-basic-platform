package com.basic.oaas.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.basic.framework.common.api.db.domain.PageJson;
import com.basic.framework.common.api.db.domain.PageList;
import com.basic.framework.common.api.db.domain.PageResult;
import com.basic.framework.common.api.query.QueryOP;
import com.basic.framework.common.model.DefaultQueryFilter;
import com.basic.framework.common.mybatis.support.DefaultPage;
import com.basic.framework.common.utils.result.HttpEntity;
import com.basic.framework.common.utils.result.HttpStatus;
import com.basic.framework.security.jwt.utils.UserUtils;
import com.basic.framework.web.BaseController;
import com.basic.oaas.bean.PrivateIbean;
import com.basic.oaas.model.Private;
import com.basic.oaas.model.User;
import com.basic.oaas.service.PrivateService;
import com.basic.oaas.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.basic.framework.common.utils.BeanUtils;

/**
 *
 * <pre>
 * 描述：权限控制器类
 * 构建组：basic.erp
 * 作者:lengzj
 * 邮箱:mail@com
 * 日期:2018-06-25 09:56:42
 * 版权：companyName
 * </pre>
 */
@Controller
@RequestMapping("/basic.oaas/private/")
public class PrivateController extends BaseController{

	private Logger logger = LoggerFactory.getLogger(PrivateController.class);

	@Autowired
	PrivateService privateService;
	
	@Autowired
	UserUtils userUtils;
	
	@Autowired
	UserService userService;
	
	/**
	 * 权限类型权限列表(分页条件查询)数据
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
        	Long menuId = jsonObject.getLong("menuId");
        	if (BeanUtils.isNotEmpty(menuId)) {
				queryFilter.addFilter("RES.MENU_ID", menuId, QueryOP.EQUAL);
			}
        	String privateName = jsonObject.getString("privateName");
        	if (BeanUtils.isNotEmpty(privateName)) {
        		queryFilter.addFilter("RES.PRIVATE_NAME", privateName, QueryOP.LIKE);
			}
        	String privateType = jsonObject.getString("privateType");
        	if (BeanUtils.isNotEmpty(privateType)) {
        		queryFilter.addFilter("RES.PRIVATE_TYPE", privateType, QueryOP.EQUAL);
        	}
			PageJson pageJson  =  privateService.queryPage(queryFilter);
			return new HttpEntity(HttpStatus.OK,true,"请求成功",pageJson);
	 	}catch (Exception e){
    		return new HttpEntity(HttpStatus.OK,false,"请求失败",e);
		}

	}

	/**
	 * 获取页面内权限信息
	 * @param ibean
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping("getPrivates")
	@ResponseBody
	public HttpEntity getPrivates(@RequestBody JSONObject jsonObject){
		try {
			Long userId = jsonObject.getLong("userId");
			Long menuId = jsonObject.getLong("menuId");
			BeanUtils.notEmpty(userId, "用户ID不可为空");
			BeanUtils.notEmpty(menuId, "菜单ID不可为空");
			return new HttpEntity(HttpStatus.OK,true,"请求成功",userUtils.getPrivates(menuId,userId));
		}catch (IllegalArgumentException e) {
			return new HttpEntity(HttpStatus.OK,false,e.getMessage(),null);
		} catch (Exception e) {
			return new HttpEntity(HttpStatus.OK,false,"请求失败",e);
		}
	}
	
	
	
	/**
	 * 获取页面内权限信息
	 * @param ibean
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping("getUserPrivates")
	@ResponseBody
	public HttpEntity getUserPrivates(@RequestBody JSONObject jsonObject){
		try {
			Long userId = jsonObject.getLong("userId");
			Long menuId = jsonObject.getLong("menuId");
			BeanUtils.notEmpty(userId, "用户ID不可为空");
			BeanUtils.notEmpty(menuId, "菜单ID不可为空");
			JSONObject obj = null;
			User user = userService.qryUserById(userId);
			BeanUtils.notEmpty(user, "用户不存在");
			if ("admin".equals(user.getUsername())) {
				obj = userUtils.getPrivates(menuId, userId);
			}else{
				obj = privateService.getPrivates(userId, menuId);
			}
			return new HttpEntity(HttpStatus.OK,true,"请求成功",obj);
		}catch (IllegalArgumentException e) {
			return new HttpEntity(HttpStatus.OK,false,e.getMessage(),null);
		} catch (Exception e) {
			return new HttpEntity(HttpStatus.OK,false,"请求失败",e);
		}
	}
}
