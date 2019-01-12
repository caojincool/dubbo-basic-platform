/**
 * 
 */
package com.basic.framework.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.basic.framework.common.utils.Base64Utils;

//import com.basic.framework.oaas.service.OaasUserService;


/**
 * @author YeRunhua
 * @desc 自定义spring security登录验证
 */
public class MyUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	
	public static final String VALIDATE_CODE = "validateCode";
    public static final String USERNAME = "userAccount";
    public static final String PASSWORD = "userPassword";
    public static final String ENCRYPT_TYPE = "encryptType";//加密类型
    public static final String BASE64 = "BASE64";//base64编码


	@Override
	public Authentication attemptAuthentication(HttpServletRequest request,
			HttpServletResponse response) throws AuthenticationException {
		//登录功能不支持GET请求
//		if (!request.getMethod().equals("POST")) {
//			throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
//		}
		
		//校验验证码
		String validateCode = null;//用户输入验证码
		String sessionValidateCode = (String) request.getSession().getAttribute("randVerifyCode");//session里面随机生成的验证码
		if(request.getParameter(VALIDATE_CODE) != null) {
			validateCode = request.getParameter(VALIDATE_CODE).toString().trim().toLowerCase();
		}
		if(StringUtils.isNotBlank(validateCode) && StringUtils.isNotBlank(sessionValidateCode)){
			if(!sessionValidateCode.equals(validateCode)){//不相符
				throw new AuthenticationServiceException("验证码错误！");
			}
		}
		
		//加密方式
		String encryptType = null;
		if(request.getParameter(ENCRYPT_TYPE) != null) {
			encryptType = request.getParameter(ENCRYPT_TYPE).toString().trim();
		}
		
		//验证用户名密码
		String username = null;
		String password = null;
		
		if(request.getParameter(USERNAME) != null) {
			username = request.getParameter(USERNAME).toString().trim();
		}else if(request.getSession().getAttribute("userAccount")!=null){
			username = request.getSession().getAttribute("userAccount").toString().trim();
		}
		if(request.getParameter(PASSWORD) != null) {
			password = request.getParameter(PASSWORD).toString().trim();
			//base64编码加密方式
			if(StringUtils.isNotBlank(encryptType) && BASE64.equals(encryptType)){
				//解码
				password = Base64Utils.decode(password);
			}
		}else if(request.getSession().getAttribute("userPassword")!=null) {
			password = request.getSession().getAttribute("userPassword").toString().trim();
			//base64编码加密方式
			if(StringUtils.isNotBlank(encryptType) && BASE64.equals(encryptType)){
				//解码
				password = Base64Utils.decode(password);
			}
		}
		
		if(StringUtils.isBlank(username) || StringUtils.isBlank(password)){
			throw new AuthenticationServiceException("账号或者密码不能为空");
		}
		
		//UsernamePasswordAuthenticationToken实现 Authentication
		UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(username, password);
		//设置详情
		setDetails(request, authRequest);
		
		//运行UserDetailsService的loadUserByUsername 再次封装Authentication
		Authentication a = this.getAuthenticationManager().authenticate(authRequest);
		return a;
	}

}
