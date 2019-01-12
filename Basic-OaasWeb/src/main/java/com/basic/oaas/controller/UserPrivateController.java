package com.basic.oaas.controller;

import com.alibaba.dubbo.common.utils.Assert;
import com.alibaba.fastjson.JSONObject;
import com.basic.framework.common.api.db.domain.PageJson;
import com.basic.framework.common.model.DefaultQueryFilter;
import com.basic.framework.common.utils.result.HttpEntity;
import com.basic.framework.common.utils.result.HttpStatus;
import com.basic.framework.security.jwt.utils.UserUtils;
import com.basic.framework.web.BaseController;
import com.basic.oaas.bean.UserPrivateIbean;
import com.basic.oaas.bean.UserRoleIbean;
import com.basic.oaas.model.User;
import com.basic.oaas.model.UserPrivate;
import com.basic.oaas.model.UserRole;
import com.basic.oaas.service.UserPrivateService;
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
 * 描述：帐号权限关系表 控制器类
 * 构建组：basic.erp
 * 作者:lengzj
 * 邮箱:mail@com
 * 日期:2018-06-26 16:09:26
 * 版权：companyName
 * </pre>
 */
@Controller
@RequestMapping("/basic.oaas/userPrivate/")
public class UserPrivateController extends BaseController{

	private Logger logger = LoggerFactory.getLogger(UserPrivateController.class);

	@Autowired
	UserPrivateService userPrivateService;
	@Autowired
	UserUtils userUtils;

	


	/**
	 * 保存帐号权限关系表信息
	 * @param  jsonObject
	 * @throws Exception
	 * void
	 * @exception
	 */
	@RequestMapping("save")
	@ResponseBody
	public HttpEntity save(@RequestBody JSONObject jsonObject){
		UserPrivateIbean ibean = JSONObject.toJavaObject(jsonObject,UserPrivateIbean.class);
		String resultMsg=null;
		try {
			Assert.notNull(ibean, "对象不可为空");
			Assert.notNull(ibean.getUserId(), "帐号ID不可为空");
			if (BeanUtils.isNotEmpty(ibean.getUserPrivates())) {
				User currentUser = userUtils.getUser();
				for (UserPrivate ur : ibean.getUserPrivates()) {
					if (currentUser!=null) {
						ur.setCreateUserId(currentUser.getUserId());
					}
				}
			}
			userPrivateService.createBatchUserPrivate(ibean);
			//登录时再放进缓存，先去掉
			//userUtils.refreshPrivatesDetail(ibean.getUserId());
			resultMsg = "保存成功";
			return new HttpEntity(HttpStatus.OK,true,resultMsg,null);
		} catch (Exception e) {
			resultMsg="保存失败";
			return new HttpEntity(HttpStatus.OK,false,resultMsg,e);
		}
	}
	
	
	/**
	 * 保存帐号权限关系表信息
	 * @param  jsonObject
	 * @throws Exception
	 * void
	 * @exception
	 */
	
	@RequestMapping("remove")
	@ResponseBody
	public HttpEntity remove(@RequestBody JSONObject jsonObject){
		String resultMsg=null;
		try {
			Long userId = jsonObject.getLong("userId");
			Long[] aryIds = getLongAryByStr(jsonObject, "ids");
			userPrivateService.deleteBatchByIds(userId,aryIds);
			resultMsg = "删除成功";
			return new HttpEntity(HttpStatus.OK,true,resultMsg,null);
		} catch (Exception e) {
			resultMsg="删除失败";
			return new HttpEntity(HttpStatus.OK,false,resultMsg,e);
		}
	}

}
