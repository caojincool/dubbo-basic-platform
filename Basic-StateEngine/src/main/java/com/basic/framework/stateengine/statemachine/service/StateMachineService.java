package com.basic.framework.stateengine.statemachine.service;

/**
 * Created by lzj on 2017/8/1.
 */
public interface StateMachineService {


    /**
     * 状态变更
     * @param modelCode
     * @param stateCode
     * @param conditionCode
     */
    String stateChange(String modelCode,String stateCode,String conditionCode);


}
