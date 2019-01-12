package com.basic.framework.security.api;

/**
 * Created by lzj on 2017/6/2.
 */
public interface UserService {

    /**
     * 查询用户详情
     * @param userAccount
     * @return
     */
    User qryUserByAccount(String userAccount);
}
