/**
 *
 * companyName
 * copyright 2015-2020
 * @date 2017年9月5日 上午11:53:40
 * @author lihiajun 
 * @Description: 流程适配规则
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
import com.basic.order.bean.ProcessRuleIbean;
import com.basic.order.define.BasicStateEnum;
import com.basic.order.model.ProcessRule;
import com.basic.order.service.ProcessRuleService;

/**
 *
 * @date 2017年9月1日 上午11:53:40
 * @author lihaijun
 * @Description: 流程适配
 * 
 */
@Controller
@RequestMapping(value = ProcessRuleController.REQUEST_PATH)
public class ProcessRuleController extends BaseController{

	protected static final String REQUEST_PATH = "/order";
	
	private static Logger logger = LoggerFactory.getLogger(ProcessRuleController.class);
	
	private static final JsonUtils JSON_UTILS = JsonUtils.getInstance();
	
	@Autowired
	private ProcessRuleService processRuleService;
	
	
	
    /**
     * 
     * @date 2017年9月7日 下午6:06:46
     * @author lihaijun
     * @Description: 流程适配规则初始化
     * @param request
     * @param response
     * @param model
     * @return
     *
     */
	@RequestMapping(value = "/menuPage/processRule")
	public ModelAndView intoPage(HttpServletRequest request,HttpServletResponse response, Model model) {
		
		if(logger.isDebugEnabled()){
			logger.debug("请求参数  intoPage getParameterMap:{}", JSON_UTILS.objectToJson(request.getParameterMap()));
		}
		
		this.doBeforeMenuPageAction(request, response, model, null);
		
		//业务逻辑开始
		ModelAndView pageView = new ModelAndView("/order/processRule/processRule");
		
		//业务逻辑结束
		this.doAfterMenuPageAction(request, response, model, null);
		
		return pageView;
	
	}
	
	/**
	 * 
	 * @date 2017年9月7日 下午6:08:41
	 * @author lihaijun
	 * @Description: 根据参数查询流程适配规则
	 * @param orderServicebean
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 *
	 */
	@RequestMapping(value = "/func/processRule/pagin", produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public String qryPage(ProcessRuleIbean processRulebean,HttpServletRequest request,HttpServletResponse response, Model model) {
				
		if(logger.isDebugEnabled()){
			logger.debug("请求参数  qryPage processRulebean:{},getParameterMap:{}", JSON_UTILS.objectToJson(processRulebean), JSON_UTILS.objectToJson(request.getParameterMap()));
		}
		
		//业务逻辑开始
		this.doBeforeFuncAction(request, response, model, null);
		String json = null;
		
		List<ProcessRule> list = processRuleService.qryProcessRuleList(processRulebean);
		int records =  (int) processRuleService.qryProcessRuleListCount(processRulebean);//总记录数
		Integer page = processRulebean.getPage();
		int total = processRulebean.getTotalPage(records);
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
	 * @date 2017年9月7日 下午6:13:04
	 * @author lihaijun
	 * @Description: 流程适配新增页面详情
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 *
	 */
	@RequestMapping(value = "/funcPage/processRule/processRuleDetail")
	public ModelAndView processRuleDetail(HttpServletRequest request,HttpServletResponse response, Model model) {
				
		if(logger.isDebugEnabled()){
			logger.debug("请求参数  OrderPage getParameterMap:{}", JSON_UTILS.objectToJson(request.getParameterMap()));
		}
		
		this.doBeforeFuncAction(request, response, model, null);
		
		//业务逻辑开始
		ModelAndView pageView = new ModelAndView("/order/processRule/processRuleDetail");
		
		//业务逻辑结束
		this.doAfterFuncAction(request, response, model, null);
		
		return pageView;
	}
	
	/**
	 * 
	 * @date 2017年9月7日 下午6:14:18
	 * @author lihaijun
	 * @Description: 添加或更新流程适配
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 *
	 */
	@RequestMapping(value = "/func/processRule/createOrModify")
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
		ProcessRule bean = JSON_UTILS.jsonToObject(this.getParameterFromRequest(request, "params"),
				ProcessRule.class);		

		processRuleService.createOrModify(bean);
			
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
	 * @date 2017年9月7日 下午6:15:34
	 * @author lihaijun
	 * @Description: 批量删除
	 * @param ibean
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 *
	 */
	@RequestMapping(value = "/func/processRule/del")
	@ResponseBody
	public Object del(ProcessRule ibean,HttpServletRequest request,HttpServletResponse response, Model model)  {
		
		if(logger.isDebugEnabled()){
			logger.debug("请求参数  del getParameterMap:{}", JSON_UTILS.objectToJson(request.getParameterMap()));
		}
		//业务逻辑开始
		this.doBeforeFuncAction(request, response, model, null);
		
		String json = null;
		DealResult dealResult = new DealResult();
		
		try {
			if(ibean != null && ibean.getRuleIds() != null && ibean.getRuleIds().length > 0 ){
				ibean.setState(BasicStateEnum.INVALID.getCode());
				Date now = DateUtils.now();
				ibean.setModifyTime(now);
				processRuleService.modifyBatchStateByRuleIds(ibean);
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
