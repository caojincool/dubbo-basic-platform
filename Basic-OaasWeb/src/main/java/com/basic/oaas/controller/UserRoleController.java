package com.basic.oaas.controller;

import com.alibaba.dubbo.common.utils.Assert;
import com.alibaba.fastjson.JSONObject;
import com.basic.framework.common.api.query.QueryOP;
import com.basic.framework.common.model.DefaultQueryFilter;
import com.basic.framework.common.utils.result.HttpEntity;
import com.basic.framework.common.utils.result.HttpStatus;
import com.basic.framework.security.jwt.utils.UserUtils;
import com.basic.framework.web.BaseController;
import com.basic.oaas.bean.UserRoleIbean;
import com.basic.oaas.model.User;
import com.basic.oaas.model.UserRole;
import com.basic.oaas.service.UserRoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.basic.framework.common.utils.BeanUtils;
import java.util.List;

/**
 *
 * <pre>
 * 描述：角色用户关系 控制器类
 * 构建组：basic.erp
 * 作者:lengzj
 * 邮箱:mail@com
 * 日期:2018-06-26 15:25:35
 * 版权：companyName
 * </pre>
 */
@Controller
@RequestMapping("/basic.oaas/userRole/")
public class UserRoleController extends BaseController{

	private Logger logger = LoggerFactory.getLogger(UserRoleController.class);

	@Autowired
	UserRoleService userRoleService;
	@Autowired
	UserUtils userUtils;

	/**
	 * 角色用户关系列表数据(不分页)
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
			Long userId = jsonObject.getLong("userId");
			Assert.notNull(userId, "帐号ID不能为空");
			queryFilter.addFilter("OUR.USER_ID", userId, QueryOP.EQUAL);
			List<UserRole> list = userRoleService.query(queryFilter);
			return new HttpEntity(HttpStatus.OK,true,"请求成功",list);
		}catch (IllegalArgumentException e) {
			return new HttpEntity(HttpStatus.OK,false,e.getMessage(),null);
		}catch (Exception e){
			return new HttpEntity(HttpStatus.OK,false,"请求失败",e);
		}
		
	}

	/**
	 * 保存角色用户关系信息
	 * @param  jsonObject
	 * @throws Exception
	 * void
	 * @exception
	 */
	@RequestMapping("save")
	@ResponseBody
	public HttpEntity save(@RequestBody JSONObject jsonObject){
		UserRoleIbean ibean = JSONObject.toJavaObject(jsonObject,UserRoleIbean.class);
		String resultMsg=null;
		try {
			Assert.notNull(ibean, "对象不可为空");
			Assert.notNull(ibean.getUserId(), "帐号ID不可为空");
			if (BeanUtils.isNotEmpty(ibean.getUserRoles())) {
				User currentUser = userUtils.getUser();
				for ( UserRole ur : ibean.getUserRoles()) {
					if (currentUser!=null) {
						ur.setCreateUserId(currentUser.getUserId());
					}
				}
			}
			userRoleService.createBatchUserRole(ibean);
			//登录时再放进缓存，先去掉
			//userUtils.refreshPrivatesDetail(ibean.getUserId());
			resultMsg = "保存成功";
			return new HttpEntity(HttpStatus.OK,true,resultMsg,null);
		} catch (Exception e) {
			resultMsg="保存失败";
			return new HttpEntity(HttpStatus.OK,false,resultMsg,e);
		}
	}

}
