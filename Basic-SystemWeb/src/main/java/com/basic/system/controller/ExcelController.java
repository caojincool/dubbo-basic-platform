package com.basic.system.controller;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.basic.framework.web.BaseController;
import com.basic.system.model.FileConfig;
import com.basic.system.model.FileInfo;
import com.basic.system.service.FileConfigService;
import com.basic.system.service.FileInfoService;

import com.basic.framework.common.model.FTPConfig;
import com.basic.framework.common.utils.datatype.JsonUtils;
import com.basic.framework.common.utils.ftp.FTPClientUtils;

@Controller
public class ExcelController extends BaseController {

	private static Logger logger = LoggerFactory.getLogger(ExcelController.class);

	private static final JsonUtils JSON_UTILS = JsonUtils.getInstance();
	
	private static final String FILE_CHARATER = "UTF-8";
	
	@Autowired
	private FileInfoService fileInfoService;
	@Autowired
	private FileConfigService fileConfigService;
	
	@Autowired
	private ExcelImport excelImport;

	@RequestMapping(value = "/excel/importExcel")
	@ResponseBody
	public String importExcel(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {

		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<String> fileIds = new ArrayList<String>();
		
		DiskFileItemFactory diskFactory = new DiskFileItemFactory();
		// threshold 极限、临界值，即硬盘缓存 1M
		diskFactory.setSizeThreshold(4 * 1024);

		ServletFileUpload upload = new ServletFileUpload(diskFactory);
		// 设置允许上传的最大文件大小（单位MB）
		upload.setSizeMax(1024 * 1048576);
		upload.setHeaderEncoding(FILE_CHARATER);

		try {
			List<FileItem> fileList = upload.parseRequest(request);
			//fileList.get(0).getFieldName()//fileinput插件额外提交参数要这样拿貌似
			//fileList.get(0).getString()//fileinput插件额外提交参数要这样拿貌似
			String moduleCode = getParameterFromRequest(request, "moduleCode");//模块编码
			String templateCode = getParameterFromRequest(request, "templateCode");//模板编码
			String busiBeanName = getParameterFromRequest(request, "busiBeanName");//服务名称
			String otherParams = getParameterFromRequest(request, "otherParams");//其他参数
			
			if(StringUtils.isBlank(moduleCode) || "undefined".equals(moduleCode)){
				throw new Exception("模块编码出错");
			}else{
				FileConfig fileConfig = fileConfigService.qryByModuleCode(moduleCode);
				if(fileConfig == null){
					throw new Exception("模块编码出错");
				}
			}
			Iterator<FileItem> it = fileList.iterator();
//			FileInfo fi = null;
			FileItem item = null;
			String fileName = null;
			InputStream input = null;
			
			while (it.hasNext()) {

				item = it.next();
				fileName = item.getName();
				input = item.getInputStream();

				// 处理上传文件内容
				if (!item.isFormField()) {
					try {
						//不能重复读取，把流缓存起来
						ByteArrayOutputStream baos = new ByteArrayOutputStream();  
						byte[] buffer = new byte[1024];  
						int len;  
						while ((len = input.read(buffer)) > -1 ) {  
							baos.write(buffer, 0, len);  
						}  
						baos.flush();
						
						InputStream stream1 = new ByteArrayInputStream(baos.toByteArray());  
						/*fi = fileService.uploadFile(input, fileName,item.getContentType(),
								InetAddress.getLocalHost().getHostAddress());*/
						
						
						//TODO 暂时作废
						/*fi = fileService.uploadFile(stream1, fileName,item.getContentType(),
								moduleCode);*/
						//上传到FTP
						FileConfig fileConfig = fileConfigService.qryByModuleCode(moduleCode);
						FTPConfig ftpConfig = new FTPConfig(fileConfig.getIp(),fileConfig.getUsername(),
								fileConfig.getPassword(),fileConfig.getStoreDir(),fileConfig.getType());
						String fileExdSplit = ".";//文件扩展名分割符号
						//用clientIp+时间防止重名
						int fileSufixIdx = fileName.lastIndexOf(fileExdSplit);
						String targetFileName = moduleCode + "_" + System.currentTimeMillis() 
											  + (fileSufixIdx >= 0 ? fileName.substring(fileSufixIdx) : "");
						FTPClientUtils.uploadFile(stream1, targetFileName, ftpConfig);
						//写文件实例表
						FileInfo fileInfo = new FileInfo();
						fileInfo.setFileConfigId(fileConfig.getFileConfigId());
						fileInfo.setSrcFileName(fileName);
						fileInfo.setTagetFileName(targetFileName);
//						fileInfo.setFileType(item.getContentType());
						Long fileInfoId = fileInfoService.createSelective(fileInfo);
						
						fileIds.add(fileInfoId.toString());
						logger.info(fileName + "文件上传成功！");
						
						
						if (input != null) {
							input.close();
						}
						
						//解析Excel
						InputStream stream2 = new ByteArrayInputStream(baos.toByteArray());  
						
						
						//TODO 暂时作废
						//Object resultList = excelReadService.readExcel(templateCode, busiBeanName, stream2);
						Object resultList = excelImport.readExcel(templateCode, busiBeanName, otherParams, stream2);
						
						
						//业务方法为空，返回数据给页面
						//if(StringUtils.isBlank(busiBeanName)){
							resultMap.put("resultList", resultList);//返回数据List<List<Object>>
						//}
						
						
					} catch (Exception e) {
						logger.error(fileName + "文件上传失败！", e); //有一个失败，继续下一个
						resultMap.put("error", e.toString());
					}
				}

			}
			resultMap.put("successCount",fileIds.size());//成功的数量
			String idsStr = fileIds.toString();
			resultMap.put("fileIds",idsStr.subSequence(1, idsStr.length()-1));//上传成功返回的ID,多个文件则有多个ids
			
			logger.info("全部文件上传完成！");

		} catch (Exception ex) {
			logger.error("文件上传失败：{}", ex);
			resultMap.put("error", ex.toString());
		}
		
		if (logger.isDebugEnabled()) {
			logger.debug("文件上传返回结果：{}", JSON_UTILS.objectToJson(resultMap));
		}

		return JSON_UTILS.objectToJson(resultMap);
	}

	/**
	 * 打开导入Excel的模态窗口
	 * @date 2016年9月1日 下午7:01:01
	 * @author lzj
	 * @Description: 打开导入Excel的模态窗口
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 *
	 */
	@RequestMapping(value = "/excel/excelImportModelWin")
	public ModelAndView demoModalWin(HttpServletRequest request,HttpServletResponse response, Model model) {
				
		if(logger.isDebugEnabled()){
			logger.debug("请求参数  excelImportModelWin getParameterMap:{}", JSON_UTILS.objectToJson(request.getParameterMap()));
		}
		
		this.doBeforeFuncPageAction(request, response, model, null);
		
		//业务逻辑开始
		ModelAndView pageView = new ModelAndView("/public/excel/excelModelWin");
		
		//业务逻辑结束
		this.doAfterFuncPageAction(request, response, model, null);
		
		return pageView;
	}

}
