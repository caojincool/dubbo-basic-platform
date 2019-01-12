package com.basic.framework.security.demo;

import com.basic.framework.security.api.RoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by lzj on 2017/6/2.
 */
public class RoleServiceImpl implements RoleService {
    private static Logger logger = LoggerFactory.getLogger(RoleServiceImpl.class);

    @Override
    public boolean judgeSuperAdmin(String userAccount) {
        logger.debug("judgeSuperAdmin,usuerAccount:{}",userAccount);

        return false;
    }
}
