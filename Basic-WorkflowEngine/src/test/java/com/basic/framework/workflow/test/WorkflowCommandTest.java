package com.basic.framework.workflow.test;

import com.basic.framework.common.BeanFactoryUtils;
import com.basic.framework.common.utils.datatype.DateUtils;
import com.basic.framework.gid.utils.sequence.GidClientUtils;
import com.basic.framework.workflow.define.CommandType;
import com.basic.framework.workflow.define.GidCodes;
import com.basic.framework.workflow.engine.WorkflowCommandListener;
import com.basic.framework.workflow.model.WorkflowCommandBean;

import java.util.HashMap;

/**
 * Created by lzj on 2017/7/26.
 */
public class WorkflowCommandTest {

    private void processStartTest(){
        WorkflowCommandListener listener = BeanFactoryUtils.getBeanByName("workflowCommandListener",WorkflowCommandListener.class);

        WorkflowCommandBean bean = new WorkflowCommandBean();

        bean.setCommandId(GidClientUtils.getInstance().getGidValue(GidCodes.WFE_SER_WORKFLOW_COMMAND_EXEC_SEQ));
        bean.setSerialNo(1L);
        bean.setProcessDefineKey("testProcess01");
        bean.setBusiOrderId(3L);
        bean.setParamMap(new HashMap<String, Object>());
        bean.setCommandType(CommandType.PROCESS_START.getCode());
        bean.setCreateTime(DateUtils.now());

        listener.commandExec(bean);
    }

    private void taskCompleteTest(String processInstanceId,String taskId){
        WorkflowCommandListener listener = BeanFactoryUtils.getBeanByName("workflowCommandListener",WorkflowCommandListener.class);

        WorkflowCommandBean bean = new WorkflowCommandBean();

        bean.setCommandId(GidClientUtils.getInstance().getGidValue(GidCodes.WFE_SER_WORKFLOW_COMMAND_EXEC_SEQ));
        bean.setSerialNo(1L);
        bean.setProcessInstanceId(processInstanceId);
        bean.setTaskId(taskId);
        bean.setParamMap(new HashMap<String, Object>());
        bean.setCommandType(CommandType.TASK_COMPLETE.getCode());
        bean.setCreateTime(DateUtils.now());

        listener.commandExec(bean);
    }


    private void taskRollbackTest(String processInstanceId,String taskId){
        WorkflowCommandListener listener = BeanFactoryUtils.getBeanByName("workflowCommandListener",WorkflowCommandListener.class);

        WorkflowCommandBean bean = new WorkflowCommandBean();

        bean.setCommandId(GidClientUtils.getInstance().getGidValue(GidCodes.WFE_SER_WORKFLOW_COMMAND_EXEC_SEQ));
        bean.setSerialNo(1L);
        bean.setProcessInstanceId(processInstanceId);
        bean.setTaskId(taskId);
        bean.setParamMap(new HashMap<String, Object>());
        bean.setCommandType(CommandType.TASK_ROLLBACK.getCode());
        bean.setCreateTime(DateUtils.now());

        listener.commandExec(bean);
    }


    private void processJumpTest(String processInstanceId,String taskId,String targetTaskDefinitionKey){
        WorkflowCommandListener listener = BeanFactoryUtils.getBeanByName("workflowCommandListener",WorkflowCommandListener.class);

        WorkflowCommandBean bean = new WorkflowCommandBean();

        bean.setCommandId(GidClientUtils.getInstance().getGidValue(GidCodes.WFE_SER_WORKFLOW_COMMAND_EXEC_SEQ));
        bean.setSerialNo(1L);
        bean.setProcessInstanceId(processInstanceId);
        bean.setTaskId(taskId);
        bean.setTargetTaskDefinitionKey(targetTaskDefinitionKey);
        bean.setParamMap(new HashMap<String, Object>());
        bean.setCommandType(CommandType.PROCESS_JUMP.getCode());
        bean.setCreateTime(DateUtils.now());

        listener.commandExec(bean);
    }

    public static void main(String args[]){
        WorkflowCommandTest workflowCommandTest = new WorkflowCommandTest();
//        workflowCommandTest.processStartTest();
//        workflowCommandTest.taskCompleteTest("107501","107505");
//        workflowCommandTest.taskRollbackTest("107501", "110002");
        workflowCommandTest.processJumpTest("107501","112502","task02");

        System.out.println("完成");
    }




}
