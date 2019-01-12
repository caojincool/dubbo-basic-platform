package com.basic.framework.security.jwt;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * JWT保存的用户信息
 *
 * @author lzj
 */
public class JwtUserDetails implements UserDetails {

	private static final long serialVersionUID = 2338888915643360259L;
	
	private String ip;
	private Long userId;
    private String password;
    private String username;
    private Long accountSetId;
    private String userText;
    private Integer userType;
   /* private final Collection<? extends GrantedAuthority> authorities;
    private final boolean accountNonExpired;
    private final boolean accountNonLocked;
    private final boolean credentialsNonExpired;
    private final boolean enabled;*/


   /* public JwtUserDetails(String ip,long userId, String username, String password,Long accountSetId,String userText) {
        this(ip,userId, username, password, accountSetId,userText);
    }*/

    public JwtUserDetails(String ip,long userId, String username, String password,Long accountSetId,String userText,Integer userType) {
        if (username != null && !"".equals(username) && password != null) {
        	this.ip=ip;
            this.userId = userId;
            this.username = username;
            this.password = password;
            this.accountSetId = accountSetId;
            this.userText=userText;
            this.userType=userType;
           /* this.enabled = enabled;
            this.accountNonExpired = accountNonExpired;
            this.credentialsNonExpired = credentialsNonExpired;
            this.accountNonLocked = accountNonLocked;
            this.authorities = authorities;*/
        } else {
            throw new IllegalArgumentException("Cannot pass null or empty values to constructor");
        }
    }

   /* @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }*/

    public long getUserId() {
        return userId;
    }

    public String getIp() {
		return ip;
	}

	@Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    /*@Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }*/

	
	
	public String getUserText() {
		return userText;
	}

	
	public void setUserText( String userText ) {
		this.userText = userText;
	}

	public Long getAccountSetId() {
		return accountSetId;
	}

	
	public void setAccountSetId( Long accountSetId ) {
		this.accountSetId = accountSetId;
	}

	public Integer getUserType() {
		return userType;
	}

	
	public void setUserType( Integer userType ) {
		this.userType = userType;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

	
	
    
}
