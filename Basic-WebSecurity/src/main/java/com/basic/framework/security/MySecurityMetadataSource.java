/**
 * 
 */
package com.basic.framework.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.basic.framework.security.api.PrivateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;


/**
 * @author lzj
 * @desc 自定义spring security资源管理器，加载资源与权限对应关系
 * 此处后续可以改为通过缓存
 */
public class MySecurityMetadataSource implements FilterInvocationSecurityMetadataSource {
	
	private static Logger logger = LoggerFactory.getLogger(MySecurityMetadataSource.class);
	//权限服务
	private PrivateService privateService;

	public void setPrivateService(PrivateService privateService) {
		this.privateService = privateService;
	}

	@Override
	public Collection<ConfigAttribute> getAllConfigAttributes() {
		return null;
	}

	/**
	 * 返回当前请求所需的权限
	 */
	@Override
	public Collection<ConfigAttribute> getAttributes(Object arg0)
			throws IllegalArgumentException {
		HttpServletRequest request = ((FilterInvocation) arg0).getHttpRequest();
		String requestUrl = request.getRequestURI();
		String method = request.getMethod();
		logger.info("REQUEST URL:{}, METHOD:{}" , requestUrl, method);
		
		Collection<ConfigAttribute> attrs = new ArrayList<>();
		
		List<String> privateCodeList = privateService.qryPrivateByRequest(requestUrl, method);
		if(privateCodeList != null && !privateCodeList.isEmpty()) {
			// 以权限编码封装为Spring的security Object
			for(String privateCode:privateCodeList){
				ConfigAttribute configAttribute = new SecurityConfig(privateCode);
				attrs.add(configAttribute);
			}
		} else {
			ConfigAttribute configAttribute = new SecurityConfig(SecurityCommon.NONE_PRIVATECODE);
			attrs.add(configAttribute);
		}
		return attrs;
	}

	@Override
	public boolean supports(Class<?> arg0) {
		return true;
	}

}
