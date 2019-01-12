package com.basic.system.controller;

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
import com.basic.system.bean.ExcelExportTmpIBean;
import com.basic.system.model.ExcelExportTmp;
import com.basic.system.service.ExcelExportTmpService;

/**
 * 
 *
 * @date 2017年7月6日 上午10:17:35
 * 
 * @Description: Excel导出模板
 *
 */
@Controller
@RequestMapping(value = ExcelExportTmpController.REQUEST_PATH)
public class ExcelExportTmpController extends BaseController{

	protected static final String REQUEST_PATH = "/system";
	
	private static Logger logger = LoggerFactory.getLogger(ExcelExportTmpController.class);
	
	private static final JsonUtils JSON_UTILS = JsonUtils.getInstance();
	
	@Autowired
	private ExcelExportTmpService excelExportTmpService;

	/**
	 * 
	 * @date 2017年7月6日 上午10:23:59
	 * 
	 * @Description: Excel导出模板主页面
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 *
	 */
	@RequestMapping(value = "/menuPage/excelExportTmp")
	public ModelAndView intoPage(HttpServletRequest request,HttpServletResponse response, Model model) {
		
		if(logger.isDebugEnabled()){
			logger.debug("请求参数  intoPage getParameterMap:{}", JSON_UTILS.objectToJson(request.getParameterMap()));
		}
		
		this.doBeforeMenuPageAction(request, response, model, null);
		
		//业务逻辑开始
		ModelAndView pageView = new ModelAndView("/system/excelExportTmp/excelExportTmp");
		
		//业务逻辑结束
		this.doAfterMenuPageAction(request, response, model, null);
		
		return pageView;
	}
	
	/**
	 * 
	 * @date 2017年7月6日 上午10:24:07
	 * 
	 * @Description: Excel导出模板查询
	 * @param ibean
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 *
	 */
	@RequestMapping(value = "/func/excelExportTmp/pagin")
	@ResponseBody
	public String pagin(ExcelExportTmpIBean ibean,HttpServletRequest request,HttpServletResponse response, Model model) {

		if(logger.isDebugEnabled()){
			logger.debug("请求参数  pagin getParameterMap:{}", JSON_UTILS.objectToJson(request.getParameterMap()));
		}
		
		//业务逻辑开始
		this.doBeforeFuncAction(request, response, model, null);
		String json = null;
		
		List<ExcelExportTmp> list = excelExportTmpService.qryExcelExportTmpList(ibean);
		int records = (int) excelExportTmpService.qryExcelExportTmpListCount(ibean);//总记录数
		//Integer offset = ibean.getOffset();
		Integer limit = ibean.getLimit();//每页记录数
		Integer page = ibean.getPage();
		
		int total = records%limit==0? records/limit : records/limit +1;//总页数
		
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
	 * @date 2017年7月6日 上午10:24:17
	 * 
	 * @Description: Excel导出模板新增修改详情页面
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 *
	 */
	@RequestMapping(value = "/funcPage/excelExportTmp/excelExportTmpDetail")
	public ModelAndView detail(HttpServletRequest request,HttpServletResponse response, Model model) {
				
		if(logger.isDebugEnabled()){
			logger.debug("请求参数  detail getParameterMap:{}", JSON_UTILS.objectToJson(request.getParameterMap()));
		}
		
		this.doBeforeFuncPageAction(request, response, model, null);
		
		//业务逻辑开始
		ModelAndView pageView = new ModelAndView("/system/excelExportTmp/excelExportTmpDetail");
		
		//业务逻辑结束
		this.doAfterFuncPageAction(request, response, model, null);
		
		return pageView;
	}
	
	/**
	 * 
	 * @date 2017年7月6日 上午10:24:24
	 * 
	 * @Description: Excel导出模板新增编辑操作
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws Exception
	 *
	 */
	@RequestMapping(value = "/func/excelExportTmp/createOrModify")
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
			ExcelExportTmp ibean = JSON_UTILS.jsonToObject(this.getParameterFromRequest(request, "params"),
					ExcelExportTmp.class);		

			excelExportTmpService.createOrModify(ibean);
			
			dealResult.setReturnCode(DealResult.SUCCESS);
			dealResult.setReturnMsg(null);
			dealResult.setReturnData(null);
		} catch (Exception e) {
			logger.error("Excel导出模板新增编辑操作失败：{}", e);
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
	 * @date 2017年7月6日 上午10:24:32
	 * 
	 * @Description: Excel导出模板删除
	 * @param templateCode
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 *
	 */
	@RequestMapping(value = "/func/excelExportTmp/del")
	@ResponseBody
	public Object del(String templateCode,HttpServletRequest request,HttpServletResponse response, Model model)  {
		
		if(logger.isDebugEnabled()){
			logger.debug("请求参数  del getParameterMap:{}", JSON_UTILS.objectToJson(request.getParameterMap()));
		}
		//业务逻辑开始
		this.doBeforeFuncAction(request, response, model, null);
		
		String json = null;
		DealResult dealResult = new DealResult();
		
		try {
			if(templateCode != null){
				excelExportTmpService.removeByPrimaryKey(templateCode);
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
