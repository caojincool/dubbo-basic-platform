package com.basic.system.service;
import com.alibaba.fastjson.JSONObject;
import com.basic.system.service.ExterInterfaceService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("exterInterfaceService")
public class ExterInterfaceServiceImpl implements ExterInterfaceService {

    @Resource
    private BaseSysInterfaceService baseSysInterfaceService;
    @Resource
    private BaseSysSysLogService baseSysSysLogService;
    @Resource 
    private BaseSysSystemService baseSysSystemService;

    
    @Override
    public List<JSONObject>  exterInterface(String type,JSONObject jsonObject, String systemCode){
        return null;
    }

}
