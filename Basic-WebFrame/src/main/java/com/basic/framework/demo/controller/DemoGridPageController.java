package com.basic.framework.demo.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.basic.framework.web.BaseController;
import com.basic.framework.common.utils.datatype.JsonUtils;
import com.basic.framework.demo.model.DemoGridBean;
import com.basic.framework.demo.model.DemoGridPageQryCondBean;

@Controller
@RequestMapping(value = DemoGridPageController.REQUEST_PATH)
public class DemoGridPageController extends BaseController {

	protected static final String REQUEST_PATH = "/demo";

	private static Logger logger = LoggerFactory.getLogger(DemoGridPageController.class);
	private static final JsonUtils JSON_UTILS = JsonUtils.getInstance();
	
	/**
	 * grid演示
	 * @param ibean
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/menuPage/demoGridPage")
	public ModelAndView demoGridPage(HttpServletRequest request,HttpServletResponse response, Model model) {
				
		if(logger.isDebugEnabled()){
			logger.debug("请求参数  demoPage getParameterMap:{}", JSON_UTILS.objectToJson(request.getParameterMap()));
		}
		
		this.doBeforeMenuPageAction(request, response, model, null);
		
		//业务逻辑开始
		ModelAndView pageView = new ModelAndView("/demo/demoGridPage");
		
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
	@RequestMapping(value = "/func/demoGridPage/qryPage")
	@ResponseBody
	public String qryPage(DemoGridPageQryCondBean demoGridPageQryCondBean,HttpServletRequest request,HttpServletResponse response, Model model) {
				
		if(logger.isDebugEnabled()){
			logger.debug("请求参数  qryPage demoGridPageQryCondBean:{},getParameterMap:{}", JSON_UTILS.objectToJson(demoGridPageQryCondBean), JSON_UTILS.objectToJson(request.getParameterMap()));
		}
		
		this.doBeforeFuncAction(request, response, model, null);
		String json = null;
		
		int records = 208;//总记录数
		int start = demoGridPageQryCondBean.getStart();
		int limit = demoGridPageQryCondBean.getLimit();//每页记录数
		int page = demoGridPageQryCondBean.getPage();
		
		int total = records%limit==0? records/limit : records/limit +1;//总页数
		
		java.util.List<DemoGridBean> list = new java.util.ArrayList<DemoGridBean>();
		
		//业务逻辑开始
		
		for(int i=0;i<limit;i++){
			int cur = (i+start)*10;
			DemoGridBean demoGridBean = new DemoGridBean();
			demoGridBean.setId(new Long(cur));
			demoGridBean.setBirthDay(new java.util.Date());
			demoGridBean.setUserName("姓名"+(cur));
			demoGridBean.setComments("很长一段话很长一段话很长一段话很长一段话很长一");
			list.add(demoGridBean);
		}

		json = this.toDataGridJson(page, total, records, list);

		//业务逻辑结束

		this.doAfterFuncAction(request, response, model, null);
		return json;
	}
	
	@RequestMapping(value = "/win/demoSearchWin")
	public ModelAndView advancedSearchPage(HttpServletRequest request,HttpServletResponse response, Model model) {
				
		if(logger.isDebugEnabled()){
			logger.debug("请求参数  demoPage getParameterMap:{}", JSON_UTILS.objectToJson(request.getParameterMap()));
		}
		
		this.doBeforeMenuPageAction(request, response, model, null);
		
		//业务逻辑开始
		ModelAndView pageView = new ModelAndView("/demo/demoSearchWin");
		
		//业务逻辑结束
		this.doAfterMenuPageAction(request, response, model, null);
		
		return pageView;
	}
}
