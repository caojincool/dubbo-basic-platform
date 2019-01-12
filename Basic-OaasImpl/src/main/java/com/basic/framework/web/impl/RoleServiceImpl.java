package com.basic.framework.web.impl;

import com.basic.framework.security.api.RoleService;
import com.basic.oaas.define.RoleCodeEnum;
import com.basic.oaas.model.Role;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by lzj on 2017/6/2.
 */
@Service("roleServiceWeb")
public class RoleServiceImpl implements RoleService {
    private static Logger logger = LoggerFactory.getLogger(RoleServiceImpl.class);

    @Autowired
    private com.basic.oaas.service.RoleService roleService;
    
    @Override
    public boolean judgeSuperAdmin(String userAccount) {
        logger.debug("judgeSuperAdmin,usuerAccount:{}",userAccount);

        boolean flag = false;
        List<Role> roles = roleService.qryRoleByUsername(userAccount);
        
        if(roles != null && roles.size() >0){
        	for (Role role : roles) {
        		if(RoleCodeEnum.SUPERADMINROLE.getCode().equals(role.getRoleCode())){
					flag = true;
				}
			}
        }
        return flag;
    }
}
