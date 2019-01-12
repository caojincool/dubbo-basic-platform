package com.basic.framework.demo.controller;

//import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.basic.framework.web.BaseController;
import com.basic.framework.common.utils.datatype.JsonUtils;

/**
 * 
 *
 * @date 2016年8月10日 下午7:07:52
 * @author 杰
 * @Description: 导入导出excel的使用
 *
 */
@Controller
@RequestMapping(value = DemoExcelController.REQUEST_PATH)
public class DemoExcelController extends BaseController {

	protected static final String REQUEST_PATH = "/demo";

	private static Logger logger = LoggerFactory.getLogger(DemoExcelController.class);
	
	private static final JsonUtils JSON_UTILS = JsonUtils.getInstance();

	/**
	 * 
	 * @date 2016年9月5日 下午4:58:58
	 * @author 杰
	 * @Description: 导入导出excel Demo主页面
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 *
	 */
	@RequestMapping(value = "/menuPage/demoExcel")
	public ModelAndView demoGridTree(HttpServletRequest request, HttpServletResponse response, Model model) {

		if (logger.isDebugEnabled()) {
			logger.debug("请求参数  demoGridTree getParameterMap:{}",
					JSON_UTILS.objectToJson(request.getParameterMap()));
		}

		this.doBeforeMenuPageAction(request, response, model, null);

		// 业务逻辑开始
		ModelAndView pageView = new ModelAndView("/demo/demoExcel");

		// 业务逻辑结束
		this.doAfterMenuPageAction(request, response, model, null);

		return pageView;
	}

	/**
	 * 
	 * @date 2016年5月24日 下午8:45:00
	 * @author Fan
	 * @Description: 导出码验证页面
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 *
	 */
	@RequestMapping(value = "/funcPage/exportCode")
	public ModelAndView detail(HttpServletRequest request,HttpServletResponse response, Model model) {
				
		if(logger.isDebugEnabled()){
			logger.debug("请求参数  detail getParameterMap:{}", JSON_UTILS.objectToJson(request.getParameterMap()));
		}
		
		this.doBeforeMenuPageAction(request, response, model, null);
		
		//业务逻辑开始
		ModelAndView pageView = new ModelAndView("/public/file/exportCode");
		
		//业务逻辑结束
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
/*	@RequestMapping(value = "/func/demoZTree/qryTree")
	@ResponseBody
	public String qryTree(DemoGridTreeQryCondBean demoGridTreeQryCondBean, HttpServletRequest request,
			HttpServletResponse response, Model model) throws Exception {

		if (logger.isDebugEnabled()) {
			logger.debug("请求参数  demoGridTree demoGridTreeQryCondBean:{},getParameterMap:{}",
					JSON_UTILS.objectToJson(demoGridTreeQryCondBean),
					JSON_UTILS.objectToJson(request.getParameterMap()));
		}

		this.doBeforeMenuPageAction(request, response, model, null);
		String json = null;
		Long id = demoGridTreeQryCondBean.getId();
		if(null == id){
			id = -1L;
		}
		List<OaasOrg> list = demoOrgService.qryOaasOrgTreeStep(id);

		json = JSON_UTILS.objectToJson(list);
		// 业务逻辑结束

		this.doAfterMenuPageAction(request, response, model, null);
		return json;
	}*/
	

}
