package com.basic.framework.stateengine.statemachine.test;

import com.basic.framework.stateengine.statemachine.service.StateMachineService;

/**
 * Created by lzj on 2017/8/3.
 */
public class StateMachineServiceTest {

    public void doTest(){
        StateMachineService service = BeanFactoryUtils.getBeanByName("stateMachineService",StateMachineService.class);
        service.stateChange("modelTest","TEST1","2");
    }

    public static void main(String args[]){
        StateMachineServiceTest t = new StateMachineServiceTest();
        t.doTest();

    }


}
