package com.basic.framework.security.demo;

import com.basic.framework.security.api.PrivateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lzj on 2017/6/2.
 */
public class PrivateServiceImpl implements PrivateService {

    private static Logger logger = LoggerFactory.getLogger(PrivateServiceImpl.class);

    @Override
    public List<String> qryPrivateByRequest(String requestUrl, String method) {
        logger.debug("qryPrivateByRequest,requestUrl:{},method:{}",requestUrl,method);
        List<String> list = new ArrayList<String>();
        list.add("privavteCode1");
        return list;
    }

    @Override
    public List<String> qryPrivateByUser(String userAccount) {
        logger.debug("qryPrivateByUser,userAccount:{}",userAccount);

        List<String> list = new ArrayList<String>();
        list.add("privavteCode1");
        list.add("privavteCode2");
        return list;
    }

    @Override
    public List<String> qryAllPrivate() {
        List<String> list = new ArrayList<String>();
        list.add("privavteCode1");
        list.add("privavteCode2");
        list.add("privavteCode3");
        return list;
    }
}
