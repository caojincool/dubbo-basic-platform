package com.basic.framework.demo.controller;

import java.io.BufferedOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.basic.framework.web.BaseController;
import com.basic.system.controller.ExcelExport;
import com.basic.framework.common.utils.datatype.JsonUtils;
import com.basic.framework.demo.model.DemoExcelRead1;
import com.basic.framework.demo.model.DemoExcelRead2;

/**
 * 
 *
 * @date 2016年8月10日 下午7:07:52
 * @author 杰
 * @Description: 导入导出excel的使用
 *
 */
@Controller
@RequestMapping(value = DemoExcelController.REQUEST_PATH)
public class DemoExcelController extends BaseController {

	protected static final String REQUEST_PATH = "/demo";

	private static Logger logger = LoggerFactory.getLogger(DemoExcelController.class);
	
	private static final JsonUtils JSON_UTILS = JsonUtils.getInstance();

	@Autowired
	private ExcelExport excelExport;
	
	/**
	 * 
	 * @date 2016年9月5日 下午4:58:58
	 * @author 杰
	 * @Description: 导入导出excel Demo主页面
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 *
	 */
	@RequestMapping(value = "/menuPage/demoExcel")
	public ModelAndView demoGridTree(HttpServletRequest request, HttpServletResponse response, Model model) {

		if (logger.isDebugEnabled()) {
			logger.debug("请求参数  demoGridTree getParameterMap:{}",
					JSON_UTILS.objectToJson(request.getParameterMap()));
		}

		this.doBeforeMenuPageAction(request, response, model, null);

		// 业务逻辑开始
		ModelAndView pageView = new ModelAndView("/demo/demoExcel");

		// 业务逻辑结束
		this.doAfterMenuPageAction(request, response, model, null);

		return pageView;
	}


    /**
     * 
     * @date 2017年1月22日 上午11:07:04
     * @author 杰
     * @Description: 导出Excel
     * @param request
     * @param response
     * @param model
     * @return
     * @throws Exception
     *
     */
	@RequestMapping(value = "/func/demoExcel/exportExcel")
	@ResponseBody
	public String exportExcel(String templateCode,HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		
		if (logger.isDebugEnabled()) {
			logger.debug("请求参数  exportExcel getParameterMap:{}",
					JSON_UTILS.objectToJson(request.getParameterMap()));
		}
		
		this.doBeforeFuncAction(request, response, model, null);
		String json = null;
		
		request.setCharacterEncoding("UTF-8");
		
		String fileName = "导出Excel_DEMO.xlsx";
		response.setContentType("text/html;charset=utf-8");  
		response.setContentType("application/x-msdownload");//excel:application/ms-excel  word:application/ms-word
		response.setHeader("Content-disposition", "attachment; filename="  
				+ new String(fileName.getBytes("utf-8"), "ISO8859-1"));
		
		BufferedOutputStream bos = null;  
		bos = new BufferedOutputStream(response.getOutputStream());

		List<List<Object>> busiBeanLists = getBusiData();
		try{
			if(StringUtils.isNotBlank(templateCode)){
				excelExport.export(bos, templateCode, busiBeanLists);
			}
		}catch(Exception e){
			logger.error("文件导出异常:{}", e);
		}finally{
        	if(bos != null){
        		bos.flush();
        		bos.close();
        	}
        }
		
		// 业务逻辑结束
		if (logger.isDebugEnabled()) {
			logger.debug("exportExcel json:{}",json);
		}
		
		this.doAfterFuncAction(request, response, model, null);
		return json;
	}
	
	private List<List<Object>> getBusiData(){
		List<Object> demoExcelRead1List = new ArrayList<Object>();
		
		DemoExcelRead1 demoExcelRead11 = new DemoExcelRead1();
		demoExcelRead11.setCreateTime(new Date());
		demoExcelRead11.setIndex(1L);
		demoExcelRead11.setProductInstCode("demoExcelRead11");
		demoExcelRead1List.add(demoExcelRead11);
		
		DemoExcelRead1 demoExcelRead12 = new DemoExcelRead1();
		demoExcelRead12.setCreateTime(new Date());
		demoExcelRead12.setIndex(2L);
		demoExcelRead12.setProductInstCode("demoExcelRead12");
		demoExcelRead1List.add(demoExcelRead12);
		
		DemoExcelRead1 demoExcelRead13 = new DemoExcelRead1();
		demoExcelRead13.setCreateTime(new Date());
		demoExcelRead13.setIndex(3L);
		demoExcelRead13.setProductInstCode("demoExcelRead13");
		demoExcelRead1List.add(demoExcelRead13);

		DemoExcelRead1 demoExcelRead14 = new DemoExcelRead1();
		demoExcelRead14.setCreateTime(new Date());
		demoExcelRead14.setIndex(4L);
		demoExcelRead14.setProductInstCode("demoExcelRead14");
		demoExcelRead1List.add(demoExcelRead14);

		DemoExcelRead1 demoExcelRead15 = new DemoExcelRead1();
		demoExcelRead15.setCreateTime(new Date());
		demoExcelRead15.setIndex(5L);
		demoExcelRead15.setProductInstCode("demoExcelRead15");
		demoExcelRead1List.add(demoExcelRead15);

		List<Object> demoExcelRead2List = new ArrayList<Object>();
		
		DemoExcelRead2 demoExcelRead21 = new DemoExcelRead2();
		demoExcelRead21.setCount(1);
		demoExcelRead21.setCreateTime(new Date());
		demoExcelRead21.setIndex(1L);
		demoExcelRead21.setProductInstCode("demoExcelRead21");
		demoExcelRead2List.add(demoExcelRead21);
		
		DemoExcelRead2 demoExcelRead22 = new DemoExcelRead2();
		demoExcelRead22.setCount(2);
		demoExcelRead22.setCreateTime(new Date());
		demoExcelRead22.setIndex(2L);
		demoExcelRead22.setProductInstCode("demoExcelRead22");
		demoExcelRead2List.add(demoExcelRead22);
		
		List<List<Object>> ret = new ArrayList<List<Object>>();
		ret.add(demoExcelRead1List);
		ret.add(demoExcelRead2List);
		
		return ret;
		
	}
	
	@RequestMapping(value = "/func/demoExcel/exportExcelForBigDate")
	@ResponseBody
	public String exportExcelForBigDate(String templateCode,HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		
		if (logger.isDebugEnabled()) {
			logger.debug("请求参数  exportExcel getParameterMap:{}",
					JSON_UTILS.objectToJson(request.getParameterMap()));
		}
		
		this.doBeforeFuncAction(request, response, model, null);
		String json = null;
		
		request.setCharacterEncoding("UTF-8");
		
		String fileName = "导出Excel_DEMO.xlsx";
		response.setContentType("text/html;charset=utf-8");  
		response.setContentType("application/x-msdownload");//excel:application/ms-excel  word:application/ms-word
		response.setHeader("Content-disposition", "attachment; filename="  
				+ new String(fileName.getBytes("utf-8"), "ISO8859-1"));
		
		BufferedOutputStream bos = null;  
		bos = new BufferedOutputStream(response.getOutputStream());

		List<List<Object>> busiBeanLists = getBusiDataForBigDate();
		try{
			if(StringUtils.isNotBlank(templateCode)){
				excelExport.export(bos, templateCode, busiBeanLists);
			}
		}catch(Exception e){
			logger.error("文件导出异常:{}", e);
		}finally{
        	if(bos != null){
        		bos.flush();
        		bos.close();
        	}
        }
		
		// 业务逻辑结束
		if (logger.isDebugEnabled()) {
			logger.debug("exportExcel json:{}",json);
		}
		
		this.doAfterFuncAction(request, response, model, null);
		return json;
	}

	private List<List<Object>> getBusiDataForBigDate(){
		List<Object> demoExcelRead1List = new ArrayList<Object>();
		
		for(long i=0; i< 1000000; i++){
			DemoExcelRead1 demoExcelRead1 = new DemoExcelRead1();
			demoExcelRead1.setCreateTime(new Date());
			demoExcelRead1.setIndex(i);
			demoExcelRead1.setProductInstCode("demoExcelRead1"+":"+i);
			demoExcelRead1List.add(demoExcelRead1);
		}
		

		List<Object> demoExcelRead2List = new ArrayList<Object>();
		
		for(long i=0; i< 10; i++){
			DemoExcelRead2 demoExcelRead2 = new DemoExcelRead2();
			demoExcelRead2.setCount((int) i);
			demoExcelRead2.setCreateTime(new Date());
			demoExcelRead2.setIndex(i);
			demoExcelRead2.setProductInstCode("demoExcelRead2"+":"+i);
			demoExcelRead2List.add(demoExcelRead2);
		}
		
		List<List<Object>> ret = new ArrayList<List<Object>>();
		ret.add(demoExcelRead1List);
		ret.add(demoExcelRead2List);
		
		return ret;
		
	}
	
}
