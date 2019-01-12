package com.basic.framework.workflow.engine;

import com.basic.framework.common.utils.Exceptions;
import com.basic.framework.common.utils.datatype.DateUtils;
import com.basic.framework.common.utils.datatype.JsonUtils;
import com.basic.framework.gid.utils.sequence.GidClientUtils;
import com.basic.framework.messagequeue.service.MessageHandler;
import com.basic.framework.workflow.dao.WorkflowEngineDAO;
import com.basic.framework.workflow.define.CommandExecFlag;
import com.basic.framework.workflow.define.CommandType;
import com.basic.framework.workflow.define.GidCodes;
import com.basic.framework.workflow.exception.WorkflowException;
import com.basic.framework.workflow.model.WorkflowCommandBean;
import com.basic.framework.workflow.model.WorkflowCommandExecBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by lzj on 2017/7/21.
 */
@Component("workflowCommandListener")
public class WorkflowCommandListener implements MessageHandler{

    private static final Logger logger = LoggerFactory.getLogger(WorkflowCommandListener.class);
    private static final int EXEC_RESULT_LENGTH = 256;

    private static final JsonUtils JSON_UTILS = JsonUtils.getInstance();
    
    @Autowired
    private WorkflowEngine workflowEngine;

    @Autowired
    private WorkflowEngineDAO workflowEngineDAO;

    /**
     * 流程指令执行
     *
     * @param workflowCommandBean
     */
    public void commandExec(WorkflowCommandBean workflowCommandBean) {
        String commandType = workflowCommandBean.getCommandType();

        WorkflowCommandExecBean execBean = this.makeWorkFlowCommandExecBean(workflowCommandBean);
        try {
            if (CommandType.PROCESS_START.getCode().equals(commandType)) {//流程启动
                String processInstanceId = workflowEngine.processStart(workflowCommandBean.getProcessDefineKey(), workflowCommandBean.getBusiOrderId(), workflowCommandBean.getParamMap());
                execBean.setProcessInstanceId(processInstanceId);

            } else if (CommandType.PROCESS_JUMP.getCode().equals(commandType)) {//流程跳转
                workflowEngine.processJump(workflowCommandBean.getProcessInstanceId(),workflowCommandBean.getTaskId(),workflowCommandBean.getTargetTaskDefinitionKey(),workflowCommandBean.getParamMap());


            } else if (CommandType.TASK_ROLLBACK.getCode().equals(commandType)) {//任务回退
                workflowEngine.taskRollBack(workflowCommandBean.getProcessInstanceId(),workflowCommandBean.getTaskId(),workflowCommandBean.getParamMap());


            } else if (CommandType.TASK_COMPLETE.getCode().equals(commandType)) {//任务提交
                workflowEngine.taskComplete(workflowCommandBean.getProcessInstanceId(),workflowCommandBean.getTaskId(),workflowCommandBean.getParamMap());
            
            } else if (CommandType.PROCESS_SUSPEND.getCode().equals(commandType)) {//流程挂起
                workflowEngine.processSuspend(workflowCommandBean.getProcessInstanceId());
            }
            execBean.setExecFlag(CommandExecFlag.SUCCESS.getCode());

        } catch (WorkflowException e) {
            String jsonStr = JsonUtils.getInstance().objectToJson(workflowCommandBean);
            logger.error("流程指令执行异常,param:{}", jsonStr, e);
            execBean.setErrorCode(e.getErrorCode());
            execBean.setExecFlag(CommandExecFlag.FAILURE.getCode());
            String execResult = Exceptions.getStackTraceAsString(e,EXEC_RESULT_LENGTH);
            execBean.setExecResult(execResult);
        } finally {
            workflowEngineDAO.insertWorkflowCommandExec(execBean);
        }

    }

    /**
     * 生成 WorkflowCommandExecBean
     *
     * @param workflowCommandBean
     * @return
     */
    private WorkflowCommandExecBean makeWorkFlowCommandExecBean(WorkflowCommandBean workflowCommandBean) {

        WorkflowCommandExecBean workflowCommandExecBean = new WorkflowCommandExecBean();
        workflowCommandExecBean.setBusiOrderId(workflowCommandBean.getBusiOrderId());
        workflowCommandExecBean.setCommandId(workflowCommandBean.getCommandId());
        workflowCommandExecBean.setBusiOrderId(workflowCommandBean.getBusiOrderId());
        workflowCommandExecBean.setCommandType(workflowCommandBean.getCommandType());
        workflowCommandExecBean.setCreateTime(DateUtils.now());
        workflowCommandExecBean.setProcessDefineKey(workflowCommandBean.getProcessDefineKey());
        workflowCommandExecBean.setProcessInstanceId(workflowCommandBean.getProcessInstanceId());
        workflowCommandExecBean.setSerialNo(workflowCommandBean.getSerialNo());
        workflowCommandExecBean.setTaskId(workflowCommandBean.getTaskId());

        GidClientUtils gidClientUtils = GidClientUtils.getInstance();
        Long execId = gidClientUtils.getGidValue(GidCodes.WFE_SER_WORKFLOW_COMMAND_EXEC_SEQ);
        workflowCommandExecBean.setExecId(execId);
        return workflowCommandExecBean;
    }

	/* (non-Javadoc)
	 * @see com.basic.framework.messagequeue.service.MessageHandler#handle(java.lang.String)
	 */
	@Override
	public void handle(String message) {
		if (logger.isDebugEnabled()) {
			logger.debug("handle message:{}", message);
		}
		
		WorkflowCommandBean bean = JSON_UTILS.jsonToObject(message, WorkflowCommandBean.class);
		commandExec(bean);
	}


}
