/**
 *
 */
package com.basic.framework.security.jwt;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.basic.framework.web.api.UserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.alibaba.dubbo.common.utils.StringUtils;
import com.alibaba.fastjson.JSONObject;

import com.basic.framework.cache.CacheRedis;
import com.basic.framework.common.utils.HttpTools;
import com.basic.framework.common.utils.IpUtil;
import com.basic.framework.common.utils.RSAUtils;
import com.basic.framework.common.utils.result.HttpEntity;
import com.basic.framework.common.utils.result.HttpStatus;
import com.basic.framework.security.jwt.JwtUserDetails;
import com.basic.framework.security.jwt.utils.JwtUtils;
import com.basic.framework.security.jwt.utils.UserUtils;
import com.basic.framework.web.BaseController;
import com.basic.oaas.model.User;
import com.basic.oaas.service.UserService;

/**
 * @author lzj
 */
@Controller
public class TokenLoginController extends BaseController {

    @Autowired
    private UserService userService;

    @Resource
    private JwtUtils jwtUtils;

    @Resource
    private UserUtils userUtils;

    @Autowired
    private CacheRedis<?, ?> cacheRedis;

    @Autowired
    private UserDetailService userDetailService;

    @Value("${tokenOverMillis}")
    private Long tokenOverMillis;

    @Value("${tokenCache}")
    private String tokenCache;

    @Value("${tokenCacheOverTime}")
    private String tokenCacheOverTime;

    @Value("${userDetailCache}")
    private String userDetailCache;

    @Value("${erp_publicKey}")
    private String publicKey;

    @Value("${erp_privateKey}")
    private String privateKey;

    @Value("${tqwlUrl}")
    private String tqwlUrl;

    @Autowired
    private AuthenticationManager myAuthenticationManager; // 很重要这样就可以自动注入

    /**
     * 基于token登陆
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/tokenLogin")
    @ResponseBody
    public HttpEntity tokenLogin(@RequestBody JSONObject jsonObject, HttpServletRequest request, HttpServletResponse response) {
        String userAccount = jsonObject.getString("userAccount");
        String password = jsonObject.getString("password");
        Long accountSetId = jsonObject.getLong("accountSetId");// 账套ID
        try {
            if (StringUtils.isNotEmpty(userAccount) && StringUtils.isNotEmpty(password) && accountSetId != null) {
                UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(userAccount, password);
                myAuthenticationManager.authenticate(authRequest);// 验证用户名密码
                User user = userService.qryUserByUsername(userAccount);
                user.setAccountSetId(accountSetId);
                //刷新缓存菜单信息
                userUtils.refreshPrivateMenus(user.getUserId());
                //刷新缓存权限信息
                userUtils.refreshPrivatesDetail(user.getUserId());
                return new HttpEntity(HttpStatus.OK, true, "请求成功", getToken(user, request));

            } else {
                return new HttpEntity(HttpStatus.OK, false, "缺少参数", "");
            }
        } catch (AuthenticationException ex) {
            return new HttpEntity(HttpStatus.OK, false, "用户名密码错误！", "");
        } catch (Exception e) {
            // e.printStackTrace();
            return new HttpEntity(HttpStatus.OK, false, "缓存token出错！", "");
        }

    }

    /**
     * 注销登陆
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/tokenLoginOut")
    @ResponseBody
    public HttpEntity tokenLoginOut(@RequestBody JSONObject jsonObject, HttpServletRequest request, HttpServletResponse response) {
        String userAccount = jsonObject.getString("userAccount");
        try {
            String ip = IpUtil.getIpAddr(request);
            String redisToken = (String) cacheRedis.getObject(tokenCache, ip + "*" + userAccount);
            if (StringUtils.isNotEmpty(redisToken)) {
                cacheRedis.removeByKey(tokenCache, ip + "*" + userAccount);
                cacheRedis.removeByKey(tokenCacheOverTime, ip + "*" + userAccount);
            }
            return new HttpEntity(HttpStatus.OK, true, "注销成功", "");
        } catch (Exception e) {
            e.printStackTrace();
            return new HttpEntity(HttpStatus.OK, false, "注销失败！", "");
        }

    }


    /**
     * 切换系统到天奇物流
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/changSysTqwl")
    @ResponseBody
    public HttpEntity changSysTqwl(@RequestBody JSONObject jsonObject, HttpServletRequest request, HttpServletResponse response) {
        String userAccount = jsonObject.getString("userAccount");
        String userId = jsonObject.getString("userId");
        String name = jsonObject.getString("name");
        String appCode = jsonObject.getString("appCode");
        try {
            // 签名
            String signData = RSAUtils.sign(appCode.getBytes("UTF-8"), privateKey);
            JSONObject json = new JSONObject();
            json.put("sign", signData);
            json.put("userId", userId);
            json.put("userAccount", userAccount);
            json.put("name", name);
            json.put("appCode", appCode);
            String result = HttpTools.sendPostJson(tqwlUrl + "/user/changeSys", json.toString(), "POST");
            JSONObject jsonResult = JSONObject.parseObject(result);
            jsonResult.get("data");

            return new HttpEntity(HttpStatus.OK, true, "请求成功", jsonResult.get("data"));
        } catch (Exception e) {
            e.printStackTrace();
            return new HttpEntity(HttpStatus.OK, false, "系统异常，切换失败！", "");
        }

    }

    /**
     * @author lengzhijun
     * @Description: 根据token查询账号的详细信息, 并将信息缓存到redis中
     */
    @ResponseBody
    @RequestMapping(value = {"/token/getCurrentUser"}, method = RequestMethod.POST)
    public HttpEntity qryUserDetail(@RequestBody JSONObject jsonObject) {
        try {
            return new HttpEntity(HttpStatus.OK, true, "请求成功", userUtils.getUserDetail());
        } catch (Exception e) {
            return new HttpEntity(HttpStatus.OK, false, "获取权限信息出错！", "");
        }

    }

    /**
     * 获取token,如果存在，且在有效期内则复用，否则重新生成
     *
     * @param user
     * @return
     * @throws Exception
     * @author lengzhijun
     */
    private String getToken(User user, HttpServletRequest request) throws Exception {
        Long userId = user.getUserId();
        String userAccount = user.getUsername();
        String userPassword = user.getUserPassword();
        String userText = user.getUserText();
        Long accountSetId = user.getAccountSetId();
        String ip = IpUtil.getIpAddr(request);
        Integer userType = user.getUserType();

        //String redisToken = ( String )cacheRedis.getObject( tokenCache, ip + "*" + userAccount );
        //Long overTime = cacheRedis.getObject( tokenCacheOverTime, ip + "*" + userAccount ) == null ? 0L : ( Long )cacheRedis.getObject( tokenCacheOverTime, ip + "*" + userAccount );
        long currentTimeMillis = System.currentTimeMillis();
		/*if( redisToken != null && overTime > currentTimeMillis ) {
			// 延长token过期时间
			cacheRedis.setObject( tokenCacheOverTime, ip + "*" + user.getUsername(), System.currentTimeMillis() + tokenOverMillis );
			return redisToken;
		} else {*/
        UserDetails userDetails = new JwtUserDetails(ip, userId, userAccount, userPassword, accountSetId, userText, userType);
        String redisToken = jwtUtils.generateAccessToken(userDetails);
        cacheRedis.setObject(tokenCache, ip + "*" + userAccount, redisToken);
        cacheRedis.setObject(tokenCacheOverTime, ip + "*" + userAccount, currentTimeMillis + tokenOverMillis);
        return redisToken;
        //}

    }


}
