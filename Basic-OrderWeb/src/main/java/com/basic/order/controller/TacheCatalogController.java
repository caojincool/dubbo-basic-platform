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
import com.basic.order.define.BasicStateEnum;
import com.basic.order.model.TacheCatalog;
import com.basic.order.service.TacheCatalogService;

/**
 * 
 *
 * @date 2017年8月2日 下午2:46:00
 * 
 * @Description: 环节目录
 *
 */
@Controller
@RequestMapping(value = TacheCatalogController.REQUEST_PATH)
public class TacheCatalogController extends BaseController{

	protected static final String REQUEST_PATH = "/order";
	
	private static Logger logger = LoggerFactory.getLogger(TacheCatalogController.class);
	
	private static final JsonUtils JSON_UTILS = JsonUtils.getInstance();
	
	@Autowired
	private TacheCatalogService tacheCatalogService;

	/**
	 * 
	 * @date 2017年9月4日 下午4:58:30
	 * 
	 * @Description: 环节目录树，分步查询
	 * @param parentCatalogId
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 *
	 */
	@RequestMapping(value = "/func/tacheCatalog/qryTreeStep")
	@ResponseBody
	public String qryTreeStep(Long id,HttpServletRequest request,HttpServletResponse response, Model model)  {
		if(logger.isDebugEnabled()){
			logger.debug("请求参数  qryTreeStep getParameterMap:{}", JSON_UTILS.objectToJson(request.getParameterMap()));
		}
		
		//业务逻辑开始
		this.doBeforeFuncAction(request, response, model, null);
		String json = null;
		
		Long parentCatalogId = null;
		if(id == null){
			id = -1L;
		}
		parentCatalogId = id;
		List<TacheCatalog> list = tacheCatalogService.qryByParentId(parentCatalogId);
		json = JSON_UTILS.objectToJson(list);

		//业务逻辑结束
		this.doAfterFuncAction(request, response, model, null);
		
		if(logger.isDebugEnabled()){
			logger.debug("输出参数json:{}", json);
		}
		return json;
	}


	/**
	 * 
	 * @date 2017年9月5日 上午10:14:59
	 * 
	 * @Description: 根据主键查询
	 * @param catalogId
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 *
	 */
	@RequestMapping(value = "/func/tacheCatalog/qryTacheCatalog")
	@ResponseBody
	public String qryTacheCatalog(Long catalogId,HttpServletRequest request,HttpServletResponse response, Model model)  {
		if(logger.isDebugEnabled()){
			logger.debug("请求参数  qryTacheCatalog getParameterMap:{}", JSON_UTILS.objectToJson(request.getParameterMap()));
		}
		
		//业务逻辑开始
		this.doBeforeFuncAction(request, response, model, null);
		String json = null;
		DealResult dealResult = new DealResult();
		
		if(catalogId != null){
			TacheCatalog item = tacheCatalogService.qryByPrimaryKey(catalogId);
			
			dealResult.setReturnCode(DealResult.QRYSUCCESS);
			dealResult.setReturnMsg(null);
			dealResult.setReturnData(item);
			
			json = JSON_UTILS.objectToJson(dealResult);
		}

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
	 * @Description: 环节目录新增修改详情页面
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 *
	 */
	@RequestMapping(value = "/funcPage/tacheCatalog/tacheCatalogDetail")
	public ModelAndView detail(HttpServletRequest request,HttpServletResponse response, Model model) {
				
		if(logger.isDebugEnabled()){
			logger.debug("请求参数  detail getParameterMap:{}", JSON_UTILS.objectToJson(request.getParameterMap()));
		}
		
		this.doBeforeFuncPageAction(request, response, model, null);
		
		//业务逻辑开始
		ModelAndView pageView = new ModelAndView("/order/tacheCatalog/tacheCatalogDetail");
		
		//业务逻辑结束
		this.doAfterFuncPageAction(request, response, model, null);
		
		return pageView;
	}
	
	
	/**
	 * 
	 * @date 2017年8月2日 下午2:56:49
	 * 
	 * @Description: 环节目录新增编辑操作
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws Exception
	 *
	 */
	@RequestMapping(value = "/func/tacheCatalog/createOrModify")
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
		TacheCatalog ibean = JSON_UTILS.jsonToObject(this.getParameterFromRequest(request, "params"),
				TacheCatalog.class);		

			tacheCatalogService.createOrModify(ibean);
			
			dealResult.setReturnCode(DealResult.SUCCESS);
			dealResult.setReturnMsg(null);
			dealResult.setReturnData(null);
		} catch (Exception e) {
			logger.error("环节目录新增编辑操作失败：{}", e);
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
	 * @Description: 环节目录批量失效
	 * TODO 后面看是否需要把下面的子节点也作废
	 * @param ibean
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 *
	 */
	@RequestMapping(value = "/func/tacheCatalog/del")
	@ResponseBody
	public Object del(TacheCatalog ibean,HttpServletRequest request,HttpServletResponse response, Model model)  {
		
		if(logger.isDebugEnabled()){
			logger.debug("请求参数  del getParameterMap:{}", JSON_UTILS.objectToJson(request.getParameterMap()));
		}
		//业务逻辑开始
		this.doBeforeFuncAction(request, response, model, null);
		
		String json = null;
		DealResult dealResult = new DealResult();
		try {
			if(ibean != null && ibean.getCatalogId() != null){
				ibean.setState(BasicStateEnum.INVALID.getCode());
				Date now = DateUtils.now();
				ibean.setModifyTime(now);
				tacheCatalogService.modifyByPrimaryKeySelective(ibean);
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
	@RequestMapping(value = "/funcPage/tacheCatalog/selTacheCatalog")
	public ModelAndView selTacheCatalog(HttpServletRequest request,HttpServletResponse response, Model model) {
				
		if(logger.isDebugEnabled()){
			logger.debug("请求参数  selTacheCatalog getParameterMap:{}", JSON_UTILS.objectToJson(request.getParameterMap()));
		}
		
		this.doBeforeFuncPageAction(request, response, model, null);
		
		//业务逻辑开始
		ModelAndView pageView = new ModelAndView("/order/tacheCatalog/selTacheCatalog");
		
		//业务逻辑结束
		this.doAfterFuncPageAction(request, response, model, null);
		
		return pageView;
	}
	
}
