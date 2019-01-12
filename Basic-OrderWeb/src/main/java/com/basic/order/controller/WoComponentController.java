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

import com.basic.framework.common.model.DealResult;
import com.basic.framework.common.utils.datatype.JsonUtils;
import com.basic.framework.web.BaseController;
import com.basic.order.bean.OrderComponetIbean;
import com.basic.order.define.BasicStateEnum;
import com.basic.order.model.WoComponent;
import com.basic.order.service.WoComponentService;

/**
 * 
 *
 * @date 2017年9月1日 下午5:01:38
 * @author wangkui
 * @Description: 任务组件
 *
 */
@Controller
@RequestMapping(value = WoComponentController.REQUEST_PATH)
public class WoComponentController extends BaseController{

	protected static final String REQUEST_PATH = "/order";
	
	private static Logger logger = LoggerFactory.getLogger(WoComponentController.class);
	
	private static final JsonUtils JSON_UTILS = JsonUtils.getInstance();
	
	
	@Autowired
	private WoComponentService woComponentService;

	/**
	 * 
	 * @date 2017年8月2日 下午2:47:46
	 * 
	 * @Description: 优先级主页面
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 *
	 */
	@RequestMapping(value = "/menuPage/woComponent")
	public ModelAndView intoPage(HttpServletRequest request,HttpServletResponse response, Model model) {
		
		if(logger.isDebugEnabled()){
			logger.debug("请求参数  intoPage getParameterMap:{}", JSON_UTILS.objectToJson(request.getParameterMap()));
		}
		
		this.doBeforeMenuPageAction(request, response, model, null);
		
		//业务逻辑开始
		ModelAndView pageView = new ModelAndView("/order/woComponent/woComponent");
		
		//业务逻辑结束
		this.doAfterMenuPageAction(request, response, model, null);
		
		return pageView;
	}
	
	/**
	 * 
	 * @date 2017年8月2日 下午2:48:15
	 * 
	 * @Description: 组件管理主页面列表查询
	 * @param ibean
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 *
	 */
	@RequestMapping(value = "/func/woComponet/pagin")
	@ResponseBody
	public String pagin(OrderComponetIbean ibean,HttpServletRequest request,HttpServletResponse response, Model model) {

		if(logger.isDebugEnabled()){
			logger.debug("请求参数  pagin getParameterMap:{}", JSON_UTILS.objectToJson(request.getParameterMap()));
		}
		
		//业务逻辑开始
		this.doBeforeFuncAction(request, response, model, null);
		String json = null;
		
		List<WoComponent> list = woComponentService.qryComponentList(ibean);
		int records = (int) woComponentService.qryComponentListCount(ibean);//总记录数
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
	 * @date 2017年8月2日 下午2:52:15
	 * 
	 * @Description: 组件管理新增修改详情页面
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 *
	 */
	@RequestMapping(value = "/funcPage/woComponet/woComponetDetail")
	public ModelAndView detail(HttpServletRequest request,HttpServletResponse response, Model model) {
				
		if(logger.isDebugEnabled()){
			logger.debug("请求参数  detail getParameterMap:{}", JSON_UTILS.objectToJson(request.getParameterMap()));
		}
		
		this.doBeforeFuncPageAction(request, response, model, null);
		
		//业务逻辑开始
		ModelAndView pageView = new ModelAndView("/order/woComponent/woComponentDetail");
		
		//业务逻辑结束
		this.doAfterFuncPageAction(request, response, model, null);
		
		return pageView;
	}
	
	
	/**
	 * 
	 * @date 2017年8月2日 下午2:56:49
	 * 
	 * @Description: 组件管理新增编辑操作
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws Exception
	 *
	 */
	@RequestMapping(value = "/func/woComponet/createOrModify")
	@ResponseBody
	public Object createOrModify(HttpServletRequest request,HttpServletResponse response, Model model) throws Exception {

		if(logger.isDebugEnabled()){
			logger.debug("请求参数  createOrModify getParameterMap:{}", JSON_UTILS.objectToJson(request.getParameterMap()));
		}
		//业务逻辑开始
		this.doBeforeFuncAction(request, response, model, null);
		String json = null;
		DealResult dealResult = new DealResult();
		
		try {
		// 获取并校验参数
			WoComponent ibean = JSON_UTILS.jsonToObject(this.getParameterFromRequest(request, "params"),
					WoComponent.class);		

		woComponentService.createOrModify(ibean);
			
		dealResult.setReturnCode(DealResult.SUCCESS);
		dealResult.setReturnMsg(null);
		dealResult.setReturnData(null);
		} catch (Exception e) {
			logger.error("优先级新增编辑操作失败：{}", e);
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
	 * @date 2017年8月30日 下午4:35:22
	 * 
	 * @Description: 优先级批量失效
	 * @param ibean
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 *
	 */
	@RequestMapping(value = "/func/woComponent/del")
	@ResponseBody
	public Object del(WoComponent ibean,HttpServletRequest request,HttpServletResponse response, Model model)  {
		
		if(logger.isDebugEnabled()){
			logger.debug("请求参数  del getParameterMap:{}", JSON_UTILS.objectToJson(request.getParameterMap()));
		}
		//业务逻辑开始
		this.doBeforeFuncAction(request, response, model, null);
		
		String json = null;
		DealResult dealResult = new DealResult();
		try {
			if(ibean != null && ibean.getComponentIds() != null && ibean.getComponentIds().length > 0){
				ibean.setComponentState(BasicStateEnum.INVALID.getCode());
				woComponentService.modifyBatchStateByCompoentId(ibean);
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
