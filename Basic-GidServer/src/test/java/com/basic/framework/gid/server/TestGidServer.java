/**
 *
 * companyName
 * copyright 2015-2020
 * @date 2015年12月12日 上午11:39:14
 * @author lzj
 * @Description: TODO(这里用一句话描述这个类的作用)
 * 
 */
package com.basic.framework.gid.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;



/**
 *
 * @date 2015年12月12日 上午11:39:14
 * @author lzj
 * @Description: TODO(这里用一句话描述这个类的作用)
 * 
 */
public class TestGidServer {

	public static void main(String[] args) throws Exception {
		String url="http://192.168.9.57:10188/";
		String seqName="PUB_PARAMETER_SEQ";
		// 创建HttpClient实例
		String getURL = url + "?gidCode=" + seqName;
		String result = null;
		URL getUrl = null;
		HttpURLConnection connection = null;
		BufferedReader reader = null;
		try {
			getUrl = new URL(getURL);
			connection = (HttpURLConnection) getUrl.openConnection();
			connection.connect();
			reader = new BufferedReader(new InputStreamReader(
					connection.getInputStream()));
			String lines;

			while ((lines = reader.readLine()) != null) {
				result = lines;
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {

					e.printStackTrace();
				}
			}

			if (connection != null) {
				connection.disconnect();
			}
		}

		System.out.println(result);
	}
	
	
  
}
