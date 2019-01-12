package com.basic.framework.security.demo;

import com.basic.framework.security.api.User;
import com.basic.framework.security.api.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by lzj on 2017/6/2.
 */
public class UserServiceImpl implements UserService {

    private static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);


    @Override
    public User qryUserByAccount(String userAccount) {
        logger.debug("qryUserByAccount,userAccount:{}",userAccount);
        User user = new User();

        user.setAccountNonExpired(true);
        user.setAccountNonLocked(true);
        user.setCredentialsNonExpired(true);
        user.setEnables(true);
        user.setUserAccount(userAccount);
//        user.setUserPassword("1234");
        user.setUserPassword("81dc9bdb52d04dc20036dbd8313ed055");//1234
        return user;
    }
}
