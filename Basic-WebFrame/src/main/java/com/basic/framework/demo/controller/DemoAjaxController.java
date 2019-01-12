package com.basic.framework.demo.controller;

import java.util.ArrayList;
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

import com.basic.framework.web.BaseController;
import com.basic.framework.common.model.DealResult;
import com.basic.framework.common.utils.datatype.JsonUtils;
import com.basic.framework.demo.model.DemoGridBean;

/**
 * 
 *
 * @date 2017年10月18日 上午10:40:44
 * 
 * @Description: ajax同步异步方法测试
 *
 */
@Controller
@RequestMapping(value = DemoAjaxController.REQUEST_PATH)
public class DemoAjaxController extends BaseController {

	protected static final String REQUEST_PATH = "/demo";

	private static Logger logger = LoggerFactory.getLogger(DemoAjaxController.class);
	
	private static final JsonUtils JSON_UTILS = JsonUtils.getInstance();

	/**
	 * 
	 * @date 2016年9月5日 下午4:58:58
	 * @author 杰
	 * @Description: ajax页面
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 *
	 */
	@RequestMapping(value = "/menuPage/demoAjax")
	public ModelAndView demoAjax(HttpServletRequest request, HttpServletResponse response, Model model) {

		if (logger.isDebugEnabled()) {
			logger.debug("请求参数  demoGridTree getParameterMap:{}",
					JSON_UTILS.objectToJson(request.getParameterMap()));
		}

		this.doBeforeMenuPageAction(request, response, model, null);

		// 业务逻辑开始
		ModelAndView pageView = new ModelAndView("/demo/demoAjax");

		// 业务逻辑结束
		this.doAfterMenuPageAction(request, response, model, null);

		return pageView;
	}
	
	/**
	 * grid演示
	 * 
	 * @param ibean
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "/func/demoAjax/qry")
	@ResponseBody
	public String qry(HttpServletRequest request,HttpServletResponse response, Model model) throws Exception {

		if (logger.isDebugEnabled()) {
			logger.debug("qry getParameterMap:{}",
					JSON_UTILS.objectToJson(request.getParameterMap()));
		}

		this.doBeforeFuncAction(request, response, model, null);
		String json = null;

		List<DemoGridBean> list = new ArrayList<DemoGridBean>();
		for(int i=0;i<3;i++){
			DemoGridBean demoGridBean = new DemoGridBean();
			demoGridBean.setId(new Long(i));
			demoGridBean.setAge(i+20);
			demoGridBean.setBirthDay(new java.util.Date());
			demoGridBean.setUserName("姓名"+i);
			demoGridBean.setState("10I");
			demoGridBean.setComments("很长一段话很长一段话很长一段话很长一段话很长一");
			demoGridBean.setImageId(239504L);
			list.add(demoGridBean);
		}
		
		DealResult dealResult = new DealResult();
		dealResult.setReturnCode(DealResult.SUCCESS);
		dealResult.setReturnMsg(null);
		dealResult.setReturnData(list);
		
		Thread.sleep(10000);
		
		json = JSON_UTILS.objectToJson(dealResult);
		// 业务逻辑结束

		this.doAfterFuncAction(request, response, model, null);
		return json;
	}
	

}
