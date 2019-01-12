package com.basic.framework.security.api;

/**
 * Created by lzj on 2017/6/2.
 */
public interface RoleService {

    /**
     * 判断是否为超级管理员
     * @param userAccount
     * @return
     */
    boolean judgeSuperAdmin(String userAccount);
}
