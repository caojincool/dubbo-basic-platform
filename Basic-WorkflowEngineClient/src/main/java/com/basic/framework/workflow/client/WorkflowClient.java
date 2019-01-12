package com.basic.framework.workflow.client;

import com.basic.framework.common.utils.Exceptions;
import com.basic.framework.common.utils.datatype.DateUtils;
import com.basic.framework.common.utils.datatype.JsonUtils;
import com.basic.framework.gid.utils.sequence.GidClientUtils;
import com.basic.framework.messagequeue.impl.MessageSenderActiveMQ;
import com.basic.framework.workflow.dao.WorkflowClientDAO;
import com.basic.framework.workflow.define.CommandQueueCode;
import com.basic.framework.workflow.define.CommandSendFlag;
import com.basic.framework.workflow.define.CommandType;
import com.basic.framework.workflow.define.GidCodes;
import com.basic.framework.workflow.define.WorkflowError;
import com.basic.framework.workflow.exception.WorkflowException;
import com.basic.framework.workflow.model.WorkflowCommandBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 工作流引擎客户端
 * Created by lzj on 2017/7/21.
 */
@Component("workflowClient")
public class WorkflowClient {

    private static final Logger logger = LoggerFactory.getLogger(WorkflowClient.class);
    private static final int SEND_RESULT_LENGTH = 256;

    private static final JsonUtils JSON_UTILS = JsonUtils.getInstance();
    
    @Autowired
    private WorkflowClientDAO workflowClientDAO;
    
	@Autowired
	private MessageSenderActiveMQ messageSenderActiveMQ;

    /**
     * 启动流程
     * @param serialNo
     * @param processDefineKey
     * @param busiOrderId
     * @param paramMap
     */
    public void processStart(Long serialNo,String processDefineKey,Long busiOrderId,Map<String,Object> paramMap) throws WorkflowException {
        logger.info("启动流程,serialNo:{},processDefineKey:{},busiOrderId:{}",serialNo,processDefineKey,busiOrderId);

        WorkflowCommandBean bean = new WorkflowCommandBean();
        bean.setSerialNo(serialNo);
        bean.setProcessDefineKey(processDefineKey);
        bean.setBusiOrderId(busiOrderId);
        bean.setParamMap(paramMap);
        bean.setCommandType(CommandType.PROCESS_START.getCode());
        this.sendCommand(bean);//发送请求
    }

    /**
     * 流程跳转
     * @param serialNo
     * @param processInstanceId
     * @param targetTaskDefinitionKey
     * @param paramMap
     */
    public void processJump(Long serialNo,String processInstanceId,String taskId,String targetTaskDefinitionKey,Map<String,Object> paramMap) throws WorkflowException {
        logger.info("流程跳转,serialNo:{},processInstanceId:{},targetTaskDefinitionKey:{}",serialNo,processInstanceId,targetTaskDefinitionKey);
        WorkflowCommandBean bean = new WorkflowCommandBean();
        bean.setSerialNo(serialNo);
        bean.setProcessInstanceId(processInstanceId);
        bean.setTaskId(taskId);
        bean.setTargetTaskDefinitionKey(targetTaskDefinitionKey);
        bean.setParamMap(paramMap);
        bean.setCommandType(CommandType.PROCESS_JUMP.getCode());

        this.sendCommand(bean);//发送请求

        return;
    }


    /**
     * 流程任务提交
     * @param serialNo
     * @param taskId
     * @param paramMap
     */
    public void taskComplete(Long serialNo,String processInstanceId,String taskId,Map<String,Object> paramMap) throws WorkflowException {
        logger.info("流程任务提交,serialNo:{},taskId:{}",serialNo,taskId);

        WorkflowCommandBean bean = new WorkflowCommandBean();
        bean.setSerialNo(serialNo);
        bean.setProcessInstanceId(processInstanceId);
        bean.setTaskId(taskId);
        bean.setParamMap(paramMap);
        bean.setCommandType(CommandType.TASK_COMPLETE.getCode());

        this.sendCommand(bean);//发送请求

        return;
    }

    /**
     * 流程任务回退
     * @param serialNo
     * @param taskId
     * @param paramMap
     */
    public void taskRollback(Long serialNo,String processInstanceId,String taskId,Map<String,Object> paramMap) throws WorkflowException {
        logger.info("流程任务回退,serialNo:{},taskId:{}",serialNo,taskId);

        WorkflowCommandBean bean = new WorkflowCommandBean();
        bean.setSerialNo(serialNo);
        bean.setProcessInstanceId(processInstanceId);
        bean.setTaskId(taskId);
        bean.setParamMap(paramMap);
        bean.setCommandType(CommandType.TASK_ROLLBACK.getCode());
        this.sendCommand(bean);//发送请求
        return;
    }
    
    /**
     * 
     * @date 2017年8月18日 上午11:28:28
     * 
     * @Description: 流程挂起，流程作废
     * @param serialNo
     * @param processInstanceId
     * @throws WorkflowException
     *
     */
    public void processSuspend(Long serialNo,String processInstanceId) throws WorkflowException {
    	logger.info("流程挂起,serialNo:{},processInstanceId:{}",serialNo,processInstanceId);
    	
    	WorkflowCommandBean bean = new WorkflowCommandBean();
    	bean.setSerialNo(serialNo);
    	bean.setProcessInstanceId(processInstanceId);
//    	bean.setTaskId(taskId);
//    	bean.setParamMap(paramMap);
    	bean.setCommandType(CommandType.PROCESS_SUSPEND.getCode());
    	this.sendCommand(bean);//发送请求
    	return;
    }

    /**
     * 发送指令
     * @param bean
     * @throws Exception
     */
    private void sendCommand(WorkflowCommandBean bean) throws WorkflowException{
//        JsonUtils JSON_UTILS = JsonUtils.getInstance();
        String commandBeanJsonStr = JSON_UTILS.objectToJson(bean);
        logger.info("发送指令,commandBeanJsonStr:{}",commandBeanJsonStr);
        GidClientUtils gidClientUtils = GidClientUtils.getInstance();
        Long commandId = gidClientUtils.getGidValue(GidCodes.WFC_CLI_WORKFLOW_COMMAND_SEQ);
        bean.setCommandId(commandId);
        bean.setCreateTime(DateUtils.now());
        try{ //发送请求
            //写入队列
            bean.setSendFlag(CommandSendFlag.SUCCESS.getCode());
//            throw new Exception("ERROR?????????????");
            String key = bean.getCommandType();
            String message = JSON_UTILS.objectToJson(bean);
            messageSenderActiveMQ.sendMessage(key, CommandQueueCode.WORKFLOW_COMMAND_SENDCOMMAND_QUEUE.getCode(), message);
        }catch (Exception e){
            bean.setSendFlag(CommandSendFlag.FAILURE.getCode());
            String sendResult = Exceptions.getStackTraceAsString(e,SEND_RESULT_LENGTH);
            bean.setSendResult(sendResult);
            bean.setErrorCode(WorkflowError.SEND_COMMAND_ERROR_11001.getCode());
            throw new WorkflowException(WorkflowError.SEND_COMMAND_ERROR_11001.getCode(),null,e);
        }finally {

            workflowClientDAO.insertWorkflowCommand(bean);
        }
    }

}
