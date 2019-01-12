package com.basic.order.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.basic.framework.common.model.DealResult;
import com.basic.framework.common.utils.datatype.JsonUtils;
import com.basic.framework.web.BaseController;
import com.basic.order.bean.ButtonIbean;
import com.basic.order.bean.CreateOrderIbean;
import com.basic.order.bean.OrderIbean;
import com.basic.order.bean.ProcessJumpIbean;
import com.basic.order.bean.ProcessStartIbean;
import com.basic.order.bean.WorkOrderDisp;
import com.basic.order.bean.WorkOrderFinish;
import com.basic.order.bean.WorkOrderGet;
import com.basic.order.bean.WorkOrderIbean;
import com.basic.order.model.Button;
import com.basic.order.model.Order;
import com.basic.order.model.OrderFollowUser;
import com.basic.order.model.OrderOper;
import com.basic.order.model.WorkOrder;
import com.basic.order.model.WorkOrderOper;
import com.basic.order.service.ButtonService;
import com.basic.order.service.OrderFollowUserService;
import com.basic.order.service.OrderOperService;
import com.basic.order.service.OrderService;
import com.basic.order.service.ProcessOrderManageService;
import com.basic.order.service.ProcessWorkOrderManageService;
import com.basic.order.service.WorkOrderOperService;
import com.basic.order.service.WorkOrderService;

/**
 * 
 *
 * @date 2017年8月2日 下午2:46:00
 * 
 * @Description: 单据监控
 *
 */
@Controller
@RequestMapping(value = OrderMonitorController.REQUEST_PATH)
public class OrderMonitorController extends BaseController{

	protected static final String REQUEST_PATH = "/order";
	
	private static Logger logger = LoggerFactory.getLogger(OrderMonitorController.class);
	
	private static final JsonUtils JSON_UTILS = JsonUtils.getInstance();
	
	@Autowired
	private OrderService orderService;
	@Autowired
	private WorkOrderService workOrderService;
	@Autowired
	private ProcessOrderManageService processOrderManageService;
	@Autowired
	private OrderFollowUserService orderFollowUserService;
	@Autowired
	private OrderOperService orderOperService;
	@Autowired
	private WorkOrderOperService workOrderOperService;
	@Autowired
	private ProcessWorkOrderManageService processWorkOrderManageService;
	@Autowired
	private ButtonService buttonService;

	/**
	 * 
	 * @date 2017年8月2日 下午2:47:46
	 * 
	 * @Description: 单据监控主页面
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 *
	 */
	@RequestMapping(value = "/menuPage/orderMonitor")
	public ModelAndView orderMonitor(HttpServletRequest request,HttpServletResponse response, Model model) {
		
		if(logger.isDebugEnabled()){
			logger.debug("请求参数  orderMonitor getParameterMap:{}", JSON_UTILS.objectToJson(request.getParameterMap()));
		}
		
		this.doBeforeMenuPageAction(request, response, model, null);
		
		//业务逻辑开始
		ModelAndView pageView = new ModelAndView("/order/orderMonitor/orderMonitor");
		
		//业务逻辑结束
		this.doAfterMenuPageAction(request, response, model, null);
		
		return pageView;
	}
	
	/**
	 * 
	 * @date 2017年8月2日 下午2:48:15
	 * 
	 * @Description: 单据监控主页面列表查询
	 * @param ibean
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 *
	 */
	@RequestMapping(value = "/func/orderMonitor/pagin")
	@ResponseBody
	public String pagin(OrderIbean ibean,HttpServletRequest request,HttpServletResponse response, Model model) {

		if(logger.isDebugEnabled()){
			logger.debug("请求参数  pagin getParameterMap:{}", JSON_UTILS.objectToJson(request.getParameterMap()));
		}
		
		//业务逻辑开始
		this.doBeforeFuncAction(request, response, model, null);
		String json = null;
		
		List<Order> list = orderService.qryOrderList(ibean);
		int records = (int) orderService.qryOrderListCount(ibean);//总记录数
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
	
	
	/**
	 * 
	 * @date 2017年8月2日 下午2:48:15
	 * 
	 * @Description: 流程任务单列表查询
	 * @param ibean
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 *
	 */
	@RequestMapping(value = "/func/orderMonitor/qryWorkOrdersByOrderId")
	@ResponseBody
	public String qryWorkOrdersByOrderId(WorkOrderIbean ibean,HttpServletRequest request,HttpServletResponse response, Model model) {
		
		if(logger.isDebugEnabled()){
			logger.debug("请求参数  qryWorkOrdersByOrderId getParameterMap:{}", JSON_UTILS.objectToJson(request.getParameterMap()));
		}
		
		//业务逻辑开始
		this.doBeforeFuncAction(request, response, model, null);
		String json = null;
		
		List<WorkOrder> list = workOrderService.qryWorkOrderList(ibean);
//		int records = (int) workOrderService.qryWorkOrderListCount(ibean);//总记录数
		//Integer offset = ibean.getOffset();
//		Integer limit = ibean.getLimit();//每页记录数
//		Integer page = ibean.getPage();
		
//		int total = records%limit==0? records/limit : records/limit +1;//总页数
		
//		int total = ibean.getTotalPage(records);
		
//		json = this.toDataGridJson(page, total, records, list);
		json = this.toDataGridJson(null, null, null, list);
		
		//业务逻辑结束
		this.doAfterFuncAction(request, response, model, null);
		
		if(logger.isDebugEnabled()){
			logger.debug("输出参数json:{}", json);
		}
		return json;
	}
	
	/**
	 * 
	 * @date 2017年8月2日 下午2:52:15
	 * 
	 * @Description: 创建单据页面
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 *
	 */
	@RequestMapping(value = "/funcPage/orderMonitor/createOrder")
	public ModelAndView createOrderPage(HttpServletRequest request,HttpServletResponse response, Model model) {
				
		if(logger.isDebugEnabled()){
			logger.debug("请求参数  createOrderPage getParameterMap:{}", JSON_UTILS.objectToJson(request.getParameterMap()));
		}
		
		this.doBeforeFuncPageAction(request, response, model, null);
		
		//业务逻辑开始
		ModelAndView pageView = new ModelAndView("/order/orderMonitor/createOrder");
		
		//业务逻辑结束
		this.doAfterFuncPageAction(request, response, model, null);
		
		return pageView;
	}
	
	
	/**
	 * 
	 * @date 2017年8月2日 下午2:56:49
	 * 
	 * @Description: 创建单据
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws Exception
	 *
	 */
	@RequestMapping(value = "/func/orderMonitor/createOrder")
	@ResponseBody
	public Object createOrder(HttpServletRequest request,HttpServletResponse response, Model model) throws Exception {

		if(logger.isDebugEnabled()){
			logger.debug("请求参数  createOrder getParameterMap:{}", JSON_UTILS.objectToJson(request.getParameterMap()));
		}
		//业务逻辑开始
		this.doBeforeFuncAction(request, response, model, null);
		String json = null;
		DealResult dealResult = new DealResult();
		
		try {
			// 获取并校验参数
			CreateOrderIbean ibean = JSON_UTILS.jsonToObject(this.getParameterFromRequest(request, "params"),
					CreateOrderIbean.class);		

			processOrderManageService.createOrder(ibean);
			
			dealResult.setReturnCode(DealResult.SUCCESS);
			dealResult.setReturnMsg(null);
			dealResult.setReturnData(null);
		} catch (Exception e) {
			logger.error("创建单据操作失败：{}", e);
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
	 * @date 2017年9月8日 下午2:35:13
	 * 
	 * @Description: 根据单据id查询流程关注人
	 * @param ibean
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 *
	 */
	@RequestMapping(value = "/func/orderMonitor/qryOrderFollowUserByOrderId")
	@ResponseBody
	public String qryOrderFollowUserByOrderId(WorkOrderIbean ibean,HttpServletRequest request,HttpServletResponse response, Model model) {
		
		if(logger.isDebugEnabled()){
			logger.debug("请求参数  qryOrderFollowUserByOrderId getParameterMap:{}", JSON_UTILS.objectToJson(request.getParameterMap()));
		}
		
		//业务逻辑开始
		this.doBeforeFuncAction(request, response, model, null);
		String json = null;
		
		List<OrderFollowUser> list = null;
		if(ibean != null && ibean.getOrderId() != null){
			list = orderFollowUserService.qryOrderFollowUserList(ibean);
		}
//		int records = (int) workOrderService.qryWorkOrderListCount(ibean);//总记录数
		//Integer offset = ibean.getOffset();
//		Integer limit = ibean.getLimit();//每页记录数
//		Integer page = ibean.getPage();
		
//		int total = records%limit==0? records/limit : records/limit +1;//总页数
		
//		int total = ibean.getTotalPage(records);
		
//		json = this.toDataGridJson(page, total, records, list);
		json = this.toDataGridJson(null, null, null, list);
		
		//业务逻辑结束
		this.doAfterFuncAction(request, response, model, null);
		
		if(logger.isDebugEnabled()){
			logger.debug("输出参数json:{}", json);
		}
		return json;
	}
	
	
	/**
	 * 
	 * @date 2017年9月4日 下午5:44:24
	 * 
	 * @Description: 弹窗单选窗口页面
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 *
	 */
	@RequestMapping(value = "/funcPage/orderMonitor/selOrder")
	public ModelAndView selOrder(HttpServletRequest request,HttpServletResponse response, Model model) {
				
		if(logger.isDebugEnabled()){
			logger.debug("请求参数  selOrder getParameterMap:{}", JSON_UTILS.objectToJson(request.getParameterMap()));
		}
		
		this.doBeforeFuncPageAction(request, response, model, null);
		
		//业务逻辑开始
		ModelAndView pageView = new ModelAndView("/order/orderMonitor/selOrder");
		
		//业务逻辑结束
		this.doAfterFuncPageAction(request, response, model, null);
		
		return pageView;
	}
	
	/**
	 * 
	 * @date 2017年9月8日 下午3:44:01
	 * 
	 * @Description: 启动流程页面
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 *
	 */
	@RequestMapping(value = "/funcPage/orderMonitor/processStart")
	public ModelAndView processStartPage(HttpServletRequest request,HttpServletResponse response, Model model) {
				
		if(logger.isDebugEnabled()){
			logger.debug("请求参数  processStartPage getParameterMap:{}", JSON_UTILS.objectToJson(request.getParameterMap()));
		}
		
		this.doBeforeFuncPageAction(request, response, model, null);
		
		//业务逻辑开始
		ModelAndView pageView = new ModelAndView("/order/orderMonitor/processStart");
		
		//业务逻辑结束
		this.doAfterFuncPageAction(request, response, model, null);
		
		return pageView;
	}
	
	/**
	 * 
	 * @date 2017年9月8日 下午3:39:36
	 * 
	 * @Description: 启动流程
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws Exception
	 *
	 */
	@RequestMapping(value = "/func/orderMonitor/processStart")
	@ResponseBody
	public Object processStart(HttpServletRequest request,HttpServletResponse response, Model model) {

		if(logger.isDebugEnabled()){
			logger.debug("请求参数  processStart getParameterMap:{}", JSON_UTILS.objectToJson(request.getParameterMap()));
		}
		//业务逻辑开始
		this.doBeforeFuncAction(request, response, model, null);
		String json = null;
		DealResult dealResult = new DealResult();
		
		try {
			// 获取并校验参数
			ProcessStartIbean ibean = JSON_UTILS.jsonToObject(this.getParameterFromRequest(request, "params"),
					ProcessStartIbean.class);		

			processOrderManageService.processStart(ibean);
			
			dealResult.setReturnCode(DealResult.SUCCESS);
			dealResult.setReturnMsg(null);
			dealResult.setReturnData(null);
		} catch (Exception e) {
			logger.error("启动流程操作失败：{}", e);
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
	 * @date 2017年9月8日 下午3:44:01
	 * 
	 * @Description: 流程重置页面
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 *
	 */
	@RequestMapping(value = "/funcPage/orderMonitor/processReset")
	public ModelAndView processResetPage(HttpServletRequest request,HttpServletResponse response, Model model) {
		
		if(logger.isDebugEnabled()){
			logger.debug("请求参数  processResetPage getParameterMap:{}", JSON_UTILS.objectToJson(request.getParameterMap()));
		}
		
		this.doBeforeFuncPageAction(request, response, model, null);
		
		//业务逻辑开始
		ModelAndView pageView = new ModelAndView("/order/orderMonitor/processReset");
		
		//业务逻辑结束
		this.doAfterFuncPageAction(request, response, model, null);
		
		return pageView;
	}
	
	/**
	 * 
	 * @date 2017年9月8日 下午3:39:36
	 * 
	 * @Description: 流程重置
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws Exception
	 *
	 */
	@RequestMapping(value = "/func/orderMonitor/processReset")
	@ResponseBody
	public Object processReset(HttpServletRequest request,HttpServletResponse response, Model model) {
		
		if(logger.isDebugEnabled()){
			logger.debug("请求参数  processReset getParameterMap:{}", JSON_UTILS.objectToJson(request.getParameterMap()));
		}
		//业务逻辑开始
		this.doBeforeFuncAction(request, response, model, null);
		String json = null;
		DealResult dealResult = new DealResult();
		
		try {
			// 获取并校验参数
			ProcessStartIbean ibean = JSON_UTILS.jsonToObject(this.getParameterFromRequest(request, "params"),
					ProcessStartIbean.class);		
			
			processOrderManageService.processReset(ibean);
			
			dealResult.setReturnCode(DealResult.SUCCESS);
			dealResult.setReturnMsg(null);
			dealResult.setReturnData(null);
		} catch (Exception e) {
			logger.error("流程重置操作失败：{}", e);
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
	 * @date 2017年9月8日 下午3:44:01
	 * 
	 * @Description: 流程跳转页面
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 *
	 */
	@RequestMapping(value = "/funcPage/orderMonitor/processJump")
	public ModelAndView processJumpPage(HttpServletRequest request,HttpServletResponse response, Model model) {
		
		if(logger.isDebugEnabled()){
			logger.debug("请求参数  processJumpPage getParameterMap:{}", JSON_UTILS.objectToJson(request.getParameterMap()));
		}
		
		this.doBeforeFuncPageAction(request, response, model, null);
		
		//业务逻辑开始
		ModelAndView pageView = new ModelAndView("/order/orderMonitor/processJump");
		
		//业务逻辑结束
		this.doAfterFuncPageAction(request, response, model, null);
		
		return pageView;
	}
	
	/**
	 * 
	 * @date 2017年9月8日 下午3:39:36
	 * 
	 * @Description: 流程跳转
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws Exception
	 *
	 */
	@RequestMapping(value = "/func/orderMonitor/processJump")
	@ResponseBody
	public Object processJump(HttpServletRequest request,HttpServletResponse response, Model model) {
		
		if(logger.isDebugEnabled()){
			logger.debug("请求参数  processJump getParameterMap:{}", JSON_UTILS.objectToJson(request.getParameterMap()));
		}
		//业务逻辑开始
		this.doBeforeFuncAction(request, response, model, null);
		String json = null;
		DealResult dealResult = new DealResult();
		
		try {
			// 获取并校验参数
			ProcessJumpIbean ibean = JSON_UTILS.jsonToObject(this.getParameterFromRequest(request, "params"),
					ProcessJumpIbean.class);		
			
			processOrderManageService.processJump(ibean);
			
			dealResult.setReturnCode(DealResult.SUCCESS);
			dealResult.setReturnMsg(null);
			dealResult.setReturnData(null);
		} catch (Exception e) {
			logger.error("流程跳转操作失败：{}", e);
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
	 * @date 2017年9月8日 下午3:44:01
	 * 
	 * @Description: 单据作废页面
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 *
	 */
	@RequestMapping(value = "/funcPage/orderMonitor/removeOrder")
	public ModelAndView removeOrderPage(HttpServletRequest request,HttpServletResponse response, Model model) {
		
		if(logger.isDebugEnabled()){
			logger.debug("请求参数  removeOrderPage getParameterMap:{}", JSON_UTILS.objectToJson(request.getParameterMap()));
		}
		
		this.doBeforeFuncPageAction(request, response, model, null);
		
		//业务逻辑开始
		ModelAndView pageView = new ModelAndView("/order/orderMonitor/removeOrder");
		
		//业务逻辑结束
		this.doAfterFuncPageAction(request, response, model, null);
		
		return pageView;
	}
	
	/**
	 * 
	 * @date 2017年9月8日 下午3:39:36
	 * 
	 * @Description: 单据作废
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws Exception
	 *
	 */
	@RequestMapping(value = "/func/orderMonitor/removeOrder")
	@ResponseBody
	public Object removeOrder(HttpServletRequest request,HttpServletResponse response, Model model) {
		
		if(logger.isDebugEnabled()){
			logger.debug("请求参数  removeOrder getParameterMap:{}", JSON_UTILS.objectToJson(request.getParameterMap()));
		}
		//业务逻辑开始
		this.doBeforeFuncAction(request, response, model, null);
		String json = null;
		DealResult dealResult = new DealResult();
		
		try {
			// 获取并校验参数
			ProcessStartIbean ibean = JSON_UTILS.jsonToObject(this.getParameterFromRequest(request, "params"),
					ProcessStartIbean.class);		
			
			processOrderManageService.removeOrder(ibean);
			
			dealResult.setReturnCode(DealResult.SUCCESS);
			dealResult.setReturnMsg(null);
			dealResult.setReturnData(null);
		} catch (Exception e) {
			logger.error("单据作废操作失败：{}", e);
			dealResult.setReturnCode(DealResult.FAILURE);
			dealResult.setReturnMsg(null);
			dealResult.setReturnData(null);
		}
		
		//业务逻辑结束
		this.doAfterFuncAction(request, response, model, null);
		return json;
	}
	
	/**
	 * 
	 * @date 2017年9月8日 下午5:31:52
	 * 
	 * @Description: 单据详情
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 *
	 */
	@RequestMapping(value = "/funcPage/orderMonitor/orderDetail")
	public ModelAndView orderDetail(HttpServletRequest request,HttpServletResponse response, Model model) {
		
		if(logger.isDebugEnabled()){
			logger.debug("请求参数  orderDetail getParameterMap:{}", JSON_UTILS.objectToJson(request.getParameterMap()));
		}
		
		this.doBeforeFuncPageAction(request, response, model, null);
		
		//业务逻辑开始
		ModelAndView pageView = new ModelAndView("/order/orderMonitor/orderDetail");
		
		//业务逻辑结束
		this.doAfterFuncPageAction(request, response, model, null);
		
		return pageView;
	}
	
	/**
	 * 
	 * @date 2017年9月11日 上午9:56:59
	 * 
	 * @Description: 根据单据id查询单据
	 * @param orderId
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 *
	 */
	@RequestMapping(value = "/func/orderMonitor/qryOrderById")
	@ResponseBody
	public String qryOrderById(Long orderId,HttpServletRequest request,HttpServletResponse response, Model model)  {
		if(logger.isDebugEnabled()){
			logger.debug("请求参数  qryOrderById getParameterMap:{}", JSON_UTILS.objectToJson(request.getParameterMap()));
		}
		
		//业务逻辑开始
		this.doBeforeFuncAction(request, response, model, null);
		String json = null;
		DealResult dealResult = new DealResult();
		
		Order order= null;
		if(orderId != null){
			order = orderService.qryByOrderId(orderId);
			
			dealResult.setReturnCode(DealResult.QRYSUCCESS);
			dealResult.setReturnMsg(null);
			dealResult.setReturnData(order);
		}
		json = JSON_UTILS.objectToJson(dealResult);

		//业务逻辑结束
		this.doAfterFuncAction(request, response, model, null);
		
		if(logger.isDebugEnabled()){
			logger.debug("输出参数json:{}", json);
		}
		return json;
	}
	
	
	/**
	 * 
	 * @date 2017年9月8日 下午2:35:13
	 * 
	 * @Description: 根据单据id查询单据操作记录
	 * @param ibean
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 *
	 */
	@RequestMapping(value = "/func/orderMonitor/qryOrderOperByOrderId")
	@ResponseBody
	public String qryOrderOperByOrderId(WorkOrderIbean ibean,HttpServletRequest request,HttpServletResponse response, Model model) {
		
		if(logger.isDebugEnabled()){
			logger.debug("请求参数  qryOrderOperByOrderId getParameterMap:{}", JSON_UTILS.objectToJson(request.getParameterMap()));
		}
		
		//业务逻辑开始
		this.doBeforeFuncAction(request, response, model, null);
		String json = null;
		
		List<OrderOper> list = null;
		if(ibean != null && ibean.getOrderId() != null){
			list = orderOperService.qryOrderOperList(ibean);
		}
//		int records = (int) workOrderService.qryWorkOrderListCount(ibean);//总记录数
		//Integer offset = ibean.getOffset();
//		Integer limit = ibean.getLimit();//每页记录数
//		Integer page = ibean.getPage();
		
//		int total = records%limit==0? records/limit : records/limit +1;//总页数
		
//		int total = ibean.getTotalPage(records);
		
//		json = this.toDataGridJson(page, total, records, list);
		json = this.toDataGridJson(null, null, null, list);
		
		//业务逻辑结束
		this.doAfterFuncAction(request, response, model, null);
		
		if(logger.isDebugEnabled()){
			logger.debug("输出参数json:{}", json);
		}
		return json;
	}
	
	/**
	 * 
	 * @date 2017年9月11日 上午11:15:00
	 * 
	 * @Description: 单据任务详情
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 *
	 */
	@RequestMapping(value = "/funcPage/orderMonitor/workOrderDetail")
	public ModelAndView workOrderDetail(HttpServletRequest request,HttpServletResponse response, Model model) {
		
		if(logger.isDebugEnabled()){
			logger.debug("请求参数  workOrderDetail getParameterMap:{}", JSON_UTILS.objectToJson(request.getParameterMap()));
		}
		
		this.doBeforeFuncPageAction(request, response, model, null);
		
		//业务逻辑开始
		ModelAndView pageView = new ModelAndView("/order/orderMonitor/workOrderDetail");
		
		//业务逻辑结束
		this.doAfterFuncPageAction(request, response, model, null);
		
		return pageView;
	}
	
	
	/**
	 * 
	 * @date 2017年9月11日 上午9:56:59
	 * 
	 * @Description: 根据任务id查询任务
	 * @param orderId
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 *
	 */
	@RequestMapping(value = "/func/orderMonitor/qryWorkOrderById")
	@ResponseBody
	public String qryWorkOrderById(Long workOrderId,HttpServletRequest request,HttpServletResponse response, Model model)  {
		if(logger.isDebugEnabled()){
			logger.debug("请求参数  qryWorkOrderById getParameterMap:{}", JSON_UTILS.objectToJson(request.getParameterMap()));
		}
		
		//业务逻辑开始
		this.doBeforeFuncAction(request, response, model, null);
		String json = null;
		DealResult dealResult = new DealResult();
		
		WorkOrder workOrder= null;
		if(workOrderId != null){
			workOrder = workOrderService.qryByWorkOrderId(workOrderId);
			
			dealResult.setReturnCode(DealResult.QRYSUCCESS);
			dealResult.setReturnMsg(null);
			dealResult.setReturnData(workOrder);
		}
		json = JSON_UTILS.objectToJson(dealResult);

		//业务逻辑结束
		this.doAfterFuncAction(request, response, model, null);
		
		if(logger.isDebugEnabled()){
			logger.debug("输出参数json:{}", json);
		}
		return json;
	}
	
	
	/**
	 * 
	 * @date 2017年9月8日 下午2:35:13
	 * 
	 * @Description: 根据任务id查询任务操作记录
	 * @param ibean
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 *
	 */
	@RequestMapping(value = "/func/orderMonitor/qryWorkOrderOperByWorkOrderId")
	@ResponseBody
	public String qryWorkOrderOperByWorkOrderId(WorkOrderIbean ibean,HttpServletRequest request,HttpServletResponse response, Model model) {
		
		if(logger.isDebugEnabled()){
			logger.debug("请求参数  qryWorkOrderOperByWorkOrderId getParameterMap:{}", JSON_UTILS.objectToJson(request.getParameterMap()));
		}
		
		//业务逻辑开始
		this.doBeforeFuncAction(request, response, model, null);
		String json = null;
		
		List<WorkOrderOper> list = null;
		if(ibean != null && ibean.getWorkOrderId() != null){
			list = workOrderOperService.qryWorkOrderOperList(ibean);
		}
//		int records = (int) workOrderService.qryWorkOrderListCount(ibean);//总记录数
		//Integer offset = ibean.getOffset();
//		Integer limit = ibean.getLimit();//每页记录数
//		Integer page = ibean.getPage();
		
//		int total = records%limit==0? records/limit : records/limit +1;//总页数
		
//		int total = ibean.getTotalPage(records);
		
//		json = this.toDataGridJson(page, total, records, list);
		json = this.toDataGridJson(null, null, null, list);
		
		//业务逻辑结束
		this.doAfterFuncAction(request, response, model, null);
		
		if(logger.isDebugEnabled()){
			logger.debug("输出参数json:{}", json);
		}
		return json;
	}
	
	
	/**
	 * 
	 * @date 2017年9月11日 下午2:48:53
	 * 
	 * @Description: 任务回单页面
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 *
	 */
	@RequestMapping(value = "/funcPage/orderMonitor/workOrderFinish")
	public ModelAndView workOrderFinishPage(HttpServletRequest request,HttpServletResponse response, Model model) {
		
		if(logger.isDebugEnabled()){
			logger.debug("请求参数  workOrderFinishPage getParameterMap:{}", JSON_UTILS.objectToJson(request.getParameterMap()));
		}
		
		this.doBeforeFuncPageAction(request, response, model, null);
		
		//业务逻辑开始
		ModelAndView pageView = new ModelAndView("/order/orderMonitor/workOrderFinish");
		
		//业务逻辑结束
		this.doAfterFuncPageAction(request, response, model, null);
		
		return pageView;
	}
	
	/**
	 * 
	 * @date 2017年9月11日 下午2:51:34
	 * 
	 * @Description: 任务回单
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 *
	 */
	@RequestMapping(value = "/func/orderMonitor/workOrderFinish")
	@ResponseBody
	public Object workOrderFinish(HttpServletRequest request,HttpServletResponse response, Model model) {
		
		if(logger.isDebugEnabled()){
			logger.debug("请求参数  workOrderFinish getParameterMap:{}", JSON_UTILS.objectToJson(request.getParameterMap()));
		}
		//业务逻辑开始
		this.doBeforeFuncAction(request, response, model, null);
		String json = null;
		DealResult dealResult = new DealResult();
		
		try {
			// 获取并校验参数
			WorkOrderFinish ibean = JSON_UTILS.jsonToObject(this.getParameterFromRequest(request, "params"),
					WorkOrderFinish.class);		
			
			processWorkOrderManageService.workOrderFinish(ibean);
			
			dealResult.setReturnCode(DealResult.SUCCESS);
			dealResult.setReturnMsg(null);
			dealResult.setReturnData(null);
		} catch (Exception e) {
			logger.error("任务回单操作失败：{}", e);
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
	 * @date 2017年9月11日 下午2:48:53
	 * 
	 * @Description: 任务转派页面
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 *
	 */
	@RequestMapping(value = "/funcPage/orderMonitor/workOrderDisp")
	public ModelAndView workOrderDispPage(HttpServletRequest request,HttpServletResponse response, Model model) {
		
		if(logger.isDebugEnabled()){
			logger.debug("请求参数  workOrderDispPage getParameterMap:{}", JSON_UTILS.objectToJson(request.getParameterMap()));
		}
		
		this.doBeforeFuncPageAction(request, response, model, null);
		
		//业务逻辑开始
		ModelAndView pageView = new ModelAndView("/order/orderMonitor/workOrderDisp");
		
		//业务逻辑结束
		this.doAfterFuncPageAction(request, response, model, null);
		
		return pageView;
	}
	
	/**
	 * 
	 * @date 2017年9月11日 下午2:51:34
	 * 
	 * @Description: 任务转派
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 *
	 */
	@RequestMapping(value = "/func/orderMonitor/workOrderDisp")
	@ResponseBody
	public Object workOrderDisp(HttpServletRequest request,HttpServletResponse response, Model model) {
		
		if(logger.isDebugEnabled()){
			logger.debug("请求参数  workOrderDisp getParameterMap:{}", JSON_UTILS.objectToJson(request.getParameterMap()));
		}
		//业务逻辑开始
		this.doBeforeFuncAction(request, response, model, null);
		String json = null;
		DealResult dealResult = new DealResult();
		
		try {
			// 获取并校验参数
			WorkOrderDisp ibean = JSON_UTILS.jsonToObject(this.getParameterFromRequest(request, "params"),
					WorkOrderDisp.class);		
			
			processWorkOrderManageService.workOrderDisp(ibean);
			
			dealResult.setReturnCode(DealResult.SUCCESS);
			dealResult.setReturnMsg(null);
			dealResult.setReturnData(null);
		} catch (Exception e) {
			logger.error("任务回单操作失败：{}", e);
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
	 * @date 2017年9月11日 下午2:48:53
	 * 
	 * @Description: 任务提单页面
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 *
	 */
	@RequestMapping(value = "/funcPage/orderMonitor/workOrderGet")
	public ModelAndView workOrderGetPage(HttpServletRequest request,HttpServletResponse response, Model model) {
		
		if(logger.isDebugEnabled()){
			logger.debug("请求参数  workOrderGetPage getParameterMap:{}", JSON_UTILS.objectToJson(request.getParameterMap()));
		}
		
		this.doBeforeFuncPageAction(request, response, model, null);
		
		//业务逻辑开始
		ModelAndView pageView = new ModelAndView("/order/orderMonitor/workOrderGet");
		
		//业务逻辑结束
		this.doAfterFuncPageAction(request, response, model, null);
		
		return pageView;
	}
	
	/**
	 * 
	 * @date 2017年9月11日 下午2:51:34
	 * 
	 * @Description: 任务提单
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 *
	 */
	@RequestMapping(value = "/func/orderMonitor/workOrderGet")
	@ResponseBody
	public Object workOrderGet(HttpServletRequest request,HttpServletResponse response, Model model) {
		
		if(logger.isDebugEnabled()){
			logger.debug("请求参数  workOrderGet getParameterMap:{}", JSON_UTILS.objectToJson(request.getParameterMap()));
		}
		//业务逻辑开始
		this.doBeforeFuncAction(request, response, model, null);
		String json = null;
		DealResult dealResult = new DealResult();
		
		try {
			// 获取并校验参数
			WorkOrderGet ibean = JSON_UTILS.jsonToObject(this.getParameterFromRequest(request, "params"),
					WorkOrderGet.class);		
			
			processWorkOrderManageService.workOrderGet(ibean);
			
			dealResult.setReturnCode(DealResult.SUCCESS);
			dealResult.setReturnMsg(null);
			dealResult.setReturnData(null);
		} catch (Exception e) {
			logger.error("任务提单操作失败：{}", e);
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
	 * @date 2017年9月13日 上午11:22:37
	 * 
	 * @Description: 根据单据类型查询单据按钮
	 * @param ibean
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 *
	 */
	@RequestMapping(value = "/func/orderMonitor/qryOrderButtonList")
	@ResponseBody
	public String qryOrderButtonList(ButtonIbean ibean,HttpServletRequest request,HttpServletResponse response, Model model) {
		
		if(logger.isDebugEnabled()){
			logger.debug("请求参数  qryOrderButtonList getParameterMap:{}", JSON_UTILS.objectToJson(request.getParameterMap()));
		}
		
		//业务逻辑开始
		this.doBeforeFuncAction(request, response, model, null);
		String json = null;
		DealResult dealResult = new DealResult();
		
		List<Button> list = null;
		if(ibean != null && StringUtils.isNoneBlank(ibean.getOrderType())){
			list = buttonService.qryOrderButtonList(ibean);
			
			dealResult.setReturnCode(DealResult.QRYSUCCESS);
			dealResult.setReturnMsg(null);
			dealResult.setReturnData(list);
		}
//		int records = (int) workOrderService.qryWorkOrderListCount(ibean);//总记录数
		//Integer offset = ibean.getOffset();
//		Integer limit = ibean.getLimit();//每页记录数
//		Integer page = ibean.getPage();
		
//		int total = records%limit==0? records/limit : records/limit +1;//总页数
		
//		int total = ibean.getTotalPage(records);
		
//		json = this.toDataGridJson(page, total, records, list);
//		json = this.toDataGridJson(null, null, null, list);
		json = JSON_UTILS.objectToJson(dealResult);
		
		//业务逻辑结束
		this.doAfterFuncAction(request, response, model, null);
		
		if(logger.isDebugEnabled()){
			logger.debug("输出参数json:{}", json);
		}
		return json;
	}
	
	/**
	 * 
	 * @date 2017年9月13日 上午11:22:37
	 * 
	 * @Description: 根据环节id查询任务按钮
	 * @param ibean
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 *
	 */
	@RequestMapping(value = "/func/orderMonitor/qryWoButtonList")
	@ResponseBody
	public String qryWoButtonList(ButtonIbean ibean,HttpServletRequest request,HttpServletResponse response, Model model) {
		
		if(logger.isDebugEnabled()){
			logger.debug("请求参数  qryWoButtonList getParameterMap:{}", JSON_UTILS.objectToJson(request.getParameterMap()));
		}
		
		//业务逻辑开始
		this.doBeforeFuncAction(request, response, model, null);
		String json = null;
		DealResult dealResult = new DealResult();
		
		List<Button> list = null;
		if(ibean != null && ibean.getTacheId() != null){
			list = buttonService.qryWoButtonList(ibean);
			
			dealResult.setReturnCode(DealResult.QRYSUCCESS);
			dealResult.setReturnMsg(null);
			dealResult.setReturnData(list);
		}
//		int records = (int) workOrderService.qryWorkOrderListCount(ibean);//总记录数
		//Integer offset = ibean.getOffset();
//		Integer limit = ibean.getLimit();//每页记录数
//		Integer page = ibean.getPage();
		
//		int total = records%limit==0? records/limit : records/limit +1;//总页数
		
//		int total = ibean.getTotalPage(records);
		
//		json = this.toDataGridJson(page, total, records, list);
//		json = this.toDataGridJson(null, null, null, list);
		json = JSON_UTILS.objectToJson(dealResult);
		
		//业务逻辑结束
		this.doAfterFuncAction(request, response, model, null);
		
		if(logger.isDebugEnabled()){
			logger.debug("输出参数json:{}", json);
		}
		return json;
	}
	
}
