package com.basic.framework.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.basic.framework.common.utils.IpUtil;
import com.basic.framework.common.utils.RSAUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

public class JwtFilter extends GenericFilterBean {

    private  final  String  publicKey="ABCEDAKJFKADFKKALFJ345&-ADF";

    @Override
    public void doFilter(final ServletRequest req,final ServletResponse res,final FilterChain chain) throws IOException, ServletException {
        final HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response=(HttpServletResponse)res;
        setResponseHeader(response);
        String ip = IpUtil.getIpAddr( request );
        if ("OPTIONS".equals(request.getMethod())) {
            //VUE预请求问题
            return;
        }
        String path = request.getContextPath();
        String basePath = req.getScheme()+"://"+req.getServerName()+":"+req.getServerPort()+path;
        //客户端将token封装在请求头中，格式为（Bearer后加空格）：Authorization：Bearer +token
        final String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new ServletException("Missing or invalid Authorization header.");
        }else{
            Map<String,String> map=new HashMap<>();
            map.put("msg", "token不存在！");
            String resultMsg = JSON.toJSONString(map);
            dealErrorReturn(response, resultMsg);
        }
        //去除Bearer 后部分
        final String token = authHeader.substring(7);
        try {
            //解密token，拿到里面的对象claims
            final Claims claims = Jwts.parser().setSigningKey("secretkey")
                    .parseClaimsJws(token).getBody();
            //将对象传递给下一个请求
            //request.setAttribute("claims", claims);

        }catch (Exception e) {
            throw new ServletException("Invalid token.");
        }
        chain.doFilter(req, res);
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


    // 允许跨域访问
    public void setResponseHeader( HttpServletResponse httpServletResponse ) {
        httpServletResponse.setHeader( "Access-Control-Allow-Origin", "*" );
        httpServletResponse.setHeader( "Access-Control-Allow-Credentials", "true" );
        httpServletResponse.setHeader( "Access-Control-Allow-Methods", "*" );
        httpServletResponse.setHeader("Access-Control-Allow-Headers", "Content-Type,Access-Token,token");
        httpServletResponse.setHeader( "Access-Control-Expose-Headers", "*" );
        httpServletResponse.setHeader("Content-type", "text/html;charset=UTF-8");
    }
}