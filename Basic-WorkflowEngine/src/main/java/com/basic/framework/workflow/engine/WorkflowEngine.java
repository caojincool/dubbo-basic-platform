package com.basic.framework.workflow.engine;

import com.basic.framework.common.utils.datatype.DateUtils;
import com.basic.framework.common.utils.datatype.LongUtils;
import com.basic.framework.gid.utils.sequence.GidClientUtils;
import com.basic.framework.workflow.define.GidCodes;
import com.basic.framework.workflow.define.NoticeType;
import com.basic.framework.workflow.define.WorkflowError;
import com.basic.framework.workflow.exception.WorkflowException;
import com.basic.framework.workflow.model.WorkflowNoticeBean;
import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.pvm.PvmActivity;
import org.activiti.engine.impl.pvm.PvmTransition;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.impl.pvm.process.ProcessDefinitionImpl;
import org.activiti.engine.impl.pvm.process.TransitionImpl;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lzj on 2017/7/1.
 */
@Service("workflowEngine")
public class WorkflowEngine {

    private static final Logger logger = LoggerFactory.getLogger(WorkflowEngine.class);

    @Resource(name = "repositoryService")
    private RepositoryService repositoryService;

    @Resource(name = "runtimeService")
    private RuntimeService runtimeService;

    @Resource(name = "historyService")
    private HistoryService historyService;

    @Resource(name = "taskService")
    private TaskService taskService;

    @Autowired
    private WorkflowNoticeSender workflowNoticeSender;


    /**
     * @param processDefineKey
     * @param busiOrderId
     */
    public String processStart(String processDefineKey, Long busiOrderId, Map<String, Object> paramMap) throws WorkflowException {

        logger.debug("流程启动开始,processDefineKey:{},busiOrderId:{}", processDefineKey, busiOrderId);

        String processInstanceId = null;

        if(paramMap == null){
        	paramMap = new HashMap<>();
        }
        paramMap.put("BUSI_ORDER_ID", busiOrderId);
        try {
            if (processDefineKey != null) {
                ProcessDefinition pd = repositoryService.createProcessDefinitionQuery()
                        .processDefinitionKey(processDefineKey).latestVersion().singleResult();// 找到流程定义
                if (pd != null) {
                    logger.debug("找到流程定义:pd.getKey():{},pd.getName():{}", pd.getKey(), pd.getName());
                    ProcessInstance pi = this.runtimeService.startProcessInstanceByKey(pd.getKey(), paramMap);
                    processInstanceId = pi.getProcessInstanceId();
                    logger.debug("流程启动成功，processInstanceId:{}", processInstanceId);

                    WorkflowNoticeBean workflowNoticeBean = this.makeProcessStartNoticeBean(busiOrderId,processInstanceId);
                    workflowNoticeBean.setNoticeType(NoticeType.PROCESS_START.getCode());
                    workflowNoticeSender.sendNotice(workflowNoticeBean);
                    logger.info("流程启动发送通知成功，processInstanceId:{}", processInstanceId);

                } else {// 流程定义为空
                    logger.error("流程启动失败,无法根据流程模板编码找到模板,processDefineKey:{},busiOrderId:{}", processDefineKey, busiOrderId);
                    throw new WorkflowException(WorkflowError.PROCESS_START_ERROR_01002.getCode(), "流程启动失败processDefineKey:" + processDefineKey + ",busiOrderId:" + busiOrderId);
                }
            } else {// 模板编码为空
                logger.error("流程启动失败,流程模板编码为空,processDefineKey:{},busiOrderId:{}", processDefineKey, busiOrderId);
                throw new WorkflowException(WorkflowError.PROCESS_START_ERROR_01001.getCode(), "流程启动失败processDefineKey:" + processDefineKey + ",busiOrderId:" + busiOrderId);
            }
        } catch (Exception e) {
            logger.error("流程启动失败,来自引擎内部的错误,processDefineKey:{},busiOrderId:{}", processDefineKey, busiOrderId, e);
            throw new WorkflowException(WorkflowError.ENGINE_ERROR_10001.getCode(), "流程启动失败processDefineKey:" + processDefineKey + ",busiOrderId:" + busiOrderId, e);
        }
        return processInstanceId;
    }

    /**
     * 流程任务提交
     *
     * @param taskId
     * @param paramMap
     */
    public void taskComplete(String processInstanceId,String taskId, Map<String, Object> paramMap) throws WorkflowException {
        logger.debug("流程任务提交开始,taskId:{}", taskId);
        try {
            taskService.complete(taskId, paramMap);
            logger.info("流程任务提交完成,taskId:{}", taskId);
        } catch (Exception e) {
            logger.error("任务提交异常，taskId:{}", taskId, e);
            throw new WorkflowException(WorkflowError.ENGINE_ERROR_10001.getCode(), "任务提交异常 taskId:" + taskId, e);
        }
        return;
    }

    /**
     * 流程回退到上一环节
     * @param taskId
     * @throws WorkflowException
     */
    public  void taskRollBack(String processInstanceId,String taskId,Map<String, Object> paramMap)throws WorkflowException {
        try {
//            Map<String, Object> variables;
            // 取得当前任务
            HistoricTaskInstance currTask = historyService.createHistoricTaskInstanceQuery().taskId(taskId)
                    .singleResult();
            // 取得流程实例
            ProcessInstance instance = runtimeService.createProcessInstanceQuery().processInstanceId(currTask.getProcessInstanceId()).singleResult();
            if (instance == null) {
                //流程结束
            }
            
            List<String> keys = new ArrayList<>();
            List<HistoricTaskInstance> historicTaskInstances = historyService.createHistoricTaskInstanceQuery().processInstanceId(processInstanceId).list();
            for (HistoricTaskInstance item : historicTaskInstances) {
            	keys.add(item.getTaskDefinitionKey());
            }
//            paramMap = INSTANCE.getProcessVariables();
            // 取得流程定义
            ProcessDefinitionEntity definition = (ProcessDefinitionEntity) (repositoryService.getProcessDefinition(currTask
                    .getProcessDefinitionId()));

            if (definition == null) {

                //log.error("流程定义未找到");
                return ;
            }
            // 取得上一步活动
            ActivityImpl currActivity = ((ProcessDefinitionImpl) definition)
                    .findActivity(currTask.getTaskDefinitionKey());
            List<PvmTransition> nextTransitionList = currActivity
                    .getIncomingTransitions();
            
            //判断上一节点是否在已经存在过的历史任务，否则当成退单
            boolean flag = true;
            for (PvmTransition nextTransition : nextTransitionList) {
            	if(!keys.contains(nextTransition.getSource().getId())){
            		flag = false;
            	}
            }
            if(!flag){
            	Map<String, Object> map = runtimeService.getVariables(processInstanceId);
            	Long busiOrderId = LongUtils.valueOf(map.get("BUSI_ORDER_ID"));
                WorkflowNoticeBean workflowNoticeBean = this.makeProcessStartNoticeBean(busiOrderId,processInstanceId);
            	workflowNoticeBean.setNoticeType(NoticeType.PROCESS_RETURN_FINISH.getCode());
                workflowNoticeSender.sendNotice(workflowNoticeBean);
                logger.info("流程回退完成发送通知成功，processInstanceId:{}", processInstanceId);
                
                processSuspend(processInstanceId);
                return ;
            }
            
            // 清除当前活动的出口
            List<PvmTransition> oriPvmTransitionList = new ArrayList<PvmTransition>();
            List<PvmTransition> pvmTransitionList = currActivity
                    .getOutgoingTransitions();
            for (PvmTransition pvmTransition : pvmTransitionList) {
                oriPvmTransitionList.add(pvmTransition);
            }
            pvmTransitionList.clear();

            // 建立新出口
            List<TransitionImpl> newTransitions = new ArrayList<TransitionImpl>();
            for (PvmTransition nextTransition : nextTransitionList) {
                PvmActivity nextActivity = nextTransition.getSource();
                ActivityImpl nextActivityImpl = ((ProcessDefinitionImpl) definition)
                        .findActivity(nextActivity.getId());
                TransitionImpl newTransition = currActivity
                        .createOutgoingTransition();
                newTransition.setDestination(nextActivityImpl);
                newTransitions.add(newTransition);
            }
            // 完成任务
            List<Task> tasks = taskService.createTaskQuery()
                    .processInstanceId(instance.getId())
                    .taskDefinitionKey(currTask.getTaskDefinitionKey()).list();
            for (Task task : tasks) {
                taskService.complete(task.getId(), paramMap);
                historyService.deleteHistoricTaskInstance(task.getId());
            }
            // 恢复方向
            for (TransitionImpl transitionImpl : newTransitions) {
                currActivity.getOutgoingTransitions().remove(transitionImpl);
            }
            for (PvmTransition pvmTransition : oriPvmTransitionList) {
                pvmTransitionList.add(pvmTransition);
            }


            return ;
        } catch (Exception e) {

            throw new WorkflowException(WorkflowError.ENGINE_ERROR_10001.getCode(), "流程回退失败taskId:" + taskId, e);

        }
    }

    /**
     * 流程跳转
     * @param processInstanceId
     * @param targetTaskDefinitionKey
     * @param paramMap
     * @throws WorkflowException
     */
    public void processJump(String processInstanceId,String taskId,String targetTaskDefinitionKey,Map<String,Object> paramMap) throws WorkflowException{

        try {
//            Map<String, Object> variables;
            // 取得当前任务
            HistoricTaskInstance currTask = historyService.createHistoricTaskInstanceQuery().taskId(taskId).singleResult();
            // 取得流程实例
            ProcessInstance instance = runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
            if (instance == null) {
                //流程结束
            }
//            paramMap = INSTANCE.getProcessVariables();
            // 取得流程定义

            ProcessDefinitionEntity definition = (ProcessDefinitionEntity) (repositoryService.getProcessDefinition(instance.getProcessDefinitionId()));

            if (definition == null) {

                //log.error("流程定义未找到");
                return ;
            }
            ActivityImpl currActivity = ((ProcessDefinitionImpl) definition).findActivity(currTask.getTaskDefinitionKey());//当前节点
            ActivityImpl nextActivity = ((ProcessDefinitionImpl) definition) .findActivity(targetTaskDefinitionKey);//目标节点


            // 清除当前活动的出口
            List<PvmTransition> oriPvmTransitionList = new ArrayList<PvmTransition>();
            List<PvmTransition> pvmTransitionList = currActivity
                    .getOutgoingTransitions();
            for (PvmTransition pvmTransition : pvmTransitionList) {
                oriPvmTransitionList.add(pvmTransition);
            }
            pvmTransitionList.clear();

            //建立新的线条
            TransitionImpl newTransition = currActivity.createOutgoingTransition();
            newTransition.setDestination(nextActivity);


            // 完成任务
            List<Task> tasks = taskService.createTaskQuery()
                    .processInstanceId(instance.getId())
                    .taskDefinitionKey(currTask.getTaskDefinitionKey()).list();
            for (Task task : tasks) {
                taskService.complete(task.getId(), paramMap);
                historyService.deleteHistoricTaskInstance(task.getId());
            }
            // 恢复方向
            currActivity.getOutgoingTransitions().remove(newTransition);

            for (PvmTransition pvmTransition : oriPvmTransitionList) {
                pvmTransitionList.add(pvmTransition);
            }
           return ;
        } catch (Exception e) {

            throw new WorkflowException(WorkflowError.ENGINE_ERROR_10001.getCode(), "流程回退失败taskId:" + taskId, e);

        }
    }


    private WorkflowNoticeBean makeProcessStartNoticeBean(Long busiOrderId,String processInstanceId){
        WorkflowNoticeBean bean = new WorkflowNoticeBean();
        bean.setNoticeId(GidClientUtils.getInstance().getGidValue(GidCodes.WFE_SER_WORKFLOW_NOTICE_SEQ));
        bean.setCreateTime(DateUtils.now());
//        bean.setNoticeType(NoticeType.PROCESS_START.getCode());
        bean.setBusiOrderId(busiOrderId);
        bean.setProcessInstanceId(processInstanceId);
         return bean;
    }

    /**
     * 
     * @date 2017年8月16日 下午2:53:15
     * 
     * @Description: 挂起一个流程实例
     * @param processInstanceId
     * @throws WorkflowException
     *
     */
    public void processSuspend(String processInstanceId) throws WorkflowException {
        logger.debug("挂起一个流程实例开始,processInstanceId:{}", processInstanceId);
        try {
        	// 取得流程实例
//        ProcessInstance INSTANCE = runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
        	//挂起一个流程实例
        	runtimeService.suspendProcessInstanceById(processInstanceId);
            logger.info("挂起一个流程实例完成,processInstanceId:{}", processInstanceId);
        } catch (Exception e) {
            logger.error("挂起一个流程实例异常,processInstanceId:{},异常信息:{}", processInstanceId, e);
            throw new WorkflowException(WorkflowError.ENGINE_ERROR_10001.getCode(), "挂起一个流程实例异常 processInstanceId:" + processInstanceId, e);
        }
    }
    
}
