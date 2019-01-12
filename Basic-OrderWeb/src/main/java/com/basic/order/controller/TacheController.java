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
import com.basic.order.bean.TacheIbean;
import com.basic.order.define.BasicStateEnum;
import com.basic.order.model.Tache;
import com.basic.order.service.TacheService;

/**
 * 
 *
 * @date 2017年8月2日 下午2:46:00
 * 
 * @Description: 环节
 *
 */
@Controller
@RequestMapping(value = TacheController.REQUEST_PATH)
public class TacheController extends BaseController{

	protected static final String REQUEST_PATH = "/order";
	
	private static Logger logger = LoggerFactory.getLogger(TacheController.class);
	
	private static final JsonUtils JSON_UTILS = JsonUtils.getInstance();
	
	@Autowired
	private TacheService tacheService;

	/**
	 * 
	 * @date 2017年8月2日 下午2:47:46
	 * 
	 * @Description: 环节主页面
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 *
	 */
	@RequestMapping(value = "/menuPage/tache")
	public ModelAndView intoPage(HttpServletRequest request,HttpServletResponse response, Model model) {
		
		if(logger.isDebugEnabled()){
			logger.debug("请求参数  intoPage getParameterMap:{}", JSON_UTILS.objectToJson(request.getParameterMap()));
		}
		
		this.doBeforeMenuPageAction(request, response, model, null);
		
		//业务逻辑开始
		ModelAndView pageView = new ModelAndView("/order/tache/tache");
		
		//业务逻辑结束
		this.doAfterMenuPageAction(request, response, model, null);
		
		return pageView;
	}
	
	/**
	 * 
	 * @date 2017年8月2日 下午2:48:15
	 * 
	 * @Description: 环节主页面列表查询
	 * @param ibean
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 *
	 */
	@RequestMapping(value = "/func/tache/pagin")
	@ResponseBody
	public String pagin(TacheIbean ibean,HttpServletRequest request,HttpServletResponse response, Model model) {

		if(logger.isDebugEnabled()){
			logger.debug("请求参数  pagin getParameterMap:{}", JSON_UTILS.objectToJson(request.getParameterMap()));
		}
		
		//业务逻辑开始
		this.doBeforeFuncAction(request, response, model, null);
		String json = null;
		
		List<Tache> list = tacheService.qryTacheList(ibean);
		int records = (int) tacheService.qryTacheListCount(ibean);//总记录数
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
	 * @Description: 环节新增修改详情页面
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 *
	 */
	@RequestMapping(value = "/funcPage/tache/tacheDetail")
	public ModelAndView detail(HttpServletRequest request,HttpServletResponse response, Model model) {
				
		if(logger.isDebugEnabled()){
			logger.debug("请求参数  detail getParameterMap:{}", JSON_UTILS.objectToJson(request.getParameterMap()));
		}
		
		this.doBeforeFuncPageAction(request, response, model, null);
		
		//业务逻辑开始
		ModelAndView pageView = new ModelAndView("/order/tache/tacheDetail");
		
		//业务逻辑结束
		this.doAfterFuncPageAction(request, response, model, null);
		
		return pageView;
	}
	
	
	/**
	 * 
	 * @date 2017年8月2日 下午2:56:49
	 * 
	 * @Description: 环节新增编辑操作
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws Exception
	 *
	 */
	@RequestMapping(value = "/func/tache/createOrModify")
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
		Tache ibean = JSON_UTILS.jsonToObject(this.getParameterFromRequest(request, "params"),
				Tache.class);		

			tacheService.createOrModify(ibean);
			
			dealResult.setReturnCode(DealResult.SUCCESS);
			dealResult.setReturnMsg(null);
			dealResult.setReturnData(null);
		} catch (Exception e) {
			logger.error("环节新增编辑操作失败：{}", e);
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
	 * @Description: 环节批量失效
	 * @param ibean
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 *
	 */
	@RequestMapping(value = "/func/tache/del")
	@ResponseBody
	public Object del(Tache ibean,HttpServletRequest request,HttpServletResponse response, Model model)  {
		
		if(logger.isDebugEnabled()){
			logger.debug("请求参数  del getParameterMap:{}", JSON_UTILS.objectToJson(request.getParameterMap()));
		}
		//业务逻辑开始
		this.doBeforeFuncAction(request, response, model, null);
		
		String json = null;
		DealResult dealResult = new DealResult();
		try {
			if(ibean != null && ibean.getTacheIds() != null && ibean.getTacheIds().length > 0){
				ibean.setState(BasicStateEnum.INVALID.getCode());
				Date now = DateUtils.now();
				ibean.setModifyTime(now);
				tacheService.modifyBatchStateByTacheIds(ibean);
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
	@RequestMapping(value = "/funcPage/tache/selTache")
	public ModelAndView selTache(HttpServletRequest request,HttpServletResponse response, Model model) {
				
		if(logger.isDebugEnabled()){
			logger.debug("请求参数  selTache getParameterMap:{}", JSON_UTILS.objectToJson(request.getParameterMap()));
		}
		
		this.doBeforeFuncPageAction(request, response, model, null);
		
		//业务逻辑开始
		ModelAndView pageView = new ModelAndView("/order/tache/selTache");
		
		//业务逻辑结束
		this.doAfterFuncPageAction(request, response, model, null);
		
		return pageView;
	}
	
}
