package com.basic.system.controller;

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
import com.basic.system.bean.ExcelImportTmpIBean;
import com.basic.system.model.ExcelImportTmp;
import com.basic.system.service.ExcelImportTmpService;

/**
 * 
 *
 * @date 2017年7月6日 上午10:20:32
 * 
 * @Description: Excel上传模板
 *
 */
@Controller
@RequestMapping(value = ExcelImportTmpController.REQUEST_PATH)
public class ExcelImportTmpController extends BaseController{

	protected static final String REQUEST_PATH = "/system";
	
	private static Logger logger = LoggerFactory.getLogger(ExcelImportTmpController.class);
	
	private static final JsonUtils JSON_UTILS = JsonUtils.getInstance();
	
	@Autowired
	private ExcelImportTmpService excelImportTmpService;

	/**
	 * 
	 * @date 2017年7月6日 上午10:24:50
	 * 
	 * @Description: Excel上传模板主页面
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 *
	 */
	@RequestMapping(value = "/menuPage/excelImportTmp")
	public ModelAndView intoPage(HttpServletRequest request,HttpServletResponse response, Model model) {
		
		if(logger.isDebugEnabled()){
			logger.debug("请求参数  intoPage getParameterMap:{}", JSON_UTILS.objectToJson(request.getParameterMap()));
		}
		
		this.doBeforeMenuPageAction(request, response, model, null);
		
		//业务逻辑开始
		ModelAndView pageView = new ModelAndView("/system/excelImportTmp/excelImportTmp");
		
		//业务逻辑结束
		this.doAfterMenuPageAction(request, response, model, null);
		
		return pageView;
	}
	
	/**
	 * 
	 * @date 2017年7月6日 上午10:24:58
	 * 
	 * @Description: Excel上传模板查询
	 * @param ibean
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 *
	 */
	@RequestMapping(value = "/func/excelImportTmp/pagin")
	@ResponseBody
	public String pagin(ExcelImportTmpIBean ibean,HttpServletRequest request,HttpServletResponse response, Model model) {

		if(logger.isDebugEnabled()){
			logger.debug("请求参数  pagin getParameterMap:{}", JSON_UTILS.objectToJson(request.getParameterMap()));
		}
		
		//业务逻辑开始
		this.doBeforeFuncAction(request, response, model, null);
		String json = null;
		
		List<ExcelImportTmp> list = excelImportTmpService.qryExcelImportTmpList(ibean);
		int records = (int) excelImportTmpService.qryExcelImportTmpListCount(ibean);//总记录数
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
	 * @date 2017年7月6日 上午10:25:06
	 * 
	 * @Description: Excel上传模板新增修改详情页面
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 *
	 */
	@RequestMapping(value = "/funcPage/excelImportTmp/excelImportTmpDetail")
	public ModelAndView detail(HttpServletRequest request,HttpServletResponse response, Model model) {
				
		if(logger.isDebugEnabled()){
			logger.debug("请求参数  detail getParameterMap:{}", JSON_UTILS.objectToJson(request.getParameterMap()));
		}
		
		this.doBeforeFuncPageAction(request, response, model, null);
		
		//业务逻辑开始
		ModelAndView pageView = new ModelAndView("/system/excelImportTmp/excelImportTmpDetail");
		
		//业务逻辑结束
		this.doAfterFuncPageAction(request, response, model, null);
		
		return pageView;
	}
	
	/**
	 * 
	 * @date 2017年7月6日 上午10:25:13
	 * 
	 * @Description: Excel上传模板新增编辑操作
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws Exception
	 *
	 */
	@RequestMapping(value = "/func/excelImportTmp/createOrModify")
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
		ExcelImportTmp ibean = JSON_UTILS.jsonToObject(this.getParameterFromRequest(request, "params"),
				ExcelImportTmp.class);		

			excelImportTmpService.createOrModify(ibean);
			
			dealResult.setReturnCode(DealResult.SUCCESS);
			dealResult.setReturnMsg(null);
			dealResult.setReturnData(null);
		} catch (Exception e) {
			logger.error("Excel上传模板新增编辑操作失败：{}", e);
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
	 * @date 2017年7月6日 上午10:25:21
	 * 
	 * @Description: Excel上传模板删除
	 * @param templateCode
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 *
	 */
	@RequestMapping(value = "/func/excelImportTmp/del")
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
				excelImportTmpService.removeByPrimaryKey(templateCode);
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
	
	@RequestMapping(value = "/func/excelImportTmp/qryByTemplateCode")
	@ResponseBody
	public String qryByTemplateCode(String templateCode,HttpServletRequest request,HttpServletResponse response, Model model){
		
		if(logger.isDebugEnabled()){
			logger.debug("请求参数  qryByTemplateCode templateCode:{}", templateCode);
		}
		
		this.doBeforeFuncPageAction(request, response, model, null);
		String json = null;
		DealResult dealResult = new DealResult();
		
		ExcelImportTmp excelImportTmp = null;
		if(StringUtils.isNotBlank(templateCode)){
			excelImportTmp = excelImportTmpService.qryByPrimaryKey(templateCode);
			
			dealResult.setReturnCode(DealResult.QRYSUCCESS);
			dealResult.setReturnMsg(null);
			dealResult.setReturnData(excelImportTmp);
		}
		json = JSON_UTILS.objectToJson(dealResult);
		//业务逻辑结束
		this.doAfterFuncPageAction(request, response, model, null);
		
		return json;
	}
	
}
