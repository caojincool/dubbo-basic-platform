package com.basic.framework.stateengine.statemachine.test;

import com.basic.framework.stateengine.statemachine.api.StateMachineNotice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by lzj on 2017/8/3.
 */
public class StateMachineNoticeImpl2 implements StateMachineNotice{

    private static final Logger logger = LoggerFactory.getLogger(StateMachineNoticeImpl2.class);

    @Override
    public void stateChangeNotice(String modelCode, String targetState) {
        logger.info("StateMachineNoticeImpl1 modelCode:{},targetState:{}",modelCode,targetState);
    }


}
