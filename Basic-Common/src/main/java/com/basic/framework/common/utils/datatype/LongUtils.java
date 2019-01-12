/**
 *
 * companyName
 * copyright 2015-2020
 * @date 2015年12月1日 下午4:53:56
 * @author lzj
 * @Description: Long工具集
 * 
 */
package com.basic.framework.common.utils.datatype;

/**
 *
 * @date 2015年12月1日 下午4:53:56
 * @author lzj
 * @Description: Long工具集
 * 
 */
public class LongUtils {

	private LongUtils() {
	}

	/**
	 * Object 转换为 Long
	 * 
	 * @param value
	 * @return
	 */
	public static Long valueOf(Object value) {
		Long res = null;
		if (value != null && value.toString().trim().length() > 0) {
			res = Long.valueOf(value.toString().trim());
		}
		return res;
	}

}
