/**
 * 
 */
package com.basic.framework.security;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.basic.framework.security.api.PrivateService;
import com.basic.framework.security.api.RoleService;
import com.basic.framework.security.api.User;
import com.basic.framework.security.api.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


/**
 * 用户信息
 *  
 *
 */
public class MyUserDetailsServiceImpl implements UserDetailsService {
	
	private static final Logger logger = LoggerFactory.getLogger(MyUserDetailsServiceImpl.class);

    private UserService userService;

    private PrivateService privateService;

	private RoleService roleService;

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public void setPrivateService(PrivateService privateService) {
		this.privateService = privateService;
	}

	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}

	@Override
	public UserDetails loadUserByUsername(String userAccount)
			throws UsernameNotFoundException {
		logger.info("加载用户：{}" , userAccount);
		User user = userService.qryUserByAccount(userAccount);

		if(user == null) {
			logger.info("用户账号{}不存在",userAccount);
			throw new UsernameNotFoundException("用户账号" + userAccount + "不存在");
		}
		String userPassword = user.getUserPassword();
		boolean enables = user.isEnables();//用户账号是否失效
		boolean accountNonExpired = user.isAccountNonExpired();//用户账号是否过期
		boolean credentialsNonExpired = user.isCredentialsNonExpired();//用户凭证是否过期
		boolean accountNonLocked = user.isAccountNonLocked();//用户账号是否锁定

		Collection<GrantedAuthority> grantedAuths = obtionGrantedAuthorities(userAccount);
		MyUserDetail userDetail = new MyUserDetail(
				userAccount,
				userPassword,
				enables, 
				accountNonExpired, 
				credentialsNonExpired, 
				accountNonLocked, 
				grantedAuths
				);
		return userDetail;
	}

	//从数据库中获取用户的权限信息
	private Collection<GrantedAuthority> obtionGrantedAuthorities(String userAccount) {
		Set<GrantedAuthority> authSet = new HashSet<GrantedAuthority>();
		List<String> privateCodeList = null;

		boolean superAdminFlag =roleService.judgeSuperAdmin(userAccount);//判断是否为超级管理员

		if(superAdminFlag){//超级管理员
			logger.info("--------超级管理员角色--------{}",userAccount);
			privateCodeList = this.privateService.qryAllPrivate();
		}else{
			privateCodeList = this.privateService.qryPrivateByUser(userAccount);
		}

		if(privateCodeList!=null && privateCodeList.size()>0){
			for(String privateCode:privateCodeList){
				authSet.add(new SimpleGrantedAuthority(privateCode));
			}
		}
		
		logger.info("拥有的权限：" + authSet.size());
		return authSet;
	}

}
