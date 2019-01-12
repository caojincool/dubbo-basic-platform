package com.basic.system.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

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
import com.basic.system.bean.ShowImageIbean;
import com.basic.system.model.FileConfig;
import com.basic.system.model.FileInfo;
import com.basic.system.model.FileInst;
import com.basic.system.service.FileConfigService;
import com.basic.system.service.FileInfoService;
import com.basic.framework.common.model.FTPConfig;
import com.basic.framework.common.utils.ImageUtils;
import com.basic.framework.common.utils.datatype.JsonUtils;
import com.basic.framework.common.utils.datatype.LongUtils;
import com.basic.framework.common.utils.ftp.FTPClientUtils;

@Controller
public class FileController extends BaseController {

	private static Logger logger = LoggerFactory.getLogger(FileController.class);

	private static final JsonUtils JSON_UTILS = JsonUtils.getInstance();
	
	private static final String FILE_CHARATER = "UTF-8";
//	private static final String IMG_CHARATER = "UTF-8";
	private static final String IS_RATIO_TRUE = "true";
//	private static final String IS_RATIO_FALSE = "false";
	
	@Autowired
	private FileInfoService fileInfoService;
	@Autowired
	private FileConfigService fileConfigService;

	/**
	 * 
	 * @date 2017年4月25日 下午5:21:38
	 * 
	 * @Description: 上传文件
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws Exception
	 *
	 */
	@RequestMapping(value = "/file/uploadFile")
	@ResponseBody
	public String uploadFile(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
        
		response.setHeader("contentType","application/json;charset=UTF-8");
		response.setContentType("application/json;charset=UTF-8");
      
		// "*"表明允许任何地址的跨域调用，正式部署时应替换为正式地址
//		((javax.servlet.http.HttpServletResponse) response).addHeader("Access-Control-Allow-Origin", "*"); 
		//((javax.servlet.http.HttpServletResponse) response).addHeader("Access-Control-Allow-Credentials", "true"); 
		//((javax.servlet.http.HttpServletResponse) response).addHeader("Access-Control-Allow-Methods", "GET, POST, OPTIONS"); 
		
		
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<String> fileIds = new ArrayList<String>();
		List<FileInfo> fileInfos = new ArrayList<FileInfo>();
		
		DiskFileItemFactory diskFactory = new DiskFileItemFactory();
		// threshold 极限、临界值，即硬盘缓存 1M
		diskFactory.setSizeThreshold(4 * 1024);

		ServletFileUpload upload = new ServletFileUpload(diskFactory);
		// 设置允许上传的最大文件大小（单位MB）
		upload.setSizeMax(1024 * 1048576);
		upload.setHeaderEncoding(FILE_CHARATER);

		try {
			List<FileItem> fileList = upload.parseRequest(request);
			String moduleCode = getParameterFromRequest(request, "moduleCode");
			if(StringUtils.isBlank(moduleCode) || "undefined".equals(moduleCode)){
				throw new Exception("模块编码为空");
			}else{
				FileConfig fileConfig = fileConfigService.qryByModuleCode(moduleCode);
				if(fileConfig == null){
					throw new Exception("系统找不到对应的模块编码");
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
					
					ByteArrayOutputStream baos = new ByteArrayOutputStream();  
					byte[] buffer = new byte[1024];  
					int len;  
					while ((len = input.read(buffer)) > -1 ) {  
						baos.write(buffer, 0, len);  
					}  
					baos.flush();
					
					InputStream stream1 = new ByteArrayInputStream(baos.toByteArray());  
					
					//TODO 暂时作废
					/*fi = fileService.uploadFile(input, fileName,item.getContentType(),
								moduleCode);*/
					//上传到FTP
					FileConfig fileConfig = fileConfigService.qryByModuleCode(moduleCode);
					FTPConfig ftpConfig = new FTPConfig(fileConfig.getIp(),fileConfig.getUsername(),
							fileConfig.getPassword(),fileConfig.getStoreDir(),fileConfig.getType());
					String fileExdSplit = ".";//文件扩展名分割符号
					//用clientIp+时间防止重名
					int fileSufixIdx = fileName.lastIndexOf(fileExdSplit);
					String uuid = UUID.randomUUID().toString().replaceAll("-", "");
					String targetFileName = moduleCode + "_" + uuid 
					+ (fileSufixIdx >= 0 ? fileName.substring(fileSufixIdx) : "");
					FTPClientUtils.uploadFile(stream1, targetFileName, ftpConfig);
					
					//写文件实例表
					FileInfo fileInfo = new FileInfo();
					fileInfo.setFileConfigId(fileConfig.getFileConfigId());
					fileInfo.setSrcFileName(fileName);
					fileInfo.setTagetFileName(targetFileName);
//						fileInfo.setFileType(item.getContentType());
					Long fileInfoId = fileInfoService.createSelective(fileInfo);
					
					fileInfo = fileInfoService.qryByPrimaryKey(fileInfoId);
					if (input != null) {
						input.close();
					}
					fileIds.add(fileInfoId.toString());
					fileInfos.add(fileInfo);
					logger.info(fileName + "文件上传成功！");
				}

			}
			resultMap.put("successCount",fileIds.size());//成功的数量
			String idsStr = fileIds.toString();
			resultMap.put("fileIds",idsStr.subSequence(1, idsStr.length()-1));//上传成功返回的ID,多个文件则有多个ids
			resultMap.put("fileInfos",fileInfos);//上传成功返回的文件信息,多个文件则有多个文件信息
			
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
	 * 
	 * @date 2017年4月25日 下午5:21:54
	 * 
	 * @Description: 下载文件，把文件写到指定的流中
	 * @param request
	 * @param response
	 * @param model
	 *
	 */
	@RequestMapping(value = "/file/getFile")
	public void getFile(HttpServletRequest request, HttpServletResponse response, Model model) {

		OutputStream os = null;
		try {
			
			// 参数序列化
			Long fileInfoId = LongUtils.valueOf(this.getParameterFromRequest(request, "fileInfoId"));
			if (logger.isDebugEnabled()) {
				logger.debug("文件读取传入参数:{}", fileInfoId);
			}
			
			os = response.getOutputStream();
			
			FileInst finst = new FileInst();
			finst.setFileInfoId(fileInfoId);
			finst.setOutputStream(os);
			
			//TODO 改成本地下载，不通过dubbo
			//fileService.downloadFile(finst);		
			FileInfo fileInfo = fileInfoService.qryByPrimaryKey(fileInfoId);
			FileConfig fileConfig = fileConfigService.qryByPrimaryKey(fileInfo.getFileConfigId());
			FTPConfig ftpConfig = new FTPConfig(fileConfig.getIp(), fileConfig.getUsername(), fileConfig.getPassword(),
					fileConfig.getStoreDir(),fileConfig.getType());
			finst.setFileName(fileInfo.getSrcFileName());
//			finst.setFileType(fileInfo.getFileType());
			FTPClientUtils.downloadFile(finst.getOutputStream(), fileInfo.getTagetFileName(), ftpConfig);
			
			if (logger.isDebugEnabled()) {
				logger.debug("成功读取文件:{}", finst.getFileName());
			}
			
			response.setContentType(finst.getFileType());
			os.flush();
			os.close();
			
		} catch (Exception e) {
			logger.error("文件读取异常,{}", e);
		}		
	}
	
	/**
	 * 
	 * @date 2017年4月25日 下午5:23:01
	 * 
	 * @Description: 下载文件，把文件写到指定的流中
	 * @param request
	 * @param response
	 * @param model
	 * @throws Exception
	 *
	 */
    @RequestMapping("/file/downloadFile")  
    public void download(HttpServletRequest request, HttpServletResponse response, Model model)
            throws Exception {  

    		request.setCharacterEncoding("UTF-8");  
  
        	// 参数序列化
			Long fileInfoId = LongUtils.valueOf(this.getParameterFromRequest(request, "fileInfoId"));
			FileInfo fileInfo = fileInfoService.qryByPrimaryKey(fileInfoId);
			String fileName = fileInfo.getSrcFileName();
	        response.setContentType("text/html;charset=utf-8");  
	        response.setContentType("application/x-msdownload;");  
	        String userAgent = request.getHeader("user-agent").toLowerCase();
	        if (userAgent.contains("msie") || userAgent.contains("like gecko")) {//IE
	        	fileName = URLEncoder.encode(fileName, "UTF-8");  
	        } else {  
	        	fileName = new String(fileName.getBytes("UTF-8"), "ISO8859-1");  
	        }  
	        response.setHeader("Content-disposition", "attachment; filename="+fileName);  
	        BufferedInputStream bis = null;  
	        BufferedOutputStream bos = null;  
	        InputStream inputStream = null;  
	  
	        try {  
	        	
	        	//TODO 暂时作废
	        	//inputStream = fileService.downloadToIS(fileInfoId);
	    		// 获取文件存储的信息
	    		//FileInfo fileInfo = fileService.qryByFileInfoId(fileInfoId);
	    		FileConfig fileConfig = fileConfigService.qryByPrimaryKey(fileInfo.getFileConfigId());
	    		FTPConfig ftpConfig = new FTPConfig(fileConfig.getIp(), fileConfig.getUsername(), fileConfig.getPassword(),
	    				fileConfig.getStoreDir(),fileConfig.getType());
	    		inputStream = FTPClientUtils.downloadFile(fileInfo.getTagetFileName(), ftpConfig);
	    		
	            bis = new BufferedInputStream(inputStream);  
	            bos = new BufferedOutputStream(response.getOutputStream());  
	            byte[] buff = new byte[2048];  
	            int bytesRead;  
	            while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {  
	                bos.write(buff, 0, bytesRead);  
	            }  
	        } catch (Exception e) {  
	        	logger.error("文件下载异常:{}", e);
	        } finally {  
	            if (bis != null) {
                    bis.close();
                }
	            if (bos != null) {
                    bos.close();
                }
	        }  
	    }  
    
    /**
     * 
     * @date 2017年4月25日 下午5:24:02
     * 
     * @Description: 根据文件id查询得到一条数据
     * @param request
     * @param response
     * @param model
     * @return
     *
     */
    @RequestMapping("/file/getFileInfo")  
    @ResponseBody
    public String getFileInfo(HttpServletRequest request, HttpServletResponse response, Model model){
    	
    	String resultStr="";
    	try {  
	    	// 参数序列化
	    	Long fileInfoId = LongUtils.valueOf(this.getParameterFromRequest(request, "fileInfoId"));
	    	FileInfo fileInfo = fileInfoService.qryByPrimaryKey(fileInfoId);
	    	
	    	resultStr = JSON_UTILS.objectToJson(fileInfo);
    		
    	} catch (Exception e) {  
    		logger.error("读取文件信息异常:{}", e);
    	}  
    	return resultStr;
    }  
    
//    @RequestMapping("/file/qryFileInfosByFileInfoIds")  
//    @ResponseBody
//    public String qryFileInfosByFileInfoIds(String fileInfoIds,HttpServletRequest request, HttpServletResponse response, Model model){
//    	if(logger.isDebugEnabled()){
//			logger.debug("FileController.qryFileInfosByFileInfoIds:{}", JsonUtils.toJsonStringNoException(fileInfoIds));
//		}
//    	String json=null;
//    	try {  
//	    	List<FileInfo> fileInfos = fileInfoService.qryFileInfosByFileInfoIds(fileInfoIds);
//	    	json = JsonUtils.toJsonString(fileInfos);
//    	} catch (Exception e) {  
//    		logger.error("获取文件信息异常:{}", e);
//    	}  
//    	return json;
//    	
//    	
//    }  
	
    /**
     * 
     * @date 2016年4月7日 上午11:01:26
     * @author 杰
     * @Description: 打开上传图片的窗口
     * @param request
     * @param response
     * @param model
     * @return
     *
     */
	@RequestMapping(value = "/file/fileModelWin")
	public ModelAndView demoModalWin(HttpServletRequest request,HttpServletResponse response, Model model) {
				
		if(logger.isDebugEnabled()){
			logger.debug("请求参数  demoOpenWin getParameterMap:{}", JSON_UTILS.objectToJson(request.getParameterMap()));
		}
		
		this.doBeforeFuncPageAction(request, response, model, null);
		
		//业务逻辑开始
		ModelAndView pageView = new ModelAndView("/public/file/fileModelWin");
		
		//业务逻辑结束
		this.doAfterFuncPageAction(request, response, model, null);
		
		return pageView;
	}
	
	/**
	 * 
	 * @date 2017年6月26日 上午11:35:06
	 * 
	 * @Description: 传入tif格式的base64，转换成jpg格式的base64
	 * @param base64ImageStr
	 * @param request
	 * @param response
	 * @param model
	 *
	 */
/*	@RequestMapping(value = "/file/tifTOJpg")
	@ResponseBody
	public String tifTOJpg(String base64ImageStr, HttpServletRequest request, HttpServletResponse response, Model model) {

		String json = null;
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		Base64 base64 = new Base64();
		byte[] bs = null;
		try {
			bs = base64.decode(base64ImageStr.getBytes(IMG_CHARATER));
		} catch (UnsupportedEncodingException e1) {
			logger.error("转换失败:{}", e1);
		}
		InputStream inputStream = null;
		inputStream = new ByteArrayInputStream(bs);
		String sourcePrefixName = System.currentTimeMillis()+"source";//前缀名
		String targetPrefixName = System.currentTimeMillis()+"target";//前缀名
		String suffixName = "tif";//后缀名
		File sourceFile = null;
		File targetFile = null;
		try {
			sourceFile = File.createTempFile(sourcePrefixName, suffixName, null);
			FTPClientUtils.inputStreamToFile(inputStream, sourceFile);
			sourceFile.deleteOnExit();
			String sourceFilePath = sourceFile.getPath();
			
			targetFile = File.createTempFile(targetPrefixName, suffixName, null);
			targetFile.deleteOnExit();
			String targetFilePath = targetFile.getPath();
			
	         tif转换到jpg格式   
	        RenderedOp src2 = JAI.create("fileload", sourceFilePath);  
	        OutputStream os2 = new FileOutputStream(targetFilePath);  
	        JPEGEncodeParam param2 = new JPEGEncodeParam();  
	        //指定格式类型，jpg 属于 JPEG 类型  
	        ImageEncoder enc2 = ImageCodec.createImageEncoder("JPEG", os2, param2);  
	        enc2.encode(src2);  
	        os2.close();  
			
	        InputStream in = null;
	        byte[] imgBs = null;
            in = new FileInputStream(targetFile);
    		if(in != null){
    			imgBs = new byte[in.available()];
    			in.read(imgBs);
    		}
    		
    		if(null != in){
    			in.close();
			}
    		
    		byte[] targetbs = base64.encode(imgBs);
    		String targetBase64Str = new String(targetbs,IMG_CHARATER);
    		resultMap.put("targetBase64Str", targetBase64Str);
            json = JsonUtils.toJsonStringNoException(targetBase64Str);
		} catch (IOException e1) {
//			e1.printStackTrace();
			logger.error("生成临时文件出错 :{}", e1);
		}

		return json;
	}*/
	
	/**
	 * 
	 * @date 2017年11月7日 下午5:49:12
	 * 
	 * @Description: 上传文件到本地
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws Exception
	 *
	 */
/*	@RequestMapping(value = "/file/uploadFileForLocal")
	@ResponseBody
	public String uploadFileForLocal(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
        
		response.setHeader("contentType","application/json;charset=UTF-8");
		response.setContentType("application/json;charset=UTF-8");
      
		// "*"表明允许任何地址的跨域调用，正式部署时应替换为正式地址
		((javax.servlet.http.HttpServletResponse) response).addHeader("Access-Control-Allow-Origin", "*"); 
		//((javax.servlet.http.HttpServletResponse) response).addHeader("Access-Control-Allow-Credentials", "true"); 
		//((javax.servlet.http.HttpServletResponse) response).addHeader("Access-Control-Allow-Methods", "GET, POST, OPTIONS"); 
		
		
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<String> fileIds = new ArrayList<String>();
		List<FileInfo> fileInfos = new ArrayList<FileInfo>();
		
		DiskFileItemFactory diskFactory = new DiskFileItemFactory();
		// threshold 极限、临界值，即硬盘缓存 1M
		diskFactory.setSizeThreshold(4 * 1024);

		ServletFileUpload upload = new ServletFileUpload(diskFactory);
		// 设置允许上传的最大文件大小（单位MB）
		upload.setSizeMax(1024 * 1048576);
		upload.setHeaderEncoding(FILE_CHARATER);

		try {
			List<FileItem> fileList = upload.parseRequest(request);
			String moduleCode = getParameterFromRequest(request, "moduleCode");
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
						ByteArrayOutputStream baos = new ByteArrayOutputStream();  
						byte[] buffer = new byte[1024];  
						int len;  
						while ((len = input.read(buffer)) > -1 ) {  
							baos.write(buffer, 0, len);  
						}  
						baos.flush();
						
						InputStream stream1 = new ByteArrayInputStream(baos.toByteArray());  
						
						FileConfig fileConfig = fileConfigService.qryByModuleCode(moduleCode);
//						FTPConfig ftpConfig = new FTPConfig(fileConfig.getIp(),fileConfig.getUsername(),
//								fileConfig.getPassword(),fileConfig.getStoreDir());
						String fileExdSplit = ".";//文件扩展名分割符号
						//用clientIp+时间防止重名
						int fileSufixIdx = fileName.lastIndexOf(fileExdSplit);
						//UUID.randomUUID().toString().replaceAll("-", "");
						String targetFileName = moduleCode + "_" + System.currentTimeMillis() 
						+ (fileSufixIdx >= 0 ? fileName.substring(fileSufixIdx) : "");
						
//						String descFileName = fileConfig.getStoreDir() + "/" + targetFileName;
						String descDirName = fileConfig.getStoreDir();
						FileUtils.createDirectory(descDirName);//创建目录
						//FileUtils.createFile(descFileName);

						OutputStream os = null;
						try {
							// 1K的数据缓冲
				            byte[] bs = new byte[1024];
				            // 读取到的数据长度
				            int length;
							os = new FileOutputStream(descDirName + File.separator + targetFileName);
				            // 开始读取
				            while ((length = stream1.read(bs)) != -1) {
				                os.write(bs, 0, length);
				            }
						} catch (IOException e) {
							e.printStackTrace();
						} catch (Exception e) {
							e.printStackTrace();
						} finally {
							// 完毕，关闭所有链接
							try {
								os.close();
								stream1.close();
							} catch (IOException e) {
								e.printStackTrace();
							}
						}
				        
						//写文件实例表
						FileInfo fileInfo = new FileInfo();
						fileInfo.setFileConfigId(fileConfig.getFileConfigId());
						fileInfo.setSrcFileName(fileName);
						fileInfo.setTagetFileName(targetFileName);
//						fileInfo.setFileType(item.getContentType());
						Long fileInfoId = fileInfoService.createSelective(fileInfo);
						
						fileInfo = fileInfoService.qryByPrimaryKey(fileInfoId);
						if (input != null) {
							input.close();
						}
						fileIds.add(fileInfoId.toString());
						fileInfos.add(fileInfo);
						logger.info(fileName + "文件上传成功！");
						
					} catch (Exception e) {
						logger.error(fileName + "文件上传失败！", e); //有一个失败，继续下一个
						resultMap.put("error:{}", e.toString());
					}
				}

			}
			resultMap.put("successCount",fileIds.size());//成功的数量
			String idsStr = fileIds.toString();
			resultMap.put("fileIds",idsStr.subSequence(1, idsStr.length()-1));//上传成功返回的ID,多个文件则有多个ids
			resultMap.put("fileInfos",fileInfos);//上传成功返回的文件信息,多个文件则有多个文件信息
			
			logger.info("全部文件上传完成！");

		} catch (Exception ex) {
			logger.error("文件上传失败：{}", ex);
			resultMap.put("error", ex.toString());
		}
		
		if (logger.isDebugEnabled()) {
			logger.debug("文件上传返回结果：{}", JSON_UTILS.objectToJson(resultMap));
		}

		return JSON_UTILS.objectToJson(resultMap);
	}*/

	/**
	 * 
	 * @date 2017年4月25日 下午5:21:54
	 * 
	 * @Description: 从本地下载文件，把文件写到指定的流中
	 * @param request
	 * @param response
	 * @param model
	 *
	 */
/*	@RequestMapping(value = "/file/getFileForLocal")
	public void getFileForLocal(HttpServletRequest request, HttpServletResponse response, Model model) {

		OutputStream os = null;
		try {
			
			// 参数序列化
			Long fileInfoId = LongUtils.valueOf(this.getParameterFromRequest(request, "fileInfoId"));
			if (logger.isDebugEnabled()) {
				logger.debug("文件读取传入参数:{}", fileInfoId);
			}
			
			os = response.getOutputStream();// 得到向客户端输出二进制数据的对象
			
			FileInst finst = new FileInst();
			finst.setFileInfoId(fileInfoId);
			finst.setOutputStream(os);
			
			FileInfo fileInfo = fileInfoService.qryByPrimaryKey(fileInfoId);
			FileConfig fileConfig = fileConfigService.qryByPrimaryKey(fileInfo.getFileConfigId());
//			FTPConfig ftpConfig = new FTPConfig(fileConfig.getIp(), fileConfig.getUsername(), fileConfig.getPassword(),
//					fileConfig.getStoreDir());
			finst.setFileName(fileInfo.getSrcFileName());
//			finst.setFileType(fileInfo.getFileType());
//			FTPClientUtils.downloadFile(finst.getOutputStream(), fileInfo.getTagetFileName(), ftpConfig);
			
			FileInputStream fileIs = null;
			fileIs = new FileInputStream(fileConfig.getStoreDir() + File.separator + fileInfo.getTagetFileName());
			int i = fileIs.available(); // 得到文件大小
			byte data[] = new byte[i];
			fileIs.read(data); // 读数据
			os.write(data); // 输出数据
			os.flush();
			os.close();
			fileIs.close();
			
			
			if (logger.isDebugEnabled()) {
				logger.debug("成功读取文件:{}", finst.getFileName());
			}
			
			response.setContentType(finst.getFileType());
			
		} catch (Exception e) {
			logger.error("文件读取异常,{}", e);
		}		
	}*/
	
	/**
	 * 
	 * @date 2017年4月25日 下午5:23:01
	 * 
	 * @Description: 从本地下载文件，把文件写到指定的流中
	 * @param request
	 * @param response
	 * @param model
	 * @throws Exception
	 *
	 */
/*    @RequestMapping("/file/downloadFileForLocal")  
    public void downloadForLocal(HttpServletRequest request, HttpServletResponse response, Model model)
            throws Exception {  

    		request.setCharacterEncoding("UTF-8");  
  
        	// 参数序列化
			Long fileInfoId = LongUtils.valueOf(this.getParameterFromRequest(request, "fileInfoId"));
			FileInfo fileInfo = fileInfoService.qryByPrimaryKey(fileInfoId);
			String fileName = fileInfo.getSrcFileName();
	        response.setContentType("text/html;charset=utf-8");  
	        response.setContentType("application/x-msdownload;");  
	        response.setHeader("Content-disposition", "attachment; filename="  
	        		+ new String(fileName.getBytes("utf-8"), "ISO8859-1"));  
	        BufferedInputStream bis = null;  
	        BufferedOutputStream bos = null;  
	        InputStream inputStream = null;  
	  
	        try {  
	        	
	    		FileConfig fileConfig = fileConfigService.qryByPrimaryKey(fileInfo.getFileConfigId());
//	    		FTPConfig ftpConfig = new FTPConfig(fileConfig.getIp(), fileConfig.getUsername(), fileConfig.getPassword(),
//	    				fileConfig.getStoreDir());
//	    		inputStream = FTPClientUtils.downloadFile(fileInfo.getTagetFileName(), ftpConfig);
//				FileInputStream fileIs = null;
				inputStream = new FileInputStream(fileConfig.getStoreDir() + File.separator + fileInfo.getTagetFileName());
	    		
	            bis = new BufferedInputStream(inputStream);  
	            bos = new BufferedOutputStream(response.getOutputStream());  
	            byte[] buff = new byte[2048];  
	            int bytesRead;  
	            while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {  
	                bos.write(buff, 0, bytesRead);  
	            }  
	        } catch (Exception e) {  
	        	logger.error("文件下载异常:{}", e);
	        } finally {  
	            if (bis != null)  
	                bis.close();  
	            if (bos != null)  
	                bos.close();  
	        }  
	    } */ 
    
	
	/**
	 * 
	 * @date 2017年11月13日 下午5:15:47
	 * 
	 * @Description: 查看图片，可查看原图和处理过后的图片
	 * @param ibean
	 * @param request
	 * @param response
	 * @param model
	 *
	 */
	@RequestMapping(value = "/file/showImage")
	public void showImage(ShowImageIbean ibean,HttpServletRequest request, HttpServletResponse response, Model model) {

		if(logger.isDebugEnabled()){
			logger.debug("请求参数  showImage getParameterMap:{}", JSON_UTILS.objectToJson(request.getParameterMap()));
		}
		
		OutputStream os = null;
		InputStream inputStream = null;
		try {

			if(ibean == null){
				throw new Exception("传入参数为空！");
			}
			if(ibean.getFileInfoId() == null){
				throw new Exception("文件id为空！");
			}
			
			if(StringUtils.isBlank(ibean.getIsOriginal())){
				throw new Exception("是否原图为空！");
			}
			
			if(StringUtils.isBlank(ibean.getIsRatio())){
				throw new Exception("是否保持比例为空！");
			}
			
			FileInfo fileInfo = fileInfoService.qryByPrimaryKey(ibean.getFileInfoId());
			String fileName = fileInfo.getSrcFileName();
			FileConfig fileConfig = fileConfigService.qryByPrimaryKey(fileInfo.getFileConfigId());
			FTPConfig ftpConfig = new FTPConfig(fileConfig.getIp(), fileConfig.getUsername(), fileConfig.getPassword(),
					fileConfig.getStoreDir(),fileConfig.getType());
			inputStream = FTPClientUtils.downloadFile(fileInfo.getTagetFileName(), ftpConfig);
			if(IS_RATIO_TRUE.equals(ibean.getIsOriginal())){//原图
				
			}else{//要处理的图
				String fileExdSplit = ".";//文件扩展名分割符号
				int fileSufixIdx = fileName.lastIndexOf(fileExdSplit);
				String sourceSuffixName = fileSufixIdx >= 0 ? fileName.substring(fileSufixIdx) : "";
				String targetSuffixName = ImageUtils.IMAGE_TYPE_JPG;
				String uuid = UUID.randomUUID().toString().replaceAll("-", "");
				
				File sourceFile = null;
				File targetFile = null;
				sourceFile = File.createTempFile(uuid+"_1", sourceSuffixName, null);
				FTPClientUtils.inputStreamToFile(inputStream, sourceFile);
				sourceFile.deleteOnExit();
				String sourceFilePath = sourceFile.getPath();
				
				targetFile = File.createTempFile(uuid+"_2", targetSuffixName, null);
				targetFile.deleteOnExit();
				String targetFilePath = targetFile.getPath();
				
				if(IS_RATIO_TRUE.equals(ibean.getIsRatio())){//保持比例
					if(ibean.getWidth() != 0 && ibean.getHeight() != 0){//宽和高都不为0
						ImageUtils.scale2(sourceFilePath, targetFilePath, ibean.getWidth(), ibean.getHeight(), false);
					}else{
						if(StringUtils.isBlank(ibean.getZoom()) || ibean.getZoomRatio() == 0){
							throw new Exception("放大缩小不能为空或者放大缩小比例不能为0！");
						}
						if(IS_RATIO_TRUE.equals(ibean.getZoom())){//放大
							ImageUtils.scale(sourceFilePath, targetFilePath, ibean.getZoomRatio(), true);
						}else{//缩小
							ImageUtils.scale(sourceFilePath, targetFilePath, ibean.getZoomRatio(), false);
						}
					}
				}else{//不保持比例，必须要有宽和高度
					if(ibean.getWidth() == 0 || ibean.getHeight() == 0){
						throw new Exception("不保持比例时候，宽度和高度都不能为0！");
					}
					ImageUtils.scale3(sourceFilePath, targetFilePath, ibean.getWidth(), ibean.getHeight(), false);
				}
				//新的图片流
				inputStream = new FileInputStream(targetFilePath);
			}
			
			
			os = response.getOutputStream();
			
			int i = inputStream.available(); // 得到文件大小
			byte data[] = new byte[i];
			inputStream.read(data); // 读数据
			os.write(data); // 输出数据
			os.flush();
			
			if (logger.isDebugEnabled()) {
				logger.debug("成功读取文件:{}", fileName);
			}
			
		} catch (Exception e) {
			logger.error("文件读取异常,{}", e);
		} finally {
			try {
				os.close();
				inputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}	
	}
	
	
}
