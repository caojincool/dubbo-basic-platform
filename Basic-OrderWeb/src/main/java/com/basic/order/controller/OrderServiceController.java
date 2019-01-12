/**
 *
 * companyName
 * copyright 2015-2020
 * @date 2017年9月1日 上午11:53:40
 * @author lihiajun 
 * @Description: 服务
 * 
 */
package com.basic.order.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.basic.framework.common.model.DealResult;
import com.basic.framework.common.utils.datatype.DateUtils;
import com.basic.framework.common.utils.datatype.JsonUtils;
import com.basic.framework.web.BaseController;
import com.basic.order.bean.OrderServiceIbean;
import com.basic.order.define.BasicStateEnum;
import com.basic.order.model.OrderService;
import com.basic.order.service.OrderServiceService;

/**
 *
 * @date 2017年9月1日 上午11:53:40
 * @author lihaijun
 * @Description: 服务设计
 * 
 */
@Controller
@RequestMapping(value = OrderServiceController.REQUEST_PATH)
public class OrderServiceController extends BaseController{

	protected static final String REQUEST_PATH = "/order";
	
	private static Logger logger = LoggerFactory.getLogger(OrderServiceController.class);
	
	private static final JsonUtils JSON_UTILS = JsonUtils.getInstance();
	@Autowired
	private OrderServiceService orderServiceService;
	
	

	/**
	 * 
	 * @date 2017年9月1日 上午11:56:23
	 * @author lihaijun
	 * @Description: 初始化服务
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 *
	 */
	@RequestMapping(value = "/menuPage/orderService")
	public ModelAndView intoPage(HttpServletRequest request,HttpServletResponse response, Model model) {
		
		if(logger.isDebugEnabled()){
			logger.debug("请求参数  intoPage getParameterMap:{}", JSON_UTILS.objectToJson(request.getParameterMap()));
		}
		
		this.doBeforeMenuPageAction(request, response, model, null);
		
		//业务逻辑开始
		ModelAndView pageView = new ModelAndView("/order/orderService/orderService");
		
		//业务逻辑结束
		this.doAfterMenuPageAction(request, response, model, null);
		
		return pageView;
	
	}
	
	/**
	 * 
	 * @date 2017年9月4日 上午11:48:48
	 * @author lihaijun
	 * @Description: 根据参数查询服务列表
	 * @param orderServicebean
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 *
	 */
	
	@RequestMapping(value = "/func/orderService/pagin", produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public String qryPage(OrderServiceIbean orderServicebean,HttpServletRequest request,HttpServletResponse response, Model model) {
				
		if(logger.isDebugEnabled()){
			logger.debug("请求参数  qryPage orderServicebean:{},getParameterMap:{}", JSON_UTILS.objectToJson(orderServicebean), JSON_UTILS.objectToJson(request.getParameterMap()));
		}
		
		//业务逻辑开始
		this.doBeforeFuncAction(request, response, model, null);
		String json = null;
		
		List<OrderService> list = orderServiceService.qryOrderServiceList(orderServicebean);
		int records =  (int) orderServiceService.qryOrderServiceListCount(orderServicebean);//总记录数
		Integer page = orderServicebean.getPage();
		int total = orderServicebean.getTotalPage(records);
		json = this.toDataGridJson(page, total, records, list);
		//业务逻辑结束
		this.doAfterFuncAction(request, response, model, null);
		
		if(logger.isDebugEnabled()){
			logger.debug("输出参数json:{}", json);
		}
		return json;
	}
	
	/**
	 * 
	 * @date 2017年9月4日 上午11:48:19
	 * @author lihaijun
	 * @Description: 服务新增页面详情
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 *
	 */
	@RequestMapping(value = "/funcPage/orderService/orderServiceDetail")
	public ModelAndView orderServiceDetail(HttpServletRequest request,HttpServletResponse response, Model model) {
				
		if(logger.isDebugEnabled()){
			logger.debug("请求参数  OrderPage getParameterMap:{}", JSON_UTILS.objectToJson(request.getParameterMap()));
		}
		
		this.doBeforeFuncAction(request, response, model, null);
		
		//业务逻辑开始
		ModelAndView pageView = new ModelAndView("/order/orderService/orderServiceDetail");
		
		//业务逻辑结束
		this.doAfterFuncAction(request, response, model, null);
		
		return pageView;
	}
	
	/**
	 * 
	 * @date 2017年9月5日 上午10:49:53
	 * @author lihaijun
	 * @Description: 添加或更新服务
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 *
	 */
	@RequestMapping(value = "/func/orderService/createOrModify")
	@ResponseBody
	public Object createOrModify(HttpServletRequest request,HttpServletResponse response, Model model) {
		if(logger.isDebugEnabled()){
			logger.debug("请求参数  createOrModify getParameterMap:{}", JSON_UTILS.objectToJson(request.getParameterMap()));
		}
		//业务逻辑开始
		this.doBeforeFuncAction(request, response, model, null);
		String json = null;
		DealResult dealResult = new DealResult();
		
		try {
		// 获取并校验参数
		OrderService bean = JSON_UTILS.jsonToObject(this.getParameterFromRequest(request, "params"),
				OrderService.class);		

		orderServiceService.createOrModify(bean);
			
		dealResult.setReturnCode(DealResult.SUCCESS);
		dealResult.setReturnMsg(null);
		dealResult.setReturnData(null);
		} catch (Exception e) {
			logger.error("服务编辑操作失败：{}", e);
			dealResult.setReturnCode(DealResult.FAILURE);
			dealResult.setReturnMsg(null);
			dealResult.setReturnData(null);
		}
		
		json = JSON_UTILS.objectToJson(dealResult);
		
	//业务逻辑结束
		this.doAfterFuncAction(request, response, model, null);
		return json;
	}
	/**
	 * 
	 * @date 2017年9月8日 上午9:22:08
	 * @author lihaijun
	 * @Description: 弹窗，选择服务ID
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 *
	 */
	@RequestMapping(value = "/funcPage/orderService/selOrderService")
	public ModelAndView selOrderService(HttpServletRequest request,HttpServletResponse response, Model model) {
		long t0 = System.currentTimeMillis();

		if(logger.isDebugEnabled()){
			logger.debug("请求参数  demoModalWin getParameterMap:{}", JSON_UTILS.objectToJson(request.getParameterMap()));
		}
		
		this.doBeforeFuncPageAction(request, response, model, null);
		
		//业务逻辑开始
		ModelAndView pageView = new ModelAndView("/order/orderService/selOrderService");
		
		//业务逻辑结束
		this.doAfterFuncPageAction(request, response, model, null);
		
		long t1 = System.currentTimeMillis();
		if(logger.isDebugEnabled()){
			logger.debug("请求参数  demoModalWin getParameterMap:{},耗时:{}ms", JSON_UTILS.objectToJson(request.getParameterMap()),(t1-t0));
		}
		
		return pageView;
	}
	
	/**
	 * 
	 * @date 2017年9月6日 下午4:47:29
	 * @author lihaijun
	 * @Description: 批量删除
	 * @param ibean
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 *
	 */
	 
	@RequestMapping(value = "/func/orderService/del")
	@ResponseBody
	public Object del(OrderService ibean,HttpServletRequest request,HttpServletResponse response, Model model)  {
		
		if(logger.isDebugEnabled()){
			logger.debug("请求参数  del getParameterMap:{}", JSON_UTILS.objectToJson(request.getParameterMap()));
		}
		//业务逻辑开始
		this.doBeforeFuncAction(request, response, model, null);
		
		String json = null;
		DealResult dealResult = new DealResult();
		try {
			if(ibean != null && ibean.getServiceIds() != null && ibean.getServiceIds().length > 0 ){
				ibean.setState(BasicStateEnum.INVALID.getCode());
				Date now = DateUtils.now();
				ibean.setModifyTime(now);
				orderServiceService.modifyBatchStateByServiceIds(ibean);
				dealResult.setReturnCode(DealResult.SUCCESS);
				dealResult.setReturnMsg(null);
				dealResult.setReturnData(null);
			}
		} catch (Exception e) {
			logger.error("删除失败:{}", e);
			dealResult.setReturnCode(DealResult.FAILURE);
			dealResult.setReturnMsg(null);
			dealResult.setReturnData(null);
		}
		
		json = JSON_UTILS.objectToJson(dealResult);
		
		//业务逻辑结束
		this.doAfterFuncAction(request, response, model, null);
		return json;
	}
	
}
