/**
 *
 * companyName
 * copyright 2015-2020
 * @date 2017年7月6日 下午5:00:02
 * @author Kevin
 * @Description: 用户控制类
 * 
 */
package com.basic.oaas.controller;



import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.basic.framework.common.model.DealResult;
import com.basic.framework.common.utils.datatype.JsonUtils;
import com.basic.framework.web.BaseController;
import com.basic.oaas.model.PrivateMenuCatalog;
import com.basic.oaas.service.PrivateMenuCatalogService;

/**
 *
 * @date 2017年7月6日 下午5:00:02
 * @author Kevin
 * @Description: 菜单目录控制器
 * 
 */
@Controller
@RequestMapping(value=MenuCatalogController.REQUEST_PATH)
public class MenuCatalogController extends BaseController{
	
	protected static final String REQUEST_PATH = "/oaas";
	
	private static Logger logger = LoggerFactory.getLogger(MenuCatalogController.class);
	
	private static final JsonUtils JSON_UTILS = JsonUtils.getInstance();
	
	
	@Autowired
	private PrivateMenuCatalogService privateMenuCatalogService;
	
	
	
	
	/**
	 * 
	 * @date 2017年8月8日 下午4:00:45
	 * @author Kevin
	 * @Description: 获取权限目录树
	 * @param menuIds
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 *
	 */
	@RequestMapping(value = "/func/menuCatalog/qryTree")
	@ResponseBody
	public Object qryTree(@RequestParam(defaultValue="-1")Long parentCatalogId)  {
		
		if(logger.isDebugEnabled()){
			logger.debug("请求参数  qryTree parentCatalogId:{}", parentCatalogId);
		}
		//业务逻辑开始
		this.doBeforeFuncAction(null, null, null, null);
		
		String json = null;
		
		List<PrivateMenuCatalog> list  = privateMenuCatalogService.qrySubMenuCatalog(parentCatalogId);
		
		for (PrivateMenuCatalog menuCatalog : list) {
			int count = privateMenuCatalogService.qryExistSubMenuCatalog(menuCatalog.getCatalogId());
			
			String flag = count>0? "true":"false";
			menuCatalog.setIsParent(flag);
		}
		
		json = JSON_UTILS.objectToJson(list);
		//业务逻辑结束
		this.doAfterFuncAction(null, null, null, null);
		return json;
	}
	
	/**
	 * 
	 * @date 2017年8月2日 下午2:52:15
	 * @author Kevin
	 * @Description: 菜单目录新增修改详情页面
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 *
	 */
	@RequestMapping(value = "/funcPage/menuCatalog/catalogDetail")
	public ModelAndView catalogDetail(HttpServletRequest request,HttpServletResponse response, Model model) {
				
		if(logger.isDebugEnabled()){
			logger.debug("请求参数  detail getParameterMap:{}", JSON_UTILS.objectToJson(request.getParameterMap()));
		}
		
		this.doBeforeFuncPageAction(request, response, model, null);
		
		//业务逻辑开始
		ModelAndView pageView = new ModelAndView("/oaas/menu/menuCatalogDetail");
		
		//业务逻辑结束
		this.doAfterFuncPageAction(request, response, model, null);
		
		return pageView;
	}
	
	/**
	 * 
	 * @date 2017年8月2日 下午2:56:49
	 * @author Kevin
	 * @Description: 菜单目录表新增编辑操作
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws Exception
	 *
	 */
	@RequestMapping(value = "/func/menuCatalog/createOrModify")
	@ResponseBody
	public Object catalogCreateOrModify(HttpServletRequest request,HttpServletResponse response, Model model) throws Exception {

		if(logger.isDebugEnabled()){
			logger.debug("请求参数  createOrModify getParameterMap:{}",JSON_UTILS.objectToJson(request.getParameterMap()));
		}
		//业务逻辑开始
		this.doBeforeFuncAction(request, response, model, null);
		String json = null;
		DealResult dealResult = new DealResult();
		try {
		// 获取并校验参数
		PrivateMenuCatalog ibean = JSON_UTILS.jsonToObject(this.getParameterFromRequest(request, "params"),
				PrivateMenuCatalog.class);		

		privateMenuCatalogService.createOrModify(ibean);
			
			dealResult.setReturnCode(DealResult.SUCCESS);
		} catch (Exception e) {
			logger.error("菜单目录表新增编辑操作失败：{}", e);
			dealResult.setReturnCode(DealResult.FAILURE);
			dealResult.setReturnMsg(e.getMessage());
		}
		
		json = JSON_UTILS.objectToJson(dealResult);
		//业务逻辑结束
		this.doAfterFuncAction(request, response, model, null);
		return json;
	}
	
	/**
	 * 
	 * @date 2017年8月8日 下午3:55:35
	 * @author Kevin
	 * @Description: 菜单目录批量删除
	 * @param dictIds
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 *
	 */
	@RequestMapping(value = "/func/menuCatalog/del")
	@ResponseBody
	public Object catalogDel(Long catalogId,HttpServletRequest request,HttpServletResponse response, Model model)  {
		
		if(logger.isDebugEnabled()){
			logger.debug("请求参数  del getParameterMap:{}", JSON_UTILS.objectToJson(request.getParameterMap()));
		}
		//业务逻辑开始
		this.doBeforeFuncAction(request, response, model, null);
		
		DealResult dealResult =  new DealResult();
		String json = null;
		try {
			if(catalogId != null ){
				
				privateMenuCatalogService.modifyAllStateById(catalogId);
				dealResult.setReturnCode(DealResult.SUCCESS);
			}
		} catch (Exception e) {
			logger.error("删除失败:{}", e);
			dealResult.setReturnCode(DealResult.FAILURE);
			dealResult.setReturnMsg(e.getMessage());
		}
		
		json = JSON_UTILS.objectToJson(dealResult);
		//业务逻辑结束
		this.doAfterFuncAction(request, response, model, null);
		return json;
	}
	
	/**
	 * 
	 * @date 2017年8月15日 下午5:54:41
	 * @author Kevin
	 * @Description: 弹窗选择目录
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 *
	 */
	@RequestMapping(value = "/funcPage/menu/selCatalog")
	public ModelAndView selOrg(HttpServletRequest request,HttpServletResponse response, Model model) {
				
		if(logger.isDebugEnabled()){
			logger.debug("请求参数  detail getParameterMap:{}", JSON_UTILS.objectToJson(request.getParameterMap()));
		}
		
		this.doBeforeFuncPageAction(request, response, model, null);
		
		//业务逻辑开始
		ModelAndView pageView = new ModelAndView("/oaas/menu/selCatalog");
		
		//业务逻辑结束
		this.doAfterFuncPageAction(request, response, model, null);
		
		return pageView;
	}
	
}
