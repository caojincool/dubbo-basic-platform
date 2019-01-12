package com.basic.framework.common.utils.excel.util;

import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.hssf.util.HSSFColor.HSSFColorPredefined;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.basic.framework.common.model.TreeBean;
import com.basic.framework.common.utils.BeanUtils;
import com.basic.framework.common.utils.StringUtil;
import com.basic.framework.common.utils.excel.DefaultExcelStyle;
import com.basic.framework.common.utils.excel.Excel;
import com.basic.framework.common.utils.excel.editor.IFontEditor;
import com.basic.framework.common.utils.excel.style.Color;
import com.basic.framework.common.utils.excel.style.font.BoldWeight;
import com.basic.framework.common.utils.excel.style.font.Font;

/**
 * 一些工具方法
 * 
 * @author zxh
 * 
 */
public class ExcelUtil {

	/**
	 * 获取工作表的行数
	 * 
	 * @param sheet
	 *            HSSFSheet表对象
	 * @return 表行数
	 */
	public static int getLastRowNum(HSSFSheet sheet) {
		int lastRowNum = sheet.getLastRowNum();
		if (lastRowNum == 0) {
			lastRowNum = sheet.getPhysicalNumberOfRows() - 1;
		}
		return lastRowNum;
	}

	/**
	 * 获取该行第一个单元格的下标
	 * 
	 * @param row
	 *            行对象
	 * @return 第一个单元格下标，从0开始
	 */
	public static int getFirstCellNum(HSSFRow row) {
		return row.getFirstCellNum();
	}

	/**
	 * 获取该行最后一个单元格的下标
	 * 
	 * @param row
	 *            行对象
	 * @return 最后一个单元格下标，从0开始
	 */
	public static int getLastCellNum(HSSFRow row) {
		return row.getLastCellNum();
	}

	/**
	 * 获取POI的行对象
	 * 
	 * @param sheet
	 *            表对象
	 * @param row
	 *            行号，从0开始
	 * @return
	 */
	public static HSSFRow getHSSFRow(HSSFSheet sheet, int row) {
		if (row < 0) {
			row = 0;
		}
		HSSFRow r = sheet.getRow(row);
		if (r == null) {
			r = sheet.createRow(row);
		}
		return r;
	}

	/**
	 * 获取单元格对象
	 * 
	 * @param sheet
	 *            表对象
	 * @param row
	 *            行，从0开始
	 * @param col
	 *            列，从0开始
	 * @return row行col列的单元格对象
	 */
	public static HSSFCell getHSSFCell(HSSFSheet sheet, int row, int col) {
		HSSFRow r = getHSSFRow(sheet, row);
		return getHSSFCell(r, col);
	}

	/**
	 * 获取单元格对象
	 * 
	 * @param row
	 *            行，从0开始
	 * @param col
	 *            列，从0开始
	 * @return 指定行对象上第col行的单元格
	 */
	public static HSSFCell getHSSFCell(HSSFRow row, int col) {
		if (col < 0) {
			col = 0;
		}
		HSSFCell c = row.getCell(col);
		c = c == null ? row.createCell(col) : c;
		return c;
	}

	/**
	 * 获取工作表对象
	 * 
	 * @param workbook
	 *            工作簿对象
	 * @param index
	 *            表下标，从0开始
	 * @return
	 */
	public static HSSFSheet getHSSFSheet(HSSFWorkbook workbook, int index) {
		if (index < 0) {
			index = 0;
		}
		if (index > workbook.getNumberOfSheets() - 1) {
			workbook.createSheet();
			return workbook.getSheetAt(workbook.getNumberOfSheets() - 1);
		} else {
			return workbook.getSheetAt(index);
		}
	}

	/**
	 * 下载文件
	 * 
	 * @param workBook
	 * @param fileName
	 * @param response
	 * @throws IOException
	 */

	public static void downloadExcel(HSSFWorkbook workBook, String fileName, HttpServletResponse response) throws IOException {
		response.setContentType("application/vnd.ms-excel");
		if("GBK".equals(System.getProperty("file.encoding"))) {
			response.setHeader("Content-Disposition", "attachment;filename=\"" + new String(fileName.getBytes(), "ISO-8859-1") + ".xls" + "\"");
		} else {
			response.setHeader("Content-Disposition", "attachment;filename=\"" + URLEncoder.encode(fileName,"utf-8") + ".xls" + "\"");
		}
		OutputStream os = null;
		try {
			os = response.getOutputStream();
			workBook.write(os);
			os.flush();
			os.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (os != null) {
                os.close();
            }
		}
	}
	
	/**
	 * 下载文件
	 * 
	 * @param workBook
	 * @param fileName
	 * @param response
	 * @throws IOException
	 */
	
	public static void downloadExcel(SXSSFWorkbook workBook, String fileName, HttpServletResponse response) throws IOException {
		response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
		if("GBK".equals(System.getProperty("file.encoding"))) {
			response.setHeader("Content-Disposition", "attachment;filename=\"" + new String(fileName.getBytes(), "ISO-8859-1") + ".xlsx" + "\"");
		} else {
			response.setHeader("Content-Disposition", "attachment;filename=\"" + URLEncoder.encode(fileName,"utf-8") + ".xlsx" + "\"");
		}
		OutputStream os = null;
		try {
			os = response.getOutputStream();
			workBook.write(os);
			os.flush();
			os.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (os != null) {
                os.close();
            }
		}
	}

	/**
	 * 导出excel文件。
	 * 
	 * @param title
	 *            excel表格名称
	 * @param rowHeight
	 *            行高
	 * @param fieldMap
	 *            字段名映射 为一个LinkedHashMap
	 * @param data
	 *            行数据
	 * @return
	 * @throws Exception
	 */
	public static HSSFWorkbook exportExcel(String title, Map<String, String> fieldMap, List data) throws Exception {

		int size = fieldMap.size();
		Excel excel = new Excel();

		int titleCols = size; // 列数

		if (titleCols == 0) {
			throw new Exception("请设置列！");
		}

		// 设置页名
		excel.sheet().sheetName(title);// 重命名当前处于工作状态的表的名称

		int i = 0;
		// 设置表头，第二行开始
		for (String name : fieldMap.values()) { // 表头已经排序过了

			excel.cell(0, i).value(name).align(HorizontalAlignment.CENTER) // 设置水平对齐方式
					.bgColor(Color.GREY_25_PERCENT) // 设置背景色
					.fontHeightInPoint(10).width(4800)// 增加宽度，150像素
					.border(BorderStyle.THIN, Color.BLACK) // 设置外边框样式
					.font(new IFontEditor() { // 设置字体
						@Override
						public void updateFont(Font font) {
							font.boldweight(BoldWeight.BOLD);// 粗体
							font.color(Color.BLACK);// 字体颜色
						}
					});
			i++;
		}

		// 插入数据，第三行开始
		int rows = 1;
		for (Object obj : data) {
			Map rowObj = (Map) obj;
			int col = 0;
			for (String key : fieldMap.keySet()) {
				String val = rowObj.get(key) == null ? "" : rowObj.get(key).toString();
				excel.cell(rows, col).value(val).border(BorderStyle.THIN, Color.BLACK) // 设置外边框样式
						.fontHeightInPoint(10).align(HorizontalAlignment.LEFT); // 设置水平对齐方式
				col++;
			}
			rows++;
		}

		return excel.getWorkBook();
	}
	
	
	/**
	 * 
	 * @Description:数据分多个sheet导出，同一个sheet可多次导出
	 * @author Administrator
	 * @param title sheet的标题
	 * @param fieldMap 列头
	 * @param data 数据
	 * @param headStyle 指定的列头单元格格式，不指定取默认
	 * @param cellStyle 指定的单元格格式，不指定取默认
	 * @param sheet 导出指定的sheet，没有指定自动创建
	 * @param sworkbook 导出指定的工作表，没有指定自动创建
	 * @return
	 * @throws Exception
	 */
	public static SXSSFWorkbook exportXlsxExcel(String title, Map<String, String> fieldMap, 
			List data,CellStyle headStyle,CellStyle cellStyle,
			SXSSFSheet sheet,SXSSFWorkbook sworkbook) throws Exception {
		if (BeanUtils.isEmpty(fieldMap)) {
			throw new Exception("请设置列！");
		}
		if (sworkbook==null) {
			sworkbook = new SXSSFWorkbook(1000);
		}
		if (sheet==null) {
			if (BeanUtils.isNotEmpty(title)) {
				sheet = sworkbook.createSheet(title);
			}else {
				sheet = sworkbook.createSheet();
			}
		}
		if (headStyle==null) {
			headStyle = getDefaultHeadStyle(sworkbook);
		}
		if (cellStyle==null) {
			cellStyle = getDefaultCellStyle(sworkbook);
		}
		// 产生标题列，每个sheet页产生一个标题
		Cell cell;
		if (sheet.getLastRowNum()==0) {
			Row header = sheet.createRow(0); // 第0行
			int index = 0;
			for (String value : fieldMap.values()) {
				cell = header.createCell(index++);
				cell.setCellStyle(headStyle);
				cell.setCellValue(value);
			}
		}
		
		// 插入数据，第三行开始
		int rows = sheet.getLastRowNum()+1;
		
		for (Object obj : data) {
			Map rowObj = (Map) obj;
			int col = 0;
			Row row = sheet.createRow(rows++);
			for (String key : fieldMap.keySet()) {
				String val = rowObj.get(key) == null ? "" : rowObj.get(key).toString();
				cell = row.createCell(col++);
				cell.setCellStyle(cellStyle);
				cell.setCellValue(val);
			}
		}
		return sworkbook;
	}
	
	public static HSSFWorkbook exportSheetExcel(String title, List<Map<String, Object>> data, String[] mods) throws Exception {
//		HSSFWorkbook workbook = new HSSFWorkbook();
//		//表头样式
//		HSSFCellStyle style = workbook.createCellStyle();
//		
//		for (int i = 0; i < mods.length; i++) {
//			HSSFSheet sheet = workbook.createSheet(mods[i]);
//			//导出字段
//			Map<String, String> exportMaps = (Map<String, String>)data.get(i).get("fields");
//			//导出数据
//			List<Map<String, String>> dataList = (List<Map<String, String>>)data.get(i).get("data");
//			
//			//生成表头
//			HSSFRow row = sheet.createRow(0);
//			int fCell = 0;
//			for (String name : exportMaps.values()) {
//				System.out.println("!!!!:"+name);
//				HSSFCell cell = row.createCell(fCell);
//				cell.setCellValue(name);
//				fCell++;
//			}
//			//插入数据
//			for (int j = 0; j < dataList.size(); j++) {
//				HSSFRow bodyRow = sheet.createRow(j+1);
//				Map rowObj = (Map) dataList.get(j);
//				int col = 0;
//				for (String key : exportMaps.keySet()) {
//					String val = rowObj.get(key) == null ? "" : rowObj.get(key).toString();
//					HSSFCell cell = bodyRow.createCell(col);
//					cell.setCellValue(val);
//					col++;
//				}
//			}
//		}
//		return workbook;
		

		Excel excel = new Excel();
		for (int i = 0; i < mods.length; i++) {
			//导出字段
			Map<String, String> fieldMap = (Map<String, String>)data.get(i).get("fields");
			//导出数据
			List<Map<String, String>> dataList = (List<Map<String, String>>)data.get(i).get("data");
			int size = fieldMap.size();
			int titleCols = size; // 列数
			if (titleCols == 0) {
				throw new Exception("请设置列！");
			}
			// 设置页名
			excel.setWorkingSheet(i).sheetName(mods[i]);
			int p = 0;
			// 设置表头，第二行开始
			for (String name : fieldMap.values()) { // 表头已经排序过了

				excel.cell(0, p).value(name).align(HorizontalAlignment.CENTER) // 设置水平对齐方式
						.bgColor(Color.GREY_25_PERCENT) // 设置背景色
						.fontHeightInPoint(12).width(100 * 40)// 增加宽度
						.border(BorderStyle.THIN, Color.BLACK) // 设置外边框样式
						.font(new IFontEditor() { // 设置字体
							@Override
							public void updateFont(Font font) {
								font.boldweight(BoldWeight.BOLD);// 粗体
								font.color(Color.BLACK);// 字体颜色
							}
						});
				p++;
			}

			// 插入数据，第三行开始
			int rows = 1;
			for (Object obj : dataList) {
				Map rowObj = (Map) obj;
				int col = 0;
				for (String key : fieldMap.keySet()) {
					String val = rowObj.get(key) == null ? "" : rowObj.get(key).toString();
					excel.cell(rows, col).value(val).border(BorderStyle.MEDIUM, Color.BLACK) // 设置外边框样式
							.fontHeightInPoint(11).warpText(false).align(HorizontalAlignment.LEFT); // 设置水平对齐方式
					col++;
				}
				rows++;
			}
		}
		return excel.getWorkBook();
	}
	
	/**
	 * 单元格数据转字符串
	 * @param row
	 * @param headMap
	 * @param cellName
	 * @return
	 */
	public static String cellToString(Row row,Map<String,Integer> headMap,String cellName){
		String str = null;
		if(BeanUtils.isEmpty(headMap.get(cellName))){
			return str;
		}
		Cell cell = row.getCell(headMap.get(cellName));
		if(BeanUtils.isNotEmpty(cell)){
			cell.setCellType(Cell.CELL_TYPE_STRING);
			str = cell.getStringCellValue().trim();
		}
		return str;
	}
	/**
	 * 单元格数据转double
	 * @param row
	 * @param headMap
	 * @param cellName
	 * @return
	 */
	public static Double cellToDouble(Row row,Map<String,Integer> headMap,String cellName,Integer decimal,int mode){
		BigDecimal res = null;
		Cell heightCell = row.getCell(headMap.get(cellName));
		if(BeanUtils.isNotEmpty(heightCell)){
			String value = cellToString(row, headMap, cellName);
			if (StringUtils.isNotEmpty(value)) {
				if (Double.valueOf(value)!=null) {
					res = BigDecimal.valueOf(Double.valueOf(value));
					return res.setScale(decimal, mode).doubleValue();
				}
			}
		}
		return null;
	}
	/**
	 * 单元格数据转日期类型
	 * @param row
	 * @param headMap
	 * @param cellName
	 * @return
	 * @throws ParseException 
	 * @throws Exception
	 */
	public static Date cellToDate(Row row,Map<String,Integer> headMap,String cellName){
		Date induTime =  null;
		if(BeanUtils.isEmpty(headMap.get(cellName))){
			return induTime;
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Cell cell = row.getCell(headMap.get(cellName));
		if(BeanUtils.isNotEmpty(cell)){
			if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC){//数字类型日期
				induTime = HSSFDateUtil.getJavaDate(cell.getNumericCellValue());
			}else if(cell.getCellType() == HSSFCell.CELL_TYPE_STRING){//字符串类型
				String entryDate = cell.getStringCellValue().trim();
				if(StringUtil.isNotEmpty(entryDate)){
					try {
						induTime = sdf.parse(entryDate);
					} catch (ParseException e) {
						e.printStackTrace();
					}
				}
			}
		}
		return induTime;
	}
	
	public static CellStyle getDefaultHeadStyle(SXSSFWorkbook sworkbook) {
		CellStyle headStyle = sworkbook.createCellStyle();
		headStyle.setAlignment(HorizontalAlignment.CENTER);
		headStyle.setFillForegroundColor(HSSFColorPredefined.GREY_25_PERCENT.getIndex());
		headStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		headStyle.setBorderBottom(BorderStyle.THIN);
		headStyle.setBorderLeft(BorderStyle.THIN);
		headStyle.setBorderRight(BorderStyle.THIN);
		headStyle.setBorderTop(BorderStyle.THIN);
		headStyle.setBottomBorderColor(HSSFColor.HSSFColorPredefined.BLACK.getIndex());
		headStyle.setTopBorderColor(HSSFColor.HSSFColorPredefined.BLACK.getIndex());
		headStyle.setLeftBorderColor(HSSFColor.HSSFColorPredefined.BLACK.getIndex());
		headStyle.setRightBorderColor(HSSFColor.HSSFColorPredefined.BLACK.getIndex());
		org.apache.poi.ss.usermodel.Font font = sworkbook.createFont();
		font.setBold(true);
		headStyle.setFont(font);
		return headStyle;
	}
	
	public static CellStyle getDefaultCellStyle(SXSSFWorkbook sworkbook) {
		CellStyle cellStyle = sworkbook.createCellStyle();
		cellStyle.setAlignment(HorizontalAlignment.LEFT);
		org.apache.poi.ss.usermodel.Font font = sworkbook.createFont();
		font.setFontHeightInPoints((short)10);
		cellStyle.setFont(font);
		return cellStyle;
	}
}
