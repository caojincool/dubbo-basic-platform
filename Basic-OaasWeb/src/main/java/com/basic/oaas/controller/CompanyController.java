package com.basic.oaas.controller;

import com.alibaba.dubbo.common.utils.Assert;
import com.alibaba.dubbo.common.utils.StringUtils;
import com.alibaba.fastjson.JSONObject;
import com.basic.framework.common.api.db.domain.PageJson;
import com.basic.framework.common.api.query.QueryOP;
import com.basic.framework.common.model.DefaultQueryFilter;
import com.basic.framework.common.utils.result.HttpEntity;
import com.basic.framework.common.utils.result.HttpStatus;
import com.basic.framework.security.jwt.utils.UserUtils;
import com.basic.framework.web.BaseController;
import com.basic.oaas.model.Company;
import com.basic.oaas.model.Org;
import com.basic.oaas.model.User;
import com.basic.oaas.service.CompanyService;
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
 * 描述：公司信息 控制器类
 * 构建组：basic.erp
 * 作者:lengzj
 * 邮箱:mail@com
 * 日期:2018-06-19 09:55:02
 * 版权：companyName
 * </pre>
 */
@Controller
@RequestMapping("/basic.oaas/company/")
public class CompanyController extends BaseController{

	private Logger logger = LoggerFactory.getLogger(CompanyController.class);

	@Autowired
	CompanyService companyService;
	@Autowired
	UserUtils userUtils;
	
	
	/**
     * 获取公司树
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
				queryFilter.addFilter("CPY.PARENT_ID", parentId, QueryOP.EQUAL);
			}
			List<Company> list  =  companyService.query(queryFilter);
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
	 * 公司列表(分页条件查询)数据
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
			PageJson pageJson  =  companyService.queryPage(queryFilter);
			return new HttpEntity(HttpStatus.OK,true,"请求成功",pageJson);
	 	}catch (Exception e){
    		return new HttpEntity(HttpStatus.OK,false,"请求失败",e);
		}

	}
	
	/**
	 * 公司列表数据(不分页)
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
			List<Company> list = companyService.query(queryFilter);
			return new HttpEntity(HttpStatus.OK,true,"请求成功",list);
		}catch (Exception e){
			return new HttpEntity(HttpStatus.OK,false,"请求失败",e);
		}
		
	}

	/**
	 * 公司明细页面
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
		Company company=null;
 		try{
			Long companyId=jsonObject.getLong("id");
			company=companyService.qryByPrimaryKey(companyId);
			return new HttpEntity(HttpStatus.OK,true,"请求成功",company);
	 	}catch (Exception e){
    		return new HttpEntity(HttpStatus.OK,false,"请求失败",e);
		}
	}

	/**
	 * 保存公司信息
	 * @param  jsonObject
	 * @throws Exception
	 * void
	 * @exception
	 */
	@RequestMapping("save")
	@ResponseBody
	public HttpEntity save(@RequestBody JSONObject jsonObject){
		Company company = JSONObject.toJavaObject(jsonObject,Company.class);
		String resultMsg=null;
		try {
			Assert.notNull(company, "公司对象不能为空");
			Assert.notNull(company.getAbsName(), "公司简称不能为空");
			Assert.notNull(company.getFullName(), "公司全称不能为空");
			DefaultQueryFilter queryFilter = new DefaultQueryFilter();
			queryFilter.addFilter("FULL_NAME", company.getFullName(), QueryOP.EQUAL);
			if (BeanUtils.isNotEmpty(company.getCompanyId())) {
				queryFilter.addFilter("COMPANY_ID", company.getCompanyId(), QueryOP.NOT_EQUAL);
			}
			if (BeanUtils.isNotEmpty(companyService.query(queryFilter))) {
				resultMsg = "公司名称不可重复";
				return new HttpEntity(HttpStatus.OK,false,resultMsg,null);
			}else {
				if (StringUtil.isEmpty(company.getParentId())) {
					company.setParentId(-1L);
				}
				User currentUser = userUtils.getUser();
				if (currentUser!=null) {
					if (BeanUtils.isEmpty(company.getCompanyId())) {
						company.setCreateUserId(currentUser.getUserId());
					}else {
						company.setModifyUserId(currentUser.getUserId());
					}
				}
				company = companyService.createOrModify(company);
			}
			resultMsg="保存成功";
			return new HttpEntity(HttpStatus.OK,true,resultMsg,company);
		}catch (IllegalArgumentException e) {
			return new HttpEntity(HttpStatus.OK,false,e.getMessage(),null);
		} catch (Exception e) {
			resultMsg="保存失败";
			return new HttpEntity(HttpStatus.OK,false,resultMsg,e);
		}
	}

	/**
	 * 批量删除公司记录
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
					companyService.removeByPrimaryKey(aryIds[i]);
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
