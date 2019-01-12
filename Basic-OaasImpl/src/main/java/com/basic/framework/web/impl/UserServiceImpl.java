package com.basic.framework.web.impl;

import com.basic.framework.common.utils.datatype.DateUtils;
import com.basic.framework.security.api.User;
import com.basic.framework.security.api.UserService;
import com.basic.oaas.define.UserState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Created by lzj on 2017/6/2.
 */
@Service("userServiceWeb")
public class UserServiceImpl implements UserService {

    private static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private com.basic.oaas.service.UserService userService;
    
    @Override
    public User qryUserByAccount(String userAccount) {
        logger.debug("qryUserByAccount,userAccount:{}",userAccount);
        User user = new User();
        com.basic.oaas.model.User oaasUser =  userService.qryUserByUsername(userAccount);
        
       
        
        boolean enables = true;//用户账号是否失效
		boolean accountNonExpired = true;//用户账号是否过期
		boolean credentialsNonExpired = true;//用户凭证是否过期
		boolean accountNonLocked = true;//用户账号是否锁定
       
        if(oaasUser != null){
        	
        	if(oaasUser.getState().equals(UserState.INVALID.getCode())) {
    			enables = false;
    		}
    		if(oaasUser.getState().equals(UserState.LOCKED.getCode())) {
    			accountNonLocked = false;
    		}
    		if(oaasUser.getExpireTime() != null 
    				&& DateUtils.compare(oaasUser.getExpireTime(), DateUtils.now()) <= 0) {
    			accountNonExpired = false;
    		}
    		if(oaasUser.getCreateTime() != null 
    				&& DateUtils.compare(oaasUser.getCreateTime(), DateUtils.now()) > 0) {
    			credentialsNonExpired = false;
    		}
    		user.setUserId(oaasUser.getUserId());
    		user.setUserName(oaasUser.getUsername());
    		user.setUserAccount(userAccount);
    		user.setUserPassword(oaasUser.getUserPassword());
    		user.setUserType( oaasUser.getUserType() );
    		user.setUserText( oaasUser.getUserText() );
    		
    		user.setAccountNonExpired(accountNonExpired);
	        user.setAccountNonLocked(accountNonLocked);
	        user.setCredentialsNonExpired(credentialsNonExpired);
	        user.setEnables(enables);
        }else{
        	user = null;
        	logger.info("用户账号" + userAccount + "不存在");
        	throw new UsernameNotFoundException("用户账号" + userAccount + "不存在");
        }
       
        return user;
    }
}
