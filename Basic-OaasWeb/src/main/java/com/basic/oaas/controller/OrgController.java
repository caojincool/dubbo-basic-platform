package com.basic.oaas.controller;

import com.alibaba.dubbo.common.utils.Assert;
import com.alibaba.dubbo.common.utils.StringUtils;
import com.alibaba.fastjson.JSONObject;
import com.basic.framework.common.api.db.domain.PageJson;
import com.basic.framework.common.api.query.QueryOP;
import com.basic.framework.common.model.DealResult;
import com.basic.framework.common.model.DefaultQueryFilter;
import com.basic.framework.common.utils.result.HttpEntity;
import com.basic.framework.common.utils.result.HttpStatus;
import com.basic.framework.security.jwt.utils.UserUtils;
import com.basic.framework.web.BaseController;
import com.basic.oaas.model.Org;
import com.basic.oaas.model.User;
import com.basic.oaas.service.OrgService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.basic.framework.common.utils.BeanUtils;
import com.basic.framework.common.utils.StringUtil;

import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 *
 * <pre>
 * 描述：状态 STATE
 控制器类
 * 构建组：basic.erp
 * 作者:lengzj
 * 邮箱:mail@com
 * 日期:2018-06-15 17:49:43
 * 版权：companyName
 * </pre>
 */
@Controller
@RequestMapping("/basic.oaas/org/")
public class OrgController extends BaseController{

	private Logger logger = LoggerFactory.getLogger(OrgController.class);

	@Autowired
	OrgService orgService;
	@Autowired
	UserUtils userUtils;
	
	
	/**
     * 获取部门树
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
				queryFilter.addFilter("ORG.PARENT_ORG_ID", parentId, QueryOP.EQUAL);
			}
			List<Org> list  =  orgService.query(queryFilter);
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
     * 获取明细
     * @param jsonObject
     * @return
     */
    @RequestMapping("get")
	@ResponseBody
	public HttpEntity get(@RequestBody JSONObject jsonObject){
		Org org=null;
 		try{
			Long orgId=jsonObject.getLong("id");
			Assert.notNull(orgId, "id不能为空");
			org=orgService.qryOrgById(orgId);
			return new HttpEntity(HttpStatus.OK,true,"请求成功",org);
	 	}catch (IllegalArgumentException e) {
			return new HttpEntity(HttpStatus.OK,false,e.getMessage(),null);
		}catch (Exception e){
    		return new HttpEntity(HttpStatus.OK,false,"请求失败",e);
		}
	}
    
    /**
	 * 保存
	 * @param  jsonObject
	 * @throws Exception
	 * void
	 * @exception
	 */
	@RequestMapping("save")
	@ResponseBody
	public HttpEntity save(@RequestBody JSONObject jsonObject){
		Org org = JSONObject.toJavaObject(jsonObject,Org.class);
		String resultMsg=null;
		try {
			Assert.notNull(org, "部门对象不能为空");
			Assert.notNull(org.getOrgName(), "部门名称不能为空");
			Assert.notNull(org.getOrgAbsName(), "部门编码不能为空");
			User currentUser = userUtils.getUser();
			if (currentUser!=null) {
				if (BeanUtils.isEmpty(org.getOrgId())) {
					org.setCreateUserId(currentUser.getUserId());
				}else {
					org.setModifyUserId(currentUser.getUserId());
				}
			}
			if (BeanUtils.isEmpty(org.getOrgId())) {
				org.setSource(2);
			}
			if (BeanUtils.isEmpty(org.getParentOrgId())) {
				org.setParentOrgId(-1L);
			}
			org = orgService.createOrModify(org);
			resultMsg = "操作成功";
			return new HttpEntity(HttpStatus.OK,true,resultMsg,org);
		}catch (IllegalArgumentException e) {
			return new HttpEntity(HttpStatus.OK,false,e.getMessage(),null);
		} catch (Exception e) {
			resultMsg="操作失败";
			return new HttpEntity(HttpStatus.OK,false,resultMsg,e);
		}
	}
	
	
	/**
	 * 批量删除
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
			Assert.notNull(aryIds, "ID不能为空");
			if (BeanUtils.isNotEmpty(aryIds)) {
				for (Long id : aryIds) {
					orgService.removeByPrimarykey(id);
				}
			}
			resultMsg="删除成功";
			return new HttpEntity(HttpStatus.OK,true,resultMsg,null);
		}catch (IllegalArgumentException e) {
			return new HttpEntity(HttpStatus.OK,false,e.getMessage(),null);
		} catch (Exception e) {
    		resultMsg="删除失败";
		 	return new HttpEntity(HttpStatus.OK,false,resultMsg,e);
		}
	}
	

}
