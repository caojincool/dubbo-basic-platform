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
import com.basic.oaas.model.Job;
import com.basic.oaas.model.User;
import com.basic.oaas.service.JobService;
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
 * 描述：岗位控制器类
 * 构建组：basic.erp
 * 作者:lengzj
 * 邮箱:mail@com
 * 日期:2018-06-19 09:55:01
 * 版权：companyName
 * </pre>
 */
@Controller
@RequestMapping("/basic.oaas/job/")
public class JobController extends BaseController{

	private Logger logger = LoggerFactory.getLogger(JobController.class);

	@Autowired
	JobService jobService;
	@Autowired
	UserUtils userUtils;

	/**
	 * 岗位列表(分页条件查询)数据
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
        	String orgId = jsonObject.getString("orgId");
        	if (BeanUtils.isNotEmpty(orgId)) {
        		queryFilter.addFilter("JOB.ORG_ID", orgId, QueryOP.EQUAL);
        	}
        	String jobName = jsonObject.getString("jobName");
        	if (BeanUtils.isNotEmpty(jobName)) {
        		queryFilter.addFilter("JOB.JOB_NAME", jobName, QueryOP.LIKE);
			}
        	String orgName = jsonObject.getString("orgName");
        	if (BeanUtils.isNotEmpty(orgName)) {
        		queryFilter.addFilter("ORG.ORG_NAME", orgName, QueryOP.LIKE);
        	}
			PageJson pageJson  =  jobService.queryPage(queryFilter);
			return new HttpEntity(HttpStatus.OK,true,"请求成功",pageJson);
	 	}catch (Exception e){
    		return new HttpEntity(HttpStatus.OK,false,"请求失败",e);
		}

	}
	
	/**
	 * 岗位列表数据(不分页)
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
	        String orgId = jsonObject.getString("orgId");
	        Assert.notNull(orgId, "部门ID不能为空");
	        queryFilter.addFilter("ORG_ID", orgId, QueryOP.EQUAL);
			List<Job> list = jobService.query(queryFilter);
			return new HttpEntity(HttpStatus.OK,true,"请求成功",list);
		}catch (IllegalArgumentException e) {
			return new HttpEntity(HttpStatus.OK,false,e.getMessage(),null);
		}catch (Exception e){
			return new HttpEntity(HttpStatus.OK,false,"请求失败",e);
		}
		
	}

	/**
	 * 岗位明细页面
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
		Job job=null;
 		try{
			Long jobId=jsonObject.getLong("id");
			Assert.notNull(jobId, "ID不可为空");
			job=jobService.qryByPrimaryKey(jobId);
			return new HttpEntity(HttpStatus.OK,true,"请求成功",job);
	 	}catch (IllegalArgumentException e) {
			return new HttpEntity(HttpStatus.OK,false,e.getMessage(),null);
		}catch (Exception e){
    		return new HttpEntity(HttpStatus.OK,false,"请求失败",e);
		}
	}

	/**
	 * 保存岗位信息
	 * @param  jsonObject
	 * @throws Exception
	 * void
	 * @exception
	 */
	@RequestMapping("save")
	@ResponseBody
	public HttpEntity save(@RequestBody JSONObject jsonObject){
		Job job = JSONObject.toJavaObject(jsonObject,Job.class);
		String resultMsg=null;
		try {
			Assert.notNull(job, "岗位对象不能为空");
			Assert.notNull(job.getJobName(), "岗位名称不能为空");
			Assert.notNull(job.getOrgId(), "部门ID不能为空");
			User currentUser = userUtils.getUser();
			if (currentUser!=null) {
				if (BeanUtils.isEmpty(job.getJobId())) {
					job.setCreateUserId(currentUser.getUserId());
				}else {
					job.setModifyUserId(currentUser.getUserId());
				}
			}
			if (BeanUtils.isEmpty(job.getJobId())) {
				job.setSource(2);
			}
			jobService.createOrModify(job);
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
	 * 批量删除岗位
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
				jobService.removeBatchByJobIds(aryIds);
			}
			resultMsg="删除成功";
			return new HttpEntity(HttpStatus.OK,true,resultMsg,null);
		} catch (Exception e) {
    		resultMsg="删除失败";
		 	return new HttpEntity(HttpStatus.OK,false,resultMsg,e);
		}
	}
}
