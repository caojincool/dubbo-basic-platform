package com.basic.system.controller;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.poi.hssf.usermodel.HSSFDataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import com.basic.framework.common.model.FTPConfig;
import com.basic.framework.common.utils.ReflectUtils;
import com.basic.framework.common.utils.datatype.IntegerUtils;
import com.basic.framework.common.utils.datatype.JsonUtils;
import com.basic.framework.common.utils.datatype.LongUtils;
import com.basic.framework.common.utils.ftp.FTPClientUtils;
import com.basic.system.model.ExcelImportTmp;
import com.basic.system.model.ExcelRead;
import com.basic.system.model.ExcelReadCol;
import com.basic.system.model.ExcelReadDefine;
import com.basic.system.model.FileConfig;
import com.basic.system.model.FileInfo;
import com.basic.system.service.ExcelImportTmpService;
import com.basic.system.service.FileConfigService;
import com.basic.system.service.FileInfoService;

/**
 * 
 *
 * @date 2017年7月7日 下午5:02:51
 * 
 * @Description: poi导入excel工具类
 *
 */
@Component("excelImport")
public class ExcelImport implements ApplicationContextAware{

	private static Logger logger = LoggerFactory.getLogger(ExcelImport.class);

	private static final JsonUtils JSON_UTILS = JsonUtils.getInstance();
	
	private static Map<String,List<ExcelRead>> readTempalteMap = new ConcurrentHashMap<String,List<ExcelRead>>();
	
	@Autowired
	private ExcelImportTmpService excelImportTmpService;

	@Autowired
	private FileInfoService fileInfoService;
	
	@Autowired
	private FileConfigService fileConfigService;
	
	@Autowired
	private ApplicationContext applicationContext;
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}


	/**
	 * 
	 * @date 2017年7月7日 下午5:03:48
	 * 
	 * @Description: 解析Excel
	 * @param templateCode
	 * @param busiBeanName
	 * @param otherParams
	 * @param in
	 * @return
	 * @throws Exception
	 *
	 */
	public Object readExcel(String templateCode,String busiBeanName,String otherParams,InputStream in) throws Exception {
		
		List<ExcelRead> excelReadList = getReadTemplate(templateCode);

//		Workbook rwb = Workbook.getWorkbook(in);
		@SuppressWarnings("resource")
		XSSFWorkbook rwb = new XSSFWorkbook(in);

//		Sheet[] sheets = rwb.getSheets();
		List<List<Object>> resultList = new ArrayList<List<Object>>();
		
		for(ExcelRead excelRead:excelReadList){
			int sheetIndex = excelRead.getSheetIndex();
			int beginRowIndex = excelRead.getBeginRowIndex();
			String javaBeanName = excelRead.getJavaBeanName();
			List<ExcelReadCol> colList = excelRead.getColList();
			
//			Sheet sheet = sheets[sheetIndex];
			XSSFSheet sheet = rwb.getSheetAt(sheetIndex);
			logger.debug("sheetIndex:{},beginRowIndex:{},javaBeanName:{}", sheetIndex, beginRowIndex, javaBeanName);
			
			List<Object> list = this.readSheet(sheet, beginRowIndex, colList, javaBeanName);
			resultList.add(list);
		}
		return resultList;
		/*if(StringUtils.isNotBlank(busiBeanName) && !"undefined".equals(busiBeanName)){//调用业务方法
			ExcelImportBusi excelImportBusi = applicationContext.getBean(busiBeanName,ExcelImportBusi.class);
			Object resultObj = excelImportBusi.importExcel(resultList,otherParams);
			return resultObj;
		}else{
			return resultList;
		}*/
	}
	
	/**
	 * 
	 * @date 2017年7月7日 下午5:04:02
	 * 
	 * @Description: 解析sheet页
	 * @param sheet
	 * @param beginRowIndex
	 * @param colList
	 * @param javaBeanName
	 * @return
	 * @throws ClassNotFoundException
	 * @throws NoSuchFieldException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 *
	 */
	private List<Object> readSheet(XSSFSheet sheet, int beginRowIndex, List<ExcelReadCol> colList, String javaBeanName) throws ClassNotFoundException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, InstantiationException{
		List<Object> list = new ArrayList<Object>();
		
		
		for (int rowIndex = beginRowIndex; rowIndex < sheet.getPhysicalNumberOfRows(); rowIndex++) {
			XSSFRow row = sheet.getRow(rowIndex);
//			Cell[] cells = sheet.getRow(rowIndex);   
//			sheet.get
			//得到当前行的以最后一个不为空的单元格为标准的单元格数量，如果小于解析模板的列数，会导致下标越界
			/*if(cells.length < colList.size()){
				continue;
			}*/
			//System.out.println(rowIndex);
			Class<?> javaBeanClass = Class.forName(javaBeanName);
			Object javaBeanInst = javaBeanClass.newInstance();
			for(ExcelReadCol excelReadCol : colList){
				String colCode = excelReadCol.getColCode();
				int colIndex = excelReadCol.getColIndex();
				String colType = excelReadCol.getColType();
				Object value = null;
				try{
					Field field = javaBeanClass.getDeclaredField(colCode);
//					Cell cell = sheet.getCell(colIndex, rowIndex);
					XSSFCell cell = row.getCell(colIndex);
					if(cell == null){
					}else if(colType.equals(ExcelReadDefine.COL_TYPE_STRING)){
						if("NUMERIC".equals(cell.getCellTypeEnum().toString())){
							 //解决导入int变为double问题
							 HSSFDataFormatter dataFormatter = new HSSFDataFormatter();
							 value = dataFormatter.formatCellValue(cell);
							//double doubleValue = cell.getNumericCellValue();
							//value = String.valueOf(doubleValue);
						}else if("STRING".equals(cell.getCellTypeEnum().toString())){
							value = cell.getStringCellValue();
						}else{
							value = cell.toString();
						}
					}else if(colType.equals(ExcelReadDefine.COL_TYPE_INTEGER)){
//						value = IntegerUtils.valueOf(cell.getContents());
						double doubleValue = cell.getNumericCellValue();
						int intValue = (int) doubleValue;
						value = IntegerUtils.valueOf(intValue);
					}else if(colType.equals(ExcelReadDefine.COL_TYPE_LONG)){
//						value = LongUtils.valueOf(cell.getContents());
						double doubleValue = cell.getNumericCellValue();
						long longValue = (long) doubleValue;
						value = LongUtils.valueOf(longValue);
					}else if(colType.equals(ExcelReadDefine.COL_TYPE_DATE)){
//						DateCell dc = (DateCell)cell;
//						value = dc.getDate();	//获取单元格的date类型
						value = cell.getDateCellValue();
					}else if(colType.equals(ExcelReadDefine.COL_TYPE_DOUBLE)){
						value = cell.getNumericCellValue();
					}else if(colType.equals(ExcelReadDefine.COL_TYPE_BIGDECIMAL)){
						value = BigDecimal.valueOf((double)cell.getNumericCellValue());
					}else if(colType.equals(ExcelReadDefine.COL_TYPE_FLOAT)){
						value = Float.valueOf(String.valueOf(cell.getNumericCellValue()));
					}else {
//						value = cell.getContents();
						value = cell.getStringCellValue();
					}
					field.setAccessible(true);
					field.set(javaBeanInst, value);

					
				}catch(Exception e){
					
					logger.error("javaBeanName:{},colCode:{},colType:{}",javaBeanName,colCode,colType,e);
					throw e;
				}
			}
			if(!ReflectUtils.checkObjIsNull(javaBeanInst)){//不为null才进去
				list.add(javaBeanInst);
			}
		}
		return list;
		
	}

	
	/**
	 * 
	 * @date 2017年7月7日 下午5:04:17
	 * 
	 * @Description: 通过模板编码，获取解析模板
	 * @param templateCode
	 * @return
	 * @throws Exception
	 *
	 */
	private List<ExcelRead> getReadTemplate(String templateCode) throws Exception{
		
		ExcelImportTmp excelImportTmp = excelImportTmpService.qryByPrimaryKey(templateCode);
		Long readFileInfoId = excelImportTmp.getReadFileInfoId();
		
		InputStream in = null;
		List<ExcelRead> list = readTempalteMap.get(templateCode);
		if(list != null){
			return list;
		}
		try{
    		// 获取文件存储的信息
			FileInfo fileInfo = fileInfoService.qryByPrimaryKey(readFileInfoId);
    		FileConfig fileConfig = fileConfigService.qryByPrimaryKey(fileInfo.getFileConfigId());
    		FTPConfig ftpConfig = new FTPConfig(fileConfig.getIp(), fileConfig.getUsername(), fileConfig.getPassword(),
    				fileConfig.getStoreDir(),fileConfig.getType());
    		in = FTPClientUtils.downloadFile(fileInfo.getTagetFileName(), ftpConfig);
			
    		list = this.analyzeReadTemplate(in);
		}finally{
			try{
				if(in != null){
					in.close();
				}
			}catch(Exception e){
				in = null;
			}
			
		}
		readTempalteMap.put(templateCode, list);
		return list;
	}
	
	
	/**
	 * 
	 * @date 2017年7月7日 下午5:04:35
	 * 
	 * @Description: 解析模板读取
	 * @param in
	 * @return
	 * @throws Exception
	 *
	 */
	private List<ExcelRead> analyzeReadTemplate(InputStream in) throws Exception{
//		Workbook rwb = Workbook.getWorkbook(in);
//		Sheet[] sheets = rwb.getSheets();
//		Sheet sheet0 = sheets[0];
		
		@SuppressWarnings("resource")
		XSSFWorkbook rwb = new XSSFWorkbook(in);
		XSSFSheet sheet0 = rwb.getSheetAt(0);
		
		List<ExcelRead> list = new ArrayList<ExcelRead>();
		
		for (int rowIndex = 1; rowIndex < sheet0.getPhysicalNumberOfRows(); rowIndex++) {
			ExcelRead excelRead = new ExcelRead();
			
//			Cell[] cells = sheet0.getRow(rowIndex);
			XSSFRow row = sheet0.getRow(rowIndex);
			
//			int sheetIndex = IntegerUtils.valueOf(cells[0].getContents());//标签页序号
			int sheetIndex = (int) row.getCell(0).getNumericCellValue();// 标签页序号
			excelRead.setSheetIndex(sheetIndex);
			
//			String javaBeanName = cells[1].getContents();//javaBean名称
			String indexName = row.getCell(1).getStringCellValue();// 标签也名称名称
			excelRead.setJavaBeanName(indexName);
			
//			String javaBeanName = cells[1].getContents();//javaBean名称
			String javaBeanName = row.getCell(2).getStringCellValue();// javaBean名称
			excelRead.setJavaBeanName(javaBeanName);
			
//			int beginRowIndex = IntegerUtils.valueOf(cells[2].getContents());//开始行号
			int beginRowIndex = (int) row.getCell(3).getNumericCellValue();// 开始行号
			excelRead.setBeginRowIndex(beginRowIndex);
			
//			Sheet sheet = sheets[sheetIndex+1];
			XSSFSheet sheet = rwb.getSheetAt(sheetIndex + 1);
			
			List<ExcelReadCol> colList = this.analyzeReadTemplateSheet(sheet, javaBeanName, beginRowIndex);
			
			excelRead.setColList(colList);
			
			list.add(excelRead);
		}
		
		if(logger.isDebugEnabled()){
			logger.debug("list:{}", JSON_UTILS.objectToJson(list));
		}
		
		return list;
	}
	
	/**
	 * 
	 * @date 2017年7月7日 下午5:05:01
	 * 
	 * @Description: 解析sheet页
	 * @param sheet
	 * @param javaBeanName
	 * @param rowIndex
	 * @return
	 * @throws ClassNotFoundException
	 * @throws NoSuchFieldException
	 * @throws SecurityException
	 *
	 */
	private List<ExcelReadCol> analyzeReadTemplateSheet(XSSFSheet sheet,String javaBeanName,int rowIndex) throws ClassNotFoundException, NoSuchFieldException, SecurityException {
		List<ExcelReadCol> list = new ArrayList<ExcelReadCol>();
		
//		Cell[] cells = sheet.getRow(rowIndex);
		XSSFRow row = sheet.getRow(rowIndex);
		Class<?> classInst = Class.forName(javaBeanName);
		
		for(int colIndex=0; colIndex < row.getPhysicalNumberOfCells(); colIndex++){
			
			ExcelReadCol excelReadCol = new ExcelReadCol();
			excelReadCol.setColIndex(colIndex);
			
//			String colCode = cells[colIndex].getContents();
			XSSFCell cell = row.getCell(colIndex);
			String colCode = cell.getStringCellValue();
			excelReadCol.setColCode(colCode);
			
			try{
				Field field = classInst.getDeclaredField(colCode);
				Class<?> colTypeClass = field.getType();
				String colType = null;
				
				if(colTypeClass.equals(String.class)){
					colType = ExcelReadDefine.COL_TYPE_STRING;
				}else if(colTypeClass.equals(Date.class)){
					colType = ExcelReadDefine.COL_TYPE_DATE;
				}else if(colTypeClass.equals(Long.class)){
					colType = ExcelReadDefine.COL_TYPE_LONG;
				}else if(colTypeClass.equals(Integer.class)){
					colType = ExcelReadDefine.COL_TYPE_INTEGER;
				}else if(colTypeClass.equals(Double.class)){
					colType = ExcelReadDefine.COL_TYPE_DOUBLE;
				}else if(colTypeClass.equals(Float.class)){
					colType = ExcelReadDefine.COL_TYPE_FLOAT;
				}else if(colTypeClass.equals(BigDecimal.class)){
					colType = ExcelReadDefine.COL_TYPE_BIGDECIMAL;
				}else{
					colType = ExcelReadDefine.COL_TYPE_OTHER;
				}
				excelReadCol.setColType(colType);
				
			}catch(Exception e){
				logger.error("javaBeanName:{},colIndex,{},colCode:{}",javaBeanName,colIndex,colCode,e);
				throw e;
			}
			
			list.add(excelReadCol);
		}
		
		return list;
	}
	
}
