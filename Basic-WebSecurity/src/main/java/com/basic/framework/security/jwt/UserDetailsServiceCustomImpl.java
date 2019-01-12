package com.basic.framework.security.jwt;

import com.basic.framework.security.api.User;
import com.basic.framework.security.api.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.basic.framework.security.MyUserDetailsServiceImpl;

/**
 * 提供认证所需的用户信息
 *
 * @author ybin
 * @since 2017-03-08
 */
public class UserDetailsServiceCustomImpl implements UserDetailsService {
	private static final Logger logger = LoggerFactory.getLogger(MyUserDetailsServiceImpl.class);

	private UserService userService;

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@Override
	public UserDetails loadUserByUsername(String userAccount) throws UsernameNotFoundException {
		logger.info("加载用户：{}", userAccount);
		User user = userService.qryUserByAccount(userAccount);

		if (user == null) {
			logger.info("用户账号{}不存在", userAccount);
			throw new UsernameNotFoundException("用户账号" + userAccount + "不存在");
		}
		Long userId = user.getUserId();
		String userPassword = user.getUserPassword();
		UserDetails userDetails = new JwtUserDetails("",userId, userAccount, userPassword, user.getAccountSetId(),user.getUserText(),user.getUserType());
		return userDetails;
	}

	

}