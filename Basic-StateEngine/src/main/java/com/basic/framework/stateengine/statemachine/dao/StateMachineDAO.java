package com.basic.framework.stateengine.statemachine.dao;

import com.basic.framework.stateengine.statemachine.model.StateMachineStateBean;

import java.util.List;

/**
 * Created by lzj on 2017/8/1.
 */
public interface StateMachineDAO {

    /**
     * 根据模板编码,
     * @param modelCode
     * @param stateCode
     * @param conditionCode
     * @return
     */
    StateMachineStateBean qryStateByModelAndState(String modelCode,String stateCode,String conditionCode);


    /**
     * 根据模板查询
     * @param modelCode
     * @return
     */
    List<StateMachineStateBean> qryStateByModel(String modelCode);

    /**
     * 查询所有状态
     * @return
     */
    List<StateMachineStateBean> qryAllState();

}
