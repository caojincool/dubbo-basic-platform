/**
 * 
 */
package com.basic.framework.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

/**
 * @author lzj
 * @desc 自定义UserDetail，方便freemaker页面直接使用
 */
public class MyUserDetail extends User {
	
		private static final long serialVersionUID = 5687265998377917076L;

	public MyUserDetail(String userAccount,String password, boolean enabled, boolean accountNonExpired,
			boolean credentialsNonExpired, boolean accountNonLocked,
			Collection<? extends GrantedAuthority> authorities) {
		super(userAccount, password, enabled, accountNonExpired, credentialsNonExpired,
				accountNonLocked, authorities);
	}


}
