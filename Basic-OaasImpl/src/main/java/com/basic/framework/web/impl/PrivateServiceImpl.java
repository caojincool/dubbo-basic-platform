package com.basic.framework.web.impl;

import com.basic.framework.security.api.PrivateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by lzj on 2017/6/2.
 */
@Service("privateServiceWeb")
public class PrivateServiceImpl implements PrivateService {

    private static Logger logger = LoggerFactory.getLogger(PrivateServiceImpl.class);

    @Autowired
    private com.basic.oaas.service.PrivateService privateService;
    @Override
    public List<String> qryPrivateByRequest(String requestUrl, String method) {
        logger.debug("qryPrivateByRequest,requestUrl:{},method:{}",requestUrl,method);
        List<String> list = privateService.qryPrivateByRequest(requestUrl);
        return list;
    }

    @Override
    public List<String> qryPrivateByUser(String userAccount) {
        logger.debug("qryPrivateByUser,userAccount:{}",userAccount);

        List<String> list = privateService.qryPrivateByUsername(userAccount);
        return list;
    }

    @Override
    public List<String> qryAllPrivate() {
        List<String> list =privateService.qryAllPrivate();
        return list;
    }
}
