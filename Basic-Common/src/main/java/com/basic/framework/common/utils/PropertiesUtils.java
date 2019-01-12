/**
 *
 * companyName
 * copyright 2015-2020
 * @date 2015年12月25日 下午2:26:31
 * @author lzj
 * @Description: TODO(这里用一句话描述这个类的作用)
 * 
 */
package com.basic.framework.common.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


/**
 *
 * @date 2015年12月25日 下午2:26:31
 * @author lzj
 * @Description: Properties文件
 * 
 */
public class PropertiesUtils {

	private PropertiesUtils() {

	}

	/**
	 * Properties文件读取
	 * 
	 * @date 2015年12月25日 下午2:27:03
	 * @author lzj
	 * @Description: Properties文件读取
	 * @param filePath
	 * @return
	 *
	 */
	public static Properties loadProperties(String filePath) {
		// path 不以’/'开头时默认是从此类所在的包下取资源，以’/'开头则是从ClassPath根下获取。其只是通过path构造一个绝对路径，最终还是由ClassLoader获取资源。
		InputStream inputStream = PropertiesUtils.class.getResourceAsStream(filePath);
		Properties p = new Properties();
		try {
			p.load(inputStream);
			inputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return p;
	}

}
