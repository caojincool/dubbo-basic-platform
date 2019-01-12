/**
 *
 * companyName
 * copyright 2015-2020
 * @date 2017年11月15日 上午11:09:45
 * @author LGK
 * @Description: TODO(这里用一句话描述这个类的作用)
 * 
 */
package com.basic.order.bean;
import java.io.BufferedInputStream;
import java.io.InputStream;


import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import com.alibaba.fastjson.JSON;

/**
 *
 * @date 2017年11月15日 上午11:09:45
 * @author LGK
 * @Description: TODO(这里用一句话描述这个类的作用)
 * 
 */
public class HttpUtils {
	
	
	
	/**
	 * 
	 * @date 2018年1月26日 下午3:20:20
	 * @author 
	 * @Description: post json 请求
	 * @param url
	 * @param params
	 * @return
	 *
	 */
	public  static String sendPost(String url,String params) throws Exception{
		CloseableHttpClient httpCilent = HttpClients.createDefault();
		 RequestConfig requestConfig = RequestConfig.custom().
	                setConnectTimeout(2000).setConnectionRequestTimeout(2000)
	                .setSocketTimeout(2000).setRedirectsEnabled(true).build();
		 
		 HttpPost post = new HttpPost(url);
		 post.setConfig(requestConfig);
		 StringEntity entity = new StringEntity(params,"utf-8");//解决中文乱码问题    
		 entity.setContentEncoding("UTF-8");    
		 entity.setContentType("application/json");  
		 post.setEntity(entity);
		 InputStream in  = null;
		 BufferedInputStream bis = null;
		 byte[] buff =new byte[2*1024];
		 StringBuffer buffer = new StringBuffer();
		 HttpResponse httpResponse = httpCilent.execute(post);
		 
		 if(httpResponse.getStatusLine().getStatusCode() != 200){
			return JSON.toJSONString("连接失败");
		 }
		 if (httpResponse.getEntity() != null) {
			 	in = httpResponse.getEntity().getContent();
			 	bis = new BufferedInputStream(in);  
			    int len=-1;
		        while((len=bis.read(buff))!=-1) {  
		        	buffer.append(new String(buff,0,len));
		        }  
	     }
		 
		bis.close();
		httpCilent.close();
		return buffer.toString();	
	}
	


}
