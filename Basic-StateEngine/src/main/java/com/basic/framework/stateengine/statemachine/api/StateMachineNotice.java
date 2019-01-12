package com.basic.framework.stateengine.statemachine.api;

/**
 * Created by lzj on 2017/8/1.
 */
public interface StateMachineNotice {

    /**
     * 状态变更通知
     * @param modelCode
     * @param targetState
     */
    void stateChangeNotice(String modelCode,String targetState);

}
