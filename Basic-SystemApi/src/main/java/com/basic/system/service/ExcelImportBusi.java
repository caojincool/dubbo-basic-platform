package com.basic.system.service;

import java.util.List;

/**
 * Excel导入业务方法
 *
 * @date 2016年9月1日 下午7:15:45
 * @author lzj
 * @Description: 
 *
 */
public interface ExcelImportBusi {

	/**
	 * 导入Excel，控制完成Excel的解析后调用此业务方法
	 * @date 2016年9月1日 下午7:17:14
	 * @author lzj
	 * @Description: 
	 * @param list 最外层每个节点代表一个Sheet页，内层一个节点代表一条记录
	 * @return
	 * @throws Exception
	 *
	 */
	public Object importExcel(List<List<Object>> list,String otherParams) throws Exception;
}
