/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.basic.framework.security.jwt.utils;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.basic.framework.web.api.UserDetailService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.basic.framework.cache.CacheRedis;
import com.basic.framework.common.model.DefaultQueryFilter;
import com.basic.framework.common.utils.BeanUtils;
import com.basic.framework.common.utils.IpUtil;
import com.basic.framework.security.jwt.JwtUserDetails;
import com.basic.oaas.define.PrivateType;
import com.basic.oaas.model.Private;
import com.basic.oaas.model.PrivateMenu;
import com.basic.oaas.model.User;
import com.basic.oaas.model.UserDetail;
import com.basic.oaas.service.PrivateMenuService;
import com.basic.oaas.service.PrivateService;
import com.basic.oaas.service.UserService;

/**
 * 用户工具类
 * @author lzj
 * @version 2013-12-05
 */
public class UserUtils {
	@Value( "${userDetailCache}" )
	private String userDetailCache;
	
	@Value( "${privateDetailCache}" )
	private String privateDetailCache;
	
	@Value( "${privateMenuCache}" )
	private String privateMenuCache;

	@Resource
	private JwtUtils jwtUtils;

	@Autowired
	private UserDetailService userDetailService;
	
	@Autowired
	private  CacheRedis<?, ?> cacheRedis;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private PrivateMenuService privateMenuService;
	
	@Autowired
	private PrivateService privateService;

	@Value( "${localhoustToken}" )
	private String localhoustToken;
	/**
	 * 根据token获取当前用户id和用户名
	 * @return 取不到返回 new User()
	 */
	public  User getUser() {
		RequestAttributes ra = RequestContextHolder.getRequestAttributes();
		User user = new User();
		if( ra != null ) {
			HttpServletRequest request = ( ( ServletRequestAttributes )ra ).getRequest();
			String token = request.getHeader( "token" );
			if("127.0.0.1".equals( IpUtil.getIpAddr( request ) )) {
				//本地测试环境token写死
				token =localhoustToken;
			}
			JwtUserDetails vo = jwtUtils.getUserFromToken( token );
			if( vo != null && StringUtils.isNotEmpty( String.valueOf( vo.getUserId() ) ) ) {
				user.setUserId( vo.getUserId() );
				user.setUsername( vo.getUsername() );
				user.setAccountSetId( vo.getAccountSetId());
				user.setCurrentIp( vo.getIp());
				user.setUserText( vo.getUserText() );
				user.setUserType( vo.getUserType() );
			}
		}
		// 如果没有登录，则返回实例化空的User对象。
		return user;
	}

	/**
	 * 缓存中获取当前用户详细信息(角色，组织，权限，地区)
	 * 
	 * @param userAccount
	 * @return
	 */
	public  UserDetail getUserDetail() {
		UserDetail userDetail = null;
		try {
			User user = getUser();
			if( user.getUserId() != null ) {
				Object obj = cacheRedis.getObject( userDetailCache, user.getUserId().toString());
				if( obj != null ) {
					userDetail = (UserDetail) obj;
				}else {
					userDetail = userDetailService.qryUserDetail( user.getUserId() ,null);
					cacheRedis.setObject(userDetailCache, user.getUserId().toString(), userDetail );
				}
			}
			return userDetail;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return userDetail;
	}
	
	
	/**
	 * 刷新用户详细信息
	 * @param userAccount
	 * @return
	 */
	public void refreshUserDetail(Long userId) {
		try {
			if(userId != null) {
				//重新覆盖放入
				UserDetail userDetail = userDetailService.qryUserDetail(userId,null);
				if (userDetail!=null) {
					cacheRedis.setObject(userDetailCache, userId.toString(), userDetail );
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 将所有缓存中的用户信息删除，重新写入
	 * @param userAccount
	 * @return
	 */
	public void refreshAll() {
		try {
			cacheRedis.removeByCacheCode(userDetailCache);
			DefaultQueryFilter queryFilter = new DefaultQueryFilter();
			
			List<User> list = userService.query(queryFilter);
			for (User user : list) {
				this.refreshUserDetail(user.getUserId());
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 删除用户详细信息
	 * @param userAccount
	 * @return
	 */
	public void removeUserDetail(Long userId) {
		try {
			cacheRedis.removeByKey(userDetailCache, userId.toString());
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	 /** 缓存中获取页面权限明细
	 * 
	 * @param userAccount
	 * @return
	 */
	public JSONObject getPrivates(Long menuId,Long userId) {
		JSONObject jsonObject = null;
		try {
			User user = userService.qryUserById(userId);
			if (user!=null && BeanUtils.isNotEmpty(menuId)) {
				jsonObject = (JSONObject) cacheRedis.getObject(privateDetailCache,user.getUsername());
				//缓存中存在
				if (jsonObject!=null) {
					//分别获取菜单、功能按钮、字段权限
					JSONArray menus = jsonObject.getJSONArray("menus");
					JSONArray funcs = jsonObject.getJSONArray("funcs");
					JSONArray attrs = jsonObject.getJSONArray("attrs");
					//匹配菜单ID，获取想要的菜单权限
					for (int i = menus.size()-1; i >= 0; i--) {
						if (!menuId.equals(menus.getJSONObject(i).get("menuId"))) {
							menus.remove(i);
						}
					}
					for (int i = funcs.size()-1; i >= 0; i--) {
						if (!menuId.equals(funcs.getJSONObject(i).get("menuId"))) {
							funcs.remove(i);
						}
					}
					for (int i = attrs.size()-1; i >= 0; i--) {
						if (!menuId.equals(attrs.getJSONObject(i).get("menuId"))) {
							attrs.remove(i);
						}
					}
					jsonObject.put("menus", menus);
					jsonObject.put("funcs", funcs);
					jsonObject.put("attrs", attrs);
				}else {
					//超级管理员获取所有的
					if ("admin".equals(user.getUsername())) {
						DefaultQueryFilter queryFilter = new DefaultQueryFilter();
						List<Private> list = privateService.query(queryFilter);
						jsonObject = new JSONObject();
						List<Private> menus = new ArrayList<Private>();
						List<Private> funcs = new ArrayList<Private>();
						List<Private> attrs = new ArrayList<Private>();
						List<Private> menus2 = new ArrayList<Private>();
						List<Private> funcs2 = new ArrayList<Private>();
						List<Private> attrs2 = new ArrayList<Private>();
						if (BeanUtils.isNotEmpty(list)) {
							for (Private priv : list) {
								if (PrivateType.MENU.getCode().equals(priv.getPrivateType())) {
									menus.add(priv);
									if (BeanUtils.isNotEmpty(menuId) && menuId.equals(priv.getMenuId())) {
										menus2.add(priv);
									}
								}else if (PrivateType.FUNC.getCode().equals(priv.getPrivateType())) {
									funcs.add(priv);
									if (BeanUtils.isNotEmpty(menuId) && menuId.equals(priv.getMenuId())) {
										funcs2.add(priv);
									}
								}else if (PrivateType.ATTR.getCode().equals(priv.getPrivateType())) {
									attrs.add(priv);
									if (BeanUtils.isNotEmpty(menuId) && menuId.equals(priv.getMenuId())) {
										attrs2.add(priv);
									}
								}
							}
						}
						jsonObject.put("menus", menus);
						jsonObject.put("funcs", funcs);
						jsonObject.put("attrs", attrs);
						cacheRedis.setObject(privateDetailCache,user.getUsername(),jsonObject);
						
						jsonObject = new JSONObject();
						jsonObject.put("menus", menus2);
						jsonObject.put("funcs", funcs2);
						jsonObject.put("attrs", attrs2);
						return jsonObject;
					}
					
					//缓存中取不到，从数据库中查找再刷新缓存
					jsonObject = privateService.getPrivates(userId, menuId);
					this.refreshPrivatesDetail(userId);
				}
			}
			return jsonObject;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return jsonObject;
	}
	
	/** 缓存中获取有权限的菜单
	 * 
	 * @param userAccount
	 * @return
	 */
	public List<PrivateMenu> getPrivateMenus(Long userId) {
		List<PrivateMenu> menus = new ArrayList<PrivateMenu>();
		try {
			User user = userService.qryUserById(userId);
			if (user!=null) {
				menus = (List<PrivateMenu>) cacheRedis.getObject(privateMenuCache,user.getUsername());
				if (menus==null) {
					if ("admin".equals(user.getUsername())) {
						DefaultQueryFilter queryFilter = new DefaultQueryFilter();
						menus = BeanUtils.listToTree(privateMenuService.query(queryFilter));
					}else {
						menus = BeanUtils.listToTree(privateMenuService.getPrivateMenus(userId,null));
					}
					cacheRedis.setObject(privateMenuCache, user.getUsername(),menus);
				}
			}
			return menus;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return menus;
	}
	
	
	/**
	 * 刷新当前用户的菜单权限
	 * @param userAccount
	 * @return
	 */
	public void refreshPrivateMenus(Long userId) {
		try {
			User user = userService.qryUserById(userId);
			if (user!=null) {
				//超级管理员直接获取所有的
				if ("admin".equals(user.getUsername())) {
					DefaultQueryFilter queryFilter = new DefaultQueryFilter();
					cacheRedis.setObject(privateMenuCache, user.getUsername(),BeanUtils.listToTree(privateMenuService.query(queryFilter)));
				}else {
					cacheRedis.setObject(privateMenuCache, user.getUsername(),BeanUtils.listToTree(privateMenuService.getPrivateMenus(userId,null)));
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 刷新页面权限明细
	 * @param userAccount
	 * @return
	 */
	public void refreshPrivatesDetail(Long userId) {
		try {
			User user = userService.qryUserById(userId);
			if (user!=null) {
				//超级管理员获取所有的
				if ("admin".equals(user.getUsername())) {
					DefaultQueryFilter queryFilter = new DefaultQueryFilter();
					List<Private> list = privateService.query(queryFilter);
					JSONObject jsonObject = new JSONObject();
					List<Private> menus = new ArrayList<Private>();
					List<Private> funcs = new ArrayList<Private>();
					List<Private> attrs = new ArrayList<Private>();
					if (BeanUtils.isNotEmpty(list)) {
						for (Private priv : list) {
							if (PrivateType.MENU.getCode().equals(priv.getPrivateType())) {
								menus.add(priv);
							}else if (PrivateType.FUNC.getCode().equals(priv.getPrivateType())) {
								funcs.add(priv);
							}else if (PrivateType.ATTR.getCode().equals(priv.getPrivateType())) {
								attrs.add(priv);
							}
						}
					}
					jsonObject.put("menus", menus);
					jsonObject.put("funcs", funcs);
					jsonObject.put("attrs", attrs);
					cacheRedis.setObject(privateDetailCache,user.getUsername(),jsonObject);
				}else {
					cacheRedis.setObject(privateDetailCache,user.getUsername(),privateService.getPrivates(userId, null));
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
