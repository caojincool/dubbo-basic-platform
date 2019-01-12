package com.basic.order.controller;

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

import com.basic.framework.common.utils.datatype.JsonUtils;
import com.basic.framework.web.BaseController;
import com.basic.order.bean.WorkOrderIbean;
import com.basic.order.model.WorkOrder;
import com.basic.order.service.WorkOrderService;

/**
 * 
 *
 * @date 2017年9月12日 下午4:02:39
 * 
 * @Description: 任务
 *
 */
@Controller
@RequestMapping(value = WorkOrderController.REQUEST_PATH)
public class WorkOrderController extends BaseController{

	protected static final String REQUEST_PATH = "/order";
	
	private static Logger logger = LoggerFactory.getLogger(WorkOrderController.class);
	
	private static final JsonUtils JSON_UTILS = JsonUtils.getInstance();
	
	@Autowired
	private WorkOrderService workOrderService;

	/**
	 * 
	 * @date 2017年8月2日 下午2:47:46
	 * 
	 * @Description: 待办任务主页面
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 *
	 */
	@RequestMapping(value = "/menuPage/partyWorkOrder")
	public ModelAndView partyWorkOrder(HttpServletRequest request,HttpServletResponse response, Model model) {
		
		if(logger.isDebugEnabled()){
			logger.debug("请求参数  partyWorkOrder getParameterMap:{}", JSON_UTILS.objectToJson(request.getParameterMap()));
		}
		
		this.doBeforeMenuPageAction(request, response, model, null);
		
		//业务逻辑开始
		ModelAndView pageView = new ModelAndView("/order/workOrder/partyWorkOrder");
		
		//业务逻辑结束
		this.doAfterMenuPageAction(request, response, model, null);
		
		return pageView;
	}
	
	/**
	 * 
	 * @date 2017年8月2日 下午2:48:15
	 * 
	 * @Description: 待办任务主页面列表查询
	 * @param ibean
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 *
	 */
	@RequestMapping(value = "/func/workOrder/qryPartyWorkOrder")
	@ResponseBody
	public String pagin(WorkOrderIbean ibean,HttpServletRequest request,HttpServletResponse response, Model model) {

		if(logger.isDebugEnabled()){
			logger.debug("请求参数  pagin getParameterMap:{}", JSON_UTILS.objectToJson(request.getParameterMap()));
		}
		
		//业务逻辑开始
		this.doBeforeFuncAction(request, response, model, null);
		String json = null;
		
		List<WorkOrder> list = workOrderService.qryPartyWorkOrder(ibean);
		int records = (int) workOrderService.qryPartyWorkOrderCount(ibean);//总记录数
		//Integer offset = ibean.getOffset();
//		Integer limit = ibean.getLimit();//每页记录数
		Integer page = ibean.getPage();
		
//		int total = records%limit==0? records/limit : records/limit +1;//总页数
		
		int total = ibean.getTotalPage(records);
		
		json = this.toDataGridJson(page, total, records, list);

		//业务逻辑结束
		this.doAfterFuncAction(request, response, model, null);
		
		if(logger.isDebugEnabled()){
			logger.debug("输出参数json:{}", json);
		}
		return json;
	}
	
}
