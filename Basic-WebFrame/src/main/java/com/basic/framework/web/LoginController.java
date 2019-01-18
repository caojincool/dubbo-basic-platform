/**
 * 
 */
package com.basic.framework.web;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import org.springframework.web.bind.annotation.RequestBody;

import com.basic.framework.common.utils.datatype.JsonUtils;
import com.basic.framework.common.utils.result.HttpEntity;
import com.basic.framework.common.utils.result.HttpStatus;
import io.jsonwebtoken.Jwts;

import java.util.Date;

/**
 * @author lzj
 *
 */
@Controller
public class LoginController extends BaseController {
	
	private static Logger logger = LoggerFactory.getLogger(LoginController.class);
	private static final JsonUtils JSON_UTILS = JsonUtils.getInstance();


	@RequestMapping(value = "login")
	@ResponseBody
	public HttpEntity login(@RequestBody JSONObject jsonObject)
			throws ServletException {
		String name = jsonObject.getString("userAccount");
		String password = jsonObject.getString("password");
		Long accountSetId = jsonObject.getLong("accountSetId");// 账套ID
		if (name == null) {
			throw new ServletException("Invalid login");
		}
		String token=Jwts.builder().setSubject(name)
				.claim("roles", name).setIssuedAt(new Date())
				.signWith(SignatureAlgorithm.HS256, "secretkey").compact();
		//加密生成token
		return new HttpEntity(HttpStatus.OK, true, "请求成功",token);
	}





	/**
	 * 判断浏览器中是否有保存名为SPRING_SECURITY_REMEMBER_ME_COOKIE的Cookie
	 *
	 * @return
	 */
	public boolean hasCookie(HttpServletRequest request) {
		boolean result = false;
		Cookie[] cookies = request.getCookies();

		logger.debug("hasCookie cookies:{}", JSON_UTILS.objectToJson(cookies));

		if(cookies != null) {
			if(cookies != null && cookies.length > 0) {
				for (int i = 0; i < cookies.length; i++) {
					if("SPRING_SECURITY_REMEMBER_ME_COOKIE".equals(cookies[i].getName())) {
						result = true;
						break;
					}
				}
			}
		}
		return result;
	}
	
}
