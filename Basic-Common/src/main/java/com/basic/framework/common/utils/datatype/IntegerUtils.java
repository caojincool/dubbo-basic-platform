/**
 *
 * companyName
 * copyright 2015-2020
 * @date 2015年12月1日 下午4:55:32
 * @author lzj
 * @Description: Integer工具集
 * 
 */
package com.basic.framework.common.utils.datatype;

/**
 *
 * @date 2015年12月1日 下午4:55:32
 * @author lzj
 * @Description: Integer工具集
 * 
 */
public class IntegerUtils {

	private IntegerUtils() {
	}

	/**
	 * Object 转换为 Integer
	 * @param value
	 * @return
	 */
	public static Integer valueOf(Object value) {
		Integer res = null;
		if (value != null && value.toString().trim().length() > 0) {
			res = Integer.valueOf(value.toString().trim());
		}
		return res;
	}

}
