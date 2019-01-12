package com.basic.framework.common.utils.datatype;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import com.basic.framework.common.model.TreeBean;
import com.basic.framework.common.utils.BeanUtils;
import com.basic.framework.common.utils.excel.util.ExcelUtil;

/**
 *
 * @Description: excel导出工具测试类
 * 
 */
public class ExcelUtilTest {

	/**
	 * 
	 * @Description:导出excel样例，大数据量分多次在同一个sheet导出
	 * @author lengzj
	 * @param reponse
	 * @throws Exception
	 */
	public static void exportInOneSheetExample(HttpServletResponse reponse) throws Exception {
		//构造列头,key-字段名，value-列头名称
		Map<String, String> fieldMap = new LinkedHashMap<String,String>();
		fieldMap.put("id", "id");
		fieldMap.put("pId", "pId");
		
		//数据总数
		int total = 1000000;
		//分页查询大小
		int pageSize = 50000;
		//在同一个sheet中导出需要先构造sheet，指定输出的sheet
		SXSSFWorkbook sworkbook = new SXSSFWorkbook(1000);
		SXSSFSheet sheet = sworkbook.createSheet();
		//将需要导出的数据分页查询来进行导出，否则查询数据量太大会爆炸
		int round = total%pageSize==0?total/pageSize:total%pageSize+1;
		for (int i = 0; i < round; i++) {
			//查询数据
			List<TreeBean> list = new ArrayList<TreeBean>();
			for (int j = 0; j < pageSize; j++) {
				TreeBean bean = new TreeBean(1L, 2L);
				list.add(bean);
			}
			//将查询的数据转换为map结构，为了与列头匹配写入
			List data = new ArrayList<Map<String,Object>>();
			for (TreeBean obj : list) {
				data.add(BeanUtils.convertObjToMap(obj));
			}
			//释放数据
			list.clear();
			//导出数据到excel
			sworkbook = ExcelUtil.exportXlsxExcel(null, fieldMap , data,null,null, sheet,sworkbook);
			//释放数据
			data.clear();
		}
		ExcelUtil.downloadExcel(sworkbook, "导出测试", reponse);
	}
	
	/**
	 * 
	 * @Description:导出excel样例，大数据量分多次在多个sheet导出
	 * @author lengzj
	 * @param reponse
	 * @throws Exception
	 */
	public static void exportInMultiSheetExample(HttpServletResponse reponse) throws Exception {
		//构造列头,key-字段名，value-列头名称
		Map<String, String> fieldMap = new LinkedHashMap<String,String>();
		fieldMap.put("id", "id");
		fieldMap.put("pId", "pId");
		
		//数据总数
		int total = 1000000;
		//分页查询大小
		int pageSize = 50000;
		SXSSFWorkbook sworkbook = new SXSSFWorkbook(1000);
		//将需要导出的数据分页查询来进行导出，否则查询数据量太大会爆炸
		int round = total%pageSize==0?total/pageSize:total/pageSize+1;
		for (int i = 0; i < round; i++) {
			//查询数据
			List<TreeBean> list = new ArrayList<TreeBean>();
			for (int j = 0; j < pageSize; j++) {
				TreeBean bean = new TreeBean(1L, 2L);
				list.add(bean);
			}
			//将查询的数据转换为map结构，为了与列头匹配写入
			List data = new ArrayList<Map<String,Object>>();
			for (TreeBean obj : list) {
				data.add(BeanUtils.convertObjToMap(obj));
			}
			//释放数据
			list.clear();
			//导出数据到excel
			//分多个sheet导出不需要指定sheet，会自动创建，若给了title会以title来命名，注意每个sheet的title不能重复
			sworkbook = ExcelUtil.exportXlsxExcel(null, fieldMap , data,null,null, null,sworkbook);
			//释放数据
			data.clear();
		}
		ExcelUtil.downloadExcel(sworkbook, "导出测试", reponse);
	}
	
	/**
	 * 
	 * @Description:导出excel样例，一次性导出所有数据
	 * @author lengzj
	 * @param reponse
	 * @throws Exception
	 */
	public static void exportExample(HttpServletResponse reponse) throws Exception {
		//构造列头,key-字段名，value-列头名称
		Map<String, String> fieldMap = new LinkedHashMap<String,String>();
		fieldMap.put("id", "id");
		fieldMap.put("pId", "pId");
		
		//查询数据
		List<TreeBean> list = new ArrayList<TreeBean>();
		for (int j = 0; j < 50000; j++) {
			TreeBean bean = new TreeBean(1L, 2L);
			list.add(bean);
		}
		//将查询的数据转换为map结构，为了与列头匹配写入
		List data = new ArrayList<Map<String,Object>>();
		for (TreeBean obj : list) {
			data.add(BeanUtils.convertObjToMap(obj));
		}
		//释放数据
		list.clear();
		//导出数据到excel
		SXSSFWorkbook sworkbook = ExcelUtil.exportXlsxExcel("测试sheet", fieldMap , data,null,null, null,null);
		//释放数据
		data.clear();
		ExcelUtil.downloadExcel(sworkbook, "导出测试", reponse);
	}
}
