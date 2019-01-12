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
import com.basic.framework.demo.model.DemoGridCheckQryCondBean;

/**
 * 带复选框的列表
 *
 * @date 2016年8月18日 下午3:13:35
 * @author lzj
 * @Description: 带复选框的列表
 *
 */
@Controller
@RequestMapping(value = DemoGridCheckController.REQUEST_PATH)
public class DemoGridCheckController extends BaseController {

	protected static final String REQUEST_PATH = "/demo";

	
	private static Logger logger = LoggerFactory.getLogger(DemoGridCheckController.class);
	
	private static final JsonUtils JSON_UTILS = JsonUtils.getInstance();
	
	
	/**
	 * 带复选框的列表
	 * @param ibean
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/menuPage/demoGridCheck")
	public ModelAndView demoGridCheck(HttpServletRequest request,HttpServletResponse response, Model model) {
				
		long t0 = System.currentTimeMillis();
		if(logger.isDebugEnabled()){
			logger.debug("请求参数  demoPage getParameterMap:{}", JSON_UTILS.objectToJson(request.getParameterMap()));
		}
		
		this.doBeforeMenuPageAction(request, response, model, null);
		
		//业务逻辑开始
		ModelAndView pageView = new ModelAndView("/demo/demoGridCheck");
		
		//业务逻辑结束
		this.doAfterMenuPageAction(request, response, model, null);
		long t1 = System.currentTimeMillis();
		if(logger.isDebugEnabled()){
			logger.debug("请求参数  demoPage getParameterMap:{},耗时:{}ms", JSON_UTILS.objectToJson(request.getParameterMap()),(t1-t0));
		}
		return pageView;
	}

	/**
	 * 加载列表中的数据
	 * @param ibean
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/func/demoGridCheck/qryAll")
	@ResponseBody
	public String qryAll(DemoGridCheckQryCondBean demoGridCheckQryCondBean,HttpServletRequest request,HttpServletResponse response, Model model) {
				
		long t0 = System.currentTimeMillis();
		if(logger.isDebugEnabled()){
			logger.debug("请求参数  qryAll demoGridCheckQryCondBean:{},getParameterMap:{}", JSON_UTILS.objectToJson(demoGridCheckQryCondBean), JSON_UTILS.objectToJson(request.getParameterMap()));
		}
		
		this.doBeforeFuncAction(request, response, model, null);
		String json = null;
		
		java.util.List<DemoGridBean> list = new java.util.ArrayList<DemoGridBean>();
		//业务逻辑开始
		for(int i=0;i<100;i++){
			DemoGridBean demoGridBean = new DemoGridBean();
			demoGridBean.setId(new Long(i));
			demoGridBean.setBirthDay(new java.util.Date());
			demoGridBean.setUserName("姓名"+i);
			demoGridBean.setComments("很长一段话很长一段话很长一段话很长一段话很长一");
			list.add(demoGridBean);
		}

		//业务逻辑结束
		Integer page = null;
		Integer total = null;
		Integer records = null;
		json = this.toDataGridJson(page, total, records, list);
		
		this.doAfterFuncAction(request, response, model, null);
		long t1 = System.currentTimeMillis();
		
		if(logger.isDebugEnabled()){
			logger.debug("请求参数  qryAll demoGridCheckQryCondBean:{},getParameterMap:{},耗时:{}ms", JSON_UTILS.objectToJson(demoGridCheckQryCondBean), JSON_UTILS.objectToJson(request.getParameterMap()),(t1-t0));
		}
		return json;
	}
}
