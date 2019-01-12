package com.basic.oaas.controller;

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
import com.basic.oaas.bean.UserIBean;
import com.basic.oaas.model.User;
import com.basic.oaas.service.UserService;

import io.jsonwebtoken.lang.Assert;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.basic.framework.common.utils.BeanUtils;
import com.basic.framework.common.utils.EncryptUtil;
import com.basic.framework.common.utils.StringUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * <pre>
 * 描述：帐号信息表 控制器类
 * 构建组：basic.erp
 * 作者:lengzj
 * 邮箱:mail@com
 * 日期:2018-06-22 16:02:55
 * 版权：companyName
 * </pre>
 */
@Controller
@RequestMapping("/basic.oaas/user/")
public class UserController extends BaseController{

	private Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	UserService userService;
	@Autowired
	UserUtils userUtils;

	/**
	 * 帐号信息表列表(分页条件查询)数据
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
        	String type = jsonObject.getString("userType");
        	if (StringUtils.isNotEmpty(type)) {
				queryFilter.addFilter("U.USER_TYPE", type, QueryOP.EQUAL);
			}
        	String userName = jsonObject.getString("userName");
        	if (StringUtils.isNotEmpty(userName)) {
        		queryFilter.addFilter("U.USERNAME", userName, QueryOP.LIKE);
        	}
        	String userText = jsonObject.getString("userText");
        	if (StringUtils.isNotEmpty(userText)) {
        		queryFilter.addFilter("U.USER_TEXT", userText, QueryOP.LIKE);
        	}
        	Date expireTimeStart = jsonObject.getDate("expireTimeStart");
        	if (BeanUtils.isNotEmpty(expireTimeStart)) {
				queryFilter.addFilter("U.EXPIRE_TIME", expireTimeStart, QueryOP.GREAT_EQUAL);
			}
        	Date expireTimeEnd = jsonObject.getDate("expireTimeEnd");
        	if (BeanUtils.isNotEmpty(expireTimeEnd)) {
				queryFilter.addFilter("U.EXPIRE_TIME", expireTimeEnd, QueryOP.LESS_EQUAL);
			}
			PageJson pageJson  =  userService.queryPage(queryFilter);
			if (BeanUtils.isNotEmpty(pageJson) && BeanUtils.isNotEmpty(pageJson.getRows())) {
				for (int i = 0; i < pageJson.getRows().size(); i++) {
					User user = (User) pageJson.getRows().get(i);
					user.setUserPassword(null);
				}
			}
			return new HttpEntity(HttpStatus.OK,true,"请求成功",pageJson);
	 	}catch (Exception e){
    		return new HttpEntity(HttpStatus.OK,false,"请求失败",e);
		}

	}
	
	/**
	 * 帐号信息表明细页面
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
		User user=null;
 		try{
			Long userId=jsonObject.getLong("id");
			BeanUtils.notEmpty("userId", "ID不能为空");
			user=userService.qryUserById(userId);
			user.setUserPassword(null);
			return new HttpEntity(HttpStatus.OK,true,"请求成功",user);
	 	}catch (IllegalArgumentException e) {
			return new HttpEntity(HttpStatus.OK,false,e.getMessage(),null);
		}catch (Exception e){
    		return new HttpEntity(HttpStatus.OK,false,"请求失败",e);
		}
	}

	/**
	 * 保存帐号信息表信息
	 * @param  jsonObject
	 * @throws Exception
	 * void
	 * @exception
	 */
	@RequestMapping("save")
	@ResponseBody
	public HttpEntity save(@RequestBody JSONObject jsonObject){
		User user = JSONObject.toJavaObject(jsonObject,User.class);
		String resultMsg=null;
		try {
			BeanUtils.notEmpty(user,"帐号对象不能为空");
			BeanUtils.notEmpty(user.getUserText(),"帐号名称不能为空");
			BeanUtils.notEmpty(user.getUsername(),"登录帐号不能为空");
			BeanUtils.notEmpty(user.getUserType(),"帐号类型不能为空");
			
			if ("admin".equals(user.getUsername())) {
				resultMsg = "超级管理员别乱动哦！";
				return new HttpEntity(HttpStatus.OK,false,resultMsg,null);
			}
			
			DefaultQueryFilter queryFilter = new DefaultQueryFilter();
			if (!StringUtil.isEmpty(user.getUserId())) {
				queryFilter.addFilter("U.USER_ID", user.getUserId(), QueryOP.NOT_EQUAL);
			}
			queryFilter.addFilter("U.USERNAME", user.getUsername(), QueryOP.EQUAL);
			if (BeanUtils.isNotEmpty(userService.query(queryFilter))) {
				resultMsg = "登录帐号不能重复";
				return new HttpEntity(HttpStatus.OK,false,resultMsg,null);
			}
			User currentUser = userUtils.getUser();
			if(StringUtil.isEmpty(user.getUserId())) {
				user.setDataPrivExemption(0);
				user.setSource(2);
				BeanUtils.notEmpty(user.getUserPassword(),"登录密码不能为空");
				if (currentUser!=null) {
					user.setCreateUserId(currentUser.getUserId());
				}
				user = userService.createSelective(user);
			}else{
				if (currentUser!=null) {
					user.setModifyUserId(currentUser.getUserId());
				}
				userService.modifyByPrimaryKey(user);
			}
			userUtils.refreshUserDetail(user.getUserId());
			resultMsg = "保存成功";
			return new HttpEntity(HttpStatus.OK,true,resultMsg,null);
		}catch (IllegalArgumentException e) {
			return new HttpEntity(HttpStatus.OK,false,e.getMessage(),null);
		} catch (Exception e) {
			resultMsg="操作失败";
			return new HttpEntity(HttpStatus.OK,false,resultMsg,e);
		}
	}

	/**
	 * 批量删除帐号信息表记录
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
			userService.removeBatchByUserIds(aryIds);
			resultMsg="删除成功";
			return new HttpEntity(HttpStatus.OK,true,resultMsg,null);
		} catch (Exception e) {
    		resultMsg="删除失败";
		 	return new HttpEntity(HttpStatus.OK,false,resultMsg,e);
		}
	}
	
	/**
	 * 重置密码
	 * @param  jsonObject
	 * @return
	 * @throws Exception
	 * HttpEntity
	 * @exception
	 * @since  1.0.0
	 */
	@RequestMapping("resetPwd")
	@ResponseBody
	public HttpEntity resetPwd(@RequestBody JSONObject jsonObject){
 		try{
			Long userId=jsonObject.getLong("userId");
			BeanUtils.notEmpty(userId, "ID不能为空");
			User user = userService.qryUserById(userId);
			User currentUser = userUtils.getUser();
			if (user!=null) {
				user.setUserPassword(EncryptUtil.encryptSha256("123456"));
				if (currentUser!=null) {
					user.setModifyUserId(currentUser.getUserId());
					user.setModifyUserName(currentUser.getUserText());
					user.setModifyTime(new Date());
				}
				userService.changePwd(user);
			}
			return new HttpEntity(HttpStatus.OK,true,"请求成功",null);
	 	}catch (IllegalArgumentException e) {
			return new HttpEntity(HttpStatus.OK,false,e.getMessage(),null);
		}catch (Exception e){
    		return new HttpEntity(HttpStatus.OK,false,"请求失败",e);
		}
	}
	
	/**
	 * 修改密码
	 * @param  jsonObject
	 * @return
	 * @throws Exception
	 * HttpEntity
	 * @exception
	 * @since  1.0.0
	 */
	@RequestMapping("changePwd")
	@ResponseBody
	public HttpEntity changePwd(@RequestBody JSONObject jsonObject){
		String resultMsg=null;
		try{
			Long userId = jsonObject.getLong("userId");
			String oldPwd = jsonObject.getString("old");
			String newPwd = jsonObject.getString("new");
			BeanUtils.notEmpty(userId, "ID不能为空");
			BeanUtils.notEmpty(oldPwd, "旧密码不能为空");
			BeanUtils.notEmpty(newPwd, "新密码不能为空");
			User user = userService.qryUserById(userId);
			if (user!=null) {
				String oldPwdEn = EncryptUtil.encryptSha256(oldPwd);
				if (user.getSource()!=null && user.getSource()==1) {
					resultMsg = "同步帐号不允许修改密码";
					return new HttpEntity(HttpStatus.OK,false,resultMsg,null);
				}
				if (!oldPwdEn.equals(user.getUserPassword())) {
					resultMsg = "旧密码不正确";
					return new HttpEntity(HttpStatus.OK,false,resultMsg,null);
				}
				user.setUserPassword(EncryptUtil.encryptSha256(newPwd));
				userService.changePwd(user);
			}
			return new HttpEntity(HttpStatus.OK,true,"请求成功",null);
		}catch (IllegalArgumentException e) {
			return new HttpEntity(HttpStatus.OK,false,e.getMessage(),null);
		}catch (Exception e){
			return new HttpEntity(HttpStatus.OK,false,"请求失败",e);
		}
	}
	
	/**
	 * 获取可绑定的帐号列表
	 * @param  jsonObject
	 * @return
	 * @throws Exception
	 * HttpEntity
	 * @exception
	 * @since  1.0.0
	 */
	@RequestMapping("getAvaliableAccounts")
	@ResponseBody
	public HttpEntity getAvaliableAccounts(@RequestBody JSONObject jsonObject){
		try{
			Integer userType = jsonObject.getInteger("userType");
			Integer page = jsonObject.getInteger("page");
			Integer limit = jsonObject.getInteger("rows");
			BeanUtils.notEmpty(userType, "账号类型不能为空");
			Map<String, Object> params = new HashMap<String,Object>();
			params.put("userType", userType);
			String userName = jsonObject.getString("userName");
			if (StringUtils.isNotEmpty(userName)) {
				params.put("userName", userName);
			}
			String userText = jsonObject.getString("userText");
			if (StringUtils.isNotEmpty(userText)) {
				params.put("userText", userText);
			}
			List<User> temp = userService.getAvaliableAccounts(params);
			//构造分页器
			DefaultPage defaultPage = new DefaultPage();
			PageResult pageResult = new PageResult(defaultPage.getPage(), defaultPage.getLimit(), 0);
			PageJson pageJson = null;
			if (BeanUtils.isNotEmpty(temp)) {
				if (page!=null && page>0 && limit!=null && limit>0) {
					params.put("start", (page.intValue()-1)*limit);
					params.put("limit", limit);
					pageResult = new PageResult(page, limit, temp.size());
				}
				 List<User> dataList = userService.getAvaliableAccounts(params);
				 
				 if (BeanUtils.isNotEmpty(dataList)) {
					for (User user : dataList) {
						user.setUserPassword(null);
					}
					PageList<User> pageList = new PageList<>(dataList);
					pageList.setPageResult(pageResult);
					pageJson = new PageJson(pageList);
					return new HttpEntity(HttpStatus.OK,true,"请求成功",pageJson);
				 }
			}
			return new HttpEntity(HttpStatus.OK,true,"请求成功",new PageJson(new PageList<>(pageResult)));
		}catch (IllegalArgumentException e) {
			return new HttpEntity(HttpStatus.OK,false,e.getMessage(),null);
		}catch (Exception e){
			return new HttpEntity(HttpStatus.OK,false,"请求失败",e);
		}
	}
	
	/**
	 * 获取已绑定的帐号列表
	 * @param jsonObject
	 * @return
	 */
	@RequestMapping("getBindingAccounts")
	@ResponseBody
	public HttpEntity getBindingAccounts(@RequestBody JSONObject jsonObject){
		try{
			Long bindId = jsonObject.getLong("bindId");
			Integer type = jsonObject.getInteger("type");
			Integer page = jsonObject.getInteger("page");
			Integer limit = jsonObject.getInteger("rows");
			BeanUtils.notEmpty(bindId, "ID不可为空");
			BeanUtils.notEmpty(type, "类型不可为空");
			Map<String, Object> params = new HashMap<String,Object>();
			params.put("userType", type);
			params.put("bindId", bindId);
			
			List<User> temp = userService.getBindingAccounts(params);
			//构造分页器
			DefaultPage defaultPage = new DefaultPage();
			PageResult pageResult = new PageResult(defaultPage.getPage(), defaultPage.getLimit(), 0);
			PageJson pageJson = null;
			if (BeanUtils.isNotEmpty(temp)) {
				if (page!=null && page>0 && limit!=null && limit>0) {
					params.put("start", (page.intValue()-1)*limit);
					params.put("limit", limit);
					pageResult = new PageResult(page, limit, temp.size());
				}
				 List<User> dataList = userService.getBindingAccounts(params);
				 
				 if (BeanUtils.isNotEmpty(dataList)) {
					for (User user : dataList) {
						user.setUserPassword(null);
					}
					PageList<User> pageList = new PageList<>(dataList);
					pageList.setPageResult(pageResult);
					pageJson = new PageJson(pageList);
					return new HttpEntity(HttpStatus.OK,true,"请求成功",pageJson);
				 }
			}
			return new HttpEntity(HttpStatus.OK,true,"请求成功",new PageJson(new PageList<>(pageResult)));
		}catch (IllegalArgumentException e) {
			return new HttpEntity(HttpStatus.OK,false,e.getMessage(),null);
		}catch (Exception e){
			return new HttpEntity(HttpStatus.OK,false,"请求失败",e);
		}
	}
}
