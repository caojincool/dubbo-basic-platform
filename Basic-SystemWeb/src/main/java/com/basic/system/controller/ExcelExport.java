package com.basic.system.controller;

import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.xssf.streaming.SXSSFCell;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.basic.framework.common.model.FTPConfig;
import com.basic.framework.common.utils.datatype.DateUtils;
import com.basic.framework.common.utils.datatype.DoubleUtils;
import com.basic.framework.common.utils.datatype.JsonUtils;
import com.basic.framework.common.utils.ftp.FTPClientUtils;
import com.basic.system.model.ExcelExportTmp;
import com.basic.system.model.ExcelWrite;
import com.basic.system.model.ExcelWriteCol;
import com.basic.system.model.ExcelWriteDefine;
import com.basic.system.model.FileConfig;
import com.basic.system.model.FileInfo;
import com.basic.system.service.ExcelExportTmpService;
import com.basic.system.service.FileConfigService;
import com.basic.system.service.FileInfoService;

/**
 * 
 *
 * @date 2017年7月5日 上午11:30:21
 * 
 * @Description: poi导出excel工具类
 *
 */
@Component("excelExport")
public class ExcelExport {

	private static Logger logger = LoggerFactory.getLogger(ExcelExport.class);

	private static final JsonUtils JSON_UTILS = JsonUtils.getInstance();
	
	@Autowired
	private ExcelExportTmpService excelExportTmpService;

	@Autowired
	private FileInfoService fileInfoService;
	
	@Autowired
	private FileConfigService fileConfigService;


	/**
	 * 
	 * @date 2017年5月26日 下午5:26:15
	 * 
	 * @Description: 根据导出模板编码查询具体模板导出excel
	 * @param os
	 * @param templateCode 模板编码
	 * @param busiBeanLists 业务数据
	 * @throws Exception
	 *
	 */
	public void export(OutputStream os, String templateCode, List<List<Object>> busiBeanLists) throws Exception {

		long startTime = System.currentTimeMillis();

		ExcelExportTmp excelExportTmp = excelExportTmpService.qryByPrimaryKey(templateCode);// 导出模板
		
		InputStream templateIn = null;
		try {

			Long writeFileInfoId = excelExportTmp.getFileInfoId();
			FileInfo fileInfo = fileInfoService.qryByPrimaryKey(writeFileInfoId);
			FileConfig fileConfig = fileConfigService.qryByPrimaryKey(fileInfo.getFileConfigId());
			FTPConfig ftpConfig = new FTPConfig(fileConfig.getIp(), fileConfig.getUsername(), fileConfig.getPassword(),
					fileConfig.getStoreDir(),fileConfig.getType());
			templateIn = FTPClientUtils.downloadFile(fileInfo.getTagetFileName(), ftpConfig);

			
			this.export(os, templateIn, busiBeanLists);

		} finally {
			try {
				if (templateIn != null) {
					templateIn.close();
				}
			} catch (Exception e) {
				templateIn = null;
			}

		}
		
		long endTime = System.currentTimeMillis();
		if (logger.isDebugEnabled()) {
			logger.debug("export excel cost time:"+(endTime-startTime)+"ms");
		}
		
	}

	/**
	 * 
	 * @date 2017年5月27日 下午4:24:49
	 * 
	 * @Description: 解析模板之后，利用该模板来导出
	 * @param os
	 * @param templateIn
	 * @param busiBeanLists
	 * @throws Exception
	 *
	 */
	public void export(OutputStream os, InputStream templateIn, List<List<Object>> busiBeanLists) throws Exception {
//		HSSFWorkbook templateWb = new HSSFWorkbook(templateIn);//模板//用这个，文件是xlsx的会报版本的错误
		XSSFWorkbook templateWb = new XSSFWorkbook(templateIn);
//		SXSSFWorkbook templateWb =  new SXSSFWorkbook(workbook);//SXSSFWorkBook是只写的，它不支持读取，对于阅读的.xlsx文件
//		Workbook templateWb = Workbook.getWorkbook(templateIn);//模板
		List<ExcelWrite> excelWriteList = this.analyzeWriteTemplate(templateWb);//解析模板

//		WorkbookSettings settings = new WorkbookSettings();  
//		settings.setWriteAccess(null);  

//		WritableWorkbook exportWb = Workbook.createWorkbook(os,templateWb,settings);//导出

//		exportWb.removeSheet(0);//删除第一个标签页

//		HSSFWorkbook exportWb = new HSSFWorkbook(templateIn);//模板
		SXSSFWorkbook exportWb =  new SXSSFWorkbook(templateWb, 1000);//内存中保留 1000 条数据，以免内存溢出，其余写入硬盘
		exportWb.removeSheetAt(0);//删除第一个标签页

		for (ExcelWrite excelWrite : excelWriteList) {
			
			int sheetIndex = excelWrite.getSheetIndex();
			
//			WritableSheet exportSheet = exportWb.getSheet(sheetIndex);
			XSSFSheet readsheet = templateWb.getSheetAt(sheetIndex);//可读的
			SXSSFSheet exportSheet = exportWb.getSheetAt(sheetIndex);//写入的
			List<Object> beanList = busiBeanLists.get(sheetIndex);
			
			if(StringUtils.isNotBlank(excelWrite.getSheetName())){
				exportWb.setSheetName(sheetIndex, excelWrite.getSheetName());//新生成的标签页名字
			}
			
			this.writeSheet(readsheet, exportSheet, excelWrite, beanList);
		}

		
		exportWb.write(os);
		exportWb.close();
		templateWb.close();
	}
	
	
	/**
	 * 
	 * @date 2017年5月27日 下午4:25:49
	 * 
	 * @Description: 传进来一个sheet页，把数据写入进去
	 * @param exportSheet
	 * @param excelWrite
	 * @param beanList
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 *
	 */
	private void writeSheet(XSSFSheet readsheet, SXSSFSheet exportSheet, ExcelWrite excelWrite, List<Object> beanList) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		logger.info("export excelWrite:{}", JSON_UTILS.objectToJson(excelWrite));
		logger.info("export beanList.size:{}", beanList.size());
//		exportSheet.getRow(0);//不可读，得到的是null
//		readsheet.getRow(0);//可读的，有数据
//		readsheet.shiftRows(2, 2, -1);//删除第二行到第二行，然后使下方单元格上移
		readsheet.removeRow(readsheet.getRow(1));//删除第二行
		
		int rowIndex = excelWrite.getBeginRowIndex();
		List<ExcelWriteCol> colList = excelWrite.getColList();
		
		Object objBean = beanList.get(0);
		Class<?> objCla = (Class<?>) objBean.getClass();
		Method[] methods = objCla.getMethods();  
		
		Map<String,Method> methodMap = new HashMap<String,Method>();
		
		for (int i = 0; i < methods.length; i++) {
			Method method = methods[i];
			String methodName = method.getName();
			if (methodName.startsWith("get")) {
				String name = methodName.substring(3).toUpperCase();
				methodMap.put(name, method);
			}
		}
		
		
		for (Object bean : beanList) {// 每一行
			SXSSFRow row = exportSheet.createRow(rowIndex);//行数，一般第二行开始，第一行是标题
			
			for (ExcelWriteCol excelWriteCol : colList) {// 每一列
				int colIndex = excelWriteCol.getColIndex();

				Method method = methodMap.get(excelWriteCol.getColCode().toUpperCase());
				Object value = method.invoke(bean);
				
				if(value != null){
					SXSSFCell cell = row.createCell(colIndex);//列数
					if (ExcelWriteDefine.COL_TYPE_STRING.equals((excelWriteCol.getColType()))) {// 字符
//						// 在Label对象的构造中指明单元格位置(参数依次代表列数、行数、内容 )
//						Label label = new Label(colIndex, rowIndex, value.toString());
//						exportSheet.addCell(label);
						cell.setCellValue(value.toString());
					} else if (ExcelWriteDefine.COL_TYPE_INTEGER.equals((excelWriteCol.getColType()))
							|| ExcelWriteDefine.COL_TYPE_LONG.equals((excelWriteCol.getColType()))
							|| ExcelWriteDefine.COL_TYPE_FLOAT.equals((excelWriteCol.getColType()))
							|| ExcelWriteDefine.COL_TYPE_DOUBLE.equals((excelWriteCol.getColType()))
							) {// 整型
						Double numberValue = DoubleUtils.valueOf(value); 
//						jxl.write.Number number = new jxl.write.Number(colIndex,rowIndex,numberValue);  
//						exportSheet.addCell(number);
						cell.setCellValue(numberValue);
					} else if (ExcelWriteDefine.COL_TYPE_DATE.equals((excelWriteCol.getColType()))) {// 日期
						String dataValue = DateUtils.defaultFormatDate((Date)value);
//						// 在Label对象的构造中指明单元格位置(参数依次代表列数、行数、内容 )
//						Label label = new Label(colIndex, rowIndex, dataValue);
//						exportSheet.addCell(label);
						cell.setCellValue(dataValue);
					} else if (ExcelWriteDefine.COL_TYPE_OTHER.equals((excelWriteCol.getColType()))) {// 其它
//						// 在Label对象的构造中指明单元格位置(参数依次代表列数、行数、内容 )
//						Label label = new Label(colIndex, rowIndex, value.toString());
//						exportSheet.addCell(label);
					}
				}else{
//					Label label = new Label(colIndex, rowIndex, "");
//					exportSheet.addCell(label);
				}

			}
			rowIndex++;
		}
	}

	/**
	 * 
	 * @date 2017年5月27日 下午4:24:26
	 * 
	 * @Description: 解析模板读取
	 * @param rwb
	 * @return
	 * @throws ClassNotFoundException
	 * @throws NoSuchFieldException
	 * @throws SecurityException
	 *
	 */
	private List<ExcelWrite> analyzeWriteTemplate(XSSFWorkbook rwb) throws ClassNotFoundException, NoSuchFieldException, SecurityException {
//		Sheet[] sheets = rwb.getSheets();
//		Sheet sheet0 = sheets[0];

		XSSFSheet sheet0 = rwb.getSheetAt(0);
		List<ExcelWrite> list = new ArrayList<ExcelWrite>();
		
		//getPhysicalNumberOfRows()获取的是物理行数，也就是不包括那些空行（隔行）的情况。
		//getLastRowNum()获取的是最后一行的编号（编号从0开始）。
		for (int rowIndex = 1; rowIndex < sheet0.getPhysicalNumberOfRows(); rowIndex++) {
			ExcelWrite excelWrite = new ExcelWrite();

			XSSFRow row = sheet0.getRow(rowIndex);

			int sheetIndex = (int) row.getCell(0).getNumericCellValue();// 标签页序号
			excelWrite.setSheetIndex(sheetIndex);

			String sheetName = row.getCell(1).getStringCellValue();// 标签页名称
			excelWrite.setSheetName(sheetName);

			String javaBeanName = row.getCell(2).getStringCellValue();// javaBean名称
			excelWrite.setJavaBeanName(javaBeanName);

			int beginRowIndex = (int) row.getCell(3).getNumericCellValue();// 开始行号
			excelWrite.setBeginRowIndex(beginRowIndex);

//			Sheet sheet = sheets[sheetIndex + 1];
			XSSFSheet sheet = rwb.getSheetAt(sheetIndex + 1);

			List<ExcelWriteCol> colList = this.analyzeWriteTemplateSheet(sheet, javaBeanName, beginRowIndex);

			excelWrite.setColList(colList);

			list.add(excelWrite);
		}

		if (logger.isDebugEnabled()) {
			logger.debug("list:{}", JSON_UTILS.objectToJson(list));
		}

		return list;
	}

	/**
	 * 
	 * @date 2017年5月27日 下午4:26:51
	 * 
	 * @Description: 解析标签页
	 * @param sheet
	 * @param javaBeanName
	 * @param rowIndex
	 * @return
	 * @throws ClassNotFoundException
	 * @throws NoSuchFieldException
	 * @throws SecurityException
	 *
	 */
	private List<ExcelWriteCol> analyzeWriteTemplateSheet(XSSFSheet sheet, String javaBeanName, int rowIndex)
			throws ClassNotFoundException, NoSuchFieldException, SecurityException {
		List<ExcelWriteCol> list = new ArrayList<ExcelWriteCol>();

		XSSFRow row = sheet.getRow(rowIndex);
		Class<?> classInst = Class.forName(javaBeanName);

		for (int colIndex = 0; colIndex < row.getPhysicalNumberOfCells(); colIndex++) {

			ExcelWriteCol excelWriteCol = new ExcelWriteCol();
			excelWriteCol.setColIndex(colIndex);

			XSSFCell cell = row.getCell(colIndex);
			String colContent = cell.getStringCellValue();
			String[] colContents = colContent.split(",");
			String colCode = colContents[0];
			excelWriteCol.setColCode(colCode);
			if(colContents != null && colContents.length > 1){
				boolean mergeFlag = "1".equals(colContents[1]);
				excelWriteCol.setMergeFlag(mergeFlag);
			}
			try {
				Field field = classInst.getDeclaredField(colCode);
				Class<?> colTypeClass = field.getType();
				String colType = null;

				if (colTypeClass.equals(String.class)) {
					colType = ExcelWriteDefine.COL_TYPE_STRING;
				} else if (colTypeClass.equals(Date.class)) {
					colType = ExcelWriteDefine.COL_TYPE_DATE;
				} else if (colTypeClass.equals(Long.class)) {
					colType = ExcelWriteDefine.COL_TYPE_LONG;
				} else if (colTypeClass.equals(Integer.class)) {
					colType = ExcelWriteDefine.COL_TYPE_INTEGER;
				} else if (colTypeClass.equals(Float.class)) {
					colType = ExcelWriteDefine.COL_TYPE_FLOAT;
				} else if (colTypeClass.equals(Double.class)) {
					colType = ExcelWriteDefine.COL_TYPE_DOUBLE;
				} else {
					colType = ExcelWriteDefine.COL_TYPE_OTHER;
				}
				excelWriteCol.setColType(colType);

			} catch (Exception e) {
				logger.error("javaBeanName:{},colIndex,{},colCode:{}", javaBeanName, colIndex, colCode, e);
				throw e;
			}

			list.add(excelWriteCol);
		}

		return list;
	}

}