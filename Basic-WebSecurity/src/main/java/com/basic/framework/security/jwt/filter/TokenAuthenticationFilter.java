
package com.basic.framework.security.jwt.filter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.alibaba.fastjson.JSON;
import com.basic.framework.cache.CacheRedis;
import com.basic.framework.common.utils.IpUtil;
import com.basic.framework.common.utils.RSAUtils;
import com.basic.framework.security.jwt.JwtUserDetails;
import com.basic.framework.security.jwt.utils.JwtUtils;

/**
 * 使用 token 进行身份验证的过滤器。 如果 request header 中有 auth-token，使用 auth-token
 * 的值查询对应的登陆用户，如果用户有效则放行访问，否则返回 401 错误。
 */
public class TokenAuthenticationFilter extends AbstractAuthenticationProcessingFilter {
	@Resource
	private JwtUtils jwtUtils;

	@Autowired
	private CacheRedis<?, ?> cacheRedis;

	@Value( "${tokenOverMillis}" )
	private Long tokenOverMillis;

	@Value( "${jwt.header}" )
	private String tokenHeader;

	@Value( "${tokenCache}" )
	private String tokenCache;

	@Value( "${tokenCacheOverTime}" )
	private String tokenCacheOverTime;
	
	@Value( "${erp_publicKey}" )
	private String publicKey;

	@Value( "${localhoustToken}" )
	private String localhoustToken;
	

	public TokenAuthenticationFilter() {
		super( new AntPathRequestMatcher( "/tokenLogin", "POST" ) ); // 参考 UsernamePasswordAuthenticationFilter
	}

	@Override
	public Authentication attemptAuthentication( HttpServletRequest request, HttpServletResponse response) {
		try {
			String token = request.getHeader(tokenHeader);
            String ip = IpUtil.getIpAddr( request );
			if("127.0.0.1".equals(ip)) {
				//本地测试环境token写死
				token =localhoustToken;
			}
			
			String username = jwtUtils.getUsernameFromToken( token );
			JwtUserDetails userDetails = jwtUtils.getUserFromToken( token );
			if( userDetails==null || StringUtils.isEmpty( username ) ) {
				return null;
			} else {
				String redisToken = (String) cacheRedis.getObject(tokenCache, ip+"*"+username);
				if("127.0.0.1".equals( IpUtil.getIpAddr( request ) )) {
					//本地测试环境token写死
					redisToken =localhoustToken;
				}else {
					Long overTime = ( Long )cacheRedis.getObject( tokenCacheOverTime, ip + "*" + username );
					String tokenip = userDetails.getIp();
					long currentTimeMillis = System.currentTimeMillis();
					 if (!token.equals(redisToken) || overTime < currentTimeMillis || StringUtils.isEmpty(tokenip)|| StringUtils.isEmpty(ip) || !tokenip.equals(ip)) {
					    return null;
					 }
					// 延长token过期时间
					cacheRedis.setObject( tokenCache, ip + "*" + username, token );
					cacheRedis.setObject( tokenCacheOverTime, ip + "*" + username, System.currentTimeMillis() + tokenOverMillis );
				}
			}
			return new UsernamePasswordAuthenticationToken( userDetails, userDetails.getPassword(), userDetails.getAuthorities() );
		} catch( Exception e ) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		String requestURI = request.getRequestURI();
		setResponseHeader(response);
		Map<String, Object> map = new HashMap<>();
		map.put("istrue", false);
		map.put("status", 801);
		map.put("data", "");
		if ("OPTIONS".equals(request.getMethod())) {
			return;
		}
		if(!requestURI.contains("interfaces") && !requestURI.contains("ureport") && !requestURI.contains("file/downloadFile") && !requestURI.contains("tokenLogin.do") && !requestURI.contains("/basic.finance/accountSet/getAll.do")) {
			Authentication auth = null;
			String token = request.getHeader(tokenHeader);
            String ip = IpUtil.getIpAddr( request );
            logger.info("请求IP值："+ip);
			logger.info("请求token值："+token);
			if("127.0.0.1".equals( ip )) {
				//本地测试环境token写死
				token =localhoustToken;
			}
			// 如果 header 里有 auth-token 时，则使用 token 查询用户数据进行登陆验证
			if (StringUtils.isNotEmpty(token )) {
				// 1. 尝试进行身份认证
				// 2. 如果用户无效，则返回 401
				// 3. 如果用户有效，则保存到 SecurityContext 中，供本次方式后续使用
				auth = attemptAuthentication(request, response);
				if (auth == null) {
					map.put("msg", "token过期或无效，请重新登陆获取token！");
					String param = JSON.toJSONString(map);
					dealErrorReturn(response, param);
					return;
				}
				// 保存认证信息到 SecurityContext，禁止 HttpSessionSecurityContextRepository 创建 session
				//allowSessionCreation.set(false);
				//SecurityContextHolder.getContext().setAuthentication(auth);
			} else {
				map.put("msg", "token为空");
				String param = JSON.toJSONString(map);
				dealErrorReturn(response, param);
				return;
			}
		}else if(requestURI.contains("/interfaces/")) {
			checkSign( request, response, map );
			//return;
			
		}
		// 继续调用下一个 filter: UsernamePasswordAuthenticationToken
		chain.doFilter(request, response);
	}

	private void checkSign( HttpServletRequest request, HttpServletResponse response, Map<String, Object> map ) {
		String sign = request.getHeader("sign");
		String appCode = request.getHeader( "appCode" );
		// 验签
		try {
			if(StringUtils.isNotEmpty(sign) && StringUtils.isNotEmpty(appCode)) {
				Boolean bl = RSAUtils.verify( appCode.getBytes( "UTF-8" ), publicKey, sign );
				if(bl) {
					return;
				}else {
					map.put("msg", "签名验证失败");
					String param = JSON.toJSONString(map);
					dealErrorReturn(response, param);
					return;
				}
			}else {
				map.put("msg", "缺少签名必要参数");
				String param = JSON.toJSONString(map);
				dealErrorReturn(response, param);
				return;
			}
			
		} catch( Exception e ) {
			map.put("msg", "签名验证异常");
			String param = JSON.toJSONString(map);
			dealErrorReturn(response, param);
			return;
		}
	}

	

	// 允许跨域访问
	public void setResponseHeader( HttpServletResponse httpServletResponse ) {
		httpServletResponse.setHeader( "Access-Control-Allow-Origin", "*" );
		httpServletResponse.setHeader( "Access-Control-Allow-Credentials", "true" );
		httpServletResponse.setHeader( "Access-Control-Allow-Methods", "*" );
		httpServletResponse.setHeader("Access-Control-Allow-Headers", "Content-Type,Access-Token,token");
		httpServletResponse.setHeader( "Access-Control-Expose-Headers", "*" );
		httpServletResponse.setHeader("Content-type", "text/html;charset=UTF-8");
	}

	// 检测到没有token，直接返回不验证
	public void dealErrorReturn( HttpServletResponse httpServletResponse, Object obj ) {
		String json = ( String )obj;
		PrintWriter writer = null;
		try {
			writer = httpServletResponse.getWriter();
			writer.print( json );

		} catch( IOException ex ) {
			logger.error( "response error", ex );
		} finally {
			if( writer != null ) {
				writer.close();
			}
		}
	}

	
}