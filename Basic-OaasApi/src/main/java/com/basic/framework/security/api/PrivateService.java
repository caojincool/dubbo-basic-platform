package com.basic.framework.security.api;

import java.util.List;

/**
 * Created by lzj on 2017/6/2.
 */
public interface PrivateService {

    /**
     * 根据请求地址与方法，返回所需要的权限编码
     * @param requestUrl
     * @param method
     * @return
     */
    List<String> qryPrivateByRequest(String requestUrl, String method);

    /**
     * 根据用户帐号查询用户权限
     * @param userAccount
     * @return
     */
    List<String> qryPrivateByUser(String userAccount);

    /**
     * 查询所有权限
     * @return
     */
    List<String> qryAllPrivate();
}
