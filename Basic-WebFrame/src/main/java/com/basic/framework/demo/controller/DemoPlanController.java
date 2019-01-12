package com.basic.framework.demo.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.basic.framework.common.utils.datatype.JsonUtils;
import com.basic.framework.demo.model.DemoGridPageQryCondBean;
import com.basic.framework.demo.model.PlanBaseBean;
import com.basic.framework.web.BaseController;

@Controller
@RequestMapping(value = DemoPlanController.REQUEST_PATH)
public class DemoPlanController extends BaseController {

	protected static final String REQUEST_PATH = "/demo";

	private static Logger logger = LoggerFactory.getLogger(DemoPlanController.class);
	private static final JsonUtils JSON_UTILS = JsonUtils.getInstance();
	
	/**
	 * grid演示
	 * @param ibean
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/menuPage/demoPlan")
	public ModelAndView demoGridPage(HttpServletRequest request,HttpServletResponse response, Model model) {
				
		if(logger.isDebugEnabled()){
			logger.debug("请求参数  demoPage getParameterMap:{}", JSON_UTILS.objectToJson(request.getParameterMap()));
		}
		
		this.doBeforeFuncAction(request, response, model, null);
		
		//业务逻辑开始
		ModelAndView pageView = new ModelAndView("/plan/demoPlan");
		
		//业务逻辑结束
		this.doAfterMenuPageAction(request, response, model, null);
		
		return pageView;
	}

	/**
	 * grid演示
	 * @param ibean
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/func/demoPlan/qryPage", produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public String qryPage(DemoGridPageQryCondBean demoGridPageQryCondBean,HttpServletRequest request,HttpServletResponse response, Model model) {
				
		if(logger.isDebugEnabled()){
			logger.debug("请求参数  qryPage demoGridPageQryCondBean:{},getParameterMap:{}", JSON_UTILS.objectToJson(demoGridPageQryCondBean), JSON_UTILS.objectToJson(request.getParameterMap()));
		}
		
		this.doBeforeFuncAction(request, response, model, null);
		String json = null;
		
		int records = 208;//总记录数
//		int offset = demoGridPageQryCondBean.getOffset();
		int limit = demoGridPageQryCondBean.getLimit();//每页记录数
		int page = demoGridPageQryCondBean.getPage();
		
		int total = records%limit==0? records/limit : records/limit +1;//总页数
		
		List<PlanBaseBean> list = new ArrayList<PlanBaseBean>();
		
		//业务逻辑开始
		
		for(int i=0;i<limit;i++){
			int cur = (i)*10;
			PlanBaseBean bean = new PlanBaseBean();
			bean.setPlanId(new Long(cur));
			bean.setPlanName("计划"+i);
			bean.setPlanBeginTime(new Date());
			bean.setPlanEndTime(new Date());
			bean.setState("未执行");
			bean.setStateTime(new Date());
			bean.setCreateStaff(1L);
			bean.setCreateTime(new Date());
			bean.setExecStaff(1L);
			bean.setModifyStaff(1L);
			bean.setTaskId("1");
			list.add(bean);
		}

		json = this.toDataGridJson(page, total, records, list);

		//业务逻辑结束

		this.doAfterFuncAction(request, response, model, null);
		return json;
	}
	
	@RequestMapping(value = "/func/demoPlanAdd")
	public ModelAndView demoPlanAdd(HttpServletRequest request,HttpServletResponse response, Model model) {
				
		if(logger.isDebugEnabled()){
			logger.debug("请求参数  demoPage getParameterMap:{}", JSON_UTILS.objectToJson(request.getParameterMap()));
		}
		
		this.doBeforeFuncAction(request, response, model, null);
		
		//业务逻辑开始
		ModelAndView pageView = new ModelAndView("/plan/demoPlanAdd");
		
		//业务逻辑结束
		this.doAfterFuncAction(request, response, model, null);
		
		return pageView;
	}
	
	@RequestMapping(value = "/func/taskTemplateAdd")
	public ModelAndView taskTemplateAdd(HttpServletRequest request,HttpServletResponse response, Model model) {
		
		if(logger.isDebugEnabled()){
			logger.debug("请求参数  demoPage getParameterMap:{}", JSON_UTILS.objectToJson(request.getParameterMap()));
		}
		
		this.doBeforeFuncAction(request, response, model, null);
		
		//业务逻辑开始
		ModelAndView pageView = new ModelAndView("/plan/taskTemplateAdd");
		
		//业务逻辑结束
		this.doAfterFuncAction(request, response, model, null);
		
		return pageView;
	}
}
