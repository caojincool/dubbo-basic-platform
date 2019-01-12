package com.basic.framework.stateengine.statemachine.service;

import com.basic.framework.common.utils.datatype.StringUtils;
import com.basic.framework.stateengine.statemachine.api.StateMachineNotice;
import com.basic.framework.stateengine.statemachine.dao.StateMachineDAO;
import com.basic.framework.stateengine.statemachine.model.StateMachineStateBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by lzj on 2017/8/1.
 */
public class StateMachineServiceImpl implements StateMachineService {

    private static Logger logger = LoggerFactory.getLogger(StateMachineServiceImpl.class);

    @Autowired
    private StateMachineDAO stateMachineDAO;

    private Map<String, StateMachineNotice> stateMachineNoticeMap;

    public void setStateMachineNoticeMap(Map<String, StateMachineNotice> stateMachineNoticeMap) {
        this.stateMachineNoticeMap = stateMachineNoticeMap;
    }

    /**
     * 状态变更
     *
     * @param modelCode
     * @param stateCode
     * @param conditionCode
     */
    @Override
    public String stateChange(String modelCode, String stateCode, String conditionCode) {
        logger.debug("状态变更,modelCode:{},stateCode:{},conditionCode:{}",modelCode,stateCode,conditionCode);
        StateMachineStateBean stateBean = stateMachineDAO.qryStateByModelAndState(modelCode, stateCode, conditionCode);
        if (stateBean == null) {
            throw new RuntimeException("无法找到目标状态");
        } else {
            String busiCode = stateBean.getBusiCode();
            StateMachineNotice stateMachineNotice = stateMachineNoticeMap.get(busiCode);
            stateMachineNotice.stateChangeNotice(stateBean.getModelCode(),stateBean.getTargetState());
            return stateBean.getTargetState();
        }

    }
}
